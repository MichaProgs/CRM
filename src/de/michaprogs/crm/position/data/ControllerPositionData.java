package de.michaprogs.crm.position.data;

import java.math.BigDecimal;
import java.math.RoundingMode;

import de.michaprogs.crm.DeleteAlert;
import de.michaprogs.crm.article.ModelArticle;
import de.michaprogs.crm.article.data.LoadArticleData;
import de.michaprogs.crm.position.add.LoadAddPosition;
import de.michaprogs.crm.position.edit.LoadEditPosition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;

public class ControllerPositionData {

	/* ARTICLE */
	      private ObservableList<ModelArticle> obsListPositions = FXCollections.observableArrayList();
	@FXML private TableView<ModelArticle> tvArticle;
	@FXML private TableColumn<ModelArticle, Integer> tcArticleID;
	@FXML private TableColumn<ModelArticle, String > tcDescription1;
	@FXML private TableColumn<ModelArticle, String> tcDescription2;
	@FXML private TableColumn<ModelArticle, String> tcBarrelsize;
	@FXML private TableColumn<ModelArticle, String> tcBolting;
	@FXML private TableColumn<ModelArticle, Double> tcAmount;
	@FXML private TableColumn<ModelArticle, String> tcAmountUnit;
	@FXML private TableColumn<ModelArticle, BigDecimal> tcVk;
	@FXML private TableColumn<ModelArticle, Integer> tcPriceUnit;
	@FXML private TableColumn<ModelArticle, BigDecimal> tcTotal;
	@FXML private TableColumn<ModelArticle, Integer> tcTax;	
	
	@FXML private Label lblTotal;
	@FXML private Label lblTax;
	@FXML private Label lblTotalWithTax;
	
	@FXML private Button btnArticleAdd;
	@FXML private Button btnArticleEdit; 
	@FXML private Button btnArticleDelete;
	
	public ControllerPositionData(){}
	
	@FXML private void initialize(){
		
		initBtnArticleAdd();
		initBtnArticleDelete();
		initBtnArticleEdit();
		
		initTableArticle();
		
		calculateTotal();
		setButtonState();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnArticleAdd(){
		
		btnArticleAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				addArticle();				
			}
		});
		
	}	
	
	private void initBtnArticleEdit(){
		
		btnArticleEdit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				editArticle();
			}
		});
		
	}
	
	private void initBtnArticleDelete(){
		
		btnArticleDelete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				deleteArticle();
			}
		});
		
	}
	
	/*
	 * TABLES
	 */
	private void initTableArticle(){
		
		tcArticleID.setCellValueFactory(new PropertyValueFactory<>("articleID"));
		tcDescription1.setCellValueFactory(new PropertyValueFactory<>("description1"));
		tcDescription2.setCellValueFactory(new PropertyValueFactory<>("description2"));
		tcBarrelsize.setCellValueFactory(new PropertyValueFactory<>("barrelsize"));
		tcBolting.setCellValueFactory(new PropertyValueFactory<>("bolting"));
		tcAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		tcAmount.getStyleClass().add("tc-align-right");
		tcAmountUnit.setCellValueFactory(new PropertyValueFactory<>("amountUnit"));
		tcVk.setCellValueFactory(new PropertyValueFactory<>("vk"));
		tcVk.getStyleClass().add("tc-align-right");
		tcPriceUnit.setCellValueFactory(new PropertyValueFactory<>("priceUnit"));
		tcTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
		tcTotal.getStyleClass().add("tc-align-right");
		tcTax.setCellValueFactory(new PropertyValueFactory<>("tax"));
		tcTax.getStyleClass().add("tc-align-right");
		
		tvArticle.setContextMenu(new ContextMenuTableArticle());
		
		tvArticle.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(event.getClickCount() == 2){
					editArticle();
				}
				
			}
		});
		
		tvArticle.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				
				if(event.getCode().equals(KeyCode.ENTER)){
					editArticle();
				}else if(event.getCode().equals(KeyCode.DELETE)){
					deleteArticle();
				}
				
			}
		});
				
		tvArticle.setItems(obsListPositions);
		
		obsListPositions.addListener(new ListChangeListener<ModelArticle>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends ModelArticle> c) {
				calculateTotal();
				setButtonState();				
			}
		});

	}
		

	/*
	 * DATABASE METHODS
	 */
	private void editArticle(){
		
		if(! btnArticleEdit.isDisabled()){
			if(tvArticle.getSelectionModel().getSelectedItems().size() > 0){
				
				LoadEditPosition editPos = new LoadEditPosition(true, tvArticle.getItems(), tvArticle.getSelectionModel().getSelectedIndex());
				tvArticle.setItems(editPos.getController().getObsListArticle());
				if(tvArticle.getItems().size() > 0){
					tvArticle.getSelectionModel().selectFirst();
				}
				
			}else{
				System.out.println("Bitte 1 Zeile markieren!");
			}
		}
		
	}
	
	private void addArticle(){
		
		if(! btnArticleAdd.isDisabled()){
			LoadAddPosition addPos = new LoadAddPosition(true, tvArticle.getItems());
			tvArticle.setItems(addPos.getController().getObsListArticle());
			if(tvArticle.getItems().size() > 0){
				tvArticle.getSelectionModel().selectFirst();
			}
		}
		
	}
	
	private void deleteArticle(){
		
		if(! btnArticleDelete.isDisabled()){
			if(tvArticle.getSelectionModel().getSelectedItems().size() == 1){
				DeleteAlert delete = new DeleteAlert();
				if(delete.getDelete()){
					tvArticle.getItems().remove(tvArticle.getSelectionModel().getSelectedIndex());
				}
			}else{
				System.out.println("Bitte 1 Zeile markieren!");
			}
		}
				
	}
	
	/*
	 * UI METHODS
	 */
	public void calculateTotal(){
	
		BigDecimal total = new BigDecimal("0.00");
		BigDecimal tax = new BigDecimal("0.00");
		BigDecimal totalWithTax = new BigDecimal("0.00");
		
		for(int i = 0; i < tvArticle.getItems().size(); i++){
			
			total = total.add(tcTotal.getCellData(i));
			tax = tax.add(tcTotal.getCellData(i).multiply(new BigDecimal(tcTax.getCellData(i)).divide(new BigDecimal(100))));
			totalWithTax = total.add(tax);
		}
		
		total = total.setScale(2, RoundingMode.CEILING);
		tax = tax.setScale(2, RoundingMode.CEILING);
		totalWithTax = totalWithTax.setScale(2, RoundingMode.CEILING);
		
		lblTotal.setText(String.valueOf(total));
		lblTax.setText(String.valueOf(tax));
		lblTotalWithTax.setText(String.valueOf(totalWithTax));
		
	}
	
	/*
	 * UI METHODS
	 */
	public void setButtonState(){
		
		if(tvArticle.getItems().size() > 0){
			btnArticleAdd.setDisable(false);
			btnArticleDelete.setDisable(false);
			btnArticleEdit.setDisable(false);
		}else{
			btnArticleAdd.setDisable(false); //Should not be disabled
			btnArticleDelete.setDisable(true);
			btnArticleEdit.setDisable(true);
		}
		
	}
	
	/*
	 * GETTER & SETTER
	 */
	public Button getBtnArticleAdd(){
		return btnArticleAdd;
	}
	
	public Button getBtnArticleEdit(){
		return btnArticleEdit;
	}
	
	public Button getBtnArticleDelete(){
		return btnArticleDelete;
	}
	
	public ObservableList<ModelArticle> getObsListPositions(){
		return obsListPositions;
	}
	
	public void setObsListPositions(ObservableList<ModelArticle> obsListPositions){
		this.obsListPositions.setAll(obsListPositions);
	}
	
	public Label getLblTotal(){
		return lblTotal;
	}
	
	/*
	 * CONTEXT MENU
	 */
	private class ContextMenuTableArticle extends ContextMenu{
		
		private MenuItem miAdd = new MenuItem("Hinzufügen..");
		private MenuItem miEdit = new MenuItem("Bearbeiten..");
		private MenuItem miDelete = new MenuItem("Löschen");
		private MenuItem miInfo = new MenuItem("Info..");
		
		public ContextMenuTableArticle(){
			
			//initialize
			initMiAdd();
			initMiEdit();
			initMiDelete();
			initMiInfo();
			
			this.getItems().addAll(miAdd, miEdit, miDelete, miInfo);		
			
			this.setOnShowing(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					
					if(! btnArticleDelete.isDisabled()){						
						miAdd.setDisable(false);
						miEdit.setDisable(false);
						miDelete.setDisable(false);
					}else{
						miAdd.setDisable(true);
						miEdit.setDisable(true);
						miDelete.setDisable(true);
					}
					
				}
			});
			
		}
		
		private void initMiAdd(){
			
			miAdd.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					addArticle();
				}
			});
			
		}
		
		private void initMiEdit(){
			
			miEdit.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					editArticle();
				}
			});
			
		}

		private void initMiDelete(){
	
			miDelete.setOnAction(new EventHandler<ActionEvent>() {
		
				@Override
				public void handle(ActionEvent event) {
					deleteArticle();
				}
			});
	
		}
		
		private void initMiInfo(){
			
			miInfo.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					new LoadArticleData(true, tcArticleID.getCellData(tvArticle.getSelectionModel().getSelectedIndex()), null);
				}
			});
			
		}
		
	}
	
}
