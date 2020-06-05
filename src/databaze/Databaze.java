package databaze;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import logika.Uzivatel;
import logika.Zkouska;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Databaze {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    // konstruktor  třídy databáze, kterým se aplikace připojí k databázi
    public Databaze()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/insis?useUnicode=yes&characterEncoding=UTF-8", "root", "");
            st = con.createStatement();
        }
        catch (Exception ex)
        {
            System.out.println("Chyba: " + ex);
        }
    }

    /**
     * Metoda slouží pro přihlašování uživatele do aplikace.
     * Vrací se přihlášený uživatel, nebo null
     * @param username
     * @param heslo
     */

    public Uzivatel prihlasUzivatele(String username, String heslo)
    {
        try
        {
            String query = "SELECT * FROM `uzivatel` where username = \"" + username + "\" AND heslo = \"" + heslo + "\"";
            rs = st.executeQuery(query);
            Uzivatel uzivatel = new Uzivatel();
            while(rs.next())
            {
                uzivatel.setID(rs.getInt(1));
                uzivatel.setJmeno(rs.getString(4));
                uzivatel.setPrijmeni(rs.getString(5));
                uzivatel.setKontaktniEmail(rs.getString(6));
                uzivatel.setDatumNarozeni(rs.getDate(7).toLocalDate());
                uzivatel.setStudent(rs.getBoolean(8));
            }
            return uzivatel;
        }
        catch(Exception ex)
        {
            System.out.println("Chyba: " + ex);
        }
        return null;
    }

    /**
     * Metoda vrací seznam všech zkoušek které jsou
     */
    public ObservableList<Zkouska> getVsechnyZkousky() {
        try
        {
            String query = "SELECT * FROM `zkouska`";
            rs = st.executeQuery(query);
            ObservableList<Zkouska>list = FXCollections.observableArrayList();
            while(rs.next())
            {
                Zkouska zkouska = new Zkouska();
                zkouska.setID(rs.getInt(1));
                zkouska.setPredmet(rs.getString(2));
                zkouska.setDatum(rs.getDate(3).toLocalDate());
                zkouska.setSemestr(rs.getString(4));
                zkouska.setKapacita(rs.getInt(5));
                list.add(zkouska);
            }
            return list;
        }
        catch(Exception ex)
        {
            System.out.println("Chyba: " + ex);
        }
        return null;
    }

    /**
     * Metoda vrací seznam všech zkoušek které jsou
     */
    public ObservableList<Zkouska> getVsechnyPrihlaseneZkousky(Uzivatel prihlasenyUzivatel) {
        try
        {
            String query = "SELECT * FROM `zkouska` WHERE `ID` IN ( Select `ID_zkousky` FROM `prihlaseneZkousky` WHERE `ID_uzivatele` = \"" + prihlasenyUzivatel.getID() + "\")";
            rs = st.executeQuery(query);
            ObservableList<Zkouska>list = FXCollections.observableArrayList();
            while(rs.next())
            {
                Zkouska zkouska = new Zkouska();
                zkouska.setID(rs.getInt(1));
                zkouska.setPredmet(rs.getString(2));
                zkouska.setDatum(rs.getDate(3).toLocalDate());
                zkouska.setSemestr(rs.getString(4));
                zkouska.setKapacita(rs.getInt(5));
                list.add(zkouska);
            }
            return list;
        }
        catch(Exception ex)
        {
            System.out.println("Chyba: " + ex);
        }
        return null;
    }

    /**
     * Metoda vrací seznam všech dostupných zkoušek které si lze přihlásit.
     */
    public ObservableList<Zkouska> getVsechnyDostupneZkousky(Uzivatel prihlasenyUzivatel) {
        try
        {
            String query = "SELECT * FROM `zkouska` WHERE `ID` NOT IN ( Select `ID_zkousky` FROM `prihlaseneZkousky` WHERE `ID_uzivatele` = \"" + prihlasenyUzivatel.getID() + "\")";
            rs = st.executeQuery(query);
            ObservableList<Zkouska>list = FXCollections.observableArrayList();
            while(rs.next())
            {
                Zkouska zkouska = new Zkouska();
                zkouska.setID(rs.getInt(1));
                zkouska.setPredmet(rs.getString(2));
                zkouska.setDatum(rs.getDate(3).toLocalDate());
                zkouska.setSemestr(rs.getString(4));
                zkouska.setKapacita(rs.getInt(5));
                list.add(zkouska);
            }
            return list;
        }
        catch(Exception ex)
        {
            System.out.println("Chyba: " + ex);
        }
        return null;
    }

    /**
     * Metoda maže zkoušku
     */
    public void smazZkousku(Zkouska kliknuta) {
        try
        {
            String query = "Delete from `zkouska` where ID = \"" + kliknuta.getID() + "\"";
            st.executeUpdate(query);
            Uspech("Zkouška byla úspěšně smazána");
        }
        catch (Exception ex)
        {
            Chyba(ex.toString());
        }
    }

    /**
     * Metoda odhlašuje / maže přihlášenou zkoušku
     */
    public void smazPrihlasenouZkousku(Zkouska kliknuta, Uzivatel prihlasenyUzivatel) {
        try
        {
            String query = "Delete from `prihlaseneZkousky` where ID_zkousky = \"" + kliknuta.getID() + "\" AND ID_uzivatele = \"" + prihlasenyUzivatel.getID() + "\"";
            st.executeUpdate(query);
            Uspech("Zkouška byla úspěšně odhlášena");
        }
        catch (Exception ex)
        {
            Chyba(ex.toString());
        }
    }

    /**
     * Metoda přidává novou zkoušku
     */
    public void pridejZkousku(Zkouska nova) {
        try
        {
            String query = "Insert into `zkouska` SET `predmet`= \"" + nova.getPredmet()
                    + "\",`datum`= \"" + nova.getDatum()
                    + "\",`semestr`= \"" + nova.getSemestr()
                    + "\",`kapacita`= \"" + nova.getKapacita() + "\"";
            st.executeUpdate(query);
            Uspech("Nová zkouška byla úspěšně přidána");
        }
        catch (Exception ex)
        {
            Chyba(ex.toString());
        }
    }

    /**
     * Metoda přihlašuje novou zkoušku
     */
    public void prihlasZkousku(Zkouska kliknuta,Uzivatel prihlasenyUzivatel) {
        try
        {
            String query = "Insert into `prihlaseneZkousky` SET `ID_zkousky`= \"" + kliknuta.getID()
                    + "\",`ID_uzivatele`= \"" + prihlasenyUzivatel.getID()+ "\"";
            st.executeUpdate(query);
            Uspech("Zkouška byla úspěšně přihlášena");
        }
        catch (Exception ex)
        {
            Chyba(ex.toString());
        }
    }

    /**
     * Metoda upravuje existující zkoušku
     */
    public void upravZkousku(Zkouska zvolenaZkouska) {
        try
        {
            String query = "UPDATE `zkouska` SET `predmet`= \"" + zvolenaZkouska.getPredmet()
                    + "\",`datum`= \"" + zvolenaZkouska.getDatum()
                    + "\",`semestr`= \"" + zvolenaZkouska.getSemestr()
                    + "\",`kapacita`= \"" + zvolenaZkouska.getKapacita()
                    + "\" where ID = \"" + zvolenaZkouska.getID() + "\"";
            st.executeUpdate(query);
            Uspech("Zkouška byla úspěšně editována");
        }
        catch (Exception ex)
        {
            Chyba(ex.toString());
        }
    }

    /**
     * Metoda upravuje existujícího uživatele
     */
    public void upravUzivatele(Uzivatel prihlasenyUzivatel)
    {
        try
        {
            String query = "UPDATE `uzivatel` SET `jmeno`= \"" + prihlasenyUzivatel.getJmeno()
                    + "\",`prijmeni`= \"" + prihlasenyUzivatel.getPrijmeni()
                    + "\",`kontaktniEmail`= \"" + prihlasenyUzivatel.getKontaktniEmail()
                    + "\",`datumNarozeni`= \"" + prihlasenyUzivatel.getDatumNarozeni()
                    + "\" where ID = \"" + prihlasenyUzivatel.getID() + "\"";
            st.executeUpdate(query);
            Uspech("Zkouška byla úspěšně editována");
        }
        catch (Exception ex)
        {
            Chyba(ex.toString());
        }
    }

    // Metoda pro výpis errorové hlášky
    public void Chyba(String error)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Chyba!");
        alert.setHeaderText("Jedno či více políček je špatně");
        alert.setContentText(error);
        alert.showAndWait();
    }

    // Metoda pro výpis úspěchové hlášky
    public void Uspech(String uspech)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Úspěch");
        alert.setHeaderText("Akce proběhla úspěšně");
        alert.setContentText(uspech);
        alert.showAndWait();
    }
}
