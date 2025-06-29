package vn.Access_Control_List.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.Access_Control_List.model.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
    Optional<UserEntity> findByUsername(String username);
    
    Optional<UserEntity> findByEmail(String email);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
    
    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.roles ur LEFT JOIN FETCH ur.role r LEFT JOIN FETCH r.permissions rp LEFT JOIN FETCH rp.permission WHERE u.username = :username AND u.isActive = true")
    Optional<UserEntity> findByUsernameWithRolesAndPermissions(@Param("username") String username);
} 