package de.michaprogs.crm.position.edit;

import de.michaprogs.crm.CreateDialog;
import de.michaprogs.crm.article.ModelArticle;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadEditPosition {

	private AnchorPane root;
	private ControllerEditPosition controller;
	private Stage stage = new Stage();
	
	public LoadEditPosition(boolean createDialog, ObservableList<ModelArticle> obsListArticle, int index){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewEditPosition.fxml"));
			root = loader.load();
			controller = loader.getController();
			controller.setArticleData(obsListArticle, index);
			
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

	public ControllerEditPosition getController() {
		return controller;
	}
	
}
