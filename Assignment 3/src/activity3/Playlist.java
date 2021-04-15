package activity3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a named list of songs.
 */
public class Playlist implements Iterable <Song>
{
	private final List<Song> aSongs = new ArrayList<>();
	private String aTitle;
	// Flyweights are not used for playlist duplicate checkers, since
	// Playlist checkers depend on the definition of Song equality.
	// To discuss.
	
	public Playlist(String pTitle)
	{
		aTitle = pTitle;
	}
	
	public void setTitle(String pTitle)
	{
		aTitle = pTitle;
	}
	
	public String getTitle()
	{
		return aTitle;
	}
	
	public void add(Song pSong)
	{
		aSongs.add(pSong);
	}
	
	public void backup() 
	{
	    System.out.println("Backing up the library");
	}
	
	/**
	 * Creates a playlist duplicate checker - totally equal.
	 * @param pSongChecker The song checker that defines the equality between two songs.
	 * @return the playlist dupe checker.
	 * @pre pSongChecker != null
	 */
	public static DupeChecker<Playlist> createTotalDupeChecker(DupeChecker<Song> pSongChecker)
	{
		assert pSongChecker != null;
		return new DupeChecker<Playlist>() {
			
			/**
			 * Checks for duplicates.
			 * @pre pO1 != null && pO2 != null
			 */
			@Override
			public boolean isDupe(Playlist pO1, Playlist pO2)
			{
				assert pO1 != null && pO2 != null;
				
				if (!pO1.aTitle.equals(pO2.aTitle)) return false;
				
				if (pO1.aSongs.size() != pO2.aSongs.size()) return false;
				
				for (int i = 0; i < pO1.aSongs.size(); i++)
				{
					if (!pSongChecker.isDupe(pO1.aSongs.get(i), pO2.aSongs.get(i)))
					{
						return false;
					}
				}
				return true;
			}
			
		};
	}
	
	/**
	 * Creates a playlist duplicate checker - content equal.
	 * @param pSongChecker The song checker that defines the equality between two songs.
	 * @return the playlist dupe checker.
	 * @pre pSongChecker != null
	 */
	public static DupeChecker<Playlist> createContentDupeChecker(DupeChecker<Song> pSongChecker)
	{
		assert pSongChecker != null;
		return new DupeChecker<Playlist>() {
			
			/**
			 * Checks for duplicates.
			 * @pre pO1 != null && pO2 != null
			 */
			@Override
			public boolean isDupe(Playlist pO1, Playlist pO2)
			{
				assert pO1 != null && pO2 != null;
				
				if (pO1.aSongs.size() != pO2.aSongs.size()) return false;
				
				for (Song s: pO1.aSongs)
				{
					if (!contains(pSongChecker, pO2.aSongs, s))
					{
						return false;
					}
				}
				return true;
			}
			
			/**
			 * A private method that that class uses internally. Params cannot be null by definition.
			 * @param pSongChecker
			 * @param pSongs
			 * @param pSong
			 * @return boolean whether the pSong is a dupe of a song in pSongs.
			 */
			private boolean contains(DupeChecker<Song> pSongChecker, List<Song> pSongs, Song pSong)
			{
				for (Song s: pSongs)
				{
					if (pSongChecker.isDupe(s, pSong))
					{
						return true;
					}
				}
				return false;
			}
			
		};
	}

	
	@Override
	public Iterator <Song> iterator(){
		return aSongs.iterator();
	}
	
	/**
	 * remove song from album if it is not empty
	 *
	 * @param pSong
 	 * @pre pSong != null
 	 * @pre aSongs != null
	 */
	protected void removeSongPLaylist (Song pSong) {
		assert pSong!= null;
		assert aSongs.size() !=0;
		
		if (aSongs.contains(pSong)) {
			aSongs.remove(pSong);
		}
		
	}

}
