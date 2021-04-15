package activity4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class ArtistTest
{
	private final Artist artist1 = new Artist("ArtistTest");
	private final ArrayList<String> alternate = new ArrayList<String>(Arrays.asList("name1", "name2"));
	private final Artist artist2 = new Artist("ArtistTest", alternate);
	
	@Test
	//Artist with no alternative names
	public void cloneArtistTest() {
		assertEquals(artist1,artist1.clone());
	}
	
	@Test
	//Artist with alternative names
	public void cloneArtistwNamesTest(){
		assertEquals(artist2,artist2.clone());
	}
}
