package lk.ijse.carhire.controller.car;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CarCategoryController {

    @FXML
    private AnchorPane node;

    @FXML
    private Button btnCategory;

    public void initialize(){
        try {
            loadCategoryTable();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnSedanOnAction(ActionEvent event) {

    }

    @FXML
    void btnSuvOnAction(ActionEvent event) {

    }

    @FXML
    void btnHatchBackOnAction(ActionEvent event) {

    }

    @FXML
    void btnConvertibleonAction(ActionEvent event) {

    }

    @FXML
    void btnSportsOnAction(ActionEvent event) {

    }

    @FXML
    void btnAddNewCategoryOnAction(ActionEvent event) throws IOException {
        loadCategoryForm();
    }

    @FXML
    void btnCategoryListOnAction(ActionEvent event) throws IOException {
        loadCategoryTable();
    }

    public void loadCategoryForm() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/carhire/view/car/category_form.fxml"));
        btnCategory.setVisible(true);

        node.getChildren().clear();
        node.getChildren().add(root);
    }

    public void loadCategoryTable() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/carhire/view/car/category_table.fxml"));
        btnCategory.setVisible(false);

        node.getChildren().clear();
        node.getChildren().add(root);
    }



}
