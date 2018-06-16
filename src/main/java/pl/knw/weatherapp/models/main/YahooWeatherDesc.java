package pl.knw.weatherapp.models.main;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pl.knw.weatherapp.models.settings.ProjectProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class YahooWeatherDesc {
    public YahooWeatherDesc() {
        ProjectProperties properties = ProjectProperties.getInstance();
        String STRING_URL = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+properties.get("city")+"%2C%20pl%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
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
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = null;
        try {
            jsonObj = (JSONObject) parser.parse(json);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        properties.put("yahoojson", jsonObj);
    }
}
