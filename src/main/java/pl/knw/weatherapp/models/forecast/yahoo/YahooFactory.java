package pl.knw.weatherapp.models.forecast.yahoo;

import pl.knw.weatherapp.models.forecast.*;
import pl.knw.weatherapp.models.forecast.owm.OWMMaxTemperature10Days;
import pl.knw.weatherapp.models.forecast.owm.OWMMinTemperature10Days;
import pl.knw.weatherapp.models.forecast.owm.OWMTemperatures10Days;
import pl.knw.weatherapp.models.forecast.owm.OWMWeatherCode10Days;

public class YahooFactory implements SiteFactory {
    @Override
    public Temperatures10Days getTemperatures() {
        return new YahooTemperatures10Days();
    }

    @Override
    public MinTemperature10Days getMinTemperatures() {
        return new YahooMinTemperature10Days();
    }

    @Override
    public MaxTemperature10Days getMaxTemperatures() {
        return new YahooMaxTemperature10Days();
    }

    @Override
    public WeatherCode10Days getWeatherCodes() {
        return new YahooWeatherCode10Days();
    }
}
