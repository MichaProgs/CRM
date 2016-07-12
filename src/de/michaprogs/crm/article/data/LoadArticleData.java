package de.michaprogs.crm.article.data;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class LoadArticleData {

	private AnchorPane root;
	private ControllerArticleData controller;
	
	public LoadArticleData(){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewArticleData.fxml"));
			root = loader.load();
			controller = loader.getController();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public AnchorPane getContent(){
		return root;
	}
	
	public ControllerArticleData getController(){
		return controller;
	}
	
}
