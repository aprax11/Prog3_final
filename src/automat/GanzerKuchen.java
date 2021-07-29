package automat;


import java.util.Date;

public interface GanzerKuchen extends Verkaufsobjekt, Kuchen {

    Date getEinfügeDate();
    String getName();
    KuchenTypen getKuchenTyp();
}
