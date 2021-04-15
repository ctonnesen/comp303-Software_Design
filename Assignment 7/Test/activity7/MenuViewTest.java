package activity7;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class MenuViewTest
{
	private Menu aMenu;
	private MenuExample aMenuExample;
	private stubObserver aStubObserver;

	static class stubObserver implements Observer
	{
		private Menu aMenu;
		private ArrayList<AbstractMenuItemSource> aItems = new ArrayList<>();

		public stubObserver(Menu pMenu)
		{
			aMenu = pMenu;
			aItems = aMenu.getItems();
			pMenu.addObserver(this);
		}

		public void update()
		{
			aItems = aMenu.getItems();
		}

		public ArrayList<AbstractMenuItemSource> getItems()
		{
			return aItems;
		}
	}

	@BeforeEach
	public void setup()
	{
		aMenu = new Menu();
		aMenuExample = new MenuExample();
		aStubObserver = new stubObserver(aMenu);
	}

	@Test
	public void testAddSuccessPriceSort()
	{
		setOrderType_Menu(aMenu, OrderType.Price);
		MenuItem item1 = MenuItem.get("Burger", 12, FoodType.MAIN);
		MenuItem item2 = MenuItem.get("Fries", 15, FoodType.SIDE);
		MenuItem item3 = MenuItem.get("Coke", 10, FoodType.DRINK);
		
		assertTrue(aStubObserver.getItems().size() == 0);
		aMenu.addItem(item1);
		aMenu.addItem(item2);
		aMenu.addItem(item3);

		assertTrue(sortedByPrice(aStubObserver.getItems()));
		
		assertTrue(aStubObserver.getItems().size() == 3);

	}

	@Test
	public void testAddSuccessAlphaSort()
	{
		setOrderType_Menu(aMenu, OrderType.Alphabetical);
		MenuItem item1 = MenuItem.get("Burger", 12, FoodType.MAIN);
		MenuItem item2 = MenuItem.get("Fries", 15, FoodType.SIDE);
		MenuItem item3 = MenuItem.get("Coke", 10, FoodType.DRINK);
		
		assertTrue(aStubObserver.getItems().size() == 0);
		aMenu.addItem(item1);
		aMenu.addItem(item2);
		aMenu.addItem(item3);
		assertTrue(sortedByAlphabet(aStubObserver.getItems()));
		
		assertTrue(aStubObserver.getItems().size() == 3);
	}

	@Test
	public void testAddFail()
	{
		MenuItem item1 = MenuItem.get("Burger", 12, FoodType.MAIN);
		assertTrue(aStubObserver.getItems().size() == 0);
		aMenu.addItem(item1);
		assertTrue(aStubObserver.getItems().size() == 1);
		aMenu.addItem(item1);
		assertTrue(aStubObserver.getItems().size() == 1);
	}
	
	@Test
	public void testRemoveSuccessAlpha() {
		setOrderType_Menu(aMenu, OrderType.Alphabetical);
		
		MenuItem item1 = MenuItem.get("Burger", 12, FoodType.MAIN);
		MenuItem item2 = MenuItem.get("Fries", 15, FoodType.SIDE);
		MenuItem item3 = MenuItem.get("Coke", 10, FoodType.DRINK);
		
		aMenu.addItem(item1);
		aMenu.addItem(item2);
		aMenu.addItem(item3);
		
		assertTrue(aStubObserver.getItems().size() == 3);
		
		aMenu.removeItem(item3);
		assertTrue(aStubObserver.getItems().size() == 2);
		
		assertTrue(sortedByAlphabet(aStubObserver.getItems()));
		
	}
	
	@Test
	public void testRemoveSuccessPrice() {
		setOrderType_Menu(aMenu, OrderType.Price);
		
		MenuItem item1 = MenuItem.get("Burger", 12, FoodType.MAIN);
		MenuItem item2 = MenuItem.get("Fries", 15, FoodType.SIDE);
		MenuItem item3 = MenuItem.get("Coke", 10, FoodType.DRINK);
		
		assertTrue(aStubObserver.getItems().size() == 0);
		
		aMenu.addItem(item1);
		aMenu.addItem(item2);
		aMenu.addItem(item3);
		
		assertTrue(aStubObserver.getItems().size() == 3);
		aMenu.removeItem(item1);
		assertTrue(aStubObserver.getItems().size() == 2);
		
		assertTrue(sortedByPrice(aStubObserver.getItems()));
	}
	
	@Test
	public void testRemoveFail() {
		
		MenuItem item1 = MenuItem.get("Burger", 12, FoodType.MAIN);
		MenuItem item2 = MenuItem.get("Fries", 15, FoodType.SIDE);
		MenuItem item3 = MenuItem.get("Coke", 10, FoodType.DRINK);
		assertTrue(aStubObserver.getItems().size() == 0);
		
		aMenu.addItem(item1);
		aMenu.addItem(item2);
		aMenu.addItem(item3);
		
		assertTrue(aStubObserver.getItems().size() == 3);
		aMenu.removeItem(item1);
		assertTrue(aStubObserver.getItems().size() == 2);
		aMenu.removeItem(item1);
		assertTrue(aStubObserver.getItems().size() == 2);
		
	}
	
	@Test
	public void testCombo2Success() {
		MenuItem item1 = MenuItem.get("Burger", 12, FoodType.MAIN);
		MenuItem item2 = MenuItem.get("Fries", 15, FoodType.SIDE);
		
		ArrayList<AbstractMenuItemSource> copy = new ArrayList<AbstractMenuItemSource>();
		copy.add(item1);
		copy.add(item2);
		
		ComboMenuItem combo=ComboMenuItem.get(false, copy);
		
        aMenu.addItem(combo);
        assertTrue(aStubObserver.getItems().contains(combo));

	}
	
	@Test
	public void testCombo4Success() {
		MenuItem item1 = MenuItem.get("Burger", 12, FoodType.MAIN);
		MenuItem item2 = MenuItem.get("Fries", 15, FoodType.SIDE);
		MenuItem item3 = MenuItem.get("Coke", 3, FoodType.DRINK);
		MenuItem item4 = MenuItem.get("Brownie", 6, FoodType.SNACK);
		
		ArrayList<AbstractMenuItemSource> copy = new ArrayList<AbstractMenuItemSource>();
		copy.add(item1);
		copy.add(item2);
		copy.add(item3);
		copy.add(item4);
		
		ComboMenuItem combo=ComboMenuItem.get(false, copy);
        aMenu.addItem(combo);
        assertTrue(aStubObserver.getItems().contains(combo));
	}
	
	@Test
	public void testComboFail() {
		try{
			MenuItem item1 = MenuItem.get("Burger", 12, FoodType.MAIN);
			ArrayList<AbstractMenuItemSource> copy = new ArrayList<AbstractMenuItemSource>();
			copy.add(item1);
			ComboMenuItem combo = ComboMenuItem.get(false, copy);
	        aMenu.addItem(combo);
	        fail();
		}catch(ExceptionInInitializerError e) {
			assertTrue(aStubObserver.getItems().size()==0);
		}
		
	}
	
	@Test
	public void putOnSale() {
		AbstractMenuItemSource item = aMenuExample.getItems().get(0);
		
		aMenu.addItem(item);
		assertFalse(getSpecial_MenuItemSource(item));
		assertFalse(getSpecial_MenuItemSource(aStubObserver.getItems().get(0)));
		
		item.changeSpecial();
		assertTrue(getSpecial_MenuItemSource(item));
		assertTrue(getSpecial_MenuItemSource(aStubObserver.getItems().get(0)));
	}
	
	@Test
	public void removeOnSale() {
		AbstractMenuItemSource item = aMenuExample.getItems().get(0);
		
		aMenu.addItem(item);
		
		item.changeSpecial();
		assertTrue(getSpecial_MenuItemSource(item));
		assertTrue(getSpecial_MenuItemSource(aStubObserver.getItems().get(0)));
		
		item.changeSpecial();
		assertFalse(getSpecial_MenuItemSource(item));
		assertFalse(getSpecial_MenuItemSource(aStubObserver.getItems().get(0)));
	}
	
	@Test
	public void sortedBy_Price() {
		setOrderType_Menu(aMenu,  OrderType.Price);

		for (AbstractMenuItemSource pItem : aMenuExample) {
			aMenu.addItem(pItem);
			assertTrue(sortedByPrice(aMenu.getItems()));
			assertTrue(sortedByPrice(aStubObserver.getItems()));
		}
	}
	
	@Test
	public void sortedBy_Price_ChangeSpecial() {
		setOrderType_Menu(aMenu,  OrderType.Price);

		for (AbstractMenuItemSource pItem : aMenuExample) {
			aMenu.addItem(pItem);
			assertTrue(sortedByPrice(aMenu.getItems()));
			assertTrue(sortedByPrice(aStubObserver.getItems()));
		}
		
		aMenu.putItemOnsale(aMenu.getItems().get(5));
		aMenu.putItemOnsale(aMenu.getItems().get(4));
		aMenu.putItemOnsale(aMenu.getItems().get(2));
		aMenu.putItemOnsale(aMenu.getItems().get(8));

		assertTrue(sortedByPrice(aMenu.getItems()));
		assertTrue(sortedByPrice(aStubObserver.getItems()));	
		
	}
	
	@Test
	public void sortedBy_Alphabet_ChangeSpecial() {
		setOrderType_Menu(aMenu,  OrderType.Alphabetical);

		for (AbstractMenuItemSource pItem : aMenuExample) {
			aMenu.addItem(pItem);
			assertTrue(sortedByAlphabet(aMenu.getItems()));
			assertTrue(sortedByAlphabet(aStubObserver.getItems()));
		}
		
		aMenu.putItemOnsale(aMenu.getItems().get(5));
		aMenu.putItemOnsale(aMenu.getItems().get(4));
		aMenu.putItemOnsale(aMenu.getItems().get(2));
		aMenu.putItemOnsale(aMenu.getItems().get(8));

		assertTrue(sortedByAlphabet(aMenu.getItems()));
		assertTrue(sortedByAlphabet(aStubObserver.getItems()));	
		
	}
	
	@Test
	public void sortedBy_Alphabet() {
		setOrderType_Menu(aMenu,  OrderType.Alphabetical);
		
		
		for (AbstractMenuItemSource pItem : aMenuExample) {
			aMenu.addItem(pItem);			
			assertTrue(sortedByAlphabet(aMenu.getItems()));
			assertTrue(sortedByAlphabet(aStubObserver.getItems()));
		}
	}
	
	private boolean sortedByAlphabet(ArrayList<AbstractMenuItemSource> pItems) {
		
		String name_before = pItems.get(0).getName();
		for(AbstractMenuItemSource item : pItems) {
			if (name_before.compareToIgnoreCase(item.getName()) > 0) {
				return false;
			}
			name_before = item.getName();
		}
		return true;
	}
	
	private boolean sortedByPrice(ArrayList<AbstractMenuItemSource> pItems) {
		double price_before = pItems.get(0).getPrice();
		for(AbstractMenuItemSource item : pItems) {
			if (price_before > item.getPrice()) {
				return false;
			}
			price_before = item.getPrice();
		}
		return true;
	}
	

	private void setOrderType_Menu(Menu menu, OrderType order) {
		try
		{
			Field f = menu.getClass().getDeclaredField("aOrderType");
			f.setAccessible(true);
			f.set(menu, order);
		
		}
		catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e)
		{
			e.printStackTrace();
			fail();
		}
	}
	
	
	private boolean getSpecial_MenuItemSource(AbstractMenuItemSource item) {
		try
		{
			Field f = item.getClass().getSuperclass().getDeclaredField("onSpecial");
			f.setAccessible(true);
			return (boolean) f.get(item);
		
		}
		catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e)
		{
			e.printStackTrace();
			fail();
		}

		return false;
	}
}
