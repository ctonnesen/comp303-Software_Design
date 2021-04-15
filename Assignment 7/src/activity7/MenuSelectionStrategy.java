package activity7;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public interface MenuSelectionStrategy {

    public static List<AbstractMenuItemSource> allItems(List<AbstractMenuItemSource> pItems){
        return pItems;
    }

    private static List<AbstractMenuItemSource> filterByFoodType(List<AbstractMenuItemSource> pItems, FoodType pFoodType){
        return pItems.stream().
                filter(item -> item.getFoodType() == pFoodType).
                collect(Collectors.toList());
    }
    
    private static List<AbstractMenuItemSource> filterByFoodType(List<AbstractMenuItemSource> pItems, FoodType pFoodType1, FoodType pFoodType2){
        return pItems.stream().
                filter(item -> item.getFoodType() == pFoodType1||item.getFoodType()==pFoodType2).
                collect(Collectors.toList());
    }

    private static List<AbstractMenuItemSource> filterByDietCategory(List<AbstractMenuItemSource> pItems, DietCategory pDietCategory){
        return pItems.stream().
                filter(item -> item.getDietCategories().contains(pDietCategory)).
                collect(Collectors.toList());
    }

    public static List<AbstractMenuItemSource> drinks(List<AbstractMenuItemSource> pItems){
        return filterByFoodType(pItems, FoodType.DRINK);
    }

    public static List<AbstractMenuItemSource> mains(List<AbstractMenuItemSource> pItems){
        return filterByFoodType(pItems, FoodType.MAIN);
    }

    public static List<AbstractMenuItemSource> snacks(List<AbstractMenuItemSource> pItems){
        return filterByFoodType(pItems, FoodType.SNACK);
    }

    public static List<AbstractMenuItemSource> sides(List<AbstractMenuItemSource> pItems){
        return filterByFoodType(pItems, FoodType.SIDE);
    }
    
    public static List<AbstractMenuItemSource> mainsAndCombos(List<AbstractMenuItemSource> pItems){
        return filterByFoodType(pItems, FoodType.MAIN,FoodType.COMBO);
    }
    
    

    public static List<AbstractMenuItemSource> vegetarian(List<AbstractMenuItemSource> pItems){
        return filterByDietCategory(pItems, DietCategory.VEGETARIAN);
    }

    public static List<AbstractMenuItemSource> vegan(List<AbstractMenuItemSource> pItems){
        return filterByDietCategory(pItems, DietCategory.VEGAN);
    }

    public static List<AbstractMenuItemSource> glutenFree(List<AbstractMenuItemSource> pItems){
        return filterByDietCategory(pItems, DietCategory.GLUTENFREE);
    }

    public static List<AbstractMenuItemSource> cheapest(List<AbstractMenuItemSource> pItems){
        //returns 10 cheapest
        return pItems.stream()
                .sorted(Comparator.comparingDouble(AbstractMenuItemSource::getPrice))
                .limit(10)
                .collect(Collectors.toList());
    }

    public static List<AbstractMenuItemSource> mostExpensive(List<AbstractMenuItemSource> pItems){
        //returns 10 most expensive
        return pItems.stream()
                .sorted(Comparator.comparingDouble(AbstractMenuItemSource::getPrice).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public static List<AbstractMenuItemSource> combo(List<AbstractMenuItemSource> pItems){
        return pItems.stream().
                filter(item -> item instanceof ComboMenuItem)
                .collect(Collectors.toList());
    }

}
