package lk.ijse.carhire.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private String nic;
    private String Fstname;
    private String Lstname;
    private LocalDate dob;
    private String addressPerm;
    private String addressPost;
    private String postalCode;
    private String city;
    private String Country;
    private String province;
    private String Cnumber;
    private String email;
    private double salary;
    private String gender;

}
