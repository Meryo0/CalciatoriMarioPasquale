package Controllers;

import Types.Piede;
import Types.Sesso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import util.DisplayInfo;

import java.io.IOException;
import java.time.LocalDate;

public class VisionaAmministratoreController {

    @FXML
    private TableColumn<DisplayInfo, String> colcognome;

    @FXML
    private TableColumn<DisplayInfo, LocalDate> coldn;

    @FXML
    private TableColumn<DisplayInfo, LocalDate> coldr;

    @FXML
    private TableColumn<DisplayInfo, Integer> colgf;

    @FXML
    private TableColumn<DisplayInfo, Integer> colgs;

    @FXML
    private TableColumn<DisplayInfo, String> colnazionalita;

    @FXML
    private TableColumn<DisplayInfo, String> colnome;

    @FXML
    private TableColumn<DisplayInfo, Piede> colpiede;

    @FXML
    private TableColumn<DisplayInfo, Sesso> colsesso;

    @FXML
    private TableColumn<DisplayInfo, String> colsquadre;

    @FXML
    private TableView<?> tableview;

    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchToSceneloginUtente(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/gui/LoginAmministratore.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }







}
