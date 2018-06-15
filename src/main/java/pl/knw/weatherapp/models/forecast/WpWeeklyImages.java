package pl.knw.weatherapp.models.forecast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.knw.weatherapp.models.settings.ProjectProperties;

import java.io.IOException;
import java.util.Map;

public class WpWeeklyImages implements WeeklyImages {

    public String siteLink;

    public WpWeeklyImages() {
        ProjectProperties properties = ProjectProperties.getInstance();
        System.out.println("[WP.pl] Getting address from google search...");
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
    public String getFirstImage() {
        return null;
    }

    @Override
    public String getSecondImage() {
        return null;
    }

    @Override
    public String getThirdImage() {
        return null;
    }

    @Override
    public String getFourthImage() {
        return null;
    }

    @Override
    public String getFifthImage() {
        return null;
    }

    @Override
    public String getSixthImage() {
        return null;
    }

    @Override
    public String getSeventhImage() {
        return null;
    }

    @Override
    public Map<String, String> getAllImages() {
        return null;
    }
}
