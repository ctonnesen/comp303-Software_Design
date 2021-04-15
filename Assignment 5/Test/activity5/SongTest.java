package activity5;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class SongTest 
{
    Song songOne = new Song(new File("path1.mp3"), "FirstSong", 0);
    Song songTwo = new Song(new File("path2.mp3"), "SecondSong", 10);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream curConsole;
    PrintStream captureConsole = new PrintStream(outputStream);

    /*
     * Info for capturing console output from here:
     * https://www.geeksforgeeks.org/redirecting-system-out-println-output-to-a-file-in-java/
     */

    /**
	 *  Test the getFile() method in Song.
	 */
    @Test
	public void testGetFile() 
	{
	    File testFile = new File ("testLocation.exe");
	    Song exampleSong = new Song(testFile, "testSong", 0);
	    
	    assertEquals(testFile,exampleSong.getFile());
	}
    
    /**
	 *  Test the setTag() method in Song.
	 */

	@Test
	public void testSetTag()
	{
	    songOne.setTag("testTag","testValue");
	    
	    assertEquals("testValue",songOne.getTag("testTag"));
	}

	/**
	 *  Test the play() method from parent (AbstractPlayable) and
	 *  the playContent() method from Song (nested together).
	 */
	@Test
    public void testPlay() 
    {
        songOne.play();
        songTwo.play();
        songTwo.play();
        
        assertEquals(1, songOne.getPlayCount());
        assertEquals(2, songTwo.getPlayCount());
        
        String expectSil = "Playing " + songOne.getSilence() + " seconds of silence" + System.lineSeparator() + "Playing " + songOne.getTitle() + " by <undefined artist>" + System.lineSeparator();
        assertEquals(expectSil, consolePlayCapture(songOne));
    }

	/**
	 *  Test the duration() method overridden in Song.
	 */
    @Test
    public void testDuration()
    {
        try
        {
            songOne.setSilence(25);
            Field aDuration = Song.class.getDeclaredField("aDuration");
            aDuration.setAccessible(true);
            int expectedDuration = aDuration.getInt(songOne) + songOne.getSilence();
            
            assertEquals(expectedDuration, songOne.duration());
        } 
        catch (ReflectiveOperationException e) 
        {
            fail();
        }
    }

    /**
	 *  Test the description() method overridden in Song.
	 */
    @Test
    public void testDescription()
    {
        String expected = "FirstSong by <undefined artist>";
        
        assertEquals(expected, songOne.description());
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