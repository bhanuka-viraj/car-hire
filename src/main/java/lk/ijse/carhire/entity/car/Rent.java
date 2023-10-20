package lk.ijse.carhire.entity.car;

import jakarta.persistence.*;
import lk.ijse.carhire.entity.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rent {
    @Id
    private String id;

    @Column(nullable = false)
    private LocalDate fromDate;

    @Column(nullable = false)
    private LocalDate toDate;

    @Column(nullable = false)
    private double total;

    @Column(nullable = false)
    private boolean isReturn;

    @Column(nullable = false)
    private double balance;

    @Column(nullable = true)
    private Double refundableDeposit;

    @Column(nullable = true)
    private Double advancedPayment;

    @Column(nullable = false)
    private double perDayRent;

    @CreationTimestamp
    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_nic", nullable = false)
    private Customer customer;


}
