package activity7;

import java.util.ArrayList;
import java.util.Comparator;


/**
 * Defines the standard interface for all MenuItemSources, the lowest building block of the Menu
 */
public interface MenuItemSource {

	//The dietary categories of the MenuItemSource
    public ArrayList<DietCategory> getDietCategories(); 

    //FoodType
    public FoodType getFoodType();

    //Name
    public String getName();

    //Whether it's on special
    public void changeSpecial();

    //The description
    public String description();

    //It's price
    public double getPrice();
    
    
    /**
	 * Factory comparators
     * @return Comparator that uses by price comparison
     */
    public static Comparator<MenuItemSource> ByPrice(){ 
		return (pItem1, pItem2) -> (int) (pItem1.getPrice() * 100 - pItem2.getPrice() * 100);
	}
	
    /**
	 * Factory comparators
     * @return Comparator that uses by alphabetical order
     */
	public static Comparator<MenuItemSource> ByAlphabetical(){
		return (pItem1, pItem2) -> pItem1.getName().compareToIgnoreCase(pItem2.getName());
	}


}
