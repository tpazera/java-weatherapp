package pl.knw.weatherapp.models.actual;

public class Sites {

    private static final String DEGREE = "\u00b0";

    public Sites() {
        System.out.println("Enclosing new site...");
    }

    public String getCurrentTemperature() {
        String temperature = "17" + DEGREE + "C";
        return temperature;
    }

    public String getCurrentWind() {
        String wind = "6 km / h";
        return wind;
    }

    public String getCurrentPressure() {
        String pressure = "1018 hPa";
        return pressure;
    }

    public String getCurrentCloudy() {
        String cloudy = "0%";
        return cloudy;
    }

    public String getCurrentHumidity() {
        String humidity = "74%";
        return humidity;
    }

    public String getCurrentRain() {
        String rain = "0,0 mm";
        return rain;
    }

    public String getCurrentImage() {
        String imageUrl = "url";
        return imageUrl;
    }

}
