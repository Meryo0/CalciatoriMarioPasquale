package Controllers;


import DAO.CalciatoriDAO;
import DAO.CalciatoriDAOimpl;
import Types.Piede;
import Types.Sesso;
import javafx.scene.control.TableColumn;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Calciatore;
import util.DisplayInfo;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class VisionaAmministratoreController implements Initializable {
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
    private TableColumn<DisplayInfo, String> colruolo;
    @FXML
    private TableView<DisplayInfo> tableview;
    CalciatoriDAO dao = new CalciatoriDAOimpl();
    ObservableList <DisplayInfo> list = dao.displaycalciatori();

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
    public void switchToSceneAggiungiGiocatore(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/gui/AggiungiGiocatore.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToSceneModificaGiocatore(ActionEvent event) throws IOException {
        DisplayInfo selectedinfo = tableview.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModificaGiocatore.fxml"));
        Parent root = loader.load();
        ModificaGiocatoreController modificaGiocatoreController = loader.getController();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        modificaGiocatoreController.prendicurrentinfo(selectedinfo);
    }
    public void modificacalciaore(ActionEvent event) throws IOException{
        if (tableview.getSelectionModel().getSelectedItem()!= null){
           switchToSceneModificaGiocatore(event);
        }else {
            System.out.println("Seleziona un giocatore");
        }
    }

    public void eliminacalciatore(ActionEvent event) throws IOException {
        if (tableview.getSelectionModel().getSelectedItem()!= null){
            DisplayInfo selectedinfo = tableview.getSelectionModel().getSelectedItem();
            Calciatore calciatoreeliminare = new Calciatore(selectedinfo.getNome(),selectedinfo.getCognome(),selectedinfo.getPiede(),
                    selectedinfo.getSesso(),selectedinfo.getDataNascita(),selectedinfo.getDataRitiro(),selectedinfo.getNazionalita());
            int codiceceliminare = dao.ottienicodicecalciatore(calciatoreeliminare);
            dao.eliminafeature(codiceceliminare);
            dao.eliminamilitanzacalciatore(codiceceliminare);
            dao.eliminamilitanzaportiere(codiceceliminare);
            dao.eliminavincetrofeo(codiceceliminare);
            dao.eliminaruolo(codiceceliminare);
            dao.eliminacalciatore(codiceceliminare);
            refreshitems();
        }else {
            System.out.println("Seleziona un giocatore");
        }
    }


    public void refreshitems(){
        list = dao.displaycalciatori();
        tableview.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colnome.setCellValueFactory(new PropertyValueFactory<DisplayInfo,String>("nome"));
        colcognome.setCellValueFactory(new PropertyValueFactory<DisplayInfo,String>("cognome"));
        coldn.setCellValueFactory(new PropertyValueFactory<DisplayInfo,LocalDate>("dataNascita"));
        coldr.setCellValueFactory(new PropertyValueFactory<DisplayInfo,LocalDate>("dataRitiro"));
        colgf.setCellValueFactory(new PropertyValueFactory<DisplayInfo,Integer>("goalfatti"));
        colgs.setCellValueFactory(new PropertyValueFactory<DisplayInfo,Integer>("goalsubiti"));
        colnazionalita.setCellValueFactory(new PropertyValueFactory<DisplayInfo,String>("nazionalita"));
        colpiede.setCellValueFactory(new PropertyValueFactory<DisplayInfo,Piede>("piede"));
        colsesso.setCellValueFactory(new PropertyValueFactory<DisplayInfo,Sesso>("sesso"));
        colsquadre.setCellValueFactory(new PropertyValueFactory<DisplayInfo,String>("nomes"));
        colruolo.setCellValueFactory(new PropertyValueFactory<DisplayInfo,String>("ruolo"));
        tableview.setItems(list);
    }


}
