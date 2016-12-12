package de.michaprogs.crm.documents.deliverybill.search;

import de.michaprogs.crm.CreateDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadDeliverybillSearch {

	private AnchorPane root;
	private ControllerDeliverybillSearch controller = new ControllerDeliverybillSearch();
	private Stage stage = new Stage();
	
	public LoadDeliverybillSearch(boolean createDialog){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewDeliverybillSearch.fxml"));
			loader.setController(controller);
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
	
	public AnchorPane getRoot() {
		return root;
	}

	public ControllerDeliverybillSearch getController(){
		return controller;
	}
	
}
