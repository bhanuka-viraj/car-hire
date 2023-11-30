package lk.ijse.carhire.tm.rent;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentListTm {
    private String id;
    private String custName;
    private String rentedCar;
    private LocalDate returningDate;
    private Double balanceAmnt;
    private Button btnDetails;
    private Button btnManage;
}
