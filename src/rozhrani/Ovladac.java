package rozhrani;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import logika.Uzivatel;
import logika.Zkouska;

import java.io.IOException;

/**
 *  Třída slouží jako vzor pro všechny kontrollery v aplikaci
 *  Neboť každý z nich musí mít jasně daného přihlášeného uživatele a také může mít danou zkoušku
 *  Vyjímkou je první okno přihlášení do aplikace, ale pro zjednodušení to implementuje také.
 *
 *  Metoda přejdi do okna přechází do okna které ji zadáme a nastaví tam přihlášeného uživatele a zvolenou zkoušku pokuď je.
 *  Zároveň pak současné okno zavře a to díky odkazu na rectangle.
 *  Což je modrý panel na každé stránce - konkrétně ten levý.
 */


public class Ovladac {
    private Uzivatel prihlasenyUzivatel;
    public Zkouska zvolenaZkouska;

    @FXML
    private Rectangle rectangle;

    public void nastavZvolenouZkousku(Zkouska zkouska)
    {
        this.zvolenaZkouska = zkouska;
    }

    public void nastavUzivatele(Uzivatel uzivatel)
    {
        this.prihlasenyUzivatel = uzivatel;
    }

    public Uzivatel getPrihlasenyUzivatel()
    {
        return this.prihlasenyUzivatel;
    }

    public void prejdiDoOkna(String nazevOkna) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(""+nazevOkna));
        Parent root = loader.load();

        stage.setTitle("Insis");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.sizeToScene();

        Ovladac ovladac = loader.getController();
        ovladac.nastavUzivatele(prihlasenyUzivatel);
        ovladac.nastavZvolenouZkousku(zvolenaZkouska);
        nactiData();

        stage.show();
        Stage soucasne = (Stage) rectangle.getScene().getWindow();
        soucasne.close();
    }

    public void nactiData() {}
}
