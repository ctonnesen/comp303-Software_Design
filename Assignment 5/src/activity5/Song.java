package activity5;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a piece of audio media that can be played,
 * along with its metadata.
 */
public class Song extends AbstractPlayable
{
	private final static String UNDEFINED = "<undefined %s>";
	private final static String TAG_ARTIST = "artist";
	
	private final File aFile;
	private int aDuration;
	private Map<String, String> aTags = new HashMap<>();
	
	/**
	 * Create a new song with no meta-data.
	 * 
	 * @param pFile The corresponding file.
	 * @pre pFile != null;
	 */
	public Song(File pFile, String pTitle,int pSilence)
	{	
		super(pTitle,pSilence);
		assert pFile != null;
		aFile = pFile;
		aDuration = MediaSystem.instance().duration(pFile);
	}
	
	/**
	 * @return The file associated with this song. Never null.
	 */
	public File getFile()
	{
		return aFile;
	}
	
	
	/**
	 * Set a piece of meta-data to associate with this song.
	 * Tags are case-insensitive.
	 * 
	 * @param pTag The tag for the piece of meta data.
	 * @param pValue The value for the piece of meta-data.
	 * @pre pTag != null && pValue != null
	 */
	public void setTag(String pTag, String pValue)
	{
		assert pTag != null && pValue != null;
		aTags.put(pTag.toLowerCase(), pValue);
	}
	
	/**
	 * Obtain the value for a given tag.
	 * 
	 * @param pTag The tag to get.
	 * @return The value for the tag, or a string indicating
	 * that there is no value for this tag if that's the case.
	 * @pre pTag != null;
	 */
	public String getTag(String pTag)
	{
		assert pTag != null;
		return aTags.getOrDefault(pTag.toLowerCase(), String.format(UNDEFINED, pTag));
	}
	
	@Override
	public void playContent()
	{
		MediaSystem.instance().playSong(this);
	}

	@Override
	public int duration()
	{
		return aDuration + getSilence();
	}

	@Override
	public String description()
	{
		return getTitle() + " by " + getTag(TAG_ARTIST);
	}
}
