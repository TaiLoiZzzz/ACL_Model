package vn.tayjava.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.JdbcTypeCode;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_user")
public class User extends AbstractEntity<Long> {

    @Column(name = "first_name", length = 100)
    private String firstName;


    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;

    @Column(length = 10)
    private String gender;

    @Column(length = 20)
    private String phone;

    @Column(length = 255, unique = true)
    private String email;

    @Column(length = 50, unique = true)
    private String username;

    @Column(columnDefinition = "text")
    private String password;

    @Column(length = 20)
    private String status;

    @Column(length = 50)
    private String type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "user")
    private Set<Address> addresses;

    public void saveAddresses(Address address) {
        if (addresses != null) {
            addresses = new HashSet<>();
        }
            addresses.add(address);
        address.setUser(this);
    }

    @OneToMany(mappedBy = "user")
    private Set<GroupHasUser> users;

    @OneToMany(mappedBy = "user")
    private Set<UserHasRole> roles;
}
