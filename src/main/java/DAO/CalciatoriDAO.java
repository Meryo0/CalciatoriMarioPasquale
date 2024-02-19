package DAO;

import model.Calciatore;


import java.util.List;

public interface CalciatoriDAO {
    public void inserisci(Calciatore calciatore);
    public void modifica(Calciatore calciatore, int idModificare);
    public void elimina(int idEliminare);

    public List <Calciatore> displaycalciatori();

}
