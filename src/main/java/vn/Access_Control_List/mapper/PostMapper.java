package vn.Access_Control_List.mapper;

import org.springframework.stereotype.Component; // Để Spring quản lý như một Bean
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl; // Import PageImpl
import vn.Access_Control_List.model.PostEntity;
import vn.Access_Control_List.model.UserEntity;
import vn.Access_Control_List.controller.Response.PostResponse;
import vn.Access_Control_List.controller.Response.UserPostResponse;

import java.util.List;
import java.util.stream.Collectors;

@Component // Đánh dấu là Spring Component để có thể @Autowired
public class PostMapper {

    public PostResponse toPostResponse(PostEntity entity) {
        if (entity == null) {
            return null;
        }
        // Sử dụng constructor của DTO để map
        return new PostResponse(entity);
    }

    // Phương thức map từ UserEntity sang UserPostResponse
    public UserPostResponse toUserPostResponse(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return new UserPostResponse(entity);
    }

    // Phương thức map một danh sách PostEntity sang List<PostResponse>
    public List<PostResponse> toPostResponseList(List<PostEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toPostResponse) // Sử dụng phương thức map từng đối tượng
                .collect(Collectors.toList());
    }

    // Phương thức map Page<PostEntity> sang Page<PostResponse>
    public Page<PostResponse> toPostResponsePage(Page<PostEntity> entityPage) {
        if (entityPage == null) {
            return Page.empty();
        }
        List<PostResponse> content = entityPage.getContent().stream()
                .map(this::toPostResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(content, entityPage.getPageable(), entityPage.getTotalElements());
    }


}