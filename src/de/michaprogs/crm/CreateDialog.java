package de.michaprogs.crm;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This Class creates a stage as a dialog
 */
public class CreateDialog{
	
	public CreateDialog(String title, Stage stage, Scene scene){
		
		scene.getStylesheets().add("style.css");
		
		new ESCClose(stage, scene);
		
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		
	}

}
