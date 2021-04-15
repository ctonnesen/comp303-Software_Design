package activity6;
//Interface for models like Library /Playlist/Album view
public interface Model
{
	public void addObserver(Observer o);
	public void removeObserver(Observer o);

	public void notifyObservers();

}
