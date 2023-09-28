package lk.ijse.carhire.entity.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Customer")
public class Customer {
    @Id
    private String nic;

    @Column(nullable = false)
    private String Fstname;

    @Column(nullable = false)
    private String Lstname;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false, columnDefinition = "VARCHAR(200)")
    private String addressPerm;

    @Column(nullable = false, columnDefinition = "VARCHAR(200)")
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
}
