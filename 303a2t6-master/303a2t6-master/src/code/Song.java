package code;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

// make it inaccessible by clients
class Song implements Comparator<Song>, Playable {
	private final String aFilePath;
	// Expected tags implemented as attributes of a song - TAG IMPLEMENTATION 1/3
	private String aTitle;
	private String aArtist;
	private String aTime;
	// Optional tags implemented as enum keys in hashmap - TAG IMPLEMENTATION 2/3
	// Note that we CAN easily use OptionalTag enum objects as keys without a problem
	// indexing the hashmap since enum types are static
	//private final HashMap<OptionalTag, String> aOptionalTags = new HashMap<>();
	// Custom tags to be implemented using some CLASS as keys in hashmap - TAG IMPLEMENTATION 3/3 
	private final  HashMap<String, String> aCustomTags = new HashMap<>();
	// Can (and should) make Title and Artist classes instead, haven't yet 
	//use time library
	public Song(String pFilePath, String pTitle, String pArtist, String pTime)	{
		assert pFilePath != null && pTitle != null && pArtist != null && pTime != null;
		if (isValidCreate(pFilePath)&&(isValidExtension(pFilePath))) {
			aFilePath = pFilePath;
			aTitle = pTitle;
			aArtist = pArtist;
			aTime = pTime;
		} else {
			throw new InvalidPathException(pFilePath,"Song does not exist on the OS\n");
		}
	}
	
	public String getFilePath()	{
		return aFilePath;
	}
	
	//Setter for mandatory filed.
	public void setCustomTag(String pTagName, String pTagValue){
		assert pTagName != null && pTagValue != null;
		aCustomTags.put(pTagName, pTagValue);
	}
	
		
	public void setTitle(String pTitle) {
		assert pTitle != null;
		this.aTitle = pTitle;
	}
	public void setArtist(String pArtist) {
		assert pArtist != null;
		this.aArtist = pArtist;
	}

	/**
	   * @pre pTagType != null
	   * @param pTagType
	   */
	public  String getTagType(String NameTag) {
		if (NameTag.toLowerCase().equals("artist") || NameTag.toLowerCase().equals("title") ||  NameTag.toLowerCase().equals("time")) {
			return "expected";
		} else if (NameTag.toLowerCase().equals("bpm") || NameTag.toLowerCase().equals("genre") || NameTag.toLowerCase().equals("composer")){
			return "optional";
		} else {
			try{
				for (String s : aCustomTags.keySet() ) {
					if (s.toLowerCase().equals(NameTag.toLowerCase())) {
						return "custom";
					}
				}
				return "notfound";
			} catch (NoSuchFieldError noSuchFieldError){
				return "notfound";
			}
		}
	}

	
	/* Optional tags are stored in CustomTags as well,
	* However, since they will have a more frequent useage than custom-naming tags,
	* It is better to provide setters for these optional tags*/
	public void setBPM(String pBPM) {
		assert pBPM != null;
		aCustomTags.put(OptionalTag.BPM.name(),pBPM);
	}
	public void setComposer(String pComposer) {
		assert pComposer != null;
		pComposer = pComposer.toUpperCase();
		aCustomTags.put(OptionalTag.COMPOSER.name(),pComposer);
	}
	public void setGenre(String pGenre) {
		assert pGenre != null;
		pGenre = pGenre.toUpperCase();
		aCustomTags.put(OptionalTag.GENRE.name(),pGenre);
	}
	/*
	 * getters for optional tags, throw exceptions if notfound.
	 */
	public String getCustomTag(String pTagName) {
		assert pTagName != null;
		pTagName = pTagName.toUpperCase();
		if (aCustomTags.containsKey(pTagName)) {
			return aCustomTags.get(pTagName);
		} else {
			throw new NoSuchFieldError();
		}
	}
	
	
	public String getBPM() {
		if (aCustomTags.get(OptionalTag.BPM)!=null){
			return aCustomTags.get(OptionalTag.BPM);
		}
		throw new NoSuchFieldError();
	}
	public String getComposer() {
		if(aCustomTags.get(OptionalTag.COMPOSER)!=null){
			return aCustomTags.get(OptionalTag.COMPOSER);
		}
		throw new NoSuchFieldError();
	}
	public String getGenre() {
		if (aCustomTags.get(OptionalTag.GENRE)!=null){
			return aCustomTags.get(OptionalTag.GENRE);
		}
		throw new NoSuchFieldError();
	}

    public String getTitle() {
		return aTitle;
    }

	public String getArtist() {
		return aArtist;
	}
	
	public String getTime() {
		return aTime;
	}
	
	//checks if a song is valid	
	public boolean isValidSong() { 
		String filepath = aFilePath.toString();
		File f = new File(filepath);
		return f.exists();
	}

	// used for checking a song's validity before creating it
	private static boolean isValidCreate(String pFilePath) {
		assert pFilePath != null;
		File f = new File(pFilePath);
		return f.exists();
	}

	public static boolean isValidExtension(String pFilePath) {
		assert pFilePath != null;
		String[] l = pFilePath.split("\\.");
		int s = l.length;
		String ext = l[s-1];
		return ext.equals("wav");
	}

	public String toString() {
		String str = "TITLE --> " + aTitle + "\n" +
				     "ARTIST --> " + aArtist + "\n" +
				     "TIME --> " + aTime;
		Set<String> customTagKeys = aCustomTags.keySet();
		if(customTagKeys.size()!=0){
			for(String key:customTagKeys){
				str += "\n" + key + " --> " + aCustomTags.get(key);
			}
		}
		return str;
	}
	/*this method print song in one line (not detailed)*/
	public String printSongInOneLine(){
		String str = aTitle +
				" - " +
				aArtist +
				" - " +
				aTime;
		Set<String> customTagKeys = aCustomTags.keySet();
		if(customTagKeys.size()!=0){
			for(String key:customTagKeys){
				str += " - " + key + ":"+ aCustomTags.get(key);
			}
		}
		return str;
	}
	
	

	@Override
	public int compare(Song o1, Song o2) {
		// TODO: TO be implemented.
		return 0;
	}

	@Override
	//Plays the song, i.e. prints out the song information.
	public void play() {
		System.out.println(toString());
	}
}
