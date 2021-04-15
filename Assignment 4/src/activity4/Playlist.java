package activity4;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a named list of songs.
 */
public class Playlist implements Playable
{
	private List<Playable> aPlayable = new ArrayList<Playable>();
	private String aTitle;
	private boolean isBackedUp = false;

	/**
	 * 
	 * @param pTitle
	 * @pre pTitle!=null
	 */
	public Playlist(String pTitle)
	{
		assert pTitle != null;
		aTitle = pTitle;
	}
	
	/**
	 * 
	 * @param pTitle
	 * @param pPlayables
	 * @pre pTitle != null && pPlayables != null
	 */
	public Playlist(String pTitle, Playable...pPlayables) {
		assert(pTitle != null && pPlayables != null);
		aTitle = pTitle;
		aPlayable = Arrays.asList(pPlayables);
	}



	public Playlist(String pTitle, List<Song> pSongs, List<Album> pAlbums, List<Playlist> pPlaylists) {
		aTitle = pTitle;

	}


	public Playlist clone(){
		try {
			Playlist clone = (Playlist) super.clone();
			clone.aPlayable = new ArrayList<>();
			for (Playable playable : aPlayable) {
				clone.aPlayable.add(playable.clone());
			}
			return clone;
		}
		catch(CloneNotSupportedException e) {
			assert false;
			return null;
		}
	}



	public void setTitle(String pTitle)
	{
		aTitle = pTitle;
	}

	public String getTitle()
	{
		return aTitle;
	}

	/**
	 * 
	 * @param pPlayables
	 */
	public void add(Playable... pPlayables)
	{
		for (Playable p : pPlayables)
		{
			if(p != Playable.NULL) {
				aPlayable.add(p);
			}		
		}
	}


	public void backup()
	{
		System.out.println("Backing up the library");
		isBackedUp = true;
	}

	@Override
	public void play()
	{
		System.out.println("Playing the playlist " + aTitle);
	}

	@Override
	public int duration()
	{
		int time = 0;
		for (Playable playable : aPlayable)
		{
			time += playable.duration();
		}
		return time;
	}

	@Override
	public String description()
	{
		return String.format("[Title: %s ; Duration: %d]", this.getTitle(), this.duration());
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
		else {
			Playlist pPlaylist = (Playlist) pObject;
			if (!this.getTitle().equals(pPlaylist.getTitle()))// check same title
			{
				return false;
			}
			else if (this.aPlayable.equals(pPlaylist.aPlayable))
			{
				return true;

			} else 
			{
				return false;
			}

		}
	}
    
    @Override
	public int hashCode(){
    	return Objects.hash(aTitle);
  
	}

}
