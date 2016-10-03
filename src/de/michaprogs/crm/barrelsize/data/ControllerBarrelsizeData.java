package de.michaprogs.crm.barrelsize.data;

import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.barrelsize.ModelBarrelsize;
import de.michaprogs.crm.barrelsize.SelectBarrelsize;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerBarrelsizeData {

	@FXML private TextField tfFilter;
	
	//Tables & Columns
	private ObservableList<ModelBarrelsize> obsListBarrelsize = FXCollections.observableArrayList();
	@FXML private TableView<ModelBarrelsize> tvBarrelsize;
	@FXML private TableColumn<ModelBarrelsize, Integer> tcBarrelsizeID;
	@FXML private TableColumn<ModelBarrelsize, String> tcBarrelsize;
		
	@FXML private Label lblSubHeadline;
	
	//Buttons
	@FXML private Button btnSelect;
	@FXML private Button btnAbort;
	
	private String selectedBarrelsize = "";
	private Stage stage;
	
	public ControllerBarrelsizeData(){}
	
	@FXML private void initialize(){	
		
		/* BUTTONS */
		initBtnAbort();
		initBtnSelect();
		
		/* TABLES */
		initTableBarrelsize();
		
		/* TEXTFIELDS */
		initTfFilter();
		
		//Load all barrelsize from Database and show
		refreshTablebarrelsize();
		
	}
	
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnSelect(){
		
		btnSelect.setGraphic(new GraphicButton("select_32.png").getGraphicButton());
		btnSelect.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				selectBarrelsize();
			}
		});
		
	}
	
	private void initBtnAbort(){
		
		btnAbort.setGraphic(new GraphicButton("cancel_32.png").getGraphicButton());
		btnAbort.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(stage != null){
					stage.close();
				}
			}
		});	
	}
	
	/*
	 * TABLES
	 */
	private void initTableBarrelsize(){
		
		tcBarrelsizeID.setCellValueFactory(new PropertyValueFactory<>("barrelsizeID"));
		tcBarrelsize.setCellValueFactory(new PropertyValueFactory<>("barrelsize"));	
		
		tvBarrelsize.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				
				if(event.getCode().equals(KeyCode.ENTER)){
					selectBarrelsize();
				}
				
			}
		});
		
		tvBarrelsize.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if(event.getClickCount() == 2){
					selectBarrelsize();
				}
				
			}
		});
		
		tvBarrelsize.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ModelBarrelsize>() {

			@Override
			public void changed(ObservableValue<? extends ModelBarrelsize> observable, ModelBarrelsize oldValue,
					ModelBarrelsize newValue) {
				lblSubHeadline.setText(tcBarrelsize.getCellData(tvBarrelsize.getSelectionModel().getSelectedIndex()));				
			}
			
		});       
		
	}
	
	/*
	 * TEXTFIELDS
	 */
	private void initTfFilter(){
		
		tfFilter.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				
				FilteredList<ModelBarrelsize> filteredList = new FilteredList<>(obsListBarrelsize);
				filteredList.setPredicate(barrelsize ->{
					
					if(tfFilter.getText().isEmpty() || tfFilter.getText() == null){
						return true;
					}else if(barrelsize.getBarrelsize().toLowerCase().contains(tfFilter.getText().toLowerCase())){
						return true;
					}
					
					return false;
					
				});
				
				tvBarrelsize.setItems(filteredList);
				
			}
		});
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	private void selectBarrelsize(){
		
		if(tvBarrelsize.getSelectionModel().getSelectedItems().size() == 1){
			
			selectedBarrelsize = tvBarrelsize.getItems().get(tvBarrelsize.getSelectionModel().getSelectedIndex()).getBarrelsize();
			
			if(stage != null){
				stage.close();
			}
			
		}else{
			System.out.println("Bitte 1 Zeile markieren!");
		}
		
	}
	
	private void refreshTablebarrelsize(){		
		
		ModelBarrelsize barrelsize = new SelectBarrelsize(new ModelBarrelsize()).getModelBarrelsize();
		obsListBarrelsize = barrelsize.getObsListBarrelsizes(); // Required for filter!
		tvBarrelsize.setItems(obsListBarrelsize);	
		
		if(tvBarrelsize.getItems().size() > 0){
			tvBarrelsize.getSelectionModel().selectFirst();
			tvBarrelsize.requestFocus();
		}
		
	}
	
	public String getSelectedBarrelsize(){
		return selectedBarrelsize;
	}
	
}
