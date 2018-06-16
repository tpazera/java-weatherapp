package pl.knw.weatherapp.models.actual;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.knw.weatherapp.models.settings.ProjectProperties;

import java.io.IOException;

public class WeatherWP extends Sites {

    private static final String DEGREE = "\u00b0";
    public String weatherlink;
    public Document doc;

    public WeatherWP() {
        ProjectProperties properties = ProjectProperties.getInstance();
        System.out.println("[WP.pl] Getting address from google search...");
        String url = "https://www.google.pl/search?q=wp+pogoda+aktualna+" + properties.get("city");
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

    //Link: https://pogoda.wp.pl/cid,43289,name,Krakow,index.html#pogoda,20180614,cid,43289
    //Tutaj trochę inaczej: nie badaj elementów bo coś się sypie
    //Znajdź w kodzie
    //<!-- PROGNOZA {-->
    //<div class='static" ...
    //stąd pobieraj dane i obcinaj tekst, żeby zwracało same wartości, tak jak ja w getCurrentTemperature

    public String getName() {
        String name = "wp";
        return name;
    }

    public String getCurrentTemperature() {
        Element tag = doc.select("body > div.static > span:nth-child(4)").first();
        String temperature = tag.text();
        temperature = temperature.substring(13,temperature.length()-1); //obcinam "Temperature: "
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
        System.out.println(imageUrl);
        return imageUrl;
    }
}
