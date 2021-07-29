package automat;

import java.io.Serializable;
import java.util.*;

public class GeschäftslogikImpl implements Subjekt, Serializable {
    private transient List<Beobachter> beobachterList = new LinkedList<>();
    private GanzerKuchen[] list ;
    private int fachnummer = 0;
    private Map<Hersteller, Integer> herstellerverwaltung = new HashMap();
    private Set<Allergen> allergenList = new HashSet<>();
    private int listGröße;


    public GeschäftslogikImpl(int i) {
        if(i > 0) {
            this.listGröße = i;
            this.list = new GanzerKuchen[i];
        }
    }

    private synchronized Hersteller checkHersteller(Hersteller hersteller) {
        for (Map.Entry<Hersteller, Integer> e : this.herstellerverwaltung.entrySet()){
                if(e.getKey().getName().equalsIgnoreCase(hersteller.getName())) {
                    return e.getKey();
                }
        }
        return null;
    }
    public boolean isFull() {
        for (int i = 0; i < this.listGröße; i++) {
            if(this.list[i] == null) {
                return false;
            }
        }
        return true;
    }

    public boolean addHersteller(Hersteller hersteller) {
        Hersteller h = this.checkHersteller(hersteller);
        if (h != null) {
            return false;
        } else {
            this.herstellerverwaltung.put(hersteller, 0);
            return true;

        }
    }

    public synchronized boolean addKuchen(List<Container> list, Container boden) {
        if(list != null && boden != null) {
            Hersteller h = this.checkHersteller(boden.getHersteller());
            if (h != null) {


                int pos = 0;
                boolean b = true;
                for (int i = 0; i < this.listGröße; i++) {
                    if (this.list[i] == null) {
                        pos = i;
                        b = true;
                        break;
                    } else {
                        b = false;
                    }
                }
                if (b) {
                    GanzerKuchen aktuellerState = new KuchenBoden(boden.getHersteller(), boden.getAllergens(), boden.getNährwert()
                            , boden.getHaltbarkeit(), boden.getPreis(), new Date(), new Date(),
                            this.fachnummer, boden.getTyp());

                    for (int i = 0; i < list.size(); i++) {
                        GanzerKuchen kuchen = new Dekorator(aktuellerState, list.get(i).getName(), list.get(i).getPreis()
                                , list.get(i).getHaltbarkeit(), list.get(i).getAllergens(), list.get(i).getNährwert());
                        aktuellerState = kuchen;
                    }
                    this.list[pos] = aktuellerState;

                    int anzahl = this.herstellerverwaltung.get(h);
                    anzahl++;
                    this.herstellerverwaltung.put(h, anzahl);
                    if (aktuellerState.getAllergene() != null) {
                        this.allergenList.addAll(aktuellerState.getAllergene());
                    }
                    this.fachnummer++;
                    this.benachrichtige();
                    return true;
                }
            }
        }
        return false;
    }
    private int getLength() {
        int anzahl = 0;
        for (int i = 0; i < list.length; i++) {
            if(this.list[i] != null) {
                anzahl++;
            }
        }
        return  anzahl;
    }

    public synchronized GanzerKuchen[] listKuchen(KuchenTypen typ) {
        if(typ == null) {
            GanzerKuchen[] copyArray = new GanzerKuchen[this.getLength()];
            int cnt = 0;
            for (int i = 0; i < this.listGröße; i++) {
                if(this.list[i] != null) {
                    copyArray[cnt] = this.list[i];
                    cnt++;
                }
            }
            return copyArray;
        }
        int count = 0;
        for (int i = 0; i < this.listGröße; i++) {
            if(this.list[i] != null) {
                if (this.list[i].getKuchenTyp() == typ) {
                    count++;
                }
            }
        }
        GanzerKuchen[] copyArray = new GanzerKuchen[count];
        int kuchenCount = 0;
        for (int i = 0; i < this.listGröße; i++) {
            if(this.list[i] != null) {
                if (this.list[i].getKuchenTyp() == typ) {
                    copyArray[kuchenCount] = this.list[i];
                    kuchenCount++;
                }
            }
        }
        return copyArray;
    }

    public synchronized void setInspektionsdatum(int fachnummer) {
        for (int i = 0; i < this.listGröße; i++) {
            if (this.list[i] != null && this.list[i].getFachnummer() == fachnummer) {
                KuchenBoden obj = (KuchenBoden) this.list[i];
                obj.setInspektionsdatum(new Date());
            }
        }
        this.benachrichtige();
    }




    public synchronized void löscheKuchen(int position)  {
        for (int i = 0; i < this.listGröße; i++) {
            if (this.list[i] != null && this.list[i].getFachnummer() == position) {
                GanzerKuchen remKuchen = this.list[i];
                this.checkHersteller(remKuchen.getHersteller());
                int anzahl = this.herstellerverwaltung.get(this.checkHersteller(remKuchen.getHersteller()));
                anzahl--;
                this.herstellerverwaltung.put(this.checkHersteller(remKuchen.getHersteller()), anzahl);
                this.list[i] = null;
            }
        }

        this.benachrichtige();
    }


    public HashMap<Hersteller, Integer> getHerstellerList() {
        HashMap<Hersteller, Integer> copy = new HashMap();
        copy.putAll(this.herstellerverwaltung);
        return copy;
    }

    public synchronized void löscheHersteller(String hersteller)  {

        for (int i = 0; i < this.listGröße; i++) {
            GanzerKuchen ao = this.list[i];
            if(this.list[i] != null) {
                if (ao.getHersteller().getName().equalsIgnoreCase(hersteller)) {
                    this.löscheKuchen(ao.getFachnummer());
                }
            }
        }
        Hersteller hersteller1 = this.checkHersteller(new HerstellerImpl(hersteller));
        this.herstellerverwaltung.remove(hersteller1);
    }


    public Set<Allergen> getAllergenList(boolean b) {
        HashSet<Allergen> copy;
        this.allergenList.clear();
        for (int i = 0; i < this.getLength(); i++) {
            if(this.list[i] != null) {
                GanzerKuchen ao = this.list[i];
                this.allergenList.addAll(ao.getAllergene());
            }
        }
        if (b) {
            copy = new HashSet<>();
            copy.addAll(this.allergenList);
            return copy;
        }else {
            EnumSet<Allergen> allAllergens = EnumSet.allOf(Allergen.class);
            allAllergens.removeIf(a -> this.allergenList.contains(a));
            return allAllergens;
        }
    }

    @Override
    public void meldeAn(Beobachter beobachter) {
        this.beobachterList.add(beobachter);
    }

    @Override
    public void meldeAb(Beobachter beobachter) {
        this.beobachterList.remove(beobachter);
    }

    @Override
    public void benachrichtige() {
        if(!(this.beobachterList == null)) {
            for (Beobachter b : this.beobachterList) {
                b.aktualisiere();
            }
        }
    }
    public synchronized int getFachnummer() {
        int ret = this.fachnummer;
        return ret;
    }
    public synchronized int getListGröße() {
        int ret = this.listGröße;
        return ret;
    }

}
