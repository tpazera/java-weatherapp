package pl.knw.weatherapp.models.forecast.owm;


import net.aksingh.owmjapis.DailyForecast;
import org.json.JSONException;
import pl.knw.weatherapp.models.forecast.MaxTemperature10Days;
import pl.knw.weatherapp.models.settings.ProjectProperties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class OWMMaxTemperature10Days implements MaxTemperature10Days {

    public ArrayList<String> array;
    public Map<String, String> map;

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
        Object obj = null;
        try {
            obj = new JSONParser().parse(json);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jo = (JSONObject) obj;
        System.out.println(jo.get("name"));*/
        /*boolean isMetric = true;
        String owmApiKey = (String) properties.get("api");
        String weatherCity = properties.get("city")+","+properties.get("countrycode");
        byte forecastDays = 10;
        OpenWeatherMap.Units units = (isMetric)
                ? OpenWeatherMap.Units.METRIC
                : OpenWeatherMap.Units.IMPERIAL;
        OpenWeatherMap owm = new OpenWeatherMap(units, owmApiKey);
        try {
            DailyForecast forecast = owm.dailyForecastByCityName(weatherCity, forecastDays);
            System.out.println("Weather for: " + forecast.getCityInstance().getCityName());
            int numForecasts = forecast.getForecastCount();
            for (int i = 0; i < numForecasts; i++) {
                DailyForecast.Forecast dayForecast = forecast.getForecastInstance(i);
                DailyForecast.Forecast.Temperature temperature = dayForecast.getTemperatureInstance();
                System.out.println("\t" + dayForecast.getDateTime());
                System.out.println("\tTemperature: " + temperature.getMinimumTemperature() +
                        " to " + temperature.getMaximumTemperature() + ", average: " + temperature.getDayTemperature() + "\n");
            }
        }
        catch (IOException | JSONException e) {
            e.printStackTrace();
        }*/
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
