package lk.ijse.carhire.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.carhire.dto.UserDto;
import lk.ijse.carhire.service.ServiceFactory;
import lk.ijse.carhire.service.ServiceType;
import lk.ijse.carhire.service.custom.UserService;

import java.io.IOException;

public class LoginController {
    @FXML
    private AnchorPane rootnode;
    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    private UserService userService= ServiceFactory.getService(ServiceType.USER);

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {

        try {
            UserDto dto=userService.getUser(txtUserName.getText());

            if (dto!=null){
                if (dto.validatePassword(txtPassword.getText())){

                    Parent rootnode = FXMLLoader.load(this.getClass().getResource("/lk/ijse/carhire/view/dashboard.fxml"));
                    Scene scene=new Scene(rootnode);

                    Stage primaryStage= (Stage) this.rootnode.getScene().getWindow();

                    primaryStage.setScene(scene);
                    primaryStage.setTitle("Dash Board");
                    primaryStage.centerOnScreen();

                }else {
                    new Alert(Alert.AlertType.WARNING,"Incorrect Password !").show();
                }
            }else{
                    new Alert(Alert.AlertType.WARNING,"User not found !").show();
            }

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            e.printStackTrace();
        }



    }

    @FXML
    void linkRegisterOnAction(ActionEvent event) throws IOException {
        Parent rootnode = FXMLLoader.load(this.getClass().getResource("/lk/ijse/carhire/view/register.fxml"));
        Scene scene=new Scene(rootnode);

        Stage primaryStage= (Stage) this.rootnode.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Register | User");
        primaryStage.centerOnScreen();
    }
}
