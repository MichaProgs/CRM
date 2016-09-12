package de.michaprogs.crm.supplier.data;

import de.michaprogs.crm.CreateDialog;
import de.michaprogs.crm.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadSupplierData {

	private AnchorPane root;
	private ControllerSupplierData controller = new ControllerSupplierData();
	private Stage stage = new Stage();
	
	public LoadSupplierData(boolean createDialog, int supplierID, Main main){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewSupplierData.fxml"));
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

	public AnchorPane getContent() {
		return root;
	}

	public ControllerSupplierData getController() {
		return controller;
	}

	public Stage getStage() {
		return stage;
	}
	
}
