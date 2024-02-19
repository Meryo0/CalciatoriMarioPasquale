package model;

//import DatabaseConnection.DbFunctions;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application{

    public static void main (String[] args) {

        Application.launch(args);

    }


public void start(Stage stage) throws Exception {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Accesso.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }catch (Exception e){
        e.printStackTrace();
    }

}
}

