package lk.ijse.carhire.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jasypt.util.password.BasicPasswordEncryptor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String firstName;

    private String lastName;

    private int contactNo;

    private String username;

    private String password;

    public boolean validatePassword(String enteredPassword) {
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

        System.out.println("Stored Hashed Password: " + this.password);
        System.out.println("Entered Password: " + enteredPassword);
        return passwordEncryptor.checkPassword(enteredPassword, this.password);
    }
}
