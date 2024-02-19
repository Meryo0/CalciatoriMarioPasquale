package DAO;

import model.Calciatore;


import java.util.List;

public interface CalciatoriDAO {
    public void inserisci(Calciatore calciatore);
    public void modifica(Calciatore calciatore);
    public void elimina(Calciatore calciatore);

    public List <Calciatore> displaycalciatori();

}
