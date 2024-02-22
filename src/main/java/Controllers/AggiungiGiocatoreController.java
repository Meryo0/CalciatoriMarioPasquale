package Controllers;

import DAO.CalciatoriDAO;
import DAO.CalciatoriDAOimpl;
import Types.Piede;
import Types.Sesso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import model.Calciatore;
import javafx.scene.control.TextField;
import Types.Posizione;
import model.Ruolo;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AggiungiGiocatoreController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchToSceneVisionaAmministratore(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/gui/VisionaAmministratore.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldCognome;
    @FXML
    private ChoiceBox<String> choiceBoxSesso;
    private String[] CBSesso = {"Maschio","Femmina"};
    @FXML
    private DatePicker DataNascita;
    @FXML
    private ChoiceBox<String> choiceBoxPiede;
    private String[] CBPiede = {"Destro","Sinistro","Ambidestro"};
    @FXML
    private DatePicker DataRitiro;
    @FXML
    private ChoiceBox<String> choiceBoxRuolo;
    private String[] CBRuolo = {"portiere","difensore","centrocampista","attaccante"};
    @FXML
    private TextField textFieldNazionalità;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxSesso.getItems().addAll(CBSesso);
        choiceBoxPiede.getItems().addAll(CBPiede);
        choiceBoxRuolo.getItems().addAll(CBRuolo);
    }

    String nome;
    String cognome;
    String sesso;
    LocalDate datanascita;
    String piede;
    LocalDate dataritiro;
    Ruolo ruolo;
    String nazionalita;
    CalciatoriDAO dao = new CalciatoriDAOimpl();
    public void switchToMilitanzaAggiungi (ActionEvent event) throws IOException {
        nome = textFieldNome.getText();
        cognome = textFieldCognome.getText();
        sesso = choiceBoxSesso.getValue();
        datanascita = DataNascita.getValue();
        piede = choiceBoxPiede.getValue();
        dataritiro = DataRitiro.getValue();
        ruolo = new Ruolo( Posizione.valueOf(choiceBoxRuolo.getValue()) );
        nazionalita = textFieldNazionalità.getText();
        Calciatore calciatore = new Calciatore(nome,cognome, Piede.valueOf(piede), Sesso.valueOf(sesso),datanascita,dataritiro,nazionalita);
        MilitanzaAggiungiController controller = new MilitanzaAggiungiController();
        controller.prendicalciatore(calciatore, ruolo);
        //dao.inserisci(calciatore);
        root = FXMLLoader.load(getClass().getResource("/gui/MilitanzaAggiungi.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
