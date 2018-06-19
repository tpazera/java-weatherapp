package pl.knw.weatherapp.models.forecast;

public interface SiteFactory {
    Temperatures10Days getTemperatures();
    MinTemperature10Days getMinTemperatures();
    MaxTemperature10Days getMaxTemperatures();
    WeatherCode10Days getWeatherCodes();
}
