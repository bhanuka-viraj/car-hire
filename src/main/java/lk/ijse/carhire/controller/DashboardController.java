package lk.ijse.carhire.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashboardController {
    @FXML
    private AnchorPane node;

    @FXML
    private AnchorPane rootnode;
    @FXML
    void btnCusDetailsOnClick(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("/lk/ijse/carhire/view/customer/customer_list.fxml"));

        this.node.getChildren().clear();
        this.node.getChildren().add(root);
    }

    @FXML
    void btnAvailableCarsOnClick(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("/lk/ijse/carhire/view/car/available_cars.fxml"));

        this.node.getChildren().clear();
        this.node.getChildren().add(root);
    }

}
