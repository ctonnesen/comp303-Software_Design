package activity7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ComboMenuItem extends AbstractMenuItemSource
{
	private static ArrayList<ComboMenuItem> comboMenuItems = new ArrayList<>();
	private final ArrayList<AbstractMenuItemSource> aMenuItems = new ArrayList<>();
	final FoodType comboType = FoodType.COMBO;
	final double comboFactor = 0.9;

    private ComboMenuItem(ArrayList<AbstractMenuItemSource> pMenuItems) {
		StringBuilder name = new StringBuilder();
		assert pMenuItems != null;
		if (pMenuItems.size()<=1) {
			throw new ExceptionInInitializerError();
		}
		// Checks that a combo is not being made out of a single item
		for (int i = 0; i < pMenuItems.size(); i++) {
			if (i == pMenuItems.size()-1) {
				name.append("and " + pMenuItems.get(i).getName() + " Combo");
				constructorHelper(pMenuItems.get(i));
				continue;
				// If we're in the last iteration, we append "Combo" to the end
			}
			if (pMenuItems.size() == 2) {
				name.append(pMenuItems.get(i).getName() + " ");
				constructorHelper(pMenuItems.get(i));
				continue;
				// This case handles if there is only two items being put into a combo to avoid an unnecessary comma in the name
			}
			name.append(pMenuItems.get(i).getName() + ", ");
			constructorHelper(pMenuItems.get(i));
			// Deals with the addition of a regular items that can be any but not the last
		}
		aName = name.toString();
		NORMALPRICE *= comboFactor;
		// All of the prices are added up an multiplied by the Combo Discount
    }

    private void constructorHelper(AbstractMenuItemSource current) {
		if (current.getFoodType().equals(FoodType.COMBO)) {
			throw new ExceptionInInitializerError();
		}
		// Handles the issue of passing a combo for inclusion in a new combo object
		aMenuItems.add(current);
		NORMALPRICE += current.getNormalPrice();
	}

	/**
	 * A public get method for FLYWEIGHT usage.
	 * @param pOnSpecial
	 * @param comboItemArray
	 * @return
	 */

    public static ComboMenuItem get(boolean pOnSpecial, ArrayList<AbstractMenuItemSource> comboItemArray) {
    	comboItemArray.removeIf(Objects::isNull);
    	// Functional programming to remove any null items from the provided array
    	ComboMenuItem newCombo = new ComboMenuItem(comboItemArray);
        for (ComboMenuItem current : comboMenuItems) {
            if (current.equals(newCombo)) {
            	// Checks for equivalent Object inside of FLYWEIGHT store
            	if (current.onSpecial != pOnSpecial) {
            		current.changeSpecial();
            	}
            	return current;
            	// The onSpecial call ensures that the client gets back an Object in the requested state
            }
        }
        comboMenuItems.add(newCombo);
        return newCombo;
    }

	/**
	 * This extraInformation is very similar to the MenuItem extraInformation()
	 * @return
	 */

	@Override
	protected String extraInformation()
	{
		StringBuilder total = new StringBuilder();
		total.append(FoodType.COMBO.toString() + System.lineSeparator());
		String diet = "";
		if (!getDietCategories().isEmpty()) {
			diet += getDietCategories().toString().replace("[","").replace("]","");
			total.append(diet + System.lineSeparator());
		}
		if (onSpecial) {
			total.append("On Special" + System.lineSeparator());
		}
		return total.toString();
	}

	/**
	 * This getDietCategories is a bit different from MenuItem in that it needs to handle conflicting dietCategories. To solve this, we
	 * just ensure that every item in a Combo has the same dietCategories as the first item through a retailAll().
	 * @return
	 */

	@Override
	public ArrayList<DietCategory> getDietCategories() {
		ArrayList<DietCategory> finalDietList = new ArrayList<>(aMenuItems.get(0).getDietCategories());
		for (MenuItemSource current : aMenuItems) {
			finalDietList.retainAll(current.getDietCategories());
		}
		return finalDietList;
    }

	@Override
	public FoodType getFoodType() {
		return comboType;
	}

	@Override
	public void removeItem(AbstractMenuItemSource menuItem) {
		comboMenuItems.remove((ComboMenuItem) menuItem);
	}

	public List<AbstractMenuItemSource> getaMenuItems() {
		return Collections.unmodifiableList(aMenuItems);
    }

	/**
	 * Equality is defined as two Combos having the same objects in their item lists
	 * @param pObject
	 * @return
	 */

	@Override
    public boolean equals(Object pObject) {
	   if (pObject == null || pObject.getClass() != this.getClass()) {
		   return false;
	   }
	   ComboMenuItem pComboMenuItem = (ComboMenuItem) pObject;
	   if(pComboMenuItem.aMenuItems.size() == aMenuItems.size()) {
		  if (aMenuItems.containsAll(pComboMenuItem.getaMenuItems())) {
			  return true;
		  }
	   }
	   return false;
	}

	/**
	 * Hashcode is made from the hashcode of all internal combo items
	 * @return
	 */

    @Override
    public int hashCode() {
    	int x=0;
    	for(AbstractMenuItemSource e:this.aMenuItems) {
    		x+=e.hashCode();
    	}
    	return x;
    	
    }

	public double getComboFactor() {
		return comboFactor;
	}
}