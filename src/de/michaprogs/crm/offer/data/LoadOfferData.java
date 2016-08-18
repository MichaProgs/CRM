package de.michaprogs.crm.offer.data;

import de.michaprogs.crm.CreateDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadOfferData {

	private AnchorPane root;
	private ControllerOfferData controller;
	private Stage stage = new Stage();
	
	public LoadOfferData(boolean createDialog){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewOfferData.fxml"));
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

	/*
	 * GETTER & SETTER
	 */
	public AnchorPane getContent() {
		return root;
	}

	public ControllerOfferData getController() {
		return controller;
	}
	
	
	
}
