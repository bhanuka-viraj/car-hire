package lk.ijse.carhire.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.carhire.dto.CarDto;
import lk.ijse.carhire.dto.CustomerDto;
import lk.ijse.carhire.dto.RentDto;
import lk.ijse.carhire.service.ServiceFactory;
import lk.ijse.carhire.service.ServiceType;
import lk.ijse.carhire.service.custom.CarService;
import lk.ijse.carhire.service.custom.CustomerService;
import lk.ijse.carhire.service.custom.RentService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DashboardController {
    @FXML
    private AnchorPane node;

    @FXML
    private AnchorPane rootnode;

    @FXML
    private Label lblTblHead;

    @FXML
    private Label lblTblDesc;
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

    private CustomerService customerService= ServiceFactory.getService(ServiceType.CUSTOMER);
    private CarService carService=ServiceFactory.getService(ServiceType.CAR);
    private RentService rentService=ServiceFactory.getService(ServiceType.RENT);

    public void initialize(){
        try {
            lblTblHead.setText("Rents");
            loadFXML("/lk/ijse/carhire/view/rent/rent_list.fxml");

            setdate();
            setTime();
            setDetails();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnCustListOnClick(ActionEvent event) throws IOException {
        lblTblHead.setText("Customers");
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
        lblTblHead.setText("Cars");
        lblTblDesc.setText("Double click to view or update details");
        loadFXML("/lk/ijse/carhire/view/car/all_cars.fxml");
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Are you sure you want to log out?");

        confirmationAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        confirmationAlert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.YES) {
                Stage stage = (Stage) rootnode.getScene().getWindow();
                stage.close();

                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/carhire/view/login.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage1 = new Stage();
                    stage1.setScene(scene);
                    stage1.setTitle("User | Login");
                    stage1.show();
                } catch (Exception e) {
                    e.printStackTrace(); // Handle the exception according to your needs
                }
            }
        });
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
        lblTblHead.setText("Rents");
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

    public void setDetails(){
        try{
            int customerCount=0;
            int carCount=0;
            int rentCount=0;
            double totalEarnings=0;
            int unrentedCars=0;

            List<CustomerDto>customers=customerService.getAllCustomers();
            List<CarDto>cars=carService.getAllCars();
            List<RentDto>rents=rentService.getAllRents();

            for (CustomerDto c:customers) {
                customerCount++;
            }
            for (CarDto c:cars) {
                if (!c.isRented()){
                    unrentedCars++;
                }
                carCount++;
            }
            for (RentDto r:rents) {
                double earnings=r.getTotal()-r.getRefundableDeposit();
                totalEarnings+=earnings;
                rentCount++;
            }

            lblUnrentedCars.setText(String.valueOf(unrentedCars));
            lblEarnings.setText(String.valueOf(totalEarnings));
            lblTotalCustomers.setText(String.valueOf(customerCount));
            lblTotalCars.setText(String.valueOf(carCount));
            lblTotalRents.setText(String.valueOf(rentCount));

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
    }

}




