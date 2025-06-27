package vn.tayjava.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address extends AbstractEntity<Long> {

    @Column(name = "apartment_number", length = 50)
    private String apartmentNumber;

    @Column(name = "floor", length = 10)
    private String floor;

    @Column(name = "building", length = 100)
    private String building;

    @Column(name = "street_number", length = 50)
    private String streetNumber;

    @Column(name = "street", length = 100)
    private String street;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "address_type", length = 50)
    private String addressType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_address_user"))
    private User user;
}
