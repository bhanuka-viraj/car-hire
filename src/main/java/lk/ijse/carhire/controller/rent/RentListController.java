package lk.ijse.carhire.controller.rent;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.carhire.dto.RentDto;
import lk.ijse.carhire.service.ServiceFactory;
import lk.ijse.carhire.service.ServiceType;
import lk.ijse.carhire.service.custom.RentService;
import lk.ijse.carhire.tm.rent.RentListTm;

import java.time.LocalDate;
import java.util.List;

public class RentListController {
    @FXML
    private TableColumn<RentListTm, Button> colDetails;

    @FXML
    private TableColumn<RentListTm, LocalDate> colReturnDate;

    @FXML
    private TableView<RentListTm> tblRents;

    @FXML
    private TableColumn<RentListTm, String> colCustName;

    @FXML
    private TableColumn<RentListTm, String> colRentedCar;

    @FXML
    private TableColumn<RentListTm, String> colId;

    @FXML
    private TableColumn<RentListTm, Button> colManage;

    @FXML
    private TableColumn<RentListTm, Double> colBalanceToPaid;

    private RentService rentService;

    public void initialize(){
        rentService= ServiceFactory.getService(ServiceType.RENT);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        colRentedCar.setCellValueFactory(new PropertyValueFactory<>("rentedCar"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returningDate"));
        colBalanceToPaid.setCellValueFactory(new PropertyValueFactory<>("balanceAmnt"));
        colDetails.setCellValueFactory(new PropertyValueFactory<>("btnDetails"));
        colManage.setCellValueFactory(new PropertyValueFactory<>("btnManage"));

        getAllRents();
    }

    private void getAllRents() {
        try{
            List<RentDto> rents=rentService.getAllRents();
            ObservableList<RentListTm> tmList= FXCollections.observableArrayList();

            for (RentDto r:rents) {

                Button btnDetails = new Button("Details");
                btnDetails.setStyle("-fx-background-color: #ff4d4d; -fx-text-fill: white;");
                btnDetails.setMaxSize(95, 50);
                btnDetails.setCursor(Cursor.HAND);

                Button btnManage = new Button("Manage");
                btnManage.setStyle("-fx-background-color: #44bd32; -fx-text-fill: white;");
                btnManage.setMaxSize(95, 50);
                btnManage.setCursor(Cursor.HAND);

                if (r.isReturn()){
                    btnManage.setDisable(true);
                }

                RentListTm rentListTm=new RentListTm(r.getId(),r.getCustomerDto().getFstname(),r.getCarDto().getMake(),r.getToDate(),r.getBalance(),btnDetails,btnManage);
                tmList.add(rentListTm);

                rentDetails(btnDetails,rentListTm);
                manageRent(btnManage,rentListTm);

            }

            tblRents.setItems(tmList);

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            e.printStackTrace();
        }
    }

    private void manageRent(Button btnManage, RentListTm rentListTm) {
        btnManage.setOnAction((e)->{
            try {
                RentDto selectedRent =rentService.getRentById(rentListTm.getId());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/carhire/view/rent/rent_car.fxml"));
                Parent rootnode = loader.load();
                RentCarController rentCarController=loader.getController();

                rentCarController.setRentDto(selectedRent);
                rentCarController.setDetails();
                rentCarController.showReturnButton(true);
                rentCarController.showDeleteButton(true);
                rentCarController.showUpdateButton(true);
                rentCarController.showRentButton(false);
                rentCarController.disableCarId(true);


                Scene scene = new Scene(rootnode);
                Stage stage= new Stage();

                stage.setScene(scene);
                stage.setTitle("Rent details");
                stage.centerOnScreen();
                stage.show();


            }catch (Exception e1){
                new Alert(Alert.AlertType.ERROR,e1.getMessage()).show();
                e1.printStackTrace();
            }
        });

    }

    private void rentDetails(Button btnDetails, RentListTm rentListTm) {
        btnDetails.setOnAction((e)->{
            try {
                RentDto selectedRent =rentService.getRentById(rentListTm.getId());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/carhire/view/rent/rent_car.fxml"));
                Parent rootnode = loader.load();
                RentCarController rentCarController=loader.getController();

                rentCarController.setRentDto(selectedRent);
                rentCarController.setDetails();
                rentCarController.disableFileds();
                rentCarController.showRentButton(false);
                rentCarController.showReturnButton(false);
                rentCarController.showUpdateButton(false);


                Scene scene = new Scene(rootnode);
                Stage stage= new Stage();

                stage.setScene(scene);
                stage.setTitle("Rent details");
                stage.centerOnScreen();
                stage.show();


            }catch (Exception e1){
                new Alert(Alert.AlertType.ERROR,e1.getMessage()).show();
                e1.printStackTrace();
            }
        });
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
    }
}
