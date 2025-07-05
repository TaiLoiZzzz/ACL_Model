package vn.Access_Control_List.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.Access_Control_List.controller.Request.CommentRequest;
import vn.Access_Control_List.controller.Response.CommentResponse;
import vn.Access_Control_List.model.UserEntity;

public interface CommentService {
    Page<CommentResponse> getCommentsByPostId(Long postId, Pageable pageable);
    // ... các phương thức khác liên quan đến bình luận (tạo, cập nhật, xóa, duyệt) ...
    CommentResponse createComment(Long postId, CommentRequest request, UserEntity author); // Thêm phương thức này
    void deleteComment(Long id); // Phương thức xóa bình luận
    boolean isCommentAuthor(Long commentId, String username); // Phương thức hỗ trợ @PreAuthorize
}