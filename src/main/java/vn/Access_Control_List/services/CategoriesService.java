package vn.Access_Control_List.services;

import vn.Access_Control_List.controller.Request.CategoriesRequest;
import vn.Access_Control_List.controller.Response.CategoriesResponse;

import java.util.List;

public interface CategoriesService {
    List<CategoriesResponse> getAllCategories();
    CategoriesResponse getCategoriesById(int id);
    CategoriesResponse createCategories(CategoriesRequest request);
    CategoriesResponse updateCategory(Long id, CategoriesRequest request); // Phương thức cập nhật
    void deleteCategory(Long id); // Phương thức xóa
}
