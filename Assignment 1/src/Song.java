import java.io.File;

public class Song {	
	private ExpectedTags aExpectedTagsMap;
	private OptionalTags aOptionalTagsMap;
	private CustomTags aCustomTagsMap;
	private AudioFormat aFormat;
	private File aFilePath;		
		
	public Song(File pFilePath, String pTitle, String pArtist, int seconds, int minutes) {
		assert  pFilePath != null && pTitle !=null && pArtist != null && seconds>=0 && seconds <60 && minutes>=0 && minutes < 60;
		this.aExpectedTagsMap = new ExpectedTags(pTitle, pArtist, new TotalTime(seconds, minutes));
		this.aOptionalTagsMap = new OptionalTags();
		this.aCustomTagsMap = new CustomTags();
		this.aFilePath = pFilePath;
		this.aFormat = validateAudioFormat(pFilePath.toString());
	}

	/**
     * Creates a new song.
     * @param aExpectedTagsMap  expected/required tags that a song should have(title, artist, time)
     * @param aOptionalTagsMap optional/common tags a song should have
     * @param aCustomTagsMap: key-value pair feature used to classify song  
	 * @param aFormat format of the song
     * @param aBpm  beats per minute
     */

	public Song(File pFilePath, ExpectedTags pExpectedTagsMap,OptionalTags pOptionalTagsMap, CustomTags pCustomTagsMap) {
		assert pFilePath !=null && pExpectedTagsMap !=null && pOptionalTagsMap != null && pCustomTagsMap !=null;
		this.aExpectedTagsMap = pExpectedTagsMap;
		this.aOptionalTagsMap = pOptionalTagsMap;
		this.aCustomTagsMap = pCustomTagsMap;
		this.aFormat = validateAudioFormat(pFilePath.toString());
		this.aFilePath = pFilePath;

	}
	
	public Song(Song pSong) {
		assert pSong != null;
		this.aExpectedTagsMap = pSong.aExpectedTagsMap;
		this.aOptionalTagsMap = pSong.aOptionalTagsMap;
		this.aCustomTagsMap = pSong.aCustomTagsMap;
		this.aFormat = pSong.aFormat;
		this.aFilePath = pSong.aFilePath;
	}
	
	
	/**
     * @return The title of the song
     */
	public String getTitle() {
		return this.aExpectedTagsMap.getTitle();
	}
	
	public ExpectedTags getExpectedTags() {
		return this.aExpectedTagsMap;
	}
	
	public OptionalTags getOptionalTags() {
		return this.aOptionalTagsMap;
	}
	
	public CustomTags getCustomTags() {
		return this.aCustomTagsMap;
	}
	
	public File getFilePath() {
		return this.aFilePath;
	}
	
	public AudioFormat getFormat() {
		return aFormat;
	}
	// This method exists solely to test the capability of the system when removing the file path

	public void setFilePathTester(File newPath) {
		this.aFilePath = newPath;
	}

	private AudioFormat validateAudioFormat(String pFileName) {
		assert pFileName != null;
		int lastIndexOf = pFileName.lastIndexOf(".") + 1;
		String fileFormat = pFileName.substring(lastIndexOf).toUpperCase();
		AudioFormat format = null;
		try 
		{
		format = AudioFormat.valueOf(fileFormat);			
		} catch (IllegalArgumentException e) {
			//System.out.println("Invalid audio format");
			System.err.print("Invalid audio format");
		}
		return format;

	}
	/**
	 * Checks to see if a song is still valid (i.e. the filepath exists)
	 * */
	public boolean isValid() {
		 if (this.aFilePath.exists()) {
			 return true;
		 } else {
			 System.out.println("The song you are attempting to add is invalid");
			 return false;
		 }
	}

	public TotalTime getTotalTime() {
		return new TotalTime(this.aExpectedTagsMap.getTime());
	}
	
}

