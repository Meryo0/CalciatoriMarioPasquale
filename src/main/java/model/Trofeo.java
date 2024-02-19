package model;

public class Trofeo {
    private String nomet;
    private String descrizione;
    private String tipo_trofeo;

    public Trofeo(String nomet, String descrizione, String tipo_trofeo) {
        this.nomet=nomet;
        this.descrizione=descrizione;
        this.tipo_trofeo=tipo_trofeo;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getNomet() {
        return nomet;
    }

    public String getTipo_trofeo() {
        return tipo_trofeo;
    }

    public void setNomet(String nomet) {
        this.nomet = nomet;
    }

    public void setTipo_trofeo(String tipo_trofeo) {
        this.tipo_trofeo = tipo_trofeo;
    }
}
