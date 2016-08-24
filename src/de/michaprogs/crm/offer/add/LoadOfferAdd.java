package de.michaprogs.crm.offer.add;

import de.michaprogs.crm.CreateDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadOfferAdd {

	private AnchorPane root;
	private ControllerOfferAdd controller;
	private Stage stage = new Stage();
	
	public LoadOfferAdd(boolean createDialog, int customerID){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewOfferAdd.fxml"));
			root = loader.load();
			controller = loader.getController();
			
			if(customerID != 0){
				controller.selectCustomer(customerID);
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

	public ControllerOfferAdd getController() {
		return controller;
	}
	
	
	
}
