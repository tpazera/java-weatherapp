package pl.knw.weatherapp.models.main;

import net.aksingh.owmjapis.DailyForecast;
import net.aksingh.owmjapis.OpenWeatherMap;

import java.io.IOException;

public class ActualWeatherDescription {

    private static final String DEGREE = "\u00b0";

    private String DateInformations;
    private String MaxTemperature;
    private String MinTemperature;
    private String Datetemperature;
    private String Humidity;
    private String Pressure;
    private String Clouds;
    private String Wind_Speed;

    public ActualWeatherDescription() {
        ProjectProperties properties = ProjectProperties.getInstance();
        String weatherCity = properties.get("city")+","+properties.get("countrycode");
        boolean isMetric = true;
        OpenWeatherMap.Units units = (isMetric)
                ? OpenWeatherMap.Units.METRIC
                : OpenWeatherMap.Units.IMPERIAL;
        OpenWeatherMap owm = new OpenWeatherMap(units, (String) properties.get("api"));
        DailyForecast forecast;
        try {
            forecast = owm.dailyForecastByCityName(weatherCity, (byte) 1);  //1 - tylko dzisiaj
            DailyForecast.Forecast dayForecast = forecast.getForecastInstance(0);
            DailyForecast.Forecast.Temperature temperature = dayForecast.getTemperatureInstance();
            DailyForecast.Forecast.Weather weather = dayForecast.getWeatherInstance(0);
            this.DateInformations = String.valueOf(dayForecast.getDateTime());
            this.Datetemperature = temperature.getDayTemperature() + DEGREE + "C";
            this.MaxTemperature = temperature.getMinimumTemperature() + DEGREE + "C";
            this.MinTemperature = temperature.getMaximumTemperature() + DEGREE + "C";
            this.Humidity = String.valueOf(dayForecast.getHumidity()) + "%";
            this.Pressure = String.valueOf(dayForecast.getPressure()) + " hPa ";
            this.Clouds = String.valueOf(dayForecast.getPercentageOfClouds()) + "%";
            this.Wind_Speed = String.valueOf(dayForecast.getWindSpeed()) + "m/s";
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //gettery i settery
    public void setDateInformations(String DateInformations) {
        this.DateInformations = DateInformations;
    }

    public void setClouds(String Clouds) {
        this.Clouds = Clouds;
    }

    public void setPressure(String Pressure) {
        this.Pressure = Pressure;
    }

    public void setHumidity(String Humidity) {
        this.Humidity = Humidity;
    }

    public void setMinTemperature(String MinTemperature) {
        this.MinTemperature = MinTemperature;
    }

    public void setMaxTemperature(String MaxTemperature) {
        this.MaxTemperature = MaxTemperature;
    }

    public void setDateTemperature(String DateTemperature) {
        this.Datetemperature = DateTemperature;
    }

    public void setWind_Speed(String Wind_Speed) {
        this.Wind_Speed = Wind_Speed;
    }

    public String getWind_Speed() {
        return Wind_Speed;
    }

    public String getDateTemperature() {
        return Datetemperature;
    }

    public String getPressure() {
        return Pressure;
    }

    public String getHumidity() {
        return Humidity;
    }

    public String getClouds() {
        return Clouds;
    }

    public String getDateInformations() {
        return DateInformations;
    }

    public String getMaxTemperature() {
        return MaxTemperature;
    }

    public String getMinTemperature() {
        return MinTemperature;
    }

    @Override
    public String toString() {
        return DateInformations + ", "
                + Datetemperature + ", "
                + MaxTemperature + ", "
                + MinTemperature + ", "
                + Humidity + ", "
                + Pressure + ", "
                + Clouds + ", "
                + Wind_Speed + ", ";
    }
}
