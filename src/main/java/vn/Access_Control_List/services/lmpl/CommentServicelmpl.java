package vn.Access_Control_List.services.lmpl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.Access_Control_List.controller.Request.CommentRequest;
import vn.Access_Control_List.controller.Response.CommentResponse;
import vn.Access_Control_List.mapper.PostMapper;
import vn.Access_Control_List.model.CommentEntity;
import vn.Access_Control_List.model.PostEntity;
import vn.Access_Control_List.model.UserEntity;
import vn.Access_Control_List.repository.CommentRepository;
import vn.Access_Control_List.repository.PostRepository;
import vn.Access_Control_List.services.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServicelmpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    @Transactional(readOnly = true) // Đặt readOnly = true cho các thao tác đọc
    public Page<CommentResponse> getCommentsByPostId(Long postId, Pageable pageable) {
        // Kiểm tra xem bài viết có tồn tại không
        if (!postRepository.existsById(postId)) {
            throw new RuntimeException("Bài viết không tìm thấy với ID: " + postId);
        }

        // Lấy các bình luận cấp cao nhất (top-level) và đã được duyệt cho bài viết
        Page<CommentEntity> commentsPage = commentRepository.findByPostIdAndParentCommentIsNullAndIsApprovedTrue(postId, pageable);

        // Map Page<CommentEntity> sang Page<CommentResponse> bằng mapper
        return postMapper.toCommentResponsePage(commentsPage);
    }

    @Override
    @Transactional(readOnly = false)
    public CommentResponse createComment(Long postId, CommentRequest request, UserEntity author) {
        PostEntity post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Khong thay bai viet"));
        CommentEntity parentComment = null;
        //Kiem tra xem co Binh luan cha khong
        if ( request.getParentCommentId() != null ) {
            parentComment = commentRepository.findById(request.getParentCommentId()).orElseThrow(() -> new RuntimeException("Khong thay binh luan cha"));
            //kiem tra cha xem binh luan cha co thuoc bai viet khong
            if (!parentComment.getPost().getId().equals(postId)){
                throw new IllegalArgumentException("Bình luận cha không thuộc bài viết này.");
            }
        }
        CommentEntity commentEntity =CommentEntity.builder()
                .post(post)
                .user(author)
                .authorName(author.getFullName())
                .authorEmail(author.getEmail())
                .content(request.getContent())
                .parentComment(parentComment)
                .isApproved(true)
                .build();
        CommentEntity savedComment = commentRepository.save(commentEntity);
        return postMapper.toCommentResponse(savedComment);
    }

    @Override
    public void deleteComment(Long id) {
        // Kiểm tra xem bình luận có tồn tại không trước khi xóa
        if (!commentRepository.existsById(id)) {
            throw new RuntimeException("Bình luận không tìm thấy với ID: " + id);
        }
        // Xóa bình luận
        commentRepository.deleteById(id);
    }

    @Override
    public boolean isCommentAuthor(Long commentId, String username) {
        return commentRepository.findById(commentId)
                .map(comment -> comment.getUser() != null && comment.getUser().getUsername().equals(username))
                .orElse(false);
    }


}