package pl.knw.weatherapp.models.forecast;

public class ForecastModel {

    public ForecastModel() {
        Site site;
        site = new Site("wp", new OnetFactory());
        WeeklyTemperature wp = site.days;
        System.out.println(wp.getFirstTemperature());
        WeeklyImages wp2 = site.images;
        System.out.println(wp2.getFirstImage());
    }

}
