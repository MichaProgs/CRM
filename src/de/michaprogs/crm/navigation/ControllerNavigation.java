package de.michaprogs.crm.navigation;

import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.Main;
import de.michaprogs.crm.article.data.LoadArticleData;
import de.michaprogs.crm.customer.data.LoadCustomerData;
import de.michaprogs.crm.documents.deliverybill.data.LoadDeliverybillData;
import de.michaprogs.crm.documents.invoice.data.LoadInvoiceData;
import de.michaprogs.crm.documents.offer.data.LoadOfferData;
import de.michaprogs.crm.documents.order.data.LoadOrderData;
import de.michaprogs.crm.stock.add.LoadStockAdd;
import de.michaprogs.crm.supplier.data.LoadSupplierData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ControllerNavigation {
	
	@FXML private AnchorPane apSide;
	@FXML private ScrollPane spSide;
	@FXML private BorderPane bpSide;
	
	@FXML private Button btnCollapse;
	
	@FXML private Button btnCustomer;
	@FXML private Button btnArticle;
	@FXML private Button btnSupplier;
	@FXML private Button btnOffer;
	@FXML private Button btnOrder;
	@FXML private Button btnDeliverybill;
	@FXML private Button btnInvoice;
	@FXML private Button btnStock;
	
	@FXML private TitledPane tpMainData;
	@FXML private TitledPane tpDocuments;
	
	private LoadCustomerData customerData;
	private LoadArticleData articleData;
	private LoadSupplierData supplierData;
	private LoadStockAdd stockAdd = new LoadStockAdd(false);
	private LoadOfferData offerData;
	private LoadOrderData orderData;
	private LoadDeliverybillData deliverybillData;
	private LoadInvoiceData invoiceData;
	
	private Main main;
	
	public ControllerNavigation(){}
	
	@FXML private void initialize(){	
		
		customerData = new LoadCustomerData(false, main);
		articleData = new LoadArticleData(false, 0, main);
		supplierData = new LoadSupplierData(false, 0, main);
		offerData = new LoadOfferData(false,0,0, main);
		orderData = new LoadOrderData(false, 0, 0, main);
		deliverybillData = new LoadDeliverybillData(false, 0, 0, main);
		invoiceData = new LoadInvoiceData(false, 0, 0, main);		
		
		//Buttons
		initBtnCollapse();
		
		initBtnCustomer();
		initBtnArticle();
		initBtnSupplier();
		initBtnStock();
		initBtnOffer();
		initBtnOrder();
		initBtnDeliverybill();
		initBtnInvoice();
		
	}
	
	/**
	 *  set by main-class via loading class  
	 */		
	public void setMain(Main main){
		this.main = main;
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnCollapse(){
		
		btnCollapse.setGraphic(new GraphicButton("menu_collapse_32.png").getGraphicButton());
		btnCollapse.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(spSide.getPrefWidth() != 90){
					tpMainData.setText("DATEN");
					tpDocuments.setText("DOC'S");
					
					btnCustomer.setText("");
					btnArticle.setText("");
					btnSupplier.setText("");
					btnOffer.setText("");
					btnOrder.setText("");
					btnStock.setText("");
					
					spSide.setPrefWidth(90);
					bpSide.setPrefWidth(90);
					apSide.setPrefWidth(90);
				}else{
					/* SAME TEXT AS IN THE FXML FILE */
					tpMainData.setText("STAMMDATEN");
					btnCustomer.setText("KUNDEN");
					btnArticle.setText("ARTIKEL");
					btnSupplier.setText("LIEFERANTEN");
					btnStock.setText("LAGER");
					
					tpDocuments.setText("DOKUMENTE");
					btnOffer.setText("ANGEBOT");
					btnOrder.setText("BESTELLUNG");
					/* SAME WIDTH AS IN THE FXML FILE */
					spSide.setPrefWidth(250);
					bpSide.setPrefWidth(250);
					apSide.setPrefWidth(250);
				}
				
				
			}
		});
		
	}
	
	private void initBtnCustomer(){
		
		btnCustomer.setGraphic(new GraphicButton("customer_32_blue.png").getGraphicButton());
		btnCustomer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {					
				main.getContentPane().setCenter(customerData.getContent());
				main.getStage().setTitle(main.getProgramName() + " - Kundenstamm");
				setSizeToScene();
			}
		});
		
	}
	
	private void initBtnArticle(){
		
		btnArticle.setGraphic(new GraphicButton("article_32_blue.png").getGraphicButton());
		btnArticle.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				main.getContentPane().setCenter(articleData.getContent());
				main.getStage().setTitle(main.getProgramName() + " - Artikelstamm");
				setSizeToScene();
			}
		});
		
	}
	
	private void initBtnSupplier(){
		
		btnSupplier.setGraphic(new GraphicButton("supplier_32_blue.png").getGraphicButton());
		btnSupplier.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				main.getContentPane().setCenter(supplierData.getContent());
				main.getStage().setTitle(main.getProgramName() + " - Lieferanten");
				setSizeToScene();
			}
		});
		
	}
	
	private void initBtnStock(){
		
		btnStock.setGraphic(new GraphicButton("warehouse_32_blue.png").getGraphicButton());
		btnStock.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				main.getContentPane().setCenter(stockAdd.getContent());
				main.getStage().setTitle(main.getProgramName() + " - Lager");
				setSizeToScene();
			}
		});
		
	}
	
	private void initBtnOffer(){
		
		btnOffer.setGraphic(new GraphicButton("offer_32_blue.png").getGraphicButton());
		btnOffer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				main.getContentPane().setCenter(offerData.getContent());
				main.getStage().setTitle(main.getProgramName() + " - Angebot");
				setSizeToScene();
			}
		});
		
	}
	
	private void initBtnDeliverybill(){
		
		btnDeliverybill.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				main.getContentPane().setCenter(deliverybillData.getContent());
				main.getStage().setTitle(main.getProgramName() + " - Lieferschein");
				setSizeToScene();
			}
		});
		
	}
	
	private void initBtnInvoice(){
		
		btnInvoice.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				main.getContentPane().setCenter(invoiceData.getContent());
				main.getStage().setTitle(main.getProgramName() + " - Rechnung");
				setSizeToScene();
			}
		});
		
	}
	
	private void initBtnOrder(){
		
		//TODO setGraphic
		btnOrder.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				main.getContentPane().setCenter(orderData.getContent());
				main.getStage().setTitle(main.getProgramName() + " - Bestellung");
				setSizeToScene();
			}
		});
		
	}
	
	private void setSizeToScene(){
		if(! main.getStage().isMaximized())
			main.getStage().sizeToScene();
	}
	
}
