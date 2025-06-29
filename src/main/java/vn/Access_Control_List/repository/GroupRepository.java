package vn.Access_Control_List.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.Access_Control_List.model.GroupEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Integer> {
    
    Optional<GroupEntity> findByName(String name);
    
    boolean existsByName(String name);
    
    List<GroupEntity> findByIsActiveTrue();
    
    @Query("SELECT g FROM GroupEntity g LEFT JOIN FETCH g.users gu LEFT JOIN FETCH gu.user WHERE g.name = :name AND g.isActive = true")
    Optional<GroupEntity> findByNameWithUsers(@Param("name") String name);
    
    @Query("SELECT g FROM GroupEntity g LEFT JOIN FETCH g.users gu LEFT JOIN FETCH gu.user WHERE g.isActive = true")
    List<GroupEntity> findAllActiveWithUsers();
} 