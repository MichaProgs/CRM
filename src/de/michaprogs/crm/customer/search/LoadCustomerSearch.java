package de.michaprogs.crm.customer.search;

import de.michaprogs.crm.CreateDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadCustomerSearch {

	private AnchorPane root;
	private ControllerCustomerSearch controller;
	private Stage stage = new Stage();
	
	public LoadCustomerSearch(boolean createDialog){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewCustomerSearch.fxml"));
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
	
	public ControllerCustomerSearch getController(){
		return controller;
	}
	
}
