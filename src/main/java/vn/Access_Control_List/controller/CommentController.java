package vn.Access_Control_List.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.HttpCodeStatusMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import vn.Access_Control_List.controller.Request.CommentRequest;
import vn.Access_Control_List.controller.Response.CommentResponse;
import vn.Access_Control_List.model.UserEntity;
import vn.Access_Control_List.services.CommentService;
import vn.Access_Control_List.services.UserService;
import vn.Access_Control_List.services.lmpl.CommentServicelmpl;

import java.util.List;

@RestController
@RequestMapping("/api/post") // Base path cho các endpoint liên quan đến bài viết
@RequiredArgsConstructor
@Tag(name = "Comment", description = "Quản lý Bình luận Blog")
public class CommentController {

    private final CommentServicelmpl commentService;
    private final UserService userService;

    @GetMapping("/{postId}/comments") // Endpoint: GET /api/posts/{postId}/comments
    @Operation(summary = "Lấy danh sách bình luận cho một bài viết cụ thể")
    public ResponseEntity<Page<CommentResponse>> getCommentsByPostId(
            @PathVariable Long postId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt,asc") String[] sort // Thường sắp xếp comment theo thời gian tạo
    ) {
        Sort sortCriteria;
        if (sort != null && sort.length == 2) {
            try {
                sortCriteria = Sort.by(Sort.Direction.fromString(sort[1].toUpperCase()), sort[0]);
            } catch (IllegalArgumentException e) {
                sortCriteria = Sort.by(Sort.Direction.ASC, "createdAt"); // Mặc định nếu lỗi
            }
        } else {
            sortCriteria = Sort.by(Sort.Direction.ASC, "createdAt"); // Mặc định sắp xếp theo thời gian tạo tăng dần
        }

        Pageable pageable = PageRequest.of(page, size, sortCriteria);

        Page<CommentResponse> commentsPage = commentService.getCommentsByPostId(postId, pageable);
        return ResponseEntity.ok(commentsPage);
    }

    @PostMapping("/{postId}/comments")
    @PreAuthorize("hasAuthority('comment:create')") // Yêu cầu quyền tạo bình luận
    @Operation(summary = "Binh luan bai viet theo ID")
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable Long postId,
            @Valid @RequestBody CommentRequest commentRequest
            ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Tìm UserEntity đầy đủ từ username
        UserEntity currentUser = userService.findUserByUserName(currentUsername)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng đã xác thực: " + currentUsername));

        CommentResponse newComment = commentService.createComment(postId, commentRequest, currentUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newComment); // Trả về 201 Created




    }
    @DeleteMapping("/comments/{id}") // Endpoint để xóa bình luận
    @Operation(summary = "Xóa một bình luận")
    @PreAuthorize("hasAuthority('comment:delete_any') or " +
            "(hasAuthority('comment:delete_own') and @commentServicelmpl.isCommentAuthor(#id, authentication.name))")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build(); // Trả về 204 No Content
    }
}