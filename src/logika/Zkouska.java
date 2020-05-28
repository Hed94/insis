package logika;

import java.time.LocalDate;

public class Zkouska {
    private int ID;
    private String predmet;
    private LocalDate datum;
    private String semestr;
    private int kapacita;
    private int obsazeno;

    public Zkouska(int ID, String predmet, LocalDate datum, String semestr, int kapacita, int obsazeno) {
        this.ID = ID;
        this.predmet = predmet;
        this.datum = datum;
        this.semestr = semestr;
        this.kapacita = kapacita;
        this.obsazeno = obsazeno;
    }

    public Zkouska() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getSemestr() {
        return semestr;
    }

    public void setSemestr(String semestr) {
        this.semestr = semestr;
    }

    public int getKapacita() {
        return kapacita;
    }

    public void setKapacita(int kapacita) {
        this.kapacita = kapacita;
    }

    public int getObsazeno() {
        return obsazeno;
    }

    public void setObsazeno(int obsazeno) {
        this.obsazeno = obsazeno;
    }
}
