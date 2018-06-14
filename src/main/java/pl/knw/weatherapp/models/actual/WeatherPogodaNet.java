package pl.knw.weatherapp.models.actual;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.knw.weatherapp.models.settings.ProjectProperties;

import java.io.IOException;

public class WeatherPogodaNet extends Sites {

    private static final String DEGREE = "\u00b0";
    public String weatherlink;

    public WeatherPogodaNet() {
        ProjectProperties properties = ProjectProperties.getInstance();
        System.out.println("[PogodaNet] Getting address from google search...");
        Document doc;
        String url = "https://www.google.pl/search?q=Pogoda+-+prognoza+pogody+prosto+i+wygodnie+pogoda.net+" + properties.get("city");
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

    //Link: http://pogoda.net/Krakow?gclid=CjwKCAjwgYPZBRBoEiwA2XeupcpA7-sjigGAGBOsrnzG6QE2zDnYCv9Zml0ng0hs6HIwPA0atMVwZBoC_pIQAvD_BwE

    public String getCurrentTemperature() {
        String temperature = null;
        Document doc = null;
        try {
            doc = Jsoup.connect(weatherlink)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .get();
            Element tag = doc.select("body > div.container > div.row.row-place > div.col-lg-4.col-lg-offset-0.col-md-4.col-md-offset-0.col-sm-5.col-sm-offset-1.col-xs-10.col-xs-offset-1 > p:nth-child(6) > strong").first();
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
            Element tag = doc.select("body > div.container > div.row.row-place > div.col-lg-4.col-lg-offset-0.col-md-4.col-md-offset-0.col-sm-5.col-sm-offset-0.col-xs-10.col-xs-offset-1 > div > img").first();
            imageUrl = tag.attr("src");
            imageUrl = "http://pogoda.net/" + imageUrl;
            System.out.println(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageUrl;
    }
}
