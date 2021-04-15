package activity5;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistTest 
{
    Song songOne = new Song(new File("path1.mp3"), "FirstSong", 0);
    Playlist playlistOne = new Playlist("testPlaylist1", 20);
    Playlist playlistTwo = new Playlist("", 0);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream curConsole;
    PrintStream captureConsole = new PrintStream(outputStream);

    /*
    Info for capturing console output from here: https://www.geeksforgeeks.org/redirecting-system-out-println-output-to-a-file-in-java/
     */

 
	/**
	 *  Test the play() method from parent (AbstractPlayable) and
	 *  the playContent() method from Playlist (nested together).
	 */
    @Test
	public void testPlay() 
	{
	    String expectSil = "Playing " + playlistOne.getSilence() + " seconds of silence" + System.lineSeparator();
	    assertEquals(expectSil, consolePlayCapture(playlistOne));
	    playlistOne.add(songOne);
	    String songPrint = "Playing " + songOne.getSilence() + " seconds of silence" + System.lineSeparator() + "Playing " + songOne.getTitle() + " by <undefined artist>" + System.lineSeparator();
	    String allParts = expectSil + expectSil + songPrint;
	    assertEquals(allParts, consolePlayCapture(playlistOne));
	}
    
    /**
	 *  Test the duration() method overridden in Playlist.
	 */
	@Test
    public void testDuration() 
    {
    	songOne.setSilence(25);
    	playlistOne.add(songOne);
    	int expectedDuration = songOne.duration() + playlistOne.getSilence();
    	
    	assertEquals(expectedDuration, playlistOne.duration());
    }

	/**
	 *  Test the description() method overridden in Playlist.
	 */
    @Test
    public void testDescription() 
    {
        String expected = "Playlist: " + playlistOne.getTitle() + "[0 items]";
        
        assertEquals(expected, playlistOne.description());
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