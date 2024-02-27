package DAO;

import Types.Genere;
import Types.Posizione;
import javafx.collections.ObservableList;
import model.Calciatore;
import model.MilitanzaCalciatore;
import model.MilitanzaPortiere;
import util.DisplayInfo;
import util.DisplayMilitanza;
import util.UserSession;

import java.time.LocalDate;

public interface CalciatoriDAO {
    public void inseriscicalciatore(Calciatore calciatore);
    public void modificacalciatore(Calciatore calciatore, int idModificare);
    public void eliminacalciatore(int idEliminare);
    public void aggiungiruolo(Posizione posizione, int codicec);
    public int ottienicodicesquadra(String nomes, Genere sesso );
    public void aggiungiMilitanzaCalciatore(MilitanzaCalciatore militanzaCalciatore);
    public void aggiungiMilitanzaPortiere(MilitanzaPortiere militanzaPortiere);
    public int ottienicodicecalciatore(Calciatore calciatore);
    public void eliminamilitanzacalciatore(int idEliminare);
    public void eliminamilitanzaportiere(int idEliminare);
    public void eliminavincetrofeo(int idEliminare);
    public void eliminafeature(int idEliminare);
    public void eliminaruolo(int idEliminare);
    public void eliminaRicopre(int codicec, Posizione posizione);

    public ObservableList<DisplayInfo> displayCalciatori(UserSession userSession);
    public ObservableList<DisplayMilitanza> displaymilitanze(int codicec);
    public void modificaMilitanza(DisplayMilitanza displayMilitanza, LocalDate di);

}
