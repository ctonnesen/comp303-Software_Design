package activity7;

/**
 * 
 * Defines the classes of foods that can be on the menu. 
 */

public enum FoodType {
    DRINK, SNACK, MAIN, COMBO, SIDE;

	/**
	 * Makes the first letter capitalized. 
	 */
    @Override
    public String toString() {
        String allLower = this.name().toLowerCase();
        return allLower.substring(0, 1).toUpperCase() + allLower.substring(1);
    }
    // Above code is from here: https://attacomsian.com/blog/capitalize-first-letter-of-string-java
}
