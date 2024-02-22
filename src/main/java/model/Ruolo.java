package model;

import Types.Posizione;

public class Ruolo implements Cloneable {

    Posizione posizione;

    public Ruolo(Posizione posizione) {
        this.posizione = posizione;
    }
    public Ruolo clone() {
        try {
            return (Ruolo) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Questo non dovrebbe mai accadere
        }
    }

    public Posizione getPosizione() {
        return posizione;
    }

    public void setPosizione(Posizione posizione) {
        this.posizione = posizione;
    }
}


