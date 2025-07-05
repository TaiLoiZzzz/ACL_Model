package vn.Access_Control_List.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data // Giữ lại để có getters/setters
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "post_has_categories",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"post_id", "category_id"})
        })
public class PostHasCategoriesEntity extends AbstractEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY) // Thêm FetchType.LAZY
    @JoinColumn(name = "post_id", nullable = false) // Rõ ràng tên cột
    private PostEntity post;

    @ManyToOne(fetch = FetchType.LAZY) // Thêm FetchType.LAZY
    @JoinColumn(name = "category_id", nullable = false) // Rõ ràng tên cột
    private CategoriesEntity category;

    // QUAN TRỌNG: Triển khai equals() và hashCode() dựa trên post và category
    // để đảm bảo tính duy nhất của cặp này trong Set và trong DB.
    // Loại bỏ @EqualsAndHashCode(callSuper = false) của Lombok nếu bạn triển khai thủ công.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostHasCategoriesEntity that = (PostHasCategoriesEntity) o;
        // So sánh theo post và category
        return Objects.equals(post, that.post) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        // Hash code dựa trên post và category
        return Objects.hash(post, category);
    }

    // Thêm một phương thức tiện ích để thiết lập mối quan hệ hai chiều
    // Mặc dù Hibernate sẽ quản lý, nhưng việc này giúp rõ ràng hơn
    public void setPost(PostEntity post) {
        this.post = post;
        // Nếu bạn cần thêm PostHasCategoriesEntity vào Set của PostEntity ngay lập tức:
        // if (post != null && !post.getCategories().contains(this)) {
        //     post.getCategories().add(this);
        // }
    }

    public void setCategory(CategoriesEntity category) {
        this.category = category;
        // Tương tự cho CategoryEntity nếu bạn có Set<PostHasCategoriesEntity> trong đó
    }
}