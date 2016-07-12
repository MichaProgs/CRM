package de.michaprogs.crm.navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class LoadNavigation {

	private AnchorPane root = null;
	private ControllerNavigation controller = null; 
	
	public LoadNavigation(){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewNavigation.fxml"));
			root = loader.load();
			controller = loader.getController();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public AnchorPane getContent(){
		return root;
	}
	
	public ControllerNavigation getController(){
		return controller;
	}
	
}
