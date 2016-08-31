package de.michaprogs.crm.contact.edit;

import de.michaprogs.crm.CreateDialog;
import de.michaprogs.crm.Main;
import de.michaprogs.crm.contact.ModelContact;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadContactEdit {

	private AnchorPane root;
	private ControllerContactEdit controller = new ControllerContactEdit();
	private Stage stage = new Stage();
	
	public LoadContactEdit(boolean createDialog, Main main, ObservableList<ModelContact> obsListContact, int index){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewContactEdit.fxml"));
			loader.setController(controller);
			root = loader.load();
			controller.setContactData(obsListContact, index);
			
			if(createDialog){
				controller.setStage(stage);
				new CreateDialog("", stage, new Scene(root));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/*
	 * GETTER & SETTER
	 */
	public AnchorPane getContent(){
		return root;
	}
	
	public ControllerContactEdit getController(){
		return controller;
	}
	
}
