package activity7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

public class MenuExample implements Iterable<AbstractMenuItemSource>
{
	private ArrayList<AbstractMenuItemSource> aItems = new ArrayList<>();
	
	public MenuExample() {
		//MenuItems, SizedMenuItem, ComboMenuItem
		//Size: Small, Medium, Large
		//DietCategory: Vegan, GlutenFree, Vegetarian, Organic [CHECK]
		//FoodType: DRINKS, SNACKS, MAINS, COMBO, SIDES;
		//OnSpecial
		
		//Drinks: Pepsi, coke, Seven up
		//Mains: Burger, Veggie Burger, Ribs
		//Snacks: Chips, Cookies
		//Sides: Fries, Poutine, LeafyGreens
		
		
		//Combo FriesBurgerDrink, Combo
		//Fries: Vegan, GlutenFree, SIDES, large, onSpecial
		//Burger: Organic, vegetarian, main M
		//Pepsi: Vegan, Drinks, Small
		
		
		ArrayList<DietCategory> pepsiDiet = new ArrayList<>();
    	pepsiDiet.add(DietCategory.VEGAN);
        pepsiDiet.add(DietCategory.VEGETARIAN);
    	MenuItem pepsi = MenuItem.get("Pepsi", 1.75, FoodType.DRINK, pepsiDiet);
    	MenuItem coke = MenuItem.get("coke", 1.75, FoodType.DRINK, pepsiDiet);
    	MenuItem sevenUp = MenuItem.get("7Up", 1.75, FoodType.DRINK, pepsiDiet);
        aItems.add(pepsi);
        aItems.add(coke);
        aItems.add(sevenUp);
        
        ArrayList<DietCategory> veggieBurgerDiet = new ArrayList<>();
        veggieBurgerDiet.add(DietCategory.ORGANIC);
        veggieBurgerDiet.add(DietCategory.VEGETARIAN);
        MenuItem burgerV = MenuItem.get("Veggie Burger", 4.00, FoodType.MAIN, veggieBurgerDiet);
        MenuItem burger = MenuItem.get("Burger", 4.00, FoodType.MAIN, new ArrayList<DietCategory>());
        MenuItem ribs = MenuItem.get("Veggie Ribs", 12.00, FoodType.MAIN, veggieBurgerDiet);
        aItems.add(burgerV);
        aItems.add(burger);
        aItems.add(ribs);
        
    	ArrayList<DietCategory> friesDiet = new ArrayList<>();
    	friesDiet.add(DietCategory.VEGAN);
    	friesDiet.add(DietCategory.GLUTENFREE);
        friesDiet.add(DietCategory.VEGETARIAN);
        MenuItem fries = MenuItem.get("Fries", 2.00, FoodType.SIDE, friesDiet);
        fries.changeSpecial();
    	SizedMenuItem sizedFriesL = SizedMenuItem.get(fries, Size.LARGE);
    	SizedMenuItem sizedFriesM = SizedMenuItem.get(fries, Size.MEDIUM);
    	SizedMenuItem sizedFriesS = SizedMenuItem.get(fries, Size.SMALL);
    	aItems.add(sizedFriesL);
    	aItems.add(sizedFriesM);
    	aItems.add(sizedFriesS);
        MenuItem Poutine = MenuItem.get("Poutine", 3.00, FoodType.SIDE, new ArrayList<DietCategory>());
        SizedMenuItem PoutineL = SizedMenuItem.get(Poutine, Size.LARGE);
    	aItems.add(Poutine);
    	aItems.add(PoutineL);
    	MenuItem leafs = MenuItem.get("Leafy Greens", 2.50, FoodType.SIDE, veggieBurgerDiet);
    	aItems.add(leafs);
    	
    	MenuItem cookie = MenuItem.get("Cookie", 5.00, FoodType.SNACK, new ArrayList<DietCategory>());
    	aItems.add(cookie);
    	MenuItem chips = MenuItem.get("Chips", 5.00, FoodType.SNACK, pepsiDiet);
    	aItems.add(chips);
    	
    	ArrayList<AbstractMenuItemSource> comboItems = new ArrayList<>();
    	comboItems.add(sizedFriesL);
    	comboItems.add(burgerV);
    	comboItems.add(pepsi);
    	ComboMenuItem comboFriesBurgerDrink = ComboMenuItem.get(false, comboItems);
    	aItems.add(comboFriesBurgerDrink);    	
	}
	
	@Override
	public Iterator<AbstractMenuItemSource> iterator() {
		return aItems.iterator();
	}

	public Stream<AbstractMenuItemSource> stream() {
		return  aItems.stream();
	}

	public ArrayList<AbstractMenuItemSource> getItems(){
		return new ArrayList<>(aItems);
	}
	
	public static void main(String[] args) {
		MenuExample me = new MenuExample();
		me.forEach(m -> System.out.println(m.description() + "\n"));
	}
}

