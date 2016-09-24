package de.michaprogs.crm.clerk.add;


import de.michaprogs.crm.DeleteAlert;
import de.michaprogs.crm.InitCombos;
import de.michaprogs.crm.bolting.ModelBolting;
import de.michaprogs.crm.bolting.UpdateBolting;
import de.michaprogs.crm.clerk.DeleteClerk;
import de.michaprogs.crm.clerk.InsertClerk;
import de.michaprogs.crm.clerk.ModelClerk;
import de.michaprogs.crm.clerk.SelectClerk;
import de.michaprogs.crm.clerk.SelectClerk.Selection;
import de.michaprogs.crm.clerk.UpdateClerk;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class ControllerClerkAdd {

	@FXML private Label lblSubHeadline;
	
	@FXML private ComboBox<String> cbSalutation;
	@FXML private TextField tfName;
	@FXML private TextField tfPhone;
	@FXML private TextField tfFax;
	@FXML private TextField tfEmail;
	@FXML private TextField tfDepartment;

	@FXML private TableView<ModelClerk> tvClerk;
	@FXML private TableColumn<ModelClerk, Integer> tcClerkID;
	@FXML private TableColumn<ModelClerk, String> tcSalutation;
	@FXML private TableColumn<ModelClerk, String> tcName;
	@FXML private TableColumn<ModelClerk, String> tcPhone;
	@FXML private TableColumn<ModelClerk, String> tcFax;
	@FXML private TableColumn<ModelClerk, String> tcEmail;
	@FXML private TableColumn<ModelClerk, String> tcDepartment;
	
	@FXML private Button btnAdd;
	@FXML private Button btnDelete;
	
	private Stage stage;
	
	public ControllerClerkAdd(){}
	
	@FXML private void initialize(){
		
		/* COMBO BOXES */
		new InitCombos().initComboSalutation(cbSalutation);
			
		/* BUTTONS */
		initBtnAdd();
		initButtonDelete();
		
		/* TABLES */
		initTableClerk();
		
		refreshTable();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnAdd(){
		
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				if(	! tfName.getText().equals("") ||
					! tfPhone.getText().equals("") ||
					! tfFax.getText().equals("") ||
					! tfEmail.getText().equals("") ||
					! tfDepartment.getText().equals("")){
				
				insert();
				refreshTable();
				resetFields();
				
				}else{
					System.out.println("Bitte mindestens 1 Feld ausfüllen!");
				}
				
			}
		});
		
	}
	
	private void initButtonDelete(){
		
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(tvClerk.getSelectionModel().getSelectedItems().size() == 1){
					if(new DeleteAlert().getDelete()){
						new DeleteClerk(tvClerk.getItems().get(tvClerk.getSelectionModel().getSelectedIndex()).getClerkID());
						refreshTable();
					}
				}else{
					System.out.println("Bitte 1 Zeile markieren");
				}
				
			}
		});
		
	}
	
	/*
	 * TABLES
	 */
	private void initTableClerk(){
		
		tcClerkID.setCellValueFactory(new PropertyValueFactory<>("clerkID"));
		tcSalutation.setCellValueFactory(new PropertyValueFactory<>("salutation"));
		tcSalutation.setCellFactory(ComboBoxTableCell.forTableColumn(cbSalutation.getItems()));
		tcSalutation.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ModelClerk,String>>() {

			@Override
			public void handle(CellEditEvent<ModelClerk, String> event) {
				((ModelClerk)event.getTableView().getItems().get(
						event.getTablePosition().getRow())).setSalutation(event.getNewValue());
					
					new UpdateClerk(new ModelClerk(	tcClerkID.getCellData(event.getTablePosition().getRow()), 
													tcSalutation.getCellData(event.getTablePosition().getRow()),
													tcName.getCellData(event.getTablePosition().getRow()),
													tcPhone.getCellData(event.getTablePosition().getRow()),
													tcFax.getCellData(event.getTablePosition().getRow()),
													tcEmail.getCellData(event.getTablePosition().getRow()),
													tcDepartment.getCellData(event.getTablePosition().getRow())
													));		
				
			}
		});
		tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tcName.setCellFactory(TextFieldTableCell.forTableColumn());
		tcName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ModelClerk,String>>() {

			@Override
			public void handle(CellEditEvent<ModelClerk, String> event) {
				((ModelClerk)event.getTableView().getItems().get(
					event.getTablePosition().getRow())).setName(event.getNewValue());
				
				new UpdateClerk(new ModelClerk(	tcClerkID.getCellData(event.getTablePosition().getRow()), 
												tcSalutation.getCellData(event.getTablePosition().getRow()),
												tcName.getCellData(event.getTablePosition().getRow()),
												tcPhone.getCellData(event.getTablePosition().getRow()),
												tcFax.getCellData(event.getTablePosition().getRow()),
												tcEmail.getCellData(event.getTablePosition().getRow()),
												tcDepartment.getCellData(event.getTablePosition().getRow())
												));
								
			}
		});
		tcPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		tcPhone.setCellFactory(TextFieldTableCell.forTableColumn());
		tcPhone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ModelClerk,String>>() {

			@Override
			public void handle(CellEditEvent<ModelClerk, String> event) {
				((ModelClerk)event.getTableView().getItems().get(
					event.getTablePosition().getRow())).setPhone(event.getNewValue());
				
				new UpdateClerk(new ModelClerk(	tcClerkID.getCellData(event.getTablePosition().getRow()), 
												tcSalutation.getCellData(event.getTablePosition().getRow()), 
												tcName.getCellData(event.getTablePosition().getRow()), 
												tcPhone.getCellData(event.getTablePosition().getRow()),
												tcFax.getCellData(event.getTablePosition().getRow()), 
												tcEmail.getCellData(event.getTablePosition().getRow()), 
												tcDepartment.getCellData(event.getTablePosition().getRow()))
												);
								
			}
		});
		tcFax.setCellValueFactory(new PropertyValueFactory<>("fax"));
		tcFax.setCellFactory(TextFieldTableCell.forTableColumn());
		tcFax.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ModelClerk,String>>() {

			@Override
			public void handle(CellEditEvent<ModelClerk, String> event) {
				((ModelClerk)event.getTableView().getItems().get(
					event.getTablePosition().getRow())).setFax(event.getNewValue());
				
				new UpdateClerk(new ModelClerk(	tcClerkID.getCellData(event.getTablePosition().getRow()), 
												tcSalutation.getCellData(event.getTablePosition().getRow()), 
												tcName.getCellData(event.getTablePosition().getRow()), 
												tcPhone.getCellData(event.getTablePosition().getRow()),
												tcFax.getCellData(event.getTablePosition().getRow()), 
												tcEmail.getCellData(event.getTablePosition().getRow()), 
												tcDepartment.getCellData(event.getTablePosition().getRow()))
												);
								
			}
		});
		tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		tcEmail.setCellFactory(TextFieldTableCell.forTableColumn());
		tcEmail.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ModelClerk,String>>() {

			@Override
			public void handle(CellEditEvent<ModelClerk, String> event) {
				((ModelClerk)event.getTableView().getItems().get(
					event.getTablePosition().getRow())).setEmail(event.getNewValue());
				
				new UpdateClerk(new ModelClerk(	tcClerkID.getCellData(event.getTablePosition().getRow()), 
												tcSalutation.getCellData(event.getTablePosition().getRow()), 
												tcName.getCellData(event.getTablePosition().getRow()), 
												tcPhone.getCellData(event.getTablePosition().getRow()),
												tcFax.getCellData(event.getTablePosition().getRow()), 
												tcEmail.getCellData(event.getTablePosition().getRow()), 
												tcDepartment.getCellData(event.getTablePosition().getRow()))
												);
								
			}
		});
		tcDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
		tcDepartment.setCellFactory(TextFieldTableCell.forTableColumn());
		tcDepartment.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ModelClerk,String>>() {

			@Override
			public void handle(CellEditEvent<ModelClerk, String> event) {
				((ModelClerk)event.getTableView().getItems().get(
					event.getTablePosition().getRow())).setDepartment(event.getNewValue());
				
				new UpdateClerk(new ModelClerk(	tcClerkID.getCellData(event.getTablePosition().getRow()), 
												tcSalutation.getCellData(event.getTablePosition().getRow()), 
												tcName.getCellData(event.getTablePosition().getRow()), 
												tcPhone.getCellData(event.getTablePosition().getRow()),
												tcFax.getCellData(event.getTablePosition().getRow()), 
												tcEmail.getCellData(event.getTablePosition().getRow()), 
												tcDepartment.getCellData(event.getTablePosition().getRow()))
												);
								
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
	private void refreshTable(){
		
		SelectClerk clerk = new SelectClerk(new ModelClerk(), Selection.SELECT_ALL);
		tvClerk.setItems(clerk.getObsListClerk());
		
	}
	
	private void insert(){	
			
		new InsertClerk(new ModelClerk(
			cbSalutation.getSelectionModel().getSelectedItem(), 
			tfName.getText(), 
			tfPhone.getText(), 
			tfFax.getText(), 
			tfEmail.getText(), 
			tfDepartment.getText()
		));
		
	}
	
	/*
	 * UI METHODS
	 */
	private void resetFields(){
		
		tfName.clear();
		tfPhone.clear();
		tfFax.clear();
		tfEmail.clear();
		tfDepartment.clear();
		
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
}
