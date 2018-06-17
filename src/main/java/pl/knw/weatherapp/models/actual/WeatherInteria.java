package pl.knw.weatherapp.models.actual;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.knw.weatherapp.models.settings.ProjectProperties;

import java.io.IOException;

public class WeatherInteria extends Sites {

    private static final String DEGREE = "\u00b0";
    public String weatherlink;
    public Document doc;

    public WeatherInteria() {
        ProjectProperties properties = ProjectProperties.getInstance();
        System.out.println("[Interia] Getting address from google search...");
        String url = "https://www.google.pl/search?q=interia+pogoda+" + properties.get("city");
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

    //Link: https://pogoda.interia.pl/prognoza-szczegolowa-krakow,cId,4970

    public String getName() {
        String name = "interia";
        return name;
    }

    public String getCurrentTemperature() {
        String temperature;
        try {
            Element tag = doc.select("#weather-currently > div.weather-currently-middle > div.weather-currently-middle-today-wrapper > div > div.weather-currently-temp > div").first();
            temperature = tag.text();
        } catch (Exception e) {
            temperature = "-";
        }
        return temperature;
    }

    public String getCurrentWind() {
        String wind;
        try {
            Element tag = doc.select("#weather-currently > div.weather-currently-middle > div.weather-currently-middle-today-wrapper > div > ul.weather-currently-details > li.weather-currently-details-item.wind > span").first();
            wind = tag.text();
        } catch (Exception e) {
            wind = "-";
        }
        return wind;
    }

    public String getCurrentPressure() {
        String pressure;
        try {
            Element tag = doc.select("#weather-currently > div.weather-currently-middle > div.weather-currently-middle-today-wrapper > div > ul.weather-currently-details > li.weather-currently-details-item.pressure > span").first();
            pressure = tag.text();
        } catch (Exception e) {
            pressure = "-";
        }
        return pressure;
    }

    public String getCurrentCloudy() {
        String cloudy;
        try {
            Element tag = doc.select("#content > div.main-content.col-xs-12.col-rs-12.col-sm-12.col-md-8.col-lg-8 > section.weather-forecast-hbh > div.weather-forecast-hbh-main-list > div.weather-forecast > div.weather-forecast-hbh-list.is-not-hidden > div > div.entry-precipitation > div > ul > li:nth-child(1) > span.entry-precipitation-value.cloud-cover").first();
            cloudy = tag.text();
        } catch (Exception e) {
            cloudy = "-";
        }
        return cloudy;
    }

    public String getCurrentHumidity() {
        String humidity;
        try {
            Element tag = doc.select("#content > div.main-content.col-xs-12.col-rs-12.col-sm-12.col-md-8.col-lg-8 > section.weather-forecast-hbh > div.weather-forecast-hbh-main-list > div.weather-forecast > div.weather-forecast-hbh-list.is-not-hidden > div > div.entry-humidity > div").first();
            humidity = tag.text();
        } catch (Exception e) {
            humidity = "-";
        }
        return humidity;
    }

    public String getCurrentRain() {
        String rain;
        try {
            Element tag = doc.select("#content > div.main-content.col-xs-12.col-rs-12.col-sm-12.col-md-8.col-lg-8 > section.weather-forecast-hbh > div.weather-forecast-hbh-main-list > div.weather-forecast > div.weather-forecast-hbh-list.is-not-hidden > div > div.entry-precipitation > div > ul > li:nth-child(2) > span.entry-precipitation-value.rain").first();
            rain = tag.text();
        } catch (Exception e) {
            rain = "-";
        }
        return rain;
    }

    public String getCurrentImage() {
        String imageUrl;
        try {
            Element tag = doc.select("#weather-currently > div.weather-currently-middle > div.weather-currently-middle-today-wrapper > div > div.weather-currently-icon").first();
            imageUrl = tag.attr("class");
            imageUrl = String.valueOf(Integer.parseInt(imageUrl.replaceAll("[\\D]", "")));
            imageUrl = "https://d.iplsc.com/weather/svg-icons/" + imageUrl + ".svg";
        } catch (Exception e) {
            imageUrl = "default";
        }
        return imageUrl;
    }
}
