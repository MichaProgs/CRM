package de.michaprogs.crm.bolting.data;

import de.michaprogs.crm.CreateDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadBoltingData{
	
	private AnchorPane root;
	private ControllerBoltingData controller;
	private Stage stage = new Stage();
	
	public LoadBoltingData(boolean createDialog){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewBoltingData.fxml"));
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

	public AnchorPane getContent() {
		return root;
	}

	public ControllerBoltingData getController() {
		return controller;
	}
	
}