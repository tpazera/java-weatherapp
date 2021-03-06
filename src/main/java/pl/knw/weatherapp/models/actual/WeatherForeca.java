package pl.knw.weatherapp.models.actual;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.knw.weatherapp.models.settings.ProjectProperties;

import java.beans.ExceptionListener;
import java.io.IOException;

public class WeatherForeca extends Sites {

    private static final String DEGREE = "\u00b0";
    private String weatherlink;
    private Document doc;

    WeatherForeca() {
        ProjectProperties properties = ProjectProperties.getInstance();
        System.out.println("[Foreca] Getting html code...");
        String url = "https://www.google.pl/search?q=foreca.pl+pogoda+" + properties.get("city");
        try {
            doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .timeout(0)
                    .get();
            Elements links = doc.select(".srg .rc .r a");
            for (Element link : links) {
                weatherlink = link.attr("href");
                break;
            }
            doc = Jsoup.connect(weatherlink)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .timeout(0)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Link: https://www.foreca.pl/Poland/Lesser-Poland-Voivodeship/Krak%C3%B3w

    public String getName() {
        String name = "foreca";
        return name;
    }

    public String getCurrentTemperature() {
        String temperature;
        try {
            Element tag = doc.select("#left > div.cf > div.column.split > div.cf > div > div.obs.cf > div.values > div > div:nth-child(2)").first();
            temperature = tag.text();
            temperature = temperature + "C";
        } catch (Exception e) {
            temperature = "-";
        }
        return temperature;
    }

    public String getCurrentWind() {
        String wind;
        try {
            Element tag = doc.select("#left > div.cf > div.column.split > div.cf > div > div.obs.cf > div.symb > div.wind > strong").first();
            wind = tag.text();
        } catch (Exception e) {
            wind = "-";
        }
        return wind;
    }

    public String getCurrentPressure() {
        String pressure;
        try {
            Element tag = doc.select("#left > div.cf > div.column.split > div.cf > div > div.obs.cf > div.values > div > div:nth-child(4)").first();
            pressure = tag.text();
        } catch (Exception e) {
            pressure = "-";
        }
        return pressure;
    }

    public String getCurrentCloudy() {
        String cloudy;
        try {
            Element tag = doc.select("").first();
            cloudy = tag.text();
            cloudy = cloudy + "km/h";
        } catch (Exception e) {
            cloudy = "-";
        }
        return cloudy;
    }

    public String getCurrentHumidity() {
        String humidity;
        try {
            Element tag = doc.select("#left > div.cf > div.column.split > div.cf > div > div.obs.cf > div.values > div > div:nth-child(4)").first();
            humidity = tag.text();
        } catch (Exception e) {
            humidity = "-";
        }
        return humidity;
    }

    public String getCurrentRain() {
        String rain;
        try {
            Element tag = doc.select("").first();
            rain = tag.text();
            rain = rain + "mm";
        } catch (Exception e) {
            rain = "-";
        }
        return rain;
    }

    public String getCurrentImage() {
        String imageUrl;
        try {
            Element tag = doc.select("#left > div.cf > div.column.split > div.cf > div > div.obs.cf > div.symb > img").first();
            imageUrl = tag.attr("src");
            imageUrl = "http:" + imageUrl;
        } catch (Exception e) {
            imageUrl = "default";
        }
        return imageUrl;
    }
}
