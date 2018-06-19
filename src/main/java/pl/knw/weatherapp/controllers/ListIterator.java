package pl.knw.weatherapp.controllers;

import java.util.List;

public class ListIterator< E > implements Iterator {
    private int posistion = 0;
    private List< E > items;

    ListIterator(List<E> items){
        this.items = items;
    }

    @Override
    public boolean hasNext(){
        if ((posistion >= items.size())) {
            return false;
        } else if ((items.get(posistion) == null)) {
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

    public int size() {
        int s = items.size();
        return s;
    }

}
