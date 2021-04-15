package code;

import java.util.ArrayList;

public interface ISongList {
	/*
	 * An interface that will support the crud operations on lists(create, read, update, delete)
	 * It can be used to support:
	 *  - the operations on the list of Songs in an Album
	 *  - the operations on the list of Songs in a Playlist
	 *  - the operations on the list of Tags (Potentially)
	 */
	public void addSong(Song item);
	public void deleteSong(Integer identifier);
	public Song getSong(Integer identifier);
	public ArrayList<Song> getAllSongs(); // can be used to implement getAllSongs from Album/Playlist/Library, in which case it just needs to
	public String getTitle();
	public void changeTitle(String title);
	public void validateList();
}

