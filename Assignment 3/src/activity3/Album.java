package activity3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Represents an album with a title (mandatory) and 
 * artist (can be absent). An album can be incomplete,
 * in the sense that some tracks can be missing.
 */
public class Album implements Iterable <Song>
{
	private String aTitle;
	private Artist aArtist;
	private Map<Integer, Song> aTracks;
	// Flyweights are not used for album duplicate checkers, since
	// Album checkers depend on the definition of Song equality.
	// To discuss.


	/*
		An anonymous class is used to extend Artist and allow the creation of a Null Object Pattern, which
		will always return true when isNull is called upon an instance of it
	 */

	public Album(String pTitle) {
		this(pTitle, Artist.NULL);
	}

	public Album (String pTitle, Artist pArtist) {
		aTitle = pTitle;
		aArtist = pArtist;
		aTracks = new HashMap<Integer, Song>();
	}


	/*
	The getter and setter for the Album title are standard given they are always going to have value
	 */

	public String getaTitle() {
		return aTitle;
	}

	/**
	 * @pre pTitle != null
	 */

	public void setaTitle(String pTitle) {
		assert aTitle != null;
		this.aTitle = aTitle;
	}

	/*
	The getter for Artist does a quick print statement to let the client know the Artist is empty before returning the attribute
	 */


	public Artist getaArtist() {
		if (aArtist.isNull()) {
			System.out.println("There is no artist name provided");
			return null;
		}
		return aArtist;
	}

	/**
	 * @pre aArtist != null
	 *
	 * The setter for aArtist functions as a normal setter
	 */

	public void setaArtist(Artist pArtist) {
		assert aArtist != null;
		this.aArtist = pArtist;
	}

	public void removeArtist() {
		aArtist = Artist.NULL;
	}


	/**
	 * Add a track to this album. If the track already exists,
	 * it is written over.
	 * 
	 * @param pNumber The track number. Must be greater than 0.
	 * @param pSong The song for this track. 
	 * @pre pNumber > 0 && pSong != null
	 */
	public void addTrack(int pNumber, Song pSong)
	{
		assert pNumber > 0 && pSong != null;
		aTracks.put(pNumber, pSong);
	}
	
	/**
	 * Creates an album duplicate checker - totally equal.
	 * @param pSongChecker The song checker that defines the equality between two songs.
	 * @return the album dupe checker.
	 * @pre pSongChecker != null
	 */
	public static DupeChecker<Album> createTotalDupeChecker(DupeChecker<Song> pSongChecker)
	{
		assert pSongChecker != null;
		return new DupeChecker<Album>() {
			
			/**
			 * Checks for duplicates.
			 * @pre pO1 != null && pO2 != null
			 */
			@Override
			public boolean isDupe(Album pO1, Album pO2)
			{
				assert pO1 != null && pO2 != null;
				Song s;
				
				if (!pO1.aTitle.equals(pO2.aTitle)) return false;
				
				if (!pO1.aArtist.equals(pO2.aArtist)) return false;
				
				if (pO1.aTracks.size() != pO2.aTracks.size()) return false;
				
				for (Entry<Integer, Song> e: pO1.aTracks.entrySet())
				{
					s = pO2.aTracks.get(e.getKey());
					if (s == null)
					{
						return false;
					}
					else
					{
						if (!pSongChecker.isDupe(s, e.getValue())) return false;
					}
				}
				return true;
			}

		};
	}
	
	/**
	 * Creates an album duplicate checker - content equal.
	 * @param pSongChecker The song checker that defines the equality between two songs.
	 * @return the album dupe checker.
	 */
	public static DupeChecker<Album> createContentDupeChecker(DupeChecker<Song> pSongChecker)
	{
		return new DupeChecker<Album>() {
			
			/**
			 * Checks for duplicates.
			 * @pre pO1 != null && pO2 != null
			 */
			@Override
			public boolean isDupe(Album pO1, Album pO2)
			{
				assert pO1 != null && pO2 != null;
				if (pO1.aTracks.size() != pO2.aTracks.size()) return false;
				
				for (Entry<Integer, Song> e: pO1.aTracks.entrySet())
				{
					if (!contains(pSongChecker, (List<Song>) pO2.aTracks.values(), e.getValue()))
					{
						return false;
					}
				}
				return true;
			}
			
			/**
			 * A private method for internal use by the class. The params will never be passed a null.
			 * @param pSongChecker
			 * @param pSongs
			 * @param pSong
			 * @return boolean whether or not pSong is a duplicate of a song in pSongs.
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

	public static void main(String[] args) {
		Album test1 = new Album("AlbumTitle1", Artist.NULL);
		Album test2 = new Album("AlbumTitle2", Artist.get("Name"));

		// The following should print out True and then False, as the first utilizes the Null Object Pattern

		System.out.println(test1.aArtist.isNull());
		System.out.println(test2.aArtist.isNull());
	}
	@Override
	public Iterator <Song> iterator(){
		return aTracks.values().iterator();
	}

	/**
	 * remove song from album if it is not empty
	 *
	 * @param pSong
	 * @pre pSong != null
	 * @pre aTracks != null
	 */
	protected void removeSongAlbum (Song pSong) {
		assert pSong!= null;
		assert aTracks.size() !=0;

		if (aTracks.containsValue(pSong)) {

			aTracks.remove(pSong.getFile(), pSong);
		}

	}
}
