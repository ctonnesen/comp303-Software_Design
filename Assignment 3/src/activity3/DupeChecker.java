package activity3;

/**
 * An interface to check whether two objects are duplicates of each other.
 * Use the isDupe method provided by the interface to check.
 * 
 * This is used over overriding the equals method in a class since we need to have
 * multiple definitions of equality.
 *
 * @param <T> The type of object to compare.
 */
public interface DupeChecker<T>
{
	/**
	 * Checks whether the two objects are duplicates.
	 * @param pO1
	 * @param pO2
	 * @return true if they are duplicates, false otherwise.
	 */
	public boolean isDupe(T pO1, T pO2);
}
