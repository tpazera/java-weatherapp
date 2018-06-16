package pl.knw.weatherapp.models.forecast.owm;

import pl.knw.weatherapp.models.forecast.*;

public class OWMFactory implements SiteFactory {
    @Override
    public Temperatures10Days getTemperatures() {
        return null;
    }

    @Override
    public MinTemperature10Days getMinTemperatures() {
        return null;
    }

    @Override
    public MaxTemperature10Days getMaxTemperatures() {
        return null;
    }

    @Override
    public WeatherCode10Days getWeatherCodes() {
        return null;
    }
}
