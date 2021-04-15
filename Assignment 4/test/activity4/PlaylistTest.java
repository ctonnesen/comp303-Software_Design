package activity4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;


public class PlaylistTest
{
	private final File filepath1 = new File("FilePathTest");
	private final File filepath2 = new File("FilePathTest2");
	private final Song song1 = new Song(filepath1);
	private final Song song2 = new Song(filepath2);
	private final Playlist playlist1 = new Playlist("TitleTest");
	private final Playlist playlist2 = new Playlist("TitleTest",song1,song2);
	
	
	@Test
	//Playlist only with title(T), no playables
	public void testPlaylistT_clone()
	{
		assertEquals(playlist1,playlist1.clone());
	}
	
	@Test
	//Playlist with title(T) and playables(T)
	public void testPlaylistTP_clone() {
		assertEquals(playlist2,playlist2.clone());
	}
	
	
	
	private final String aTitle = "testPlaylistTitle";
	private final Playlist aPlaylist = new Playlist(aTitle);

	static class PlayableStub implements Playable
	{
		private int aCount = 0;
		private int aTime = 0;
		private String aDesc;

		public PlayableStub(String pDesc)
		{
			pDesc = aDesc;
		}

		public int getCount()
		{
			return aCount;
		}

		@Override
		public void play()
		{
			aCount++;
			aTime += this.aTime;
		}

		@Override
		public int duration()
		{
			return aTime;
		}

		@Override
		public String description()
		{
			return aDesc;
		}
		
		@Override
		public PlayableStub clone() {
			try
			{
				return (PlayableStub) super.clone();
			}
			catch (CloneNotSupportedException e)
			{
				e.printStackTrace();
			}
			return null;
		}

	}

	@Test
	public void testSetTitle()
	{
		String newTitle = "newPlaylistTitle";
		aPlaylist.setTitle(newTitle);
		assertEquals(aPlaylist.getTitle(), newTitle);

	}

	@Test
	public void testGetTitle()
	{
		assertEquals(aPlaylist.getTitle(), aTitle);
	}

	@Test
	public void testAdd()
	{
		aPlaylist.add(Playable.NULL);
		assertEquals(0, getPlayable().size());
		assertEquals(0, aPlaylist.duration());
		
		PlayableStub stub = new PlayableStub("stubTitle");
		aPlaylist.add(stub);
		assertEquals(1, getPlayable().size());
	}
		

	@Test
	public void testBackup() {
		aPlaylist.backup();
		assertTrue(this.getIsBackedUp());
		
	}


	@Test
	public void testEquals()
	{
		PlayableStub stub = new PlayableStub("testStub");
		List<Playable> aPlayable = new ArrayList<Playable>();
		aPlayable.add(stub);
		setPlayable(aPlaylist, aPlayable);
		
		Playlist otherPlaylist = new Playlist("otherPlaylist");
		
		assertFalse(aPlaylist.equals(Playable.NULL));
		assertFalse(aPlaylist.equals(stub)); 
		assertFalse(aPlaylist.equals(otherPlaylist));
		assertFalse(aPlaylist.equals(null));
		
		assertTrue(aPlaylist.equals(aPlaylist));
				
		otherPlaylist.setTitle(aTitle);
		assertFalse(aPlaylist.equals(otherPlaylist));
		
		setPlayable(otherPlaylist, getPlayable());
		assertTrue(aPlaylist.equals(otherPlaylist));
		
		otherPlaylist.add(stub);
		assertTrue(aPlaylist.equals(otherPlaylist));	
	}
	

/**
	 * making sure duration works
	 */
	@Test
	public void testDuration(){
		Playlist p1 = new Playlist("Small");

		PlayableStub stub = new PlayableStub("a stub with some time");

		p1.add(stub);
		assertEquals(p1.duration(), stub.duration());

		Song s = new Song(new File("someSong.mp3"));
		s.setDuration(10);
		p1.add(s);

		assertEquals(p1.duration(), stub.duration() + s.duration());

		Playlist p2 = new Playlist("Big");

		p2.add(p1);

		assertEquals(p2.duration(), p1.duration());


	}


	/**
	 * Making sure the description method works
	 */
	@Test
	public void testDescription(){
		Playlist p1 = new Playlist("Small");

		PlayableStub stub = new PlayableStub("a stub with some time");


		p1.add(stub);
		assertEquals(p1.description(), 
		String.format("[Title: %s ; Duration: %d]", p1.getTitle(), p1.duration()));

		Song s = new Song(new File("someSong.mp3"));
		s.setDuration(10);
		p1.add(s);

		Playlist p2 = new Playlist("Big");

		p2.add(p1);
		assertEquals(p2.description(), 
		String.format("[Title: %s ; Duration: %d]", p2.getTitle(), p2.duration()));


	}


	private void setPlayable(Playlist pPlaylist, List<Playable> pPlayable) {
		try
		{
			Field field = pPlaylist.getClass().getDeclaredField("aPlayable");
			field.setAccessible(true);
			field.set(pPlaylist, pPlayable);
		}
		catch (ReflectiveOperationException e)
		{

		}
		
		
	}

	@SuppressWarnings({"unchecked" })
	private List<Playable> getPlayable()
	{
		try
		{
			Field field = aPlaylist.getClass().getDeclaredField("aPlayable");
			field.setAccessible(true);
			return (List<Playable>) field.get(aPlaylist);
		}
		catch (ReflectiveOperationException e)
		{

		}
		return null;
	}

	private boolean getIsBackedUp()
	{
		try
		{
			Field field = aPlaylist.getClass().getDeclaredField("isBackedUp");
			field.setAccessible(true);
			return (boolean) field.get(aPlaylist);
		}
		catch (ReflectiveOperationException e)
		{
			e.printStackTrace();
		}
		return false;

	}

}
