package vn.Access_Control_List.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.Access_Control_List.model.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    // Phương thức để lấy các bình luận cấp cao nhất (top-level) cho một bài viết cụ thể
    // và chỉ lấy các bình luận đã được duyệt.
    // Sử dụng @EntityGraph để tải eager mối quan hệ 'replies' và 'user'
    // Điều này giúp tránh vấn đề N+1 Query cho các bình luận con trực tiếp và thông tin user.
    @EntityGraph(attributePaths = {"replies", "user"})
    Page<CommentEntity> findByPostIdAndParentCommentIsNullAndIsApprovedTrue(Long postId, Pageable pageable);

}
