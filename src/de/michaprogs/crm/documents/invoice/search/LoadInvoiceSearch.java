package de.michaprogs.crm.documents.invoice.search;

import de.michaprogs.crm.CreateDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadInvoiceSearch {

	private AnchorPane root;
	private ControllerInvoiceSearch controller = new ControllerInvoiceSearch();
	private Stage stage = new Stage();
	
	public LoadInvoiceSearch(boolean createDialog){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewInvoiceSearch.fxml"));
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
	
	public AnchorPane getRoot() {
		return root;
	}

	public ControllerInvoiceSearch getController(){
		return controller;
	}
	
}
