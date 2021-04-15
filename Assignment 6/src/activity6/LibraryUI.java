package activity6;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Mock-up of a partial user interface for the music library.
 * 
 * This code makes a simplistic use of the JavaFX library
 * to limit the code comprehension efforts not directly related 
 * to the course objectives. 
 *
 */
public class LibraryUI extends Application implements Model {
	private List<Observer> aObservers = new ArrayList<>();
	private Library library = new Library();
	ListView<Playable> listView;
	private Label label;
	private File selectedFile;
	HBox topBarView = new HBox();
	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;
	
	/**
	 * Launches the application.
	 * @param pArgs This program takes no argument.
	 */
	public static void main(String[] pArgs) 
	{
		launch(pArgs);
    }
	private void TextInputBox() {
		 
        TextInputDialog dialog = new TextInputDialog("New Playlist");
 
        dialog.setTitle("Add new Playlist");
        dialog.setHeaderText("New Playlist");
        dialog.setContentText("Name :");
 
        Optional<String> result = dialog.showAndWait();
 
        result.ifPresent(name -> {
            this.label.setText(name);
        });
    }


    /**
	 * For the "Add Song", we initialize a new view object of listGUI and add it to the Observer list. We assign the local
	 * variable responsible for display (listView) to the object inside listRepresent. Whenever we click "Add Song", the
	 * anonymous class runs, adds the file to the library as a Song, and notifies the Observers, which update the views.
     */

    @Override
    public void start(Stage pStage) 
    {
    	// create library UI
		BorderPane root = new BorderPane();
		
		// create and add listGUI
    	ListGUI listRepresent = new ListGUI(library.iterator());
    	addObserver(listRepresent);
    	StatusPanel status = new StatusPanel(library.iterator()); 
    	addObserver(status);
    	listView = new ListView<>(listRepresent.getObservableList());
    	
    	//Ability to support multiple selections
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		// create buttons
		this.label = new Label();
		Button addPlaylist = new Button("Add Library");
    	Button addButton = new Button("Add Song");
    	Button deleteButton = new Button("Remove item");
    	topBarView.getChildren().addAll(addPlaylist,addButton, deleteButton, status);
		
    	// set views
    	root.setTop(topBarView);
    	root.setCenter(listView);
    	//Add library handler
    	addPlaylist.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent)
			{
				try {
					TextInputBox();
					Playlist newplaylist = new Playlist(label.getText());
					library.addItem(newplaylist);
					notifyObservers();
					
				}catch (Exception e) {
					System.out.print('.');
				}
				
			}
    		
    	});
    	// addButton handler
    	addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				try {
					selectedFile = selectFile(pStage).get();
					library.addItem(Song.get(selectedFile,selectedFile.getName()));
					notifyObservers();
				} catch (Exception e) {
					System.out.println("No song selected");
				}
			}
		});
    	
    	deleteButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent pActionEvent) {
				try {
					for (Playable p : getSelected(listView)) {
						library.removeItem(p);
					}
					notifyObservers();
				}
				catch(Exception e)
					{
						System.out.println("No item selected");
					}
				}
    	});

        pStage.setScene(new Scene(root, WIDTH, HEIGHT));
        pStage.show();
    }

	// Sample code to activate the file chooser
    private static Optional<File> selectFile(Stage pStage)
    {
    	FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showOpenDialog(pStage);
		return Optional.ofNullable(selectedFile);
    }

    // Sample code to get the selected object from a ListView
    private static ObservableList<Playable> getSelected(ListView<Playable> pView)
    {
    	return pView.getSelectionModel().getSelectedItems();
    }

    @Override
    public void notifyObservers(){
		for (Observer current : aObservers) {
			current.ItemChange(library.iterator());
		}
	}
    @Override
	public void addObserver(Observer pObserver) {
		aObservers.add(pObserver);
	}
    @Override
	public void removeObserver(Observer pObserver) {
    	aObservers.remove(pObserver);
	}
}


