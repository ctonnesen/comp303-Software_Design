package activity3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.naming.NameAlreadyBoundException;
import javax.naming.NameNotFoundException;

/**
 * Represents the musician or musical formation
 * that performed the song.
 *
 * Flyweight implementation of Artist.
 */
public class Artist
{
	private final String aName;
	private final Optional<List<String>> aAlternativeNames;

	// static flyweight store of artists, retrievable by name or alt name
	private static final Map<String, Artist> aArtists = new HashMap<>();

	// NULL object design pattern implementation
	public static final Artist NULL = new Artist()
		{
			@Override
	        public boolean isNull()
			{
				return true;
			}

			@Override
			public String toString()
			{
				return "This is the NULL Artist.";
			}
        };

    /**
     * Constructor for NULL design pattern implementation
     */
    private Artist()
    {
    	aName = null;
    	aAlternativeNames = null;
    }

	/**
	 * @param pName artist name
	 * @pre pName != null
	 */
	private Artist(String pName)
	{
		assert pName != null;
		aName = pName;
		aAlternativeNames = Optional.empty();
	}

	/**
	 * @param pName artist name
	 * @param pAlternativeNames list of alternative names for the artist
	 * @pre pName != null && pAlternativeNames != null
	 */
	private Artist(String pName, List<String> pAlternativeNames)
	{
		assert pName != null && pAlternativeNames != null;
		aName = pName;
		aAlternativeNames = Optional.of(deepCopy(pAlternativeNames));
	}

	/**
	 * Returns a flyweight artist, creating a new one if it does not exist
	 *
	 * @param pName artist name
	 * @pre pName != null
	 */
	public static Artist get(String pName)
	{
		assert pName != null;
		if (!nameTaken(pName))
		{
			System.out.println("Adding " + pName + " as a new artist.");
			aArtists.put(pName, new Artist(pName));
		}
		return aArtists.get(pName);
	}

	/**
	 * Adds a new artist if name is not taken and none of the alt names are taken. Unlike single param get(),
	 * it is assumed the user would only use this get() to create a new artist with its alt names, so it throws
	 * an exception if a used name is supplied. Cannot add an empty list of alt names. None of
	 * the names in the list can be null
	 *
	 * @param pName artist name
	 * @param pAlternativeNames alternative names of artist
	 * @throw NameAlreadyBoundException if supplied with existing artist name or alt name
	 * @pre pName != null && pAlternativeNames != null && pAlternativeNames.size() != 0;
	 * @pre pAlternativeNames does not contain any null values
	 */
	public static Artist get(String pName, List<String> pAlternativeNames) throws NameAlreadyBoundException
	{
		assert pName != null && pAlternativeNames != null && pAlternativeNames.size() != 0;
		String nameTaken = nameTaken(pName, pAlternativeNames);
		if (nameTaken != null)
		{
			throw new NameAlreadyBoundException("There is already an artist with the name " + nameTaken + ".");
		}
		System.out.println("Adding " + pName + " as a new artist.");
		aArtists.put(pName, new Artist(pName, pAlternativeNames));
		for (String altName : pAlternativeNames)
		{
			aArtists.put(altName, get(pName));
		}
		return get(pName);
	}
	
	/**
	 * Returns the artist with the first matching name from the list in the input.
	 *
	 * @param pPossibleNames possible names of artist to be retrieved
	 * @pre pPossibleNames != null && pPossibleNames.size() != 0;
	 * @pre pPossibleNames does not contain any null values
	 */
	public static Artist get(List<String> pPossibleNames) throws NameNotFoundException
	{
		assert pPossibleNames != null && pPossibleNames.size() != 0;
		String name = nameTaken(pPossibleNames);
		if (name != null)
		{
			System.out.println("Found the following artist with a matching name: " + get(name));
			return get(name);
		}
		else
		{
			throw new NameNotFoundException("There is no artist with any of the input names.");
		}
	}
	
	/**
	 * Get the name of an artist.
	 * 
	 * @pre !isNull()
	 */
	public String getName()
	{
		assert !isNull();
		return aName;
	}
	
	/**
	 * Get a list of all names for a given artist, starting with the artist name.
	 * 
	 * @pre !isNull()
	 */
	public List<String> getAllNames()
	{
		assert !isNull();
		if (aAlternativeNames.isEmpty())
		{
			return Arrays.asList(aName);
		}
		else
		{
			List<String> allNames = deepCopy(aAlternativeNames.get());
			allNames.add(0, aName);
			return allNames;
		}
	}

	/**
	 * Checks if an artist name is taken.
	 *
	 * @param pName artist name
	 * @pre pName != null
	 */
	private static boolean nameTaken(String pName)
	{
		assert pName != null;
		if (aArtists.containsKey(pName))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * If a name is taken, return the first name from the input parameters that is taken by some artist, else return null.
	 * 
	 * @param pPossibleNames possible names of artist
	 * @pre pPossibleNames != null && pPossibleNames.size() != 0;
	 * @pre pPossibleNames does not contain any null values
	 */
	private static String nameTaken(List<String> pPossibleNames)
	{
		assert pPossibleNames != null && pPossibleNames.size() != 0;
		for (String name : pPossibleNames)
		{
			assert name != null;
			if (nameTaken(name))
			{
				return name;
			}
		}
		return null;
	}
	
	/**
	 * If a name is taken, return the first name from the input parameters that is taken by some artist, else return null.
	 * 
	 * @param pName name of the artist
	 * @param pAlternativeNames possible names of artist
	 * @pre pName != null && pAlternativeNames != null && pAlternativeNames.size() != 0;
	 */
	private static String nameTaken(String pName, List<String> pAlternativeNames)
	{
		assert pName != null && pAlternativeNames != null && pAlternativeNames.size() != 0;
		if (nameTaken(pName))
		{
			return pName;
		}
		else 
		{
			return nameTaken(pAlternativeNames);
		}
	}

	/**
	 * Copies pAlternativeNames into a new array to keep aAlternativeNames immutable. If pAlternativeNames contains
	 * duplicate names, only a single new name is added
	 *
	 * @param pAlternativeNames artist alt names
	 * @pre pAlternativeNames != null
	 * @pre pAlternativeNames does not contain any null values
	 */
	private static List<String> deepCopy(List<String> pAlternativeNames)
	{
		assert pAlternativeNames != null;
		List<String> newAlternativeNames = new ArrayList<>();
		for (String name : pAlternativeNames)
		{
			assert name != null;
			if (!newAlternativeNames.contains(name))
			{
				newAlternativeNames.add(name);
			}
		}
		return newAlternativeNames;
	}

	/**
	 * NULL object design pattern implementation
	 */
	public boolean isNull()
	{
		return false;
	}

	/**
	 * Prints all artist names and alt names
	 */
	public static void print()
	{
		ArrayList<Artist> uniqueArtists = new ArrayList<Artist>();
		for (Artist artist : aArtists.values())
		{
			if (!uniqueArtists.contains(artist))
			{
				uniqueArtists.add(artist);
				System.out.println(artist);
			}
		}
	}

	public String toString()
	{
		if (aAlternativeNames.isEmpty())
		{
			return aName;
		}
		else
		{
			String nameAndAltNames = aName + " (alternative names: " + aAlternativeNames.get().get(0);
			for (int i = 1; i < aAlternativeNames.get().size(); i++)
			{
				nameAndAltNames = nameAndAltNames + ", " + aAlternativeNames.get().get(i);
			}
			return nameAndAltNames + ")";
		}
	}
}


