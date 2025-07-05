package vn.Access_Control_List.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.Access_Control_List.controller.Request.CategoriesRequest;
import vn.Access_Control_List.controller.Response.CategoriesResponse;
import vn.Access_Control_List.services.CategoriesService;
import vn.Access_Control_List.services.lmpl.CategoriesServiceimpl;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoriesController {
    private final CategoriesServiceimpl categoriesService;
    @GetMapping // Endpoint: GET /api/categories
    @Operation(summary = "Lấy tất cả danh mục")
    public ResponseEntity<List<CategoriesResponse>> getAllCategories() {
        List<CategoriesResponse> categories = categoriesService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("{id}")
    @Operation(summary = "Lay chi tiet 1 danh muc")
    @PreAuthorize("hasAuthority('category:read')")
    public ResponseEntity<CategoriesResponse> getCategoriesById(@PathVariable("id") int id) {
        CategoriesResponse categories = categoriesService.getCategoriesById(id);
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    @Operation(summary = "Tao moi ")
    @PreAuthorize("hasAuthority('category:create')")
    public ResponseEntity<CategoriesResponse> createCategories(@RequestBody CategoriesRequest categoriesRequest) {
       CategoriesResponse categoriesResponse = categoriesService.createCategories(categoriesRequest);
       return ResponseEntity.ok(categoriesResponse);
    }

    @PutMapping("/{id}") // Endpoint để cập nhật danh mục
    @Operation(summary = "Cập nhật một danh mục hiện có")
    @PreAuthorize("hasAuthority('category:update')") // Yêu cầu quyền cập nhật danh mục
    public ResponseEntity<CategoriesResponse> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoriesRequest request) {
        CategoriesResponse updatedCategory = categoriesService.updateCategory(id, request);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}") // Endpoint để xóa danh mục
    @Operation(summary = "Xóa một danh mục")
    @PreAuthorize("hasAuthority('category:delete')") // Yêu cầu quyền xóa danh mục
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoriesService.deleteCategory(id);
        return ResponseEntity.noContent().build(); // Trả về 204 No Content
    }



}
