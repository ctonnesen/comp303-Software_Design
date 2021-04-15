package activity7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MenuTest
{
	
	private static MenuItem poutine=MenuItem.get("Poutine",3.00,FoodType.MAIN);
	private static MenuItem soup=MenuItem.get("Soup",6.00,FoodType.MAIN);
	private static MenuItem spaghetti=MenuItem.get("Spaghetti",5.78,FoodType.MAIN);
	private static MenuItem chips=MenuItem.get("Chips",1,FoodType.SNACK, DietCategory.VEGAN);
	private static MenuItem Peanuts=MenuItem.get("Peanuts",1,FoodType.SNACK);
	private static MenuItem Chocolate=MenuItem.get("Chocolate Bar",1.75,FoodType.SNACK);
	private static MenuItem coffee=MenuItem.get("Coffee",2,FoodType.DRINK);
	private static MenuItem tea=MenuItem.get("Tea",1.75,FoodType.DRINK);
	private static MenuItem beer=MenuItem.get("Beer",2.50,FoodType.DRINK);
	
	static class stubObserver implements Observer{
		public void update() {
		}
	}
	Menu menu;
	ArrayList<AbstractMenuItemSource> itemlist;
	List<Observer> observerslist;
	 
	
	@BeforeEach
	public void setup() {
		menu=new Menu();
		try {
			Field field1=Menu.class.getDeclaredField("aItems");
			field1.setAccessible(true);
			itemlist=(ArrayList<AbstractMenuItemSource>)field1.get(menu);
			Field field2=Menu.class.getDeclaredField("aObservers");
			field2.setAccessible(true);
			observerslist=(List<Observer>)field2.get(menu);
		}
		catch(ReflectiveOperationException exception) {
			fail();
		}
		
	}
	
	@Test
	public void test_addObserver(){
		stubObserver observer=new stubObserver();
		menu.addObserver(observer);
		observerslist.contains(observer);
	}
	
	@Test
	public void test_removeObserver(){
		stubObserver observer=new stubObserver();
		menu.addObserver(observer);
		menu.removeObserver(observer);
		assertFalse(observerslist.contains(observer));
	}
	
	@Test
	public void test_addItem_singleItem() {
		menu.addItem(Chocolate);
		assertTrue(itemlist.contains(Chocolate));
	}
	
	@Test
	public void test_addItem_MultiItem() {
		menu.addItem(Chocolate);
		menu.addItem(tea);
		assertTrue(itemlist.contains(Chocolate));
		assertTrue(itemlist.contains(tea));
		
	}
	
	@Test
	public void test_addItem_addSameItem() {
		menu.addItem(Chocolate);
		menu.addItem(Chocolate);
		assertEquals(itemlist.size(),1);
		
	}
	
	@Test
	public void test_addItem_orderKeeping() {
		menu.addItem(Chocolate);
		menu.addItem(soup);
		menu.addItem(beer);
		assertEquals(itemlist.get(0),Chocolate);
		assertEquals(itemlist.get(1),beer);
		assertEquals(itemlist.get(2),soup);
			
	}
	
	@Test
	public void test_removeItem() {
		menu.addItem(Chocolate);
		menu.removeItem(Chocolate);
		assertEquals(itemlist.size(),0);	
	}
	
	@Test
	public void test_removeItem_orderKeeping() {
		menu.addItem(Chocolate);
		menu.addItem(soup);
		menu.addItem(tea);
		menu.removeItem(Chocolate);
		assertEquals(itemlist.get(0),tea);
		assertEquals(itemlist.get(1),soup);
	}
	
	@Test
	public void test_putOnSpecial_orderKeeping() {
		menu.addItem(chips);
		menu.addItem(soup);
		menu.addItem(Peanuts);
		menu.putItemOnsale(Peanuts);
		assertEquals(itemlist.get(0),Peanuts);
		assertEquals(itemlist.get(1),chips);
		assertEquals(itemlist.get(2),soup);
			
	}

	@Test
	public void testFilter(){
		menu.addItem(chips);
		menu.addItem(soup);
		menu.addItem(poutine);
		Predicate<AbstractMenuItemSource> cheapFilter = menuItem -> menuItem.getPrice() < 3;
		ArrayList<AbstractMenuItemSource> output1 = menu.filter(cheapFilter);
		assertEquals(output1.size(), 1);
		Predicate<AbstractMenuItemSource> veganFilter = menuItem -> menuItem.getDietCategories().contains(DietCategory.VEGAN);
		ArrayList<AbstractMenuItemSource> output2 = menu.filter(cheapFilter, veganFilter);
		assertEquals(output2.size(), 1);
	}

}
