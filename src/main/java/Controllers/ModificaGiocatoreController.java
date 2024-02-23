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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Calciatore;
import util.DisplayInfo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModificaGiocatoreController implements Initializable {
    @FXML
    private TextField cognometext;

    @FXML
    private DatePicker datanpicker;

    @FXML
    private DatePicker datarpicker;

    @FXML
    private TextField nazionalitatext;

    @FXML
    private TextField nometext;

    @FXML
    private ChoiceBox<String> piedebox;
    final String[] CBPiede = {"Destro","Sinistro","Ambidestro"};

    @FXML
    private ChoiceBox<String> sessobox;
    final String[] CBSesso = {"Maschio","Femmina"};
    @FXML
    private CheckBox attaccantechec;

    @FXML
    private CheckBox centrocheck;
    @FXML
    private CheckBox difensorecheck;
    @FXML
    private CheckBox portierecheck;
    private Stage stage;
    private Scene scene;
    private Parent root;
    DisplayInfo selectedinfo;
    public void switchToSceneVisionaAmministratore(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/gui/VisionaAmministratore.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToSceneModificaMilitanza(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/gui/MilitanzaModifica.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void prendicurrentinfo(DisplayInfo displayInfo){
        selectedinfo = displayInfo.clone();
        nometext.setText(displayInfo.getNome());
        cognometext.setText(displayInfo.getCognome());
        sessobox.setValue(String.valueOf(displayInfo.getSesso()));
        datanpicker.setValue(displayInfo.getDataNascita());
        piedebox.setValue(String.valueOf(displayInfo.getPiede()));
        datarpicker.setValue(displayInfo.getDataRitiro());
        String[] allruoli = displayInfo.getRuolo().split(",");
        for (int i = 0; i < allruoli.length - 1; i++) {
            allruoli[i] = allruoli[i].trim(); // Rimuovi spazi bianchi eventuali
        }
        allruoli[allruoli.length - 1] = allruoli[allruoli.length - 1].replaceAll(";\\s*$", "").trim();
        for (String parola : allruoli) {
            System.out.println(parola);
            if(parola.equals("portiere")){
                portierecheck.setSelected(true);
            }
            if(parola.equals("centrocampista")){
                centrocheck.setSelected(true);
            }
            if(parola.equals("difensore")){
                difensorecheck.setSelected(true);
            }
            if(parola.equals("attaccante")){
                attaccantechec.setSelected(true);
            }
        }
        nazionalitatext.setText(displayInfo.getNazionalita());
    }

    public void modificagiocatore(){
        CalciatoriDAO dao = new CalciatoriDAOimpl();
        Calciatore calciatore = new Calciatore(nometext.getText(),cognometext.getText(), Piede.valueOf(piedebox.getValue()),
                Sesso.valueOf(sessobox.getValue()),datanpicker.getValue(),datarpicker.getValue(),nazionalitatext.getText());
        dao.modifica(calciatore,selectedinfo.getIdCalciatore());

        //se fai lo stesso inserimento due volte violi il vincolo di pkey, bisogna prima prendere i ruoli che ricopre il giocatore dal database e poi modificare...


        if(portierecheck.isSelected()){
            System.out.println("portiere selezionato");
            //dao.aggiungiruolo(Posizione.valueOf("portiere"),selectedinfo.getIdCalciatore());
        }
        if(difensorecheck.isSelected()){
            System.out.println("difensore selezionato");
            //dao.aggiungiruolo(Posizione.valueOf("difensore"),selectedinfo.getIdCalciatore());
        }
        if(centrocheck.isSelected()){
            System.out.println("centro selezionato");
            //dao.aggiungiruolo(Posizione.valueOf("centrocampista"),selectedinfo.getIdCalciatore());
        }
        if(attaccantechec.isSelected()){
            System.out.println("attaccante selezionato");
            //dao.aggiungiruolo(Posizione.valueOf("attaccante"),selectedinfo.getIdCalciatore());
        }


    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            sessobox.getItems().addAll(CBSesso);
            piedebox.getItems().addAll(CBPiede);
    }
}
