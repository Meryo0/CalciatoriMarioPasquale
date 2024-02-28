package Controllers;


import Types.Genere;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Calciatore;
import model.MilitanzaPortiere;
import model.Ruolo;

import java.io.IOException;
import java.time.LocalDate;

public class MilitanzaPortiereAggiungiController {
    Controller controller = new Controller();
    private Stage stage;
    private Scene scene;
    private Parent root;
    Calciatore calciatoreattuale;
    Ruolo ruoloattuale;
    @FXML
    private TextField textFieldSquadra;
    @FXML
    private DatePicker DataInizio;
    @FXML
    private DatePicker DataFine;
    @FXML
    private Spinner<Integer> gfspinner;
    @FXML
    private Spinner<Integer> pgspinner;
    @FXML
    private Spinner<Integer> gsspinner;
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
    String nomes;
    LocalDate datainizio;
    LocalDate datafine;
    int partitegiocate;
    int golfatti;
    int golsubiti;

    public void initialize() {
        SpinnerValueFactory<Integer> vfpg = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000);
        vfpg.setValue(-1);
        SpinnerValueFactory<Integer> vfgf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000);
        vfgf.setValue(-1);
        SpinnerValueFactory<Integer> vfgs = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000);
        vfgf.setValue(-1);
        pgspinner.setValueFactory(vfpg);
        gfspinner.setValueFactory(vfgf);
        gsspinner.setValueFactory(vfgs);
        textFieldSquadra.setVisible(false);
        DataInizio.setVisible(false);
        DataFine.setVisible(false);
        pgspinner.setVisible(false);
        gfspinner.setVisible(false);
        gsspinner.setVisible(false);
        textSquadra.setVisible(false);
        textdatainizio.setVisible(false);
        textdatafine.setVisible(false);
        squadramanca.setVisible(false);
        partitegiocatemanca.setVisible(false);
        datainiziomanca.setVisible(false);
        golsubitimanca.setVisible(false);
        golfattimanca.setVisible(false);
        datafinemanca.setVisible(false);
        textPartiteGiocate.setVisible(false);
        textgolfatti.setVisible(false);
        textgolsubiti.setVisible(false);
        visionacampi.setOnAction(event -> {

            boolean isChecked = visionacampi.isSelected();
            textFieldSquadra.setVisible(isChecked);
            DataInizio.setVisible(isChecked);
            DataFine.setVisible(isChecked);
            pgspinner.setVisible(isChecked);
            gfspinner.setVisible(isChecked);
            gsspinner.setVisible(isChecked);
            textSquadra.setVisible(isChecked);
            textdatainizio.setVisible(isChecked);
            textdatafine.setVisible(isChecked);
            textPartiteGiocate.setVisible(isChecked);
            textgolfatti.setVisible(isChecked);
            textgolsubiti.setVisible(isChecked);
        });
    }

    public void prendicalciatore(Calciatore calciatore, Ruolo ruolo) {
        calciatoreattuale = calciatore.clone();
        ruoloattuale = ruolo.clone();
    }

    public void AggiungiMilitanzaGiocatore(ActionEvent event) throws IOException {
        if(visionacampi.isSelected()) {
            nomes = textFieldSquadra.getText();
            datainizio = DataInizio.getValue();
            datafine = DataFine.getValue();
            partitegiocate = pgspinner.getValue();
            golfatti = gfspinner.getValue();
            golsubiti = gsspinner.getValue();
            if (nomes.isEmpty() || nomes == null || datainizio == null  || partitegiocate == -1 || golfatti == -1 || golsubiti == -1) {
                campovuoto1.setText("Riempi i campi vuoti indicati con l'asterisco prima di proseguire!");
                squadramanca.setVisible(nomes == null || nomes.isEmpty());
                datainiziomanca.setVisible(datainizio == null);
                partitegiocatemanca.setVisible(partitegiocate == -1);
                golfattimanca.setVisible(golfatti == -1);
                golsubitimanca.setVisible(golsubiti == -1);
            } else {
                controller.inseriscicalciatore(calciatoreattuale);
                String generes;
                String sessocalc = String.valueOf(calciatoreattuale.getSesso());
                if (sessocalc.equals("Maschio")) {
                    generes = "Maschile";
                } else {
                    generes = "Femminile";
                }
                int codices = controller.ottienicodicesquadra(nomes, Genere.valueOf(generes));
                int codicec = controller.ottienicodicecalciatore(calciatoreattuale);
                controller.aggiungiruolo(ruoloattuale.getPosizione(),codicec);
                MilitanzaPortiere militanzaPortiere = new MilitanzaPortiere(datainizio, datafine, golfatti,
                        partitegiocate, golsubiti, codicec, codices);
                controller.aggiungiMilitanzaPortiere(militanzaPortiere);
                switchToSceneVisualizzaAmministratore(event);
            }
        }else {
            controller.inseriscicalciatore(calciatoreattuale);
            int codicec = controller.ottienicodicecalciatore(calciatoreattuale);
            controller.aggiungiruolo(ruoloattuale.getPosizione(),codicec);
            switchToSceneVisualizzaAmministratore(event);
        }
    }
    public void switchToSceneVisualizzaAmministratore(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/gui/VisionaAmministratore.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}








