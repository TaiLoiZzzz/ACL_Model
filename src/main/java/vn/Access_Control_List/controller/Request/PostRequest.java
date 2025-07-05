package vn.Access_Control_List.controller.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotEmpty; // Thêm import này cho List/Set

import java.util.Set; // Sử dụng Set cho categoryIds để tránh trùng lặp

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequest {

    @NotBlank(message = "Tiêu đề không được để trống")
    @Size(min = 5, max = 255, message = "Tiêu đề phải từ 5 đến 255 ký tự")
    private String title;

    @NotBlank(message = "Nội dung không được để trống")
    private String content;

    @NotBlank(message = "Slug không được để trống")
    @Size(min = 5, max = 255, message = "Slug phải từ 5 đến 255 ký tự")
    @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$", message = "Slug phải là chữ cái thường, số, và dấu gạch ngang")
    private String slug;

    @NotBlank(message = "Trạng thái không được để trống")
    @Pattern(regexp = "draft|published|archived", message = "Trạng thái phải là 'draft', 'published', hoặc 'archived'")
    private String status;

    @NotEmpty(message = "Bài viết phải có ít nhất một danh mục") // Bắt buộc phải có danh mục
    private Set<Integer> categoryIds; // Danh sách ID của các danh mục liên quan
}