package Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;


public class LoginAmministratoreController {

    @FXML
    TextField emailfield;
    @FXML
    TextField passwordfield;
    @FXML
    private Label wrongcred;
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToVisionaAmministratore(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/gui/VisionaAmministratore.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void login (ActionEvent event) throws IOException {
        String email = emailfield.getText() ;
        String pword = passwordfield.getText();
        if (email.equals("admin") && pword.equals("admin")){
            switchToVisionaAmministratore(event);
        }else{
            wrongcred.setText("utente o password non valide");
        }

    }

    public void switchToSceneVisionaUtente(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/VisionaAmministratore.fxml"));
        Parent root = loader.load();
        VisionaAmministratoreController visionaAmministratoreController = loader.getController();
        visionaAmministratoreController.SetInvisibleButton(event);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
