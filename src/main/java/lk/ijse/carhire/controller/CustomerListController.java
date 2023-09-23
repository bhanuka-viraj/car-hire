package lk.ijse.carhire.controller;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.carhire.dto.CustomerDto;
import lk.ijse.carhire.service.ServiceFactory;
import lk.ijse.carhire.service.ServiceType;
import lk.ijse.carhire.service.custom.CustomerService;
import lk.ijse.carhire.tm.CustomerTm;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CustomerListController {

    @FXML
    private TableColumn<CustomerTm, String> colAddress;

    @FXML
    private TableColumn<CustomerTm, Button> colDelete;

    @FXML
    private TableColumn<CustomerTm, String> colEmail;

    @FXML
    private TableColumn<CustomerTm, String> colEmail1;

    @FXML
    private TableColumn<CustomerTm, String> colName;

    @FXML
    private TableColumn<CustomerTm, String> colNic;

    @FXML
    private TableColumn<CustomerTm, String> colNumber;

    @FXML
    private TableColumn<CustomerTm, Button> colUpdate;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private AnchorPane rootnode;

    private CustomerService service;

    public void intialize(){
        service= ServiceFactory.getService(ServiceType.CUSTOMER);

        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Fstname"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("addressPerm"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("Cnumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("btnUpdate"));

        getAllCustomers();
    }

    private void getAllCustomers() {
        try {
            List<CustomerDto>allCustomer=service.getAllCustomers();
            ObservableList  <CustomerTm>tmList= FXCollections.observableArrayList();

            for (CustomerDto c : allCustomer){

                Button btnDelete = new Button("Delete");
                btnDelete.setStyle("-fx-background-color: #ff4d4d; -fx-text-fill: white;");

                btnDelete.setMaxSize(95, 50);
                btnDelete.setCursor(Cursor.HAND);


                Button btnUpdate = new Button("Update");
                btnUpdate.setStyle("-fx-background-color: #0984e3; -fx-text-fill: white;");

                btnUpdate.setMaxSize(95, 50);
                btnUpdate.setCursor(Cursor.HAND);

                CustomerTm customerTm = new CustomerTm(c.getNic(),c.getFstname(),c.getAddressPerm(),c.getCnumber(),c.getEmail(),btnDelete,btnUpdate);

                tmList.add(customerTm);

                btnDelete.setOnAction((e) -> {
                    ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure..!", ok, no);

                    Optional<ButtonType> result = alert.showAndWait();

                    try {
                        if (result.orElse(no) == ok) {

                            if (service.deleteCustomer(Integer.parseInt(customerTm.getNic()))) {
                                new Alert(Alert.AlertType.CONFIRMATION, "Customer is Deleted..!").show();
                                getAllCustomers();
                                return;
                            }
                        }

                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                });
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Parent rootnode = FXMLLoader.load(this.getClass().getResource("/lk/ijse/carhire/view/customer/customer_form.fxml"));
        Scene scene=new Scene(rootnode);

        Stage primaryStage= (Stage) this.rootnode.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Customer Form");
        primaryStage.centerOnScreen();
    }

}
