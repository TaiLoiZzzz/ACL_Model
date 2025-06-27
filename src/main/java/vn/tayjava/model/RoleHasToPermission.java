package vn.tayjava.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_role_has_permission")
public class RoleHasToPermission  extends AbstractEntity<Integer> {
    @ManyToOne
    @JoinColumn(name ="role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name ="permission _id")
    private Permission permission;
}
