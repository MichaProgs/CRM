package de.michaprogs.crm.offer.data;

import de.michaprogs.crm.CreateDialog;
import de.michaprogs.crm.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadOfferData {

	private AnchorPane root;
	private ControllerOfferData controller = new ControllerOfferData();
	private Stage stage = new Stage();
	
	/**
	 * 
	 * @param createDialog - true: new dialog window / false: set content on center of contentpane
	 * @param offerID - if not 0 the given offer will be selected
	 * @param customerID - if not 0 the given customer will be selected
	 * @param main
	 */
	public LoadOfferData(boolean createDialog, int offerID, int customerID, Main main){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewOfferData.fxml"));
			controller.setMain(main);
			loader.setController(controller);
			root = loader.load();
			
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
