package activity5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a collection of songs, playlists, and albums.
 */
public class Library implements Iterable<Playable>
{	
	private final CompositeCommand aComposite= new CompositeCommand();
	private final List<Playable> aPlayables = new ArrayList<>();
	private int aCommandProgress = -1;
	
	/**
	 * Creates a new empty library.
	 */
	public Library()
	{}
	
	public void add(Playable pPlayable)
	{
		if( aCommandProgress != aComposite.size()-1 )
		{
			clearAfter(aCommandProgress);
		}
		
		Command addCommand = createAddCommand(pPlayable);
		addCommand.execute();
		aCommandProgress++;
		
	}

	public void remove(Playable pPlayable)
	{	
		assert aPlayables.contains(pPlayable);
		
		if( aCommandProgress != aComposite.size()-1 )
		{
			clearAfter(aCommandProgress);
		}
		
		Command removeCommand = createRemoveCommand(pPlayable);
		removeCommand.execute();
		aCommandProgress++;
	}
	
	public void redo() 
	{
		if( aComposite.size()-1 > aCommandProgress )
		{
			aComposite.getCommand(aCommandProgress+1).execute();
			aCommandProgress++;
		}
	}
	
	public void undo() 
	{
		if( aCommandProgress >= 0 )
		{
			aComposite.getCommand(aCommandProgress).undo();
			aCommandProgress--;
		}
	}
	
	/**
	 * removes all commands in aComposite after index of pCommandProgress
	 * */
	private void clearAfter(int pCommandProgress)
	{
		aCommandProgress = pCommandProgress;
		for(int i = aCommandProgress+1; i < aComposite.size(); )
		{
			aComposite.removeCommand(aComposite.getCommand(i));
		}
	}
	
	private Command createAddCommand(Playable pPlayable) {
			
		aComposite.addCommand(new Command() 
				{	
					
					@Override
					public void execute()
					{
						aPlayables.add(pPlayable);
					}
					
					@Override
					public void undo()
					{	
						aPlayables.remove(aPlayables.lastIndexOf(pPlayable));	
					}
					
					@Override
					public String toString() {
						return "ADD: " + pPlayable.description();
					}
					
				} );
		
		return aComposite.getCommand(aComposite.size() - 1);
			
	}
	
	private Command createRemoveCommand(Playable pPlayable) 
	{
		aComposite.addCommand( new Command()
		{
			int index;
			@Override
			public void execute()
			{	
				if(aPlayables.contains(pPlayable)) 
				{
					index = aPlayables.indexOf(pPlayable);
					aPlayables.remove(pPlayable);
				} 
				else
				{
					index = -1;
				}
			}

			@Override
			public void undo()
			{	
				if (index != -1)
				{
					aPlayables.add(index, pPlayable);
				}
				index = -1;
			}
			
			@Override
			public String toString() {
				return "Remove: " + pPlayable.description() ;
			}
			
		});
		
		return aComposite.getCommand(aComposite.size() - 1);
	}
	
	public void executeAll()
	{
		for( int i = aCommandProgress+1; i < aComposite.size(); i++ )
		{
			aComposite.getCommand(i).execute();
		}
	}
	
	public void undoAll()
	{
		for( int i = aCommandProgress; i >= 0; i-- )
		{
			aComposite.getCommand(i).undo();
		}
	}
	
	@Override
	public Iterator<Playable> iterator()
	{
		return new ArrayList<>(aPlayables).iterator();
	}
	
	public boolean isEmpty()
	{
		return aPlayables.isEmpty();
	}
	
}
