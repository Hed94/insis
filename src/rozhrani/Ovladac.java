package rozhrani;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logika.Uzivatel;

import java.io.IOException;

public class Ovladac {
    private Uzivatel prihlasenyUzivatel;

    public void nastavUzivatele(Uzivatel uzivatel)
    {
        this.prihlasenyUzivatel = uzivatel;
    }

    public void prejdiDoOkna(String nazevOkna) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(""+nazevOkna));
        Parent root = loader.load();

        stage.setTitle("Insis");
        stage.setScene(new Scene(root));
        stage.setResizable(false);

        Ovladac ovladac = loader.getController();
        ovladac.nastavUzivatele(prihlasenyUzivatel);

        stage.show();
    }
}
