package code;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Collections;
import java.util.Comparator;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

//Initializing library attributes outside of constructor

public class Library implements Iterable<Song>, Filtereable {

	private final HashMap<Integer, Album> aAlbums = new HashMap<>();
	private final HashMap<Integer, Playlist> aPlaylists = new HashMap<>();
	private final HashMap<String, Song> aSongs = new HashMap<>();
	private final AtomicLong idCounterPlaylist = new AtomicLong();
	private final AtomicLong idCounterAlbum = new AtomicLong();

	// for now the constructor will take nothing as we can
	// start with empty Library
	public Library() {
	}

	// unique id generator methods
	private Long createIDPlaylist() {
		return idCounterPlaylist.getAndIncrement();
	}

	private Long createIDAlbum() {
		return idCounterAlbum.getAndIncrement();
	}

	/**
	 * @pre the song must not be null.
	 */
	// add song with song constructor, return song path
	public void addSong(Song song) {
		assert song != null;
		aSongs.put(song.getFilePath(), song);
	}

	/**
	 * @pre the album added must not be null
	 */
	// add album with album constructor
	public void addAlbum(Album album) {
		assert album != null;
		aAlbums.put(this.createIDAlbum().intValue(), album);
	}

	/**
	 * @pre pName != null
	 */
	// add playlist with playlist constructor
	public void addPlayList(Playlist playlist) {
		assert playlist != null;
		aPlaylists.put(this.createIDPlaylist().intValue(), playlist);
	}

	// remove song, album or playlist by deleting key from Library
	/*
	 * @pre pFilepath must be a valid song filepath and contained in the library
	 */
	public void deleteSong(String pFilePath) {
		assert pFilePath != null && aSongs.containsKey(pFilePath);
		aSongs.remove(pFilePath);
	}

	/*
	 * @pre pPlaylistID must be the ID of an existing playlist created by user
	 */
	public void removePlayList(int pPlaylistID) {
		assert aPlaylists.containsKey(pPlaylistID);
		aPlaylists.remove(pPlaylistID);
	}

	/*
	 * @pre pAlbumID must be the ID of an existing album created by user
	 */
	public void removeAlbum(int pAlbumID) {
		assert aAlbums.containsKey(pAlbumID);
		aAlbums.remove(pAlbumID);
	}

	public HashMap<Integer, Song> getAllSongs() {
		return new HashMap(aSongs);
	}

	public HashMap<String, Song> getAllSongsReferencable() {
		return new HashMap<String, Song>(aSongs);
	}

	public HashMap<Integer, Album> getAllAlbums() {
		return new HashMap<Integer, Album>(aAlbums);
	}

	public HashMap<Integer, Playlist> getAllPlaylists() {
		return new HashMap<Integer, Playlist>(aPlaylists);
	}

	public void addSongToPlayList(Integer key, String songKey) {
		aPlaylists.get(key).addSong(aSongs.get(songKey));
	}

	/*
	 * returns int score of similiarity of songs if score >= 2 then songs are deemed
	 * similiar
	 * 
	 * @pre pSong1 != null && pSong2 != null
	 */
	private boolean similiarSongScore(Song pSong1, Song pSong2) {
		assert pSong1 != null && pSong2 != null;
		int score = 0;
		if ((pSong1.getArtist() != null) && (pSong2.getArtist() != null)) {
			if (pSong1.getArtist().equals(pSong2.getArtist()))
				score += 1;
		}
		try {
			if (pSong1.getCustomTag("GENRE").equalsIgnoreCase(pSong2.getCustomTag("GENRE")))
				score += 1;
		} catch (NoSuchFieldError e) {
		}
		try {
			int difference = Integer.parseInt(pSong1.getCustomTag("BPM"))
					- Integer.parseInt(pSong2.getCustomTag("BPM"));
			if (difference < 0)
				difference *= -1;
			if (difference <= 30)
				score += 1; // songs with similiar music tend to have a bpm difference <= 30
		} catch (NoSuchFieldError e) {
		}
		try {
			if (pSong1.getCustomTag("COMPOSER").equalsIgnoreCase(pSong2.getCustomTag("COMPOSER")))
				score += 1;
		} catch (NoSuchFieldError e) {
		}
		if (score >= 2)
			return true;
		return false;
	}

	/*
	 * generate a playlist that includes pSong and has songs similiar to pSong
	 * 
	 * @pre pSong != null
	 */
	public void PlaylistBySimiliarity(Song pSong) {
		assert pSong != null;
		Playlist similarTo = new Playlist("SimilarTo_(" + pSong.getTitle() + ")");
		for (Song song : aSongs.values()) {
			if (this.similiarSongScore(pSong, song)) {
				similarTo.addSong(song);
			}
		}
		if (similarTo.getNumberOfSongs() == 0 || similarTo.getNumberOfSongs() == 1) {// no songs similiar
			System.out.println("No Songs similiar to the song (" + pSong.getTitle() + ") in Library");
			System.out.println("Playlist Generation Failed");
		} else {
			System.out.println("Playlist with all songs of similiar to the song ( " + pSong.getTitle()
					+ ") was successfully generated with the name: " + "SimilarTo_(" + pSong.getTitle() + ")");
			System.out.println("Playlist with name " + "SimilarTo_(" + pSong.getTitle() + ") has "
					+ similarTo.getNumberOfSongs() + " songs in it");
			aPlaylists.put(this.createIDPlaylist().intValue(), similarTo);
			similarTo.play();
		}
	}

	/*
	 * generate Playlist all by same Artist
	 * 
	 * @pre pArtist != null
	 */
	public void PlaylistAllByArtist(String pArtist) {
		assert pArtist != null;
		pArtist = pArtist.toUpperCase();
		Playlist byArtist = new Playlist("All_by_" + pArtist);
		for (Song song : aSongs.values()) {
			if (song.getArtist() != null) {
				if (song.getArtist().toUpperCase().equals(pArtist)) {
					byArtist.addSong(song);
				}
			}
		}
		if (byArtist.getNumberOfSongs() == 0) {
			System.out.println("No Songs by " + pArtist + " in Library");
			System.out.println("Playlist Generation Failed");
		} else {
			System.out.println("Playlist with all songs of Genre " + pArtist
					+ " was successfully generated with the name: " + "All_by_" + pArtist);
			System.out.println("Playlist with name " + "All_by_" + pArtist + " has " + byArtist.getNumberOfSongs()
					+ " songs in it");
			aPlaylists.put(this.createIDPlaylist().intValue(), byArtist);
			byArtist.play();
		}
	}

	/*
	 * generate Playlist by Genres
	 * 
	 * @pre pGenre != null
	 */
	public void PlaylistByGenre(String pGenre) {
		assert pGenre != null;
		pGenre = pGenre.toUpperCase();
		Playlist byGenre = new Playlist("All_of_" + pGenre);
		for (Song song : aSongs.values()) {
			try {
				if (song.getCustomTag("GENRE").equals(pGenre)) {
					byGenre.addSong(song);
				}
			} catch (NoSuchFieldError e) {
				continue;
			}
		}
		if (byGenre.getNumberOfSongs() == 0) {
			System.out.println("No Songs with genre " + pGenre + " in Library");
			System.out.println("Playlist Generation Failed");
		} else {
			System.out.println("Playlist with all songs of Genre " + pGenre
					+ " was successfully generated with the name: " + "All_Of_" + pGenre);
			System.out.println(
					"Playlist with name " + "All_Of_" + pGenre + " has " + byGenre.getNumberOfSongs() + " songs in it");
			aPlaylists.put(this.createIDPlaylist().intValue(), byGenre);
			byGenre.play();
		}
	}

	/*
	 * @pre pPlaylistID must be the ID of an existing playlist created by user
	 */
	public void playPlaylist(int pPlaylistID) {
		assert aPlaylists.containsKey(pPlaylistID);
		aPlaylists.get(pPlaylistID).play();
	}

	public void playSong(String filePath) {
		assert aSongs.containsKey(filePath);
		aSongs.get(filePath).play();
	}

	public void playAlbum(int pAlbumID) {
		assert aAlbums.containsKey(pAlbumID);
		aAlbums.get(pAlbumID).play();
	}

	@Override
	public Iterator<Song> iterator() {
		// TODO Auto-generated method stub
		return new SongIterator((ArrayList<Song>) aSongs.clone());
	}

//	}
	/*
	 * This is method import wav audio files from a directory to Library. it assumes
	 * the audio file are stored as "TitleXXXX-AuthorAAA.wav" in the OS. in real
	 * life, you will use external lib to working with audio metadata
	 */
	public ArrayList<String> importSongsToLib(String dirpath) throws IOException, UnsupportedAudioFileException {
		ArrayList<String> songKeys = new ArrayList<>();
		File myFile = new File(dirpath);
		if (!myFile.exists()) {
			throw new IOException();
		}
		for (File file : myFile.listFiles()) {
			if (file.getName().endsWith(".wav")) {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
				AudioFormat format = audioInputStream.getFormat();
				long length = audioInputStream.getFrameLength();
				int lengthInSeconds = (int) Math.round((length + 0.0) / format.getFrameRate());
				Song song = new Song(file.getPath(), file.getName().split("-")[0].replaceAll("\\s+", ""),
						file.getName().split("-")[1].split(".wav")[0].replaceAll("\\s+", ""),
						secondsToMinutes(lengthInSeconds, 60));
				addSong(song);
				try {
					if (!aSongs.containsKey(song.getFilePath())) {
						throw new Exception(
								"Error importing Song" + ", song " + song.getFilePath() + " does not exist");
					}
					songKeys.add(song.getFilePath());
				} catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
					/* wrong tile, program continue */
				} catch (DuplicateFormatFlagsException duplicateFormatFlagsException) {
					/* duplicate files, program continue */
					System.out.println("Duplicated Song.");
				} catch (Exception e) {
					System.out.println("Error importing Song");
				}
			}
		}
		return songKeys;
	}

	/* helper method convert seconds to Minute */
	private String secondsToMinutes(int sec, int minuteToSecond) {
		int s = sec % minuteToSecond;
		int m = sec / minuteToSecond;
		return m + ":" + s;
	}

	public void printLibSongs(boolean ifdetailed, ArrayList<String> songKeys) {
		System.out.println("Currently there are " + songKeys.size() + " songs in your library.");
		int counter = 0;
		for (String key : songKeys) {
			System.out
					.println("========================================================" + "\nSong " + counter++ + ":");
			if (ifdetailed) {
				System.out.println(aSongs.get(key));
			} else {
				System.out.println(aSongs.get(key).printSongInOneLine());
			}
		}
	}

	public void updateLib() {
		for (Song s : aSongs.values()) {
			if (!s.isValidSong()) {
				deleteSong(s.getFilePath());
				System.out.println("Find song with invalid path. Deleted.");
			}
		}
	}

	/* print method */
	public void printAllPlaylists(ArrayList<Integer> keys) {
		int counter = 0;
		for (Integer key : keys) {
			System.out.println("Playlist " + counter + " : " + aPlaylists.get(key).getTitle());
			counter++;
		}
		if (counter == 0) {
			System.out.println("There is no playlist.");
		}
	}

	public void printAllAlbums(ArrayList<Integer> keys) {
		int counter = 0;
		for (Integer i : keys) {
			System.out.println("Album " + counter + " : " + aAlbums.get(i).getTitle());
			counter++;
		}
		if (counter == 0) {
			System.out.println("There is no album.");
		}
	}

	// Adds a playlist to the playlists ArrayList and returns an instance of
	// the playlist to the client.
	public Playlist makePlaylist(String title) {
		for (Playlist tmp : aPlaylists.values()) {
			if (tmp.getTitle().equals(title)) {
				throw new IllegalArgumentException("\nERROR: Playlist with name " + title + " already exist.");
			}
		}
		Playlist p = new Playlist(title);
		addPlayList(p);
		return p;
	}

	// Adds an album to the albums Arraylist and returns an instance of the
	// album to the client.
	public Album makeAlbum(String title, String artist) {
		for (Album tmp : aAlbums.values()) {
			if (tmp.getTitle().equals(title)) {
				throw new IllegalArgumentException("\nERROR: Album with name " + title + " already exist.");
			}
		}
		Album a = new Album(title, artist);
		addAlbum(a);
		return a;
	}

	/*
	 * Add a song to an album
	 * 
	 * @pre - the album id must exist and the filepath must be a song in the library
	 */
	public void addSongToAlbumWithTrackNumber(Integer id, String filePath, int trackNumber) {
		assert aAlbums.containsKey(id) && aSongs.containsKey(filePath);
		aAlbums.get(id).addSongWithTrackNumber(trackNumber, aSongs.get(filePath));
	}

	/* Returns an arraylist of songIDs sorted by song Name */
	public ArrayList<String> getSortedSongKeysBySongName() {
		// Sort songs
		ArrayList<Song> songsToSort = new ArrayList<>(aSongs.values());
		Comparator<Song> byNameComparator = new ByNameComparator();
		Collections.sort(songsToSort, byNameComparator);
		// Create and return ArrayList of sorted String Keys
		ArrayList<String> sortedKeys = new ArrayList<String>();
		for (Song song : songsToSort) {
			sortedKeys.add(song.getFilePath());
		}
		return sortedKeys;
	}

	public void removeTrackFromAlbum(Integer albumKey, int trackNumber) {
		aAlbums.get(albumKey).deleteSong(trackNumber);
	}

	// Returns an arraylist of songIDs sorted by song length.
	public ArrayList<String> getSortedSongKeysBySongLength() {
		// Sort songs
		ArrayList<Song> songsToSort = new ArrayList<>(aSongs.values());
		Comparator<Song> byLengthComparator = new ByLengthComparator();
		Collections.sort(songsToSort, byLengthComparator);
		// Create and return ArrayList of sorted String Keys(Song path)
		ArrayList<String> sortedKeys = new ArrayList<String>();
		for (Song song : songsToSort) {
			sortedKeys.add(song.getFilePath());
		}
		return sortedKeys;
	}

	// Returns an arraylist of songIDs sorted by song length.
	public ArrayList<String> getSortedSongKeysBySongArtist() {
		// Sort songs
		ArrayList<Song> songsToSort = new ArrayList<>(aSongs.values());
		Comparator<Song> byArtistComparator = new ByArtistComparator();
		Collections.sort(songsToSort, byArtistComparator);
		// Create and return ArrayList of sorted String Keys(Song path)
		ArrayList<String> sortedKeys = new ArrayList<String>();
		for (Song song : songsToSort) {
			sortedKeys.add(song.getFilePath());
		}
		return sortedKeys;
	}

	public ArrayList<String> sort(String[] userSortFields) {
		ArrayList<Song> songsToSort = new ArrayList<>(aSongs.values());
		ArrayList<Comparator<Song>> comparators = new ArrayList<>();
		for (String s : userSortFields) {
			comparators.add(new Comparator<Song>() {
				public int compare(Song song1, Song song2) {
					if (s.equals("TIME")) {
						/* Since time is a string like 0:33, we need to trim it before parse to int */
						int time1 = Integer.parseInt(song1.getTime().replace(":", ""));
						int time2 = Integer.parseInt(song2.getTime().replace(":", ""));
						return time1 - time2;
					} else if (s.equals("ARTIST")) {
						return song1.getArtist().toUpperCase().compareTo(song2.getArtist().toUpperCase());
					} else if (s.equals("TITLE")) {
						return song1.getTitle().toUpperCase().compareTo(song2.getTitle().toUpperCase());
					}
					String tag1Value = "UNKNOWNTAG";
					String tag2Value = "UNKNOWNTAG";
					int readNumber = 0;
					try {
						tag1Value = song1.getCustomTag(s);
						readNumber++;
						tag2Value = song2.getCustomTag(s);
						readNumber++;
						if (s.equals("BPM")) {
							return Integer.parseInt(tag1Value) - Integer.parseInt(tag2Value);
						} else
							return tag1Value.toUpperCase().compareTo(tag2Value.toUpperCase());
					} catch (NoSuchFieldError err) {
						if (tag1Value.equals("UNKNOWNTAG")) { /* if song1 doesnot have this field */
							try {
								tag2Value = song2.getCustomTag(s); /* check song2 */
								return 666; /* song2 has value, so song1 < song2 */
							} catch (NoSuchFieldError err2) {
								return 0; /* song1 and song2 both are empty, they are equal */
							}
						} else { /* song1 has value, song2 doesnt */
							return -1; /* song1 > song2 */
						}
					}
				}
			});
		}
		Collections.sort(songsToSort, new MultiComparator<Song>(comparators));
		ArrayList<String> sortedKeys = new ArrayList<String>();
		for (Song song : songsToSort) {
			sortedKeys.add(song.getFilePath());
		}
		return sortedKeys;
	}

	public void filterAlbumBy(Integer key, String tagName, String filter){
		aAlbums.get(key).filterByTag(tagName,filter);
	}
	public void filterPlaylistBy(Integer key, String tagName, String filter){
		aPlaylists.get(key).filterByTag(tagName,filter);
	}
	@Override
	public void filterByTag(String tagName, String filter) {

		Filter.filterByTag(tagName, filter, aSongs);

	}

	@Override
	public void songsShorterThan(String t) {

		Filter.songsShorterThan(t, aSongs);

	}

	@Override
	public void songsLongerThan(String t) {
		Filter.songsLongerThan(t, aSongs);

	}

	@Override
	public void songsofExactlength(String t) {
		Filter.songsofExactlength(t, aSongs);

	}

	@Override
	public void titleStartwith(String pattern) {
		Filter.titleStartwith(pattern, aSongs);

	}

	@Override
	public void titleContains(String pattern) {
		Filter.titleContains(pattern, aSongs);

	}

}
