package vn.Access_Control_List.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.Access_Control_List.model.PostEntity;
import vn.Access_Control_List.model.PostHasCategoriesEntity;

@Repository
public interface PostHasCategoriesRepository extends JpaRepository<PostHasCategoriesEntity,Long> {
    @Modifying // Bắt buộc khi thực hiện thao tác sửa đổi (INSERT, UPDATE, DELETE)
    @Transactional
    @Query("DELETE FROM PostHasCategoriesEntity phc WHERE phc.post = :post")
    void deleteByPost(@Param("post") PostEntity post);
}

