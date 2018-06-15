package pl.knw.weatherapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rootPane.setBackground(Background.EMPTY);

        List< GridPane > gridpanes = new ArrayList<>();
        gridpanes.add(gridpane1);
        gridpanes.add(gridpane2);
        gridpanes.add(gridpane3);
        gridpanes.add(gridpane4);
        gridpanes.add(gridpane5);
        gridpanes.add(gridpane6);

        ActualModel model = new ActualModel();
        ArrayList< Sites > sites = model.getWeatherModels();
        Iterator iterator = new ListIterator(sites);

        int numberOfGridPane = iterator.size();
        Sites site = new Sites();

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
            site.getCurrentTemperature();
            System.out.println(site.getName());
            i++;
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
