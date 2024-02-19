package model;

import java.time.LocalDate;

public class Calciatore {
    private int idCalciatore;
    private String nome;
    private String cognome;
    private String piede;
    private char sesso;
    private LocalDate dataNascita;
    private LocalDate dataRitiro;
    public Calciatore (int idCalciatore, String nome, String cognome, String piede, char sesso, LocalDate dataNascita, LocalDate dataRitiro){
        this.idCalciatore=idCalciatore;
        this.nome=nome;
        this.cognome=cognome;
        this.piede=piede;
        this.sesso=sesso;
        this.dataNascita=dataNascita;
        this.dataRitiro=dataRitiro;
    }

    public int getIdCalciatore() {
        return idCalciatore;
    }

    public String getCognome() {
        return cognome;
    }

    public char getSesso() {
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

    public String getPiede() {
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

    public void setPiede(String piede) {
        this.piede = piede;
    }

    public void setSesso(char sesso) {
        this.sesso = sesso;
    }
}
