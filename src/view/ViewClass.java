package view;

import automat.Allergen;

import automat.DurationRechner;
import automat.GanzerKuchen;
import automat.Hersteller;

import java.util.Date;
import java.util.Map;
import java.util.Set;


public class ViewClass {

    public void printList(GanzerKuchen[] list) {
        for (GanzerKuchen a : list) {
            System.out.println("Fachnummer: " + a.getFachnummer() + System.lineSeparator() + "Inspektionsdatum: "
                    + a.getInspektionsdatum() + System.lineSeparator() + "verbleibende Haltbarkeit in Tagen:"
                    + DurationRechner.getVerbleibendeHaltbarkeit(a.getEinfügeDate(), new Date(), a.getHaltbarkeit()).toDays());
        }
    }
    public void printHersteller(Map<Hersteller, Integer> list) {
        for(Map.Entry<Hersteller, Integer> e : list.entrySet()) {
            System.out.println("Hersteller: " + e.getKey().getName() + " Kuchen Anzahl: " + e.getValue());
        }
    }
    public void printAllergene(Set<Allergen> list) {
        System.out.println(list.toString());
    }
}

