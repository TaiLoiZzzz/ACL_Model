package vn.Access_Control_List.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.Access_Control_List.config.JwtUtil;
import vn.Access_Control_List.model.RefreshTokenEntity;
import vn.Access_Control_List.model.UserEntity;
import vn.Access_Control_List.repository.RefreshTokenRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;

    public RefreshTokenEntity createRefreshToken(UserEntity user) {
        // Revoke all existing refresh tokens for this user
        refreshTokenRepository.revokeAllByUser(user);
        
        // Generate new refresh token
        String token = jwtUtil.generateRefreshToken(user.getUsername());
        Date expirationDate = jwtUtil.extractExpiration(token);
        LocalDateTime expiresAt = expirationDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        RefreshTokenEntity refreshToken = RefreshTokenEntity.builder()
                .token(token)
                .user(user)
                .expiresAt(expiresAt)
                .isRevoked(false)
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshTokenEntity validateRefreshToken(String token) {
        return refreshTokenRepository.findByTokenAndIsRevokedFalse(token)
                .filter(refreshToken -> refreshToken.getExpiresAt().isAfter(LocalDateTime.now()))
                .orElseThrow(() -> new RuntimeException("Invalid or expired refresh token"));
    }

    public void revokeRefreshToken(String token) {
        refreshTokenRepository.findByToken(token)
                .ifPresent(refreshToken -> {
                    refreshToken.setIsRevoked(true);
                    refreshTokenRepository.save(refreshToken);
                });
    }

    public void revokeAllUserTokens(UserEntity user) {
        refreshTokenRepository.revokeAllByUser(user);
    }

    public void deleteExpiredTokens() {
        refreshTokenRepository.deleteExpiredTokens(LocalDateTime.now());
    }
} 