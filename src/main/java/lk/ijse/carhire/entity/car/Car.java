package lk.ijse.carhire.entity.car;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String vehicleNumber;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private int seats;

    @Column(nullable = false)
    private int yom;

    @Column(nullable = false)
    private double pricePerDay;

    @Column(nullable = true)
    private Double minDeposit;

    @Column(nullable = true)
    private int maxKmPerDay;

    @Lob
    private String remarks;

    @Column(nullable = false)
    private boolean isRented;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private CarCategory category;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "update_date", nullable = false)
    private Date updateDate;




}
