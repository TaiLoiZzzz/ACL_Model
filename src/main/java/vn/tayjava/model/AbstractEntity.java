package vn.tayjava.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@MappedSuperclass
public abstract class AbstractEntity<T> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  T id;

    @CreatedBy
    @Column(name ="created_by")
    private T createdBy;

    @LastModifiedBy
    @Column(name ="updated_by")
    private T updatedBy;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDate createdAd;

    @Column(name ="updated_at")
    @UpdateTimestamp
    private LocalDate updatedAd;




}
