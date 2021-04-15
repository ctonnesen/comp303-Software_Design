package activity7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConfigurationTest
{
	private EnumMap<PanelType, Function< List<AbstractMenuItemSource>, List<AbstractMenuItemSource>>> bySorting1 = new EnumMap<>(PanelType.class);
	private EnumMap<PanelType, Function< List<AbstractMenuItemSource>, List<AbstractMenuItemSource>>> bySorting2 = new EnumMap<>(PanelType.class);
	private EnumMap<PanelType, Function< List<AbstractMenuItemSource>, List<AbstractMenuItemSource>>> bySorting3 = new EnumMap<>(PanelType.class);
	
	@BeforeEach
	public void setup() {
		bySorting1.put(PanelType.LEFT,MenuSelectionStrategy::drinks);
		bySorting1.put(PanelType.CENTER,MenuSelectionStrategy::mainsAndCombos);
		bySorting1.put(PanelType.RIGHT,MenuSelectionStrategy::snacks);

		
		bySorting2.put(PanelType.LEFT,MenuSelectionStrategy::cheapest);
		bySorting2.put(PanelType.CENTER,MenuSelectionStrategy::allItems);
		bySorting2.put(PanelType.RIGHT,MenuSelectionStrategy::mostExpensive);
		
		
		bySorting3.put(PanelType.LEFT,MenuSelectionStrategy::vegan);
		bySorting3.put(PanelType.CENTER,MenuSelectionStrategy::vegetarian);
		bySorting3.put(PanelType.RIGHT,MenuSelectionStrategy::glutenFree);
	}
	
	@Test
	public void CreateConfig() {
		Configuration testConfig;
		testConfig = new Configuration("title","left","center","right", bySorting1);
		
		assertEquals("left", testConfig.GetLeftLabel());
		assertEquals("center", testConfig.GetCenterLabel());
		assertEquals("right", testConfig.GetRightLabel());
		assert testConfig.GetStrategy() == bySorting1;
	}
	
	
	@Test
	public void CreateConfigMissingPanel() {
		
		bySorting1.remove(PanelType.LEFT);
		bySorting2.remove(PanelType.CENTER);
		bySorting3.remove(PanelType.RIGHT);
		
		try {
			Configuration testConfig = new Configuration("title","left","center","right", bySorting1);
		    fail( "Constructor should have thrown an exception due to missing left panel");
		} catch (IllegalArgumentException expectedException) {		
		}
		
		try {
			Configuration testConfig = new Configuration("title","left","center","right", bySorting2);
		    fail( "Constructor should have thrown an exception due to missing center panel");
		} catch (IllegalArgumentException expectedException) {			
		}
		
		try {
			Configuration testConfig = new Configuration("title","left","center","right", bySorting3);
		    fail( "Constructor should have thrown an exception due to missing right panel");
		} catch (IllegalArgumentException expectedException) {
		}
	}


}
