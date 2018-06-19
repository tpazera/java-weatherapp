package pl.knw.weatherapp.models.forecast;

import pl.knw.weatherapp.models.forecast.owm.OWMFactory;
import pl.knw.weatherapp.models.forecast.yahoo.YahooFactory;

import java.util.ArrayList;

public class ForecastModel {

    private Site site;

    public ForecastModel(int s) {
        if (s == 0) site = new Site("openweathermap", new OWMFactory());
        else site = new Site("yahoo", new YahooFactory());

    }

    public ArrayList<String> getTemperatures() {
        Temperatures10Days average = site.average;
        return average.getArray();
    }

    public ArrayList<String> getMinTemperatures() {
        MinTemperature10Days min = site.min;
        return min.getArray();
    }

    public ArrayList<String> getMaxTemperatures() {
        MaxTemperature10Days max = site.max;
        return max.getArray();
    }

    public ArrayList<String> getWeatherCodes() {
        WeatherCode10Days code = site.code;
        return code.getArray();
    }


}
