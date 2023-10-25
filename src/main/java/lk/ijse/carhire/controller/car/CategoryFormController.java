package lk.ijse.carhire.controller.car;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.carhire.dto.CategoryDto;
import lk.ijse.carhire.service.ServiceFactory;
import lk.ijse.carhire.service.ServiceType;
import lk.ijse.carhire.service.custom.CategoryService;

import java.io.IOException;

public class CategoryFormController {

    @FXML
    private TextField txtCatName;

    @FXML
    private TextField txtCatNameAgain;

    CategoryService service = ServiceFactory.getService(ServiceType.CATEGORY);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {

            if (txtCatNameAgain.getText().equals(txtCatName.getText())) {
                CategoryDto categoryDto = new CategoryDto();

                categoryDto.setCategoryName(txtCatName.getText());

                boolean success = service.saveCategory(categoryDto);

                if (success) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Category added successfully !").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to add category !").show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Name entered in two fields should be same !").show();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }


    public void clearFields(){
        txtCatName.setText("");
        txtCatNameAgain.setText("");
    }


}
