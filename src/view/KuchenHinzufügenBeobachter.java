package view;

import automat.GeschäftslogikImpl;
import automat.Beobachter;

public class KuchenHinzufügenBeobachter implements Beobachter {
    private GeschäftslogikImpl gl;
    private int oldState;

    public KuchenHinzufügenBeobachter(GeschäftslogikImpl gl) {
        this.gl = gl;
        this.gl.meldeAn(this);
        this.oldState = this.gl.listKuchen(null).length;
    }

    @Override
    public void aktualisiere() {
        int newState = this.gl.listKuchen(null).length;
        if(this.oldState > newState) {
            System.out.println("Beobachter: Kuchen wurde wirklich entfernt");
            this.oldState = newState;
        }else  if(this.oldState < newState) {
            System.out.println("Beobachter: Kuchen wurde wirklich hinzugefügt");
            this.oldState = newState;
        }
    }
}
