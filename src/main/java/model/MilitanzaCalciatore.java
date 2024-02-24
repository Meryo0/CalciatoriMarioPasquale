package model;

import java.time.LocalDate;

public class MilitanzaCalciatore {
    private LocalDate datainizio;
    private LocalDate datafine;
    private int goalfatti;
    private int partitegiocate;
    private int codicec;
    private int codices;
    public MilitanzaCalciatore(LocalDate data_inizio, LocalDate data_fine, int goal_fatti, int partite_giocate,  int codicec, int codices){
        this.codicec = codicec;
        this.codices = codices;
        this.datafine = data_fine;
        this.datainizio = data_inizio;
        this.goalfatti = goal_fatti;
        this.partitegiocate = partite_giocate;
    }

    public int getCodicec() {
        return codicec;
    }

    public int getCodices() {
        return codices;
    }

    public int getGoalfatti() {
        return goalfatti;
    }


    public int getPartitegiocate() {
        return partitegiocate;
    }

    public LocalDate getDatafine() {
        return datafine;
    }

    public LocalDate getDatainizio() {
        return datainizio;
    }

    public void setCodicec(int codicec) {
        this.codicec = codicec;
    }

    public void setCodices(int codices) {
        this.codices = codices;
    }

    public void setDatafine(LocalDate datafine) {
        this.datafine = datafine;
    }

    public void setDatainizio(LocalDate datainizio) {
        this.datainizio = datainizio;
    }

    public void setGoalfatti(int goalfatti) {
        this.goalfatti = goalfatti;
    }

    public void setPartitegiocate(int partitegiocate) {
        this.partitegiocate = partitegiocate;
    }
}
