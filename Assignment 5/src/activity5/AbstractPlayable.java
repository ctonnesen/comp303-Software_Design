package activity5;


public abstract class AbstractPlayable implements Playable {
    private int aPlayCount = 0;
    private int aSilence = 0;
    private String aTitle = ""; 
    private final static String UNTITLED = "<untitled>";
    
    
    protected AbstractPlayable(String pTitle, int pSilence)
    {	
    	assert pTitle != null;
		this.aTitle = pTitle;
		this.aSilence = pSilence;
    }
    
	protected String getTitle()
	{
		if( aTitle.isBlank() )
		{
			return UNTITLED;
		}
		return aTitle;
	}
	
	/**
	 * 
	 * @param pTitle The title. Can be empty but not null.
	 * @pre pTitle != null
	 */
	protected void setTitle(String pTitle)
	{
		assert pTitle != null;
		aTitle = pTitle;
	}
	
	protected final int getSilence()
	{
		return aSilence;
	}

    protected final void setSilence(int pSilence)
	{
	    aSilence = pSilence;
	}

    /**
     * @return The duration of the play at normal speed.
     */
    public abstract int duration();

    /**
     * @return A short description of the item that can be displayed while it plays.
     */
    public abstract String description();

	public final void play(){
        incrementPlayCount();
        MediaSystem.instance().playSilence(aSilence);
        playContent();
    }

    /**
	 * @return nothing, rather just make sure the content of the playable has been played.
	 */
	
	protected abstract void playContent();

	protected final int getPlayCount() {
        return aPlayCount;
    }


    protected final void resetPlayCount() {
        aPlayCount = 0;
	}


    protected final void incrementPlayCount() {
        aPlayCount++;
    }

}




