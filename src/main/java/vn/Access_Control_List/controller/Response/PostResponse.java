package vn.Access_Control_List.controller.Response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import vn.Access_Control_List.model.PostEntity;
import vn.Access_Control_List.model.UserEntity; // Có thể không cần nếu UserPostResponse đã xử lý
import vn.Access_Control_List.model.PostHasCategoriesEntity; // Cần để truy cập liên kết

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors; // Import Collectors

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private String slug;
    private String status;
    private UserPostResponse user;
    private Set<CategoriesResponse> categories; // Đã thêm Set<CategoriesResponse>

    // Constructor để map từ PostEntity sang PostResponse (mapping thủ công)
    public PostResponse(PostEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.slug = entity.getSlug();
        this.status = entity.getStatus();

        if (entity.getUser() != null) {
            this.user = new UserPostResponse(entity.getUser());
        }

        // Map Set<PostHasCategoriesEntity> sang Set<CategoriesResponse>
        if (entity.getCategories() != null && !entity.getCategories().isEmpty()) {
            this.categories = entity.getCategories().stream()
                    .map(phc -> new CategoriesResponse(phc.getCategory())) // Lấy CategoryEntity từ PostHasCategoriesEntity và map
                    .collect(Collectors.toSet());
        } else {
            this.categories = new HashSet<>(); // Khởi tạo Set rỗng nếu không có danh mục
        }
    }
}