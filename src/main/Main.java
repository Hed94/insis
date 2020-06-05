package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    private static Main mInstance;

    public static Main getInstance() {
        return mInstance;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../zdroje/prihlasovaciObrazovka.fxml"));
        primaryStage.setTitle("Insis");
        primaryStage.getIcons().add(new Image("/zdroje/vseLogo.png"));
        primaryStage.setScene(new Scene(root, 541, 472));
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
