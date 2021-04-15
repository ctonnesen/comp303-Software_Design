package activity4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import activity4.Album;
import activity4.Artist;
import activity4.Playlist;
import activity4.Song;

public class AlbumTest
{
	private final File filepath1 = new File("FilePathTest");
	private final File filepath2 = new File("FilePathTest2");
	private final Song song1 = new Song(filepath1);
	private final Song song2 = new Song(filepath2);
	private final Artist artist = new Artist("ArtistTest");
	private final Album  album1 = new Album("TitleTest");
	private final Album  album2 = new Album("TitleTest",artist);
	private final Album  album3 = new Album("TitleTest");
	private final Album  album4 = new Album("TitleTest",artist);
	
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
}