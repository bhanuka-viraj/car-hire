package lk.ijse.carhire.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {
    @FXML
    private AnchorPane node;

    @FXML
    private AnchorPane rootnode;


    @FXML
    void btnCustListOnClick(ActionEvent event) throws IOException {
    loadFXML("/lk/ijse/carhire/view/customer/customer_list.fxml");
    }

    @FXML
    void btnRentFormOnClick(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("/lk/ijse/carhire/view/rent/rent_car.fxml"));
        Scene scene=new Scene(root);

        Stage stage=new Stage();

        stage.setScene(scene);
        stage.setTitle("Rent");
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void btnCarListOnClick(ActionEvent event) throws IOException {
        loadFXML("/lk/ijse/carhire/view/car/all_cars.fxml");
    }

    @FXML
    void btnLogoutOnClick(ActionEvent event) {

    }

    @FXML
    void btnCustFormOnClick(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("/lk/ijse/carhire/view/customer/customer_form.fxml"));
        Scene scene=new Scene(root);

        Stage stage=new Stage();

        stage.setScene(scene);
        stage.setTitle("Customer Form");
        stage.centerOnScreen();
        stage.show();


    }

    @FXML
    void btnCarFormOnClick(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("/lk/ijse/carhire/view/car/car_form.fxml"));
        Scene scene=new Scene(root);

        Stage stage=new Stage();

        stage.setScene(scene);
        stage.setTitle("Customer Form");
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void btnCategoriesOnClick(ActionEvent event) {

    }

    @FXML
    void btnRentListOnClick(ActionEvent event) throws IOException {
        loadFXML("/lk/ijse/carhire/view/rent/rent_list.fxml");
    }

    private void loadFXML(String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));

        node.getChildren().clear();
        node.getChildren().add(root);
    }

}




