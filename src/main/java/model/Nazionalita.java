package model;

public class Nazionalita {
    private String nomen;
    private String continente;
    public Nazionalita(String nomen, String continente){
        this.continente=continente;
        this.nomen= nomen;
    }
    public String getContinente() {
        return continente;
    }

    public String getNomen() {
        return nomen;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public void setNomen(String nomen) {
        this.nomen = nomen;
    }
}
