package activity5;

import java.util.ArrayList;
import java.util.List;



public class CompositeCommand implements Command
{	
	
	private final List<Command> aCommands = new ArrayList<>();
	/**
	 * 
	 * @param pCommands any commands that can be added to this composite
	 */
	public CompositeCommand(Command ...pCommands)
	{
		for(Command command : pCommands )
		{
			addCommand(command);
		}
	}
	
	public void addCommand(Command pCommand)
	{
		aCommands.add(pCommand);
	}
	
	public void removeCommand(Command pCommand)
	{	
		assert aCommands.contains(pCommand);
		aCommands.remove(pCommand);
	}
	
	public Command getCommand(int index)
	{
		assert index <= aCommands.size();
		return aCommands.get(index);
	}
	
	public int size() {
		return aCommands.size(); 
	}
	
	@Override
	public void execute()
	{
		for (Command command : aCommands)
		{
			command.execute();
		}
		
	}

	@Override
	public void undo()
	{
		for ( int i = aCommands.size()-1; i >= 0; i--) {
			aCommands.get(i).undo();
		}
		
	}

}
