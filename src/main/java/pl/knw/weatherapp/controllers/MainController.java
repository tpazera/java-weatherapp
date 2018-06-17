package pl.knw.weatherapp.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import pl.knw.weatherapp.models.main.OWMWeatherDesc;
import pl.knw.weatherapp.models.main.MainModel;
import pl.knw.weatherapp.models.main.YahooWeatherDesc;
import pl.knw.weatherapp.models.settings.ProjectProperties;


import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainController implements Initializable {

    @FXML
    public AnchorPane rootPane;
    public Map<String, String> locationParams;
    public Button actualButton;
    public Pane loading_img;
    public Label min;
    public Label max;
    public Label avg;
    public Label date;
    public Label wind;
    public Label clouds;
    public Label pressure;
    public Label humidity;
    public ImageView img_icon;

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

        OWMWeatherDesc weatherParams = model.getDataFromOWM();
        YahooWeatherDesc yahoo = model.getDataFromYahoo();
        properties.put("weathercondition", weatherParams.getIconName());
        String windstring = weatherParams.getWind_Speed();
        wind.setText(windstring);

        String avgtemperature = weatherParams.getDateTemperature();
        avg.setText(avgtemperature);

        String pressurestring = weatherParams.getPressure();
        pressure.setText(pressurestring);

        String humiditystring = weatherParams.getHumidity();
        humidity.setText(humiditystring);

        String cloudsstring = weatherParams.getClouds();
        clouds.setText(cloudsstring);

        String dateinformationsstring = weatherParams.getDateInformations();
        date.setText(dateinformationsstring);

        String maxstring = weatherParams.getMaxTemperature();
        max.setText(maxstring);

        String minstring = weatherParams.getMinTemperature();
        min.setText(minstring);

        String descriptionstring = weatherParams.getDescription();
        String imagestring = weatherParams.getIconName();
        img_icon.setImage(new Image(getClass().getResourceAsStream("../images/tiles/" + imagestring + ".gif"),300,300,false,false));
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
