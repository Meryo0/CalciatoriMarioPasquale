package model;

import java.time.LocalDate;

public class VinceSquadra {
    Squadra squadra;
    Trofeo trofeo;
    LocalDate data_vittoria;

    public VinceSquadra(Squadra squadra,Trofeo trofeo, LocalDate data_vittoria){

        this.trofeo=trofeo;
        this.data_vittoria=data_vittoria;
        this.squadra=squadra;
    }

    public Squadra getSquadra() {
        return squadra;
    }

    public void setSquadra(Squadra squadra) {
        this.squadra = squadra;
    }

    public LocalDate getData_vittoria() {
        return data_vittoria;
    }

    public Trofeo getTrofeo() {
        return trofeo;
    }



    public void setData_vittoria(LocalDate data_vittoria) {
        this.data_vittoria = data_vittoria;
    }

    public void setTrofeo(Trofeo trofeo) {
        this.trofeo = trofeo;
    }
}
