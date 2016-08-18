package de.michaprogs.crm.offer.search;

import de.michaprogs.crm.CreateDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadOfferSearch {

	private AnchorPane root;
	private ControllerOfferSearch controller;
	private Stage stage = new Stage();
	
	public LoadOfferSearch(boolean createDialog){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewOfferSearch.fxml"));
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

	public ControllerOfferSearch getController(){
		return controller;
	}
	
}
