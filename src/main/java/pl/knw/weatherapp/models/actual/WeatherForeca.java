package pl.knw.weatherapp.models.actual;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.knw.weatherapp.models.settings.ProjectProperties;

import java.io.IOException;

public class WeatherForeca extends Sites {

    private static final String DEGREE = "\u00b0";
    public String weatherlink;

    public WeatherForeca() {
        ProjectProperties properties = ProjectProperties.getInstance();
        System.out.println("[Foreca] Getting address from google search...");
        Document doc;
        String url = "https://www.google.pl/search?q=foreca.pl+pogoda+" + properties.get("city");
        try {
            doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .get();
            Elements links = doc.select(".srg .rc .r a");
            for (Element link : links) {
                weatherlink = link.attr("href");
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Link: https://www.foreca.pl/Poland/Lesser-Poland-Voivodeship/Krak%C3%B3w

    public String getName() {
        String name = "Foreca";
        return name;
    }

    public String getCurrentTemperature() {
        String temperature = null;
        Document doc = null;
        try {
            doc = Jsoup.connect(weatherlink)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .get();
            Element tag = doc.select("#left > div.cf > div.column.split > div.cf > div > div.obs.cf > div.values > div > div:nth-child(2)").first();
            temperature = tag.text();
            System.out.println(temperature);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        String imageUrl = "<DEFAULT IMAGE>";
        Document doc = null;
        try {
            doc = Jsoup.connect(weatherlink)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .get();
            Element tag = doc.select("#left > div.cf > div.column.split > div.cf > div > div.obs.cf > div.symb > img").first();
            imageUrl = tag.attr("src");
            imageUrl = "http:" + imageUrl;
            System.out.println(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageUrl;
    }
}
