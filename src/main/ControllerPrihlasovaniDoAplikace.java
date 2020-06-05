package main;

import databaze.Databaze;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import rozhrani.Ovladac;

import java.io.IOException;

/**
 *  Kontroler slouží pro ovládání přihlašovacího formuláře.
 *  Pokuď uživatel existuje, tak přejde do menu, pokuď ne vyběhne okno.
 */

public class ControllerPrihlasovaniDoAplikace extends Ovladac {
    @FXML
    TextField uzivatelskeJmeno;
    @FXML
    PasswordField heslo;

    private Databaze databaze = new Databaze();

    @FXML
    public void prihlasSe() throws IOException {
        nastavUzivatele(databaze.prihlasUzivatele(uzivatelskeJmeno.getText(),heslo.getText()));
        if(getPrihlasenyUzivatel() != null)
        {
            prejdiDoOkna("../zdroje/menu.fxml");
        }
        else
        {
            databaze.Chyba("Špatná kombinace jména nebo hesla");
        }
    }
}
