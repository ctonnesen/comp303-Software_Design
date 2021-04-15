package activity6;
import java.util.*;

import javafx.collections.FXCollections ; 

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class StatusPanel extends Parent implements Observer
{
	private Text aText = new Text(); 
	private int size; 
	private List<Playable> listView = new ArrayList<>();
	
	public StatusPanel(Iterator<Playable> iterator) {
		listView.clear(); 
		while(iterator.hasNext()) {
			listView.add(iterator.next()) ;
		}
		this.size = listView.size();
		getChildren().add(aText); 
	}
	
	@Override
	public void ItemChange(Iterator<Playable> iterator)
	{
		listView.clear(); 
		while(iterator.hasNext()) {
			listView.add(iterator.next()) ;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(getStatus(listView.size()));
		aText.setText( sb.toString() );
	}
	
	public String getStatus(int aSize) {
		StringBuilder sb = new StringBuilder(); 
		if((this.size == 0 && aSize == 0) || (this.size == aSize)) {
			return "Library empty";
		}
		else if(aSize> this.size) {
			this.size = aSize ; 

			sb.append("Item ADDED, Items in Library: " + size); 
		}
		else if (aSize < this.size) {
			this.size = aSize ; 

			sb.append("Song REMOVE, Items in Library: "+ size) ; 	
			}
		System.out.println(sb.toString());
		return sb.toString(); 
	}
}
