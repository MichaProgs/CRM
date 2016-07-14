package de.michaprogs.crm.supplier.data;

import de.michaprogs.crm.CreateDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadSupplierData {

	private AnchorPane root;
	private ControllerSupplierData controller;
	private Stage stage = new Stage();
	
	public LoadSupplierData(boolean createDialog){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewSupplierData.fxml"));
			root = loader.load();
			controller = loader.getController();
			
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
