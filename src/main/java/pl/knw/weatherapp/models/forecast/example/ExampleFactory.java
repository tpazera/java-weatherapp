package pl.knw.weatherapp.models.forecast.example;

import pl.knw.weatherapp.models.forecast.*;
import pl.knw.weatherapp.models.forecast.owm.OWMMaxTemperature10Days;
import pl.knw.weatherapp.models.forecast.owm.OWMMinTemperature10Days;
import pl.knw.weatherapp.models.forecast.owm.OWMTemperatures10Days;
import pl.knw.weatherapp.models.forecast.owm.OWMWeatherCode10Days;

public class ExampleFactory implements SiteFactory {
    @Override
    public Temperatures10Days getTemperatures() {
        return new ExampleTemperatures10Days();
    }

    @Override
    public MinTemperature10Days getMinTemperatures() {
        return new ExampleMinTemperature10Days();
    }

    @Override
    public MaxTemperature10Days getMaxTemperatures() {
        return new ExampleMaxTemperature10Days();
    }

    @Override
    public WeatherCode10Days getWeatherCodes() {
        return new ExampleWeatherCode10Days();
    }
}
