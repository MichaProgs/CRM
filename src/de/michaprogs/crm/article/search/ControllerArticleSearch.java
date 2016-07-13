package de.michaprogs.crm.article.search;

import de.michaprogs.crm.article.ModelArticle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerArticleSearch {

	//Table & Columns
	@FXML private TableView<ModelArticle> tvArticleSearch;
	@FXML private TableColumn<ModelArticle, String> tcArticleID;
	@FXML private TableColumn<ModelArticle, String> tcDescription1;
	@FXML private TableColumn<ModelArticle, String> tcDescription2;
	@FXML private TableColumn<ModelArticle, String> tcBarrelsize;
	@FXML private TableColumn<ModelArticle, String> tcBolting;
	
	//Textfields
	@FXML private TextField tfSearchArticleID;
	@FXML private TextField tfSearchDescription1;
	@FXML private TextField tfSearchDescription2;
	
	//Buttons
	@FXML private Button btnSearch;
	@FXML private Button btnSearchAbort;
	@FXML private Button btnSearchSelect;
	
	private Stage stage;
	private String selectedArticleID = "";
	
	public ControllerArticleSearch(){}
	
	@FXML private void initialize(){
		
		//Table & Columns
		this.tcArticleID.setCellValueFactory(new PropertyValueFactory<>("articleID"));
		this.tcDescription1.setCellValueFactory(new PropertyValueFactory<>("description1"));
		this.tcDescription2.setCellValueFactory(new PropertyValueFactory<>("description2"));
		this.tcBarrelsize.setCellValueFactory(new PropertyValueFactory<>("barrelsize"));
		this.tcBolting.setCellValueFactory(new PropertyValueFactory<>("bolting"));
		
		initBtnTableDoubleClick();
		initTextfieldEnter();
		
		//Buttons
		initBtnSearch();
		initBtnSearchAbort();
		initBtnSearchSelect();
		
		//set the buttonstate in the beginning
		setButtonState();
		
	}
	
	//is set in the controller
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	private void initBtnSearch(){
	
		final EventHandler<ActionEvent> ae = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
//				search();
			}
		};
		
		btnSearch.setOnAction(ae);
		
	}
	
	private void initBtnSearchAbort(){
		
		final EventHandler<ActionEvent> ae = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stage.close();				
			}
		};
		
		btnSearchAbort.setOnAction(ae);
		
	}
	
	private void initBtnSearchSelect(){
		
		final EventHandler<ActionEvent> ae = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				selectedArticleID = tcArticleID.getCellData(tvArticleSearch.getSelectionModel().getSelectedIndex());
				stage.close();
				
			}
		};
		
		btnSearchSelect.setOnAction(ae);
		
	}
	
	private void initBtnTableDoubleClick(){
		
		tvArticleSearch.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(	event.getClickCount() == 2 && 
					tvArticleSearch.getItems().size() > 0 &&
					tcArticleID.getCellData(tvArticleSearch.getSelectionModel().getSelectedIndex()) != null){
					
					selectedArticleID = tcArticleID.getCellData(tvArticleSearch.getSelectionModel().getSelectedIndex());
					stage.close();
					
				}
			}
		});
		
	}
	
	private void initTextfieldEnter(){
		
		EventHandler<ActionEvent> ae = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
//				search();
			}
		}; 
		
		tfSearchArticleID.setOnAction(ae);
		tfSearchDescription1.setOnAction(ae);
		tfSearchDescription2.setOnAction(ae);
		
	}
	
//	private void search(){
//		
//		tvArticleSearch.setItems(new ModelArticle().selectSearchedArticles(	tfSearchArticleID.getText(), 
//				tfSearchDescription1.getText(), 
//				tfSearchDescription2.getText()));
//
//		tvArticleSearch.getSelectionModel().select(0);
//		
//		setButtonState();
//		
//	}
	
	private void setButtonState(){
		
		if(tvArticleSearch.getItems().size() > 0){
			btnSearchSelect.setDisable(false);
		}else{
			btnSearchSelect.setDisable(true);
		}
		
	}
	
	/**
	 * @return Returns the ArticleID which was selected in the table
	 */
	public String getSelectedArticleID(){
		return selectedArticleID;
	}
	
}
