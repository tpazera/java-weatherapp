package pl.knw.weatherapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.knw.weatherapp.models.forecast.ForecastModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ForecastController implements Initializable {

    @FXML
    public AnchorPane rootPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ForecastModel model = new ForecastModel(0);
        System.out.println(model.getMaxTemperatures());

    }

    public void goToMainPage(ActionEvent actionEvent) throws IOException {
        System.out.println("-> Switching scene from 'Forecast' to 'Home'...");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/MainView.fxml"));
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setTitle("WeatherApp - Strona główna");
        rootPane.getChildren().setAll(pane);
    }
}
