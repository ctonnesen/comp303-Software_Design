package activity7;

import java.util.ArrayList;

public abstract class AbstractMenuItemSource implements MenuItemSource
{
	
    protected static final double DISCOUNT = 0.9;
    protected double NORMALPRICE;
    protected boolean onSpecial = false; 
    protected String aName;
    
    @Override
    public String toString() {
    	return this.description();
    }

    
	@Override
    public String description() {
    	return String.format("%s\n%s%.2f$", getName(), extraInformation(), getPrice());
    }
	
	protected abstract String extraInformation();

	public String getName() {
		return aName;
	};
	
	public void changeSpecial() {
    	onSpecial = ! onSpecial;
    };

	public double getPrice() {
		if(onSpecial) {
			return NORMALPRICE * DISCOUNT;
		} else {
			return NORMALPRICE;
		}
	}

	public abstract void removeItem(AbstractMenuItemSource menuItem);

	public abstract ArrayList<DietCategory> getDietCategories();

	public abstract FoodType getFoodType();

	protected double getNormalPrice() {
		return NORMALPRICE;
	}
	
}
