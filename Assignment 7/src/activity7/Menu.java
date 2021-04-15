package activity7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;


/**
 * Represents a menu that can be displayed in the menu display.
 * uses the composite design pattern to aggregate AbstractMenuItemSource's
 * @author @ohaber
 *
 */
 
public class Menu implements Iterable<AbstractMenuItemSource>
{

	private OrderType aOrderType=OrderType.Price;
	private final List<Observer> aObservers = new ArrayList<>();
	private ArrayList<AbstractMenuItemSource> aItems = new ArrayList<>();

	//allows items to be removed from Menu and updates observers so changes are reflected in GUI
	public void removeItem(AbstractMenuItemSource pItem)
	{
		aItems.remove(pItem);
		pItem.removeItem(pItem);
		//Sort resulting menu based on Price or Alphabetical
		if(this.aOrderType==OrderType.Price) {
			aItems.sort(MenuItemSource.ByPrice());
		}else {
			aItems.sort(MenuItemSource.ByAlphabetical());
		}
		//Update observers
		for(Observer observer:aObservers) {
			observer.update();
		}

	}
	//Allows items to be added to menu and updates observers so changes are reflected in GUI
	public void addItem(AbstractMenuItemSource pItem)
	{
		if(!aItems.contains(pItem)) {
			aItems.add(pItem);
			//Sort resulting menu based on Price or alphabetical
			if(this.aOrderType==OrderType.Price) {
				aItems.sort(MenuItemSource.ByPrice());
			}else {
				aItems.sort(MenuItemSource.ByAlphabetical());
			}
			for(Observer observer:aObservers) {
				observer.update();
			}
		}

	
	}
	
	public void putItemOnsale(AbstractMenuItemSource pItem) {
		int i=aItems.indexOf(pItem);
		aItems.get(i).changeSpecial();
		if(this.aOrderType==OrderType.Price) {
			aItems.sort(MenuItemSource.ByPrice());
		}else {
			aItems.sort(MenuItemSource.ByAlphabetical());
		}
		for(Observer observer:aObservers) {
			observer.update();
		}
	}
	
	

	
	public void addObserver(Observer pObserver) {
		assert pObserver!=null;
		aObservers.add(pObserver);
	}
	
	public void removeObserver(Observer pObserver) {
		assert pObserver!=null;
		aObservers.remove(pObserver);
	}
	
	
	@Override
	public Iterator<AbstractMenuItemSource> iterator(){
		return new ArrayList<>(aItems).iterator();
	}

	//Takes an unspecified number of Predicates and returns a cloned list of items with those predicates applied
	public ArrayList<AbstractMenuItemSource> filter(Predicate<AbstractMenuItemSource>... pFilters){
		//Clone for encapsulation purposes. Shallow is sufficient because the items are effectively immutable
		ArrayList<AbstractMenuItemSource> filteredList = (ArrayList<AbstractMenuItemSource>) aItems.clone();
		for (Predicate<AbstractMenuItemSource> filt: pFilters) {
			//Use negate to show the things that fit the filter rather than removing them
			filteredList.removeIf(filt.negate());
		}
		return filteredList;
	}

	
	//Get the menu items while respecting encapsulation as outlined in filter method
	public ArrayList<AbstractMenuItemSource> getItems(){
		
		ArrayList<AbstractMenuItemSource> clone = (ArrayList<AbstractMenuItemSource>) aItems.clone();
		return clone;
		
	}
	

	
	

}
