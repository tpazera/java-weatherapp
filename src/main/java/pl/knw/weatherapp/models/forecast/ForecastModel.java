package pl.knw.weatherapp.models.forecast;

import pl.knw.weatherapp.models.forecast.owm.OWMFactory;
import pl.knw.weatherapp.models.forecast.yahoo.YahooFactory;

import java.util.ArrayList;

public class ForecastModel {

    Site site;

    public ForecastModel(int s) {
        if (s == 0) site = new Site("openweathermap", new OWMFactory());
        else site = new Site("yahoo", new YahooFactory());

    }

    public ArrayList<String> getTemperatures() {
        Temperatures10Days average = site.average;
        return average.getArray();
    }

    public ArrayList<String> getMinTemperatures() {
        MinTemperature10Days average = site.min;
        return average.getArray();
    }

    public ArrayList<String> getMaxTemperatures() {
        MaxTemperature10Days average = site.max;
        return average.getArray();
    }

    public ArrayList<String> getWeatherCodes() {
        WeatherCode10Days average = site.code;
        return average.getArray();
    }


}
