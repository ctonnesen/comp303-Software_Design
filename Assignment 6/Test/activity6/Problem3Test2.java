package activity6;

import static org.junit.jupiter.api.Assertions.* ; 

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.System.Logger;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Problem3Test2
{
	private LibraryUI libraryUI ; 
	private Library lib ; 
	private StatusPanel statusPanel ; 
	File file ; 
	Text status ; 
	StringBuilder ADDED  ; 
	StringBuilder REMOVED  ; 
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); 
	PrintStream printStream ; 
	PrintStream standardOut = new PrintStream(outputStream); 
	
	@BeforeEach
	public void setup() throws FileNotFoundException , IllegalArgumentException, NoSuchFieldException, SecurityException, IllegalAccessException{

		ADDED = new StringBuilder(); 
		REMOVED = new StringBuilder(); 
		ADDED.append("Song ADDED"); 
		REMOVED.append("Song REMOVED"); 

		file = new File("song1.mp3"); 
		libraryUI = new LibraryUI(); 
//		Field aPlayables = Library.class.getDeclaredField("aPlayable"); 
//		aPlayables.setAccessible(true);

		Field field_lib = LibraryUI.class.getDeclaredField("library"); 
		field_lib.setAccessible(true);
		lib = (Library)field_lib.get(libraryUI);  // get the library instance

		statusPanel = new StatusPanel(lib.iterator()); 
		Field field_status = StatusPanel.class.getDeclaredField("aText"); 
		field_status.setAccessible(true);
		status = (Text) field_status.get(statusPanel); 

		
	}
	
	@Test
	void GUIText_songADDED() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// uses flyweight to add create a Song instance + added to library aPlayables list
		lib.addItem(Song.get(new File("./song1.mp3"), "TitleSong1.mp3")); 

//		statusPanel.songChange(lib.iterator());
String m1 = "addObserver"; 
		String m2 = "notifyObservers"; 
		Method method1 = null ; 
		Method method2 = null ; 
		for(Method method : LibraryUI.class.getDeclaredMethods()) {
//			System.out.println(method.getName()) ;
			if(m1.equals(method.getName())) {
				
//				System.out.println(method.getName()) ;
				method1 = method ; 
			}
			else if(m2.equals(method.getName())) {
//				System.out.println(method.getName()); 
				method2 = method ; 
			}
		}
		method1.setAccessible(true);
		method2.setAccessible(true);
		method1.invoke(libraryUI, statusPanel);  // invoke addObserver
		method2.invoke(libraryUI);  // invoke notifyObserver 

		assertEquals(status.getText(), ADDED.toString()); 
		
	}
	
	@Test
	void GUIText_SongREMOVED()throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException { 
		lib.addItem(Song.get( file , "TitleSong1.mp3"));

		String m1 = "addObserver"; 
		String m2 = "notifyObservers"; 
		Method method1 = null ; 
		Method method2 = null ; 

		for(Method method : LibraryUI.class.getDeclaredMethods()) {
			if(m1.equals(method.getName())) {
				
				method1 = method ; 
			}
			else if(m2.equals(method.getName())) {
				method2 = method ; 
			}
		}
		method1.setAccessible(true);
		method2.setAccessible(true);
		method1.invoke(libraryUI, statusPanel);  // invoke addObserver
		method2.invoke(libraryUI);  // invoke notifyObserver 
		lib.removeItem(Song.get( file, "TitleSong1.mp3" ));
		method2.invoke(libraryUI);  // invoke notifyObserver 
		assertEquals(status.getText(), REMOVED.toString()); 

	}
	
	@Test
	void CONSOLEText_SongADDED()throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException { 
		printStream =  System.out; 
		System.setOut(standardOut);

		lib.addItem(Song.get(file, "TitleSong1.mp3"));

		String m1 = "addObserver"; 
		String m2 = "notifyObservers"; 
		Method method1 = null ; 
		Method method2 = null ; 
		for(Method method : LibraryUI.class.getDeclaredMethods()) {
//			System.out.println(method.getName()) ;
			if(m1.equals(method.getName())) {
				
//				System.out.println(method.getName()) ;
				method1 = method ; 
			}
			else if(m2.equals(method.getName())) {
//				System.out.println(method.getName()); 
				method2 = method ; 
			}
		}
		method1.setAccessible(true);
		method2.setAccessible(true);
		method1.invoke(libraryUI, statusPanel);  // invoke addObserver
		method2.invoke(libraryUI);  // invoke notifyObserver 
		
		System.setOut(printStream); 
		assertEquals(ADDED.toString(), outputStream.toString().trim());

	}
	@Test
	void CONSOLEText_SongREMOVED()throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException { 
		lib.addItem(Song.get(file, "TitleSong1.mp3")) ; 


		String m1 = "addObserver"; 
		String m2 = "notifyObservers"; 
		Method method1 = null ; 
		Method method2 = null ; 

		for(Method method : LibraryUI.class.getDeclaredMethods()) {
			if(m1.equals(method.getName())) {
				
				method1 = method ; 
			}
			else if(m2.equals(method.getName())) {
				method2 = method ; 
			}
		}
		method1.setAccessible(true);
		method2.setAccessible(true);
		method1.invoke(libraryUI, statusPanel);  // invoke addObserver
		method2.invoke(libraryUI);  // invoke notifyObserver 
		printStream = System.out ;
		System.setOut(standardOut);
		lib.removeItem(Song.get( file, "TitleSong1.mp3" ));
		method2.invoke(libraryUI);  // invoke notifyObserver 


		System.setOut(printStream);
		assertEquals(REMOVED.toString(), outputStream.toString().trim()) ;
		
	}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
