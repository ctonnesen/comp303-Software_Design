package activity4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents an album with a title (mandatory) and 
 * artist (can be absent). An album can be incomplete,
 * in the sense that some tracks can be missing.
 */
public class Album implements Playable
{
	private String aTitle;
	private Optional<Artist> aArtist = Optional.empty();
	private Map<Integer, Song> aTracks=new HashMap<>();
	
	/**
	 * 
	 * @param pTitle
	 * @pre pTitle != null 
	 */
	public Album(String pTitle) {
		assert pTitle != null;
		aTitle = pTitle;
	}
	
	/**
	 * 
	 * @param pTitle and pArtist
	 * to make artist unavailable, use Artist.NULL
	 * @pre pTitle != null 
	 */
	public Album(String pTitle, Artist pArtist)
	{
		assert pTitle != null && pArtist != null;
		setTitle(pTitle);
		aArtist = Optional.of(pArtist);

	}

	
	public void setTitle(String pTitle) {
		aTitle = pTitle;
	}
	
	public String getTitle() {
		return aTitle;
	}
	
	/**
	 * 
	 * @param pArtist
	 * to make artist unavailable, use Artist.NULL
	 */
	public void setArtist(Artist pArtist){
		assert pArtist != null;
		
		aArtist = Optional.of(pArtist);
	}
	
	public String printArtistName() {
		if (aArtist.equals(Optional.empty())) {
			return "Not available";
		} else {
			return aArtist.get().getName();
		}
	}
	
	
	/**
	 * Add a track to this album. If the track already exists,
	 * it is written over.
	 * 
	 * @param pNumber The track number. Must be greater than 0.
	 * @param pSong The song for this track. 
	 * @pre pNumber > 0 && pSong != null
	 */
	public void addTrack(int pNumber, Song pSong){
		assert pNumber > 0 && pSong != null;
		aTracks.put(pNumber, pSong);
	}
	
	@Override
	public void play(){
		System.out.println("Playing the album.");
		
	}


	@Override
	public int duration(){
		int time = 0;
		for (Song song : aTracks.values()) {
			time += song.duration();
		}
		return time;
	}

	@Override
	public String description(){
		return String.format("[Title: %s ; Artist: %s ; Duration: %d]", this.getTitle(), this.printArtistName(), this.duration());
	}
	
	public Map<Integer, Song> getTracks()// encapsulated
	{
		return new HashMap<Integer, Song>(this.aTracks);
	}

	
	public boolean equals(Object pObject)
	{
		if (pObject == null || pObject.getClass() != this.getClass())
		{
			return false;
		} else if (pObject == this)
		{
			return true;
		}
		else
		{
			Album pAlbum = (Album) pObject;
			if (!this.getTitle().equals(pAlbum.getTitle()) || !(this.printArtistName() == (pAlbum).printArtistName()))// check same title
			{
				return false;
			}
			
			else
			{
				return this.getTracks().equals(pAlbum.getTracks());
			}
		}

	}
	
	public int hashCode()
	{
		return Objects.hash(aTitle, aArtist, aTracks);
	}
	/**
	 * @return a list of songs in the album
	 */
	public List<Song> getSongs()// encapsulated
	{

		ArrayList<Song> copy = new ArrayList<>();
		for (Song s : aTracks.values())
		{
			copy.add(s);
		}
		return copy;
	}

	public Album clone(){
		try {
			Album clone = (Album) super.clone();
			clone.aTracks = deepCopy();
			return clone;
			
		}
		catch(CloneNotSupportedException e) {
			assert false;
			return null;
		}
	}

	private HashMap<Integer, Song> deepCopy() {
		HashMap<Integer, Song> copiedMap = new HashMap<Integer, Song>();
		for (Integer current : aTracks.keySet()) {
			copiedMap.put(current, aTracks.get(current));
		}
		return copiedMap;
	}

}
