package de.michaprogs.crm.paths;

import de.michaprogs.crm.CreateDialog;
import de.michaprogs.crm.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadPaths {

	private ControllerPaths controller = new ControllerPaths();
	private AnchorPane root;
	private Stage stage;
	
	public LoadPaths(boolean createDialog, Main main){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewPaths.fxml"));
			controller.setMain(main);
			loader.setController(controller);
			root = loader.load();
			
			if(createDialog){
				new CreateDialog("", stage, new Scene(root));
				controller.setStage(stage);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public AnchorPane getContent(){
		return root;
	}
	
	public ControllerPaths getController(){
		return controller;
	}
	
}
