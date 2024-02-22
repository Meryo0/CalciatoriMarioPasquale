package model;

import Types.Piede;
import Types.Sesso;

import java.time.LocalDate;

public class Calciatore implements Cloneable {
    private int idCalciatore;
    private String nome;
    private String cognome;
    private Piede piede;
    private Sesso sesso;
    private LocalDate dataNascita ;
    private LocalDate dataRitiro;
    private String nazionalita;
    public Calciatore ( String nome, String cognome, Piede piede, Sesso sesso, LocalDate dataNascita, LocalDate dataRitiro, String nazionalita){
        this.nome=nome;
        this.cognome=cognome;
        this.piede=piede;
        this.sesso=sesso;
        this.dataNascita=dataNascita;
        this.dataRitiro=dataRitiro;
        this.nazionalita=nazionalita;
    }
    public Calciatore clone() {
        try {
            return (Calciatore) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Questo non dovrebbe mai accadere
        }
    }
    public int getIdCalciatore() {
        return idCalciatore;
    }

    public String getCognome() {
        return cognome;
    }

    public Sesso getSesso() {
        return sesso;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public LocalDate getDataRitiro() {
        return dataRitiro;
    }

    public String getNome() {
        return nome;
    }

    public Piede getPiede() {
        return piede;
    }

    public void setIdCalciatore(int idCalciatore) {
        this.idCalciatore = idCalciatore;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public void setDataRitiro(LocalDate dataRitiro) {
        this.dataRitiro = dataRitiro;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPiede(Piede piede) {
        this.piede = piede;
    }

    public void setSesso(Sesso sesso) {
        this.sesso = sesso;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }
}
