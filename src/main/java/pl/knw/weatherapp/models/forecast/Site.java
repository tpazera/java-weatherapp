package pl.knw.weatherapp.models.forecast;

public class Site {
    public String siteName;
    public WeeklyTemperature days;
    public WeeklyImages images;

    public Site(String siteName, SiteFactory factory) {
        this.siteName = siteName;
        days = factory.downloadDays();
        images = factory.downloadImages();
        process();
    }

    private void process() {

    }
}
