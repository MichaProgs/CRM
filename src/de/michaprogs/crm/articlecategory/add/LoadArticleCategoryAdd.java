package de.michaprogs.crm.articlecategory.add;

import de.michaprogs.crm.CreateDialog;
import de.michaprogs.crm.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadArticleCategoryAdd {

	private AnchorPane root;
	private ControllerArticleCategoryAdd controller = new ControllerArticleCategoryAdd();
	private Stage stage = new Stage();
	
	public LoadArticleCategoryAdd(boolean createDialog, Main main){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewArticleCategoryAdd.fxml"));
			controller.setMain(main);
			loader.setController(controller);
			root = loader.load();
			
			if(createDialog){
				controller.setStage(stage);
				new CreateDialog("", stage, new Scene(root));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public AnchorPane getContent(){
		return root;
	}
	
	public ControllerArticleCategoryAdd getController(){
		return controller;
	}
	
}
