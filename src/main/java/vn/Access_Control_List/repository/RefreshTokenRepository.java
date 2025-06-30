package vn.Access_Control_List.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.Access_Control_List.model.RefreshTokenEntity;
import vn.Access_Control_List.model.UserEntity;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    
    Optional<RefreshTokenEntity> findByToken(String token);
    
    Optional<RefreshTokenEntity> findByTokenAndIsRevokedFalse(String token);
    
    @Modifying
    @Query("DELETE FROM RefreshTokenEntity rt WHERE rt.user = :user")
    void deleteByUser(@Param("user") UserEntity user);
    
    @Modifying
    @Query("DELETE FROM RefreshTokenEntity rt WHERE rt.expiresAt < :now")
    void deleteExpiredTokens(@Param("now") LocalDateTime now);
    
    @Modifying
    @Query("UPDATE RefreshTokenEntity rt SET rt.isRevoked = true WHERE rt.user = :user")
    void revokeAllByUser(@Param("user") UserEntity user);
    
    boolean existsByTokenAndIsRevokedFalse(String token);
} 