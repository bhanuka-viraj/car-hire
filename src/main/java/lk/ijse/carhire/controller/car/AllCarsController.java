package lk.ijse.carhire.controller.car;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.carhire.controller.rent.RentCarController;
import lk.ijse.carhire.dto.CarDto;
import lk.ijse.carhire.service.ServiceFactory;
import lk.ijse.carhire.service.ServiceType;
import lk.ijse.carhire.service.custom.CarService;
import lk.ijse.carhire.tm.car.AllCarTm;

import java.util.List;
import java.util.Optional;

public class AllCarsController {

    @FXML
    private AnchorPane rootnode;

    @FXML
    private TableView<AllCarTm> tblCars;

    @FXML
    private TableColumn<AllCarTm, Button> colRent;

    @FXML
    private TableColumn<AllCarTm, String> colMake;

    @FXML
    private TableColumn<AllCarTm, Double> colRentPerDay;

    @FXML
    private TableColumn<AllCarTm, String> colCategory;

    @FXML
    private TableColumn<AllCarTm, String> colRemarks;

    @FXML
    private TableColumn<AllCarTm, String> colAvailability;

    @FXML
    private TableColumn<AllCarTm, String> colId;

    @FXML
    private TableColumn<AllCarTm, Button> colDelete;

    private CarService service;
    public void initialize(){
        service= ServiceFactory.getService(ServiceType.CAR);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMake.setCellValueFactory(new PropertyValueFactory<>("make"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        colRentPerDay.setCellValueFactory(new PropertyValueFactory<>("rentPerDay"));
        colRemarks.setCellValueFactory(new PropertyValueFactory<>("remarks"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));
        colRent.setCellValueFactory(new PropertyValueFactory<>("rent"));

        getAllCars();
    }

    public void getAllCars() {
        try {
            List<CarDto> cars=service.getAllCars();
            ObservableList<AllCarTm> tmList= FXCollections.observableArrayList();

            for (CarDto c:cars) {

                Button btnDelete = new Button("Delete");
                btnDelete.setStyle("-fx-background-color: #ff4d4d; -fx-text-fill: white;");
                btnDelete.setMaxSize(95, 50);
                btnDelete.setCursor(Cursor.HAND);

                Button btnRent = new Button("Rent");
                btnRent.setStyle("-fx-background-color: #44bd32; -fx-text-fill: white;");
                btnRent.setMaxSize(95, 50);
                btnRent.setCursor(Cursor.HAND);

                if (checkAvailability(c).equals("Rented")){
                    btnRent.setDisable(true);
                    btnDelete.setDisable(true);
                }
                AllCarTm allCarTm = new AllCarTm(c.getId(),c.getMake(),c.getCategory(),checkAvailability(c),c.getPricePerDay(),c.getRemarks(),btnDelete,btnRent);
                tmList.add(allCarTm);

                deleteCar(allCarTm,btnDelete);
                rentCar(allCarTm,btnRent);

            }

            tblCars.setItems(tmList);

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            e.printStackTrace();
        }
    }

    public String checkAvailability(CarDto carDto){
        try {
            if (carDto.isRented()==true){
                return "Rented";
            }else {
                return "Available";
            }

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            e.printStackTrace();
            return null;
        }
    }

    private void deleteCar(AllCarTm allCarTm, Button btnDelete) {
        btnDelete.setOnAction((e)->{
               try{
                   ButtonType ok = new ButtonType("OK");
                   ButtonType no = new ButtonType("NO");

                   Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure..!", ok, no);

                   Optional<ButtonType> result = alert.showAndWait();


                   if (result.orElse(no) == ok) {

                       if (service.deleteCar(allCarTm.getId())) {
                           new Alert(Alert.AlertType.CONFIRMATION, "Customer is Deleted..!").show();
                           getAllCars();

                       }
                   }

               } catch (Exception e1){
                   new Alert(Alert.AlertType.ERROR,e1.getMessage()).show();
               }
        });


    }





    private void handleDoubleClick() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update or view details");
        alert.setHeaderText("If you want to see Car detilas, click details or if you want to update car details, click update");

        ButtonType update=new ButtonType("Update");
        ButtonType details =new ButtonType("Details");

        alert.getButtonTypes().setAll(update, details,ButtonType.CANCEL);
        Optional<ButtonType> result=alert.showAndWait();

        result.ifPresent(buttonType -> {
            if(buttonType== details){
                showCarDetails();
            } else if (buttonType==update) {
                updateCarDetails();
            }
        });


    }

    private void updateCarDetails() {
        try{
            AllCarTm selectedCar =tblCars.getSelectionModel().getSelectedItem();

            if(selectedCar !=null){
                CarDto carDto=service.getCarById(selectedCar.getId());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/carhire/view/car/car_form.fxml"));
                Parent rootnode = loader.load();

                CarFormController carFormController=loader.getController();

                carFormController.setCarDto(carDto);
                carFormController.setFields();
                carFormController.disableTxtId();
                carFormController.setBtnUpdate(true);
                carFormController.setBtnSave(false);
                carFormController.setLblHead("Update Car");
                carFormController.setLblDescription("Edit details and click save to update car details");

                Scene scene=new Scene(rootnode);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Update car");
                stage.centerOnScreen();
                stage.show();



            }
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            e.printStackTrace();
        }
    }

    private void showCarDetails() {
        try{
            AllCarTm selectedCar =tblCars.getSelectionModel().getSelectedItem();

            if(selectedCar !=null){
                CarDto carDto=service.getCarById(selectedCar.getId());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/carhire/view/car/car_form.fxml"));
                Parent rootnode = loader.load();

                CarFormController carFormController=loader.getController();

                carFormController.setCarDto(carDto);
                carFormController.setFields();
                carFormController.disableFiels();
                carFormController.setBtnUpdate(false);
                carFormController.setBtnClear(false);
                carFormController.setBtnSave(false);
                carFormController.setLblHead("Car Details");
                carFormController.setLblDescription("You can't update or change details from here");

                Scene scene=new Scene(rootnode);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Car Details");
                stage.centerOnScreen();
                stage.show();



            }
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            e.printStackTrace();
        }
    }

    private void rentCar(AllCarTm allCarTm,Button btnRent){
        btnRent.setOnAction((e)->{
            try{
                ButtonType ok = new ButtonType("OK");
                ButtonType no = new ButtonType("NO");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure..!", ok, no);

                Optional<ButtonType> result = alert.showAndWait();


                if (result.orElse(no) == ok) {
                    CarDto carDto=service.getCarById(allCarTm.getId());

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/carhire/view/rent/rent_car.fxml"));
                    Parent rootnode = loader.load();
                    RentCarController rentCarController=loader.getController();

                    rentCarController.setCarDto(carDto);

                    Stage stage = new Stage();
                    Scene scene = new Scene(rootnode);
                    stage.setScene(scene);
                    stage.setTitle("Rent Car");
                    stage.show();


                }

            } catch (Exception e1){
                new Alert(Alert.AlertType.ERROR,e1.getMessage()).show();
            }
        });
    }
    public void tblCarsOnClick(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount()==2){
            handleDoubleClick();
        }

        }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        getAllCars();
    }
}

