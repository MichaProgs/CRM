package de.michaprogs.crm;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Alert.AlertType;

public class ErrorAlert {

	public ErrorAlert(String kindOfError){
	
		Alert a = new Alert(AlertType.ERROR);
		a.setHeaderText("Es ist ein Fehler aufgetreten?");
		a.setContentText(kindOfError);
		
		//Style
		DialogPane dp = a.getDialogPane();
		dp.getStylesheets().add("style.css");
		
		a.showAndWait();
	
	}
	
}
