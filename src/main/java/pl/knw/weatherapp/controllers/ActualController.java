package pl.knw.weatherapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import net.aksingh.owmjapis.CurrentWeather;
import pl.knw.weatherapp.models.actual.ActualModel;
import pl.knw.weatherapp.models.actual.Sites;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ActualController implements Initializable {

    @FXML
    public AnchorPane rootPane;
    public GridPane gridpane1, gridpane2, gridpane3, gridpane4, gridpane5, gridpane6;
    public ImageView img1, img2, img3, img4, img5, img6;
    public Label temperature1, temperature2, temperature3, temperature4, temperature5, temperature6;
    public Label wind1, wind2, wind3, wind4, wind5, wind6;
    public Label pressure1, pressure2, pressure3, pressure4, pressure5, pressure6;
    public Label cloudy1, cloudy2, cloudy3, cloudy4, cloudy5, cloudy6;
    public Label humidity1, humidity2, humidity3, humidity4, humidity5, humidity6;
    public Label rain1, rain2, rain3, rain4, rain5, rain6;
    private List< GridPane > gridpanes = new ArrayList<>();
    private List< ImageView > imageviews = new ArrayList<>();
    private List< Label > temperature_labels = new ArrayList<>();
    private List< Label > wind_labels = new ArrayList<>();
    private List< Label > pressure_labels = new ArrayList<>();
    private List< Label > cloudy_labels = new ArrayList<>();
    private List< Label > humidity_labels = new ArrayList<>();
    private List< Label > rain_labels = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //rootPane.setBackground(Background.EMPTY);

        fillListWithObjects();

        ActualModel model = new ActualModel();
        ArrayList< Sites > sites = model.getWeatherModels();
        Iterator iterator = new ListIterator(sites);

        int numberOfGridPane = iterator.size();
        Sites site;

        int i = 0;
        while (iterator.hasNext()) {
            gridpanes.get(i).getStyleClass().clear();
            gridpanes.get(i).getStyleClass().add("actual-grid-visible");
            if(numberOfGridPane == 1) {
                double posx = 900 / 2 - 109;
                gridpanes.get(i).setLayoutX(posx);
            } else if (numberOfGridPane == 2) {
                double posx = (i + 1) * 155 + i * 218;
                gridpanes.get(i).setLayoutX(posx);
            } else if (numberOfGridPane == 3) {
                double posx = (i + 1) * 61 + i * 218;
                gridpanes.get(i).setLayoutX(posx);
            }
            site = (Sites) iterator.next();
            temperature_labels.get(i).setText("Temperatura: " + site.getCurrentTemperature());
            wind_labels.get(i).setText("Wiatr: " + site.getCurrentWind());
            pressure_labels.get(i).setText("Ciśnienie: " + site.getCurrentPressure());
            cloudy_labels.get(i).setText("Zachmurzenie: " + site.getCurrentCloudy());
            humidity_labels.get(i).setText("Wilgotność: " + site.getCurrentHumidity());
            rain_labels.get(i).setText("Deszcz: " + site.getCurrentRain());
            System.out.println("../images/"+site.getName()+"logo.png");
            imageviews.get(i).setImage(new Image(getClass().getResourceAsStream("../images/logo/"+site.getName()+"logo.png")));

            //System.out.println(site.getName());
            i++;
        }


    }

    private void fillListWithObjects() {
        //Gridpanes
        gridpanes.add(gridpane1);
        gridpanes.add(gridpane2);
        gridpanes.add(gridpane3);
        gridpanes.add(gridpane4);
        gridpanes.add(gridpane5);
        gridpanes.add(gridpane6);
        //ImageViews
        imageviews.add(img1);
        imageviews.add(img2);
        imageviews.add(img3);
        imageviews.add(img4);
        imageviews.add(img5);
        imageviews.add(img6);
        //TemperatureLabels
        temperature_labels.add(temperature1);
        temperature_labels.add(temperature2);
        temperature_labels.add(temperature3);
        temperature_labels.add(temperature4);
        temperature_labels.add(temperature5);
        temperature_labels.add(temperature6);
        //WindLabels
        wind_labels.add(wind1);
        wind_labels.add(wind2);
        wind_labels.add(wind3);
        wind_labels.add(wind4);
        wind_labels.add(wind5);
        wind_labels.add(wind6);
        //PressureLabels
        pressure_labels.add(pressure1);
        pressure_labels.add(pressure2);
        pressure_labels.add(pressure3);
        pressure_labels.add(pressure4);
        pressure_labels.add(pressure5);
        pressure_labels.add(pressure6);
        //CloudyLabels
        cloudy_labels.add(cloudy1);
        cloudy_labels.add(cloudy2);
        cloudy_labels.add(cloudy3);
        cloudy_labels.add(cloudy4);
        cloudy_labels.add(cloudy5);
        cloudy_labels.add(cloudy6);
        //HumidityLabels
        humidity_labels.add(humidity1);
        humidity_labels.add(humidity2);
        humidity_labels.add(humidity3);
        humidity_labels.add(humidity4);
        humidity_labels.add(humidity5);
        humidity_labels.add(humidity6);
        //RainLabels
        rain_labels.add(rain1);
        rain_labels.add(rain2);
        rain_labels.add(rain3);
        rain_labels.add(rain4);
        rain_labels.add(rain5);
        rain_labels.add(rain6);
    }


    public void goToMainPage(ActionEvent actionEvent) throws IOException {
        System.out.println("-> Switching scene from 'Actual' to 'Home'...");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/MainView.fxml"));
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setTitle("WeatherApp - Strona główna");
        rootPane.getChildren().setAll(pane);
    }
}
