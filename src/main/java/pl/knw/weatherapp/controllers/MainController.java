package pl.knw.weatherapp.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import pl.knw.weatherapp.models.main.ActualWeatherDescription;
import pl.knw.weatherapp.models.main.MainModel;
import pl.knw.weatherapp.models.settings.ProjectProperties;


import javax.swing.text.html.ImageView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MainController implements Initializable {

    @FXML
    public AnchorPane rootPane;
    public Map<String, String> locationParams;
    public Button actualButton;
    public Pane loading_img;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainModel model = new MainModel();
        loading_img.getStyleClass().clear();
        loading_img.getStyleClass().add("loading-hidden");
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
        //wilgotność, ciśnienie, zachmurzenie, prędkosć wiatru, opis, kod obrazka (zdj na dysku), kod pogody (link na strone)
        //na podstawie kodu obrazka lub kodu pogody wyświetl odpowiedni obrazek
        //fajnie by było też spolszczyć te opisy, czyli zrobić ify że gdy opis jest równy "clear sky" to wpisujesz do labela "Czyste niebo"
        //wyświetl jakoś ładnie te wszystkie parametry
        String windSpeed = weatherParams.getWind_Speed();
        System.out.println(windSpeed);
        //<TUTAJ WPISZ DO LABELA>
    }

    @FXML
    public void goToActualWeather(ActionEvent actionEvent) {
        System.out.println("-> Switching scene to 'Actual Weather'...");
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1),
                ae -> showLoadingImage()),
                new KeyFrame(
                        Duration.millis(2000),
                        ae -> {
                            try {
                                showActualWeather();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }));
        timeline.play();

    }

    private void showActualWeather() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/ActualView.fxml"));
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setTitle("WeatherApp - Aktualna temperatura");
        rootPane.getChildren().setAll(pane);
    }

    private void showLoadingImage() {
        loading_img.getStyleClass().clear();
        loading_img.getStyleClass().add("loading-visible");
    }

    @FXML
    public void goToForecast(ActionEvent actionEvent) throws IOException {
        System.out.println("-> Switching scene to 'Forecast'...");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/ForecastView.fxml"));
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
