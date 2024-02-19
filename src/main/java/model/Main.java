package model;

import DatabaseConnection.ConnessioneDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.SQLException;

import java.net.URL;

public class Main extends Application{

    public static void main (String[] args) {

        try {
            ConnessioneDatabase connessione = ConnessioneDatabase.getInstance();

            // Check if the connection is valid before using it
            if (connessione.isConnectionValid()) {
                System.out.println("Connection is valid. Proceed with database operations.");

                // Perform your database operations here

            } else {
                System.out.println("Connection is not valid. Unable to proceed with database operations.");
            }

        } catch (SQLException e) {
            System.out.println("Error creating or validating database connection: " + e.getMessage());
            e.printStackTrace();
        }
        Application.launch(args);

    }

    @Override
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

