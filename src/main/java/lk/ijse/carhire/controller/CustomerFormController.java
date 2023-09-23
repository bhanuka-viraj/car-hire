package lk.ijse.carhire.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.carhire.dto.CustomerDto;
import lk.ijse.carhire.service.ServiceFactory;
import lk.ijse.carhire.service.ServiceType;
import lk.ijse.carhire.service.custom.CustomerService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class CustomerFormController {

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private RadioButton femaleRadioBtn;

    @FXML
    private RadioButton maleRadioBtn;

    @FXML
    private TextArea txtAddress;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCnumber;

    @FXML
    private TextField txtCountry;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextArea txtPostalAddress;

    @FXML
    private TextField txtPostalCode;

    @FXML
    private TextField txtProvince;

    @FXML
    private TextField txtSalary;

    @FXML
    private AnchorPane rootnode;

    private ToggleGroup genderToggleGroup=new ToggleGroup();

    private CustomerService service= ServiceFactory.getService(ServiceType.CUSTOMER);

    public void initialize(){
        try {
            maleRadioBtn.setToggleGroup(genderToggleGroup);
            femaleRadioBtn.setToggleGroup(genderToggleGroup);

        }catch (Exception e){
            new Alert(Alert.AlertType.WARNING,e.getMessage()).show();
        }


    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
//        System.out.println("Clicked");
//        Parent rootnode = FXMLLoader.load(this.getClass().getResource("/lk/ijse/carhire/view/customer/customer_form.fxml"));
//        Scene scene=new Scene(rootnode);
//
//        Stage primaryStage= (Stage) this.rootnode.getScene().getWindow();
//
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Customer Form");
//        primaryStage.centerOnScreen();

    }

    private void clearFields() {
                txtNic.setText("");
                txtFirstName.setText("");
                txtLastName.setText("");
                dateOfBirth.setValue(null);
                txtAddress.setText("");
                txtPostalAddress.setText("");
                txtPostalCode.setText("");
                txtCity.setText("");
                txtCountry.setText("");
                txtProvince.setText("");
                txtCnumber.setText("");
                txtEmail.setText("");
                txtSalary.setText("");
                genderToggleGroup.selectToggle(null);
    }

    @FXML
    void btnCustomerlistOnAction(ActionEvent event) throws IOException {

        Parent rootnode = FXMLLoader.load(this.getClass().getResource("/lk/ijse/carhire/view/customer/customer_list.fxml"));
        Scene scene=new Scene(rootnode);

        Stage primaryStage= (Stage) this.rootnode.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Customer List");
        primaryStage.centerOnScreen();
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws Exception {


        try {
            RadioButton selectedRadioBtn= (RadioButton) genderToggleGroup.getSelectedToggle();
            String selectedGender=selectedRadioBtn.getText();

            LocalDate localDate=dateOfBirth.getValue();
            Date dateOfBirth=java.sql.Date.valueOf(localDate);


            CustomerDto customerDto = new CustomerDto(txtNic.getText(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    dateOfBirth,
                    txtAddress.getText(),
                    txtPostalAddress.getText(),
                    txtPostalCode.getText(),
                    txtCity.getText(),
                    txtCountry.getText(),
                    txtProvince.getText(),
                    txtCnumber.getText(),
                    txtEmail.getText(),
                    Double.parseDouble(txtSalary.getText()),
                    selectedGender);
            boolean isSaved=service.saveCustomer(customerDto);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer saved successfully !").show();
            }

        }catch (Exception e){
            new Alert(Alert.AlertType.WARNING,"Please fill out the fields with correct information").show();
        }


    }

}

