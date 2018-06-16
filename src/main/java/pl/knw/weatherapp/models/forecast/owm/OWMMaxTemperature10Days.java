package pl.knw.weatherapp.models.forecast.owm;


import pl.knw.weatherapp.models.forecast.MaxTemperature10Days;
import pl.knw.weatherapp.models.settings.ProjectProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class OWMMaxTemperature10Days implements MaxTemperature10Days {

    public OWMMaxTemperature10Days() {
        ProjectProperties properties = ProjectProperties.getInstance();
        String STRING_URL = "https://samples.openweathermap.org/data/2.5/forecast/daily?lat="+properties.get("Latitude")+"&lon="+properties.get("Longitude")+"&cnt=10&appid="+properties.get("api");
        BufferedReader in = null;
        String json = null;
        try {
            URL jsonURL = new URL(STRING_URL);

            in = new BufferedReader(new InputStreamReader(
                    jsonURL.openStream()));
            json = in.readLine();

        } catch (Exception ex) {
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(json);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(json);
        JSONArray array = (JSONArray)obj;

        Object obj = new JSONParser().parse(json);
    }

    @Override
    public Map<String, String> getMap() {
        return null;
    }

    @Override
    public ArrayList<String> getArray() {
        return null;
    }
}
