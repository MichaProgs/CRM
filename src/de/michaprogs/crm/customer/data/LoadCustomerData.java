package de.michaprogs.crm.customer.data;

import de.michaprogs.crm.CreateDialog;
import de.michaprogs.crm.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadCustomerData {

	private AnchorPane root;
	private ControllerCustomerData controller = new ControllerCustomerData();
	private Stage stage = new Stage();
	
	public LoadCustomerData(boolean createDialog, Main main){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewCustomerData.fxml"));
			controller.setMain(main);
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
	
	public AnchorPane getContent(){
		return root;
	}
	
	public ControllerCustomerData getController(){
		return controller;
	}
	
}
