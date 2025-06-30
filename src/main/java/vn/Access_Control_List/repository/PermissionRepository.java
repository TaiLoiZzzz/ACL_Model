package vn.Access_Control_List.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.Access_Control_List.model.PermissionEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer> {
    
    Optional<PermissionEntity> findByName(String name);
    
    boolean existsByName(String name);
    
    List<PermissionEntity> findByResource(String resource);
    
    List<PermissionEntity> findByAction(String action);
    
    Optional<PermissionEntity> findByResourceAndAction(String resource, String action);
    
    boolean existsByResourceAndAction(String resource, String action);
} 