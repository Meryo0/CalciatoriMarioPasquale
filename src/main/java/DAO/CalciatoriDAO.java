package DAO;

import javafx.collections.ObservableList;
import model.Calciatore;
import util.DisplayInfo;

public interface CalciatoriDAO {
    public void inserisci(Calciatore calciatore);
    public void modifica(Calciatore calciatore, int idModificare);
    public void elimina(int idEliminare);

    public ObservableList<DisplayInfo> displaycalciatori();

}
