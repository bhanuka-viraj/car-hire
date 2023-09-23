package lk.ijse.carhire.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    private ToggleGroup genderToggleGroup=new ToggleGroup();

    public void initialize(){
        maleRadioBtn.setToggleGroup(genderToggleGroup);
        femaleRadioBtn.setToggleGroup(genderToggleGroup);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnBackOnAction(ActionEvent event) {

    }

    @FXML
    void btnCustomerlistOnAction(ActionEvent event) {

    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        RadioButton selectedRadioBtn= (RadioButton) genderToggleGroup.getSelectedToggle();
        String selectedGender=selectedRadioBtn.getText();

        System.out.println(selectedGender);

    }

}

