package de.michaprogs.crm.documents.offer.search;

import de.michaprogs.crm.AbortAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.components.TextFieldOnlyInteger;
import de.michaprogs.crm.documents.offer.ModelOffer;
import de.michaprogs.crm.documents.offer.SearchOffer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerOfferSearch {

	@FXML private Label lblSubHeadline;
	
	//Table & Columns
	@FXML private TableView<ModelOffer> tvOfferSearch;
	@FXML private TableColumn<ModelOffer, Integer> tcOfferID;
	@FXML private TableColumn<ModelOffer, String> tcOfferDate;
	@FXML private TableColumn<ModelOffer, Integer> tcCustomerID;
	@FXML private TableColumn<ModelOffer, String> tcRequest;
	@FXML private TableColumn<ModelOffer, String> tcRequestDate;
		
	//Textfields
	@FXML private TextFieldOnlyInteger tfOfferID;
	@FXML private DatePicker tfOfferDate;
	@FXML private TextFieldOnlyInteger tfCustomerID;
	@FXML private TextField tfRequest;
	@FXML private DatePicker tfRequestDate;
	
	//Buttons
	@FXML private Button btnSearch;
	@FXML private Button btnReset;
	@FXML private Button btnSelect;
	@FXML private Button btnAbort;
	
	@FXML private Button btnBarrelsize;
	@FXML private Button btnBolting;
	
	private Stage stage;
	private int selectedOfferID = 0;
	private int selectedOfferCustomerID = 0;
	
	public ControllerOfferSearch(){}
	
	@FXML private void initialize(){
		
		initTable();
		initTextFields();
		
		//Buttons
		initBtnSearch();
		initBtnReset();
		initBtnAbort();
		initBtnSelect();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnSearch(){
	
		btnSearch.setGraphic(new GraphicButton("search_32.png").getGraphicButton());
		btnSearch.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				search();
			}
		});
		
	}
	
	private void initBtnReset(){
		
		btnReset.setGraphic(new GraphicButton("clear_32.png").getGraphicButton());
		btnReset.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				resetAllFields();
			}
		});
		
	}
	
	private void initBtnSelect(){
		
		btnSelect.setGraphic(new GraphicButton("select_32.png").getGraphicButton());
		btnSelect.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				select();
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
						resetAllFields();
					}
				}
				
			}
		});
		
	}
	
	/*
	 * TABLES
	 */
	private void initTable(){
		
		//Table & Columns
		this.tcOfferID.setCellValueFactory(new PropertyValueFactory<>("offerID"));
		this.tcOfferDate.setCellValueFactory(new PropertyValueFactory<>("offerDate"));
		this.tcCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
		this.tcRequest.setCellValueFactory(new PropertyValueFactory<>("request"));
		this.tcRequestDate.setCellValueFactory(new PropertyValueFactory<>("requestDate"));
		
		tvOfferSearch.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(	event.getClickCount() == 2 && 
					tvOfferSearch.getItems().size() > 0 &&
					tcOfferID.getCellData(tvOfferSearch.getSelectionModel().getSelectedIndex()) != null){
					
					select();
					
				}
			}
		});
		
		tvOfferSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)){
					select();					
				}
			}
		});
		
	}
	
	/*
	 * TEXTFIELDS
	 */
	private void initTextFields(){
		
		EventHandler<ActionEvent> ae = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				search();
			}
		}; 
		
		tfOfferID.setOnAction(ae);
//		tfOfferDate.setOnAction(ae);
		tfCustomerID.setOnAction(ae);
		tfRequest.setOnAction(ae);
//		tfRequestDate.setOnAction(ae);
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	private void search(){
		
		SearchOffer search = new SearchOffer(
			tfOfferID.getText(),
			String.valueOf(tfOfferDate.getValue()), 
			tfCustomerID.getText(),
			tfRequest.getText(),
			String.valueOf(tfRequestDate.getValue())
		);
		
		tvOfferSearch.setItems(search.getObsListSearch());
		if(tvOfferSearch.getItems().size() > 0){
			tvOfferSearch.getSelectionModel().selectFirst();
			tvOfferSearch.requestFocus();
		}
		
		if(tvOfferSearch.getItems().size() == 1){
			lblSubHeadline.setText("(" + String.valueOf(tvOfferSearch.getItems().size()) + " Suchergebnis)" );
		}else{
			lblSubHeadline.setText("(" + String.valueOf(tvOfferSearch.getItems().size()) + " Suchergebnisse)" );
		}
		
	}
	
	private void select(){
		
		if(tvOfferSearch.getSelectionModel().getSelectedItems().size() == 1 ){
			
			selectedOfferID = tvOfferSearch.getItems().get(tvOfferSearch.getSelectionModel().getSelectedIndex()).getOfferID();
			selectedOfferCustomerID = tvOfferSearch.getItems().get(tvOfferSearch.getSelectionModel().getSelectedIndex()).getCustomerID();
			
			if(stage != null){
				stage.close();
			}else{
				resetAllFields();
			}
			
		}else{
			System.out.println("Bitte 1 Zeile auswählen!");
		}
		
	}
	
	/*
	 * UI METHODS
	 */
	private void resetAllFields(){
		
		this.tfOfferID.clear();
		this.tfOfferDate.setValue(null);
		this.tfCustomerID.clear();
		this.tfRequest.clear();
		this.tfRequestDate.setValue(null);
		
		this.tvOfferSearch.getItems().clear();
		this.lblSubHeadline.setText("");
				
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	/**
	 * @return Returns the ArticleID which was selected in the table
	 */
	public int getSelectedOfferID(){
		return selectedOfferID;
	}
	
	public int getSelectedOfferCustomerID(){
		return selectedOfferCustomerID;
	}
	
}
