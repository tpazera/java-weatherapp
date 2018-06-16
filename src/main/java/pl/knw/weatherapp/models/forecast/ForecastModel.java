package pl.knw.weatherapp.models.forecast;

public class ForecastModel {

    public ForecastModel() {
        Site site;
        site = new Site("wp", new WpFactory());
        WeeklyTemperature wp = site.days;
        System.out.println(wp.getFifthTemperature());
    }

}
