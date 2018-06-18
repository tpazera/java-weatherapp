package pl.knw.weatherapp.models.settings;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;
import static pl.knw.weatherapp.models.settings.ProjectProperties.PROTOTYPE;

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

    @Test
    public void remove() {
        String name = "name";

        Properties properties = new ProjectProperties();
        assertNull(properties.remove(name));
    }

    @Test
    public void testPrototypePut() {
        Properties prototype = new ProjectProperties();
        Properties properties = new ProjectProperties();
        assertNull(properties.put(PROTOTYPE, prototype));
        assertTrue(properties.has(PROTOTYPE));
    }

    @Test
    public void testPrototypeHas() {
        String name1 = "name1";
        String name2 = "name2";
        String value = "value";

        Properties prototype = new ProjectProperties();
        assertNull(prototype.put(name1, value));

        Properties properties = new ProjectProperties();
        assertNull(properties.put(PROTOTYPE, prototype));
        assertTrue(properties.has(name1));
        assertFalse(properties.has(name2));
    }

    @Test
    public void testPrototypeGet() {
        String name1 = "name1";
        String name2 = "name2";
        String value = "value";

        Properties prototype = new ProjectProperties();
        assertNull(prototype.put(name1, value));

        ProjectProperties properties = new ProjectProperties();
        assertNull(properties.put(PROTOTYPE, prototype));
        assertEquals(value, properties.get(name1));
        assertNull(properties.get(name2));
    }

}