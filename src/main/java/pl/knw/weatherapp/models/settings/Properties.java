package pl.knw.weatherapp.models.settings;

interface Properties<N, V> {

  V get(N name);

  V put(N name, V value);

  boolean has(N name);

  V remove(N name);
}
