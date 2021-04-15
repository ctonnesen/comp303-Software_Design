package code;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SongIterator<Song> implements Iterator<Song> {
	ArrayList<Song> ourList;
	int index;
	int size;

	SongIterator(ArrayList<Song> songList) {
		assert songList != null;
		this.ourList = songList;
		this.size = songList.size();
		this.index = 0;
	}

	@Override
	public boolean hasNext() {
		if ((index+1) < size){
			return true;
		}
		else{
			return false;
		}
	}

	public Song next(){
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		index++;
		return this.ourList.get(index);
	}
}
