package lk.ijse.carhire.controller.car;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.carhire.dto.CarDto;
import lk.ijse.carhire.dto.CategoryDto;
import lk.ijse.carhire.service.ServiceFactory;
import lk.ijse.carhire.service.ServiceType;
import lk.ijse.carhire.service.custom.CarService;
import lk.ijse.carhire.service.custom.CategoryService;

import java.util.List;

public class CarFormController {
    @FXML
    private Button btnMenu;

    @FXML
    private TextField txtId;

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
    private TextArea txtRemaks;

    CarService service=ServiceFactory.getService(ServiceType.CAR);
    CategoryService CatService=ServiceFactory.getService(ServiceType.CATEGORY);

    public void initialize(){
        btnMenu.requestFocus();

        ObservableList<Integer>seats= FXCollections.observableArrayList(2,4,5);
        seatsCombo.setItems(seats);

        setCategories();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        try{
            checkNull();


            CarDto carDto = new CarDto(txtId.getText(),txtVehicleNmb.getText(),txtMake.getText(),txtColour.getText(),
                    seatsCombo.getValue(),Integer.parseInt(txtYom.getText()),Double.parseDouble(txtPricePerDay.getText()),
                    Double.parseDouble(txtMinDeposit.getText()),Integer.parseInt(txtMaxKm.getText()),
                    txtRemaks.getText(),categoryCombo.getValue());

            boolean isSaved=service.saveCar(carDto);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Car Saved Successfully !").show();
                clearFields();

            }else{
                new Alert(Alert.AlertType.WARNING,"Car cannot be saved !").show();
            }

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        
        
    }

    @FXML
    void btnViewAllOnAction(ActionEvent event) {

    }

    @FXML
    void btnMenuOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
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
}
