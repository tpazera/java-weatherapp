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
    public Document doc;

    public WeatherPogodaNet() {
        ProjectProperties properties = ProjectProperties.getInstance();
        System.out.println("[PogodaNet] Getting html code...");
        String url = "https://www.google.pl/search?q=Pogoda+-+prognoza+pogody+prosto+i+wygodnie+pogoda.net+" + properties.get("city");
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

    //Link: http://pogoda.net/Krakow?gclid=CjwKCAjwgYPZBRBoEiwA2XeupcpA7-sjigGAGBOsrnzG6QE2zDnYCv9Zml0ng0hs6HIwPA0atMVwZBoC_pIQAvD_BwE

    public String getName() {
        String name = "pogodanet";
        return name;
    }

    public String getCurrentTemperature() {
        String temperature;
        try {
            Element tag = doc.select("body > div.container > div.row.row-place > div.col-lg-4.col-lg-offset-0.col-md-4.col-md-offset-0.col-sm-5.col-sm-offset-1.col-xs-10.col-xs-offset-1 > div > em").first();
            temperature = tag.text();
            temperature = temperature.substring(0, temperature.length()-1) + DEGREE + "C";
        } catch (Exception e) {
            temperature = "-";
        }
        return temperature;
    }

    public String getCurrentWind() {
        String wind;
        try {
            Element tag = doc.select("body > div.container > div.row.row-place > div.col-lg-4.col-lg-offset-0.col-md-4.col-md-offset-0.col-sm-5.col-sm-offset-1.col-xs-10.col-xs-offset-1 > p:nth-child(8) > strong").first();
            wind = tag.text();
        } catch (Exception e) {
            wind = "-";
        }
        return wind;
    }

    public String getCurrentPressure() {
        String pressure;
        try {
            Element tag = doc.select("body > div.container > div.row.row-place > div.col-lg-4.col-lg-offset-0.col-md-4.col-md-offset-0.col-sm-5.col-sm-offset-1.col-xs-10.col-xs-offset-1 > p:nth-child(5) > strong").first();
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
        } catch (Exception e) {
            cloudy = "-";
        }
        return cloudy;
    }

    public String getCurrentHumidity() {
        String humidity;
        try {
            Element tag = doc.select("body > div.container > div.row.row-place > div.col-lg-4.col-lg-offset-0.col-md-4.col-md-offset-0.col-sm-5.col-sm-offset-1.col-xs-10.col-xs-offset-1 > p:nth-child(7) > strong").first();
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

        String imageUrl;
        try {
            Element tag = doc.select("body > div.container > div.row.row-place > div.col-lg-4.col-lg-offset-0.col-md-4.col-md-offset-0.col-sm-5.col-sm-offset-0.col-xs-10.col-xs-offset-1 > div > img").first();
            imageUrl = tag.attr("src");
            imageUrl = "http://pogoda.net/" + imageUrl;
        } catch (Exception e) {
            imageUrl = "default";
        }
        return imageUrl;
    }
}
