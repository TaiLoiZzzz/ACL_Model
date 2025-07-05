package vn.Access_Control_List.controller.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRequest {

    @NotBlank(message = "Nội dung bình luận không được để trống")
    @Size(min = 5, message = "Nội dung bình luận phải có ít nhất 5 ký tự")
    private String content;

    @Size(max = 100, message = "Tên tác giả không được vượt quá 100 ký tự")
    private String authorName;

    @Email(message = "Email tác giả không hợp lệ")
    @Size(max = 100, message = "Email tác giả không được vượt quá 100 ký tự")
    private String authorEmail;

    private Long parentCommentId;
}