package de.michaprogs.crm.article.bolting.add;

import de.michaprogs.crm.CreateDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadBolting{
	
	private AnchorPane root;
	private ControllerBolting controller;
	private Stage stage = new Stage();
	
	public LoadBolting(boolean createDialog){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewBolting.fxml"));
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

	public AnchorPane getRoot() {
		return root;
	}

	public ControllerBolting getController() {
		return controller;
	}
	
}