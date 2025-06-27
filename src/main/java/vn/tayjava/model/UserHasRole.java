package vn.tayjava.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_user_has_role")
public class UserHasRole extends AbstractEntity<Integer> {

    @JoinColumn(name ="user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name ="role_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

}
