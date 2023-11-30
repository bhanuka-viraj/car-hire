package lk.ijse.carhire.dto;

import lk.ijse.carhire.entity.car.CarCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private String id;
    private String vehicleNumber;
    private String make;
    private String color;
    private int seats;
    private int yom;
    private double pricePerDay;
    private double minDeposit;
    private int maxKmPerDay;
    private boolean isRented;
    private String remarks;
    private String category;

}
