package logika;

import java.time.LocalDate;

public class Uzivatel {
    private String username;
    private String heslo;
    private boolean student;
    private String jmeno;
    private String prijmeni;
    private String kontaktniEmail;
    private LocalDate datumNarozeni;

    public Uzivatel(String username, String heslo, boolean student, String jmeno, String prijmeni, String kontaktniEmail, LocalDate datumNarozeni) {
        this.username = username;
        this.heslo = heslo;
        this.student = student;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.kontaktniEmail = kontaktniEmail;
        this.datumNarozeni = datumNarozeni;
    }

    public Uzivatel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public String getKontaktniEmail() {
        return kontaktniEmail;
    }

    public void setKontaktniEmail(String kontaktniEmail) {
        this.kontaktniEmail = kontaktniEmail;
    }

    public LocalDate getDatumNarozeni() {
        return datumNarozeni;
    }

    public void setDatumNarozeni(LocalDate datumNarozeni) {
        this.datumNarozeni = datumNarozeni;
    }
}
