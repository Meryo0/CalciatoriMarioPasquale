package util;

import java.time.LocalDate;

public class DisplayMilitanza implements Cloneable {
    private LocalDate datainizio;
    private LocalDate datafine;
    private int goalfatti;
    private int partitegiocate;
    private Integer goalsubiti;
    private int codicec;
    private int codices;
    private  String nomes;
    public DisplayMilitanza(LocalDate data_inizio, LocalDate data_fine, int goal_fatti, int partite_giocate, Integer goal_subiti, int codicec, int codices, String nomes){
        this.codicec = codicec;
        this.codices = codices;
        this.datafine = data_fine;
        this.datainizio = data_inizio;
        this.goalfatti = goal_fatti;
        this.goalsubiti = goal_subiti;
        this.partitegiocate = partite_giocate;
        this.nomes = nomes;
    }
    public DisplayMilitanza clone() {
        try {
            return (DisplayMilitanza) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Questo non dovrebbe mai accadere
        }
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

    public Integer getGoalsubiti() {
        return goalsubiti;
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

    public void setGoalsubiti(Integer goalsubiti) {
        this.goalsubiti = goalsubiti;
    }

    public void setPartitegiocate(int partitegiocate) {
        this.partitegiocate = partitegiocate;
    }

    public String getNomes() {
        return nomes;
    }

    public void setNomes(String nomes) {
        this.nomes = nomes;
    }
}
