package activity4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

public class SongTest
{
	private final File filepath1 = new File("FilePathTest");
	private final Song song1 = new Song(filepath1);
	
	@Test
	//song
	public void testSongF_clone() 
	{
		assertEquals(song1, song1.clone());
	}
	
	@Test
	//testing equals method
	public void testSongEquals ()
	{
	    File filepath2 = new File ("filepath2");
		Song song2 = new Song (filepath1);
		Song song3 = new Song (filepath2);
		
		
		assertFalse(song1.equals(null));
		assertFalse(song1.equals(song3));
		
		//same filepath ?
		assertTrue (song1.equals(song2));
		
		//same song title
		song1.setTitle("songTitle");
		song2.setTitle("songTitle");
		assertTrue (song1.getTitle().equals(song2.getTitle()));
		
		song1.setGenre("genre");
		song2.setGenre("genre");
		assertTrue (song1.getGenre().equals(song2.getGenre()));
		
		
		
		
	}

}
