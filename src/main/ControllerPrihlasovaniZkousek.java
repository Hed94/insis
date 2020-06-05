package main;

import databaze.Databaze;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logika.Zkouska;
import rozhrani.Ovladac;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 *  Kontroler slouží pro ovládání obrazovky přihlašování zkoušek.
 */

public class ControllerPrihlasovaniZkousek extends Ovladac implements Initializable
{
    @FXML
    private TableView<Zkouska> seznam,prihlasene;
    @FXML
    private TableColumn<Zkouska,String> sPredmet,sSemestr,pPredmet,pSemestr;
    @FXML
    private TableColumn<Zkouska,Integer> sID,sKapacita,sPrihlaseno,pID;
    @FXML
    private TableColumn<Zkouska, LocalDate> sDatum,pDatum;

    private ObservableList<Zkouska> listVsechZkousek = FXCollections.observableArrayList();
    private ObservableList<Zkouska> listPrihlasenychZkousek = FXCollections.observableArrayList();

    private Databaze databaze = new Databaze();

    /**
     *  Metoda přihlašuje zkoušku
     *  Ale pouze v případě že je nějaká vybraná a že už na ní není uživatel přihlášen a je na ní kapacitně místo.
     */

    @FXML
    public void prihlasit() throws IOException {
        Zkouska kliknuta = prihlasene.getSelectionModel().getSelectedItem();
        if(kliknuta != null)
        {
            boolean uzPrihlaseno = false;
            for(Zkouska zkouska : listPrihlasenychZkousek)
            {
                if(zkouska.getID()==kliknuta.getID())
                {
                    uzPrihlaseno = true;
                    break;
                }
            }
            if(!uzPrihlaseno)
            {
                if(kliknuta.getKapacita()>kliknuta.getObsazeno())
                {
                    databaze.prihlasZkousku(kliknuta,getPrihlasenyUzivatel());
                    aktualizujTabulku();
                }
                else
                {
                    databaze.Chyba("Zkouška je už plně zaplněná");
                }
            }
            else
            {
                databaze.Chyba("Tuto zkoušku už máte přihlášenou");
            }
        }
        else
        {
            databaze.Chyba("Není vybrána žádna zkouška");
        }
    }

    /**
     *  Metoda maže vybranou zkoušku
     *  Ovšem pouze v případě, že je nějaká vybrána, jinak vyhodí chybovou hlášku
     */
    @FXML
    public void odhlasit() throws IOException {
        Zkouska kliknuta = prihlasene.getSelectionModel().getSelectedItem();
        if(kliknuta != null)
        {
            databaze.smazPrihlasenouZkousku(kliknuta,getPrihlasenyUzivatel());
            aktualizujTabulku();
        }
        else
        {
            databaze.Chyba("Není vybrána žádna zkouška");
        }
    }

    /**
     *  Metoda přechází zpět do menu
     */
    @FXML
    public void zpet() throws IOException {
        prejdiDoOkna("../zdroje/menu.fxml");
    }

    /**
     *  Metoda aktualizuje tabulku
     */
    public void aktualizujTabulku()
    {
        listVsechZkousek = databaze.getVsechnyDostupneZkousky();
        seznam.setItems(listVsechZkousek);

        listPrihlasenychZkousek = databaze.getVsechnyZkousky();
        prihlasene.setItems(listPrihlasenychZkousek);
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

        pPredmet.setCellValueFactory(new PropertyValueFactory<>("predmet"));
        pSemestr.setCellValueFactory(new PropertyValueFactory<>("semestr"));
        pID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        pDatum.setCellValueFactory(new PropertyValueFactory<>("datum"));

        aktualizujTabulku();
    }
}
