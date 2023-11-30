package lk.ijse.carhire.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentDto {
    private String id;
    private LocalDate fromDate;
    private LocalDate toDate;
    private double total;
    private boolean isReturn;
    private double balance;
    private Double refundableDeposit;
    private Double advancedPayment;
    private double perDayRent;
    private double overdueAmt;
    private CarDto carDto;
    private CustomerDto customerDto;
}
