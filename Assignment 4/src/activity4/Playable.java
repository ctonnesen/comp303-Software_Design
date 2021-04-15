package activity4;

/**
 * An item that can be played in a media player.
 */
public interface Playable extends Cloneable
{
	public static Playable createNull() {
		return new Playable() {
			@Override
			public int duration() {
				return 0;
			}

			@Override
			public String description() {
				return "Not available";
			}
			
			public boolean isNull() {
				return true;
			}
			@Override
			public void play(){
				System.out.println("Not available for playing.");
			}
			@Override
			public Playable clone() {
				System.out.println("Not availble for cloning");
				return null;
			}
		};
	}
	
	static Playable NULL = createNull();
	
	/**
	 * Play the entire item.
	 */
	void play();
	
	/**
	 * @return The duration of the play at normal speed.
	 */
	int duration();
	
	/**
	 * @return A short description of the item that can be displayed while it plays.
	 */
	String description();
	
	default boolean isNull() {return false;}

	Playable clone();
}
