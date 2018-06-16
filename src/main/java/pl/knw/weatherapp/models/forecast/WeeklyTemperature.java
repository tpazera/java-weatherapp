package pl.knw.weatherapp.models.forecast;

import java.util.Map;

public interface WeeklyTemperature {
    String getFirstTemperature();
    String getSecondTemperature();
    String getThirdTemperature();
    String getFourthTemperature();
    String getFifthTemperature();
    String getSixthTemperature();
    String getSeventhTemperature();
    Map<String, String> getAllTemperatures();
    
}
