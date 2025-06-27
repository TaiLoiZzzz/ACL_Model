package vn.tayjava.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_permission")
public class Permission  extends AbstractEntity<Integer> {
private String name;
private String description;

@OneToMany(mappedBy = "permission")
private Set<RoleHasToPermission> roleHasToPermissions = new HashSet<>();
}
