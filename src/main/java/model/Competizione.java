package model;

public class Competizione {
    private String nomef;
    private String genere;
    private String tipo_competizione;

    public Competizione (String nomef, String genere, String tipo_competizione){
        this.genere=genere;
        this.tipo_competizione=tipo_competizione;
        this.nomef=nomef;
    }
    public String getGenere() {
        return genere;
    }

    public String getNomef() {
        return nomef;
    }

    public String getTipo_competizione() {
        return tipo_competizione;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public void setNomef(String nomef) {
        this.nomef = nomef;
    }

    public void setTipo_competizione(String tipo_competizione) {
        this.tipo_competizione = tipo_competizione;
    }
}

