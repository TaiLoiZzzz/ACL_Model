package vn.Access_Control_List.controller.Response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import vn.Access_Control_List.model.CommentEntity; // Import CommentEntity để map

import java.util.Set;
import java.util.HashSet; // Import HashSet
import java.util.stream.Collectors; // Import Collectors

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {
    private Long id;
    private String content;
    private String authorName;
    private String authorEmail;
    private Boolean isApproved;
    private UserCommentResponse user; // Người dùng đã bình luận
    private Long parentCommentId; // ID của bình luận cha (nếu có)
    private Set<CommentResponse> replies; // Các bình luận con (lồng nhau)

    // Constructor mặc định, mapping sẽ được thực hiện trong PostMapper
    // Constructor này không gọi đệ quy để tránh StackOverflowError
    // nếu cây bình luận quá sâu, thay vào đó mapper sẽ quản lý đệ quy.
}