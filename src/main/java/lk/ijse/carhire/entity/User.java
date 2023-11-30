package lk.ijse.carhire.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jasypt.util.password.BasicPasswordEncryptor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false,unique = true)
    private int contactNo;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    private Date createdDate;

    @UpdateTimestamp
    private Date updateDate;


    public void setPassword(String password) {
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        this.password = passwordEncryptor.encryptPassword(password);
    }


}
