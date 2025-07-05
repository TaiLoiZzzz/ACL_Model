package vn.Access_Control_List.model;

import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet; // Import for Set
import java.util.Objects;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false) // Important for entities inheriting from AbstractEntity
@NoArgsConstructor // Required by JPA, also useful
@AllArgsConstructor // Useful for full constructor, often used with @Builder
@Builder // Provides a fluent API for creating instances
@Entity // Marks this class as a JPA entity
@Table(name = "categories") // Maps this entity to the 'categories' table
public class CategoriesEntity extends AbstractEntity<Long> {

    @NotBlank(message = "Category name cannot be empty")
    @Size(min = 2, max = 100, message = "Category name must be between 2 and 100 characters")
    @Column(name = "category_name", unique = true, nullable = false)
    private String name;

    @Size(max = 3000, message = "Description cannot exceed 3000 characters")
    @Column(name ="description") // description can be null and doesn't need to be unique
    private String description;


    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<PostHasCategoriesEntity> postCategories = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        // FIX: The instanceof check and cast should be for CategoriesEntity, not UserEntity.
        if (!(o instanceof CategoriesEntity)) return false;
        CategoriesEntity that = (CategoriesEntity) o;
        // It's best practice to use the ID for equality if it's not null (persisted entity)
        // Otherwise, use a natural/business key (like 'name' for Category, which is unique)
        if (getId() != null) {
            return Objects.equals(getId(), that.getId());
        }
        return Objects.equals(getName(), that.getName()); // Use unique 'name' as business key if ID is null
    }

    @Override
    public int hashCode() {
        // FIX: Use ID if available, otherwise use a unique business key (like 'name')
        return getId() != null ? Objects.hash(getId()) : Objects.hash(getName());
    }
}