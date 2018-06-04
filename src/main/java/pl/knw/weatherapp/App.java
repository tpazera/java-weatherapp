package pl.knw.weatherapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/MainView.fxml"));
        primaryStage.setTitle("WeatherApp - Strona główna");
        Scene rootScene = new Scene(root, 800, 600);
        rootScene.getStylesheets().add(String.valueOf(getClass().getResource("styles/style.css")));
        primaryStage.setScene(rootScene);
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
