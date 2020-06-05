package main;

import databaze.Databaze;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logika.Zkouska;
import rozhrani.Ovladac;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 *  Kontroler slouží pro ovládání obrazovky všech zkoušek.
 */

public class ControllerVsechZkousek extends Ovladac implements Initializable
{
    @FXML
    private TableView<Zkouska> seznam;
    @FXML
    private TableColumn<Zkouska,String> sPredmet,sSemestr;
    @FXML
    private TableColumn<Zkouska,Integer> sID,sKapacita,sPrihlaseno;
    @FXML
    private TableColumn<Zkouska, LocalDate> sDatum;

    private ObservableList<Zkouska> listZkousek = FXCollections.observableArrayList();

    /**
     *  Metoda přechází do formuláře zkoušek na vytvoření nové
     */

    @FXML
    public void nova() throws IOException {
        zvolenaZkouska = null;
        prejdiDoOkna("formularZkousek");
    }

    /**
     *  Metoda kontroluje zda je nějaká zkouška vybrána a pokuď ano, tak se přejde do editace
     *  Případně vyhodí chybu ve formě hlášky
     */
    @FXML
    public void editovat() throws IOException
    {
        Zkouska kliknuta = seznam.getSelectionModel().getSelectedItem();
        if(kliknuta != null)
        {
            zvolenaZkouska = kliknuta;
            prejdiDoOkna("formularZkousek");
        }
        else
        {
           Databaze.Chyba("Není vybrána žádna zkouška");
        }
    }

    /**
     *  Metoda maže vybranou zkoušku
     *  Ovšem pouze v případě, že je nějaká vybrána, jinak vyhodí chybovou hlášku
     */
    @FXML
    public void smazat() throws IOException {
        Zkouska kliknuta = seznam.getSelectionModel().getSelectedItem();
        if(kliknuta != null)
        {
            Databaze.smazZkousku(kliknuta);
        }
        else
        {
            Databaze.Chyba("Není vybrána žádna zkouška");
        }
    }

    /**
     *  Metoda přechází zpět do menu
     */
    @FXML
    public void zpet() throws IOException {
        prejdiDoOkna("menu");
    }

    /**
     *  Metoda aktualizuje tabulku
     */
    public void aktualizujTabulku()
    {
        listZkousek = Databaze.getVsechnyZkousky();
        seznam.setItems(listZkousek);
    }

    // Zobrazení správného tlačítka podle typu přihlášeného uživatele
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        sPredmet.setCellValueFactory(new PropertyValueFactory<>("predmet"));
        sSemestr.setCellValueFactory(new PropertyValueFactory<>("semestr"));

        sID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        sKapacita.setCellValueFactory(new PropertyValueFactory<>("kapacita"));
        sPrihlaseno.setCellValueFactory(new PropertyValueFactory<>("obsazeno"));

        sDatum.setCellValueFactory(new PropertyValueFactory<>("datum"));

        aktualizujTabulku();
    }
}
