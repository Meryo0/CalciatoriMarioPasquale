package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MilitanzaModificaController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToSceneModificaGiocatore(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/gui/ModificaGiocatore.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
