package activity4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a musical genre, for example classical or jazz.
 */
public class Genre 
{
	private final String aName;
	private static final Map<String, Genre> aGenres = new HashMap<String, Genre>();
	private final List<String> aSynonyms;
	private static final HashMap<String, String> main_alt_pairs = new HashMap<String, String>();// store the map between
																								// alternative name and
																								// main name

	// first constructor of genre, input only name
	private Genre(String pName)
	{
		assert pName != null;
		this.aName = pName;
		this.aSynonyms = new ArrayList<>();
		aGenres.put(pName, this);// put it in the main_name-object map
	}

	// second constructor of genre, input name and synonyms
	private Genre(String pName, List<String> synonyms)
	{
		assert pName != null && synonyms != null;
		this.aName = pName;
		this.aSynonyms = synonyms;
		aGenres.put(pName, this);// put it in the main_name-object map
		for (String s : synonyms)
		{// put all (alt_name,main_name) pairs in the field main_alt_pairs
			assert s != null;
			main_alt_pairs.put(s, pName);
		}
	}

	// factory method using first constructor
	// return a genre object if exist. If not exist, create one and return
	public static Genre getGenre(String pName)
	{
		assert pName != null;
		if (aGenres.containsKey(pName))
		{// check for all main name
			return aGenres.get(pName);
		}
		else if (main_alt_pairs.containsKey(pName))
		{// if not a main name, check for alternative names
			return aGenres.get(main_alt_pairs.get(pName));
		}
		else
		{// if pName is neither a main name or an alternative name, create a object with pName as main name

			return new Genre(pName);
		}
	}

	// factory method using second constructor
	public static Genre getGenre(String pName, List<String> synonyms)
	{// return a genre object if exist. If not exist, create one and return
		assert pName != null && synonyms != null;
		if (aGenres.containsKey(pName))
		{// check for all main name
			return aGenres.get(pName);
		}
		else if (main_alt_pairs.containsKey(pName))
		{// if not a main name, check for alternative names
			return aGenres.get(main_alt_pairs.get(pName));
		}
		else
		{// if pName is neither a main name or an alternative name, create a object with pName as main name and list of
			// synonyms

			return new Genre(pName, synonyms);
		}
	}

	public String getName()
	{
		return aName;
	}

	public List<String> getSynonyms()
	{
		return Collections.unmodifiableList(aSynonyms);// make a copy
	}

	public void addSynonyms(String psynonym)
	{
		assert psynonym != null && !aGenres.containsKey(psynonym) && !main_alt_pairs.containsKey(psynonym);
		// this makes sure that input name is neither an existing main name nor an existing alternative name
		aSynonyms.add(psynonym);
		main_alt_pairs.put(psynonym, aName);
	}


}
