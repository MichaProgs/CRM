package de.michaprogs.crm.documents.order.search;

import de.michaprogs.crm.CreateDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadOrderSearch {

	private AnchorPane root;
	private ControllerOrderSearch controller = new ControllerOrderSearch();
	private Stage stage = new Stage();
	
	public LoadOrderSearch(boolean createDialog){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewOrderSearch.fxml"));
			loader.setController(controller);
			root = loader.load();

			if(createDialog){
				controller.setStage(stage);
				new CreateDialog("", stage, new Scene(root));
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public AnchorPane getRoot() {
		return root;
	}

	public ControllerOrderSearch getController(){
		return controller;
	}
	
}
