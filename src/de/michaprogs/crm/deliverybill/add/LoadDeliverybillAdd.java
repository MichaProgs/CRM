package de.michaprogs.crm.deliverybill.add;

import de.michaprogs.crm.CreateDialog;
import de.michaprogs.crm.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadDeliverybillAdd {

	private AnchorPane content;
	private ControllerDeliverybillAdd controller = new ControllerDeliverybillAdd();
	private Stage stage = new Stage();
	
	public LoadDeliverybillAdd(boolean createDialog, Main main, int customerID){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewDeliverybillAdd.fxml"));
			controller.setMain(main);
			loader.setController(controller);
			content = loader.load();
			
			if(customerID != 0){
				controller.selectCustomer(customerID);
			}
			
			if(createDialog){
				controller.setStage(stage);
				new CreateDialog("", stage, new Scene(content));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/*
	 * GETTER & SETTER
	 */
	public AnchorPane getContent() {
		return content;
	}

	public ControllerDeliverybillAdd getController() {
		return controller;
	}
	
	
	
}
