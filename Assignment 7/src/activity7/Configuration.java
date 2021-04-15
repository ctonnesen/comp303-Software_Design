package activity7;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Function;

public class Configuration
{
	private final String name;
	private final String leftLabel;
	private final String centerLabel;
	private final String rightLabel;
	private final EnumMap<PanelType, Function< List<AbstractMenuItemSource>, List<AbstractMenuItemSource>>> strategy;
	
	
	public Configuration(String pName, String pLeftLabel, String pCenterLabel, String pRightLabel, EnumMap<PanelType, Function< List<AbstractMenuItemSource>, List<AbstractMenuItemSource>>> pStrategy) {
		
		if (!pStrategy.containsKey(PanelType.LEFT)) throw new IllegalArgumentException ("Missing sorting strategy for left panel");
		if (!pStrategy.containsKey(PanelType.CENTER)) throw new IllegalArgumentException ("Missing sorting strategy for center panel");
		if (!pStrategy.containsKey(PanelType.RIGHT)) throw new IllegalArgumentException ("Missing sorting strategy for right panel");
		
		name = pName;
		leftLabel = pLeftLabel;
		centerLabel = pCenterLabel;
		rightLabel = pRightLabel;
		strategy = pStrategy;
	}
	
	public String GetName() {
		return name;
	}
	
	public String GetLeftLabel() {
		return leftLabel;
	}
	
	public String GetCenterLabel() {
		return centerLabel;
	}
	
	public String GetRightLabel() {
		return rightLabel;
	}
	
	public EnumMap<PanelType, Function< List<AbstractMenuItemSource>, List<AbstractMenuItemSource>>> GetStrategy() {
		return strategy;
	}
	
}
