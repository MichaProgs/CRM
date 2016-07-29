package de.michaprogs.crm.customer.data;

import de.michaprogs.crm.CreateDialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadCustomerData {

	@FXML private AnchorPane root;
	@FXML private ControllerCustomerData controller;
	private Stage stage = new Stage();
	
	public LoadCustomerData(boolean createDialog){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewArticleData.fxml"));
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
	
	public ControllerCustomerData getController(){
		return controller;
	}
	
}
