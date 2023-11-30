package lk.ijse.carhire.controller.customer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
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
    private AnchorPane rootnode;
    @FXML
    private Button btnClear;

    @FXML
    private Button btnSave;

    @FXML
    private Label lblHead1;

    @FXML
    private Label lblDescription;
    @FXML
    private Label lblDescription2;

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
    private Button btnUpdate;

    @FXML
    private TextField txtProvince;

    @FXML
    private TextField txtSalary;

    private ToggleGroup genderToggleGroup = new ToggleGroup();

    private CustomerService service = ServiceFactory.getService(ServiceType.CUSTOMER);

    private CustomerDto customerDto;

    public CustomerFormController() {
        customerDto = new CustomerDto();
    }

    public void initialize() {
        try {
            maleRadioBtn.setToggleGroup(genderToggleGroup);
            femaleRadioBtn.setToggleGroup(genderToggleGroup);
            btnUpdate.setVisible(false);
            setLblDescription2("Please note that once registered, you cannot edit or update the NIC number.");

            Platform.runLater(() -> txtFirstName.requestFocus());

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    public void setLableHead1(String text) {
        lblHead1.setText(text);
        lblHead1.setAlignment(Pos.CENTER);

        AnchorPane.setLeftAnchor(lblHead1, (rootnode.getWidth() - lblHead1.getWidth()) / 2);
        AnchorPane.setRightAnchor(lblHead1, (rootnode.getWidth() - lblHead1.getWidth()) / 2);
    }

    public void setLableDescription(String text) {
        lblDescription.setText(text);

        lblDescription.setAlignment(Pos.CENTER);

        AnchorPane.setLeftAnchor(lblDescription, (rootnode.getWidth() - lblDescription.getWidth()) / 2);
        AnchorPane.setRightAnchor(lblDescription, (rootnode.getWidth() - lblDescription.getWidth()) / 2);
    }

    public void setLblDescription2(String text){
        lblDescription2.setText(text);
        lblDescription2.setAlignment(Pos.CENTER);

        AnchorPane.setLeftAnchor(lblDescription2, (rootnode.getWidth() - lblDescription2.getWidth()) / 2);
        AnchorPane.setRightAnchor(lblDescription2, (rootnode.getWidth() - lblDescription2.getWidth()) / 2);
        lblDescription2.setTextFill(Color.web("#FFA500"));
    }

    public void hideSaveClearButtons() {
        btnClear.setVisible(false);
        btnSave.setVisible(false);
    }


    public CustomerDto getcustomerDto() {
        return customerDto;
    }

    public void setCustomerDto(CustomerDto customerDto) {
        this.customerDto = customerDto;
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {


        try {
            if (!checkExisting()) {
                saveCustomer();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, "Please fill out the fields with correct information | " + e.getMessage()).show();
        }


    }


    @FXML
    void txtNicOnAction(ActionEvent event) {
        checkExisting();


    }

    @FXML
    void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
                saveCustomer();

        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, "Error updating customer: " + e.getMessage()).show();
        }

    }

    private void saveCustomer() {
        try {
            RadioButton selectedRadioBtn = (RadioButton) genderToggleGroup.getSelectedToggle();
            String selectedGender = selectedRadioBtn.getText();

            LocalDate dob = dateOfBirth.getValue();


            CustomerDto customerDto = new CustomerDto(txtNic.getText(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    dob,
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

            boolean isSaved= service.saveCustomer(customerDto);


            if (isSaved) {
                clearFields();
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer saved successfully !").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Please fill out the fields with correct information").show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private boolean checkExisting() {
        try {
            customerDto = service.getCustomerByNic(txtNic.getText());

            if (customerDto != null) {
                new Alert(Alert.AlertType.WARNING, "Customer of NIC number you entered is " +
                        "already registered, please check the customer list, or if you need to update details you can " +
                        "update from here").show();
                setFields();
                btnUpdate.setVisible(true);
                return true;
            } else {
                dateOfBirth.requestFocus();
                return false;
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            return false;
        }
    }


    public void setFields() {
        txtNic.setText(customerDto.getNic());
        txtFirstName.setText(customerDto.getFstname());
        txtLastName.setText(customerDto.getLstname());
        txtAddress.setText(customerDto.getAddressPerm());
        txtPostalAddress.setText(customerDto.getAddressPost());
        txtCity.setText(customerDto.getCity());
        txtCountry.setText(customerDto.getCountry());
        txtPostalCode.setText(customerDto.getPostalCode());
        txtProvince.setText(customerDto.getProvince());
        txtEmail.setText(customerDto.getEmail());
        txtCnumber.setText(customerDto.getCnumber());
        txtSalary.setText(String.valueOf(customerDto.getSalary()));

        String gender = customerDto.getGender();

        if (gender.equals("Male")) {
            maleRadioBtn.setSelected(true);
        } else if (gender.equals("Female")) {
            femaleRadioBtn.setSelected(true);
        }


        LocalDate dob = customerDto.getDob();
        if (dob != null) {
            dateOfBirth.setValue(dob);
        }

    }

    public void clearFields() {
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

    public void disableFields() {
        txtNic.setEditable(false);
        txtFirstName.setEditable(false);
        txtLastName.setEditable(false);
        dateOfBirth.setEditable(false);
        txtAddress.setEditable(false);
        txtPostalAddress.setEditable(false);
        txtPostalCode.setEditable(false);
        txtCity.setEditable(false);
        txtCountry.setEditable(false);
        txtProvince.setEditable(false);
        txtCnumber.setEditable(false);
        txtEmail.setEditable(false);
        txtSalary.setEditable(false);

        for (Toggle toggle : genderToggleGroup.getToggles()) {
            RadioButton radioButton = (RadioButton) toggle;
            radioButton.setDisable(true);
        }
    }

    public void setBtnUpdate(boolean value){
        if (value==true){
            btnUpdate.setVisible(true);
        }else {
            btnUpdate.setVisible(false);
        }
    }

    public void setBtnSave(boolean value){
        if (value==true){
            btnSave.setVisible(true);
        }else {
            btnSave.setVisible(false);
        }
    }

    public void setDisableNic(boolean value){
        if (value==true){
            txtNic.setEditable(true);
        }else {
            txtNic.setEditable(false);
        }
    }

    public void setBtnClear(boolean value) {
        if (value==true){
            btnClear.setVisible(true);
        }else {
            btnClear.setVisible(false);
        }
    }
}

