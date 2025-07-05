package vn.Access_Control_List.model;

import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.HashSet; // Import cho Set nếu dùng
import java.util.Set; // Import cho Set nếu dùng

@Data
@EqualsAndHashCode(callSuper = false) // Quan trọng để tránh lỗi với ID từ AbstractEntity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comments")
public class CommentEntity extends AbstractEntity<Long> {

    @NotNull(message = "Comment must be associated with a post")
    @ManyToOne(fetch = FetchType.LAZY) // Thêm FetchType.LAZY để tối ưu hiệu suất
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    // Lưu ý: Nếu cho phép bình luận ẩn danh, hãy bỏ @NotNull và nullable = false
    // Hiện tại đang cấu hình KHÔNG cho phép bình luận ẩn danh (bắt buộc có user)
    @NotNull(message = "Comment must have an author (user)")
    @ManyToOne(fetch = FetchType.LAZY) // Thêm FetchType.LAZY
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Size(max = 100, message = "Author name cannot exceed 100 characters")
    @Column(name = "author_name")
    private String authorName; // Chỉ dùng nếu user là null (bình luận ẩn danh)

    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Author email cannot exceed 100 characters")
    @Column(name = "author_email")
    private String authorEmail; // Chỉ dùng nếu user là null

    @NotBlank(message = "Comment content cannot be empty")
    @Size(min = 5, message = "Comment content must be at least 5 characters long")
    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    // Mối quan hệ tự tham chiếu cho bình luận lồng nhau (nested comments)
    @ManyToOne(fetch = FetchType.LAZY) // Thêm FetchType.LAZY
    @JoinColumn(name = "parent_comment_id") // parent_comment_id có thể là NULL cho comment cấp cao nhất
    private CommentEntity parentComment;

    // Mối quan hệ một-nhiều để lấy các comment con (replies)
    // mappedBy="parentComment" trỏ đến trường 'parentComment' trong chính CommentEntity này
    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<CommentEntity> replies = new HashSet<>(); // Khởi tạo để tránh NullPointerException

    @NotNull(message = "Approval status cannot be null")
    @Column(name = "is_approved", nullable = false)
    private Boolean isApproved = false; // Mặc định là false, cần duyệt
}