package pl.knw.weatherapp.models.actual;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.knw.weatherapp.models.settings.ProjectProperties;

import java.io.IOException;

public class WeatherGismeteo extends Sites {

    private static final String DEGREE = "\u00b0";
    public String weatherlink;
    public Document doc;

    public WeatherGismeteo() {
        ProjectProperties properties = ProjectProperties.getInstance();
        System.out.println("[Gismeteo] Getting html code...");
        String url = "https://www.google.pl/search?q=gismeteo+pogoda+" + properties.get("city");
        try {
            doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .timeout(0)
                    .get();
            Elements links = doc.select(".srg .rc .r a");
            for (Element link : links) {
                weatherlink = link.attr("href") + "14-days/";
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

    //Link: https://www.gismeteo.pl/weather-krakow-3212/14-days/

    public String getName() {
        String name = "gismeteo";
        return name;
    }

    public String getCurrentTemperature() {
        String temperature;
        try {
            Element tag = doc.select("#weather > div.fcontent > div.section.higher > div.temp > dd.value.m_temp.c").first();
            temperature = tag.text();
        } catch (Exception e) {
            temperature = "-";
        }
        return temperature;
    }

    public String getCurrentWind() {
        String wind;
        try {
            Element tag = doc.select("#weather > div.fcontent > div.section.higher > div.temp > dd.value.m_temp.c").first();
            wind = tag.text();
        } catch (Exception e) {
            wind = "-";
        }
        return wind;
    }

    public String getCurrentPressure() {
        String pressure;
        try {
            Element tag = doc.select("#weather > div.fcontent > div.section.higher > div.temp > dd.value.m_temp.c").first();
            pressure = tag.text();
        } catch (Exception e) {
            pressure = "-";
        }
        return pressure;
    }

    public String getCurrentCloudy() {
        String cloudy;
        try {
            Element tag = doc.select("#weather > div.fcontent > div.section.higher > div.temp > dd.value.m_temp.c").first();
            cloudy = tag.text();
        } catch (Exception e) {
            cloudy = "-";
        }
        return cloudy;
    }

    public String getCurrentHumidity() {
        String humidity;
        try {
            Element tag = doc.select("#weather > div.fcontent > div.section.higher > div.temp > dd.value.m_temp.c").first();
            humidity = tag.text();
        } catch (Exception e) {
            humidity = "-";
        }
        return humidity;
    }

    public String getCurrentRain() {
        String rain;
        try {
            Element tag = doc.select("#weather > div.fcontent > div.section.higher > div.temp > dd.value.m_temp.c").first();
            rain = tag.text();
        } catch (Exception e) {
            rain = "-";
        }
        return rain;
    }

    public String getCurrentImage() {
        String imageUrl;
        try {
            Element tag = doc.select("#weather > div.fcontent > div.section.higher > dl > dt").first();
            imageUrl = tag.attr("style");
            imageUrl = "https:" + imageUrl.substring(imageUrl.indexOf("//"), imageUrl.indexOf(")"));
        } catch (Exception e) {
            imageUrl = "default";
        }
        return imageUrl;
    }
}
