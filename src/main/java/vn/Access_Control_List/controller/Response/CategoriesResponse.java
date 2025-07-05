package vn.Access_Control_List.controller.Response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import vn.Access_Control_List.model.CategoriesEntity; // Import CategoriesEntity để map

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriesResponse { // Đổi tên từ CategoryResponse sang CategoriesResponse (số nhiều)
    private Long id;
    private String name; // Tên danh mục (category_name)
    private String description;

    // Constructor để map từ CategoriesEntity sang CategoriesResponse (mapping thủ công)
    public CategoriesResponse(CategoriesEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName(); // Giả định CategoriesEntity có getName()
        this.description = entity.getDescription();
    }
}