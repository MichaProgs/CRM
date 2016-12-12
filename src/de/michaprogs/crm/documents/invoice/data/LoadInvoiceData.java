package de.michaprogs.crm.documents.invoice.data;

import de.michaprogs.crm.CreateDialog;
import de.michaprogs.crm.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadInvoiceData {

	private AnchorPane content;
	private ControllerInvoiceData controller = new ControllerInvoiceData();
	private Stage stage = new Stage();
	
	public LoadInvoiceData(boolean createDialog, int invoiceID, int customerID, Main main){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewInvoiceData.fxml"));
			controller.setMain(main);
			loader.setController(controller);			
			content = loader.load();
			
			if(invoiceID != 0 && customerID != 0){
				controller.selectInvoice(invoiceID, customerID);
			}
			
			if(createDialog){
				controller.setStage(stage);
				new CreateDialog("", stage, new Scene(content));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public AnchorPane getContent(){
		return content;
	}
	
	public ControllerInvoiceData getController(){
		return controller;
	}
	
}
