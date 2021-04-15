
public enum Genre
{
	CLASSICAL, JAZZ, POP, ROCK, COUNTRY;

	public String toString() {
		String enumString = this.name(); 
		return (enumString.substring(0, 1).toUpperCase() + enumString.substring(1).toLowerCase());
		//Capitalizing code from: https://stackoverflow.com/questions/3904579/how-to-capitalize-the-first-letter-of-a-string-in-java
	}
}
