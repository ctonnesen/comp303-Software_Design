
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class Playlist {

	private TotalTime aTotalTime;
	private String aName;
	private ArrayList<Song> playList = new ArrayList<Song>();

	// constructor that initializes the the playlist.
	public Playlist(String pName) {
		assert pName != null;
		aName = new String(pName);
		playList = new ArrayList<Song>();
		aTotalTime = new TotalTime(0,0,0);
	}
	
	// constructor to create copy of playlist
	public Playlist(Playlist pPlaylist) {
		assert pPlaylist != null;
		aName = pPlaylist.aName;
		playList = new ArrayList<Song>(pPlaylist.playList);
		aTotalTime = new TotalTime(pPlaylist.aTotalTime);
	}

	/**
	 * @pre pName !=null
	 */
	public void setName(String pName) {
		assert pName != null;
		aName = pName;
	}
	public void sortingByTitle() {
		Collections.sort(this.playList, new Comparator<Song>() {

			@Override
			public int compare(Song o1, Song o2) {
				// TODO Auto-generated method stub
				return o1.getTitle().compareToIgnoreCase(o2.getTitle());
			}
			
		});
	}
	public void sortingByArtist() {
		Collections.sort(this.playList, new Comparator<Song>() {

			@Override
			public int compare(Song o1, Song o2) {
				// TODO Auto-generated method stub
				return o1.getExpectedTags().getArtist().compareToIgnoreCase(o2.getExpectedTags().getArtist());
			}
			
		});
	}
	public void sortingByLength() {
		Collections.sort(this.playList, new Comparator<Song>() {

			@Override
			public int compare(Song o1, Song o2) {
				// TODO Auto-generated method stub
				
				return o1.getTotalTime().convertToSeconds()-o2.getTotalTime().convertToSeconds();
			}
			
		});
	}
	
	/**
	 * @ return name of the playlist
	 */
	public String getName() {
		return aName;
	}
	
	public ArrayList<Song> getPlaylistSongs() {
		ArrayList<Song> clonedArray = new ArrayList<Song>();
		for (Song song : playList) {
			clonedArray.add(song);
		}
		return clonedArray;
	}

	/**
	 * @return duration of a playlist in hours, minutes and seconds
	 */
	public String convertTime() {
		return aName + "'s duration is " + aTotalTime.getHours() + ":" + aTotalTime.getMinutes() + ":"
				+ aTotalTime.getSeconds();
	}

	/**
	 * @pre pSongs !=null
	 * @return number of songs in a playlist
	 */
	public int totalNumberOfSongs(ArrayList<Song> pSongs) {
		assert pSongs != null;
		return pSongs.size();
	}

	// returning a copy of the playlist.
	public ArrayList<Song> getSongs() {
		//updating attribute to make sure no invalid songs reside in it.
		ArrayList<Song> songsInPlaylist = new ArrayList<Song>();

		for (Song song : this.playList) {
			songsInPlaylist.add(song);
		}

		return songsInPlaylist;
	}

	/**
	 * adds song to the playList.
	 * 
	 * @pre pSong !=null
	 */
	public void addSong(Song pSong) {
		assert pSong != null;
		if(!pSong.isValid()) {
			System.err.print("Invalid audio format");
		}
		else {
	
			playList.add(pSong);
			aTotalTime.addTime(pSong.getTotalTime());//adds to the totalTime of playlist
			this.sortingByTitle();//sorting algorithm by title to keep list sorted
		}
	}

	/**
	 * @pre pTitle !=null removes song in the playlist by song title
	 */
	public void removeSongByTitle(String pTitle) {
		assert pTitle != null;
		for (Song curSong : this.getSongs()) {
			if (curSong.getTitle().equals(pTitle))
					playList.remove(curSong);
					aTotalTime.subtractTime(curSong.getTotalTime());
		}
	}
	
	public TotalTime getTotalTime() {
		return new TotalTime(this.aTotalTime);
	}
}

