package code;

import java.util.ArrayList;
import java.util.HashMap;

//Playlist an ordered collection of songs
//A playlist have a name that can be changed after the playlist is created
//It is possible for client code to access all the songs in the playlist, as well as pre-determined aggregated information
//Pre-determined aggregated information are: number of songs, all the songs, and total playing time
class Playlist implements ISongList, Playable, Filtereable {
	private String aName;
	private ArrayList<Song> aSongList = new ArrayList<Song>();
	private HashMap<Integer, Song> allSongs = new HashMap<Integer, Song>();
	private int aTotalPlayingTime;
	private int aNumberSongs;

	// playlist constructor
	public Playlist(String pName) {
		assert pName != null;
		this.aName = pName;
		this.aTotalPlayingTime = 0;
		this.aNumberSongs = 0;
	}

	// aName is encapsulated because string objects are immutable (internal state
	// can't be changed after initialization)
	// therefore it can be shared through the two methods below.
	public void changeTitle(String pName) {
		assert pName != null;
		this.aName = pName;
	}

	// retrieves the name of the playlist of type String
	public String getTitle() {
		return aName;
	}

	// get the total number of songs from the playlist
	// checks if the song is valid, if so, the song is removed from the playlist
	public int getNumberOfSongs() {
		validateList();
		return this.aNumberSongs;
	}

	// adds a song object to the Arraylist of aSongList
	// validity does not need to checked here because it will be checked in the
	// Library class
	public void addSong(Song aSong) {
		assert aSong != null;
		this.aSongList.add(aSong);
		updateTotalPlayingTime();
	}

	// removes the song from the playlist by the filepath of the song
	// want to make sure that we are removing the song AND validating the
	// songs at the same time.
	public void deleteSong(Integer index) {
		assert 0 <= index && index < aSongList.size() - 1;
		validateList();
		aSongList.remove((int) index);
	}

	// get all the songs in the playlist
	// returns an Arraylist of Songe
	// checks if the song is valid, if so, the song is removed from the playlist
	/* return a copy of the song */
	public ArrayList<Song> getAllSongs() {
		validateList();
		return new ArrayList<>(aSongList);
	}

	// returns the total playing time of all the songs in the playlist
	// checks if the song is valid, if so, the song is removed from the playlist

	/* helper method convert string time to seconds */
	private int convertTime(String time) { /* 3:30 */
		String min = time.split(":")[0].replaceAll(" ", "");
		String sec = time.split(":")[1].replaceAll(" ", "");
		int min2 = Integer.parseInt(min) * 60;
		int sec2 = Integer.parseInt(sec);
		return min2 + sec2;
	}

	private void updateTotalPlayingTime() {
		validateList();
		this.aTotalPlayingTime = 0;
		for (Song song : aSongList) {
			this.aTotalPlayingTime += convertTime(song.getTime());
		}
	}

	public void validateList() {
		ArrayList<Song> songs = new ArrayList<Song>();
		for (Song song : aSongList) {
			if (song.isValidSong()) {
				songs.add(song);
			}
		}
		aNumberSongs = songs.size();
		aSongList = songs;
	}

	// returns the total playing time attribute
	public int getTotalPlayingTime() {
		updateTotalPlayingTime();
		return this.aTotalPlayingTime;
	}

	/*
	 * @pre index must not be out of range
	 */
	public Song getSong(Integer index) {
		assert index < aSongList.size() && index > 0;
		return aSongList.get(index);
	}

	@Override
	public void play() {
		System.out.println("==========================\nNow playing playlist '" + aName + "'." + "\nWith "
				+ aNumberSongs + " songs, this playlist is " + aTotalPlayingTime
				+ " seconds long.\n==========================");
		for (Song s : aSongList) {
			s.play();
		}
	}

	public void filterByTag(String tagName, String filter) {
		allSongs = Filter.convertListToHashMap(aSongList, allSongs);
		Filter.filterByTag(tagName, filter, allSongs);

	}

	public void songsShorterThan(String t) {
		allSongs = Filter.convertListToHashMap(aSongList, allSongs);

		Filter.songsShorterThan(t, allSongs);
	}

	public void songsLongerThan(String t) {
		allSongs = Filter.convertListToHashMap(aSongList, allSongs);

		Filter.songsLongerThan(t, allSongs);

	}

	public void songsofExactlength(String t) {
		allSongs = Filter.convertListToHashMap(aSongList, allSongs);

		Filter.songsofExactlength(t, allSongs);

	}

	public void titleStartwith(String pattern) {
		allSongs = Filter.convertListToHashMap(aSongList, allSongs);

		Filter.titleStartwith(pattern, allSongs);

	}

	public void titleContains(String pattern) {
		allSongs = Filter.convertListToHashMap(aSongList, allSongs);

	}
}
