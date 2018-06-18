package pl.knw.weatherapp.models.forecast.example;

import org.json.simple.JSONObject;
import pl.knw.weatherapp.models.forecast.WeatherCode10Days;
import pl.knw.weatherapp.models.settings.ProjectProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExampleWeatherCode10Days implements WeatherCode10Days {
    public ArrayList<String> array = new ArrayList<>();
    public Map<String, String> map = new HashMap<>();

    public ExampleWeatherCode10Days() {
        //get json
        //get code temperatures from json
    }


    @Override
    public Map<String, String> getMap() {
        return map;
    }

    @Override
    public ArrayList<String> getArray() {
        return array;
    }
}
