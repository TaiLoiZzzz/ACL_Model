package vn.Access_Control_List.mapper;

import org.springframework.stereotype.Component;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import vn.Access_Control_List.model.PostEntity;
import vn.Access_Control_List.model.UserEntity;
import vn.Access_Control_List.model.CategoriesEntity; // Import CategoriesEntity
import vn.Access_Control_List.model.PostHasCategoriesEntity; // Import PostHasCategoriesEntity
import vn.Access_Control_List.controller.Response.PostResponse;
import vn.Access_Control_List.controller.Response.UserPostResponse;
import vn.Access_Control_List.controller.Response.CategoriesResponse; // Import CategoriesResponse

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class PostMapper {

    // Phương thức map từ PostEntity sang PostResponse
    public PostResponse toPostResponse(PostEntity entity) {
        if (entity == null) {
            return null;
        }

        PostResponse response = new PostResponse(); // Sử dụng constructor không đối số
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        response.setContent(entity.getContent());
        response.setSlug(entity.getSlug());
        response.setStatus(entity.getStatus());

        if (entity.getUser() != null) {
            response.setUser(toUserPostResponse(entity.getUser()));
        }

        // Map Set<PostHasCategoriesEntity> sang Set<CategoriesResponse>
        if (entity.getCategories() != null && !entity.getCategories().isEmpty()) {
            response.setCategories(entity.getCategories().stream()
                    .map(phc -> toCategoriesResponse(phc.getCategory())) // Gọi phương thức map Categories
                    .collect(Collectors.toSet()));
        } else {
            response.setCategories(new HashSet<>()); //  không trả về null
        }

        return response;
    }

    // Phương thức map từ UserEntity sang UserPostResponse
    public UserPostResponse toUserPostResponse(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return new UserPostResponse(entity);
    }

    // Phương thức map từ CategoriesEntity sang CategoriesResponse
    public CategoriesResponse toCategoriesResponse(CategoriesEntity entity) {
        if (entity == null) {
            return null;
        }
        return new CategoriesResponse(entity);
    }

    // Phương thức map một danh sách PostEntity sang List<PostResponse>
    public List<PostResponse> toPostResponseList(List<PostEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toPostResponse)
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