package de.michaprogs.crm.amountunit.add;

import de.michaprogs.crm.DeleteAlert;
import de.michaprogs.crm.amountunit.DeleteAmountUnit;
import de.michaprogs.crm.amountunit.InsertAmountUnit;
import de.michaprogs.crm.amountunit.ModelAmountUnit;
import de.michaprogs.crm.amountunit.SelectAmountUnit;
import de.michaprogs.crm.amountunit.UpdateAmountUnit;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class ControllerAmountUnitAdd {

	@FXML private Label lblSubHeadline;
	
	@FXML private TableView<ModelAmountUnit> tvAmountUnit;
	@FXML private TableColumn<ModelAmountUnit, Integer> tcAmountUnitID;
	@FXML private TableColumn<ModelAmountUnit, String> tcAmountUnit;
	
	@FXML private TextField tfAmountUnit;
	
	@FXML private Button btnAdd;
	@FXML private Button btnDelete;
	
	private Stage stage;
	
	public ControllerAmountUnitAdd(){}
	
	@FXML private void initialize(){
	
		/* BUTTONS */
		initBtnAdd();
		initBtnDelete();
		
		/* TABLES */
		initTableAmountUnit();
		
		refreshTable();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnAdd(){
		
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				if(! tfAmountUnit.getText().isEmpty()){					
					new InsertAmountUnit(new ModelAmountUnit(tfAmountUnit.getText()));
					tfAmountUnit.clear();
					refreshTable();
				}else{
					System.out.println("Bitte Bezeichnung angeben!");
				}
				
			}
		});
		
	}
	
	private void initBtnDelete(){
		
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(tvAmountUnit.getSelectionModel().getSelectedItems().size() == 1){
					if(new DeleteAlert().getDelete()){
						new DeleteAmountUnit(tcAmountUnitID.getCellData(tvAmountUnit.getSelectionModel().getSelectedIndex()));
						refreshTable();
					}
				}else{
					System.out.println("Bitte 1 Zeile markieren!");
				}
				
			}
		});
		
	}
	
	/*
	 * TABLES
	 */
	private void initTableAmountUnit(){
		
		tcAmountUnitID.setCellValueFactory(new PropertyValueFactory<>("amountUnitID"));
		tcAmountUnit.setCellValueFactory(new PropertyValueFactory<>("amountUnit"));
		tcAmountUnit.setCellFactory(TextFieldTableCell.forTableColumn());
		tcAmountUnit.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ModelAmountUnit,String>>() {

			@Override
			public void handle(CellEditEvent<ModelAmountUnit, String> event) {
				((ModelAmountUnit)event.getTableView().getItems().get(
					event.getTablePosition().getRow())).setAmountUnit(event.getNewValue());
				
				new UpdateAmountUnit(new ModelAmountUnit(	tcAmountUnitID.getCellData(event.getTablePosition().getRow()), 
															tcAmountUnit.getCellData(event.getTablePosition().getRow())));
								
			}
			
		});
		
		tvAmountUnit.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ModelAmountUnit>() {

			@Override
			public void changed(ObservableValue<? extends ModelAmountUnit> observable, ModelAmountUnit oldValue,
					ModelAmountUnit newValue) {
				lblSubHeadline.setText(tcAmountUnit.getCellData(tvAmountUnit.getSelectionModel().getSelectedIndex()));				
			}
			
		});
		
	}
	
	private void refreshTable(){		
		tvAmountUnit.setItems(new SelectAmountUnit(new ModelAmountUnit()).getModelAmountUnit().getObsListAmountUnits());		
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
}
