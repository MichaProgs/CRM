package de.michaprogs.crm.properties;

import de.michaprogs.crm.articlecategory.add.LoadArticleCategoryAdd;
import de.michaprogs.crm.barrelsize.add.LoadBarrelsizeAdd;
import de.michaprogs.crm.bolting.add.LoadBoltingAdd;
import de.michaprogs.crm.clerk.add.LoadClerkAdd;
import de.michaprogs.crm.warehouse.add.LoadWarehouseAdd;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ControllerProperties {

	@FXML private BorderPane content;
	
	@FXML private TreeView<String> treeNavigation;	
		  private TreeItem<String> root = new TreeItem<String>("Einstellungen");
		  
		  private TreeItem<String> itemVariables = new TreeItem<String>("Variablen");
		  
		  private TreeItem<String> itemVariablesBolting = new TreeItem<String>("Verschraubungen");
		  private TreeItem<String> itemVariablesBarrelsize = new TreeItem<String>("Gebindegrößen");
		  private TreeItem<String> itemVariablesArticleCategories = new TreeItem<String>("Artikel-Kategorien");
		  private TreeItem<String> itemVariablesWarehouse = new TreeItem<String>("Lager");
		  private TreeItem<String> itemVariablesClerk = new TreeItem<String>("Sachbearbeiter");
	
//	@FXML private Button btnBolting;
//	@FXML private Button btnBarrelsize;
//	@FXML private Button btnWarehouse;
//	@FXML private Button btnClerk;
	
		  private LoadBarrelsizeAdd barrelsize = new LoadBarrelsizeAdd(false);
		  private LoadBoltingAdd bolting = new LoadBoltingAdd(false);
		  private LoadClerkAdd clerk = new LoadClerkAdd(false);
		  private LoadWarehouseAdd warehouse = new LoadWarehouseAdd(false);
		  private LoadArticleCategoryAdd articleCategory = new LoadArticleCategoryAdd(false, null);
		  
		  private Stage stage;
	
	public ControllerProperties(){}
	
	@FXML private void initialize(){
	
		content.setCenter(new LoadBoltingAdd(false).getContent());
		
		/* TREE */
		initTreeNavigation();
		
	}
	
	/*
	 * TREE
	 */
	@SuppressWarnings("unchecked")
	private void initTreeNavigation(){
		
		itemVariables.getChildren().addAll(	itemVariablesBarrelsize, 
											itemVariablesBolting,
											itemVariablesArticleCategories,
											itemVariablesWarehouse, 
											itemVariablesClerk);
		itemVariables.setExpanded(true);
		
		root.getChildren().addAll(itemVariables);
		root.setExpanded(true);
		
		treeNavigation.setRoot(root);
		
		treeNavigation.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {

			@Override
			public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> oldValue,
					TreeItem<String> newValue) {

				if(newValue.equals(itemVariablesBarrelsize)){
					content.setCenter(barrelsize.getContent());
				}else if(newValue.equals(itemVariablesBolting)){
					content.setCenter(bolting.getContent());
				}else if(newValue.equals(itemVariablesArticleCategories)){
					content.setCenter(articleCategory.getContent());
				}else if(newValue.equals(itemVariablesClerk)){
					content.setCenter(clerk.getContent());
				}else if(newValue.equals(itemVariablesWarehouse)){
					content.setCenter(warehouse.getContent());
				}
				
				stage.sizeToScene();
				
			}
		});
		
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
}
