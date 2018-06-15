package pl.knw.weatherapp.models.forecast;

public class WpFactory implements SiteFactory {

    @Override
    public WeeklyTemperature downloadDays() {
        return new WpWeeklyTemperature();
    }

    @Override
    public WeeklyImages downloadImages() {
        return new WpWeeklyImages();
    }
}
