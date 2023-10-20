package lk.ijse.carhire.controller.car;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AllCarsController {

    @FXML
    private AnchorPane rootnode;

    @FXML
    private TableView<?> tblCars;

    @FXML
    private TableColumn<?, ?> colRent;

    @FXML
    private TableColumn<?, ?> colMake;

    @FXML
    private TableColumn<?, ?> colRentPerDay;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colRemarks;

    @FXML
    private TableColumn<?, ?> colAvailability;

    @FXML
    private TableColumn<?, ?> colDelete;


    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        Parent rootnode = FXMLLoader.load(this.getClass().getResource("/lk/ijse/carhire/view/dashboard.fxml"));
        Scene scene=new Scene(rootnode);

        Stage primaryStage= (Stage) this.rootnode.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Car Hire |  All cars");
        primaryStage.centerOnScreen();
    }

    @FXML
    void btnCarFormOnAction(ActionEvent event) {

    }
}
