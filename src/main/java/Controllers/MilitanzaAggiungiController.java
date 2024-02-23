package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Calciatore;
import model.Ruolo;

import java.io.IOException;
import java.time.LocalDate;

public class MilitanzaAggiungiController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    Calciatore attuale;
    Ruolo ruoloattuale;


    public void prendicalciatore(Calciatore calciatore, Ruolo ruolo) {
        attuale = calciatore.clone();
        ruoloattuale = ruolo.clone();
    }

    public void switchToSceneAggiungiGiocatore(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/gui/AggiungiGiocatore.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TextField textFieldSquadra;
    @FXML
    private DatePicker DataInizio;
    @FXML
    private DatePicker DataFine;
    @FXML
    private TextField textFieldPartiteGiocate;
    @FXML
    private TextField textFieldGolFatti;
    @FXML
    private TextField textFieldGolSubiti;
    @FXML
    private CheckBox visionacampi;
    @FXML
    private Text textSquadra;
    @FXML
    private Text textdatainizio;
    @FXML
    private Text textdatafine;
    @FXML
    private Text textPartiteGiocate;
    @FXML
    private Text textgolfatti;
    @FXML
    private Text textgolsubiti;
    @FXML
    private Label datafinemanca;

    @FXML
    private Label golfattimanca;

    @FXML
    private Label golsubitimanca;

    @FXML
    private Label datainiziomanca;

    @FXML
    private Label partitegiocatemanca;

    @FXML
    private Label squadramanca;
    @FXML
    private Label campovuoto1;

    public void initialize() {
        textFieldSquadra.setVisible(false);
        DataInizio.setVisible(false);
        DataFine.setVisible(false);
        textFieldPartiteGiocate.setVisible(false);
        textFieldGolFatti.setVisible(false);
        textFieldGolSubiti.setVisible(false);
        textSquadra.setVisible(false);
        textdatainizio.setVisible(false);
        textdatafine.setVisible(false);
        textPartiteGiocate.setVisible(false);
        textgolfatti.setVisible(false);
        textgolsubiti.setVisible(false);
        squadramanca.setVisible(false);
        partitegiocatemanca.setVisible(false);
        datainiziomanca.setVisible(false);
        golsubitimanca.setVisible(false);
        golfattimanca.setVisible(false);
        datafinemanca.setVisible(false);
        visionacampi.setOnAction(event -> {

            boolean isChecked = visionacampi.isSelected();
            textFieldSquadra.setVisible(isChecked);
            DataInizio.setVisible(isChecked);
            DataFine.setVisible(isChecked);
            textFieldPartiteGiocate.setVisible(isChecked);
            textFieldGolFatti.setVisible(isChecked);
            textFieldGolSubiti.setVisible(isChecked);
            textSquadra.setVisible(isChecked);
            textdatainizio.setVisible(isChecked);
            textdatafine.setVisible(isChecked);
            textPartiteGiocate.setVisible(isChecked);
            textgolfatti.setVisible(isChecked);
            textgolsubiti.setVisible(isChecked);
        });
    }

    String nomes;
    LocalDate datainizio;
    LocalDate datafine;
    String partitegiocate;
    String golfatti;
    String golsubiti;

    public void AggiungiMilitanzaGiocatore(ActionEvent event) throws IOException {
        nomes = textFieldSquadra.getText();
        datainizio = DataInizio.getValue();
        datafine = DataFine.getValue();
        partitegiocate = textPartiteGiocate.getText();
        golfatti = textgolfatti.getText();
        golsubiti = textgolsubiti.getText();
        if (nomes.isEmpty() || nomes == null || datainizio == null || datafine == null || partitegiocate == null || golfatti == null || golsubiti == null) {
            campovuoto1.setText("Riempi i campi vuoti indicati con l'asterisco prima di proseguire!");
            squadramanca.setVisible(nomes == null || nomes.isEmpty());
            datainiziomanca.setVisible(datainizio == null);
            datafinemanca.setVisible(datafine == null);
            partitegiocatemanca.setVisible(partitegiocate == null);
            golfattimanca.setVisible(golfatti == null || golfatti.isEmpty());
            golsubitimanca.setVisible(golsubiti == null || golsubiti.isEmpty());
        }
    }


    }








