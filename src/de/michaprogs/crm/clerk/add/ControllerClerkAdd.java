package de.michaprogs.crm.clerk.add;


import de.michaprogs.crm.DeleteAlert;
import de.michaprogs.crm.InitCombos;
import de.michaprogs.crm.clerk.DeleteClerk;
import de.michaprogs.crm.clerk.InsertClerk;
import de.michaprogs.crm.clerk.ModelClerk;
import de.michaprogs.crm.clerk.SelectClerk;
import de.michaprogs.crm.clerk.SelectClerk.Selection;
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
import javafx.scene.control.cell.PropertyValueFactory;
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
		
		tcSalutation.setCellValueFactory(new PropertyValueFactory<>("salutation"));
		tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tcPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		tcFax.setCellValueFactory(new PropertyValueFactory<>("fax"));
		tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		tcDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
		
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
