package pl.knw.weatherapp.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.knw.weatherapp.models.settings.ProjectProperties;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    public AnchorPane rootPane;
    public Label ipLabel, cityLabel;
    public TextField nameTextField;
    public ToggleButton wpToggleButton, onetToggleButton, interiaToggleButton, forecaToggleButton, pogodaToggleButton, gismeteoToggleButton;
    public ChoiceBox choicebox;
    private List<ToggleButton> togglebuttons = new ArrayList<ToggleButton>();
    public Integer[] sitesSettings;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProjectProperties properties = ProjectProperties.getInstance();

        String css = String.valueOf(getClass().getResource("../styles/" + properties.get("style")));
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(css);

        ipLabel.setText("Adres IP: " + properties.get("ip"));
        cityLabel.setText("Lokalizacja: " + properties.get("city"));
        nameTextField.setText((String) properties.get("name"));
        sitesSettings = (Integer[]) properties.get("sites");
        togglebuttons.add(wpToggleButton);
        togglebuttons.add(onetToggleButton);
        togglebuttons.add(interiaToggleButton);
        togglebuttons.add(forecaToggleButton);
        togglebuttons.add(pogodaToggleButton);
        togglebuttons.add(gismeteoToggleButton);

        for(int i = 0; i < togglebuttons.size(); i++) {
            if(sitesSettings[i] == 1) {
                togglebuttons.get(i).setSelected(false);
                togglebuttons.get(i).setText("Wyłącz pobieranie z tej strony");
                togglebuttons.get(i).getStyleClass().add("notselected");
            } else {
                togglebuttons.get(i).setSelected(true);
                togglebuttons.get(i).setText("Włącz pobieranie z tej strony");
                togglebuttons.get(i).getStyleClass().add("selected");
            }
            final int finalI = i;
            togglebuttons.get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    if(sitesSettings[finalI] == 1) {
                        sitesSettings[finalI] = 0;
                        togglebuttons.get(finalI).setSelected(false);
                        togglebuttons.get(finalI).setText("Włącz pobieranie z tej strony");
                        togglebuttons.get(finalI).getStyleClass().remove("notselected");
                        togglebuttons.get(finalI).getStyleClass().add("selected");
                    } else {
                        sitesSettings[finalI] = 1;
                        togglebuttons.get(finalI).setSelected(true);
                        togglebuttons.get(finalI).setText("Wyłącz pobieranie z tej strony");
                        togglebuttons.get(finalI).getStyleClass().remove("selected");
                        togglebuttons.get(finalI).getStyleClass().add("notselected");
                    }
                }
            });

        }

        if (Objects.requireNonNull(properties.get("style")).toString().equals("style1.css")) {
            choicebox.setValue("style1.css");
        } else if (Objects.requireNonNull(properties.get("style")).toString().equals("style2.css")) {
            choicebox.setValue("style2.css");
        }

        choicebox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                ProjectProperties properties = ProjectProperties.getInstance();
                properties.put("style", choicebox.getItems().get((Integer) number2));
                Scene scene = choicebox.getScene();
                System.out.println(properties.get("style"));
                String css = String.valueOf(getClass().getResource("../styles/" + properties.get("style")));
                rootPane.getStylesheets().clear();
                rootPane.getStylesheets().add(css);
            }
        });

    }

    public void goToMainPage(ActionEvent actionEvent) throws IOException {
        final ProjectProperties properties = ProjectProperties.getInstance();
        System.out.println("-> Switching scene from 'Settings' to 'Home'...");
        PrintWriter writer = new PrintWriter(new File("config.cfg"));
        writer.println(nameTextField.getText());
        properties.put("name", nameTextField.getText());
        properties.put("sites", sitesSettings);
        for (Integer sitesSetting : sitesSettings) {
            if (sitesSetting == 0) writer.println(0);
            else writer.println(1);
        }
        if(choicebox.getValue().toString().equals("style1.css")) writer.println(0);
        else writer.println(1);
        writer.close();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/MainView.fxml"));
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setTitle("WeatherApp - Strona główna");
        rootPane.getChildren().setAll(pane);
    }


}
