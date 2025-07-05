package vn.Access_Control_List.services.lmpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.Access_Control_List.controller.Request.CategoriesRequest;
import vn.Access_Control_List.controller.Response.CategoriesResponse;
import vn.Access_Control_List.mapper.PostMapper;
import vn.Access_Control_List.model.CategoriesEntity;
import vn.Access_Control_List.repository.CategoriesRepository;
import vn.Access_Control_List.services.CategoriesService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoriesServiceimpl implements CategoriesService {
    private final CategoriesRepository categoriesRepository;
    private final PostMapper postMapper;
    @Override
    public List<CategoriesResponse> getAllCategories() {
        List<CategoriesEntity>  categories = categoriesRepository.findAll();
     return  categories.stream()
             .map(postMapper::toCategoriesResponse)
             .collect(Collectors.toList());
    }

    @Override
    public CategoriesResponse getCategoriesById(int id) {
        return postMapper.toCategoriesResponse(categoriesRepository.findById(id).get());
    }



    @Override
    @Transactional // Đảm bảo phương thức này có @Transactional
    public CategoriesResponse createCategories(CategoriesRequest request) {
        // 1. Tạo CategoriesEntity từ CategoriesRequest DTO
        CategoriesEntity newCategory = CategoriesEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        CategoriesEntity savedCategory = categoriesRepository.save(newCategory);

        return postMapper.toCategoriesResponse(savedCategory);
    }

    @Override
    @Transactional // Đảm bảo có @Transactional cho thao tác cập nhật
    public CategoriesResponse updateCategory(Long id, CategoriesRequest request) {
        CategoriesEntity existingCategory = categoriesRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("Danh mục không tìm thấy với ID: " + id));

        // Cập nhật các trường
        existingCategory.setName(request.getName());
        existingCategory.setDescription(request.getDescription());

        CategoriesEntity updatedCategory = categoriesRepository.save(existingCategory);
        return postMapper.toCategoriesResponse(updatedCategory);
    }

    @Override
    @Transactional // Đảm bảo có @Transactional cho thao tác xóa
    public void deleteCategory(Long id) {
        // Kiểm tra xem danh mục có tồn tại không trước khi xóa
        if (!categoriesRepository.existsById(Math.toIntExact(id))) {
            throw new RuntimeException("Danh mục không tìm thấy với ID: " + id);
        }
        // Xóa danh mục
        categoriesRepository.deleteById(Math.toIntExact(id));
    }


}
