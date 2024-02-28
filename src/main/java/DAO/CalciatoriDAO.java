package DAO;

import Types.Genere;
import Types.Piede;
import Types.Posizione;
import Types.Sesso;
import javafx.collections.ObservableList;
import model.Calciatore;
import model.MilitanzaCalciatore;
import model.MilitanzaPortiere;
import util.DisplayInfo;
import util.DisplayMilitanza;
import util.UserSession;

import java.time.LocalDate;

public interface CalciatoriDAO {

    public void modificacalciatore(String nome, String cognome, Piede piede, Sesso sesso, LocalDate dataNascita, LocalDate dataRitiro, String nazionalita, int idModificare);
    public void eliminacalciatore(int idEliminare);
    public void aggiungiruolo(Posizione posizione, int codicec);
    public int ottienicodicesquadra(String nomes, Genere sesso );
    public void inseriscicalciatore(String nome, String cognome, Piede piede, Sesso sesso, LocalDate dataNascita, LocalDate dataRitiro, String nazionalita);
    public void  aggiungiMilitanzaCalciatore(LocalDate datainizio, LocalDate datafine, int goalfatti, int partitegiocate, int codicec, int codices);
    public void aggiungiMilitanzaPortiere(LocalDate datainizio, LocalDate datafine, int goalfatti, int partitegiocate, int goalsubiti, int codicec, int codices);
    public int ottienicodicecalciatore(String nome , String cognome);
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
