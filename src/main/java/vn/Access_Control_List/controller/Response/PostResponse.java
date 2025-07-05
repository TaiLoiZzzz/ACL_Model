package vn.Access_Control_List.controller.Response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import vn.Access_Control_List.model.PostEntity;
import vn.Access_Control_List.model.UserEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {
    private Long id; // Đổi từ int sang Long
    private String title;
    private String content;
    private String slug; // Thêm slug nếu bạn muốn hiển thị
    private String status;
    private UserPostResponse user; // Đã đổi từ 'users' sang 'user' và kiểu là UserPostResponse

        public PostResponse(PostEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.slug = entity.getSlug(); // Đảm bảo PostEntity có getSlug()
        this.status = entity.getStatus();
        if (entity.getUser() != null) {
            this.user = new UserPostResponse(entity.getUser()); // Sử dụng UserPostResponse
        }
    }
}