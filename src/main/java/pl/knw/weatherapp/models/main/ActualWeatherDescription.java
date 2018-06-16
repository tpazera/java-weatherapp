package pl.knw.weatherapp.models.main;

import net.aksingh.owmjapis.DailyForecast;
import net.aksingh.owmjapis.OpenWeatherMap;
import org.json.simple.JSONObject;
import pl.knw.weatherapp.models.settings.ProjectProperties;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

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
    private String Description;
    private int WeatherCode;
    private String IconName;

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
            forecast = owm.dailyForecastByCityName(weatherCity, (byte) 11);
            DailyForecast.Forecast dayForecast = forecast.getForecastInstance(0);
            DailyForecast.Forecast.Temperature temperature = dayForecast.getTemperatureInstance();
            DailyForecast.Forecast.Weather weather = dayForecast.getWeatherInstance(0);
            properties.put("forecast", forecast);
            this.DateInformations = String.valueOf(dayForecast.getDateTime());
            this.Datetemperature = temperature.getDayTemperature() + DEGREE + "C";
            this.MaxTemperature = temperature.getMinimumTemperature() + DEGREE + "C";
            this.MinTemperature = temperature.getMaximumTemperature() + DEGREE + "C";
            this.Humidity = String.valueOf(dayForecast.getHumidity()) + "%";
            this.Pressure = String.valueOf(dayForecast.getPressure()) + " hPa ";
            this.Clouds = String.valueOf(dayForecast.getPercentageOfClouds()) + "%";
            this.Wind_Speed = String.valueOf(dayForecast.getWindSpeed()) + "m/s";
            this.Description = weather.getWeatherDescription();
            this.WeatherCode = weather.getWeatherCode();
            this.IconName = weather.getWeatherIconName();
            JSONObject forecastJson = new JSONObject();
            for (int i = 0; i < 10; i++) {
                Map m = new LinkedHashMap(4);
                dayForecast = forecast.getForecastInstance(i);
                temperature = dayForecast.getTemperatureInstance();
                weather = dayForecast.getWeatherInstance(0);
                m.put("average", temperature.getDayTemperature());
                m.put("min", temperature.getMinimumTemperature());
                m.put("max", temperature.getMaximumTemperature());
                m.put("code", weather.getWeatherCode());
                forecastJson.put(i, m);
            }
            properties.put("forecastjson", forecastJson);
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

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setWeatherCode(int WeatherCode) {
        this.WeatherCode = WeatherCode;
    }

    public void setIconName(String IconName) {
        this.IconName = IconName;
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

    public String getDescription() {
        return Description;
    }

    public String getIconName() {
        return IconName;
    }

    public int getWeatherCode() {
        return WeatherCode;
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
                + Wind_Speed + ", "
                + Description + ", "
                + IconName + ", "
                + WeatherCode + ", ";
    }
}
