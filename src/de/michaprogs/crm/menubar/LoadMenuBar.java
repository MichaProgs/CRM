package de.michaprogs.crm.menubar;

import de.michaprogs.crm.CreateDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadMenuBar {

	private AnchorPane root;
	private ControllerMenuBar controller; 
	private Stage stage;
	
	public LoadMenuBar(boolean createDialog){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewMenuBar.fxml"));
			root = loader.load();
			controller = loader.getController();

			if(createDialog){
				new CreateDialog("", stage, new Scene(root));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public AnchorPane getContent() {
		return root;
	}

	public ControllerMenuBar getController() {
		return controller;
	}
	
}
