package pl.knw.weatherapp.models;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import net.aksingh.owmjapis.DailyForecast;
import net.aksingh.owmjapis.OpenWeatherMap;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainModel {

    private static final String STRING_URL = "http://checkip.amazonaws.com";

    public Map<String, String> getCurrentLocation() {
        Location locationServices = null;
        Map<String, String> locationInfo = new HashMap<String, String>();
        try {
            LookupService lookup = null;
            String classesRootDir = String.valueOf(getClass().getProtectionDomain().getCodeSource().getLocation());
            String[] parts = classesRootDir.split("file:");
            lookup = new LookupService(parts[1]+"pl/knw/weatherapp/geolite/GeoLiteCity.dat", LookupService.GEOIP_MEMORY_CACHE | LookupService.GEOIP_CHECK_CACHE);
            locationServices = lookup.getLocation(this.getIp());
            if (locationServices == null) {
                System.out.println("GeoliteCity database null!");
            }
            locationInfo.put("City", locationServices.city);
            locationInfo.put("CountryCode", locationServices.countryCode);
            locationInfo.put("Latitude", String.valueOf(locationServices.latitude));
            locationInfo.put("Longitude", String.valueOf(locationServices.longitude));

            return locationInfo;

        } catch (IOException e) {
            e.printStackTrace();
        }

        locationInfo.put("City", "Krakow");
        locationInfo.put("CountryCode", "PL");
        locationInfo.put("Latitude", "50");
        locationInfo.put("Longitude", "19");

        return locationInfo;
    }

    public Map<String, String> getCurrentWeather() {
        boolean isMetric = true;
        String owmApiKey = "305be23140a9d5d08890247143be3227";
        Map<String, String> locationParams = this.getCurrentLocation();
        String weatherCity = locationParams.get("City")+","+locationParams.get("CountryCode");
        byte forecastDays = 3;
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
                        " to " + temperature.getMaximumTemperature() + "\n");
            }
        }
        catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return locationParams;
    }

    public static String getIp() {
        BufferedReader in = null;
        URL whatismyip = null;
        String ip = null;
        try {
            whatismyip = new URL(STRING_URL);

            in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            ip = in.readLine();

            return ip;
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
        return ip;
    }
}
