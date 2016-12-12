package de.michaprogs.crm;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Alert.AlertType;

public class SaveErrorAlert {
	
	/**
	 * @param value - what happened and how to solve the error
	 */
	public SaveErrorAlert(String message){
		
		Alert a = new Alert(AlertType.ERROR);
		a.setHeaderText("Speichern nicht möglich");
		a.setContentText(message);
		
		//Style
		DialogPane dp = a.getDialogPane();
		dp.getStylesheets().add("style.css");
		
		a.showAndWait();
		
	}
	
}
