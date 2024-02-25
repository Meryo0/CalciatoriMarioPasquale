package Controllers;

import DAO.CalciatoriDAO;
import DAO.CalciatoriDAOimpl;
import Types.Piede;
import Types.Sesso;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Calciatore;
import model.MilitanzaPortiere;
import util.DisplayInfo;
import util.DisplayMilitanza;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class MilitanzaModificaController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableColumn<DisplayMilitanza, LocalDate> coldf;

    @FXML
    private TableColumn<DisplayMilitanza, LocalDate> coldi;

    @FXML
    private TableColumn<DisplayMilitanza, Integer> colgf;

    @FXML
    private TableColumn<DisplayMilitanza, Integer> colgs;

    @FXML
    private TableColumn<DisplayMilitanza, Integer> colpg;

    @FXML
    private TableColumn<DisplayMilitanza, String> colsquadra;

    @FXML
    private TableView<DisplayMilitanza> tableview;
    boolean portiere;
    boolean difensore;
    boolean centrocampista;
    boolean attaccante;

    DisplayInfo displayInfo;
    CalciatoriDAO dao = new CalciatoriDAOimpl();
    int codicec;
    ObservableList<DisplayMilitanza> list;

    public void switchToSceneModificaGiocatore(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModificaGiocatore.fxml"));
        Parent root = loader.load();
        ModificaGiocatoreController modificaGiocatoreController = loader.getController();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println(attaccante);
        modificaGiocatoreController.mantieniInfo(displayInfo,portiere,difensore,centrocampista,attaccante,displayInfo);
    }
    public void prendicodice(DisplayInfo displayInfo,boolean portiere, boolean difensore, boolean centrocampista, boolean attaccante){
        this.displayInfo = displayInfo.clone();
        codicec = displayInfo.getIdCalciatore();
        list = dao.displaymilitanze(codicec);
        tableview.setItems(list);
        this.portiere = portiere;
        this.difensore = difensore;
        this.centrocampista = centrocampista;
        this.attaccante = attaccante;
        System.out.println(attaccante);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colsquadra.setCellValueFactory(new PropertyValueFactory<DisplayMilitanza,String>("nomes"));
        colpg.setCellValueFactory(new PropertyValueFactory<DisplayMilitanza,Integer>("partitegiocate"));
        colgf.setCellValueFactory(new PropertyValueFactory<DisplayMilitanza,Integer>("goalfatti"));
        colgs.setCellValueFactory(new PropertyValueFactory<DisplayMilitanza,Integer>("goalsubiti"));
        coldf.setCellValueFactory(new PropertyValueFactory<DisplayMilitanza,LocalDate>("datafine"));
        coldi.setCellValueFactory(new PropertyValueFactory<DisplayMilitanza,LocalDate>("datainizio"));
        tableview.setItems(list);
    }
}
