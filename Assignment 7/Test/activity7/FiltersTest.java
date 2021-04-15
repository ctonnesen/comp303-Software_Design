package activity7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class FiltersTest {
    private String expensive = "expensiveFilter";
    private Predicate<AbstractMenuItemSource> expensiveFilter = menuItem -> menuItem.getPrice() > 10;

    @BeforeEach
    public void setup() {
        Filters.add(expensive, expensiveFilter);
    }

    @Test
    public void testAdd() {
        try {
            Field filterByNameMap = Filters.class.getDeclaredField("aFiltersByName");
            filterByNameMap.setAccessible(true);
            HashMap<String, Predicate<AbstractMenuItemSource>> copyHashMap = (HashMap<String, Predicate<AbstractMenuItemSource>>) filterByNameMap.get(Filters.class);
            assertEquals(expensiveFilter, copyHashMap.get(expensive));
            assertNull(Filters.add(expensive, expensiveFilter));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testGetByFilter() {
        assertEquals(expensive, Filters.get(expensiveFilter));
    }

    @Test
    public void testGetByString() {
        assertEquals(expensiveFilter, Filters.get(expensive));
    }

}