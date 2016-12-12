package de.michaprogs.crm.deliverybill.data;

import de.michaprogs.crm.CreateDialog;
import de.michaprogs.crm.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadDeliverybillData {

	private AnchorPane content;
	private ControllerDeliverybillData controller = new ControllerDeliverybillData();
	private Stage stage = new Stage();
	
	public LoadDeliverybillData(boolean createDialog, int deliverybillID, int customerID, Main main){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewDeliverybillData.fxml"));
			controller.setMain(main);
			loader.setController(controller);			
			content = loader.load();
			
			if(deliverybillID != 0 && customerID != 0){
				controller.selectDeliverybill(deliverybillID, customerID);
			}
			
			if(createDialog){
				controller.setStage(stage);
				new CreateDialog("", stage, new Scene(content));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public AnchorPane getContent(){
		return content;
	}
	
	public ControllerDeliverybillData getController(){
		return controller;
	}
	
}
