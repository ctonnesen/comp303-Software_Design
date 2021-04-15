package activity4;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Song implements Playable
{
	private final File aFile;
	private Optional<String> aTitle;
	private Optional<Integer> aDuration;
	private Optional<Artist> aArtist;
	private Optional<String> aGenre;
	private Map<String, Optional<String>> aTags = new HashMap<>();
	
	/**
	 * 
	 * @param pFile
	 * @pre pFile != null
	 */
	public Song(File pFile){
		assert pFile != null;
		aFile = pFile;
	}
	
	public File getFile(){
		return aFile;
	}
	
	public void setTitle(String pTitle) {
		aTitle = Optional.ofNullable(pTitle);
	}
	
	public void setDuration(int pDuration) {
		aDuration = Optional.ofNullable(pDuration);
	}
	
	public void setArtist(Artist pArtist) {
		aArtist = Optional.ofNullable(pArtist);
	}
	
	public void setGenre(String pGenre) {
		aGenre = Optional.ofNullable(pGenre);
	}
	
	public String printDuration() {
		if(aDuration.equals(Optional.empty())){
			return "Not Available";
		} else {
			return "" + aDuration.get();
		}
	}

	public String getTitle() {
		if (aTitle.equals(Optional.empty())) {
			return "Not available";
		} else {
			return aTitle.get();
		}
	}
	
	public String printArtistName() {
		if (aArtist.equals(Optional.empty())) {
			return "Not available";
		} else {
			return aArtist.get().getName();
		}
	}
	
	public String getGenre() {
		if (aGenre.equals(Optional.empty())) {
			return "Not available";
		} else {
			return aGenre.get();
		}
	}
	
	public void editTag(String pTag, String pValue){
		aTags.put(pTag, Optional.ofNullable(pValue));
	}
	
	public boolean isValid(){
		return aFile.exists() && !aFile.isDirectory();
	}
	
	@Override
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
		else
		{
			return this.getFile().equals(((Song) pObject).getFile());
		}
	}
	




	
	@Override
	public int hashCode(){
		try{
			return aFile.getCanonicalFile().hashCode();
		}catch( IOException e ){
			return aFile.hashCode();
		}
	}
	
	@Override
	public void play(){
		System.out.println("Playing the song.");
	}

	@Override
	public int duration(){
		assert aDuration !=null;
		return aDuration.get();
	}

	@Override
	public String description(){
		return String.format("[File: %s ; Title: %s ; Artist: %s ; Genre: %s ; Duration: %s]", this.aFile, this.getTitle(), this.printArtistName(), this.getGenre(), this.printDuration());
	}

	@Override
	public Song clone(){
		try {
		Song clone = (Song) super.clone();
		clone.aTags = deepCopy();
		return clone;
		}
		catch(CloneNotSupportedException e) {
			assert false;
			return null;
		}
	}
	
	private HashMap<String, Optional<String>> deepCopy(){
		HashMap<String, Optional<String>> copiedMap = new HashMap<>();
		for (String tag : aTags.keySet()) {
			copiedMap.put(tag, aTags.get(tag));
		}
		return copiedMap;	
	}

}
