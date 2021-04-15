package activity4;

public class PlayCountDecorator implements Playable
{
	private final Playable aPlayable;
	private int playCount;

	public PlayCountDecorator (Playable pPlayable) {
		aPlayable = pPlayable;
		playCount = 0;
	}

	@Override
	public void play()
	{
		aPlayable.play();
		playCount++;

	}

	@Override
	public int duration()
	{
		return aPlayable.duration();
	}

	@Override
	public String description()
	{
		return  "Played for " + playCount + "times. " + aPlayable.description();
	}
	
	@Override
	public boolean isNull() {
	    return aPlayable.isNull();
	}

	@Override
	public PlayCountDecorator clone()
	{
		try
		{
			PlayCountDecorator clone = (PlayCountDecorator) super.clone();
			return clone;
		}
		catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
		return null;
	}

}

