package Controllers;

import DAO.CalciatoriDAO;
import DAO.CalciatoriDAOimpl;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.DisplayInfo;
import util.DisplayMilitanza;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MilitanzaVisionaController implements Initializable {
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
    @FXML
    private Text notselectedtext;
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
    }
    public void setListView (ObservableList<DisplayMilitanza> list){
        tableview.setItems(list);
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
    public void switchToSceneModificaMilitanza(ActionEvent event) throws IOException {
        if (tableview.getSelectionModel().getSelectedItem() != null){
            notselectedtext.setVisible(false);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModificaMilitanza.fxml"));
            Parent root = loader.load();
            ModificaMilitanzaController modificaMilitanzaController = loader.getController();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            if(tableview.getSelectionModel().getSelectedItem().getGoalsubiti()==null){
                modificaMilitanzaController.SetInfoMilitanza(tableview.getSelectionModel().getSelectedItem(),false);
            }else {
                modificaMilitanzaController.SetInfoMilitanza(tableview.getSelectionModel().getSelectedItem(),true);
            }
        }else {
            notselectedtext.setVisible(true);
        }
    }
}
