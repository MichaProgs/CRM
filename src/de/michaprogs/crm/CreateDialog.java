package de.michaprogs.crm;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This Class creates a stage as a dialog
 */
public class CreateDialog extends Stage{
	
	public CreateDialog(String title, Scene scene){
		
		scene.getStylesheets().add("style.css");
		
		new ESCClose(this, scene);
		
		this.setScene(scene);
		this.initModality(Modality.APPLICATION_MODAL);
		this.showAndWait();
		
	}

}
