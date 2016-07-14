package de.michaprogs.crm.supplier.search;

import de.michaprogs.crm.CreateDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadSupplierSearch {

	private AnchorPane root;
	private ControllerSupplierSearch controller;
	private Stage stage = new Stage();
	
	public LoadSupplierSearch(boolean createDialog){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewSupplierSearch.fxml"));
			root = loader.load();
			controller = loader.getController();
			
			if(createDialog){
				controller.setStage(stage);
				new CreateDialog("", stage, new Scene(root));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public AnchorPane getContent() {
		return root;
	}

	public ControllerSupplierSearch getController() {
		return controller;
	}

	public Stage getStage() {
		return stage;
	}
	
}
