
package activity4;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.List;
import java.util.Objects;



/**
 * Represents the musician or musical formation that performed the song.
 */
public class Artist implements Cloneable
{

	private final String aName;
	private List<String> aAlternativeNames =new ArrayList<String>();; 
	private static final Artist NULL =  new Artist(null){

        @Override
        public boolean isNull() {
            return true;
        }

        @Override
        public String toString() {
            return "This is the NULL Artist.";
        }
    };


	public Artist(String pName)
	{
		aAlternativeNames = new ArrayList<>();
		aName = pName;

	}


	public Artist(String pName, ArrayList<String> pAlternativeNames)
	{
		assert pName != null && pAlternativeNames != null;
		aName = pName;
		aAlternativeNames = pAlternativeNames;
	}

	public String getName()
	{
		return aName;
	}

	/**
	 * 
	 * @return a deep copy of aAlternativeNames
	 */
	public List<String> getAlternativeNames()
	{
		List<String> answer = new ArrayList<>();
		for (String string : aAlternativeNames)
		{
			answer.add(string);
		}
		return answer;
	}


    public boolean isNull() {
        return false;
    }

    public Artist clone(){
        try {
            Artist clone = (Artist) super.clone();
            clone.aAlternativeNames.clear();
            for (String alternativeName : aAlternativeNames) {
            	clone.aAlternativeNames.add(alternativeName);
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            assert false;
            return null;
        }
    }
    
    public boolean equals(Object pObject)
   	{
   		if (pObject == null || pObject.getClass() != this.getClass())
   		{
   			return false;
   		}
   		else if (pObject == this)
   		{
   			return true;
   		}
   		else {
   			Artist artist = (Artist) pObject;
   			if (!this.getName().equals(artist.getName()))// check same name
   			{
   				return false;
   			}
   			else {
   				for (String s: artist.aAlternativeNames) {
   					if (this.aAlternativeNames.contains(s)) {
   						continue;
   					} else {
   						return false;
   					}
   				}
   				return true;
   			}
   		} 
   	}
       
       @Override
   	public int hashCode(){
       	return Objects.hash(aName);
       	
   	}



}
