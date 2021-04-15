package activity4;

import java.util.Objects;

public class BufferDecorator implements Playable {
    private final Playable aPlayable;
    private int aBufferBefore;
    private int aBufferAfter;

    public int getBufferBefore() {
        return aBufferBefore;
    }

    public void setBufferBefore(int pBufferBefore) {
        aBufferBefore = pBufferBefore;
    }

    public int getBufferAfter() {
        return aBufferAfter;
    }

    public void setBufferAfter(int pBufferAfter) {
        aBufferAfter = pBufferAfter;
    }



    public BufferDecorator(Playable pPlayable, int pBufferBefore, int pBufferAfter){
        aPlayable = pPlayable;
        aBufferBefore = pBufferBefore;
        aBufferAfter = pBufferAfter;

    }


    /**
     * Play the entire item, making sure to use the decorated item's methods
     */
    @Override
    public void play() {
        System.out.println("Buffering for " + aBufferBefore + " before playing");
        aPlayable.play();
        System.out.println("Buffering for " + aBufferAfter + " after playing");
    }

    /**
     * @return The duration of the play at normal speed ,making sure to use the decorated item's methods
     */
    @Override
    public int duration() {
        return aBufferBefore + aPlayable.duration() + aBufferAfter;
    }

    /**
     * @return A short description of the item that can be displayed while it plays,  making sure to use the decorated item's methods
     */
    @Override
    public String description() {
        return  "Buffers for " + aBufferBefore + "before. " + aPlayable.description() + " Buffers for " + aBufferAfter + " after.";
    }


    /**
     *
     * @return if the underlying item is null
     */
    @Override
    public boolean isNull() {
        return aPlayable.isNull();
    }
    
    @Override
    public BufferDecorator clone() {
		try
		{
			BufferDecorator clone = (BufferDecorator) super.clone();
			return clone;
		}
		catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
    	return null;
    }
    
    
    @Override
    public boolean equals(Object pObject) {
    	if (pObject == null || pObject.getClass() != this.getClass())
		{
			return false;
		} else if (pObject == this)
		{
			return true;
		}
		else
		{
			BufferDecorator buffer = (BufferDecorator) pObject;
			if ((this.aBufferBefore == buffer.aBufferBefore) && (this.aBufferAfter == buffer.aBufferAfter) && (this.aPlayable==buffer.aPlayable)) {
				return true;
			} else {
				return false;
			}
		}
    }
    
    @Override
    public int hashCode()
	{
		return Objects.hash(aPlayable, aBufferBefore, aBufferAfter);
	}
    

}
