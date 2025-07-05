package vn.Access_Control_List.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import vn.Access_Control_List.controller.Request.PostRequest;
import vn.Access_Control_List.controller.Response.PostResponse;
import vn.Access_Control_List.model.PostEntity;
import vn.Access_Control_List.model.UserEntity;
import vn.Access_Control_List.services.PostService;
import vn.Access_Control_List.services.UserService;
import vn.Access_Control_List.services.lmpl.PostServicelmpl;

@RestController // Giữ lại @RestController ở đây
@RequestMapping("/api/post") // <<< THAY ĐỔI TẠI ĐÂY: Dùng @RequestMapping cho base path
@RequiredArgsConstructor
@Tag(name = "Post", description = "Post Mananger")
public class PostController {
    private final UserService userService;
    private final PostServicelmpl postService;


    @GetMapping("/get") // Endpoint này sẽ map tới /api/post/get
    @Operation(summary = "Lay tat ca post")
    public Page<PostResponse> GetPost(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort
    ){
        // Xử lý tham số sort
        // Đảm bảo xử lý trường hợp mảng sort không đủ phần tử để tránh lỗi IndexOutOfBoundsException
        Sort sortCriteria;
        if (sort != null && sort.length == 2) {
            try {
                sortCriteria = Sort.by(Sort.Direction.fromString(sort[1].toUpperCase()), sort[0]);
            } catch (IllegalArgumentException e) {
                // Xử lý nếu direction không hợp lệ, ví dụ: mặc định lại hoặc ném exception tùy ý
                sortCriteria = Sort.by(Sort.Direction.ASC, "id");
            }
        } else {
            sortCriteria = Sort.by(Sort.Direction.ASC, "id"); // Mặc định sắp xếp theo ID tăng dần
        }

        // Tạo đối tượng Pageable
        Pageable pageable = PageRequest.of(page, size, sortCriteria);

        // Gọi service để lấy dữ liệu đã phân trang
        return postService.getPosts(pageable);
    }
    @GetMapping("/get/{id}") // <<< Sửa đổi ở đây: dùng {id}
    @Operation(summary = "Lấy một bài post theo ID")
    private PostResponse GetPostById(@PathVariable("id") Long id){
        return postService.getPostsById(id);
    }


    @PostMapping("")
    @Operation(summary = "Tao bai viet moi")
    public ResponseEntity<PostResponse> CreatePost(@Valid @RequestBody PostRequest post){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        UserEntity currentUser = userService.findUserByUserName(currentUserName).orElseThrow(()->new RuntimeException("User not found"));
        PostResponse newPostResponse = postService.CreatePost(post, currentUser);
        return new ResponseEntity<>(newPostResponse, HttpStatus.CREATED);

    }

    @PutMapping("/{id}") // Endpoint để cập nhật bài viết
    @Operation(summary = "Cập nhật bài viết hiện có")
    // Phân quyền:
    // - hasAuthority('post:update_any'): Nếu người dùng có quyền cập nhật bất kỳ bài viết nào (Admin/Editor).
    // - (hasAuthority('post:update_own') and @postService.isPostAuthor(#id, authentication.name)):
    //   HOẶC người dùng có quyền cập nhật bài viết của chính mình VÀ người dùng hiện tại là tác giả của bài viết có ID là #id.
    //   '@postService' gọi một bean Spring tên là postService.
    //   '#id' là tham số ID từ @PathVariable.
    //   'authentication.name' là username của người dùng đã xác thực.
    @PreAuthorize("hasAuthority('post:update_any') or " +
            "(hasAuthority('post:update_own') and @postServicelmpl.isPostAuthor(#id, authentication.name))")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long id, @Valid @RequestBody PostRequest postRequest) {
        // Lấy thông tin người dùng hiện tại (để truyền vào service nếu cần ghi log người sửa, hoặc để service kiểm tra lại)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        PostResponse updatedPostResponse = postService.updatePost(id, postRequest, currentUsername);
        return ResponseEntity.ok(updatedPostResponse);
    }
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('post:delete_any') or " +  "(hasAnyAuthority('post:delete_own') and @postServicelmpl.isPostAuthor(#id,authentication.name))")
    public ResponseEntity<String > deletePost(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        postService.DeletePost(id);
        return ResponseEntity.ok("Da xoa");

    }
    @PutMapping("{id}/status")
    @PreAuthorize("(hasAnyAuthority('post:change_status') or @postServicelmpl.isPostAuthor(#id,authentication.name))")
    public ResponseEntity<PostResponse> changeStatus(@PathVariable Long id, @Valid @RequestBody String Status){
        PostResponse updatedPostResponse = postService.UpdateStatusPost(id, Status);
        return ResponseEntity.ok(updatedPostResponse);

    }
}