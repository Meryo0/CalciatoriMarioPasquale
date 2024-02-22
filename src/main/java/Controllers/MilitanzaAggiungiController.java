package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Calciatore;
import model.Ruolo;

import java.io.IOException;

public class MilitanzaAggiungiController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    Calciatore attuale ;
    Ruolo ruoloattuale;


    public void prendicalciatore(Calciatore calciatore, Ruolo ruolo) {
        attuale = calciatore.clone();
        ruoloattuale = ruolo.clone();
    }
    public void switchToSceneAggiungiGiocatore(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/gui/AggiungiGiocatore.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
