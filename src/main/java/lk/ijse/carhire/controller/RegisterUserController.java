package lk.ijse.carhire.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.carhire.dto.UserDto;
import lk.ijse.carhire.service.ServiceFactory;
import lk.ijse.carhire.service.ServiceType;
import lk.ijse.carhire.service.custom.UserService;

import java.io.IOException;

public class RegisterUserController {
    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtLastName;

    @FXML
    private PasswordField txtPasswordAgain;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtCNumber;

    @FXML
    private PasswordField txtPassword;
    @FXML
    private AnchorPane rootnode;

    private UserService userService= ServiceFactory.getService(ServiceType.USER);


    @FXML
    void btnRegisterOnAction(ActionEvent event) {

        try {
            if (txtPassword.getText().equals(txtPasswordAgain.getText())){
                UserDto userDto = new UserDto();

                userDto.setFirstName(txtFirstName.getText());
                userDto.setLastName(txtLastName.getText());
                userDto.setUsername(txtUserName.getText());
                userDto.setPassword(txtPassword.getText());
                userDto.setContactNo(Integer.parseInt(txtCNumber.getText()));

                boolean isSaved=userService.save(userDto);

                if (isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION,"User saved successfully").show();
                    clearFileds();
                }else {
                    new Alert(Alert.AlertType.WARNING, "Please fill out the fields with correct information").show();
                }
            }else {
                new Alert(Alert.AlertType.WARNING,"Passwords do not match !").show();
            }


        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            e.printStackTrace();
        }

    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        Parent rootnode = FXMLLoader.load(this.getClass().getResource("/lk/ijse/carhire/view/login.fxml"));
        Scene scene=new Scene(rootnode);

        Stage primaryStage= (Stage) this.rootnode.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Login | User");
        primaryStage.centerOnScreen();
    }

    public void clearFileds(){
        txtCNumber.setText("");
        txtPassword.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtUserName.setText("");
        txtPasswordAgain.setText("");
    }


}
