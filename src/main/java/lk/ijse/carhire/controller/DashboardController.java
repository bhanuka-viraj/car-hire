package lk.ijse.carhire.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DashboardController {
    @FXML
    private AnchorPane node;

    @FXML
    private AnchorPane rootnode;

    @FXML
    private Label lblNode;

    @FXML
    private Label lblEarnings;

    @FXML
    private Label lblUnrentedCars;

    @FXML
    private Label lblTotalCars;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblTotalRents;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTotalCustomers;

    public void initialize(){
        try {
            lblNode.setText("Rents");
            loadFXML("/lk/ijse/carhire/view/rent/rent_list.fxml");

            setdate();
            setTime();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnCustListOnClick(ActionEvent event) throws IOException {
        lblNode.setText("Customers");
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
        lblNode.setText("Cars");
        loadFXML("/lk/ijse/carhire/view/car/all_cars.fxml");
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {

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
        stage.setTitle("Car Form");
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void btnCategoriesOnClick(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("/lk/ijse/carhire/view/car/car_category.fxml"));
        Scene scene=new Scene(root);

        Stage stage=new Stage();

        stage.setScene(scene);
        stage.setTitle("Car Categories");
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void btnRentListOnClick(ActionEvent event) throws IOException {
        lblNode.setText("Rents");
        loadFXML("/lk/ijse/carhire/view/rent/rent_list.fxml");
    }

    private void loadFXML(String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));

        node.getChildren().clear();
        node.getChildren().add(root);
    }

    private void setTime(){
        final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalTime currentTime = LocalTime.now();
            String formattedTime = currentTime.format(timeFormatter);
            lblTime.setText(formattedTime);
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void setdate(){
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        lblDate.setText(formattedDate);
    }

}




