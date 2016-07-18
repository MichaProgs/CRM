package de.michaprogs.crm;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

public class AbortAlert {

	private boolean abort = false;
	
	public AbortAlert(){
		
		Alert a = new Alert(AlertType.WARNING);
		a.setHeaderText("Sind Sie sicher, dass Sie den Vorgang abbrechen möchten?");
		a.setContentText("Alle nicht gespeicherten Daten gehen verloren!");
		
		//Style
		DialogPane dp = a.getDialogPane();
		dp.getStylesheets().add("style.css");
		
		ButtonType btnYes = new ButtonType("Ja");
		ButtonType btnNo = new ButtonType("Nein");
		
		a.getButtonTypes().setAll(btnYes, btnNo);
		
		Optional<ButtonType> button = a.showAndWait();
		if(button.get() == btnYes){
			abort = true;
		}else{
			abort = false;
		}
		
	}
	
	public boolean getAbort(){
		return abort;
	}
	
}
