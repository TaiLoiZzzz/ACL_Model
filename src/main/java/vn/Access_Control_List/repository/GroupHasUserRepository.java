package vn.Access_Control_List.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.Access_Control_List.model.GroupHasUserEntity;

@Repository
public interface GroupHasUserRepository extends JpaRepository<GroupHasUserEntity, Integer> {
    
    void deleteByGroupIdAndUserId(Integer groupId, Long userId);
    
    boolean existsByGroupIdAndUserId(Integer groupId, Long userId);
} 