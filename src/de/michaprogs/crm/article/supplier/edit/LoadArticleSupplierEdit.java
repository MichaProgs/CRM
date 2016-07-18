package de.michaprogs.crm.article.supplier.edit;

import java.math.BigDecimal;

import de.michaprogs.crm.CreateDialog;
import de.michaprogs.crm.article.supplier.ModelArticleSupplier;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadArticleSupplierEdit {

	private AnchorPane root;
	private ControllerArticleSupplierEdit controller;
	private Stage stage = new Stage();
	
	public LoadArticleSupplierEdit(	boolean createDialog,
									int supplierID,
									String articleID,
									String description1, 
									String description2, 
									String barrelsize, 
									String bolting,
									BigDecimal ek,
									int priceUnit,
									String amountUnit,
									ObservableList<ModelArticleSupplier> obsListArticleSupplier,
									int indexOfEdit){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewArticleSupplierEdit.fxml"));
			root = loader.load();
			controller = loader.getController();
			controller.setArticleData(	
				supplierID, 
				articleID, 
				description1, 
				description2, 
				barrelsize, 
				bolting, 
				ek, 
				priceUnit, 
				amountUnit, 
				obsListArticleSupplier, 
				indexOfEdit
			);
			
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

	public ControllerArticleSupplierEdit getController() {
		return controller;
	}

	public Stage getStage() {
		return stage;
	}
	
}
