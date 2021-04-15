package activity7;

import java.lang.reflect.Field;
import java.util.ArrayList;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComboMenuItemTest
{
	private static ArrayList<ComboMenuItem> comboMenuItems;
	private static MenuItem soup=MenuItem.get("Soup",6.00,FoodType.MAIN);
	private static MenuItem spaghetti=MenuItem.get("Spaghetti",5.78,FoodType.MAIN);
	private static MenuItem chips=MenuItem.get("Chips",1,FoodType.SNACK);
	private static MenuItem Peanuts=MenuItem.get("Peanuts",1,FoodType.SNACK);

	private MenuItem testMain;
	private MenuItem testSide;
	private MenuItem testDrink;
	private MenuItem testSnack;
	private ComboMenuItem comboTestItem;

	private static ArrayList<AbstractMenuItemSource> getItems(ComboMenuItem combo){
		ArrayList<AbstractMenuItemSource> list=new ArrayList<>();
		try {
			Field field=ComboMenuItem.class.getDeclaredField("aMenuItems");
			field.setAccessible(true);
			list=(ArrayList<AbstractMenuItemSource>)field.get(combo);
			return list;
		}
		catch(ReflectiveOperationException exception) {
			fail();
		}
		return list;
	}

	@BeforeEach
	public void setup() {
		try {
			Field field=ComboMenuItem.class.getDeclaredField("comboMenuItems");
			field.setAccessible(true);
			comboMenuItems=(ArrayList<ComboMenuItem>)field.get(null);
			comboMenuItems.clear();

			ArrayList<DietCategory> dietCategories1 = new ArrayList<>();
			dietCategories1.add(DietCategory.VEGAN);
			dietCategories1.add(DietCategory.VEGETARIAN);

			ArrayList<DietCategory> dietCategories2 = new ArrayList<>();
			dietCategories2.add(DietCategory.VEGETARIAN);

			testMain = MenuItem.get("testMain", 5.50, FoodType.MAIN, dietCategories1);
			testSide = MenuItem.get("testSide", 3.50, FoodType.SIDE, dietCategories2);
			testDrink = MenuItem.get("testDrink", 1.50, FoodType.DRINK, dietCategories2);
			testSnack = MenuItem.get("testSnack", 2.00, FoodType.SNACK, dietCategories2);

			ArrayList<AbstractMenuItemSource> itemsInCombo = new ArrayList<>();
			itemsInCombo.add(testSide);
			itemsInCombo.add(testDrink);
			itemsInCombo.add(testMain);
			itemsInCombo.add(testSnack);

			comboTestItem = ComboMenuItem.get(false, itemsInCombo);
		}
		catch(ReflectiveOperationException exception) {
			fail();
		}

	}

	@Test
	public void test_get_inputItemsLessThanOne() {
		ArrayList<AbstractMenuItemSource> items=new ArrayList<AbstractMenuItemSource>();
		try {
			ComboMenuItem.get(false, items);
			fail();
		}
		catch(ExceptionInInitializerError e) {}

		items.add(chips);
		try {
			ComboMenuItem.get(false, items);
			fail();
		}
		catch(ExceptionInInitializerError e) {}

	}

	@Test
	public void test_get_validInputs() {
		ArrayList<AbstractMenuItemSource> items=new ArrayList<AbstractMenuItemSource>();
		items.add(Peanuts);
		items.add(spaghetti);
		ComboMenuItem combo=ComboMenuItem.get(false, items);
		ArrayList<AbstractMenuItemSource> list=ComboMenuItemTest.getItems(combo);
		assertTrue(list.contains(spaghetti));
		assertTrue(list.contains(Peanuts));
		assertEquals(combo.getPrice(),(Peanuts.getPrice()+spaghetti.getPrice())*combo.getComboFactor());
	}

	@Test
	public void test_equals_SameMenuItemsList() {
		ArrayList<AbstractMenuItemSource> items=new ArrayList<AbstractMenuItemSource>();
		items.add(Peanuts);
		items.add(spaghetti);

		ComboMenuItem combo1=ComboMenuItem.get(false, items);
		ComboMenuItem combo2=ComboMenuItem.get(false, items);

		assertEquals(combo1, combo2);
	}

	@Test
	public void test_equals_DifferentMenuItemsList(){
		ArrayList<AbstractMenuItemSource> items=new ArrayList<AbstractMenuItemSource>();
		items.add(Peanuts);
		items.add(spaghetti);
		items.add(chips);

		ComboMenuItem combo=ComboMenuItem.get(false, items);
		assertNotEquals(comboTestItem, combo);
	}


	@Test
	public void test_getFoodType(){
		assertEquals(FoodType.COMBO, comboTestItem.getFoodType());
	}

	@Test
	public void test_getDietCategory_notAllItemsShareCategory(){
		assertFalse(comboTestItem.getDietCategories().contains(DietCategory.VEGAN));
	}

	@Test
	public void test_getDietCategory_AllItemsShareCategory(){
		assertTrue(comboTestItem.getDietCategories().contains(DietCategory.VEGETARIAN));
	}

	@Test
	public void test_removeItem(){
		comboTestItem.removeItem(comboTestItem);
		assertFalse(comboMenuItems.contains(comboTestItem));
	}


	@Test
	public void test_getaMenuItems(){
		assertEquals(getItems(comboTestItem), comboTestItem.getaMenuItems());
	}

}
