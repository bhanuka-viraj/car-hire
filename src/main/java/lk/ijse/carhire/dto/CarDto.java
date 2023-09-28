package lk.ijse.carhire.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private String id;
    private String name;
    private String color;
    private int seats;
    private int yom;
    private String vehicleNumber;
    private double minDeposit;
    private float maxKmPerDay;
    private String remarks;
    private String category;

}
