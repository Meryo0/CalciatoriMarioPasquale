package Controllers;

import DAO.CalciatoriDAO;
import DAO.CalciatoriDAOimpl;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.DisplayMilitanza;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class ModificaMilitanzaController {
    @FXML
    private DatePicker DataFine;

    @FXML
    private DatePicker DataInizio;

    @FXML
    private Label campovuoto1;

    @FXML
    private Label datafinemanca;

    @FXML
    private Label datainiziomanca;

    @FXML
    private Spinner<Integer> gfspinner;

    @FXML
    private Label golfattimanca;

    @FXML
    private Label golsubitimanca;

    @FXML
    private Spinner<Integer> gsspinner;

    @FXML
    private Label partitegiocatemanca;

    @FXML
    private Spinner<Integer> pgspinner;

    @FXML
    private Label squadramanca;

    @FXML
    private TextField textFieldSquadra;

    @FXML
    private Text textPartiteGiocate;

    @FXML
    private Text textdatafine;

    @FXML
    private Text textdatainizio;

    @FXML
    private Text textgolfatti;

    @FXML
    private Text textgolsubiti;
    private Stage stage;
    private Scene scene;
    private Parent root;
    CalciatoriDAO dao = new CalciatoriDAOimpl();
    DisplayMilitanza displayMilitanza;

    public void SetInfoMilitanza(DisplayMilitanza displayMilitanza, boolean bollean){
        this.displayMilitanza = displayMilitanza.clone();
        SpinnerValueFactory<Integer> vfpg = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000);
        vfpg.setValue(displayMilitanza.getPartitegiocate());
        SpinnerValueFactory<Integer> vfgf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000);
        vfgf.setValue(displayMilitanza.getGoalfatti());
        SpinnerValueFactory<Integer> vfgs = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000);
        vfgs.setValue(displayMilitanza.getGoalsubiti());
        pgspinner.setValueFactory(vfpg);
        gfspinner.setValueFactory(vfgf);
        gsspinner.setValueFactory(vfgs);
        gsspinner.setVisible(bollean);
        textgolsubiti.setVisible(bollean);
        DataInizio.setValue(displayMilitanza.getDatainizio());
        DataFine.setValue(displayMilitanza.getDatafine());
    }
    public void switchToSceneVisionaAmministratore(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/gui/VisionaAmministratore.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void modificaMilitanza(ActionEvent event) throws IOException {
        LocalDate di = DataInizio.getValue();
        displayMilitanza.setDatafine(DataFine.getValue());
        displayMilitanza.setDatainizio(DataInizio.getValue());
        displayMilitanza.setGoalfatti(gfspinner.getValue());
        displayMilitanza.setGoalsubiti(gsspinner.getValue());
        displayMilitanza.setPartitegiocate(pgspinner.getValue());
        dao.modificaMilitanza(displayMilitanza,di);
        switchToSceneVisionaAmministratore(event);
    }


}
