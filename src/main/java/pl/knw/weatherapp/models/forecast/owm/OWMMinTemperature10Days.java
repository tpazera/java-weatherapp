package pl.knw.weatherapp.models.forecast.owm;

import net.aksingh.owmjapis.DailyForecast;
import net.aksingh.owmjapis.OpenWeatherMap;
import org.json.JSONException;
import pl.knw.weatherapp.models.forecast.MinTemperature10Days;
import pl.knw.weatherapp.models.settings.ProjectProperties;

import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.Map;

public class OWMMinTemperature10Days implements MinTemperature10Days {

    public ArrayList<String> array;
    public Map<String, String> map;

    public OWMMinTemperature10Days() {
        ProjectProperties properties = ProjectProperties.getInstance();
        String forecastJson = (String) properties.get("forecastjson");
        System.out.println(forecastJson);
    }

    private String getShortDay(String dateTime) {
        dateTime = dateTime.substring(0,3);
        switch(dateTime) {
            case "Mon":
                return "PON";
            case "Tue":
                return "WTO";
            case "Wed":
                return "ŚRO";
            case "Thu":
                return "CZW";
            case "Fri":
                return "PT";
            case "Sat":
                return "SOB";
            default:
                return "NIE";
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
