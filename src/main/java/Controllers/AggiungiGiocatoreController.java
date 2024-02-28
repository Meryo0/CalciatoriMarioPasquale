package Controllers;

import DAO.CalciatoriDAO;
import DAO.CalciatoriDAOimpl;
import Types.Piede;
import Types.Posizione;
import Types.Sesso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Calciatore;
import model.Ruolo;
import javafx.scene.control.Label;

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
    @FXML
    private Label campovuoto;
    @FXML
    private Label nomemanca;
    @FXML
    private Label cognomemanca;

    @FXML
    private Label datanascitamanca;

    @FXML
    private Label dataritiromanca;

    @FXML
    private Label nazionalitàmanca;

    @FXML
    private Label piedemanca;

    @FXML
    private Label ruolomanca;

    @FXML
    private Label sessomanca;


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
    String ruolo;
    String nazionalita;
    CalciatoriDAO dao = new CalciatoriDAOimpl();
    public void switchToMilitanzaAggiungi (ActionEvent event) throws IOException {
        nome = textFieldNome.getText();
        cognome = textFieldCognome.getText();
        sesso = choiceBoxSesso.getValue();
        datanascita = DataNascita.getValue();
        piede = choiceBoxPiede.getValue();
        nazionalita = textFieldNazionalità.getText();
        ruolo = choiceBoxRuolo.getValue();
            if (nome.isEmpty() || cognome.isEmpty() || sesso.isEmpty() || datanascita == null || piede.isEmpty() || ruolo.isEmpty() || nazionalita.isEmpty()) {
                campovuoto.setText("Riempi i campi vuoti indicati con l'asterisco prima di proseguire!");
                if (nome.isEmpty()) {
                    nomemanca.setVisible(true);
                } else {
                    nomemanca.setVisible(false);
                }

                if (cognome.isEmpty()) {
                    cognomemanca.setVisible(true);
                } else {
                    cognomemanca.setVisible(false);
                }

                if (sesso == null || sesso.isEmpty()) {
                    sessomanca.setVisible(true);
                } else {
                    sessomanca.setVisible(false);
                }

                if (datanascita == null) {
                    datanascitamanca.setVisible(true);
                } else {
                    datanascitamanca.setVisible(false);
                }

                if (piede == null || piede.isEmpty()) {
                    piedemanca.setVisible(true);
                } else {
                    piedemanca.setVisible(false);
                }

                if (choiceBoxRuolo.getValue() == null) {
                    ruolomanca.setVisible(true);
                } else {
                    ruolomanca.setVisible(false);
                }
                if (nazionalita.isEmpty()) {
                    nazionalitàmanca.setVisible(true);
                } else {
                    nazionalitàmanca.setVisible(false);
                }
            } else {
                Calciatore calciatore = new Calciatore(nome, cognome, Piede.valueOf(piede), Sesso.valueOf(sesso), datanascita, null, nazionalita);
                if(ruolo.equals("portiere")){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MilitanzaPortiereAggiungi.fxml"));
                    Parent root = loader.load();
                    MilitanzaPortiereAggiungiController militanzaPortiereAggiungiController = loader.getController();
                    Ruolo ruoloinit = new Ruolo(Posizione.valueOf(ruolo));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    militanzaPortiereAggiungiController.prendicalciatore(calciatore,ruoloinit);
                }else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MilitanzaCalciatoreAggiungi.fxml"));
                    Parent root = loader.load();
                    MilitanzaCalciatoreAggiungiController militanzaCalciatoreAggiungiController = loader.getController();
                    Ruolo ruoloinit = new Ruolo(Posizione.valueOf(ruolo));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    militanzaCalciatoreAggiungiController.prendicalciatore(calciatore,ruoloinit);
                }
            }
        }
    }


