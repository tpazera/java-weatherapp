package pl.knw.weatherapp.models.settings;

import java.util.*;

public final class ProjectProperties<V, T, A> implements Properties<String, V> {

  public static final String PROTOTYPE = "prototype";
  private final Map<String, V> properties;
  private volatile static ProjectProperties instance;

  public ProjectProperties() {
    this.properties = Collections.synchronizedMap(new HashMap());
  }

    public static ProjectProperties getInstance() {
        if (instance == null) {
            synchronized (ProjectProperties.class) {
                if (instance == null) {
                    instance = new ProjectProperties();
                }
            }
        }

        return instance;
    }

  private ProjectProperties(final Map<String, V> map) {
    this.properties = Collections.synchronizedMap(new HashMap<String, V>(map));
  }

  @Override
  public V get(final String name) {
    if (properties.containsKey(name)) {
      return properties.get(name);
    }

    if (properties.containsKey(PROTOTYPE)) {
      Properties<String, V> prototype =
              (Properties<String, V>) properties.get(PROTOTYPE);
      return prototype.get(name);
    }

    return null;
  }

  @Override
  public V put(final String name, final V value) {
    if (PROTOTYPE.equals(name) && !(value instanceof Properties)) {
      throw new IllegalArgumentException("Prototype must be an instance of Properties!");
    }

    return properties.put(name, value);
  }

  @Override
  public boolean has(final String name) {
    if (properties.containsKey(name)) {
      return null != properties.get(name);
    }

    if (properties.containsKey(PROTOTYPE)) {
      Properties<String, V> prototype = (Properties<String, V>) properties.get(PROTOTYPE);
      return prototype.has(name);
    }

    return false;
  }

  @Override
  public V remove(final String name) {
    if (properties.containsKey(PROTOTYPE)) {
      Properties<String, V> prototype =
              (Properties<String, V>) properties.get(PROTOTYPE);

      if (prototype.has(name)) {
        properties.put(name, null);
        return prototype.get(name);
      }
    }

    if (properties.containsKey(name)) {
      return properties.remove(name);
    }

    return null;
  }

  @Override
  public ProjectProperties<V, T, A> clone() {

    return new ProjectProperties<V, T, A>(properties);
  }

}
