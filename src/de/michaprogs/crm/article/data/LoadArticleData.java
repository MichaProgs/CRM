package de.michaprogs.crm.article.data;

import de.michaprogs.crm.CreateDialog;
import de.michaprogs.crm.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadArticleData {

	private AnchorPane root;
	private ControllerArticleData controller = new ControllerArticleData();
	private Stage stage = new Stage();
	
	public LoadArticleData(boolean createDialog, int articleID, Main main){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewArticleData.fxml"));
			controller.setMain(main);
			loader.setController(controller);
			root = loader.load();
			
			if(articleID != 0){
				controller.selectArticle(articleID);
			}
			
			if(createDialog){
				new CreateDialog("", stage, new Scene(root));
			}
			
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
