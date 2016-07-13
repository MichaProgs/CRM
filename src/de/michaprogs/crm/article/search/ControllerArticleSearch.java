package de.michaprogs.crm.article.search;

import de.michaprogs.crm.article.ModelArticle;
import de.michaprogs.crm.components.TextFieldOnlyInteger;
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
	@FXML private TableColumn<ModelArticle, Integer> tcArticleID;
	@FXML private TableColumn<ModelArticle, String> tcDescription1;
	@FXML private TableColumn<ModelArticle, String> tcDescription2;
	@FXML private TableColumn<ModelArticle, String> tcBarrelsize;
	@FXML private TableColumn<ModelArticle, String> tcBolting;
	
	//Textfields
	@FXML private TextFieldOnlyInteger tfArticleID;
	@FXML private TextField tfDescription1;
	@FXML private TextField tfDescription2;
	
	//Buttons
	@FXML private Button btnSearch;
	@FXML private Button btnAbort;
	@FXML private Button btnSelect;
	
	private Stage stage;
	private int selectedArticleID = 0;
	
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
		tfArticleID.setText(""); //The custom component 'TextFieldOnlyInteger' sets 0 automatically
		
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
				search();
			}
		};
		
		btnSearch.setOnAction(ae);
		
	}
	
	private void initBtnSearchAbort(){
		
		final EventHandler<ActionEvent> ae = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(stage != null)
					stage.close();					
			}
		};
		
		btnAbort.setOnAction(ae);
		
	}
	
	private void initBtnSearchSelect(){
		
		btnSelect.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				selectedArticleID = tcArticleID.getCellData(tvArticleSearch.getSelectionModel().getSelectedIndex());
				if(stage != null)
					stage.close();
				
			}
		});
		
	}
	
	private void initBtnTableDoubleClick(){
		
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
		
	}
	
	private void initTextfieldEnter(){
		
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
	
	private void search(){
		
		ModelArticle article = new ModelArticle();
		article.searchArticle(
			tfArticleID.getText(),
			tfDescription1.getText(), 
			tfDescription2.getText()
		);
		
		tvArticleSearch.setItems(article.getObsListSearch());
		tvArticleSearch.getSelectionModel().select(0);
		
		setButtonState();
		
	}
	
	private void setButtonState(){
		
		if(tvArticleSearch.getItems().size() > 0){
			btnSelect.setDisable(false);
		}else{
			btnSelect.setDisable(true);
		}
		
	}
	
	/**
	 * @return Returns the ArticleID which was selected in the table
	 */
	public int getSelectedArticleID(){
		return selectedArticleID;
	}
	
}
