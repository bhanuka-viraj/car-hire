package lk.ijse.carhire.controller.car;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.carhire.dto.CarDto;
import lk.ijse.carhire.dto.CategoryDto;
import lk.ijse.carhire.service.ServiceFactory;
import lk.ijse.carhire.service.ServiceType;
import lk.ijse.carhire.service.custom.CarService;
import lk.ijse.carhire.service.custom.CategoryService;
import javafx.application.Platform;


import java.io.IOException;
import java.util.List;

public class CarFormController {

    @FXML
    private AnchorPane rootnode;
    @FXML
    private TextField txtId;

    @FXML
    private Label lblHead;
    @FXML
    private Label lblDescription;
    @FXML
    private TextField txtPricePerDay;

    @FXML
    private TextField txtMake;

    @FXML
    private TextField txtMinDeposit;

    @FXML
    private ComboBox<Integer> seatsCombo;

    @FXML
    private TextField txtYom;

    @FXML
    private ComboBox<String> categoryCombo;

    @FXML
    private TextField txtMaxKm;

    @FXML
    private TextField txtColour;

    @FXML
    private TextField txtVehicleNmb;
    @FXML
    private Button btnUpdate;

    @FXML
    private TextArea txtRemaks;
    @FXML
    private Button btnSave;

    @FXML
    private Button btnClear;

    CarService service=ServiceFactory.getService(ServiceType.CAR);
    CategoryService CatService=ServiceFactory.getService(ServiceType.CATEGORY);
    CarDto carDto;

    public CarFormController(){
        carDto=new CarDto();
    }
    public CarDto getCarDto(){
        return carDto;
    }

    public void setCarDto(CarDto carDto){
        this.carDto=carDto;
    }
    public void initialize(){
        ObservableList<Integer>seats= FXCollections.observableArrayList(2,4,5);
        seatsCombo.setItems(seats);
        setCategories();
        setLblDescription("Please note that once registered, you cannot edit or update the Car id.");
        setBtnUpdate(false);

        Platform.runLater(() -> txtId.requestFocus());
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        try{
            checkNull();

            if(checkExisting()==null){
                saveCar();
            }



        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        
        
    }

    private void saveCar() {
        try {
            CarDto existingCar=checkExisting();
            boolean isAvailable;

            if(existingCar!=null){
                isAvailable= existingCar.isRented();
            }else {
                isAvailable=false;
            }

            CarDto carDto = new CarDto(txtId.getText(),txtVehicleNmb.getText(),txtMake.getText(),txtColour.getText(),
                    seatsCombo.getValue(),Integer.parseInt(txtYom.getText()),Double.parseDouble(txtPricePerDay.getText()),
                    Double.parseDouble(txtMinDeposit.getText()),Integer.parseInt(txtMaxKm.getText()),isAvailable,
                    txtRemaks.getText(),categoryCombo.getValue());

            boolean isSaved=service.saveCar(carDto);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Car Saved Successfully !").show();
                clearFields();

            }else if(checkExisting()!=null){
                new Alert(Alert.AlertType.WARNING,"Car cannot be saved !").show();
            }


        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
                saveCar();



        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, "Error updating car details: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void txtCarIdOnAction(ActionEvent event) {
        checkExisting();
    }


    private CarDto checkExisting() {
        try {
            CarDto existingCar=service.getCarById(txtId.getText());

            if(existingCar!=null){
                new Alert(Alert.AlertType.WARNING,"Car of Id number you entered is " +
                        "already registered, please check the Car list, or if you need to update details you can " +
                        "update from here").show();
                setFields();

                return carDto;
            }else{
                txtVehicleNmb.requestFocus();
                return null;
            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void clearFields(){
        txtId.setText("");
        txtVehicleNmb.setText("");
        txtYom.setText("");
        txtMake.setText("");
        txtMaxKm.setText("");
        txtPricePerDay.setText("");
        txtMinDeposit.setText("");
        txtColour.setText("");
        txtRemaks.setText("");
        categoryCombo.setValue("");
        seatsCombo.setValue(null);
    }

    public void setFields(){
        txtId.setText(carDto.getId());
        txtVehicleNmb.setText(carDto.getVehicleNumber());
        txtYom.setText(String.valueOf(carDto.getYom()));
        txtMake.setText(carDto.getMake());
        txtMaxKm.setText(String.valueOf(carDto.getMaxKmPerDay()));
        txtPricePerDay.setText(String.valueOf(carDto.getPricePerDay()));
        txtMinDeposit.setText(String.valueOf(carDto.getMinDeposit()));
        txtColour.setText(carDto.getColor());
        txtRemaks.setText(carDto.getRemarks());
        categoryCombo.setValue(carDto.getCategory());
        seatsCombo.setValue(carDto.getSeats());

    }

    public void disableFiels(){
        txtId.setEditable(false);
        txtVehicleNmb.setEditable(false);
        txtYom.setEditable(false);
        txtMake.setEditable(false);
        txtMaxKm.setEditable(false);
        txtPricePerDay.setEditable(false);
        txtMinDeposit.setEditable(false);
        txtColour.setEditable(false);
        txtRemaks.setEditable(false);
        categoryCombo.setEditable(false);
        seatsCombo.setEditable(false);
    }

    public void checkNull(){
        if(isAnyfieldEmpty(txtVehicleNmb,txtColour,txtMake,txtMaxKm,txtMinDeposit,txtPricePerDay,txtYom)){

           new Alert(Alert.AlertType.WARNING,"All fields should be filled with corect information").show();
        }
    }

    public boolean isAnyfieldEmpty(TextField... fields) {
        for(TextField field:fields){
            if(field.getText().isEmpty()){
                return true;
            }
        }

        return false;
    }


    public void setCategories() {
        try {
            List<CategoryDto> allCategories=CatService.getAllCategories();

            ObservableList<String>categories= FXCollections.observableArrayList();

            for(CategoryDto category:allCategories){
                categories.add(category.getCategoryName());
            }

            categoryCombo.setItems(categories);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void setLblHead(String text) {
        lblHead.setText(text);
        lblHead.setAlignment(Pos.CENTER);

        AnchorPane.setLeftAnchor(lblHead, (rootnode.getWidth() - lblHead.getWidth()) / 2);
        AnchorPane.setRightAnchor(lblHead, (rootnode.getWidth() - lblHead.getWidth()) / 2);
    }

    public void setLblDescription(String text) {
        lblDescription.setText(text);
        lblDescription.setAlignment(Pos.CENTER);

        AnchorPane.setLeftAnchor(lblDescription, (rootnode.getWidth() - lblDescription.getWidth()) / 2);
        AnchorPane.setRightAnchor(lblDescription, (rootnode.getWidth() - lblDescription.getWidth()) / 2);
    }

    public void disableTxtId(){
        txtId.setEditable(false);
    }

    public void setBtnUpdate(boolean value){
        if(value==true){
            btnUpdate.setVisible(true);
        }else {
            btnUpdate.setVisible(false);
        }
    }

    public void setBtnSave(boolean value){
        if(value==true){
            btnSave.setVisible(true);
        }else {
            btnSave.setVisible(false);
        }
    }

    public void setBtnClear(boolean value){
        if(value==true){
            btnClear.setVisible(true);
        }else {
            btnClear.setVisible(false);
        }
    }
}
