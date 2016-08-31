package de.michaprogs.crm.contact.data;

import de.michaprogs.crm.DeleteAlert;
import de.michaprogs.crm.Main;
import de.michaprogs.crm.contact.ModelContact;
import de.michaprogs.crm.contact.add.LoadContactAdd;
import de.michaprogs.crm.contact.edit.LoadContactEdit;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ControllerContactData {
	
	@FXML private TableView<ModelContact> tvContact;
	@FXML private TableColumn<ModelContact, String> tcContactSalutation;
	@FXML private TableColumn<ModelContact, String> tcContactName;
	@FXML private TableColumn<ModelContact, String> tcContactPhone;
	@FXML private TableColumn<ModelContact, String> tcContactFax;
	@FXML private TableColumn<ModelContact, String> tcContactEmail;
	@FXML private TableColumn<ModelContact, String> tcContactDepartment;
	
	/* BUTTONS */
	@FXML private Button btnContactAdd;
	@FXML private Button btnContactEdit;
	@FXML private Button btnContactDelete;
	
	private Main main;
	private Stage stage;
	
	public ControllerContactData(){}
	
	@FXML private void initialize(){
		
		/* BUTTONS */
		initBtnContactAdd();
		initBtnContactEdit();
		initBtnContactDelete();
		
		/* TABLES */
		initTableContact();
		
		setButtonState();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnContactAdd(){
	
		btnContactAdd.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				addContact();
			}
		});
		
	}
	
	private void initBtnContactEdit(){
		
		btnContactEdit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				editContact();
			}
		});
		
	}
	
	private void initBtnContactDelete(){
		
		btnContactDelete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				deleteContact();
			}
		});
		
	}
	
	/*
	 * TABLES
	 */
	private void initTableContact(){
		
		tcContactSalutation.setCellValueFactory(new PropertyValueFactory<>("salutation"));
		tcContactName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tcContactPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		tcContactFax.setCellValueFactory(new PropertyValueFactory<>("fax"));
		tcContactEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		tcContactDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
		
		tvContact.setContextMenu(new ContextMenuTableContacts());
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	private void addContact(){	
		
		LoadContactAdd contactAdd = new LoadContactAdd(true, main, tvContact.getItems());
		tvContact.setItems(contactAdd.getController().getObsListContact());	
		setButtonState();
		
	}
	
	private void editContact(){
		
		if(tvContact.getSelectionModel().getSelectedItems().size() == 1){
			LoadContactEdit contactEdit = new LoadContactEdit(true, main, tvContact.getItems(), tvContact.getSelectionModel().getSelectedIndex());
			tvContact.setItems(contactEdit.getController().getObsListContact());
			setButtonState();
		}else{
			System.out.println("Bitte 1 Zeile markieren!");
		}
		
	}
	
	private void deleteContact(){
		
		if(tvContact.getSelectionModel().getSelectedItems().size() == 1){
			DeleteAlert delete = new DeleteAlert();
			if(delete.getDelete()){
				tvContact.getItems().remove(tvContact.getSelectionModel().getSelectedIndex());
				setButtonState();
			}
		}else{
			System.out.println("Bitte 1 Zeile markieren!");
		}
		
	}
	
	/*
	 * UI METHODS
	 */
	private void setButtonState(){
		
		if(tvContact.getItems().size() > 0){
			btnContactEdit.setDisable(false);
			btnContactDelete.setDisable(false);
		}else{
			btnContactEdit.setDisable(true);
			btnContactDelete.setDisable(true);
		}
		
	}
		
	/*
	 * GETTER & SETTER
	 */
	public void setMain(Main main){
		this.main = main;
	}
	
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	public ObservableList<ModelContact> getObsListContact(){
		return tvContact.getItems();
	}
	
	public void setTableData(ObservableList<ModelContact> obsListContact){
		tvContact.setItems(obsListContact);
	}
	
	public Button getButtonContactAdd(){
		return btnContactAdd;
	}
	
	public Button getButtonContactEdit(){
		return btnContactEdit;
	}
	
	public Button getButtonContactDelete(){
		return btnContactDelete;
	}
	
	/*
	 * CONTEXT MENU
	 */
	private class ContextMenuTableContacts extends ContextMenu{
		
		private MenuItem itemAdd = new MenuItem("Hinzufügen..");
		private MenuItem itemEdit = new MenuItem("Bearbeiten..");
		private MenuItem itemDelete = new MenuItem("Löschen");
		
		public ContextMenuTableContacts(){
		
			initItemAdd();
			initItemEdit();
			initItemDelete();
			
			this.getItems().addAll(itemAdd, itemEdit, itemDelete);
			this.setOnShowing(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					
					if(btnContactAdd.isDisabled()){
						itemAdd.setDisable(true);
					}else{
						itemAdd.setDisable(false);
					}
					
					if(btnContactEdit.isDisabled()){
						itemEdit.setDisable(true);
					}else{
						itemEdit.setDisable(false);
					}
					
					if(btnContactDelete.isDisabled()){
						itemDelete.setDisable(true);
					}else{
						itemDelete.setDisable(false);
					}
					
				}
			});
			
		}
		
		private void initItemAdd(){
			
			itemAdd.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					addContact();
				}
			});
			
		}
		
		private void initItemEdit(){

			itemEdit.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					editContact();
				}
			});
			
		}
		
		private void initItemDelete(){
			
			itemDelete.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					deleteContact();					
				}
			});
			
		}
		
	}
	
}
