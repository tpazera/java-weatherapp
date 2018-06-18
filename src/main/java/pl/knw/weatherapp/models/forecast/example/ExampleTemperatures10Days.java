package pl.knw.weatherapp.models.forecast.example;

import org.json.simple.JSONObject;
import pl.knw.weatherapp.models.forecast.Temperatures10Days;
import pl.knw.weatherapp.models.settings.ProjectProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExampleTemperatures10Days implements Temperatures10Days {
    public ArrayList<String> array = new ArrayList<>();
    public Map<String, String> map = new HashMap<>();

    public ExampleTemperatures10Days() {
        //get json
        //get average temperatures from json
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
