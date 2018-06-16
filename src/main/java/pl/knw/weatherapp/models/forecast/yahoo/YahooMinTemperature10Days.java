package pl.knw.weatherapp.models.forecast.yahoo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pl.knw.weatherapp.models.forecast.MinTemperature10Days;
import pl.knw.weatherapp.models.settings.ProjectProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class YahooMinTemperature10Days implements MinTemperature10Days {

    public ArrayList<String> array = new ArrayList<>();
    public Map<String, String> map = new HashMap<>();

    public YahooMinTemperature10Days() {
        ProjectProperties properties = ProjectProperties.getInstance();
        Object forecastJson = properties.get("yahoojson");
        JSONObject jo = (JSONObject) forecastJson;
        jo = (JSONObject) jo.get("query");
        jo = (JSONObject) jo.get("results");
        jo = (JSONObject) jo.get("channel");
        jo = (JSONObject) jo.get("item");
        JSONArray ja = (JSONArray) jo.get("forecast");
        for (int i = 0; i < ja.size(); i++) {
            jo = (JSONObject) ja.get(i);
            String tmp = jo.get("low").toString();
            double ftoc = (Integer.parseInt(tmp) - 32) * 5.0/9.0;
            tmp = String.valueOf(ftoc);
            map.put(String.valueOf(i), tmp);
            array.add(tmp);
        }
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
