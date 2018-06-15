package pl.knw.weatherapp.models.forecast;

public interface SiteFactory {
    public WeeklyTemperature downloadDays();
    public WeeklyImages downloadImages();
}
