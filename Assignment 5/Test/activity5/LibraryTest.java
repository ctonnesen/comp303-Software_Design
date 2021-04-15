package activity5;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest
{
	
	Library testLibrary = new Library();
	Playlist testPlaylist = new Playlist("testPlaylist1", 0);
	
	@Test
	public void testAdd()
	{
		testLibrary.add(testPlaylist);
		
		Iterator<Playable> iter = testLibrary.iterator();
		assertEquals(testPlaylist, iter.next());
		assertEquals(false, iter.hasNext());
	}
	
	@Test
	public void testRemove()
	{
		testLibrary.add(testPlaylist);
		assertEquals(false, testLibrary.isEmpty());
		
		testLibrary.remove(testPlaylist);
		assertEquals(true, testLibrary.isEmpty());
	}
	
	@Test
	public void testUndo_Add()
	{
		testLibrary.add(testPlaylist);
		testLibrary.undo();
		assertEquals(true, testLibrary.isEmpty());
	}
	
	@Test
	public void testUndo_Remove()
	{
		testLibrary.add(testPlaylist);
		testLibrary.remove(testPlaylist);

		assertEquals(true, testLibrary.isEmpty());

		testLibrary.undo();
		Iterator<Playable> iter = testLibrary.iterator();
		assertEquals(testPlaylist, iter.next());
		assertEquals(false, iter.hasNext());
	}
	
	@Test
	public void testRedo_Add()
	{
		testLibrary.add(testPlaylist);
		testLibrary.undo();
		testLibrary.redo();
		
		Iterator<Playable> iter = testLibrary.iterator();
		assertEquals(testPlaylist, iter.next());
		assertEquals(false, iter.hasNext());
	}
	
	@Test
	public void testRedo_Remove()
	{
		testLibrary.add(testPlaylist);
		testLibrary.remove(testPlaylist);

		assertEquals(true, testLibrary.isEmpty());

		testLibrary.undo();
		testLibrary.redo();

		assertEquals(true, testLibrary.isEmpty());

		testLibrary.undo();
		testLibrary.undo();
		testLibrary.redo();
		testLibrary.redo();

		assertEquals(true, testLibrary.isEmpty());
	}
	
	@Test
	public void testExecuteAll()
	{
		testLibrary.add(testPlaylist);
		testLibrary.add(testPlaylist);
		testLibrary.add(testPlaylist);
		testLibrary.remove(testPlaylist);
		testLibrary.remove(testPlaylist);
		testLibrary.undo();
		
		testLibrary.executeAll();
		
		Iterator<Playable> iter = testLibrary.iterator();
		assertEquals(testPlaylist, iter.next());
		assertEquals(false, iter.hasNext());
	}
	
	@Test
	public void testUndoAll()
	{
		testLibrary.add(testPlaylist);
		testLibrary.add(testPlaylist);
		testLibrary.add(testPlaylist);
		testLibrary.remove(testPlaylist);
		testLibrary.remove(testPlaylist);
		testLibrary.undo();
		testLibrary.redo();
		
		testLibrary.undoAll();
		assertEquals(true, testLibrary.isEmpty());
	}
}
