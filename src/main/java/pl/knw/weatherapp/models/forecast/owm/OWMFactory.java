package pl.knw.weatherapp.models.forecast.owm;

import pl.knw.weatherapp.models.forecast.*;

public class OWMFactory implements SiteFactory {
    @Override
    public Temperatures10Days getTemperatures() {
        return new OWMTemperatures10Days();
    }

    @Override
    public MinTemperature10Days getMinTemperatures() {
        return new OWMMinTemperature10Days();
    }

    @Override
    public MaxTemperature10Days getMaxTemperatures() {
        return new OWMMaxTemperature10Days();
    }

    @Override
    public WeatherCode10Days getWeatherCodes() {
        return new OWMWeatherCode10Days();
    }
}
