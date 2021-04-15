package activity4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import java.io.File;

import java.lang.reflect.Field;

import java.util.List;
import java.util.ArrayList;

public class AlbumTester2 {
	
	private final File filepath1 = new File("FilePathTest");
	private final File filepath2 = new File("FilePathTest2");
	private final Song song1 = new Song(filepath1);
	private final Song song2 = new Song(filepath2);
	private final Artist artist = new Artist("ArtistTest");
	private final String aTitle = "TitleTest";
	private final Album  album1 = new Album(aTitle);
	private final Album  album2 = new Album(aTitle,artist);
	private final Album  album3 = new Album(aTitle);
	private final Album  album4 = new Album(aTitle,artist);
	
	@Test
	// get title with only title 
	public void testGetTitle1() {
		assertEquals(album1.getTitle(), aTitle);
	}
	
	@Test
	// get title with title and artist
	public void testGetTitle2() {
		assertEquals(album2.getTitle(), aTitle);
	}
	
	@Test
	// set title with only title
	public void testSetTitle1() {
		String newTitle = "Alternative title";
		album1.setTitle(newTitle);
		assertEquals(album1.getTitle(), newTitle);
	}
	
	@Test
	// set title with title and artist
	public void testSetTitle2() {
		String newTitle = "Alternative title";
		album2.setTitle(newTitle);
		assertEquals(album2.getTitle(), newTitle);
	}
	
	@Test
	// set artist with only title
	public void testSetArtist1() {
		Artist newArtist = new Artist("newArtist");
		album1.setArtist(newArtist);
		assertEquals(album1.printArtistName(), "newArtist");
	}
	
	@Test
	// set artist with title and artist
	public void testSetArtist2() {
		Artist newArtist = new Artist("newArtist");
		album2.setArtist(newArtist);
		assertEquals(album2.printArtistName(), "newArtist");
	}

	@Test
	//Album only with title (T), no songs
	public void testAlbumT_clone() {
		assertEquals(album1, album1.clone());
	}
	
	@Test
	//Album with title (T) and artist (A), no songs
	public void testAlbumTA_clone() {
		assertEquals(album2,album2.clone());
	}
	
	@Test
	//Album with title(T) and songs(S), no artist
	public void testAlbumTS_clone() {
		album3.addTrack(1, song1);
		album3.addTrack(2, song2);
		assertEquals(album3,album3.clone());
	}
	
	@Test
	//Album with title(T), artist(A) and songs(S)
	public void testAlbumATS_clone() {
		album4.addTrack(1, song1);
		album4.addTrack(2, song2);
		assertEquals(album4,album4.clone());
	}

	@Test
	// test add track (new track)
	public void testAlbum1_addTracks() {
		album4.addTrack(1, song1);
		album4.addTrack(2, song2);
		File filepath3 = new File("Filepath3");
		Song song3 = new Song(filepath3); 
		album4.addTrack(3, song3);
		assertTrue(album4.getTracks().size() == 3);
		assertEquals(album4.getTracks().get(3), song3);
	}
	
	@Test
	// test add track (repeated track)
	public void testAlbum2_addTracks() {
		album4.addTrack(1, song1);
		album4.addTrack(2, song2);
		album4.addTrack(2, song2);
		assertTrue(album4.getTracks().size() == 2);
		assertEquals(album4.getTracks().get(2), song2);
	}
	
	@Test
	// testing equals method
	public void testEquals() {
		Album otherAlbum = new Album("otherAlbum");
		File filepath = new File("filepath");
		File filepath2 = new File("filepath2");
		Song aSong = new Song(filepath);
		Song aSong2 = new Song(filepath2);
		album1.addTrack(1, aSong);
		album1.addTrack(2, aSong2);
		Artist anArtist = new Artist("artist");
		album1.setArtist(anArtist);
		
		assertFalse(album1.equals(otherAlbum));
		assertFalse(album1.equals(null));
		
		assertTrue(album1.equals(album1));
		
		otherAlbum.setTitle(aTitle);
		assertFalse(album1.equals(otherAlbum));
		
		otherAlbum.setArtist(anArtist);
		assertFalse(album1.equals(otherAlbum));
		
		otherAlbum.addTrack(1, aSong);
		assertFalse(album1.equals(otherAlbum));
		
		otherAlbum.addTrack(2, aSong2);
		assertTrue(album1.equals(otherAlbum));
		
	}
	
}
