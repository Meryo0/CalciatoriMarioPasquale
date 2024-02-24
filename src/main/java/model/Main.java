package model;

import DAO.CalciatoriDAO;
import DAO.CalciatoriDAOimpl;
import DatabaseConnection.ConnessioneDatabase;
import Types.Piede;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main extends Application{

    public static void main (String[] args) {
        CalciatoriDAO dao = new CalciatoriDAOimpl();
        Application.launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LoginAmministratore.fxml"));
            Parent root = loader.load();
            stage.setResizable(false);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

