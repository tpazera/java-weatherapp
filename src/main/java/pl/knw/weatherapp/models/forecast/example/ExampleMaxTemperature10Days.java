package pl.knw.weatherapp.models.forecast.example;

import org.json.simple.JSONObject;
import pl.knw.weatherapp.models.forecast.MaxTemperature10Days;
import pl.knw.weatherapp.models.settings.ProjectProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExampleMaxTemperature10Days implements MaxTemperature10Days {


    public Map<String, String> map = new HashMap<>();
    public ArrayList<String> array = new ArrayList<>();

    public ExampleMaxTemperature10Days() {
        //get json
        //get max temperatures from json
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
