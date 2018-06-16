package pl.knw.weatherapp.models.forecast.owm;

import org.json.simple.JSONObject;
import pl.knw.weatherapp.models.forecast.MaxTemperature10Days;
import pl.knw.weatherapp.models.settings.ProjectProperties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OWMMaxTemperature10Days implements MaxTemperature10Days {


    public Map<String, String> map = new HashMap<>();
    public ArrayList<String> array = new ArrayList<>();

    public OWMMaxTemperature10Days() {
        /*String STRING_URL = "https://samples.openweathermap.org/data/2.5/forecast/daily?lat="+50+"&lon="+19+"&cnt=10&appid="+properties.get("api");
        System.out.println(STRING_URL);
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
        */
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
