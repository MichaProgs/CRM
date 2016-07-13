package de.michaprogs.crm.article.barrelsize.add;

import de.michaprogs.crm.CreateDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadBarrelsize{
	
	private AnchorPane root;
	private ControllerBarrelsize controller;
	private Stage stage = new Stage();
	
	public LoadBarrelsize(boolean createDialog){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewBarrelsize.fxml"));
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

	public ControllerBarrelsize getController() {
		return controller;
	}
	
}