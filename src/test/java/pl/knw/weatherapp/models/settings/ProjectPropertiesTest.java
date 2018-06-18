package pl.knw.weatherapp.models.settings;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ProjectPropertiesTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    public ProjectPropertiesTest() {
    }

    @Test
    public void get() {
        String name = "empty";

        Properties properties = new ProjectProperties();
        assertNull(properties.get(name));
    }

    @Test
    public void put() {
        String name = "name";
        String value = "value";

        Properties properties = new ProjectProperties();
        assertNull(properties.put(name, value));
    }

    @Test
    public void has() {
        String name = "empty";

        Properties properties = new ProjectProperties();
        assertFalse(properties.has(name));
    }

}