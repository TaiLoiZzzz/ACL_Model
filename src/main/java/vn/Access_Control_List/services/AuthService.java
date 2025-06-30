package vn.Access_Control_List.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.Access_Control_List.config.CustomUserDetails;
import vn.Access_Control_List.config.JwtUtil;
import vn.Access_Control_List.controller.Request.LoginRequest;
import vn.Access_Control_List.controller.Request.RefreshTokenRequest;
import vn.Access_Control_List.controller.Request.RegisterRequest;
import vn.Access_Control_List.controller.Response.AuthResponse;
import vn.Access_Control_List.model.RefreshTokenEntity;
import vn.Access_Control_List.model.RoleEntity;
import vn.Access_Control_List.model.UserEntity;
import vn.Access_Control_List.model.UserHasRoleEntity;
import vn.Access_Control_List.repository.RoleRepository;
import vn.Access_Control_List.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final RoleRepository roleRepository;


    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UserEntity user = userDetails.getUser();

        // Extract roles and permissions
        Set<String> roles = user.getRoles().stream()
                .map(userRole -> userRole.getRole().getName())
                .collect(Collectors.toSet());

        Set<String> permissions = user.getRoles().stream()
                .flatMap(userRole -> userRole.getRole().getPermissions().stream())
                .map(rolePermission -> rolePermission.getPermission().getName())
                .collect(Collectors.toSet());

        // Generate JWT token with user info
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("userId", user.getId());
        extraClaims.put("email", user.getEmail());
        extraClaims.put("roles", roles);
        extraClaims.put("permissions", permissions);

        String accessToken = jwtUtil.generateToken(user.getUsername(), extraClaims);
        RefreshTokenEntity refreshToken = refreshTokenService.createRefreshToken(user);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .roles(roles)
                .permissions(permissions)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        // 1. Kiểm tra trùng username/email
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username is already taken");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already in use");
        }

        // 2. Lấy role mặc định VIEWER
        RoleEntity viewerRole = roleRepository.findByNameWithPermissions("VIEWER")
                .orElseThrow(() -> new RuntimeException("Default role VIEWER not found"));

        // 3. Tạo UserEntity (chưa lưu ngay)
        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .isActive(true)
                .build();

        // 4. Tạo bản ghi trung gian UserHasRole
        UserHasRoleEntity userRole = UserHasRoleEntity.builder()
                .user(user)
                .role(viewerRole)
                .build();

        user.setRoles(Set.of(userRole));

        // 5. Lưu user + role
        user = userRepository.save(user);

        // 6. Lấy roles & permissions để đưa vào token
        Set<String> roles = Set.of(viewerRole.getName());
        Set<String> permissions = viewerRole.getPermissions().stream()
                .map(p -> p.getPermission().getName())
                .collect(Collectors.toSet());

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("userId", user.getId());
        extraClaims.put("email", user.getEmail());
        extraClaims.put("roles", roles);
        extraClaims.put("permissions", permissions);

        String accessToken = jwtUtil.generateToken(user.getUsername(), extraClaims);
        RefreshTokenEntity refreshToken = refreshTokenService.createRefreshToken(user);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .roles(roles)
                .permissions(permissions)
                .build();
    }


    public AuthResponse refreshToken(RefreshTokenRequest request) {
        RefreshTokenEntity refreshTokenEntity = refreshTokenService.validateRefreshToken(request.getRefreshToken());
        UserEntity user = refreshTokenEntity.getUser();

        // Extract roles and permissions
        Set<String> roles = user.getRoles().stream()
                .map(userRole -> userRole.getRole().getName())
                .collect(Collectors.toSet());

        Set<String> permissions = user.getRoles().stream()
                .flatMap(userRole -> userRole.getRole().getPermissions().stream())
                .map(rolePermission -> rolePermission.getPermission().getName())
                .collect(Collectors.toSet());

        // Generate new access token
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("userId", user.getId());
        extraClaims.put("email", user.getEmail());
        extraClaims.put("roles", roles);
        extraClaims.put("permissions", permissions);

        String accessToken = jwtUtil.generateToken(user.getUsername(), extraClaims);
        
        // Generate new refresh token
        RefreshTokenEntity newRefreshToken = refreshTokenService.createRefreshToken(user);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(newRefreshToken.getToken())
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .roles(roles)
                .permissions(permissions)
                .build();
    }

    public void logout(String refreshToken) {
        refreshTokenService.revokeRefreshToken(refreshToken);
    }
} 