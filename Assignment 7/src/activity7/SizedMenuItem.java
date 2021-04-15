package activity7;

import java.util.ArrayList;

/**
 *
 * @author @ctonne, @slaidm
 * Implements the Decorator Pattern for MenuItemSources.
 * Is a flyweight object
 */

public class SizedMenuItem extends AbstractMenuItemSource{
    private static ArrayList<SizedMenuItem> sizedMenuItems = new ArrayList<>();
    //Internals
    private MenuItem aMenuItem;
    private Size aSize;

    //Standard factors on the small and large sizes of an item.
    final double smallFactor = 0.8;
    final double largeFactor = 1.3;


    /**
     * Private constructor for the class. Notable difference is that aName just places the size before it
     *
     * @param pMenuItem
     * @param pSize
     */

    private SizedMenuItem(MenuItem pMenuItem, Size pSize) {
        aMenuItem = pMenuItem;
        aSize = pSize;
        NORMALPRICE = getSizePrice();

        aName = aSize.toString() + " " + aMenuItem.getName(); //Sets the name to include the size.
    }

    /**
     * Flyweight public constructor. 
     * Defines equality as both name and size.
     * @param pMenuItem
     * @param pSize
     * @return
     */
    public static SizedMenuItem get(MenuItem pMenuItem, Size pSize) {
        assert pMenuItem != null && pSize != null;
        SizedMenuItem newItem = new SizedMenuItem(pMenuItem, pSize);
        for (SizedMenuItem current : sizedMenuItems) {
            if (current.equals(newItem)) {
            	return current;
                // Checks FlYWEIGHT store to see if there's any matches and returns if so
            }
        }
        sizedMenuItems.add(newItem);
        // If no matches, adds created Object to the store
        return newItem;
    }

    /**
     * Helper method that calculates the price from the menu item price given the size
     * @return the factored price
     */
    private double getSizePrice() {
        if (aSize.equals(Size.SMALL)) {
            return aMenuItem.NORMALPRICE*smallFactor;
        }
        if (aSize.equals(Size.MEDIUM)) {
        	return aMenuItem.NORMALPRICE;
        }
        return aMenuItem.NORMALPRICE*largeFactor;
    }
    
    //Super methods
    @Override
    public void removeItem(AbstractMenuItemSource menuItem) {
        assert menuItem != null;
        sizedMenuItems.remove((SizedMenuItem) menuItem);
    }

    /**
     * The below two methods just pull from the internal MenuItem of the class
     */

    @Override
    public ArrayList<DietCategory> getDietCategories() {
        return aMenuItem.getDietCategories();
    }

    @Override
    public FoodType getFoodType() {
        return aMenuItem.getFoodType();
    }

    /**
     * The only extra items required for this class are the size, but we must also append the information from the soure item
     * @return extraInformationString
     */

	@Override
	protected String extraInformation()
	{
	    return aSize.toString() + System.lineSeparator() + aMenuItem.extraInformation();
	}

    /**
     * Equality is based off of it two Objects have the same internal MenuItem, and if their size is the same.
     * @param pObject
     * @return
     */

    @Override
    public boolean equals(Object pObject) {
	   if (pObject == null || pObject.getClass() != this.getClass()) {
		   return false;
	   } else if (pObject == this) {
		   return true;
	   }
	   SizedMenuItem pSizedMenuItem = (SizedMenuItem) pObject;
	   if (aMenuItem.equals(pSizedMenuItem.aMenuItem) && aSize == pSizedMenuItem.aSize) {
	       return true;
	   }
       return false;
    }

    /**
     * New hashcode just appends hashCode of size enum
     * @return
     */


    @Override
    public int hashCode() {
    	return this.aMenuItem.hashCode()+this.aSize.hashCode();
    }

    public double getSmallFactor() {
        return smallFactor;
    }

    public double getLargeFactor() {
        return largeFactor;
    }
}
