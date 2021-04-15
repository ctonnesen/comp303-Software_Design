package activity7;


/**
 * 
 * Defines the sizes relevant for the SizedMenuItem
 */
public enum Size
{
    SMALL, MEDIUM, LARGE; //The three food sizes

    @Override
    public String toString(){ //Capitalizes the first letter of the name. 
        String allLower = this.name().toLowerCase();
        return allLower.substring(0,1).toUpperCase() + allLower.substring(1);
    }
    // Above code is from here: https://attacomsian.com/blog/capitalize-first-letter-of-string-java
}
