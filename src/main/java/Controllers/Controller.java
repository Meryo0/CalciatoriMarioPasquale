package Controllers;

import DAO.CalciatoriDAO;
import DAOImplementazione.CalciatoriDAOimpl;
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

public class Controller  {
    ObservableList<DisplayInfo> listinfo = null;
    ObservableList<DisplayMilitanza> listmil = null;
    CalciatoriDAO dao = new CalciatoriDAOimpl();

    public ObservableList<DisplayInfo> displayCalciatori(UserSession userSession){
        listinfo = dao.displayCalciatori(userSession);
        return listinfo;
    }
    public ObservableList<DisplayMilitanza> displaymilitanze(int codicec){
        listmil = dao.displaymilitanze(codicec);
        return listmil;
    }
    public int ottienicodicecalciatore(Calciatore calciatore){
        int codicec = dao.ottienicodicecalciatore(calciatore.getNome(),calciatore.getCognome());
        return codicec;
    }
    public void eliminafeature(int codicec){
        dao.eliminafeature(codicec);
    }
    public void eliminamilitanzacalciatore(int codicec){
        dao.eliminamilitanzacalciatore(codicec);
    }
    public void eliminamilitanzaportiere(int codicec){
        dao.eliminamilitanzaportiere(codicec);
    }
    public void eliminavincetrofeo(int codicec){
        dao.eliminavincetrofeo(codicec);
    }
    public void eliminaruolo(int codicec){
        dao.eliminaruolo(codicec);
    }
    public void eliminacalciatore(int codicec){
        dao.eliminacalciatore(codicec);
    }
    public void eliminaRicopre(int codicec, Posizione posizione){
        dao.eliminaRicopre(codicec,posizione);
    }

    public void inseriscicalciatore(Calciatore calciatore){
        dao.inseriscicalciatore(calciatore.getNome(),calciatore.getCognome(),calciatore.getPiede(),calciatore.getSesso(),calciatore.getDataNascita(),calciatore.getDataRitiro(),calciatore.getNazionalita());
    }
    public int ottienicodicesquadra(String nomes, Genere genere){
        int codices = dao.ottienicodicesquadra(nomes,genere);
        return codices;
    }

    public void aggiungiruolo(Posizione posizione, int codicec){
        dao.aggiungiruolo(posizione,codicec);
    }
    public void aggiungiMilitanzaPortiere(MilitanzaPortiere militanzaPortiere){
        dao.aggiungiMilitanzaPortiere(militanzaPortiere.getDatainizio(),militanzaPortiere.getDatafine(),militanzaPortiere.getGoalfatti(),militanzaPortiere.getPartitegiocate(),militanzaPortiere.getGoalsubiti(),militanzaPortiere.getCodicec(),militanzaPortiere.getCodices());
    }
    public void aggiungiMilitanzaCalciatore(MilitanzaCalciatore militanzaCalciatore){
        dao.aggiungiMilitanzaCalciatore(militanzaCalciatore.getDatainizio(),militanzaCalciatore.getDatafine(),militanzaCalciatore.getGoalfatti(),militanzaCalciatore.getPartitegiocate(),militanzaCalciatore.getCodicec(),militanzaCalciatore.getCodices());
    }
    public void modificacalciatore(Calciatore calciatore,int codicec){
        dao.modificacalciatore(calciatore.getNome(),calciatore.getCognome(),calciatore.getPiede(),calciatore.getSesso(),calciatore.getDataNascita(),calciatore.getDataRitiro(),calciatore.getNazionalita(),codicec);
    }
    public void modificaMilitanza(DisplayMilitanza displayMilitanza, LocalDate di){
        dao.modificaMilitanza(displayMilitanza,di);
    }


}
