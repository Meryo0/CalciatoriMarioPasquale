package Controllers;

import DAO.CalciatoriDAO;
import DAO.CalciatoriDAOimpl;
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
import model.MilitanzaCalciatore;
import model.Ruolo;

import java.io.IOException;
import java.time.LocalDate;

public class MilitanzaCalciatoreAggiungiController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    Calciatore calciatoreattuale;
    Ruolo ruoloattuale;


    public void prendicalciatore(Calciatore calciatore, Ruolo ruolo) {
        calciatoreattuale = calciatore.clone();
        System.out.println(calciatoreattuale.getNome());
        ruoloattuale = ruolo.clone();
    }

    public void switchToSceneAggiungiGiocatore(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/gui/AggiungiGiocatore.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        //AGGIUNGI CHE SI PORTA IL GIOCATORE E LO SALVA.
    }

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
    private Label datafinemanca;

    @FXML
    private Label golfattimanca;


    @FXML
    private Label datainiziomanca;

    @FXML
    private Label partitegiocatemanca;

    @FXML
    private Label squadramanca;
    @FXML
    private Label campovuoto1;

    public void initialize() {
        SpinnerValueFactory<Integer> vfpg = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000);
        vfpg.setValue(-1);
        SpinnerValueFactory<Integer> vfgf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000);
        vfgf.setValue(-1);
        pgspinner.setValueFactory(vfpg);
        gfspinner.setValueFactory(vfgf);
        textFieldSquadra.setVisible(false);
        DataInizio.setVisible(false);
        DataFine.setVisible(false);
        gfspinner.setVisible(false);
        pgspinner.setVisible(false);
        textSquadra.setVisible(false);
        textdatainizio.setVisible(false);
        textdatafine.setVisible(false);
        textPartiteGiocate.setVisible(false);
        textgolfatti.setVisible(false);
        squadramanca.setVisible(false);
        partitegiocatemanca.setVisible(false);
        datainiziomanca.setVisible(false);
        golfattimanca.setVisible(false);
        datafinemanca.setVisible(false);
        visionacampi.setOnAction(event -> {

            boolean isChecked = visionacampi.isSelected();
            textFieldSquadra.setVisible(isChecked);
            DataInizio.setVisible(isChecked);
            DataFine.setVisible(isChecked);
            pgspinner.setVisible(isChecked);
            gfspinner.setVisible(isChecked);
            textSquadra.setVisible(isChecked);
            textdatainizio.setVisible(isChecked);
            textdatafine.setVisible(isChecked);
            textPartiteGiocate.setVisible(isChecked);
            textgolfatti.setVisible(isChecked);

        });

    }

    String nomes;
    LocalDate data_inizio;
    LocalDate data_fine;
    int partitegiocate;
    int golfatti;



    public void AggiungiMilitanzaGiocatore(ActionEvent event) throws IOException {
        CalciatoriDAO dao = new CalciatoriDAOimpl();
        if(visionacampi.isSelected()) {
            nomes = textFieldSquadra.getText();
            data_inizio = DataInizio.getValue();
            data_fine = DataFine.getValue();
            partitegiocate = pgspinner.getValue();
            golfatti = gfspinner.getValue();
            if (nomes.isEmpty()  || data_inizio == null || partitegiocate == -1 || golfatti == -1) {
                campovuoto1.setText("Riempi i campi vuoti indicati con l'asterisco prima di proseguire!");
                squadramanca.setVisible(nomes .isEmpty() );
                datainiziomanca.setVisible(data_inizio == null);
                partitegiocatemanca.setVisible(partitegiocate == -1);
                golfattimanca.setVisible(golfatti == -1);
            } else {
                dao.inseriscicalciatore(calciatoreattuale);
                String generes;
                String sessocalc = String.valueOf(calciatoreattuale.getSesso());
                if (sessocalc.equals("Maschio")) {
                    generes = "Maschile";
                } else {
                    generes = "Femminile";
                }
                int codices = dao.ottienicodicesquadra(nomes, Genere.valueOf(generes));
                int codicec = dao.ottienicodicecalciatore(calciatoreattuale);
                dao.aggiungiruolo(ruoloattuale.getPosizione(),codicec);
                MilitanzaCalciatore militanzaCalciatore = new MilitanzaCalciatore(data_inizio, data_fine, golfatti,
                        partitegiocate, codicec, codices);
                dao.aggiungiMilitanzaCalciatore(militanzaCalciatore);
                switchToSceneVisualizzaAmministratore(event);
            }
        }else {
            dao.inseriscicalciatore(calciatoreattuale);
            int codicec = dao.ottienicodicecalciatore(calciatoreattuale);
            dao.aggiungiruolo(ruoloattuale.getPosizione(),codicec);
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








