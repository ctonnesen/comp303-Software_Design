import java.io.File;
import java.util.*;
import java.util.Arrays;
public class Album {

	private String title;
	private String artist = null;
	// use client code access the album and get all track numbers 
	private TotalTime aTotalTime = new TotalTime(0,0);
	HashMap<Integer,SongWithTrack> album=new HashMap<Integer,SongWithTrack>();

	public Album(SongWithTrack[] input, String title) {
		assert input != null && title != null;
		aTotalTime = new TotalTime(0, 0);
		for (int i = 0; i < input.length; i++) {
			if(album.containsKey(input[i].getTrackNum())) {//checks if the track is already taken
				album.remove(input[i].getTrackNum());//removes the track 
				this.aTotalTime.subtractTime(input[i].getTotalTime());//subtracts its time adn then
				album.put(input[i].getTrackNum(),input[i]);//adds the song along with the time below
				this.aTotalTime.addTime(input[i].getTotalTime());
			}else {
				album.put(input[i].getTrackNum(),input[i]);//if it doesnt exist in the hashmap it just adds
				this.aTotalTime.addTime(input[i].getTotalTime());
			}

		}
		this.title = title;
	}

	public SongWithTrack getSongWithInt(Integer pTracknum) {
		return this.album.get(pTracknum);
	}
	
	//second constructor: artist presented

   public Album(SongWithTrack[] input,String title,String artist) { 
    assert input != null && title != null && artist != null;
    for (int i = 0; i < input.length; i++) {
     	if(album.containsKey(input[i].getTrackNum())) {//checks if the track is already taken
    		album.remove(input[i].getTrackNum());//removes the track 
    	    this.aTotalTime.subtractTime(input[i].getTotalTime());//subtracts its time adn then
    	    album.put(input[i].getTrackNum(),input[i]);//adds the song along with the time below
    		this.aTotalTime.addTime(input[i].getTotalTime());
    	}else {
    		album.put(input[i].getTrackNum(),input[i]);//if it doesnt exist in the hashmap it just adds
    		this.aTotalTime.addTime(input[i].getTotalTime());
    	}

    }
    this.title = title;
    this.artist = artist;
  }

	public Album(String pTitle) {
		title = pTitle;
	}
	
	public Album(String pTitle, String pArtist) {
		artist = pArtist;
		title = pTitle;
	}

	public SongWithTrack getSongTrack(Integer inputThree) {
		return this.album.get(inputThree);//return the songWithTrack
	}

	public String setTitle(String inputOne) {
		assert inputOne != null;//checks if the string is null 
		return this.title = inputOne;
	}

	public String setArtist(String inputTwo) {
		assert inputTwo != null;
		return artist = inputTwo;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public HashMap<Integer,SongWithTrack> getAlbumList(){
		return album;
	}


	public void addSong(Song pSong, int trackNum) {
		SongWithTrack input = new SongWithTrack(pSong, trackNum);
		if (!album.containsKey(input.getTrackNum())) {//checks if the song exists
			album.put(input.getTrackNum(), input);//adds the song and time to total time
			aTotalTime.addTime(input.getTotalTime());
		} else {//if it does exist it subtracts the time and adds new song with new trackNum
			aTotalTime.subtractTime(album.get(input.getTrackNum()).getTotalTime());
			album.put(input.getTrackNum(), input);
			aTotalTime.addTime(input.getTotalTime());
		}
	}
	

	public void removeByTitle(String pTitle) {
		assert pTitle != null;
		for (Integer curSong  : album.keySet()) {
			if (album.get(curSong).getTitle().equals(pTitle)) {
				this.removeSong(album.get(curSong));
			}
		}
	}
   // delete the song
  /**
    * 
    * @param input
    * @pre input != null;
    */
  public void removeSong(SongWithTrack input) {
    assert input != null;
    album.remove(input.getTrackNum());//removes the song and the time of the song from total time
    this.aTotalTime.subtractTime(input.getTotalTime());
  }

  public String getTotalTimeString() {
    return this.aTotalTime.getStringOfTimeWithHours();
  }

  public ArrayList<SongWithTrack> getAllSongs() {
    return new ArrayList<SongWithTrack>(this.album.values());
  }

  public TotalTime getTotalTime(){
    return new TotalTime(this.aTotalTime);
      }
}

