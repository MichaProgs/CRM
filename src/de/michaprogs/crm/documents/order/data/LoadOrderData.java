package de.michaprogs.crm.order.data;

import de.michaprogs.crm.CreateDialog;
import de.michaprogs.crm.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadOrderData {

	private AnchorPane root;
	private ControllerOrderData controller = new ControllerOrderData();
	private Stage stage = new Stage();
	
	public LoadOrderData(boolean createDialog, int orderID, int supplierID, Main main){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewOrderData.fxml"));
			controller.setMain(main);
			loader.setController(controller);
			root = loader.load();
			
			if(orderID != 0 && supplierID != 0){
				controller.selectOrder(orderID, supplierID);
			}
			
			if(createDialog){
				controller.setStage(stage);
				new CreateDialog("", stage, new Scene(root));
			}			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public AnchorPane getContent(){
		return root;
	}
	
	public ControllerOrderData getController(){
		return controller;
	}
	
}
