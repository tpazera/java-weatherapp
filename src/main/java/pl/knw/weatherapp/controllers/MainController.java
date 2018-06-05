package pl.knw.weatherapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.knw.weatherapp.models.main.ActualWeatherDescription;
import pl.knw.weatherapp.models.main.MainModel;
import pl.knw.weatherapp.models.main.ProjectProperties;


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
        //Property design pattern (jak singleton) - zostanie utworzona tylko jedna instancja
        ProjectProperties properties = ProjectProperties.getInstance();
        if (properties.get("ip") == null || properties.get("city") == null) {
            System.out.println("Adres IP: " + model.getIp());
            properties.put("ip", model.getIp());
            locationParams = model.getCurrentLocation();
            System.out.println("Aktualna lokalizacja: " + locationParams.get("City"));
            properties.put("city", locationParams.get("City"));
            properties.put("countrycode", locationParams.get("CountryCode"));
            properties.put("latitude", locationParams.get("Latitude"));
            properties.put("longtitude", locationParams.get("Longtitude"));
            properties.put("api", "305be23140a9d5d08890247143be3227");

        }

        ActualWeatherDescription weatherParams = model.getCurrentWeather();
        //KASIA - TUTAJ MASZ AKTUALNĄ POGODĘ - KLASA ActualWeatherDescription()
        //używaj getterów i setterów do wpisywania do labeli, np:
        //Lista parametrów: data, maksymalna temperatura, minimalna temperatura, średnia temperatura,
        //wilgotność, ciśnienie, zachmurzenie, prędkosć wiatru
        //wyświetl jakoś ładnie te wszystkie parametry
        String windSpeed = weatherParams.getWind_Speed();
        System.out.println(windSpeed);
        //<TUTAJ WPISZ DO LABELA>
    }

    @FXML
    public void goToActualWeather(ActionEvent actionEvent) throws IOException {
        System.out.println("-> Switching scene to 'Actual Weather'...");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/SettingsView.fxml"));
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setTitle("WeatherApp - Aktualna temperatura");
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    public void goToForecast(ActionEvent actionEvent) throws IOException {
        System.out.println("-> Switching scene to 'Forecast'...");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/SettingsView.fxml"));
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setTitle("WeatherApp - Prognozowana pogoda");
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    public void goToSettings(ActionEvent actionEvent) throws IOException {
        System.out.println("-> Switching scene to 'Settings'...");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/SettingsView.fxml"));
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setTitle("WeatherApp - Ustawienia");
        rootPane.getChildren().setAll(pane);
    }
}
