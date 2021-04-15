package activity3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a musical genre, for example classical or 
 * jazz.
 */
public class Genre
{
	private final String aName;
	private final List<String> aSynonyms;
	
	//fly weight state space
	private static Map<String, Genre> aGenres = new HashMap<>();
	
	public static final Genre NULL = new Genre()
	{
		@Override
        public boolean isNull()
		{
			return true;
		}

		@Override
		public String toString()
		{
			return "This is an Unknown/Null Genre";
		}
    };

	
	private Genre() {
		aName = null;
		aSynonyms = null;
	}
	
	/**
	 * @param pName Genre name
	 * @pre pName != null
	 */
	private Genre(String pName) {
		assert pName != null;
		aName = pName;
		aSynonyms = new ArrayList<>();
	}
	
	public static Genre get(String name) {
		assert name!=null;
		// if genre not already exist
		if (!nameUsed(name)) {
			aGenres.put(name, new Genre(name));
		}
		return aGenres.get(name);
	}
	
	/**
	 * @param pName Genre name
	 * @param Syno a synonym for genre
	 * @pre pName != null && Syno != null
	 */
	private Genre(String pName, List<String> Syno) {
		assert pName != null && Syno != null;
		aName = pName;
		aSynonyms = Syno;
		
	}
	
	
	public static Genre get(String name, List<String> Syno) {
		assert name != null && Syno !=null && Syno.size()>0;
		
		if (!nameUsed(name)) {
			aGenres.put(name, new Genre(name, Syno));
			for (String a : Syno) {
				if (!nameUsed(a)) {
					aGenres.put(a, get(name));
				}
			}
			return aGenres.get(name);
			
			//if the same name, check if synonyms are also same; if not create a new Genre 
		}else if (nameUsed(name) && get(name).difference(Syno).size()>0) {
			
			String newName = get(name).difference(Syno).remove(0);
			aGenres.put(newName, new Genre(newName, Syno));
			for (String a : Syno) {
				aGenres.put(a, get(newName));
			}
			return aGenres.get(newName);
			
		}else { // same name and synonyms
			return aGenres.get(name);
		}
	
	}
	
	
	
	private List<String> difference(List<String> syno)
	{
		assert syno != null;
		List<String> notTaken = new ArrayList<>();
		for(String a : syno) {
			if (!aSynonyms.contains(a) && !nameUsed(a)) {
				notTaken.add(a);
			}
		}
		
		return notTaken;
	}

	private static boolean nameUsed (String name) {
		return aGenres.containsKey(name);
	}

	
	public List<List<String>> getAllNames()
	{
		assert !isNull();
		if (aSynonyms.isEmpty())
		{
			return  Collections.singletonList(Arrays.asList(aName));
		}
		else
		{
			List<String> allNames = deepCopy(aSynonyms);
			
			
			if(!aName.equals(allNames.get(0))) {
			
				allNames.add(0, aName);
			}
			
			
			return Collections.singletonList(allNames);
			
		}
	}
	
	private List<String> deepCopy(List<String> aSynonyms2)
	{
	
		assert aSynonyms != null;
		List<String> newSyno = new ArrayList<>();
		for (String name : aSynonyms)
		{
			assert name != null;
			if (!newSyno.contains(name))
			{
				newSyno.add(name);
			}
		}
			return newSyno;
	}

	public boolean isNull() {
		return false;
	}
	
	public String toString() {
		
		if (aSynonyms.isEmpty())
		{
			return aName;
		}
		else
		{	String all = aName + ", ";
			for (String a : aSynonyms) {
				all += a + ", ";
			}
			return all;
		}
	}
}
