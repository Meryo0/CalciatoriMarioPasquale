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

        //prova();
        Application.launch(args);


    }
    public static void prova() {
        String q1 = "Select nome, cognome from calciatore where codicec = 85";
        try{
            Connection con = ConnessioneDatabase.getInstance().getConnection();
            PreparedStatement stm  = con.prepareStatement(q1);
            ResultSet res = stm.executeQuery();
            res.next();
            String nome ;
            String cognome ;
            nome= res.getString("nome");
            cognome= res.getString("cognome");
            System.out.println(nome +" "+cognome);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LoginAmministratore.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

