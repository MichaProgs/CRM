package de.michaprogs.crm.contact.data;

import de.michaprogs.crm.CreateDialog;
import de.michaprogs.crm.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadContactData {

	private AnchorPane root;
	private ControllerContactData controller = new ControllerContactData();
	private Stage stage = new Stage();
	
	public LoadContactData(boolean createDialog, Main main){
		
		try{
			
			FXMLLoader loader = new FXMLLoader();
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
	
	public ControllerContactData getController(){
		return controller;
	}
	
	public void setController(ControllerContactData controller){
		this.controller = controller;
	}
	
}
