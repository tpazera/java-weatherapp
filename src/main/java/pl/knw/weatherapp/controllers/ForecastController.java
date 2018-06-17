package pl.knw.weatherapp.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pl.knw.weatherapp.models.forecast.ForecastModel;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ForecastController implements Initializable {

    @FXML
    public AnchorPane forecastPane;
    public GridPane yahoo_grid;
    public GridPane owm_grid;

    private static final String DEGREE = "\u00b0";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ForecastModel model;
        for(int j = 0; j < 2; j++) {
            model = new ForecastModel(j);
            GridPane api_grid;
            if(j == 0) api_grid = owm_grid;
            else api_grid = yahoo_grid;
            ArrayList<String> max = model.getMaxTemperatures();
            ArrayList<String> min = model.getMinTemperatures();
            ArrayList<String> average = model.getTemperatures();
            ArrayList<String> code = model.getWeatherCodes();
            Label result = null;
            ObservableList<Node> owm_childrens = api_grid.getChildren();
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int i = 0;
            for (Node owm_node : owm_childrens) { //2
                GridPane child = (GridPane) owm_node;
                ObservableList<Node> row_childrens = child.getChildren();
                for(Node row_node : row_childrens) { //5
                    child = (GridPane) row_node;
                    ObservableList<Node> block_childrens = child.getChildren();
                    int tmp = 0;
                    for(Node block_node : block_childrens) { //2
                        if(tmp % 2 == 0) { //grid
                            GridPane child2 = (GridPane) block_node;
                            ObservableList<Node> labels_childrens = child2.getChildren();
                            int tmp2 = 0;
                            for(Node labels_node : labels_childrens) { //3
                                result = (Label) labels_node;
                                if (tmp2 % 3 == 0) {
                                    String month;
                                    if(localDate.getMonthValue() < 10) {
                                        month = "0" + localDate.getMonthValue();
                                    } else {
                                        month = String.valueOf(localDate.getMonthValue());
                                    }
                                    result.setText(localDate.getDayOfMonth() + "." + month);
                                    localDate = localDate.plusDays(1);
                                } else if (tmp2 % 3 == 1) {
                                    String text = average.get(i);
                                    try {
                                        text = text.substring(0,5);
                                    } catch (Exception e) {

                                    }
                                    result.setText(text + DEGREE + "C");
                                } else {
                                    String text = max.get(i);
                                    String text2 = min.get(i);
                                    try {
                                        text = text.substring(0,5);
                                    } catch (Exception e) {

                                    }
                                    try {
                                        text2 = text.substring(0,5);
                                    } catch (Exception e) {

                                    }
                                    result.setText(text + "-" + text2 + DEGREE + "C");
                                }
                                tmp2++;
                            }
                        } else { //image
                            ImageView imgview = (ImageView) block_node;
                            imgview.setImage(new Image(getClass().getResourceAsStream("../images/tiles/tile0"+ 56 +".png"),25,25,true,true));
                        }
                        tmp++;
                    }
                    i++;
                }
            }
        }

    }

    public void goToMainPage(ActionEvent actionEvent) throws IOException {
        System.out.println("-> Switching scene from 'Forecast' to 'Home'...");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/MainView.fxml"));
        Stage stage = (Stage) forecastPane.getScene().getWindow();
        stage.setTitle("WeatherApp - Strona główna");
        forecastPane.getChildren().setAll(pane);
    }
}
