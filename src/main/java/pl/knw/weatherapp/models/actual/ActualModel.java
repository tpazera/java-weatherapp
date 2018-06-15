package pl.knw.weatherapp.models.actual;

import pl.knw.weatherapp.models.settings.ProjectProperties;

import java.util.ArrayList;

public class ActualModel {

    private static ArrayList< Sites > sites = new ArrayList<>();

    public ArrayList< Sites > getWeatherModels() {
        ProjectProperties properties = ProjectProperties.getInstance();
        Integer[] sitesSettings = (Integer[]) properties.get("sites");
        for(int i = 0; i < sitesSettings.length; i++) {
            if(sitesSettings[i] == 1) {
                switch(i) {
                    case 0:
                        sites.add(new WeatherWP());
                        break;
                    case 1:
                        sites.add(new WeatherOnet());
                        break;
                    case 2:
                        sites.add(new WeatherInteria());
                        break;
                    case 3:
                        sites.add(new WeatherPogodaNet());
                        break;
                    case 4:
                        sites.add(new WeatherForeca());
                        break;
                    case 5:
                        sites.add(new WeatherGismeteo());
                        break;
                    default:
                        System.out.println("Wszystkie strony zablokowane!");
                }
            }
        }

        return sites;
    }
}
