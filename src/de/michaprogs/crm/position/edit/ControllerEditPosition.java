package de.michaprogs.crm.position.edit;

import java.math.BigDecimal;
import java.math.RoundingMode;

import de.michaprogs.crm.AbortAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.InitCombos;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.amountunit.ModelAmountUnit;
import de.michaprogs.crm.amountunit.SelectAmountUnit;
import de.michaprogs.crm.article.ModelArticle;
import de.michaprogs.crm.article.SelectArticle;
import de.michaprogs.crm.article.search.LoadArticleSearch;
import de.michaprogs.crm.components.TextFieldDouble;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerEditPosition {

	/* ARTICLE */
	@FXML private TextField tfArticleID;
	@FXML private TextField tfDescription1;
	@FXML private TextField tfDescription2;
	@FXML private TextField tfBarrelsize;
	@FXML private TextField tfBolting;
	
	@FXML private TextFieldDouble tfAmount;
	@FXML private ComboBox<String> cbAmountUnit;
	@FXML private TextFieldDouble tfEk;
	@FXML private ComboBox<String> cbPriceUnitEk;
	@FXML private TextFieldDouble tfVk;
	@FXML private ComboBox<String> cbPriceUnitVk;
	@FXML private ComboBox<String> cbTax;
	
	/* BUTTONS */
	@FXML private Button btnArticleSearch;
	@FXML private Button btnSave;
	@FXML private Button btnAbort;
	
	private Stage stage;
	private ObservableList<ModelArticle> obsListArticle;
	private int index;
	
	public ControllerEditPosition(){}
	
	@FXML private void initialize(){
		
		/* COMBO BOX */
		cbAmountUnit.setItems(new SelectAmountUnit(new ModelAmountUnit()).getModelAmountUnit().getObsListAmountUnitsComboBox());
		new InitCombos().initComboPriceUnit(cbPriceUnitEk);
		new InitCombos().initComboPriceUnit(cbPriceUnitVk);
		new InitCombos().initComboTax(cbTax);
		
		/* BUTTONS */
		initBtnArticleSearch();
		initBtnSave();
		initBtnAbort();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnArticleSearch(){
		
		btnArticleSearch.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LoadArticleSearch articleSearch = new LoadArticleSearch(true);
				if(articleSearch.getController().getSelectedArticleID() != 0){
					
					ModelArticle article = new SelectArticle(new ModelArticle(articleSearch.getController().getSelectedArticleID())).getModelArticle();
					
					tfArticleID.setText(String.valueOf(article.getArticleID()));
					tfDescription1.setText(article.getDescription1());
					tfDescription2.setText(article.getDescription2());
					tfBarrelsize.setText(article.getBarrelsize());
					tfBolting.setText(article.getBolting());
					tfEk.setText(String.valueOf(article.getEk()));
					cbPriceUnitEk.getSelectionModel().select(String.valueOf(article.getPriceUnit()));					
					cbPriceUnitVk.getSelectionModel().select(String.valueOf(article.getPriceUnit()));
					cbAmountUnit.getSelectionModel().select(article.getAmountUnit());
					
					//ACTIVATE TEXTFIELDS
					tfAmount.setDisable(false);
					tfEk.setDisable(false);
					tfVk.setDisable(false);
					
					btnSave.setDisable(false);
					
				}
				
			}
		});
		
	}
	
	private void initBtnSave(){
		
		btnSave.setGraphic(new GraphicButton("save_32.png").getGraphicButton());
		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(! tfArticleID.getText().equals("")){
					
					/* CALULATE TOTAL PRICE */
					if(tfAmount.getText().isEmpty()){
						tfAmount.setText("0.00");
					}
					if(tfEk.getText().isEmpty()){
						tfEk.setText("0.00");
					}
					if(tfVk.getText().isEmpty()){
						tfVk.setText("0.00");
					}
					
					double amount = Double.parseDouble(tfAmount.getText());
					int priceUnit = Integer.parseInt(cbPriceUnitVk.getSelectionModel().getSelectedItem());
					BigDecimal vk = new BigDecimal(tfVk.getText());					
					BigDecimal total = new BigDecimal(String.valueOf(vk.multiply(new BigDecimal(amount)).divide(new BigDecimal(priceUnit))));
					total = total.setScale(2, RoundingMode.CEILING);
					/*----------------------*/
					
					obsListArticle.set(index, new ModelArticle(
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfArticleID.getText()),
						tfDescription1.getText(),
						tfDescription2.getText(),
						tfBarrelsize.getText(),
						tfBolting.getText(),
						new Validate().new ValidateDoubleTwoDigits().validateDouble(tfAmount.getText()),
						cbAmountUnit.getSelectionModel().getSelectedItem(),
						new Validate().new ValidateCurrency().validateCurrency(tfVk.getText()),
						new Validate().new ValidateCurrency().validateCurrency(tfEk.getText()),
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(cbPriceUnitVk.getSelectionModel().getSelectedItem()),
						total,
						new Validate().new ValidateOnlyInteger().validateOnlyInteger(cbTax.getSelectionModel().getSelectedItem())
					));
					
					if(stage != null){
						stage.close();
					}else{
						resetFields();			
						tfAmount.setDisable(true);
						tfEk.setDisable(true);
						tfVk.setDisable(true);
						btnSave.setDisable(true);
					}
					
				}
				
			}
		});
		
	}
	
	private void initBtnAbort(){
		
		btnAbort.setGraphic(new GraphicButton("cancel_32.png").getGraphicButton());
		btnAbort.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				AbortAlert abort = new AbortAlert();
				if(abort.getAbort()){
					if(stage != null){
						stage.close();
					}else{
						resetFields();
					}
				}
				
			}
		});
		
	}
	
	/*
	 * UI METHODS
	 */
	private void resetFields(){
		
		tfArticleID.clear();
		tfDescription1.clear();
		tfDescription2.clear();
		tfBarrelsize.clear();
		tfBolting.clear();
		tfAmount.clear();
		cbAmountUnit.getSelectionModel().selectFirst();
		tfEk.clear();
		cbPriceUnitEk.getSelectionModel().selectFirst();
		tfVk.clear();
		cbPriceUnitVk.getSelectionModel().selectFirst();
		cbTax.getSelectionModel().selectFirst();
		
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	public ObservableList<ModelArticle> getObsListArticle(){
		return obsListArticle;
	}
	
	public void setArticleData(	ObservableList<ModelArticle> obsListArticle, int index){
		
		this.obsListArticle = obsListArticle;
		this.index = index;
		
		tfArticleID.setText(String.valueOf(obsListArticle.get(index).getArticleID()));
		tfDescription1.setText(obsListArticle.get(index).getDescription1());
		tfDescription2.setText(obsListArticle.get(index).getDescription2());
		tfBarrelsize.setText(obsListArticle.get(index).getBarrelsize());
		tfBolting.setText(obsListArticle.get(index).getBolting());
		tfAmount.setText(String.valueOf(obsListArticle.get(index).getAmount()));
		cbAmountUnit.getSelectionModel().select(obsListArticle.get(index).getAmountUnit());
		tfEk.setText(String.valueOf(obsListArticle.get(index).getEk()));
		cbPriceUnitEk.getSelectionModel().select(String.valueOf(obsListArticle.get(index).getPriceUnit()));
		tfVk.setText(String.valueOf(obsListArticle.get(index).getVk()));
		cbPriceUnitVk.getSelectionModel().select(String.valueOf(obsListArticle.get(index).getPriceUnit()));
		cbTax.getSelectionModel().select(obsListArticle.get(index).getTax());
	
	}
	
}
