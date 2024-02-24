package model;

import Types.Genere;

public class Squadra {

    private int idSquadra;
    private String nomes;
    private Genere genere;
    private String tipo_squadra;
    private Nazionalita nazionalita;

    public Squadra(int idSquadra, String nomes, Genere genere, String tipo_squadra, Nazionalita nazionalita){
        this.genere=genere;
        this.idSquadra=idSquadra;
        this.nazionalita=nazionalita;
        this.nomes=nomes;
        this.tipo_squadra=tipo_squadra;
    }


    public Genere getGenere() {
        return genere;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    public void setNome(String nomes) {
        this.nomes = nomes;
    }

    public String getNome() {
        return nomes;
    }

    public int getIdSquadra() {
        return idSquadra;
    }

    public Nazionalita getNazionalita() {
        return nazionalita;
    }

    public void setIdSquadra(int idSquadra) {
        this.idSquadra = idSquadra;
    }

    public String getTipo_squadra() {
        return tipo_squadra;
    }

    public void setTipo_squadra(String tipo_squadra) {
        this.tipo_squadra = tipo_squadra;
    }

    public void setNazionalita(Nazionalita nazionalita) {
        this.nazionalita = nazionalita;
    }

}
