package de.michaprogs.crm.supplier.add;

import de.michaprogs.crm.CreateDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadSupplierAdd {

	private AnchorPane root;
	private ControllerSupplierAdd controller;
	private Stage stage = new Stage();
	
	public LoadSupplierAdd(boolean createDialog){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewSupplierAdd.fxml"));
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

	public ControllerSupplierAdd getController() {
		return controller;
	}

	public Stage getStage() {
		return stage;
	}
	
}
