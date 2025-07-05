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
public class UserCommentResponse {
    private Long id;
    private String username;
    private String fullName;

    // Constructor để map thủ công từ UserEntity
    public UserCommentResponse(UserEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.username = entity.getUsername();
            this.fullName = entity.getFullName();
        }
    }
}