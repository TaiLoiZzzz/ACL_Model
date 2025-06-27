package vn.tayjava.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_user")
@Data // Tự động sinh getter/setter/toString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(length = 10)
    private String gender;

    @Column(length = 20)
    private String phone;

    @Column(length = 255, unique = true)
    private String email;

    @Column(length = 50, unique = true)
    private String username;

    @Column(columnDefinition = "text")
    private String password;

    @Column(length = 20)
    private String status;

    @Column(length = 50)
    private String type;

    @Column(name = "created_at", columnDefinition = "timestamp")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "timestamp")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
