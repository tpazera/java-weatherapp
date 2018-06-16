package pl.knw.weatherapp.models.forecast;

public class Site {
    public String siteName;
    public Temperatures10Days average;
    public MinTemperature10Days min;
    public MaxTemperature10Days max;
    public WeatherCode10Days code;


    public Site(String siteName, SiteFactory factory) {
        this.siteName = siteName;
        average = factory.getTemperatures();
        min = factory.getMinTemperatures();
        max = factory.getMaxTemperatures();
        code = factory.getWeatherCodes();
        process();
    }

    private void process() {

    }
}
