package de.michaprogs.crm.article.search;

import de.michaprogs.crm.AbortAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.article.ModelArticle;
import de.michaprogs.crm.article.SearchArticle;
import de.michaprogs.crm.articlecategory.ModelArticleCategory;
import de.michaprogs.crm.articlecategory.SelectArticleCategory;
import de.michaprogs.crm.barrelsize.data.LoadBarrelsizeData;
import de.michaprogs.crm.bolting.data.LoadBoltingData;
import de.michaprogs.crm.components.TextFieldOnlyInteger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerArticleSearch {

	@FXML private Label lblSubHeadline;
	
	//Table & Columns
	@FXML private TableView<ModelArticle> tvArticleSearch;
	@FXML private TableColumn<ModelArticle, Integer> tcArticleID;
	@FXML private TableColumn<ModelArticle, String> tcDescription1;
	@FXML private TableColumn<ModelArticle, String> tcDescription2;
	@FXML private TableColumn<ModelArticle, String> tcBarrelsize;
	@FXML private TableColumn<ModelArticle, String> tcBolting;
	
	//Textfields
	@FXML private TextFieldOnlyInteger tfArticleID;
	@FXML private TextField tfDescription1;
	@FXML private TextField tfDescription2;
	@FXML private TextField tfBarrelsize;
	@FXML private TextField tfBolting;
	@FXML private ComboBox<String> cbCategory;
	
	//Buttons
	@FXML private Button btnSearch;
	@FXML private Button btnReset;
	@FXML private Button btnSelect;
	@FXML private Button btnAbort;
	
	@FXML private Button btnBarrelsize;
	@FXML private Button btnBolting;
	
	private Stage stage;
	private int selectedArticleID = 0;
	
	public ControllerArticleSearch(){}
	
	@FXML private void initialize(){
		
		cbCategory.setItems(new SelectArticleCategory(new ModelArticleCategory()).getModelArticleCategory().getObsListArticleCategoriesComboBox());
		
		initTable();
		initTextFields();
		tfArticleID.setText(""); //The custom component 'TextFieldOnlyInteger' sets 0 automatically
		
		//Buttons
		initBtnSearch();
		initBtnReset();
		initBtnAbort();
		initBtnSelect();
		
		initBtnBarrelsize();
		initBtnBolting();
		
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
	
	private void initBtnBarrelsize(){
		
		btnBarrelsize.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LoadBarrelsizeData barrelsize = new LoadBarrelsizeData(true);
				String selectedBarrelsize = barrelsize.getController().getSelectedBarrelsize();
				if(! selectedBarrelsize.isEmpty()){
					tfBarrelsize.setText(selectedBarrelsize);
				}
				
			}
		});
		
	}
	
	private void initBtnBolting(){
		
		btnBolting.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LoadBoltingData bolting = new LoadBoltingData(true);
				if(! bolting.getController().getSelectedBolting().equals("")){
					tfBolting.setText(bolting.getController().getSelectedBolting());
				}
				
			}
		});
		
	}
	
	/*
	 * TABLES
	 */
	private void initTable(){
		
		//Table & Columns
		this.tcArticleID.setCellValueFactory(new PropertyValueFactory<>("articleID"));
		this.tcDescription1.setCellValueFactory(new PropertyValueFactory<>("description1"));
		this.tcDescription2.setCellValueFactory(new PropertyValueFactory<>("description2"));
		this.tcBarrelsize.setCellValueFactory(new PropertyValueFactory<>("barrelsize"));
		this.tcBolting.setCellValueFactory(new PropertyValueFactory<>("bolting"));
		
		tvArticleSearch.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(	event.getClickCount() == 2 && 
					tvArticleSearch.getItems().size() > 0 &&
					tcArticleID.getCellData(tvArticleSearch.getSelectionModel().getSelectedIndex()) != null){
					
					selectedArticleID = tcArticleID.getCellData(tvArticleSearch.getSelectionModel().getSelectedIndex());
					if(stage != null)
						stage.close();
					
				}
			}
		});
		
		tvArticleSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {

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
		
		tfArticleID.setOnAction(ae);
		tfDescription1.setOnAction(ae);
		tfDescription2.setOnAction(ae);
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	private void search(){
		
		SearchArticle search = new SearchArticle(
			new ModelArticle(
				new Validate().new ValidateOnlyInteger().validateOnlyInteger(tfArticleID.getText()),
				tfDescription1.getText(), 
				tfDescription2.getText(),
				tfBarrelsize.getText(),
				tfBolting.getText(),
				cbCategory.getSelectionModel().getSelectedItem())
		);
		
		tvArticleSearch.setItems(search.getObsListSearch());
		if(tvArticleSearch.getItems().size() > 0){
			tvArticleSearch.getSelectionModel().selectFirst();
			tvArticleSearch.requestFocus();
		}
		
		if(tvArticleSearch.getItems().size() == 1){
			lblSubHeadline.setText("(" + String.valueOf(tvArticleSearch.getItems().size()) + " Suchergebnis)" );
		}else{
			lblSubHeadline.setText("(" + String.valueOf(tvArticleSearch.getItems().size()) + " Suchergebnisse)" );
		}
		
	}
	
	private void select(){
		
		if(tvArticleSearch.getSelectionModel().getSelectedItems().size() == 1 ){
			selectedArticleID = tvArticleSearch.getItems().get(tvArticleSearch.getSelectionModel().getSelectedIndex()).getArticleID();
			
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
		
		this.tfArticleID.clear();
		this.tfDescription1.clear();
		this.tfDescription2.clear();
		this.tfBarrelsize.clear();
		this.tfBolting.clear();
		this.cbCategory.getSelectionModel().clearSelection();
		
		this.tvArticleSearch.getItems().clear();
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
	public int getSelectedArticleID(){
		return selectedArticleID;
	}
	
}
