package pl.knw.weatherapp.controllers;

public interface Iterator< E >{
    boolean hasNext();
    E next();
    int size();
}
