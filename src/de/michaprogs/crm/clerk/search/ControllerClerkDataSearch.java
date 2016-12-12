package de.michaprogs.crm.clerk.search;

import de.michaprogs.crm.AbortAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.clerk.ModelClerk;
import de.michaprogs.crm.clerk.SelectClerk;
import de.michaprogs.crm.clerk.SelectClerk.Selection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerClerkDataSearch {

	@FXML private Label lblSubHeadline;

	@FXML private TableView<ModelClerk> tvClerk;
	@FXML private TableColumn<ModelClerk, String> tcSalutation;
	@FXML private TableColumn<ModelClerk, String> tcName;
	@FXML private TableColumn<ModelClerk, String> tcPhone;
	@FXML private TableColumn<ModelClerk, String> tcFax;
	@FXML private TableColumn<ModelClerk, String> tcEmail;
	@FXML private TableColumn<ModelClerk, String> tcDepartment;
	
	@FXML private Button btnSelect;
	@FXML private Button btnAbort;
	
	private Stage stage;
	private int selectedClerkID = 0;
	
	public ControllerClerkDataSearch(){}
	
	@FXML private void initialize(){
			
		/* BUTTONS */
		initBtnSelect();
		initBtnAbort();
		
		/* TABLES */
		initTableClerk();
		
		selectAll();
		
	}
	
	/*
	 * BUTTONS
	 */
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
					}
				}
				
			}
		});
		
	}
	
	/*
	 * TABLES
	 */
	private void initTableClerk(){
		
		tcSalutation.setCellValueFactory(new PropertyValueFactory<>("salutation"));
		tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tcPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		tcFax.setCellValueFactory(new PropertyValueFactory<>("fax"));
		tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		tcDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
		
		tvClerk.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if(event.getClickCount() == 2){
					select();
				}
				
			}
		});
		
		tvClerk.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ModelClerk>() {

			@Override
			public void changed(ObservableValue<? extends ModelClerk> observable, ModelClerk oldValue,
					ModelClerk newValue) {
				lblSubHeadline.setText(tcName.getCellData(tvClerk.getSelectionModel().getSelectedIndex()));	
			}
			
		});
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	private void selectAll(){
		
		SelectClerk clerk = new SelectClerk(new ModelClerk(), Selection.SELECT_ALL);
		tvClerk.setItems(clerk.getObsListClerk());
		
	}
	
	private void select(){
		
		if(tvClerk.getSelectionModel().getSelectedItems().size() == 1){
			
			selectedClerkID = tvClerk.getItems().get(tvClerk.getSelectionModel().getSelectedIndex()).getClerkID();
			
			if(stage != null){
				stage.close();
			}
			
		}else{
			System.out.println("Bitte 1 Zeile markieren!");
		}
		
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	public int getSelectedClerkID(){
		return selectedClerkID;
	}
	
}
