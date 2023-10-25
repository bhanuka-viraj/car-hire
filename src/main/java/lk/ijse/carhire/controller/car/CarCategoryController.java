package lk.ijse.carhire.controller.car;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class CarCategoryController {

    @FXML
    private TableColumn<?, ?> ColCategory;

    @FXML
    private Label lblHead1;

    @FXML
    private TableColumn<?, ?> ColSelect;

    @FXML
    private TableView<?> tblCategories;

    @FXML
    void btnSedanOnAction(ActionEvent event) {

    }

    @FXML
    void btnSuvOnAction(ActionEvent event) {

    }

    @FXML
    void btnHatchBackOnAction(ActionEvent event) {

    }

    @FXML
    void btnConvertibleonAction(ActionEvent event) {

    }

    @FXML
    void btnSportsOnAction(ActionEvent event) {

    }

    @FXML
    void btnAddNewCategoryOnAction(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("/lk/ijse/carhire/view/car/category_form.fxml"));
        Scene scene=new Scene(root);

        Stage stage=new Stage();

        stage.setScene(scene);
        stage.setTitle("Car Categories");
        stage.centerOnScreen();
        stage.show();
    }



}
