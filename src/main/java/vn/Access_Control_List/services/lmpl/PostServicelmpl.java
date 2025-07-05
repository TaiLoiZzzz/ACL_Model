package vn.Access_Control_List.services.lmpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.Access_Control_List.controller.Request.PostRequest;
import vn.Access_Control_List.controller.Response.PostResponse;
import vn.Access_Control_List.mapper.PostMapper;
import vn.Access_Control_List.model.CategoriesEntity;
import vn.Access_Control_List.model.PostEntity;
import vn.Access_Control_List.model.PostHasCategoriesEntity;
import vn.Access_Control_List.model.UserEntity;
import vn.Access_Control_List.repository.CategoriesRepository;
import vn.Access_Control_List.repository.PostHasCategoriesRepository;
import vn.Access_Control_List.repository.PostRepository;
import vn.Access_Control_List.services.PostService;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostServicelmpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final CategoriesRepository categoriesRepository;
    private final PostHasCategoriesRepository postHasCategoriesRepository;

    @Override // Đảm bảo có @Override
    public boolean isPostAuthor(Long postId, String username) { // Đảm bảo là PUBLIC
        return postRepository.findById(postId)
                .map(post -> post.getUser().getUsername().equals(username))
                .orElse(false);
    }

    @Override
    public void DeletePost(Long Id) {
        PostEntity postDelete = postRepository.findById(Id).orElseThrow(() -> new RuntimeException("Bài viết không tìm thấy với ID: " + Id));
        postHasCategoriesRepository.deleteByPost(postDelete);
        postRepository.delete(postDelete);
    }

    @Override
    public PostResponse UpdateStatusPost(Long id, String Status) {
        PostEntity post =  postRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong Tim thay"));
        if (!Status.equals("draft") && !Status.equals("published") && !Status.equals("archived")) {
            throw new IllegalArgumentException("Trạng thái không hợp lệ: " + Status + ". Trạng thái hợp lệ là 'draft', 'published', hoặc 'archived'.");
        }
        post.setStatus(Status);
        PostEntity updatedPost = postRepository.save(post);
        return postMapper.toPostResponse(updatedPost);

    }

    @Override
    public Page<PostResponse> getPosts(Pageable pageable) {
        Page<PostEntity> posts = postRepository.findAll(pageable);
        return postMapper.toPostResponsePage(posts);
    }

    @Override
    public PostResponse getPostById(Long id) {
        return postRepository.findById(id).map(postMapper::toPostResponse).orElseThrow(()-> new RuntimeException("Khong Tim thay"));
    }

    @Override
    @Transactional
    public PostResponse CreatePost(PostRequest post, UserEntity user) {
        PostEntity postEntity = PostEntity.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .slug(post.getSlug())
                .status(post.getStatus())
                .user(user)
                .build();
        PostEntity savedPost = postRepository.save(postEntity);
        Set<PostHasCategoriesEntity> postHasCategoriesEntities = new HashSet<>();
        if ( post.getCategoryIds() != null  && !post.getCategoryIds().isEmpty()) {
            for (Integer categoryId : post.getCategoryIds()) {
                CategoriesEntity category = categoriesRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
                PostHasCategoriesEntity postHasCategories = PostHasCategoriesEntity.builder()
                        .post(savedPost)
                        .category(category)
                        .build();
                postHasCategoriesEntities.add(postHasCategories);
            }
            postHasCategoriesRepository.saveAll(postHasCategoriesEntities);
            savedPost.setCategories(postHasCategoriesEntities);

        }
        return postMapper.toPostResponse(savedPost);
    }

    @Override
    @Transactional // Đảm bảo phương thức này có @Transactional
    public PostResponse updatePost(Long id, PostRequest postRequest, String updaterUsername) {
        PostEntity existingPost = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bài viết không tìm thấy với ID: " + id));

        // Cập nhật các trường cơ bản của bài viết
        existingPost.setTitle(postRequest.getTitle());
        existingPost.setContent(postRequest.getContent());
        existingPost.setSlug(postRequest.getSlug());
        existingPost.setStatus(postRequest.getStatus());


        postHasCategoriesRepository.deleteByPost(existingPost);


        existingPost.getCategories().clear();

       Set<PostHasCategoriesEntity> newPostCategories = new HashSet<>();
        if (postRequest.getCategoryIds() != null && !postRequest.getCategoryIds().isEmpty()) {
            for (Integer categoryId : postRequest.getCategoryIds()) {
                CategoriesEntity category = categoriesRepository.findById(categoryId)
                        .orElseThrow(() -> new RuntimeException("Danh mục không tìm thấy với ID: " + categoryId));

                PostHasCategoriesEntity postHasCategory = PostHasCategoriesEntity.builder()
                        .post(existingPost)
                        .category(category)
                        .build();
                newPostCategories.add(postHasCategory); // Thêm vào Set tạm thời
            }
        }
        existingPost.setCategories(newPostCategories);


        PostEntity savedPost = postRepository.save(existingPost); // Lưu bài viết đã cập nhật
        PostResponse postResponse = postMapper.toPostResponse(savedPost);
        return postResponse;
    }

    // ... các phương thức khác ...



}
