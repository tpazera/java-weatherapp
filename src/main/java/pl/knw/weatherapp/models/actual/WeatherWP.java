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
        System.out.println("[WP.pl] Getting html code...");
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
        String temperature;
        try {
            Element tag = doc.select("body > div.static > span:nth-child(4)").first();
            temperature = tag.text();
            temperature = temperature.substring(13,temperature.length()); //obcinam "Temperatura : "
        } catch (Exception e) {
            temperature = "-";
        }
        return temperature;
    }

    public String getCurrentWind() {
        String wind;
        try {
            Element tag = doc.select("body > div.static > span:nth-child(9)").first();
            wind = tag.text();
            wind = wind.substring(17,wind.length()); //obcinam "prędkosć wiatru: ""
        } catch (Exception e) {
            wind = "-";
        }
        return wind;
    }

    public String getCurrentPressure() {
        String pressure;
        try {
            Element tag = doc.select("body > div.static > span:nth-child(10)").first();
            pressure = tag.text();
            pressure = pressure.substring(11,pressure.length()); //obcinam "ciśnienie: "
        } catch (Exception e) {
            pressure = "-";
        }
        return pressure;
    }

    public String getCurrentCloudy() {
        String cloudy;
        try {
            Element tag = doc.select("body > div.static > span:nth-child(4)").first();
            cloudy = tag.text();
            cloudy = cloudy.substring(13,cloudy.length()); //obcinam "Temperatura : "
        } catch (Exception e) {
            cloudy = "-";
        }
        return cloudy;
    }

    public String getCurrentHumidity() {
        String humidity;
        try {
            Element tag = doc.select("").first();
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
        } catch (Exception e) {
            rain = "-";
        }
        return rain;
    }

    public String getCurrentImage() {
        String imageUrl = "default";
        System.out.println(imageUrl);
        return imageUrl;
    }
}
