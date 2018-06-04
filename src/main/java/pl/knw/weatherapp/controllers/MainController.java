package pl.knw.weatherapp.controllers;

import com.maxmind.geoip.Location;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.knw.weatherapp.models.MainModel;


import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public AnchorPane rootPane;
    public Map<String, String> locationParams, locationParams2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainModel model = new MainModel();
        System.out.println("Adres IP: " + model.getIp());
        locationParams = model.getCurrentLocation();
        locationParams2 = model.getCurrentWeather();
        System.out.println("Aktualna lokalizacja: " + locationParams);

    }

    @FXML
    public void goToActualWeather(ActionEvent actionEvent) throws IOException {
        System.out.println("Switching scene to 'Actual Weather'...");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/SettingsView.fxml"));
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setTitle("WeatherApp - Aktualna temperatura");
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    public void goToForecast(ActionEvent actionEvent) throws IOException {
        System.out.println("Switching scene to 'Forecast'...");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/SettingsView.fxml"));
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setTitle("WeatherApp - Prognozowana pogoda");
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    public void goToSettings(ActionEvent actionEvent) throws IOException {
        System.out.println("Switching scene to 'Settings'...");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/SettingsView.fxml"));
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setTitle("WeatherApp - Ustawienia");
        rootPane.getChildren().setAll(pane);
    }
}
