package pl.knw.weatherapp.models.forecast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.knw.weatherapp.models.settings.ProjectProperties;

import java.io.IOException;
import java.util.Map;

public class OnetWeeklyTemperature implements WeeklyTemperature {

    public String siteLink;
    public Document doc;

    public OnetWeeklyTemperature() {
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
    public String getFirstTemperature() {
        String temperature;
        try {
            Element tag = doc.select("#weatherMainWidget > div.widgetContent > div.underSearchBox > div.timelineBox.widgetLeftCol.activeTab_1 > div > div.longTermWeather.tab_1.tabPanel.gtm_mainWidget_timelineLongTerm.showButtons.showScrollbar.showGradient > div.timelineListHolder > div.timelineListWrapper.swiper-container.swiper-container-horizontal > ul > li.item.swiper-slide.swiper-slide-active > div > div.temperature > div").first();
            temperature = tag.text();
        } catch (Exception e) {
            temperature = "-";
        }
        return temperature;
    }

    @Override
    public String getSecondTemperature() {
        String temperature;
        try {
            Element tag = doc.select("#weatherMainWidget > div.widgetContent > div.underSearchBox > div.timelineBox.widgetLeftCol.activeTab_1 > div > div.longTermWeather.tab_1.tabPanel.gtm_mainWidget_timelineLongTerm.showButtons.showScrollbar.showGradient > div.timelineListHolder > div.timelineListWrapper.swiper-container.swiper-container-horizontal > ul > li.item.swiper-slide.swiper-slide-active > div > div.temperature > div").first();
            temperature = tag.text();
        } catch (Exception e) {
            temperature = "-";
        }
        return temperature;
    }

    @Override
    public String getThirdTemperature() {
        String temperature;
        try {
            Element tag = doc.select("#weatherMainWidget > div.widgetContent > div.underSearchBox > div.timelineBox.widgetLeftCol.activeTab_1 > div > div.longTermWeather.tab_1.tabPanel.gtm_mainWidget_timelineLongTerm.showButtons.showScrollbar.showGradient > div.timelineListHolder > div.timelineListWrapper.swiper-container.swiper-container-horizontal > ul > li.item.swiper-slide.swiper-slide-active > div > div.temperature > div").first();
            temperature = tag.text();
        } catch (Exception e) {
            temperature = "-";
        }
        return temperature;
    }

    @Override
    public String getFourthTemperature() {
        String temperature;
        try {
            Element tag = doc.select("#weatherMainWidget > div.widgetContent > div.underSearchBox > div.timelineBox.widgetLeftCol.activeTab_1 > div > div.longTermWeather.tab_1.tabPanel.gtm_mainWidget_timelineLongTerm.showButtons.showScrollbar.showGradient > div.timelineListHolder > div.timelineListWrapper.swiper-container.swiper-container-horizontal > ul > li.item.swiper-slide.swiper-slide-active > div > div.temperature > div").first();
            temperature = tag.text();
        } catch (Exception e) {
            temperature = "-";
        }
        return temperature;
    }

    @Override
    public String getFifthTemperature() {
        String temperature;
        try {
            Element tag = doc.select("#weatherMainWidget > div.widgetContent > div.underSearchBox > div.timelineBox.widgetLeftCol.activeTab_1 > div > div.longTermWeather.tab_1.tabPanel.gtm_mainWidget_timelineLongTerm.showButtons.showScrollbar.showGradient > div.timelineListHolder > div.timelineListWrapper.swiper-container.swiper-container-horizontal > ul > li.item.swiper-slide.swiper-slide-active > div > div.temperature > div").first();
            temperature = tag.text();
        } catch (Exception e) {
            temperature = "-";
        }
        return temperature;
    }

    @Override
    public String getSixthTemperature() {
        String temperature;
        try {
            Element tag = doc.select("#weatherMainWidget > div.widgetContent > div.underSearchBox > div.timelineBox.widgetLeftCol.activeTab_1 > div > div.longTermWeather.tab_1.tabPanel.gtm_mainWidget_timelineLongTerm.showButtons.showScrollbar.showGradient > div.timelineListHolder > div.timelineListWrapper.swiper-container.swiper-container-horizontal > ul > li.item.swiper-slide.swiper-slide-active > div > div.temperature > div").first();
            temperature = tag.text();
        } catch (Exception e) {
            temperature = "-";
        }
        return temperature;
    }

    @Override
    public String getSeventhTemperature() {
        String temperature;
        try {
            Element tag = doc.select("#weatherMainWidget > div.widgetContent > div.underSearchBox > div.timelineBox.widgetLeftCol.activeTab_1 > div > div.longTermWeather.tab_1.tabPanel.gtm_mainWidget_timelineLongTerm.showButtons.showScrollbar.showGradient > div.timelineListHolder > div.timelineListWrapper.swiper-container.swiper-container-horizontal > ul > li.item.swiper-slide.swiper-slide-active > div > div.temperature > div").first();
            temperature = tag.text();
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
