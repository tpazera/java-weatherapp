package pl.knw.weatherapp.models.forecast.yahoo;

import org.json.simple.JSONObject;
import pl.knw.weatherapp.models.forecast.Temperatures10Days;
import pl.knw.weatherapp.models.settings.ProjectProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class YahooTemperatures10Days implements Temperatures10Days {
    public ArrayList<String> array = new ArrayList<>();
    public Map<String, String> map = new HashMap<>();

    public YahooTemperatures10Days() {
        ProjectProperties properties = ProjectProperties.getInstance();
        Object forecastJson = properties.get("forecastjson");
        JSONObject jo = (JSONObject) forecastJson;
        for(int i = 0; i < 10; i++) {
            Map m = (Map) jo.get(i);
            String tmp = m.get("average").toString();
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
