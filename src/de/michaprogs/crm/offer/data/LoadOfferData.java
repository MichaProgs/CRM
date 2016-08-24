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
	
	public LoadOfferData(boolean createDialog, int offerID, int customerID){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewOfferData.fxml"));
			root = loader.load();
			controller = loader.getController();
			
			if(offerID != 0 && customerID != 0){
				controller.selectOffer(offerID, customerID);
			}
			
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
