package lk.ijse.carhire.tm.car;


import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllCarTm {
    private String id;
    private String make;
    private String category;
    private String availability;
    private Double rentPerDay;
    private String remarks;
    private Button delete;
    private Button rent;
}
