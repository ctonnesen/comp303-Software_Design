package activity7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Defines the basic, concrete part of the menu, which the SizedMenuItem and the ComboMenuItem use 
 * Implements a flyweight pattern for it's construction. 
 * @author @ctonne, @slaidm, @ohaber
 *
 */

public class MenuItem extends AbstractMenuItemSource{
	//For the flyweight pattern. 
    private static ArrayList<MenuItem> menuItems=new ArrayList<>();
    
    //Defines some internal information about the food. 
    private FoodType aFoodType;
    private ArrayList<DietCategory> aDietCategory;

    /** 
     * Flyweight private constructor. 
     * All checks against null etc are made in the MenuItem.get() method
     */
    private MenuItem (String pName, double pPrice, FoodType pFoodType, ArrayList<DietCategory> pDietCategory){
        aName = pName;
        NORMALPRICE = pPrice;
        aFoodType = pFoodType;
        
        pDietCategory.removeIf(Objects::isNull); //Removes all of the null objects
        aDietCategory = pDietCategory;
    }

    /**
     * Flyweight function for getting MenuItems
     * @param pName
     * @param pPrice
     * @param pFoodType
     * @param pDietCategory
     * @pre pName != null && pPrice > 0 && pFoodType != null && pDietCategory != null;
     * @return The flyweight MenuItem
     */
    public static MenuItem get(String pName, double pPrice, FoodType pFoodType, ArrayList<DietCategory> pDietCategory) {
        assert pName != null && pPrice > 0 && pFoodType != null && pDietCategory != null;
        
        MenuItem newItem = new MenuItem(pName,pPrice,pFoodType,pDietCategory);
        
        for (MenuItem current : menuItems) {
            if (current.equals(newItem)) {
                return current;
            }
        }
        
        menuItems.add(newItem);
        return newItem;
    }
    
    //Aliases on the get MenuItems, which use the main get method as their basis. 
    public static MenuItem get(String pName, double pPrice, FoodType pFoodType) {
    	return MenuItem.get(pName, pPrice, pFoodType, new ArrayList<>());
    }

    public static MenuItem get(String pName, double pPrice, FoodType pFoodType, DietCategory ... pDietCategories) {
        return MenuItem.get(pName, pPrice, pFoodType, new ArrayList<>(Arrays.asList(pDietCategories)));
    }

    
    /**
     * Displays the FoodType, the diet categories, if they exist, and whether the item is on special. 
     */
    @Override
	protected String extraInformation()
	{
       String total = aFoodType.toString() + System.lineSeparator();
       String diet = "";
       if (!aDietCategory.isEmpty()) {
           diet += aDietCategory.toString().replace("[","").replace("]","");
           total += diet + System.lineSeparator();
       }
       if (onSpecial) {
           total += "On Special" + System.lineSeparator();
       }
       return total;
	}

    // Inherited methods from MenuItemSource
    @Override
    public ArrayList<DietCategory> getDietCategories() {
        return aDietCategory;
    }

    @Override
    public FoodType getFoodType() {
        return aFoodType;
    }

    //Removes an item from the menu. 
    @Override
    public void removeItem(AbstractMenuItemSource menuItem) {
        menuItems.remove((MenuItem) menuItem);
    }

    /**
     * Equals if the name is the same. 
     */
    @Override
    public boolean equals(Object pObject) {
	   if (pObject == null || pObject.getClass() != this.getClass()) {
		   return false;
	   } else if (pObject == this) {
		   return true;
	   }
	   MenuItem pMenuItem = (MenuItem) pObject;
	   if (aName.equals(pMenuItem.getName())) {
	       return true;
	   }
       return false;
    }

    @Override
    public int hashCode() {
    	int x=0;
    	for(DietCategory e:this.aDietCategory) {
    		x+=e.hashCode();
    	}
    	x+=this.aFoodType.hashCode()+this.aName.hashCode()+this.NORMALPRICE;
    	return x;
    }



}
