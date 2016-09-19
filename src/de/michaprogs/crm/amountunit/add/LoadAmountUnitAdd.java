package de.michaprogs.crm.amountunit.add;

import de.michaprogs.crm.CreateDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadAmountUnitAdd {

	private ControllerAmountUnitAdd controller = new ControllerAmountUnitAdd();
	private AnchorPane root;
	private Stage stage = new Stage();
	
	public LoadAmountUnitAdd(boolean createDialog){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAmountUnitAdd.fxml"));
			loader.setController(controller);
			root = loader.load();
			
			if(createDialog){
				new CreateDialog("", stage, new Scene(root));
				controller.setStage(stage);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public ControllerAmountUnitAdd getController(){
		return controller;
	}
	
	public AnchorPane getContent(){
		return root;
	}
	
}
