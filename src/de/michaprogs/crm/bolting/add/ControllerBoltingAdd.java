package de.michaprogs.crm.bolting.add;

import de.michaprogs.crm.DeleteAlert;
import de.michaprogs.crm.bolting.DeleteBolting;
import de.michaprogs.crm.bolting.ModelBolting;
import de.michaprogs.crm.bolting.UpdateBolting;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ControllerBoltingAdd {

	@FXML private TextField tfFilter;
	
	//Tables & Columns
	      private ObservableList<ModelBolting> obsListBolting = FXCollections.observableArrayList();
	@FXML private TableView<ModelBolting> tvBolting;
	@FXML private TableColumn<ModelBolting, Integer> tcBoltingID;
	@FXML private TableColumn<ModelBolting, String> tcBolting;
	
	@FXML private Label lblSubHeadline;
	
	//TextFields
	@FXML private TextField tfBolting;
	
	//Buttons
	@FXML private Button btnAdd;
	@FXML private Button btnDelete;
	
	private Stage stage;
	
	public ControllerBoltingAdd(){
		
	}
	
	@FXML private void initialize(){
		
		/* BUTTONS */
		initBtnDelete();
		initBtnAdd();
		
		/* TABLES */
		initTableBolting();
		
		/* TEXTFIELDS */
		initTfFilter();
		
		//Load all Bolting from Database and show
		refreshTable();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnDelete(){
		
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				deleteBolting();							
			}
		});	
	}
	
	private void initBtnAdd(){
		
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				new ModelBolting().insertBolting(tfBolting.getText());
				refreshTable();
				tfBolting.clear();
				
			}
		});
		
	}
	
	/*
	 * TABLES
	 */
	private void initTableBolting(){
		
		tcBoltingID.setCellValueFactory(new PropertyValueFactory<>("boltingID"));	
		tcBolting.setCellValueFactory(new PropertyValueFactory<>("bolting"));
		tcBolting.setCellFactory(TextFieldTableCell.forTableColumn());
		tcBolting.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ModelBolting,String>>() {

			@Override
			public void handle(CellEditEvent<ModelBolting, String> event) {
				((ModelBolting)event.getTableView().getItems().get(
					event.getTablePosition().getRow())).setBolting(event.getNewValue());
				
				new UpdateBolting(new ModelBolting(	tcBoltingID.getCellData(event.getTablePosition().getRow()), 
													tcBolting.getCellData(event.getTablePosition().getRow())));
								
			}
		});
		
		tvBolting.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ModelBolting>() {
			
			@Override
			public void changed(ObservableValue<? extends ModelBolting> observable, ModelBolting oldValue,
					ModelBolting newValue) {
				lblSubHeadline.setText(tcBolting.getCellData(tvBolting.getSelectionModel().getSelectedIndex()));					
			}
			
		});
		
		tvBolting.setContextMenu(new ContextMenuTable());
		
	}
	
	/*
	 * TEXTFIELDS
	 */
	private void initTfFilter(){
		
		tfFilter.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				
				FilteredList<ModelBolting> filteredList = new FilteredList<>(obsListBolting);
				filteredList.setPredicate(bolting ->{
					
					if(tfFilter.getText().isEmpty() || tfFilter.getText() == null){
						return true;
					}else if(bolting.getBolting().toLowerCase().contains(tfFilter.getText().toLowerCase())){
						return true;
					}
					
					return false;
					
				});
				
				tvBolting.setItems(filteredList);
				
			}
		});
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	private void refreshTable(){		
		ModelBolting bolting = new ModelBolting();
		bolting.selectBoltings();
		obsListBolting = bolting.getObsListBoltings();
		tvBolting.setItems(bolting.getObsListBoltings());	
	}
	
	private void deleteBolting(){
		
		if(tvBolting.getSelectionModel().getSelectedItems().size() == 1){
			if(new DeleteAlert().getDelete()){
				new DeleteBolting(tcBoltingID.getCellData(tvBolting.getSelectionModel().getSelectedIndex()));
				refreshTable();
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
	
	private class ContextMenuTable extends ContextMenu{
		
		private MenuItem itemDelete = new MenuItem("Löschen");;
		
		public ContextMenuTable(){
			
			initItemDelete();
			
			this.getItems().add(itemDelete);
			
		}
		
		private void initItemDelete(){
			
			itemDelete.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					deleteBolting();
				}
			});
			
		}
		
	}
	
}
