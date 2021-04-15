package activity7;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class MenuView implements Observer
{
	private EnumMap<PanelType, Function< List<AbstractMenuItemSource>, List<AbstractMenuItemSource>>> aConfigurationStrategies;
	private ListView<AbstractMenuItemSource> aLeft;
	private ListView<AbstractMenuItemSource> aRight;
	private ListView<AbstractMenuItemSource> aCenter;

	private final Iterable<AbstractMenuItemSource> aMenu;

	public MenuView(Menu pMenu, ListView<AbstractMenuItemSource> pLeft,
					ListView<AbstractMenuItemSource> pCenter,
					ListView<AbstractMenuItemSource> pRight,
					EnumMap<PanelType, Function< List<AbstractMenuItemSource>, List<AbstractMenuItemSource>>> pConfigurations) {

		this.aMenu=pMenu;
		this.aConfigurationStrategies = pConfigurations;
		this.aCenter = pCenter;
		this.aRight = pRight;
		this.aLeft = pLeft;

		update();
		pMenu.addObserver(this);
	}

	
	public void update() {
		//pull updated menu items
		ArrayList<AbstractMenuItemSource> items = new ArrayList<>();
		aMenu.forEach(items::add);

		//apply strategies and distribute to listviews

		aLeft.getItems().setAll(aConfigurationStrategies.get(PanelType.LEFT).apply(items));
		aCenter.getItems().setAll(aConfigurationStrategies.get(PanelType.CENTER).apply(items));
		aRight.getItems().setAll(aConfigurationStrategies.get(PanelType.RIGHT).apply(items));
	}

	public void SetConfigurations(EnumMap<PanelType, Function< List<AbstractMenuItemSource>, List<AbstractMenuItemSource>>> pConfigurationStrategies){
		assert pConfigurationStrategies.size() == 3;
		this.aConfigurationStrategies = pConfigurationStrategies;
		update();
	}


}