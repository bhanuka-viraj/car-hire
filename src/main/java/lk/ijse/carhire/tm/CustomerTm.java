package lk.ijse.carhire.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerTm {
    private String nic;
    private String FstName;
    private String addressPerm;
    private String Cnumber;
    private String email;
    private Button btnDelete;
    private Button btnUpdate;
}
