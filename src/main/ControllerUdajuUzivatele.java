package main;

import databaze.Databaze;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import rozhrani.Ovladac;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *  Kontroller slouží pro ovládání obrazovky s osobními údaji přihlašeného uživatele.
 */

public class ControllerUdajuUzivatele extends Ovladac
{
    @FXML
    private TextField jmeno,prijmeni,email;
    @FXML
    private DatePicker narozeni;
    @FXML
    private ImageView fotka;

    private Databaze databaze = new Databaze();

    /**
     *  Metoda potvrzuje formulář a upravuje údaje a přechází do menu
     */
    @FXML
    public void potvrdit() throws IOException {

        if(jmeno.getText()!= null && prijmeni.getText() != null && email.getText() != null && narozeni.getValue() != null)
        {
            getPrihlasenyUzivatel().setDatumNarozeni(narozeni.getValue());
            getPrihlasenyUzivatel().setJmeno(jmeno.getText());
            getPrihlasenyUzivatel().setPrijmeni(prijmeni.getText());
            getPrihlasenyUzivatel().setKontaktniEmail(email.getText());
            databaze.upravUzivatele(getPrihlasenyUzivatel());
            prejdiDoOkna("../zdroje/menu.fxml");
        }
    }

    /**
     *  Metoda přechází pouze zpět do menu
     */
    @FXML
    public void zpet() throws IOException {
        prejdiDoOkna("../zdroje/menu.fxml");
    }

    // Metoda nahrává po otevření okna do políček data a nastavuje obrázek https://randomuser.me/api/portraits/men/75.jpg
    @Override
    public void nactiData()
    {
        jmeno.setText(getPrihlasenyUzivatel().getJmeno());
        prijmeni.setText(getPrihlasenyUzivatel().getPrijmeni());
        email.setText(getPrihlasenyUzivatel().getKontaktniEmail());
        narozeni.setValue(getPrihlasenyUzivatel().getDatumNarozeni());

        String path = "https://randomuser.me/api/portraits/men/"+getPrihlasenyUzivatel().getID()+".jpg";
        String pathToOpen = "https://randomuser.me/api/portraits/men/"+getPrihlasenyUzivatel().getID()+".jpg";

        Image image = new Image(path);
        fotka.setImage(image);
    }
}
