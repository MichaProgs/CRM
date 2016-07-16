package de.michaprogs.crm.article.supplier.add;

import de.michaprogs.crm.CreateDialog;
import de.michaprogs.crm.article.supplier.ModelArticleSupplier;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadArticleSupplierAdd {

	private AnchorPane root;
	private ControllerArticleSupplierAdd controller;
	private Stage stage = new Stage();
	
	public LoadArticleSupplierAdd(	boolean createDialog, 
									String description1, 
									String description2, 
									String barrelsize, 
									String bolting,
									int priceUnit,
									String amountUnit,
									ObservableList<ModelArticleSupplier> obsListArticleSupplier){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewArticleSupplierAdd.fxml"));
			root = loader.load();
			controller = loader.getController();
			controller.setArticleData(description1, description2, barrelsize, bolting, priceUnit, amountUnit, obsListArticleSupplier);
			
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

	public ControllerArticleSupplierAdd getController() {
		return controller;
	}

	public Stage getStage() {
		return stage;
	}
	
}
