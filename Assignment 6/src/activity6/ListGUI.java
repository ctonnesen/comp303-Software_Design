package activity6;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.Iterator;

public class ListGUI extends Parent implements Observer {
    private ObservableList<Playable> observableList = FXCollections.observableArrayList();
    public ListGUI(Iterator<Playable> iterator) {
    	ItemChange(iterator);
    }


    // This method goes through the newest version of the Library iterator and changes the listView accordingly.

    @Override
    public void ItemChange(Iterator<Playable> iterator) {
        observableList.clear();
        while (iterator.hasNext()) {
            observableList.add(iterator.next());
        }
    }


    public ObservableList<Playable> getObservableList() {
        return observableList;
    }
}



