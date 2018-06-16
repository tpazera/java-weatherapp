package pl.knw.weatherapp.models.forecast;

public interface SiteFactory {
    public Temperatures10Days getTemperatures();
    public MinTemperature10Days getMinTemperatures();
    public MaxTemperature10Days getMaxTemperatures();
    public WeatherCode10Days getWeatherCodes();
}
