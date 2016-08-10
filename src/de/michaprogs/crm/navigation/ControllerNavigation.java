package de.michaprogs.crm.navigation;

import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.article.data.LoadArticleData;
import de.michaprogs.crm.customer.data.LoadCustomerData;
import de.michaprogs.crm.offer.add.LoadOfferAdd;
import de.michaprogs.crm.stock.add.LoadStockAdd;
import de.michaprogs.crm.supplier.data.LoadSupplierData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ControllerNavigation {
	
	@FXML private AnchorPane anchorpaneSidebar;
	
	@FXML private Button btnCollapse;
	
	@FXML private Button btnCustomer;
	@FXML private Button btnArticle;
	@FXML private Button btnSupplier;
	@FXML private Button btnOffer;
	@FXML private Button btnStock;
	
	@FXML private TitledPane tpMainData;
	@FXML private TitledPane tpDocuments;
	
	private LoadCustomerData customerData = new LoadCustomerData(false);
	private LoadArticleData articleData = new LoadArticleData();
	private LoadSupplierData supplierData = new LoadSupplierData(false);
	private LoadStockAdd stockAdd = new LoadStockAdd(false);
	private LoadOfferAdd offerAdd = new LoadOfferAdd(false);
	
	private BorderPane content;
	
	public ControllerNavigation(){}
	
	@FXML private void initialize(){		
		
		//Buttons
		initBtnCollapse();
		
		initBtnCustomer();
		initBtnArticle();
		initBtnSupplier();
		initBtnStock();
		initBtnOffer();
		
	}
	
	/**
	 *  set by main-class 
	 *	@param content - the contentpane (borderpane) where the panels are changed 
	 */	
	public void setContent(BorderPane content){
		this.content = content;
	}
	
	private void initBtnCollapse(){
		
		btnCollapse.setGraphic(new GraphicButton("file:resources/menu_collapse_32.png").getGraphicButton());
		btnCollapse.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(anchorpaneSidebar.getPrefWidth() != 50){
					tpMainData.setText("DATEN");
					tpDocuments.setText("DOC'S");
					
					btnCustomer.setText("");
					btnArticle.setText("");
					btnSupplier.setText("");
					btnOffer.setText("");
					btnStock.setText("");
					anchorpaneSidebar.setPrefWidth(50);
				}else{
					/* SAME TEXT AS IN THE FXML FILE */
					tpMainData.setText("STAMMDATEN");
					btnCustomer.setText("KUNDEN");
					btnArticle.setText("ARTIKEL");
					btnSupplier.setText("LIEFERANTEN");
					btnStock.setText("LAGER");
					
					tpDocuments.setText("DOKUMENTE");
					btnOffer.setText("ANGEBOT");
					/* SAME WIDTH AS IN THE FXML FILE */
					anchorpaneSidebar.setPrefWidth(250);
				}
				
				
			}
		});
		
	}
	
	private void initBtnCustomer(){
		
		btnCustomer.setGraphic(new GraphicButton("file:resources/customer_32_blue.png").getGraphicButton());
		btnCustomer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				content.setCenter(customerData.getContent());
			}
		});
		
	}
	
	private void initBtnArticle(){
		
		btnArticle.setGraphic(new GraphicButton("file:resources/article_32_blue.png").getGraphicButton());
		btnArticle.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				content.setCenter(articleData.getContent());
			}
		});
		
	}
	
	private void initBtnSupplier(){
		
		btnSupplier.setGraphic(new GraphicButton("file:resources/supplier_32_blue.png").getGraphicButton());
		btnSupplier.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				content.setCenter(supplierData.getContent());
			}
		});
		
	}
	
	private void initBtnStock(){
		
		btnStock.setGraphic(new GraphicButton("file:resources/warehouse_32_blue.png").getGraphicButton());
		btnStock.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				content.setCenter(stockAdd.getContent());
			}
		});
		
	}
	
	private void initBtnOffer(){
		
//		btnOffer.setGraphic(new GraphicButton("file:resources/warehouse_32_blue.png").getGraphicButton());TODO
		btnOffer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				content.setCenter(offerAdd.getContent());
			}
		});
		
	}
	
}
