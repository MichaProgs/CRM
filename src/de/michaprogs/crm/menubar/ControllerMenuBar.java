package de.michaprogs.crm.menubar;


import de.michaprogs.crm.GraphicMenuItem;
import de.michaprogs.crm.Main;
import de.michaprogs.crm.article.data.LoadArticleData;
import de.michaprogs.crm.customer.data.LoadCustomerData;
import de.michaprogs.crm.documents.offer.data.LoadOfferData;
import de.michaprogs.crm.properties.LoadProperties;
import de.michaprogs.crm.stock.add.LoadStockAdd;
import de.michaprogs.crm.supplier.data.LoadSupplierData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class ControllerMenuBar {

	@FXML private MenuItem itemCustomer;
	@FXML private MenuItem itemArticle;
	@FXML private MenuItem itemSupplier;
	@FXML private MenuItem itemStock;
	
	@FXML private MenuItem itemOffer;
	
	@FXML private MenuItem itemProperties;
	
	private LoadCustomerData customerData;
	private LoadArticleData articleData;
	private LoadSupplierData supplierData;
	private LoadOfferData offerData;
	private LoadStockAdd stockAdd = new LoadStockAdd(false);
	
	private Main main;
	
	public ControllerMenuBar(){}
	
	@FXML private void initialize(){
		
		//Content
		customerData = new LoadCustomerData(false, main);
		articleData  = new LoadArticleData(false, 0, main);
		supplierData =  new LoadSupplierData(false, 0, main);
		offerData = new LoadOfferData(false, 0, 0, main);
		
		//MenuItems
		initItemCustomer();
		initItemArticle();
		initItemSupplier();
		initItemStock();
		
		initItemOffer();
		
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
				main.getContentPane().setCenter(articleData.getContent());
			}
		});
		
	}
	
	private void initItemSupplier(){
		
		itemSupplier.setGraphic(new GraphicMenuItem("file:resources/supplier_32_white.png").getGraphicMenuItem());
		itemSupplier.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				main.getContentPane().setCenter(supplierData.getContent());
			}
		});
		
	}
	
	private void initItemStock(){
		
		itemStock.setGraphic(new GraphicMenuItem("file:resources/warehouse_32_white.png").getGraphicMenuItem());
		itemStock.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				main.getContentPane().setCenter(stockAdd.getContent());
			}
		});
		
	}
	
	private void initItemCustomer(){
		
		itemCustomer.setGraphic(new GraphicMenuItem("file:resources/customer_32_white.png").getGraphicMenuItem());
		itemCustomer.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				main.getContentPane().setCenter(customerData.getContent());
			}
		});
		
	}
	
	private void initItemOffer(){
		
		itemOffer.setGraphic(new GraphicMenuItem("file:resources/offer_32_white.png").getGraphicMenuItem());
		itemOffer.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				main.getContentPane().setCenter(offerData.getContent());
			}
		});

	}
	
	private void initItemProperties(){
		
//		itemProperties.setGraphic(); TODO
		itemProperties.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				new LoadProperties(true).getController().setMain(main);
			}
		});
		
	}
	
	/**
	 *  set by main-class via loader class
	 */	
	public void setMain(Main main){
		this.main = main;
	}
	
}
