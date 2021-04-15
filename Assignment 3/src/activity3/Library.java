package activity3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Represents a collection of songs, playlists, and albums.
 */
public class Library implements Iterable <Song>
{
	private static final Library INSTANCE = new Library();
	
	private final Map<Integer, Song> aSongs = new HashMap<>();
	
	private final Map<Integer, Album> aAlbums = new HashMap<>();
	
	private final Map<Integer, Playlist> aPlaylists = new HashMap<>();
	
	private Library() {}
	
	/**public method to retrieve the Library instance
	 * 
	 * @return Library INSTANCE
	 */
	public static Library instance() {
		return INSTANCE;
	}
	
	/**
	 * Checks if library is empty
	 */
	public boolean isEmpty() {
		return aSongs.isEmpty();
	}	
	
	/**
	 * Adds song to library
	 *
	 * @param pSong
 	 * @pre pSong != null
	 */
	public void addSong(Song pSong) {
		assert pSong != null;
		if (songHashCode(pSong)!= 0){
			aSongs.put(songHashCode(pSong), pSong);
		}
		
	}
	
	/**
	 * Adds album to library if it is not empty
	 *
	 * @param pAlbum
 	 * @pre pAlbum != null
	 */
	public void addAlbum(Album pAlbum) {
		
		assert pAlbum != null;
		
		if (!(this.isEmpty()) && aAlbums.get(albumHashCode(pAlbum))==null) {
			aAlbums.put(albumHashCode(pAlbum), pAlbum);
		}
		
		else {
			System.out.println("Error add album to library");
		}
	}
	
	/**
	 * Adds playlist to library if it is not empty
	 *
	 * @param pPlaylist
 	 * @pre pPlaylist != null
	 */
	public void addPlaylist(Playlist pPlaylist) {
		assert pPlaylist != null;
		
		if (!(this.isEmpty()) && aPlaylists.get(playlistHashCode(pPlaylist))==null) {
			aPlaylists.put(playlistHashCode(pPlaylist), pPlaylist);
		} else {
			System.out.println("Error add Playlist to library");
		}
	}
	
	/** Generate song hash code
	 * 
	 * @param pSong
	 * @return int
	 * @pre pSong!=null
	 *
	 */
	private int songHashCode(Song pSong) {
		assert pSong!=null;	
		
		try {
			 pSong.getaArtist();
		}
		catch (Exception NoSuchElementException)
		{
			System.out.println( NoSuchElementException);
			return 0;
			
		}
		
		try {
			 pSong.getaTitle();
		}
		catch (Exception NoSuchElementException)
		{
			System.out.println(NoSuchElementException);
			return 0;
		}
		return pSong.getaTitle().hashCode()*pSong.getaArtist().hashCode();
	}
	
	/**Generate Playlist hash code
	 * Need to figure out how to make names unique 
	 * 
	 * @param pPlaylist
	 * @return int
	 * @pre pPlaylist!=null
	 *
	 */
	private int playlistHashCode(Playlist pPlaylist) {
		assert pPlaylist!=null;
		return pPlaylist.hashCode();
	}
	
	/** generate album hash code
	 * 
	 * @param pAlbum
	 * @return int
	 * @pre pAlbum!=null
	 */
	private int albumHashCode(Album pAlbum) {
		assert pAlbum!=null;
		return pAlbum.hashCode();
	}
	
	/** Backs up the Library to the database
	 * 
	 */
	public void backup() {
		
		//if library hasn't been backed up
		if(isBackedUp()) {
			Database.backup(INSTANCE);
		} else {
			System.out.println("No changes made since last backup");
		}
	}
	
	/**checks if there have been changes made to the aSaved value
	 * 
	 * @return boolean
	 */
	public boolean isBackedUp() {
		if(Database.compareLibrary(INSTANCE)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Iterator <Song> iterator(){
		return aSongs.values().iterator();
	}
	
	
	/**
	 * remove song from library if it is not empty
	 *
	 * @param pSong
	 * @pre pSong != null
	 */
	public void removeSong (Song pSong) {
		assert pSong!= null;
		assert !this.isEmpty();
	
		aSongs.remove(songHashCode(pSong));
	
	}

	/**
	 * remove playlist from library if it is not empty
	 *
	 *
	 * @param pPlaylist
	 * @pre pPlaylist != null
	 * @pre aPlaylists != null
	 * @pre this != null
	 */
	public void removePlaylist (Playlist pPlaylist, boolean ifRemoveSongs) {
		assert pPlaylist != null;
		assert aPlaylists.size() != 0;
		assert !this.isEmpty();
		
		aPlaylists.remove(playlistHashCode(pPlaylist));
		
		//removes songs from library if true 
		if(ifRemoveSongs) {
			for(Song s: pPlaylist) {
				andRemoveSongs(s);
			}
		}
	}
	
	/**
	 * remove album from library if it is not empty
	 *
	 * @param pAlbum
	 * @pre pAlbum != null
	 * @pre aAlbums != null
	 * @pre this != null
	 */
	public void removeAlbum(Album pAlbum, boolean ifRemoveSongs) {
		assert pAlbum != null;
		assert aAlbums.size() != 0;
		assert !this.isEmpty();
		
		aAlbums.remove(albumHashCode(pAlbum));
		
		//removes songs from library if true 
		if(ifRemoveSongs) {
			for(Song s: pAlbum) {
				andRemoveSongs(s);
			}
		}
	}
	/** removes a single song from a playlist
	 * 
	 * @param pPlaylist
	 * @param pSong
	 * @pre pPlaylist != null
	 * @pre pSongs != null
	 * @pre this != null
	 */
	public void removeSongFromPlaylist(Playlist pPlaylist, Song pSong) {
		assert pPlaylist!=null;
		assert pSong!=null;
		assert this.isEmpty();
		
		pPlaylist.removeSongPLaylist(pSong);
	}
	
	/** removes a single song from an album
	 * Calls method  removeSongAlbum from Album class
	 * 
	 * @param pAlbum
	 * @param pSong
	 * @pre pAlbum != null
	 * @pre pSongs != null
	 * @pre this != null
	 * 
	 */
	public void removeSongFromAlbum(Album pAlbum, Song pSong) {
		assert pAlbum!=null;
		assert pSong!=null;
		assert this.isEmpty();
		
		pAlbum.removeSongAlbum(pSong);
		
	}
	
	/** removes the song from anywhere in the library 
	 * Uses an iterator to avoid any concurrentmodificationexceptions
	 * 
	 * 
	 * @param pSong
	 * @pre pSong != null
	 * @pre this != null
	 */
	public void andRemoveSongs(Song pSong) {
		assert pSong!=null;
		assert !this.isEmpty();
		
		//removes the song from the library using an iterator
		Iterator<Integer> songIterator = aSongs.keySet().iterator();
		while(songIterator.hasNext()) {
			Song curSong = aSongs.get(songIterator.next());
			if(pSong.equals(curSong)) {
				songIterator.remove();
			}
		}
		
		//remove the song from other albums
		for(Album a: aAlbums.values()) {
			removeSongFromAlbum(a, pSong);
		}
		
		//remove the song from other playlists
		for(Playlist p: aPlaylists.values()) {
			removeSongFromPlaylist(p, pSong);
		}
	}


}
