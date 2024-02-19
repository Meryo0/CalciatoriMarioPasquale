package model;

import java.time.LocalDate;
import java.util.Date;

public class VinceGiocatore {
    Calciatore calciatore;
    Trofeo trofeo;
    LocalDate data_vittoria;

    public VinceGiocatore(Calciatore calciatore,Trofeo trofeo, LocalDate data_vittoria){
        this.calciatore=calciatore;
        this.trofeo=trofeo;
        this.data_vittoria=data_vittoria;
    }
    public Calciatore getCalciatore() {
        return calciatore;
    }

    public LocalDate getData_vittoria() {
        return data_vittoria;
    }

    public Trofeo getTrofeo() {
        return trofeo;
    }

    public void setCalciatore(Calciatore calciatore) {
        this.calciatore = calciatore;
    }

    public void setData_vittoria(LocalDate data_vittoria) {
        this.data_vittoria = data_vittoria;
    }

    public void setTrofeo(Trofeo trofeo) {
        this.trofeo = trofeo;
    }

}


