package pl.knw.weatherapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import pl.knw.weatherapp.models.actual.ActualModel;
import pl.knw.weatherapp.models.actual.Sites;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ActualController implements Initializable {

    @FXML
    public AnchorPane rootPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rootPane.setBackground(Background.EMPTY);
        ActualModel model = new ActualModel();
        ArrayList< Sites > sites = model.getWeatherModels();
        Iterator iterator = new ListIterator(sites);
        Sites site = new Sites();
        while (iterator.hasNext()) {
            site = (Sites) iterator.next();
            site.getCurrentTemperature();
            System.out.println(site.getName());
        }


    }

    public void goToMainPage(ActionEvent actionEvent) throws IOException {
        System.out.println("-> Switching scene from 'Actual' to 'Home'...");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/MainView.fxml"));
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setTitle("WeatherApp - Strona główna");
        rootPane.getChildren().setAll(pane);
    }
}
