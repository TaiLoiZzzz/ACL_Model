package vn.Access_Control_List.mapper;

import org.springframework.stereotype.Component;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import vn.Access_Control_List.controller.Response.*;
import vn.Access_Control_List.model.*;

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
    // Phương thức map Page<CommentEntity> sang Page<CommentResponse>
    public Page<CommentResponse> toCommentResponsePage(Page<CommentEntity> entityPage) {
        if (entityPage == null) {
            return Page.empty();
        }
        List<CommentResponse> content = entityPage.getContent().stream()
                .map(this::toCommentResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(content, entityPage.getPageable(), entityPage.getTotalElements());
    }

    // Phương thức map từ UserEntity sang UserCommentResponse
    public UserCommentResponse toUserCommentResponse(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return new UserCommentResponse(entity);
    }

    // Phương thức map từ CommentEntity sang CommentResponse (đệ quy)
    public CommentResponse toCommentResponse(CommentEntity entity) {
        if (entity == null) {
            return null;
        }

        CommentResponse response = new CommentResponse();
        response.setId(entity.getId());
        response.setContent(entity.getContent());
        response.setAuthorName(entity.getAuthorName());
        response.setAuthorEmail(entity.getAuthorEmail());
        response.setIsApproved(entity.getIsApproved());

        if (entity.getUser() != null) {
            response.setUser(toUserCommentResponse(entity.getUser()));
        }

        if (entity.getParentComment() != null) {
            response.setParentCommentId(entity.getParentComment().getId());
        }

        // Map các bình luận con (replies) một cách đệ quy
        // Đảm bảo CommentEntity.getReplies() đã được tải (FetchType.EAGER hoặc trong cùng transaction)
        // để tránh LazyInitializationException.
        if (entity.getReplies() != null && !entity.getReplies().isEmpty()) {
            response.setReplies(entity.getReplies().stream()
                    .map(this::toCommentResponse) // Gọi đệ quy phương thức này
                    .collect(Collectors.toSet()));
        } else {
            response.setReplies(new HashSet<>());
        }

        return response;
    }

}