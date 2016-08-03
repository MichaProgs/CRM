package de.michaprogs.crm.menubar;


import de.michaprogs.crm.GraphicMenuItem;
import de.michaprogs.crm.article.data.LoadArticleData;
import de.michaprogs.crm.customer.data.LoadCustomerData;
import de.michaprogs.crm.properties.LoadProperties;
import de.michaprogs.crm.stock.add.LoadStockAdd;
import de.michaprogs.crm.supplier.data.LoadSupplierData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class ControllerMenuBar {

	@FXML private MenuItem itemCustomer;
	@FXML private MenuItem itemArticle;
	@FXML private MenuItem itemSupplier;
	@FXML private MenuItem itemStock;
	
	@FXML private MenuItem itemProperties;
	
	private LoadCustomerData customerData = new LoadCustomerData(false);
	private LoadArticleData articleData = new LoadArticleData();
	private LoadSupplierData supplierData = new LoadSupplierData(false);
	private LoadStockAdd stockAdd = new LoadStockAdd(false);
	
	private BorderPane content;
	
	public ControllerMenuBar(){}
	
	@FXML private void initialize(){
		
		//MenuItems
		initItemCustomer();
		initItemArticle();
		initItemSupplier();
		initItemStock();
		
		initItemProperties();
		
	}
	
	/*
	 * MENUITEMS
	 */
	private void initItemArticle(){
		
		itemArticle.setGraphic(new GraphicMenuItem("file:resources/article_32_white.png").getGraphicMenuItem());
		itemArticle.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				content.setCenter(articleData.getContent());
			}
		});
		
	}
	
	private void initItemSupplier(){
		
		itemSupplier.setGraphic(new GraphicMenuItem("file:resources/supplier_32_white.png").getGraphicMenuItem());
		itemSupplier.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				content.setCenter(supplierData.getContent());
			}
		});
		
	}
	
	private void initItemStock(){
		
		itemStock.setGraphic(new GraphicMenuItem("file:resources/warehouse_32_white.png").getGraphicMenuItem());
		itemStock.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				content.setCenter(stockAdd.getContent());
			}
		});
		
	}
	
	private void initItemCustomer(){
		
		itemCustomer.setGraphic(new GraphicMenuItem("file:resources/customer_32_white.png").getGraphicMenuItem());
		itemCustomer.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				content.setCenter(customerData.getContent());
			}
		});
		
	}
	
	private void initItemProperties(){
		
//		itemProperties.setGraphic(); TODO
		itemProperties.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				new LoadProperties(true);
			}
		});
		
	}
	
	/**
	 *  set by main-class 
	 *	@param content - the contentpane (borderpane) where the panels are changed 
	 */	
	public void setContent(BorderPane content){
		this.content = content;
	}
	
}
