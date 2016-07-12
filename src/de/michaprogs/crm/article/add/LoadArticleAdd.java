package de.michaprogs.crm.article.add;

import de.michaprogs.crm.CreateDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class LoadArticleAdd {

	private AnchorPane root;
	private ControllerArticleAdd controller;
	
	public LoadArticleAdd(boolean createDialog){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewArticleAdd.fxml"));
			root = loader.load();
			controller = loader.getController();
			
			if(createDialog){
				new CreateDialog("", new Scene(root));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public AnchorPane getRoot() {
		return root;
	}

	public ControllerArticleAdd getController() {
		return controller;
	}
	
}
