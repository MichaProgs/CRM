package de.michaprogs.crm;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

public class DeleteAlert {

	private boolean delete = false;
	
	public DeleteAlert(){
		
		Alert a = new Alert(AlertType.WARNING);
		a.setHeaderText("Sicher löschen?");
		
		//Style
		DialogPane dp = a.getDialogPane();
		dp.getStylesheets().add("style.css");
		
		ButtonType btnYes = new ButtonType("Ja");
		ButtonType btnNo = new ButtonType("Nein");
		
		a.getButtonTypes().setAll(btnYes, btnNo);
		
		Optional<ButtonType> button = a.showAndWait();
		if(button.get() == btnYes){
			delete = true;
		}else{
			delete = false;
		}
		
	}
	
	public boolean getDelete(){
		return delete;
	}
	
}
