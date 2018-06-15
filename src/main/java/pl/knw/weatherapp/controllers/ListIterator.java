package pl.knw.weatherapp.controllers;

import java.util.List;

public class ListIterator< E > implements Iterator {
    int posistion = 0;
    List< E > items;

    public ListIterator(List< E > items){
        this.items = items;
    }

    @Override
    public boolean hasNext(){
        if (posistion >= items.size() || items.get(posistion) == null){
            return false;
        }
        return true;
    }

    @Override
    public E next(){
        E item = items.get(posistion);
        posistion++;
        return item;
    }
}
