package lk.ijse.carhire;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent rootnode = FXMLLoader.load(this.getClass().getResource("view/dashboard.fxml"));
        Image icon = new Image(getClass().getResourceAsStream("view/images/background_2.jpg"));

        Scene scene = new Scene(rootnode);

        stage.setTitle("Login");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();



    }

    public static void main(String[] args) {
        launch();
    }
}