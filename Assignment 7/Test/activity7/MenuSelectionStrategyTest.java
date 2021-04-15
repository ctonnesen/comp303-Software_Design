package activity7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MenuSelectionStrategyTest {
    private static MenuItem poutine = MenuItem.get("Poutine", 3.00, FoodType.MAIN);
    private static MenuItem soup = MenuItem.get("Soup", 6.00, FoodType.MAIN, DietCategory.GLUTENFREE, DietCategory.VEGAN);
    private static MenuItem spaghetti = MenuItem.get("Spaghetti", 5.78, FoodType.MAIN, DietCategory.VEGETARIAN);
    private static MenuItem chips = MenuItem.get("Chips", 1, FoodType.SIDE, DietCategory.VEGAN);
    private static MenuItem peanuts = MenuItem.get("Peanuts", 1, FoodType.SNACK, DietCategory.GLUTENFREE, DietCategory.ORGANIC, DietCategory.VEGAN, DietCategory.VEGETARIAN);
    private static MenuItem chocolate = MenuItem.get("Chocolate Bar", 1.75, FoodType.SNACK, DietCategory.GLUTENFREE, DietCategory.ORGANIC, DietCategory.VEGAN, DietCategory.VEGETARIAN);
    private static MenuItem veggieBurger = MenuItem.get("Veggie Burger", 7, FoodType.MAIN);
    private static MenuItem fries = MenuItem.get("Fries", 1.75, FoodType.SIDE);
    private static MenuItem tea = MenuItem.get("Tea", 1.75, FoodType.DRINK);
    private static MenuItem beer = MenuItem.get("Beer", 2.50, FoodType.DRINK);
    private static ComboMenuItem beerAndBurger;
    private static ArrayList<AbstractMenuItemSource> allItems = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        allItems.clear();
        ArrayList<AbstractMenuItemSource> burgerBeer = new ArrayList<>();
        burgerBeer.add(veggieBurger);
        burgerBeer.add(beer);
        beerAndBurger = ComboMenuItem.get(false, burgerBeer);
        allItems.add(poutine);
        allItems.add(soup);
        allItems.add(spaghetti);
        allItems.add(chips);
        allItems.add(peanuts);
        allItems.add(chocolate);
        allItems.add(veggieBurger);
        allItems.add(fries);
        allItems.add(tea);
        allItems.add(beer);
        allItems.add(beerAndBurger);
    }

    @Test
    public void testAllItems() {
        List copyList = MenuSelectionStrategy.allItems(allItems);
        assertEquals(allItems, copyList);
    }

    @Test
    public void testFilterByFoodTypeFirst() {
        try {
            Method filterMethod = MenuSelectionStrategy.class.getDeclaredMethod("filterByFoodType", List.class, FoodType.class);
            filterMethod.setAccessible(true);
            List copyList = (List) filterMethod.invoke(null, allItems, FoodType.COMBO);
            assertEquals(1,copyList.size());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testFilterByFoodTypeSecond() {
        try {
            Method filterMethod = MenuSelectionStrategy.class.getDeclaredMethod("filterByFoodType", List.class, FoodType.class, FoodType.class);
            filterMethod.setAccessible(true);
            List copyList = (List) filterMethod.invoke(null, allItems, FoodType.COMBO, FoodType.SIDE);
            assertEquals(3,copyList.size());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testFilterByDietCategory() {
        try {
            Method filterMethod = MenuSelectionStrategy.class.getDeclaredMethod("filterByDietCategory", List.class, DietCategory.class);
            filterMethod.setAccessible(true);
            List copyList = (List) filterMethod.invoke(null, allItems, DietCategory.ORGANIC);
            assertEquals(2,copyList.size());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testCombo() {
        List copyList = MenuSelectionStrategy.combo(allItems);
        assertEquals(1, copyList.size());
    }
}
