package activity7;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.function.Function;

public class PanelView extends VBox {
    private static final int WIDTH_SIDE = 150;
    private static final int WIDTH_CENTER = 200;


    private static final String STYLE =
            "-fx-pref-height: 300px; -fx-border-width: 1; -fx-border-color: black;" +
                    "-fx-background-color: lightgrey; -fx-padding: 5px; -fx-alignment: top-center";



    public PanelView(String pName, ListView<AbstractMenuItemSource> pView){

        //configure panel
        pView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.setStyle(STYLE);
        this.setPrefWidth(WIDTH_SIDE);
        Label title = new Label(pName);
        title.setStyle("-fx-font-weight: bold");
        this.getChildren().add(title);
        this.getChildren().add(pView);
    }

}
