package de.michaprogs.crm.position.add;

import de.michaprogs.crm.CreateDialog;
import de.michaprogs.crm.article.ModelArticle;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadAddPosition {

	private AnchorPane root;
	private ControllerAddPosition controller;
	private Stage stage = new Stage();
	
	public LoadAddPosition(boolean createDialog, ObservableList<ModelArticle> obsListArticle){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAddPosition.fxml"));
			root = loader.load();
			controller = loader.getController();
			controller.setObsListArticle(obsListArticle);
			
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

	public ControllerAddPosition getController() {
		return controller;
	}
	
}
