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
    public Document doc;

    public OnetWeeklyImages() {
        ProjectProperties properties = ProjectProperties.getInstance();
        System.out.println("[Onet] Getting address from google search...");
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
            doc = Jsoup.connect(siteLink)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .timeout(0)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getFirstImage() {
        String imageUrl;
        try {
            Element tag = doc.select("#weatherMainWidget > div.widgetContent > div.underSearchBox > div.timelineBox.widgetLeftCol.activeTab_1 > div > div.longTermWeather.tab_1.tabPanel.gtm_mainWidget_timelineLongTerm.showButtons.showScrollbar.showGradient > div.timelineListHolder > div.timelineListWrapper.swiper-container.swiper-container-horizontal > ul > li.item.swiper-slide.swiper-slide-active > div > div.forecast > span > img").first();
            imageUrl = tag.attr("src");
            imageUrl = "http:" + imageUrl;
        } catch (Exception e) {
            imageUrl = "default";
        }
        return imageUrl;
    }

    @Override
    public String getSecondImage() {
        String imageUrl;
        try {
            Element tag = doc.select("#weatherMainWidget > div.widgetContent > div.underSearchBox > div.timelineBox.widgetLeftCol.activeTab_1 > div > div.longTermWeather.tab_1.tabPanel.gtm_mainWidget_timelineLongTerm.showButtons.showScrollbar.showGradient > div.timelineListHolder > div.timelineListWrapper.swiper-container.swiper-container-horizontal > ul > li.item.swiper-slide.swiper-slide-next > div > div.forecast > span > img").first();
            imageUrl = tag.attr("src");
            imageUrl = "http:" + imageUrl;
        } catch (Exception e) {
            imageUrl = "default";
        }
        return imageUrl;
    }

    @Override
    public String getThirdImage() {
        String imageUrl;
        try {
            Element tag = doc.select("#weatherMainWidget > div.widgetContent > div.underSearchBox > div.timelineBox.widgetLeftCol.activeTab_1 > div > div.longTermWeather.tab_1.tabPanel.gtm_mainWidget_timelineLongTerm.showButtons.showScrollbar.showGradient > div.timelineListHolder > div.timelineListWrapper.swiper-container.swiper-container-horizontal > ul > li:nth-child(3) > div > div.forecast > span > img").first();
            imageUrl = tag.attr("src");
            imageUrl = "http:" + imageUrl;
        } catch (Exception e) {
            imageUrl = "default";
        }
        return imageUrl;
    }

    @Override
    public String getFourthImage() {
        String imageUrl;
        try {
            Element tag = doc.select("#weatherMainWidget > div.widgetContent > div.underSearchBox > div.timelineBox.widgetLeftCol.activeTab_1 > div > div.longTermWeather.tab_1.tabPanel.gtm_mainWidget_timelineLongTerm.showButtons.showScrollbar.showGradient > div.timelineListHolder > div.timelineListWrapper.swiper-container.swiper-container-horizontal > ul > li:nth-child(4) > div > div.forecast > span > img").first();
            imageUrl = tag.attr("src");
            imageUrl = "http:" + imageUrl;
        } catch (Exception e) {
            imageUrl = "default";
        }
        return imageUrl;
    }

    @Override
    public String getFifthImage() {
        String imageUrl;
        try {
            Element tag = doc.select("#weatherMainWidget > div.widgetContent > div.underSearchBox > div.timelineBox.widgetLeftCol.activeTab_1 > div > div.longTermWeather.tab_1.tabPanel.gtm_mainWidget_timelineLongTerm.showButtons.showScrollbar.showGradient > div.timelineListHolder > div.timelineListWrapper.swiper-container.swiper-container-horizontal > ul > li:nth-child(5) > div > div.forecast > span > img").first();
            imageUrl = tag.attr("src");
            imageUrl = "http:" + imageUrl;
        } catch (Exception e) {
            imageUrl = "default";
        }
        return imageUrl;
    }

    @Override
    public String getSixthImage() {
        String imageUrl;
        try {
            Element tag = doc.select("#weatherMainWidget > div.widgetContent > div.underSearchBox > div.timelineBox.widgetLeftCol.activeTab_1 > div > div.longTermWeather.tab_1.tabPanel.gtm_mainWidget_timelineLongTerm.showButtons.showScrollbar.showGradient > div.timelineListHolder > div.timelineListWrapper.swiper-container.swiper-container-horizontal > ul > li:nth-child(6) > div > div.forecast > span > img").first();
            imageUrl = tag.attr("src");
            imageUrl = "http:" + imageUrl;
        } catch (Exception e) {
            imageUrl = "default";
        }
        return imageUrl;
    }

    @Override
    public String getSeventhImage() {
        String imageUrl;
        try {
            Element tag = doc.select("#weatherMainWidget > div.widgetContent > div.underSearchBox > div.timelineBox.widgetLeftCol.activeTab_1 > div > div.longTermWeather.tab_1.tabPanel.gtm_mainWidget_timelineLongTerm.showButtons.showScrollbar.showGradient > div.timelineListHolder > div.timelineListWrapper.swiper-container.swiper-container-horizontal > ul > li:nth-child(7) > div > div.forecast > span > img").first();
            imageUrl = tag.attr("src");
            imageUrl = "http:" + imageUrl;
        } catch (Exception e) {
            imageUrl = "default";
        }
        return imageUrl;
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
