package vn.Access_Control_List.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.Access_Control_List.controller.Request.PostRequest;
import vn.Access_Control_List.controller.Response.PostResponse;
import vn.Access_Control_List.model.PostEntity;
import vn.Access_Control_List.model.UserEntity;

public interface PostService {
    public Page<PostResponse> getPosts(Pageable pageable);
    public PostResponse getPostsById(Long id);
    public PostResponse CreatePost(PostRequest post, UserEntity user);
    public PostResponse updatePost(Long id,PostRequest post, String name);
    public boolean isPostAuthor(Long postId, String username);  // Đảm bảo là PUBLIC
    public void DeletePost(Long Id);
    public PostResponse UpdateStatusPost(Long id, String Status);

}
