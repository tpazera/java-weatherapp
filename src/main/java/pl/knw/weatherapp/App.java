package pl.knw.weatherapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.knw.weatherapp.models.settings.ProjectProperties;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/MainView.fxml"));
        primaryStage.setTitle("WeatherApp - Strona główna");
        Scene rootScene = new Scene(root, 900, 600);
        ProjectProperties properties = ProjectProperties.getInstance();
        Scanner sc = null;
        try {
            sc = new Scanner(new File("config.cfg"));
            List<String> lines = new ArrayList<String>();
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
            String[] fileLines = lines.toArray(new String[0]);
            properties.put("name", fileLines[0]);
            Integer[] sitesSettings = {Integer.valueOf(fileLines[1]), Integer.valueOf(fileLines[2]), Integer.valueOf(fileLines[3]), Integer.valueOf(fileLines[4]), Integer.valueOf(fileLines[5]), Integer.valueOf(fileLines[6])};
            properties.put("sites", sitesSettings);
            int tmp = Integer.parseInt(fileLines[7])+1;
            properties.put("style", "style" + tmp +".css");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        rootScene.getStylesheets().add(String.valueOf(getClass().getResource("styles/" + properties.get("style"))));
        rootScene.setFill(Color.TRANSPARENT);
        System.out.println(properties.get("style"));
        primaryStage.setScene(rootScene);
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
