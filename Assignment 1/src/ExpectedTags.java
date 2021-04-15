import java.util.HashMap;


public class ExpectedTags {
	private static final String title = "title";
	private static final String artist = "artist";
	private static final String time = "time";
	HashMap <String, Object> expectedMap = new HashMap <String, Object>();

	/**
	 *@param pTitle
	 *@param pArtists
	 *@param pTime
	 *@pre pTitle !=null && pArtists !=null && pTime !=null
	 */ 
	
	public ExpectedTags(String pTitle, String pArtists, TotalTime pTime){
		assert pTitle !=null && pArtists !=null && pTime !=null;
		expectedMap.put(title, pTitle);
		expectedMap.put(artist, pArtists);
		expectedMap.put(time, pTime);
	}
    public ExpectedTags(ExpectedTags aTag) {
		assert aTag != null;//asserts if its null
		expectedMap.put(title, aTag.getTitle());//adds the new title
		expectedMap.put(artist, aTag.getArtist());//artist
		expectedMap.put(time, new TotalTime(aTag.getTime()));//and time to make a copy
	}
	public String getTitle(){
		return (String) expectedMap.get(title);
	}

	public String getArtist(){
		return (String) expectedMap.get(artist);
	}

	public TotalTime getTime(){
		return new TotalTime((TotalTime) expectedMap.get(time));
	}
	public void setTitle(String newTitle){
		assert newTitle != null;
		expectedMap.put(title, newTitle);
	}

	public void setArtist(String newArtist){
		assert newArtist != null;
		expectedMap.put(artist, newArtist);
	}

	public void setTime(TotalTime newTime){
		assert newTime != null;
		expectedMap.put(time, newTime);
	}
	
	public void addExpectedTag(String tagName, Object pObject) {
		if (tagName.toLowerCase().equals(title)) {
			setTitle((String) pObject);
			return;
		}
		if (tagName.toLowerCase().equals(artist)){
			setArtist((String) pObject);
			return;
		}
		if (tagName.toLowerCase().equals(time)) {
			setTime((TotalTime) pObject);
			return;
		} else {
			throw new IllegalArgumentException ("The provided Tag Name does not match any predetermined expected tag names");
		}
	}
	
	
	public String toString(){
		TotalTime placeHold = (TotalTime) expectedMap.get(time);
		return "Title: " + (String) expectedMap.get(title) + "\nArtists: " + (String) expectedMap.get(artist) + " \nSong Length:  "  + placeHold.getMinutesAndSecondsTime();
	}
}
