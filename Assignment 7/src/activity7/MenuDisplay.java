package activity7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/*
 * Skeleton code that illustrates the general layout
 * expected. Modify as necessary.
 */
public class MenuDisplay extends Application
{
	private static PanelView leftPanel;
	private static PanelView rightPanel;
	private static PanelView centerPanel;
	private ArrayList<Configuration> configList = new ArrayList<Configuration>();
	/**
	 * Launches the application.
	 * @param pArgs This program takes no argument.
	 */
	public static void main(String[] pArgs) 
	{
        launch(pArgs);
    }
    
    @Override
    public void start(Stage pStage) 
    {
		//These are the most obvious predicates and are added to the list in the Filters class in order to save the developer that extends the GUI to inlude filtering, some time.
    	Predicate<AbstractMenuItemSource> expensiveFilter = menuItem -> menuItem.getPrice() > 10;
    	Predicate<AbstractMenuItemSource> mainFilter = menuItem -> menuItem.getFoodType() == FoodType.MAIN;
    	Predicate<AbstractMenuItemSource> sidesFilter = menuItem -> menuItem.getFoodType() == FoodType.SIDE;
    	Predicate<AbstractMenuItemSource> drinksFilter = menuItem -> menuItem
    			.getFoodType() == FoodType.DRINK;
    	Predicate<AbstractMenuItemSource> snacksFilter = menuItem -> menuItem
    			.getFoodType() == FoodType.SNACK;
    	Predicate<AbstractMenuItemSource> veganFilter = menuItem -> menuItem.getDietCategories()
    			.contains(DietCategory.VEGAN);
    	Predicate<AbstractMenuItemSource> vegetarianFilter = menuItem -> menuItem.getDietCategories()
    			.contains(DietCategory.VEGETARIAN);
    	Predicate<AbstractMenuItemSource> glutenFreeFilter = menuItem -> menuItem.getDietCategories()
    			.contains(DietCategory.GLUTENFREE);
    	Filters.add("Expensive Filter", expensiveFilter);
    	Filters.add("Cheap Filter", expensiveFilter.negate());
    	Filters.add("Main Filter", mainFilter);
    	Filters.add("Sides Filter", sidesFilter);
    	Filters.add("Drinks Filter", drinksFilter);
    	Filters.add("Snacks Filter", snacksFilter);
    	Filters.add("Vegan Filter", veganFilter);
    	Filters.add("Vegetarian Filter", vegetarianFilter);
    	Filters.add("Gluten Free Filter", glutenFreeFilter);
    	
    	BorderPane main = new BorderPane();
    	Menu menu=new Menu();
    	ArrayList<DietCategory> diet=new ArrayList<>();
    	diet.add(DietCategory.GLUTENFREE);
    	diet.add(DietCategory.ORGANIC);


    	//---Add Menu Items---//
    	MenuItem poutine=MenuItem.get("Poutine",3.00,FoodType.MAIN, diet);
    	MenuItem soup=MenuItem.get("Soup",6.00,FoodType.MAIN);
    	MenuItem spaghetti=MenuItem.get("Spaghetti",5.78,FoodType.MAIN);
    	MenuItem chips=MenuItem.get("Chips",1,FoodType.SNACK);
    	MenuItem Peanuts=MenuItem.get("Peanuts",1,FoodType.SNACK);
    	MenuItem Chocolate=MenuItem.get("Chocolate Bar",1.75,FoodType.SNACK);
    	MenuItem coffe=MenuItem.get("Coffee",2,FoodType.DRINK);
    	MenuItem tea=MenuItem.get("Tea",1.75,FoodType.DRINK);
    	MenuItem beer=MenuItem.get("Beer",2.50,FoodType.DRINK);

    	menu.addItem(soup);
    	menu.addItem(spaghetti);
    	menu.addItem(poutine);
    	menu.addItem(chips);
    	menu.addItem(Peanuts);
    	menu.addItem(Chocolate);
    	menu.addItem(coffe);
    	menu.addItem(tea);
    	menu.addItem(beer);
		

    	//---Set up Menu Selection Strategies---//
    	EnumMap<PanelType, Function< List<AbstractMenuItemSource>, List<AbstractMenuItemSource>>> byFoodType = new EnumMap<>(PanelType.class);
		byFoodType.put(PanelType.LEFT,MenuSelectionStrategy::drinks);
		byFoodType.put(PanelType.CENTER,MenuSelectionStrategy::mainsAndCombos);
		byFoodType.put(PanelType.RIGHT,MenuSelectionStrategy::snacks);

		EnumMap<PanelType, Function< List<AbstractMenuItemSource>, List<AbstractMenuItemSource>>> byPrice = new EnumMap<>(PanelType.class);
		byPrice.put(PanelType.LEFT,MenuSelectionStrategy::cheapest);
		byPrice.put(PanelType.CENTER,MenuSelectionStrategy::allItems);
		byPrice.put(PanelType.RIGHT,MenuSelectionStrategy::mostExpensive);
		
		EnumMap<PanelType, Function< List<AbstractMenuItemSource>, List<AbstractMenuItemSource>>> byDiet = new EnumMap<>(PanelType.class);
		byDiet.put(PanelType.LEFT,MenuSelectionStrategy::vegan);
		byDiet.put(PanelType.CENTER,MenuSelectionStrategy::vegetarian);
		byDiet.put(PanelType.RIGHT,MenuSelectionStrategy::glutenFree);
		
		EnumMap<PanelType, Function< List<AbstractMenuItemSource>, List<AbstractMenuItemSource>>> bySnacktime = new EnumMap<>(PanelType.class);
		bySnacktime.put(PanelType.LEFT,MenuSelectionStrategy::snacks);
		bySnacktime.put(PanelType.CENTER,MenuSelectionStrategy::snacks);
		bySnacktime.put(PanelType.RIGHT,MenuSelectionStrategy::snacks);

		
		//---Add Configurations to list---//
		configList.add(new Configuration("Drinks/Snacks", "Drinks", "Mains&Combos", "Snacks", byFoodType));
		configList.add(new Configuration("Cheap/Expensive", "Cheap", "All Items", "Expensive", byPrice));
		configList.add(new Configuration("Diet", "Vegan", "Vegetarian", "Gluten Free", byDiet));
		configList.add(new Configuration("SNACK TIME", "Snack", "Snack", "Snack", bySnacktime));
		
		
		//---If more than 3 Configurations exist, choose up to 3 Configurations to be displayed--//
		ArrayList<Configuration> configsToShow;
		if (configList.size() > 3) {
			int[] configIndices = {0,1,2};
			configsToShow = SetConfigsToShow(configIndices);
		} else {
			configsToShow = configList;
		}
		

				
		//---Set up Menu View---//
		ListView<AbstractMenuItemSource> left = new ListView<>();
		ListView<AbstractMenuItemSource> right = new ListView<>();
		ListView<AbstractMenuItemSource> center = new ListView<>();

		Configuration initialConfig = configsToShow.get(0);
		MenuView aMenuView = new MenuView(menu, left, center, right, initialConfig.GetStrategy());
		
		leftPanel = new PanelView(initialConfig.GetLeftLabel(), left);
		rightPanel = new PanelView(initialConfig.GetRightLabel(), right);
		centerPanel = new PanelView(initialConfig.GetCenterLabel(), center);

    	main.setLeft(leftPanel);
    	main.setCenter(centerPanel);
    	main.setRight(rightPanel);
    	

    	pStage.setScene(new Scene(new VBox(main, createControl(aMenuView, configsToShow))));
        HBox bottom = new HBox();

        Button addButton = new Button("Add item");
    	bottom.getChildren().add(addButton);
    	addButton.setOnAction(event ->{
    		Stage popup=new Stage();
    		VBox content=new VBox();
    		Label title1 = new Label("Name:");
    		TextField name=new TextField();
    		name.setPromptText("Please type the new item's Name here");
    		Label title2 = new Label("Price:");
    		TextField price=new TextField();
    		price.setPromptText("Please type the new item's Price here");
    		Button confirmButton=new Button("Confirm");
    		ToggleGroup group = new ToggleGroup();
    		RadioButton button1 = new RadioButton("MAIN");
    		button1.setToggleGroup(group);
    		button1.setSelected(true);
    		RadioButton button2 = new RadioButton("SNACK");
    		button2.setToggleGroup(group);
    		RadioButton button3 = new RadioButton("DRINK");
    		button3.setToggleGroup(group);
    		RadioButton button4 = new RadioButton("SIDE");
    		button4.setToggleGroup(group);
			HBox dietCategoryAddition=new HBox();
			ArrayList<CheckBox> tempHolder = new ArrayList<>();
			for(DietCategory current : DietCategory.values()) {
				CheckBox button = new CheckBox(current.name());
				dietCategoryAddition.getChildren().add(button);
				tempHolder.add(button);
			}
			dietCategoryAddition.setSpacing(10);
    		HBox subcontent=new HBox();
    		subcontent.getChildren().add(button1);
    		subcontent.getChildren().add(button2);
    		subcontent.getChildren().add(button3);
    		subcontent.getChildren().add(button4);
    		subcontent.setSpacing(10);

    		confirmButton.setOnAction(pEvent -> {
    			try {
					String inputName = name.getText();
					double inputPrice = Double.parseDouble(price.getText());
					String foodtype = ((RadioButton) (group.getSelectedToggle())).getText();
					FoodType type = FoodType.valueOf(foodtype);
					ArrayList<DietCategory> dietCategories = new ArrayList<>();
					for (CheckBox current : tempHolder) {
						if (current.isSelected()) {
							dietCategories.add(DietCategory.valueOf(current.getText()));
						}
					}
					MenuItem newItem = MenuItem.get(inputName, inputPrice, type, dietCategories);
					menu.addItem(newItem);
					System.out.println(newItem + " is added to the Menu!");
					popup.close();
				} catch (Exception e) {
					System.out.println("Please ensure the price is a number" + System.lineSeparator());
				} catch (AssertionError e) {
					System.out.println("Please input a valid price"+ System.lineSeparator());
				}
			});

    		content.getChildren().add(title1);
    		content.getChildren().add(name);
    		content.getChildren().add(title2);
    		content.getChildren().add(price);
    		content.getChildren().add(subcontent);
    		content.getChildren().add(dietCategoryAddition);
    		content.getChildren().add(confirmButton);
    		Scene popupScene=new Scene(content,400,200);
    		popup.setScene(popupScene);
    		popup.show();
    	});
    	
    	Button deleteButton = new Button("Remove item");
    	deleteButton.setOnAction(event -> {
			//get all listview items
            List<AbstractMenuItemSource> selectedItems = Stream.of(right.getSelectionModel().getSelectedItems(),
					left.getSelectionModel().getSelectedItems(),
					center.getSelectionModel().getSelectedItems())
					.flatMap(Collection::stream)
					.collect(Collectors.toList());
            for(AbstractMenuItemSource item: selectedItems) {
            	menu.removeItem(item);
                System.out.println(item +" is removed from the menu!");
            }
        });
    	bottom.getChildren().add(deleteButton);


    	Button createComboButton = new Button("Create Combo");
    	createComboButton.setOnAction(event -> {
			try {
				List<AbstractMenuItemSource> selectedItems = Stream.of(right.getSelectionModel().getSelectedItems(),
						left.getSelectionModel().getSelectedItems(),
						center.getSelectionModel().getSelectedItems())
						.flatMap(Collection::stream)
						.collect(Collectors.toList());
				ArrayList<AbstractMenuItemSource> copy = new ArrayList(selectedItems);
				ComboMenuItem combo = ComboMenuItem.get(false, copy);
				menu.addItem(combo);
			} catch (ExceptionInInitializerError e) {
				System.out.println("Please input more than one item for a combo and assure that no inputted items are combos" + System.lineSeparator());
			}

		});
    	bottom.getChildren().add(createComboButton);

    	Button putOnSaleButton = new Button("Put it on sale/Change the price back");
    	putOnSaleButton.setOnAction(event -> {
			List<AbstractMenuItemSource> selectedItems = Stream.of(right.getSelectionModel().getSelectedItems(),
					left.getSelectionModel().getSelectedItems(),
					center.getSelectionModel().getSelectedItems())
					.flatMap(Collection::stream)
					.collect(Collectors.toList());
            for(AbstractMenuItemSource item: List.copyOf(selectedItems)) {
            	menu.putItemOnsale(item);
            }
        });
    	bottom.getChildren().add(putOnSaleButton);

    	bottom.setSpacing(70.0);
    	main.setBottom(bottom);
        pStage.show();
    }
    
    private static HBox createControl(MenuView pMenuView, ArrayList<Configuration> configs) {
		HBox control = new HBox();
		control.setPadding(new Insets(5));
		control.setAlignment(Pos.CENTER);
		ToggleGroup group = new ToggleGroup();
		
		for (Configuration c : configs)
		{
		RadioButton button = new RadioButton(c.GetName());
		
		
		button.setOnAction(event -> {
			pMenuView.SetConfigurations(c.GetStrategy());
			
			Label e = (Label) leftPanel.getChildren().get(0);
			e.setText(c.GetLeftLabel());
			
			e = (Label) centerPanel.getChildren().get(0);
			e.setText(c.GetCenterLabel());

			e = (Label) rightPanel.getChildren().get(0);
			e.setText(c.GetRightLabel());
		});
		
		
		button.setPadding(new Insets(5));
		button.setToggleGroup(group);
		control.getChildren().add(button);
		}
		((RadioButton)control.getChildren().get(0)).setSelected(true);
		return control;
	}
    
    private ArrayList<Configuration> SetConfigsToShow(int[] configIndex) {
    	assert configIndex.length == 3;
    	
    	ArrayList<Configuration> configsToShow = new ArrayList<Configuration>();
    	
    	for (int i = 0; i < configIndex.length; i++) {
    		assert i >= 0;
        	assert i < configList.size();
        	
    		configsToShow.add(configList.get(configIndex[i]));
    	}
    	
    	return configsToShow;
    	
    }
    
    
}
