package Controllers;


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
import util.Constant;
import util.DisplayInfo;
import util.UserSession;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class VisionaAmministratoreController  implements Initializable  {

    Controller controller = new Controller();
    @FXML
    private Button aggiungibutton;
    @FXML
    private Button eliminabutton;
    @FXML
    private Button modificabutton;
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
    UserSession userSession = null;
    ObservableList<DisplayInfo> list = null;

    @FXML
    private TextField squadrefield;
    @FXML
    private TextField namefield;
    @FXML
    Spinner <Integer> spinnergf;
    @FXML
    Spinner <Integer> spinnergs;
    @FXML
    Spinner <Integer> spinnereta;


    private Stage stage;
    private Scene scene;
    private Parent root;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> vfgf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000);
        vfgf.setValue(0);
        SpinnerValueFactory<Integer> vfgs = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000);
        vfgs.setValue(0);
        SpinnerValueFactory<Integer> vfeta = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000);
        vfeta.setValue(0);
        spinnergf.setValueFactory(vfgf);
        spinnergs.setValueFactory(vfgs);
        spinnereta.setValueFactory(vfeta);
        userSession = UserSession.getInstance("admin", "admin");

        list = controller.displayCalciatori(userSession);
        colnome.setCellValueFactory(new PropertyValueFactory<DisplayInfo, String>("nome"));
        colcognome.setCellValueFactory(new PropertyValueFactory<DisplayInfo, String>("cognome"));
        coldn.setCellValueFactory(new PropertyValueFactory<DisplayInfo, LocalDate>("dataNascita"));
        coldr.setCellValueFactory(new PropertyValueFactory<DisplayInfo, LocalDate>("dataRitiro"));
        colgf.setCellValueFactory(new PropertyValueFactory<DisplayInfo, Integer>("goalfatti"));
        colgs.setCellValueFactory(new PropertyValueFactory<DisplayInfo, Integer>("goalsubiti"));
        colnazionalita.setCellValueFactory(new PropertyValueFactory<DisplayInfo, String>("nazionalita"));
        colpiede.setCellValueFactory(new PropertyValueFactory<DisplayInfo, Piede>("piede"));
        colsesso.setCellValueFactory(new PropertyValueFactory<DisplayInfo, Sesso>("sesso"));
        colsquadre.setCellValueFactory(new PropertyValueFactory<DisplayInfo, String>("nomes"));
        colruolo.setCellValueFactory(new PropertyValueFactory<DisplayInfo, String>("ruolo"));
        tableview.setItems(list);
    }

    public void SetInvisibleButton(ActionEvent event){
        aggiungibutton.setVisible(false);
        eliminabutton.setVisible(false);
        modificabutton.setVisible(false);
    }

    public void switchToSceneloginUtente(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/gui/LoginAmministratore.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        userSession.getFilters().clear();
    }

    public void switchToSceneAggiungiGiocatore(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/gui/AggiungiGiocatore.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSceneModificaGiocatore(ActionEvent event) throws IOException {
        DisplayInfo selectedinfo = tableview.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModificaGiocatore.fxml"));
        Parent root = loader.load();
        ModificaGiocatoreController modificaGiocatoreController = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        modificaGiocatoreController.prendicurrentinfo(selectedinfo);
    }

    public void modificacalciaore(ActionEvent event) throws IOException {
        if (tableview.getSelectionModel().getSelectedItem() != null) {
            switchToSceneModificaGiocatore(event);
        } else {
            System.out.println("Seleziona un giocatore");
        }
    }

    public void eliminacalciatore(ActionEvent event) throws IOException {
        if (tableview.getSelectionModel().getSelectedItem() != null) {
            DisplayInfo selectedinfo = tableview.getSelectionModel().getSelectedItem();
            Calciatore calciatoreeliminare = new Calciatore(selectedinfo.getNome(), selectedinfo.getCognome(), selectedinfo.getPiede(),
                    selectedinfo.getSesso(), selectedinfo.getDataNascita(), selectedinfo.getDataRitiro(), selectedinfo.getNazionalita());
            int codiceceliminare = controller.ottienicodicecalciatore(calciatoreeliminare);
            controller.eliminafeature(codiceceliminare);
            controller.eliminamilitanzacalciatore(codiceceliminare);
            controller.eliminamilitanzaportiere(codiceceliminare);
            controller.eliminavincetrofeo(codiceceliminare);
            controller.eliminaruolo(codiceceliminare);
            controller.eliminacalciatore(codiceceliminare);
            updateView();
        } else {
            System.out.println("Seleziona un giocatore");
        }
    }


    public void updateView(boolean clearFilters) {
        if (clearFilters)
            userSession.getFilters().clear();
        list = controller.displayCalciatori(userSession);
        tableview.setItems(list);
    }

    public void updateView() {
        userSession.getFilters().clear();
        list = controller.displayCalciatori(userSession);
        tableview.setItems(list);
    }

    @FXML
    public void handleSessoMenuAction(ActionEvent event) {
        if (event.getSource() instanceof MenuItem menuItem) {
            String filterKey = Constant.FILTER_KEY_SESSO;
            String filterValue = menuItem.getText();
            userSession.getFilters().put(filterKey, filterValue);
            this.updateView(false);
        }
    }

    public void handleNazioneMenuAction(ActionEvent event) {
        if (event.getSource() instanceof MenuItem menuItem) {
            String filterKey = Constant.FILTER_KEY_NAZIONE;
            String filterValue = menuItem.getText();
            userSession.getFilters().put(filterKey, filterValue);
            this.updateView(false);
        }
    }

    public void handleFullNameInput(ActionEvent event) {
        String filterValue = namefield.getText();
        //userSession.getFilters().clear();
        userSession.getFilters().put(Constant.FILTER_KEY_FULL_NOME, filterValue);
        this.updateView(false);
    }
    public void handleGolFattiMenuAction(ActionEvent event) {
        String filterKey = Constant.FILTER_KEY_GOAL_FATTI;
        String filterValue = spinnergf.getValue().toString();
        userSession.getFilters().put(filterKey, filterValue);
        this.updateView(false);
        }
    public void handleGolSubitiMenuAction(ActionEvent event) {
        String filterKey = Constant.FILTER_KEY_GOAL_SUBITI;
        String filterValue = spinnergs.getValue().toString();
        userSession.getFilters().put(filterKey, filterValue);
        this.updateView(false);
    }
    public void handleSquadreMenuAction(ActionEvent event) {
        String filterKey = Constant.FILTER_KEY_SQUADRE;
        String filterValue = squadrefield.getText();
        userSession.getFilters().put(filterKey, filterValue);
        this.updateView(false);
    }
    public void handleRuoliMenuAction(ActionEvent event) {
        if (event.getSource() instanceof MenuItem menuItem) {
            String filterKey = Constant.FILTER_KEY_RUOLO;
            String filterValue = menuItem.getText();
            userSession.getFilters().put(filterKey, filterValue);
            this.updateView(false);
        }
    }
    public void handleEtaMenuAction(ActionEvent event) {
        String filterKey = Constant.FILTER_KEY_ETA;
        String filterValue = spinnereta.getValue().toString();
        userSession.getFilters().put(filterKey, filterValue);
        this.updateView(false);
    }
    public void handlePiedeMenuAction(ActionEvent event) {
        if (event.getSource() instanceof MenuItem menuItem) {
            String filterKey = Constant.FILTER_KEY_PIEDE;
            String filterValue = menuItem.getText();
            userSession.getFilters().put(filterKey, filterValue);
            this.updateView(false);
        }
    }

}





