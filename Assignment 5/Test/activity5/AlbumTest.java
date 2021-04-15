package activity5;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class AlbumTest 
{
    Song songOne = new Song(new File("path1.mp3"), "FirstSong", 0);
    Song songTwo = new Song(new File("path2.mp3"), "SecondSong", 0);
    Album albumOne = new Album("Album1");
    Album albumTwo = new Album("Album2", "ExampleArtist", 0);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream curConsole;
    PrintStream captureConsole = new PrintStream(outputStream);

    /*
     * Info for capturing console output from here:
     * https://www.geeksforgeeks.org/redirecting-system-out-println-output-to-a-file-in-java/
     */

	/**
	 *  Test the getArtist() method in Album.
	 */
	@Test
	public void testGetArtist()
	{
	    assertEquals("ExampleArtist",albumTwo.getArtist());
	    assertEquals("<undefined artist>", albumOne.getArtist());
	}

	/**
	 *  Test the play() method from parent (AbstractPlayable) and
	 *  the playContent() method from Album (nested together).
	 */
	@Test
	public void testPlay()
	{
	    albumOne.addTrack(1, songOne);
	    albumOne.addTrack(2, songTwo);
	    albumOne.play();
	    
	    assertEquals(1, songOne.getPlayCount());
	    assertEquals(1, songTwo.getPlayCount());
	    assertEquals(1, albumOne.getPlayCount());
	    
	    String expectSil = "Playing " + albumTwo.getSilence() + " seconds of silence" + System.lineSeparator();
	    assertEquals(expectSil, consolePlayCapture(albumTwo));
	}

	/**
	 *  Test the duration() method overridden in Album.
	 */
	@Test
    public void testDuration()
    {
        albumOne.addTrack(1, songOne);
        albumOne.addTrack(2, songTwo);
        albumOne.setSilence(30);
        int expectedDuration = songOne.duration() + songTwo.duration() + 30;
        assertEquals(expectedDuration, albumOne.duration());
    }

	/**
	 *  Test the description() method overridden in Album.
	 */
    @Test
    public void testDescription()
    {
        String expected = "Album: Album1 by <undefined artist>";
        assertEquals(expected, albumOne.description());
    }

    /**
	 *  A String output to console when playObj.play() is called.
	 */
	public String consolePlayCapture(Playable playObj)
    {
        curConsole = System.out;
        System.setOut(captureConsole);
        playObj.play();
        System.setOut(curConsole);
        System.out.print(outputStream.toString());
        return outputStream.toString();
    }
}