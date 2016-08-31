package de.michaprogs.crm.contact.add;

import de.michaprogs.crm.CreateDialog;
import de.michaprogs.crm.Main;
import de.michaprogs.crm.contact.ModelContact;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadContactAdd {

	private AnchorPane root;
	private ControllerContactAdd controller;
	private Stage stage = new Stage();
	
	public LoadContactAdd(boolean createDialog, Main main, ObservableList<ModelContact> obsListContact){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewContactAdd.fxml"));
			controller = new ControllerContactAdd(obsListContact);
			controller.setMain(main);
			loader.setController(controller);
			root = loader.load();
			
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
	
	public ControllerContactAdd getController(){
		return controller;
	}
	
}
