package lk.ijse.carhire.entity.customer;

import jakarta.persistence.*;
import lk.ijse.carhire.entity.car.Rent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
    @Id
    private String nic;

    @Column(nullable = false)
    private String Fstname;

    @Column(nullable = false)
    private String Lstname;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Rent> rents;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false, length =200 )
    private String addressPerm;

    @Column(nullable = false, length =200)
    private String addressPost;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String Country;

    @Column(nullable = false)
    private String province;

    @Column(nullable = false)
    private String Cnumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private double salary;

    @Column(nullable = false)
    private String gender;

    @CreationTimestamp
    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "update_date", nullable = false)
    private Date updateDate;
}
