package de.michaprogs.crm.navigation;

import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.article.data.LoadArticleData;
import de.michaprogs.crm.stock.add.LoadStockAdd;
import de.michaprogs.crm.supplier.data.LoadSupplierData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ControllerNavigation {
	
	@FXML private AnchorPane anchorpaneSidebar;
	
	@FXML private Button btnCollapse;
	
	@FXML private Button btnArticle;
	@FXML private Button btnSupplier;
	@FXML private Button btnOffer;
	@FXML private Button btnStock;
	
	private LoadArticleData articleData = new LoadArticleData();
	private LoadSupplierData supplierData = new LoadSupplierData(false);
	private LoadStockAdd stockAdd = new LoadStockAdd(false);
	private BorderPane content;
	
	public ControllerNavigation(){}
	
	@FXML private void initialize(){		
		
		//Buttons
		initBtnCollapse();
		
		initBtnArticle();
		initBtnSupplier();
		initBtnStock();
		
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
					btnArticle.setText("");
					btnSupplier.setText("");
					btnOffer.setText("");
					btnStock.setText("");
					anchorpaneSidebar.setPrefWidth(50);
				}else{
					/* SAME TEXT AS IN THE FXML FILE */
					btnArticle.setText("Artikel");
					btnSupplier.setText("Lieferanten");
					btnOffer.setText("Angebot");
					btnStock.setText("Lager");
					/* SAME WIDTH AS IN THE FXML FILE */
					anchorpaneSidebar.setPrefWidth(200);
				}
				
				
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
	
}
