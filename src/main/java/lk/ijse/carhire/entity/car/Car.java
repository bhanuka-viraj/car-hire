package lk.ijse.carhire.entity.car;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    private String id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private int seats;

    @Column(nullable = false)
    private int yom;

    @Column(nullable = false)
    private int qoh;

    @Column(nullable = false)
    private String vehicleNumber;

    private double minDeposit;

    private float maxKmPerDay;

    @Lob
    private String remarks;



}
