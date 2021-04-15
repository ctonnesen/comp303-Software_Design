package activity7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SizedMenuItemTest
{
	private static ArrayList<SizedMenuItem> sizedMenuItems;
	private static MenuItem soup=MenuItem.get("Soup",6.00,FoodType.MAIN);
	private static MenuItem spaghetti=MenuItem.get("Spaghetti",5.78,FoodType.MAIN);
	private static MenuItem chips=MenuItem.get("Chips",1,FoodType.SNACK);
	private static MenuItem Peanuts=MenuItem.get("Peanuts",1,FoodType.SNACK);

	private SizedMenuItem testMediumSizedMenuitem;
    private SizedMenuItem testSmallSizedMenuitem;
    private SizedMenuItem testLargeSizedMenuitem;
    private MenuItem testMain;
    private MenuItem testSide;
    private MenuItem testDrink;
    private MenuItem testSnack;
    private ArrayList<DietCategory> sizedItemCategories = new ArrayList<>();

	
	@BeforeEach
	public void setup() {
		try {
			Field field=SizedMenuItem.class.getDeclaredField("sizedMenuItems");
			field.setAccessible(true);
			sizedMenuItems=(ArrayList<SizedMenuItem>)field.get(null);
			sizedMenuItems.clear();

			sizedItemCategories.add(DietCategory.VEGAN);
        	testMain = MenuItem.get("testMain", 5.50, FoodType.MAIN, sizedItemCategories);
        	testSide = MenuItem.get("testSide", 3.50, FoodType.SIDE);
        	testDrink = MenuItem.get("testDrink", 1.50, FoodType.DRINK);
     		testSnack = MenuItem.get("testSnack", 2.00, FoodType.SNACK);
        	testMediumSizedMenuitem = SizedMenuItem.get(testMain, Size.MEDIUM);
        	testSmallSizedMenuitem = SizedMenuItem.get(testSnack, Size.SMALL);
        	testLargeSizedMenuitem = SizedMenuItem.get(testSide, Size.LARGE);
		}
		catch(ReflectiveOperationException exception) {
			fail();
		}

	}

	@Test
	public void test_get_singleItem() {
		SizedMenuItem sizedSoup=SizedMenuItem.get(soup,Size.LARGE);
		assertTrue(sizedMenuItems.contains(sizedSoup));
	}
	
	@Test
	public void test_get_singleItemWithDiffSizes() {
		SizedMenuItem largeSoup=SizedMenuItem.get(soup,Size.LARGE);
		SizedMenuItem smallSoup=SizedMenuItem.get(soup,Size.SMALL);
		assertTrue(sizedMenuItems.contains(largeSoup));
		assertTrue(sizedMenuItems.contains(smallSoup));
	}
	
	@Test
	public void test_get_singleItemTwice() {
		SizedMenuItem sizedSoup=SizedMenuItem.get(soup,Size.LARGE);
		SizedMenuItem sizedSoupCopy=SizedMenuItem.get(soup,Size.LARGE);
		assertTrue(sizedMenuItems.contains(sizedSoup));
		assertTrue(sizedMenuItems.contains(sizedSoupCopy));
		assertEquals(4, sizedMenuItems.size());
	}
	

	@Test
	public void test_getSizePrice() {
		SizedMenuItem largeSoup=SizedMenuItem.get(soup,Size.LARGE);
		try {
			Method method=SizedMenuItem.class.getDeclaredMethod("getSizePrice");
			method.setAccessible(true);
			assertEquals(method.invoke(largeSoup),largeSoup.getPrice());
			assertFalse((Double)method.invoke(largeSoup)==6.00);
		}
		catch(ReflectiveOperationException exception) {
			fail();
		}
		
		
	}
	
	@Test
	public void test_changeSpecial() {
		SizedMenuItem largeSoup=SizedMenuItem.get(soup,Size.LARGE);
		assertFalse(largeSoup.onSpecial);
		largeSoup.changeSpecial();
		assertTrue(largeSoup.onSpecial);
		assertEquals(largeSoup.getPrice(),largeSoup.NORMALPRICE*largeSoup.DISCOUNT);
	}


	@Test
	public void test_equals_SameItemSameSize() {
		SizedMenuItem sizedSoup=SizedMenuItem.get(soup,Size.LARGE);
		SizedMenuItem sizedSoupCopy=SizedMenuItem.get(soup,Size.LARGE);
		assertEquals(sizedSoup,sizedSoupCopy);
	}

	@Test
	public void test_equals_SameItemDifferentSize() {
		SizedMenuItem sizedSoup=SizedMenuItem.get(soup,Size.LARGE);
		SizedMenuItem smallSoup=SizedMenuItem.get(soup,Size.SMALL);
		assertNotEquals(sizedSoup,smallSoup);
	}

	@Test
	public void test_equals_DifferentItemDifferentSize() {
		SizedMenuItem sizedSoup=SizedMenuItem.get(soup,Size.LARGE);
		SizedMenuItem smallchips=SizedMenuItem.get(chips,Size.SMALL);
		assertNotEquals(sizedSoup,smallchips);
	}



	@Test
	public void test_equals_DifferentItemSameSize() {
		SizedMenuItem smallSoup=SizedMenuItem.get(soup,Size.SMALL);
		SizedMenuItem smallchips=SizedMenuItem.get(chips,Size.SMALL);
		assertNotEquals(smallSoup,smallchips);
	}

	@Test
    public void test_getSizePrice_Small(){
        try {
            Method mthd = SizedMenuItem.class.getDeclaredMethod("getSizePrice", null);
            mthd.setAccessible(true);
            double val = (double) mthd.invoke(testSmallSizedMenuitem, null);
			assertEquals(testSnack.getPrice()*testSmallSizedMenuitem.getSmallFactor(), val);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_getSizePrice_Medium(){
        try {
            Method mthd = SizedMenuItem.class.getDeclaredMethod("getSizePrice", null);
            mthd.setAccessible(true);
            double val = (double) mthd.invoke(testMediumSizedMenuitem, null);
            assertEquals(testMain.getPrice(), val);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


	@Test
    public void test_getSizePrice_Large(){
        try {
            Method mthd = SizedMenuItem.class.getDeclaredMethod("getSizePrice", null);
            mthd.setAccessible(true);
            double val = (double) mthd.invoke(testLargeSizedMenuitem, null);
            assertEquals(testSide.getPrice()*testLargeSizedMenuitem.getLargeFactor(), val);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

	@Test
	public void test_removeItem(){
		testSmallSizedMenuitem.removeItem(testSmallSizedMenuitem);
		assertFalse(sizedMenuItems.contains(testSmallSizedMenuitem));
	}




	@Test
	public void test_getFoodType(){
		assertEquals(FoodType.MAIN, testMediumSizedMenuitem.getFoodType());
	}

}
