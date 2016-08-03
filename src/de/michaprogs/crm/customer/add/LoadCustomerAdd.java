package de.michaprogs.crm.customer.add;

import de.michaprogs.crm.CreateDialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadCustomerAdd {

	@FXML private AnchorPane root;
	@FXML private ControllerCustomerAdd controller;
	private Stage stage = new Stage();
	
	public LoadCustomerAdd(boolean createDialog){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewCustomerAdd.fxml"));
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
	
	public AnchorPane getContent(){
		return root;
	}
	
	public ControllerCustomerAdd getController(){
		return controller;
	}
	
}
