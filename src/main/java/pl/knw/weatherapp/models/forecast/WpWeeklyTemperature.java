package pl.knw.weatherapp.models.forecast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.knw.weatherapp.models.settings.ProjectProperties;

import java.io.IOException;
import java.util.Map;

public class WpWeeklyTemperature implements WeeklyTemperature {

    public String siteLink;

    public WpWeeklyTemperature() {
        ProjectProperties properties = ProjectProperties.getInstance();
        System.out.println("[WP.pl] Temperature: Getting address from google search...");
        Document doc;
        String url = "https://www.google.pl/search?q=wp+pogoda+aktualna+" + properties.get("city");
        try {
            doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .timeout(0)
                    .get();
            Elements links = doc.select(".srg .rc .r a");
            for (Element link : links) {
                siteLink = link.attr("href");
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getFirstTemperature() {
        String temperature = null;
        Document doc = null;
        try {
            doc = Jsoup.connect(siteLink)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .timeout(0)
                    .get();
            Element tag = doc.select("body > div.static > span:nth-child(5)").first();
            temperature = tag.text();
            temperature = temperature.substring(24,temperature.length()-1); //obcinam "Temperatura odczuwalna: "
        } catch (IOException e) {
            temperature = "-";
        } catch (Exception e) {
            temperature = "-";
        }
        return temperature;
    }

    @Override
    public String getSecondTemperature() {
        String temperature = null;
        Document doc = null;
        try {
            doc = Jsoup.connect(siteLink)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .timeout(0)
                    .get();
            Element tag = doc.select("body > div.static > span:nth-child(13)").first();
            temperature = tag.text();
            temperature = temperature.substring(24,temperature.length()-1); //obcinam "Temperatura odczuwalna: "
        } catch (IOException e) {
            temperature = "-";
        } catch (Exception e) {
            temperature = "-";
        }
        return temperature;
    }

    @Override
    public String getThirdTemperature() {
        String temperature = null;
        Document doc = null;
        try {
            doc = Jsoup.connect(siteLink)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .timeout(0)
                    .get();
            Element tag = doc.select("body > div.static > span:nth-child(21)").first();
            temperature = tag.text();
            temperature = temperature.substring(24,temperature.length()-1); //obcinam "Temperatura odczuwalna: "
        } catch (IOException e) {
            temperature = "-";
        } catch (Exception e) {
            temperature = "-";
        }
        return temperature;
    }

    @Override
    public String getFourthTemperature() {
        String temperature = null;
        Document doc = null;
        try {
            doc = Jsoup.connect(siteLink)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .timeout(0)
                    .get();
            Element tag = doc.select("body > div.static > span:nth-child(29)").first();
            temperature = tag.text();
            temperature = temperature.substring(24,temperature.length()-1); //obcinam "Temperatura odczuwalna: "
        } catch (IOException e) {
            temperature = "-";
        } catch (Exception e) {
            temperature = "-";
        }
        return temperature;
    }

    @Override
    public String getFifthTemperature() {
        String temperature = null;
        Document doc = null;
        try {
            doc = Jsoup.connect(siteLink)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .timeout(0)
                    .get();
            Element tag = doc.select("#Pogoda > div.nW > ul > li:nth-child(5) > div.dT").first();
            temperature = tag.text();
        } catch (IOException e) {
            temperature = "-";
        } catch (Exception e) {
            temperature = "-";
        }
        return temperature;
    }

    @Override
    public String getSixthTemperature() {
        String temperature = null;
        Document doc = null;
        try {
            doc = Jsoup.connect(siteLink)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .timeout(0)
                    .get();
            Element tag = doc.select("#Pogoda > div.nW > ul > li:nth-child(6) > div.dT").first();
            temperature = tag.text();
        } catch (IOException e) {
            temperature = "-";
        } catch (Exception e) {
            temperature = "-";
        }
        return temperature;
    }

    @Override
    public String getSeventhTemperature() {
        String temperature = null;
        Document doc = null;
        try {
            doc = Jsoup.connect(siteLink)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .timeout(0)
                    .get();
            Element tag = doc.select("#Pogoda > div.nW > ul > li:nth-child(7) > div.dT").first();
            temperature = tag.text();
        } catch (IOException e) {
            temperature = "-";
        } catch (Exception e) {
            temperature = "-";
        }
        return temperature;
    }

    @Override
    public Map<String, String> getAllTemperatures() {
        Map<String, String> temperaturemap = null;
        temperaturemap.put("1", getFirstTemperature());
        temperaturemap.put("2", getSecondTemperature());
        temperaturemap.put("3", getThirdTemperature());
        temperaturemap.put("4", getFourthTemperature());
        temperaturemap.put("5", getFifthTemperature());
        temperaturemap.put("6", getSixthTemperature());
        temperaturemap.put("7", getSeventhTemperature());
        return temperaturemap;
    }
}
