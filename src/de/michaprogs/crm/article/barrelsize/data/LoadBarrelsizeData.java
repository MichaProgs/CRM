package de.michaprogs.crm.article.barrelsize.data;

import de.michaprogs.crm.CreateDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadBarrelsizeData{
	
	private AnchorPane root;
	private ControllerBarrelsizeData controller;
	private Stage stage = new Stage();
	
	public LoadBarrelsizeData(boolean createDialog){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewBarrelsizeData.fxml"));
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

	public ControllerBarrelsizeData getController() {
		return controller;
	}
	
}