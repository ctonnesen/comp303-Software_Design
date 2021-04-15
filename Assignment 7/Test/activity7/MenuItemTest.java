package activity7;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MenuItemTest
{
	ArrayList<MenuItem> allitems;
	String aName = "TesterItemName";
	double NORMALPRICE = 5.50;
	FoodType aFoodType = FoodType.SNACK;
	ArrayList<DietCategory> aDietCategory = new ArrayList<>();

	@BeforeEach
	public void setup() {
		try {
			Field field=MenuItem.class.getDeclaredField("menuItems");
			field.setAccessible(true);
			allitems=(ArrayList<MenuItem>)field.get(null);
			allitems.clear();
		}
		catch(ReflectiveOperationException exception) {
			fail();
		}

	}

	@Test
	public void testFirstGet() {
		aDietCategory.add(DietCategory.GLUTENFREE);
		aDietCategory.add(DietCategory.VEGAN);
		MenuItem newItem = MenuItem.get("NewItemName1", NORMALPRICE, aFoodType, aDietCategory);
		assertEquals(aDietCategory, newItem.getDietCategories());
		MenuItem newItem2 = MenuItem.get("NewItemName1", NORMALPRICE, aFoodType, aDietCategory);
		assertEquals(newItem, newItem2);
		newItem.removeItem(newItem);
	}

	@Test
	public void testSecondGet() {
		MenuItem newSecondGetItem = MenuItem.get("NewItemName1", NORMALPRICE, aFoodType);
		assertEquals(FoodType.SNACK, newSecondGetItem.getFoodType());
		newSecondGetItem.removeItem(newSecondGetItem);
	}

	@Test
	public void testThirdGet() {
		MenuItem newThirdGetItem = MenuItem.get("NewItemName3", NORMALPRICE, aFoodType, DietCategory.GLUTENFREE, DietCategory.ORGANIC);
		aDietCategory.add(DietCategory.GLUTENFREE);
		aDietCategory.add(DietCategory.ORGANIC);
		assertEquals(aDietCategory, newThirdGetItem.getDietCategories());
	}

	@Test
	public void testDescription() {
		MenuItem newItem = MenuItem.get("NewItemName3", NORMALPRICE, aFoodType, DietCategory.GLUTENFREE, DietCategory.ORGANIC);
		newItem.changeSpecial();
		String fullDescription = "NewItemName3" + "\n" +
				aFoodType.toString() + System.lineSeparator() +
				DietCategory.GLUTENFREE.toString() + ", " + DietCategory.ORGANIC + System.lineSeparator() +
				"On Special" + System.lineSeparator() +
				NORMALPRICE*AbstractMenuItemSource.DISCOUNT + "$";
		assertEquals(fullDescription, newItem.description());
		assertEquals(fullDescription, newItem.toString());
	}

	@Test
	public void testPrices() {
		MenuItem newItem = MenuItem.get("NewItem", NORMALPRICE, aFoodType, DietCategory.GLUTENFREE, DietCategory.ORGANIC);
		assertEquals(newItem.getPrice(), newItem.getNormalPrice());
	}

	@Test
	public void testEquals() {
		MenuItem newItem = MenuItem.get("NewItemName4", NORMALPRICE, aFoodType, DietCategory.GLUTENFREE, DietCategory.ORGANIC);
		String random = "";
		assertFalse(newItem.equals(random));
	}
	
	@Test
	public void test_get_singleItem() {
		MenuItem poutine=MenuItem.get("Poutine",3.00,FoodType.MAIN);
		assertTrue(allitems.contains(poutine));
	}
	
	@Test
	public void test_get_singleItemTwice() {
		MenuItem poutine=MenuItem.get("Poutine",3.00,FoodType.MAIN);
		MenuItem poutineCopy=MenuItem.get("Poutine",3.00,FoodType.MAIN);
		assertTrue(allitems.contains(poutine));
		assertEquals(poutine,poutineCopy);
	}
	
	@Test
	public void test_get_sameItemWithDietCategory() {
		ArrayList<DietCategory> diet=new ArrayList<>();
    	diet.add(DietCategory.GLUTENFREE);
    	diet.add(DietCategory.ORGANIC);
		MenuItem poutine1=MenuItem.get("Poutine1",3.00,FoodType.MAIN);
		MenuItem poutine2=MenuItem.get("Poutine2",3.00,FoodType.MAIN,diet);
		assertEquals(poutine2.getDietCategories().get(0),DietCategory.GLUTENFREE);
		assertEquals(poutine2.getDietCategories().get(1),DietCategory.ORGANIC);
		assertTrue(allitems.contains(poutine1));
		assertTrue(allitems.contains(poutine2));
	}

	@Test
	public void testHashCode() {
		MenuItem veggieBurger=MenuItem.get("VeggieBurger",3.00,FoodType.MAIN, DietCategory.GLUTENFREE, DietCategory.VEGAN);
		MenuItem veggieBurger2=MenuItem.get("VeggieBurger",3.00,FoodType.MAIN, DietCategory.GLUTENFREE, DietCategory.VEGAN);
		assertTrue(veggieBurger.equals(veggieBurger2) && veggieBurger2.equals(veggieBurger));
		assertEquals(veggieBurger2.hashCode(),veggieBurger.hashCode());
	}

}
