package activity5;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a named list of Playable items.
 */
public class Playlist extends AbstractPlayable
{
	private final List<Playable> aItems = new ArrayList<>();
	

	/**
	 * Create a new, empty playlist.
	 * 
	 * @param pTitle The title of the playlist.
	 * @pre pTitle != null;
	 */
	public Playlist(String pTitle, int pSilence)
	{
		super(pTitle, pSilence);
	}

	/**
	 * Add an item to the playlist.
	 * 
	 * @param pPlayable The item to add.
	 * @pre pPlayable != null;
	 */
	public void add(Playable pPlayable)
	{
		assert pPlayable != null;
		aItems.add(pPlayable);
	}

	@Override
	public void playContent()
	{
		for( Playable playable : aItems )
		{
			playable.play();
		}
	}

	@Override
	public int duration()
	{
		int duration = 0;
		duration += this.getSilence();
		for( Playable playable : aItems )
		{
			duration += playable.duration();
		}
		return duration;
	}

	@Override
	public String description()
	{
		return "Playlist: " + getTitle() + "[" + aItems.size() + " items]";
	}
}

