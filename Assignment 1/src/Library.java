import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Library {

	//list of albums, playlists and songs. Note that the album, playlist, and song objects have to be immutable.

	private List<Album> aAlbums = new ArrayList<Album>();
	private List<Playlist> aPlaylists = new ArrayList<Playlist>();
	private List<Song> aSongs = new ArrayList<Song>();

	public void addNewSong (File pFilePath, String pTitle, String pArtist, int seconds, int minutes) {
		Song newSong = new Song(pFilePath, pTitle, pArtist, seconds, minutes);
		aSongs.add(newSong);
	}

	public void addNewAlbum (String title) {
		Album newAlbum = new Album(title);
		aAlbums.add(newAlbum);
	}

	public void addNewPlaylist (String name) {
		Playlist newPlaylist = new Playlist(name);
		aPlaylists.add(newPlaylist);
	}

	public void addSongToAlbum (Song pSong, String title, int trackNumber) {
		this.checkForNullSongs();
		Album albumToFind = null;
		for (Album curAlbum: aAlbums) {
			if (curAlbum.getTitle().equals(title)) {
				albumToFind = curAlbum;
				break;
			}
		}
		if (albumToFind == null) {
			System.out.println("The album you wish to add the song to does not exist");
			return;
		}
		albumToFind.addSong(pSong, trackNumber);
	}

	public void addSongToPlayList (Song pSong, String title) {
		this.checkForNullSongs();
		Playlist playlistToFind = null;
		for (Playlist curPlay: aPlaylists) {
			if (curPlay.getName().equals(title)) {
				playlistToFind = curPlay;
				break;
			}
		}
		if (playlistToFind == null) {
			System.out.println("The playlist you wish to add the song to does not exist");
			return;
		}
		playlistToFind.addSong(pSong);
	}

	public void addOptionalTag (Song pSong, String tagName, Object pObject) {
		this.checkForNullSongs();
		pSong.getOptionalTags().addOptionalTag(tagName, pObject);
	}

	public void addCustomTag (Song pSong, String tagName, Object pObject) {
		this.checkForNullSongs();
		pSong.getCustomTags().setCustomTags(tagName, pObject);
	}

	public void changeExpectedTagValue (Song pSong, String tagName, Object pObject) {
		if (!pSong.isValid()) {
			this.checkForNullSongs();
			return;
		}
		pSong.getExpectedTags().addExpectedTag(tagName, pObject);
	}

	public void changeOptionalTagValue (Song pSong, String tagName, Object pObject) {
		this.checkForNullSongs();
		pSong.getOptionalTags().addOptionalTag(tagName, pObject);
	}

	public void changePlaylistName (Playlist pPlayList, String name) {
		pPlayList.setName(name);
	}

	public ArrayList<Song> accessPlaylistSongs (String title) {
		this.checkForNullSongs();
		Playlist playlistToFind = null;
		for (Playlist curPlay: aPlaylists) {
			if (curPlay.getName().equals(title)) {
				playlistToFind = curPlay;
				break;
			}
		}
		if (playlistToFind == null) {
			throw new IllegalArgumentException("The playlist you are looking for does not exist");
		}
		return playlistToFind.getPlaylistSongs();
	}

	public ArrayList<Object> accessPlayListInfo (String title) {
		this.checkForNullSongs();
		Playlist playlistToFind = null;
		for (Playlist curPlay: aPlaylists) {
			if (curPlay.getName().equals(title)) {
				playlistToFind = curPlay;
				break;
			}
		}
		if (playlistToFind == null) {
			throw new IllegalArgumentException("The playlist you wish to add the song to does not exist");
		}
		ArrayList<Object> playListInfo = new ArrayList<Object>();
		playListInfo.add(playlistToFind.getPlaylistSongs().size());
		playListInfo.add(playlistToFind.getTotalTime());
		return playListInfo;
	}

	public HashMap<Integer, SongWithTrack> accessAlbumSongs (String title) {
		this.checkForNullSongs();
		Album albumToFind = null;
		for (Album curPlay: aAlbums) {
			if (curPlay.getTitle().equals(title)) {
				albumToFind = curPlay;
				break;
			}
		}
		if (albumToFind == null) {
			throw new IllegalArgumentException("The album you are looking for does not exist");
		}
		return albumToFind.getAlbumList();
	}

	public void changeAlbumArtist (String oldAlbumTitle, String newAlbumTitle) {
		this.checkForNullSongs();
		Album albumToFind = null;
		for (Album curPlay: aAlbums) {
			if (curPlay.getTitle().equals(oldAlbumTitle)) {
				albumToFind = curPlay;
				break;
			}
		}
		if (albumToFind == null) {
			throw new IllegalArgumentException("The album you are looking for does not exist");
		}
		albumToFind.setTitle(newAlbumTitle);
	}

	public void checkForNullSongs() {
		ArrayList<String> invalidSongTitles = new ArrayList<String>();
		ArrayList<Song> validSongs = new ArrayList<Song>();
		for (Song curSong : aSongs) {
			if (!curSong.getFilePath().exists()) {
				invalidSongTitles.add(curSong.getTitle());
				continue;
			}
			validSongs.add(curSong);
		}
		aSongs = validSongs;
		for(Album curAlbum : aAlbums) {
			Iterator<Integer> iterator = curAlbum.getAlbumList().keySet().iterator();
			while (iterator.hasNext()) {
				String songNameString = curAlbum.getAlbumList().get((iterator.next())).getTitle();
				if (invalidSongTitles.contains(songNameString)) {
					iterator.remove();
				}
			}
		}
		for(Playlist curPlay : aPlaylists) {
			for (Song curSong : curPlay.getPlaylistSongs()) {
				if (invalidSongTitles.contains(curSong.getTitle())) {
					curPlay.removeSongByTitle(curSong.getTitle());
				}
			}
		}
	}

	public static void main (String args[]) throws IOException {
		Library library = new Library();
		File countrySong = null;
		File popSong = null;
		File rockSong = null;
		try{
			countrySong = File.createTempFile("countrysongfile", ".WAV");
			popSong = File.createTempFile("popsongfile", ".MP3");
			rockSong = File.createTempFile("rocksongfile", ".FLAC");
		}catch (Exception e) {
			System.out.println("Files could not be created");
		}
		File fakeLocation = new File("FakeLocale");
		library.addNewSong(countrySong, "Walk the Line", "Johhny Cash", 30, 2);
		library.addNewSong(popSong, "Modern Loneliness", "Lauv", 2, 4);
		library.addNewSong(rockSong, "We Will Rock You", "Queen", 14, 2);
		System.out.println("Songs in library currently:");
		for (Song curSong: library.aSongs) {
			System.out.println(curSong.getTitle());
		}
		System.out.println();
		System.out.println("Modern Loneliness File Format: " + library.aSongs.get(1).getFormat().toString());
		System.out.println();
		System.out.println("Adding Optional and Custom Tag to: " + library.aSongs.get(0).getTitle());
		library.addOptionalTag(library.aSongs.get(0), "genre", Genre.COUNTRY);
		library.addCustomTag(library.aSongs.get(0), "StarsOutOfFive", 5);
		System.out.println(library.aSongs.get(0).getTitle() + "'s Genre: " + library.aSongs.get(0).getOptionalTags().getGenre().toString());
		System.out.println(library.aSongs.get(0).getTitle() + "'s StarsOutOfFive: " + library.aSongs.get(0).getCustomTags().getCustomTag("StarsOutOfFive"));
		System.out.println();
		library.addNewAlbum("PopAndRock Album");
		library.addSongToAlbum(library.aSongs.get(1), "PopAndRock Album", 1);
		library.addSongToAlbum(library.aSongs.get(2), "PopAndRock Album", 3);		
		System.out.println("Song Stored in Album 'PopAndRock Album' with track number 3: " + library.accessAlbumSongs("PopAndRock Album").get(3).getTitle());
		System.out.println();
		System.out.println("The content of PopAndRock Album: ");
		for (Integer curSong : library.accessAlbumSongs("PopAndRock Album").keySet()) {
			System.out.println("Track Num: " + curSong + "        " + "Song Name: " + library.accessAlbumSongs("PopAndRock Album").get(curSong).getTitle());
		}
		System.out.println();
		library.addNewPlaylist("PopAndCountry Playlist");
		library.addSongToPlayList(library.aSongs.get(0), "PopAndCountry Playlist");
		library.addSongToPlayList(library.aSongs.get(1), "PopAndCountry Playlist");		
		System.out.println("The content of PopAndCountry PlayList: ");
		for (Song curSong : library.accessPlaylistSongs("PopAndCountry Playlist")) {
			System.out.println("Song Name: " + curSong.getTitle());
		}
		System.out.println();
		System.out.println();
		System.out.println("Removing file for: " + library.accessAlbumSongs("PopAndRock Album").get(1).getTitle());
		System.out.println();
		library.aSongs.get(1).setFilePathTester(fakeLocation);		
		library.checkForNullSongs();
		System.out.println();
		System.out.println("Songs in library after removal:");
		for (Song curSong: library.aSongs) {
			System.out.println(curSong.getTitle());
		}
		System.out.println();
		System.out.println("The content of PopAndRock Album: ");
		for (Integer curSong : library.accessAlbumSongs("PopAndRock Album").keySet()) {
			System.out.println("Track Num: " + curSong + "        " + "Song Name: " + library.accessAlbumSongs("PopAndRock Album").get(curSong).getTitle());
		}
		System.out.println();
		System.out.println("The content of PopAndCountry PlayList after removal: ");
		for (Song curSong : library.accessPlaylistSongs("PopAndCountry Playlist")) {
			System.out.println("Song Name: " + curSong.getTitle());
		}
	}

}
