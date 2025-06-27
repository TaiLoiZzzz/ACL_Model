package vn.tayjava.model;

import jakarta.persistence.*;
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
@Table(name = "tbl_group")
public class Group extends AbstractEntity<Integer> {
    private String name;
    private String description;

    @OneToOne
    private Role role;

    @OneToMany(mappedBy = "group")
    private Set<GroupHasUser> groupHasUsers = new HashSet<>();

}
