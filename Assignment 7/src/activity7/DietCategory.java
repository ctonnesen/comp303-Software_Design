package activity7;

/**
 * Defines the dietary classes of an item. 
 */
public enum DietCategory
{
	VEGAN, VEGETARIAN, GLUTENFREE, ORGANIC;

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
