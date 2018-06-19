package pl.knw.weatherapp.models.actual;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.knw.weatherapp.models.settings.ProjectProperties;

import java.io.IOException;

public class WeatherOnet extends Sites {

    private static final String DEGREE = "\u00b0";
    private String weatherlink;
    private Document doc;

    WeatherOnet() {
        ProjectProperties properties = ProjectProperties.getInstance();
        System.out.println("[Onet] Getting html code...");
        String url = "https://www.google.pl/search?q=onet+pogoda+" + properties.get("city");
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

    //Link: https://pogoda.onet.pl/prognoza-pogody/krakow-306020

    public String getName() {
        String name = "onet";
        return name;
    }

    public String getCurrentTemperature() {
        String temperature;
        try {
            Element tag = doc.select("#weatherMainWidget > div.widgetContent > div.underSearchBox > div.mainBox.widgetLeftCol > div.mainBoxContent > div.mainParams > div.temperature > div.temp").first();
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
            Element tag = doc.select("#weatherMainWidget > div.widgetContent > div.underSearchBox > div.mainBox.widgetLeftCol > div.mainBoxContent > div.weatherParams > ul > li:nth-child(2) > span.restParamValue").first();
            wind = tag.text();
        } catch (Exception e) {
            wind = "-";
        }
        return wind;
    }

    public String getCurrentPressure() {
        String pressure;
        try {
            Element tag = doc.select("#weatherMainWidget > div.widgetContent > div.underSearchBox > div.mainBox.widgetLeftCol > div.mainBoxContent > div.weatherParams > ul > li:nth-child(3) > span.restParamValue").first();
            pressure = tag.text();
        } catch (Exception e) {
            pressure = "-";
        }
        return pressure;
    }

    public String getCurrentCloudy() {
        String cloudy;
        try {
            Element tag = doc.select("#weatherMainWidget > div.widgetContent > div.underSearchBox > div.mainBox.widgetLeftCol > div.mainBoxContent > div.weatherParams > ul > li:nth-child(4) > span.restParamValue").first();
            cloudy = tag.text();
        } catch (Exception e) {
            cloudy = "-";
        }
        return cloudy;
    }

    public String getCurrentHumidity() {
        String humidity;
        try {
            Element tag = doc.select("#weatherMainWidget > div.widgetContent > div.underSearchBox > div.mainBox.widgetLeftCol > div.mainBoxContent > div.weatherParams > ul > li:nth-child(5) > span.restParamValue").first();
            humidity = tag.text();
        } catch (Exception e) {
            humidity = "-";
        }
        return humidity;
    }

    public String getCurrentRain() {
        String rain;
        try {
            Element tag = doc.select("#weatherMainWidget > div.widgetContent > div.underSearchBox > div.mainBox.widgetLeftCol > div.mainBoxContent > div.weatherParams > ul > li:nth-child(1) > span.restParamValue").first();
            rain = tag.text();
        } catch (Exception e) {
            rain = "-";
        }
        return rain;
    }

    public String getCurrentImage() {
        String imageUrl;
        try {
            Element tag = doc.select("#weatherMainWidget > div.widgetContent > div.underSearchBox > div.mainBox.widgetLeftCol > div.mainBoxContent > div.mainParams > div.forecast > span > img").first();
            imageUrl = tag.attr("src");
            imageUrl = "http:" + imageUrl;
        } catch (Exception e) {
            imageUrl = "default";
        }
        return imageUrl;
    }
}
