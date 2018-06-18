package pl.knw.weatherapp.models.main;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

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
            locationServices = lookup.getLocation(getIp());
            if (locationServices == null) {
                System.out.println("GeoliteCity database null!");
            }
            assert locationServices != null;
            if(locationServices.city.equals("Polska")) {
                locationInfo.put("City", "Krakow");
                locationInfo.put("CountryCode", "PL");
                locationInfo.put("Latitude", "50");
                locationInfo.put("Longitude", "19");
            } else {
                locationInfo.put("City", locationServices.city);
                locationInfo.put("CountryCode", locationServices.countryCode);
                locationInfo.put("Latitude", String.valueOf(locationServices.latitude));
                locationInfo.put("Longitude", String.valueOf(locationServices.longitude));
            }
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

    public OWMWeatherDesc getDataFromOWM() {
        OWMWeatherDesc actualWeather = new OWMWeatherDesc();
        System.out.println("Aktualna pogoda: " + actualWeather);
        return actualWeather;
    }

    public YahooWeatherDesc getDataFromYahoo() {
        return new YahooWeatherDesc();
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
        return null;
    }
}
