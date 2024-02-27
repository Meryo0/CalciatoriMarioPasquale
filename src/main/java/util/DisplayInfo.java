package util;

import Types.Piede;
import Types.Sesso;
import model.Calciatore;

import java.time.LocalDate;

public class DisplayInfo implements Cloneable {
    private int idCalciatore;
    private String nome;
    private String cognome;
    private Piede piede;
    private Sesso sesso;
    private LocalDate dataNascita ;
    private LocalDate dataRitiro;
    private String nazionalita;
    private String nomes;
    private String ruolo;
    private int goalfatti;
    private int partitegiocate;
    private Integer goalsubiti;

    public DisplayInfo(int idCalciatore, String nome, String cognome, Piede piede, Sesso sesso,
                       LocalDate dataNascita, LocalDate dataRitiro, String nazionalita, String nomes, int goalfatti, int partitegiocate, int goalsubiti, String ruolo) {
        this.idCalciatore = idCalciatore;
        this.nome = nome;
        this.cognome = cognome;
        this.piede = piede;
        this.sesso = sesso;
        this.dataNascita = dataNascita;
        this.dataRitiro = dataRitiro;
        this.nazionalita = nazionalita;
        this.nomes = nomes;
        this.goalfatti = goalfatti;
        this.partitegiocate = partitegiocate;
        this.goalsubiti = goalsubiti;
        this.ruolo = ruolo;
    }

    public DisplayInfo clone() {
        try {
            return (DisplayInfo) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Questo non dovrebbe mai accadere
        }
    }

    public String getNomes() {
        return nomes;
    }

    public void setNomes(String nomes) {
        this.nomes = nomes;
    }

    public int getGoalsubiti() {
        return goalsubiti;
    }

    public void setGoalsubiti(int goalsubiti) {
        this.goalsubiti = goalsubiti;
    }

    public int getPartitegiocate() {
        return partitegiocate;
    }

    public void setPartitegiocate(int partitegiocate) {
        this.partitegiocate = partitegiocate;
    }

    public int getGoalfatti() {
        return goalfatti;
    }

    public void setGoalfatti(int goalfatti) {
        this.goalfatti = goalfatti;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }

    public LocalDate getDataRitiro() {
        return dataRitiro;
    }

    public void setDataRitiro(LocalDate dataRitiro) {
        this.dataRitiro = dataRitiro;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public Sesso getSesso() {
        return sesso;
    }

    public void setSesso(Sesso sesso) {
        this.sesso = sesso;
    }

    public Piede getPiede() {
        return piede;
    }

    public void setPiede(Piede piede) {
        this.piede = piede;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdCalciatore() {
        return idCalciatore;
    }

    public void setIdCalciatore(int idCalciatore) {
        this.idCalciatore = idCalciatore;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
}
