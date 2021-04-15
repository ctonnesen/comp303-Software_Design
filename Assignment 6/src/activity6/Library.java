package activity6;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a collection of songs, playlists, and albums.
 */
public class Library implements Iterable<Playable>
{
	private final List<Playable> aPlayables = new ArrayList<>();
	
	/**
	 * Creates a new empty library.
	 * @pre  pPlayable != null; 
	 */
	public Library(){}
	
	public void addItem(Playable pPlayable)
	{
		assert pPlayable != null ; 
		if (aPlayables.contains(pPlayable)) {
			System.out.println("Playable with source File already exists");
			return;
		}
		aPlayables.add(pPlayable);
	}
	
	/**
	 * 
	 * @param pPlayable
	 * @pre pPlayable != null ; 
	 */
	public void removeItem(Playable pPlayable)
	{

		assert pPlayable != null ; 
		aPlayables.remove(pPlayable);
	}

	@Override
	public Iterator<Playable> iterator()
	{
		return new ArrayList<>(aPlayables).iterator();
	}
}
