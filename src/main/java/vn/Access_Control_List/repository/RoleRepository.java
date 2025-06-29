package vn.Access_Control_List.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.Access_Control_List.model.RoleEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    
    Optional<RoleEntity> findByName(String name);
    
    boolean existsByName(String name);
    
    List<RoleEntity> findByIsActiveTrue();
    
    @Query("SELECT r FROM RoleEntity r LEFT JOIN FETCH r.permissions rp LEFT JOIN FETCH rp.permission WHERE r.name = :name AND r.isActive = true")
    Optional<RoleEntity> findByNameWithPermissions(@Param("name") String name);
    
    @Query("SELECT r FROM RoleEntity r LEFT JOIN FETCH r.permissions rp LEFT JOIN FETCH rp.permission WHERE r.isActive = true")
    List<RoleEntity> findAllActiveWithPermissions();
} 