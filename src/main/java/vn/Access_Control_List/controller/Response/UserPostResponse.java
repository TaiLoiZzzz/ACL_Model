package vn.Access_Control_List.controller.Response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import vn.Access_Control_List.model.UserEntity; // Import UserEntity để map

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPostResponse { // Tên DTO mới cho User trong PostResponse
    private Long id;
    private String username;
    private String fullName;
    // Không bao gồm email hoặc các thông tin nhạy cảm khác nếu không cần thiết cho PostResponse
    // Constructor để map từ UserEntity sang UserPostResponse (mapping thủ công)
    public UserPostResponse(UserEntity entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.fullName = entity.getFullName();
    }
}