package activity5;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractPlayableTest
{
    public Album album1 = new Album("","",0);
	public Playlist playlist1 = new Playlist("",0);
	public Song song1 = new Song(new File("song A.mp3"),"",0);
	
    /**
     *  Test the subclass can properly use the method in super class.
     *  Test the getTitle() method in the abstract class.
     */
    @Test
    public void testGetTitle() 
    {
    	assertEquals("<untitled>",album1.getTitle());	
    	assertEquals("<untitled>",playlist1.getTitle());
    	assertEquals("<untitled>",song1.getTitle());
    }
    	
    /**
     *  Test the setTitle() method in the abstract class.
     */
    @Test
    public void testSetTitle() 
    {
    	album1.setTitle("");
    	playlist1.setTitle("good title");
    	song1.setTitle("nice title");
    	
    	assertEquals("<untitled>",album1.getTitle());
    	assertEquals("nice title",song1.getTitle());
    	assertEquals("good title",playlist1.getTitle());	
    }
    	
    @Test
    public void testIncrementPlayCount() 
    {
    	album1.incrementPlayCount();
    	assertEquals(1,album1.getPlayCount());
    	playlist1.incrementPlayCount();
    	assertEquals(1,playlist1.getPlayCount());
    	song1.incrementPlayCount();
    	song1.incrementPlayCount();
    	assertEquals(2,song1.getPlayCount());
    }
    	
    @Test
    public void testGetPlayCount() 
    {
    	album1.play();
    	assertEquals(1,album1.getPlayCount());
    	album1.play();
    	assertEquals(2,album1.getPlayCount());
    	playlist1.play();
    	assertEquals(1,playlist1.getPlayCount());
    	song1.play();
    	assertEquals(1,song1.getPlayCount());
    }
    	
    @Test
    public void testResetPlayCount() {
    	song1.play();
    	song1.resetPlayCount();
    	assertEquals(0,song1.getPlayCount());
    	album1.play();
    	album1.play();
    	album1.resetPlayCount();
    	assertEquals(0,album1.getPlayCount());
    	playlist1.play();
    	playlist1.play();
    	playlist1.play();
    	playlist1.resetPlayCount();
    	assertEquals(0,playlist1.getPlayCount());
    }
}
