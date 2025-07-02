package vn.Access_Control_List.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name ="group_has_permission")
public class GroupHasPermission extends AbstractEntity<Integer> {
    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private PermissionEntity permission;
}
