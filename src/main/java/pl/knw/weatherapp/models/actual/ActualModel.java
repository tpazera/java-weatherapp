package pl.knw.weatherapp.models.actual;

public class ActualModel {
    public ActualModel() {
        WeatherOnet onet = new WeatherOnet();
        onet.getCurrentTemperature();
        onet.getCurrentImage();
        WeatherWP wp = new WeatherWP();
        wp.getCurrentTemperature();
        wp.getCurrentImage();
        WeatherInteria interia = new WeatherInteria();
        interia.getCurrentTemperature();
        interia.getCurrentImage();
        WeatherPogodaNet pogodanet = new WeatherPogodaNet();
        pogodanet.getCurrentTemperature();
        pogodanet.getCurrentImage();
        WeatherForeca foreca = new WeatherForeca();
        foreca.getCurrentTemperature();
        foreca.getCurrentImage();
        WeatherGismeteo gismeteo = new WeatherGismeteo();
        gismeteo.getCurrentTemperature();
        gismeteo.getCurrentImage();

    }
}
