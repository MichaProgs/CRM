package de.michaprogs.crm.documents.invoice.add;

import de.michaprogs.crm.CreateDialog;
import de.michaprogs.crm.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadInvoiceAdd {

	private AnchorPane content;
	private ControllerInvoiceAdd controller = new ControllerInvoiceAdd();
	private Stage stage = new Stage();
	
	public LoadInvoiceAdd(boolean createDialog, Main main, int customerID){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewInvoiceAdd.fxml"));
			controller.setMain(main);
			loader.setController(controller);
			content = loader.load();
			
			if(customerID != 0){
				controller.selectCustomer(customerID);
			}
			
			if(createDialog){
				controller.setStage(stage);
				new CreateDialog("", stage, new Scene(content));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/*
	 * GETTER & SETTER
	 */
	public AnchorPane getContent() {
		return content;
	}

	public ControllerInvoiceAdd getController() {
		return controller;
	}
	
	
	
}
