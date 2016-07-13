package de.michaprogs.crm.article.search;

import de.michaprogs.crm.CreateDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadArticleSearch {

	private AnchorPane root;
	private ControllerArticleSearch controller;
	private Stage stage = new Stage();
	
	public LoadArticleSearch(boolean createDialog){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewArticleSearch.fxml"));
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

	public ControllerArticleSearch getControllerSearch(){
		return controller;
	}
	
}
