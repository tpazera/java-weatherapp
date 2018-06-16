package pl.knw.weatherapp.models.forecast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.knw.weatherapp.models.settings.ProjectProperties;

import java.io.IOException;
import java.util.Map;

public class OnetWeeklyImages implements WeeklyImages {

    public String siteLink;

    public OnetWeeklyImages() {
        ProjectProperties properties = ProjectProperties.getInstance();
        System.out.println("[Onet] Getting address from google search...");
        Document doc;
        String url = "https://www.google.pl/search?q=onet+pogoda+dlugoterminowa" + properties.get("city");
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
        return "default";
    }

    @Override
    public String getSecondImage() {
        return "default";
    }

    @Override
    public String getThirdImage() {
        return "default";
    }

    @Override
    public String getFourthImage() {
        return "default";
    }

    @Override
    public String getFifthImage() {
        return "default";
    }

    @Override
    public String getSixthImage() {
        return "default";
    }

    @Override
    public String getSeventhImage() {
        return "default";
    }

    @Override
    public Map<String, String> getAllImages() {
        Map<String, String> imagesmap = null;
        imagesmap.put("1", getFirstImage());
        imagesmap.put("2", getSecondImage());
        imagesmap.put("3", getThirdImage());
        imagesmap.put("4", getFourthImage());
        imagesmap.put("5", getFifthImage());
        imagesmap.put("6", getSixthImage());
        imagesmap.put("7", getSeventhImage());
        return imagesmap;
    }

    public String getImageName(String className) {

        return null;
    }
}
