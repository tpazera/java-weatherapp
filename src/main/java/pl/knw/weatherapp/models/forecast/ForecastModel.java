package pl.knw.weatherapp.models.forecast;

public class ForecastModel {

    public ForecastModel() {
        Site site;
        site = new Site("wp", new WpFactory());
        WeeklyTemperature wp = site.days;
        System.out.println(wp.getFirstTemperature());
        Temperatures10Days wp2 = site.images;
        System.out.println(wp2.getFirstImage());
    }

}
