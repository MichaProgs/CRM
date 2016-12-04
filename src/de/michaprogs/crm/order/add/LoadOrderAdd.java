package de.michaprogs.crm.order.add;

import de.michaprogs.crm.CreateDialog;
import de.michaprogs.crm.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadOrderAdd {

	private AnchorPane root;
	private ControllerOrderAdd controller = new ControllerOrderAdd();
	private Stage stage = new Stage();
	
	public LoadOrderAdd(boolean createDialog, int supplierID, Main main){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewOrderAdd.fxml"));
			controller.setMain(main);
			loader.setController(controller);
			root = loader.load();
			
			if(supplierID != 0){
				controller.selectSupplier(supplierID);
			}
			
			if(createDialog){
				controller.setStage(stage);
				new CreateDialog("", stage, new Scene(root));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public AnchorPane getContent(){
		return root;
	}
	
	public ControllerOrderAdd getController(){
		return controller;
	}
	
}
