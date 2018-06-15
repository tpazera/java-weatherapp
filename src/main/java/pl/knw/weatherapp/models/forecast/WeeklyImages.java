package pl.knw.weatherapp.models.forecast;

import java.util.Map;

public interface WeeklyImages {
    String getFirstImage();
    String getSecondImage();
    String getThirdImage();
    String getFourthImage();
    String getFifthImage();
    String getSixthImage();
    String getSeventhImage();
    Map<String, String> getAllImages();

}
