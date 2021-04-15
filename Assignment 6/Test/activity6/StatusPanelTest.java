package activity6;

import static org.junit.jupiter.api.Assertions.* ; 

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.System.Logger;
import java.lang.reflect.Field;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.scene.text.Text;
import javafx.stage.Stage;

class StatusPanelTest
{
	private LibraryUI libraryUI;
	private Library library;
	private StatusPanel statusPanel;
	File newFile;
	Text status;

	ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	PrintStream c;
	PrintStream standardOut = new PrintStream(outputStreamCaptor);

	@BeforeEach
	public void setUp() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{

		libraryUI = new LibraryUI();

		Field field = LibraryUI.class.getDeclaredField("library");
		field.setAccessible(true);

		library = (Library) field.get(libraryUI);

		statusPanel = new StatusPanel(library.iterator());
		newFile = new File("song.mp3");

		Field sfield = StatusPanel.class.getDeclaredField("aText");
		sfield.setAccessible(true);
		status = (Text) sfield.get(statusPanel);

	}

	@Test
	void testText_SongAdded()
	{

		library.addItem(Song.get(new File("ffff"), "Titfle.mp3"));
		statusPanel.songChange(library.iterator());

		assertEquals(status.getText(), "Song ADDED");

	}

	@Test
	void testText_SongRemoved()
	{

		library.addItem(Song.get(newFile, newFile.toString()));
		statusPanel.songChange(library.iterator());

		library.removeItem(Song.get(newFile, "Title"));
		statusPanel.songChange(library.iterator());

		assertEquals(status.getText(), "Song REMOVED");
	}

	@Test
	void testConsole_SongAdded() throws IOException
	{

		c = System.out;
		System.setOut(standardOut);

		library.addItem(Song.get(new File("ffff.mp3"), new File("ffff.mp3").toString()));
		statusPanel.songChange(library.iterator());

		System.setOut(c);

		assertEquals("Song ADDED", outputStreamCaptor.toString().trim());
	}

	@Test
	void testConsole_SongRemove()
	{
		library.addItem(Song.get(newFile, newFile.toString()));
		statusPanel.songChange(library.iterator());

		c = System.out;
		System.setOut(standardOut);

		library.removeItem(Song.get(newFile, newFile.toString()));
		statusPanel.songChange(library.iterator());
		System.setOut(c);

		assertEquals("Song REMOVED", outputStreamCaptor.toString().trim());
	}

}
