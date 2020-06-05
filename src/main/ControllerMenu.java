package main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import rozhrani.Ovladac;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *  Kontroler slouží pro ovládání obrazovky menu.
 *  V podstatě se zde řeší pouze přecházení do jiných oken aplikace.
 *  A podle typu uživatele ( učitel / student ) se zobrazí tlačítko pro správu, nebo přihlašování na zkoušky.
 */

public class ControllerMenu extends Ovladac implements Initializable
{
    @FXML
    Button tPrihlasovani,tSprava;

    @FXML
    public void udaje() throws IOException {
        prejdiDoOkna("udajeUzivatele");
    }

    @FXML
    public void zkousky() throws IOException {
        prejdiDoOkna("prihlasovaniZkousky");
    }

    @FXML
    public void sprava() throws IOException {
        prejdiDoOkna("vsechnyZkousky");
    }

    @FXML
    public void odhlasSe() throws IOException {
        prejdiDoOkna("prihlasovaciObrazovka");
    }

    // Zobrazení správného tlačítka podle typu přihlášeného uživatele
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        if(getPrihlasenyUzivatel().isStudent())
        {
            // Jsem student
            tPrihlasovani.setVisible(true);
            tSprava.setVisible(false);
        }
        else
        {
            // Nejsem student
            tPrihlasovani.setVisible(false);
            tSprava.setVisible(true);
        }
    }
}
