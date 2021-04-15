package code;

import java.util.*;


public abstract class AudioList implements Iterable<Song> {
	private String title;

	public AudioList(String title) {
		assert title != null;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String input) {
		assert input != null;
		this.title = input;
	}

	public abstract ArrayList<Song> getContents();

	public Iterator<Song> iterator(){
		return new the_iterator(this.getContents());
	}
}

class the_iterator implements Iterator<Song>
{

	private final Queue<Object> songsQueue;

	the_iterator(ArrayList<Song> TheSongs){

		songsQueue = new LinkedList<>(TheSongs);
	}

	@Override
	public boolean hasNext() {
		return !songsQueue.isEmpty();
	}

	@Override
	public Song next() {
		return (Song) songsQueue.remove();
	}
}

/*
class the_iterator implements Iterator<Song>{
	int index = -1; 
	private ArrayList<code.Song> TheSongs;

	the_iterator(ArrayList<code.Song> TheSongs){
		this.TheSongs = TheSongs; 
	}

	@Override
	public boolean hasNext() {

// 		index++;
// 		if ((index + 1) < TheSongs.size()){
// 			index++; 
// 			return this.hasNext(); 
// 		}
// Commented out because it seems to serve the same function as below, which was already written

		if ((index+1) < TheSongs.size()){
			return true; 
		}
		return false; 
	}

	@Override
	public Song next() {
		if (!hasNext()) {
            throw new NoSuchElementException();
        }
		index++;
		return TheSongs.get(index);
		// TODO Auto-generated method stub
	}
}
 */


