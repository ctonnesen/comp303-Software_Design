package activity6;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ListGUITest {
    private LibraryUI libraryUI = new LibraryUI();
    private ListGUI listGUI;
    private Song song1 = Song.get(new File("Test1.exe"), "");
    private ObservableList<Playable> internalList;

    /**
     * This method creates a new listGUI each time
     */
    @BeforeEach
    public void setEnvironment() {
        try {
            listGUI = new ListGUI(getLibrary().iterator());
            Field listGUIList = ListGUI.class.getDeclaredField("observableList");
            listGUIList.setAccessible(true);
            internalList = (ObservableList<Playable>) listGUIList.get(listGUI);
        } catch (Exception e) {
            System.out.println("Problem getting internal list");
        }
    }

    @Test
    public void deleteItemTest() {
        getLibrary().addItem(song1);
        runNotify();
        getLibrary().removeItem(song1);
        runNotify();
        listGUI.songChange(getLibrary().iterator());
        assertTrue(internalList.isEmpty());
    }

    @Test
    public void addItemTest() {
        try {
            getLibrary().addItem(song1);
            Method method = LibraryUI.class.getDeclaredMethod("addObserver", Observer.class);
            method.setAccessible(true);
            method.invoke(libraryUI,listGUI);
            runNotify();
            assertEquals(song1, internalList.get(0));
            assertEquals(song1, listGUI.getObservableList().get(0));
        } catch (Exception e) {
            fail();
            System.out.println(e.toString());
        }
    }

    private Library getLibrary() {
        try {
            Field field = LibraryUI.class.getDeclaredField("library");
            field.setAccessible(true);
            return (Library) field.get(libraryUI);
        } catch (ReflectiveOperationException e) {
            fail();
            return null;
        }
    }

    // Above method is based on https://gitlab.cs.mcgill.ca/mnassif/303a6t23/-/blob/testprob3/test/activity6/TestStatusPanel.java

    private void runNotify() {
        try {
            Method method = libraryUI.getClass().getDeclaredMethod("notifyObservers");
            method.setAccessible(true);
            method.invoke(libraryUI);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


}
