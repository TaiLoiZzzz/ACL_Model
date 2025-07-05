package vn.Access_Control_List.model;

import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.HashSet; // Import cho Set
import java.util.Objects;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false) // Quan trọng để tránh lỗi với ID từ AbstractEntity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "post")
public class PostEntity extends AbstractEntity<Long> {

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 5, max = 255, message = "Title must be between 5 and 255 characters")
    @Column(name = "title", nullable = false)
    private String title;

    @NotBlank(message = "Content cannot be empty")
    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @NotBlank(message = "Slug cannot be empty")
    @Size(min = 5, max = 255, message = "Slug must be between 5 and 255 characters")
    @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$", message = "Slug must be lowercase alphanumeric, separated by hyphens")
    @Column(name = "slug", unique = true, nullable = false)
    private String slug;

    @NotBlank(message = "Status cannot be empty")
    @Pattern(regexp = "draft|published|archived", message = "Status must be 'draft', 'published', or 'archived'")
    @Column(name = "status", nullable = false)
    private String status;

    @NotNull(message = "Post must have an author")
    @ManyToOne(optional = false, fetch = FetchType.LAZY) // Thêm FetchType.LAZY
    @JoinColumn(name = "user_id", nullable = false) // Thêm JoinColumn để rõ ràng và đảm bảo nullable = false ở DB
    private UserEntity user;

    // Bỏ @NotNull vì một Set có thể rỗng mà vẫn hợp lệ
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PostHasCategoriesEntity> categories = new HashSet<>(); // Khởi tạo để tránh NullPointerException

    // Thêm cascade và orphanRemoval để quản lý lifecycle comment khi Post bị xóa
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<CommentEntity> comments = new HashSet<>(); // Khởi tạo để tránh NullPointerException

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        // Sửa lỗi: Phải kiểm tra instanceof PostEntity, không phải UserEntity
        if (!(o instanceof PostEntity)) return false;
        PostEntity that = (PostEntity) o;
        // Chỉ so sánh ID nếu ID không null, nếu không so sánh theo business key (slug)
        // Đây là cách an toàn hơn cho entities chưa được persist
        if (getId() != null) {
            return Objects.equals(getId(), that.getId());
        }
        return Objects.equals(getSlug(), that.getSlug()); // Dùng slug làm business key nếu ID chưa có
    }

    @Override
    public int hashCode() {
        // Sửa lỗi: Dùng ID nếu có, nếu không dùng business key (slug)
        return getId() != null ? Objects.hash(getId()) : Objects.hash(getSlug());
    }
}