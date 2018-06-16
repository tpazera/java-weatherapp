package pl.knw.weatherapp.models.forecast;

public class OnetFactory implements SiteFactory{
    @Override
    public WeeklyTemperature downloadDays() {
        return new OnetWeeklyTemperature();
    }

    @Override
    public WeeklyImages downloadImages() {
        return new OnetWeeklyImages();
    }
}
