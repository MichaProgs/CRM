package de.michaprogs.crm.customer.category.add;

import de.michaprogs.crm.CreateDialog;
import de.michaprogs.crm.Main;
import de.michaprogs.crm.articlecategory.add.ControllerArticleCategoryAdd;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadCustomerCategoryAdd {

	private AnchorPane root;
	private ControllerCustomerCategoryAdd controller = new ControllerCustomerCategoryAdd();
	private Stage stage = new Stage();
	
	public LoadCustomerCategoryAdd(boolean createDialog, Main main){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewCustomerCategoryAdd.fxml"));
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
	
	public AnchorPane getContent(){
		return root;
	}
	
	public ControllerCustomerCategoryAdd getController(){
		return controller;
	}
	
}
