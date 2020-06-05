package main;

import databaze.Databaze;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import logika.Zkouska;
import rozhrani.Ovladac;

import java.io.IOException;

/**
 *  Kontroller slouží pro ovládání formuláře zkoušky
 */

public class ControllerFormulareZkousek extends Ovladac
{
    @FXML
    private TextField predmet,semestr,kapacita;
    @FXML
    private DatePicker datum;

    private Databaze databaze = new Databaze();

    /**
     *  Metoda potvrzuje formulář a podle toho zda se sem uživatel dostal přes tlačítko nová / editace
     *  Se vybere adekvátní akce a poté se vrátí uživatel zpět do přehledu všech zkoušek
     */
    @FXML
    public void potvrdit() throws IOException {

        if(predmet.getText()!= null && semestr.getText() != null && kapacita.getText() != null && datum.getValue() != null)
        {
            if(zvolenaZkouska!=null)
            {
                zvolenaZkouska.setDatum(datum.getValue());
                zvolenaZkouska.setKapacita(Integer.parseInt(kapacita.getText()));
                zvolenaZkouska.setSemestr(semestr.getText());
                zvolenaZkouska.setPredmet(predmet.getText());
                databaze.upravZkousku(zvolenaZkouska);
            }
            else
            {
                Zkouska nova = new Zkouska();
                nova.setDatum(datum.getValue());
                nova.setKapacita(Integer.parseInt(kapacita.getText()));
                nova.setSemestr(semestr.getText());
                nova.setPredmet(predmet.getText());
                databaze.pridejZkousku(nova);
            }
            prejdiDoOkna("../zdroje/vsechnyZkousky.fxml");
        }
    }

    /**
     *  Metoda přechází pouze zpět do všech zkoušek
     */
    @FXML
    public void zpet() throws IOException {
        prejdiDoOkna("../zdroje/vsechnyZkousky.fxml");
    }

    // Metoda nahrává po otevření okna do políček data
    @Override
    public void nactiData()
    {
        predmet.setText(zvolenaZkouska.getPredmet());
        semestr.setText(zvolenaZkouska.getSemestr());
        kapacita.setText(zvolenaZkouska.getKapacita()+"");
        datum.setValue(zvolenaZkouska.getDatum());
    }
}
