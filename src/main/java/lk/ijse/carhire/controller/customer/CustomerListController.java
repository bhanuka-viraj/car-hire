package lk.ijse.carhire.controller.customer;

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
import lk.ijse.carhire.tm.customer.CustomerTm;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CustomerListController {

    @FXML
    private TableColumn<CustomerTm, String> colCity;

    @FXML
    private TableColumn<CustomerTm, String> colEmail;

    @FXML
    private TableColumn<CustomerTm, String> colName;

    @FXML
    private TableColumn<CustomerTm, String> colNic;

    @FXML
    private TableColumn<CustomerTm, String> colNumber;

    @FXML
    private TableColumn<CustomerTm, Button> colDelete;

    @FXML
    private TableColumn<CustomerTm, Button> colUpdate;

    @FXML
    private TableColumn<CustomerTm, Button> colDetails;

    @FXML
    private TableColumn<CustomerTm, Button> colRents;

    @FXML
    private TableView<CustomerTm> tblCustomer;


    @FXML
    private AnchorPane rootnode;

    private CustomerService service;

    public void initialize(){
        service= ServiceFactory.getService(ServiceType.CUSTOMER);

        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colName.setCellValueFactory(new PropertyValueFactory<>("FstName"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("Cnumber"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("btnUpdate"));
        colDetails.setCellValueFactory(new PropertyValueFactory<>("btnDetails"));
        colRents.setCellValueFactory(new PropertyValueFactory<>("btnRents"));

      getAllCustomers();
    }

    private void getAllCustomers() {
        try {
            List<CustomerDto>allCustomers=service.getAllCustomers();
            ObservableList  <CustomerTm>tmList= FXCollections.observableArrayList();

            for (CustomerDto c : allCustomers){

                //System.out.println("Customer -> "+c);

                Button btnDelete = new Button("Delete");
                btnDelete.setStyle("-fx-background-color: #ff4d4d; -fx-text-fill: white;");
                btnDelete.setMaxSize(95, 50);
                btnDelete.setCursor(Cursor.HAND);


                Button btnUpdate = new Button("Update");
                btnUpdate.setStyle("-fx-background-color: #0984e3; -fx-text-fill: white;");
                btnUpdate.setMaxSize(95, 50);
                btnUpdate.setCursor(Cursor.HAND);

                Button btnDetails = new Button("Details");
                btnDetails.setStyle("-fx-background-color: #474787; -fx-text-fill: white;");
                btnDetails.setMaxSize(95, 50);
                btnDetails.setCursor(Cursor.HAND);

                Button btnRents = new Button("Rents");
                btnDetails.setStyle("-fx-background-color: #474787; -fx-text-fill: white;");
                btnDetails.setMaxSize(95, 50);
                btnDetails.setCursor(Cursor.HAND);

                CustomerTm customerTm = new CustomerTm(c.getNic(),c.getFstname(),c.getCnumber(),btnDelete,btnUpdate,btnDetails,btnRents);

                tmList.add(customerTm);

                btnDelete.setOnAction((e) -> {
                    ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure..!", ok, no);

                    Optional<ButtonType> result = alert.showAndWait();

                    try {
                        if (result.orElse(no) == ok) {

                            if (service.deleteCustomer(customerTm.getNic())) {
                                new Alert(Alert.AlertType.CONFIRMATION, "Customer is Deleted..!").show();
                                getAllCustomers();

                            }
                        }

                    } catch (Exception e1) {
                            new Alert(Alert.AlertType.ERROR,e1.getMessage()).show();
                    }

                });
                updateCustomer(customerTm,btnUpdate);
                custDetails(customerTm,btnDetails);
                rentDetails(customerTm,btnRents);

            }
                tblCustomer.setItems(tmList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void rentDetails(CustomerTm customerTm, Button btnRentDetails) {
        btnRentDetails.setOnAction((e) -> {

            try {
                CustomerDto selectedCustomer=service.getCustomerByNic(customerTm.getNic());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/carhire/view/customer/customer_form.fxml"));
                Parent rootnode = loader.load();
                CustomerFormController customerFormController = loader.getController();

                customerFormController.setCustomerDto(selectedCustomer);
                customerFormController.setFields();
                customerFormController.disableFields();
                customerFormController.setLableHead1("Customer Details");
                customerFormController.setLableDescription("You cannot edit or save customers from here");
                customerFormController.hideSaveClearButtons();


                Scene scene=new Scene(rootnode);

                Stage stage= new Stage();

                stage.setScene(scene);
                stage.setTitle("Customer Details");
                stage.centerOnScreen();


            } catch (Exception e1) {
                new Alert(Alert.AlertType.ERROR,e1.getMessage()).show();
            }

        });
    }

    private void custDetails(CustomerTm customerTm, Button btnDetails) {
        btnDetails.setOnAction((e) -> {

            try {
                CustomerDto selectedCustomer=service.getCustomerByNic(customerTm.getNic());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/carhire/view/customer/customer_form.fxml"));
                Parent rootnode = loader.load();
                CustomerFormController customerFormController = loader.getController();

                customerFormController.setCustomerDto(selectedCustomer);
                customerFormController.setFields();
                customerFormController.disableFields();
                customerFormController.setLableHead1("Customer Details");
                customerFormController.setLableDescription("You cannot edit or save customers from here");
                customerFormController.hideSaveClearButtons();

                Scene scene=new Scene(rootnode);

                Stage stage= new Stage();

                stage.setScene(scene);
                stage.setTitle("Customer Details");
                stage.centerOnScreen();
                stage.show();


            } catch (Exception e1) {
                new Alert(Alert.AlertType.ERROR,e1.getMessage()).show();
            }

        });
    }

    private void updateCustomer(CustomerTm customerTm, Button btnUpdate) {
        btnUpdate.setOnAction((e) -> {

            try {
                    CustomerDto selectedCustomer=service.getCustomerByNic(customerTm.getNic());

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/carhire/view/customer/customer_form.fxml"));
                    Parent rootnode = loader.load();
                    CustomerFormController customerFormController = loader.getController();

                    customerFormController.setCustomerDto(selectedCustomer);
                    customerFormController.setFields();
                    customerFormController.setLableHead1("Update Customer");
                    customerFormController.setLableDescription("Change the fields that you want to update and click Save button");

                    Scene scene=new Scene(rootnode);

                Stage stage= new Stage();

                stage.setScene(scene);
                stage.setTitle("Customer Update");
                stage.centerOnScreen();
                stage.show();


            } catch (Exception e1) {
                new Alert(Alert.AlertType.ERROR,e1.getMessage()).show();
            }

        });
    }


}
