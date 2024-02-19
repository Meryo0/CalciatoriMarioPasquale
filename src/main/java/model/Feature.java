package model;

public class Feature {
    private String nomef;
    private String descrizione;

    public Feature (String nomef, String descrizione)
    {
        this.descrizione=descrizione;
        this.nomef=nomef;
    }
    public void setNomef(String nomef) {
        this.nomef = nomef;
    }

    public String getNomef() {
        return nomef;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}


