package de.michaprogs.crm.article.barrelsize.add;

import de.michaprogs.crm.CreateDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadBarrelsizeAdd{
	
	private AnchorPane root;
	private ControllerBarrelsizeAdd controller;
	private Stage stage = new Stage();
	
	public LoadBarrelsizeAdd(boolean createDialog){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewBarrelsizeAdd.fxml"));
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

	public AnchorPane getContent() {
		return root;
	}

	public ControllerBarrelsizeAdd getController() {
		return controller;
	}
	
}