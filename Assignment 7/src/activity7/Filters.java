package activity7;

import java.util.HashMap;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Static class to store the Predicates that can be applied to the menu. 
 * This class is very helpful in the branch where the filters can be accessed through the GUI
 * @author @ohaber
 *
 */
public class Filters
{
	//Use two hashmaps to be able to access pair based on name or value
	private static HashMap<Predicate<AbstractMenuItemSource>, String> aFiltersByValue = new HashMap<Predicate<AbstractMenuItemSource>, String>();
	private static HashMap<String, Predicate<AbstractMenuItemSource>> aFiltersByName = new HashMap<String, Predicate<AbstractMenuItemSource>>();

	// @pre pFilter != null
	//Method to add unique Predicates to the lists. Return's String to aid the branch where accessible in the GUI which is helpful for a future developer
	public static String add(String pName, Predicate<AbstractMenuItemSource> pFilter)
	{
		assert pFilter != null;
		//add if not present
		if (!(aFiltersByValue.containsKey(pFilter) && aFiltersByName.containsKey(pName)))
		{
			//add to both in reverse order
			aFiltersByValue.put(pFilter, pName);
			aFiltersByName.put(pName, pFilter);
			return pName;
		}
		else
		{
			return null;
		}

	}
	//Two getters that are overloaded to return the corresponding Predicate's value or name based on input
	public static String get(Predicate<AbstractMenuItemSource> pPredicate)
	{
		return aFiltersByValue.get(pPredicate);
	}

	public static Predicate<AbstractMenuItemSource> get(String pName)
	{
		return aFiltersByName.get(pName);
	}

}
