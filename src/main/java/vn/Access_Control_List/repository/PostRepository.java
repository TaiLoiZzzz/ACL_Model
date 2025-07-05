package vn.Access_Control_List.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.Access_Control_List.model.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

}