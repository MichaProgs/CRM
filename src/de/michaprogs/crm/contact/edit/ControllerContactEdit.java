package de.michaprogs.crm.contact.edit;

import de.michaprogs.crm.AbortAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.InitCombos;
import de.michaprogs.crm.Main;
import de.michaprogs.crm.contact.ModelContact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerContactEdit {

	/* FIELDS */
	@FXML private ComboBox<String> cbSalutation;
	@FXML private TextField tfName;
	@FXML private TextField tfPhone;
	@FXML private TextField tfMobile;
	@FXML private TextField tfFax;
	@FXML private TextField tfEmail;
	@FXML private TextField tfDepartment;
	
	/* BUTTONS */
	@FXML private Button btnSave;
	@FXML private Button btnAbort;
	
	private Main main;
	private Stage stage;
	private ObservableList<ModelContact> obsListContact = FXCollections.observableArrayList();
	private int index;
	
	public ControllerContactEdit(){}
	
	@FXML private void initialize(){
		
		/* COMBO BOXES */
		new InitCombos().initComboSalutation(cbSalutation);
		
		/* BUTTONS */
		initBtnSave();
		initBtnAbort();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnSave(){
		
		btnSave.setGraphic(new GraphicButton("save_32.png").getGraphicButton());
		btnSave.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				obsListContact.get(index).setSalutation(cbSalutation.getSelectionModel().getSelectedItem());
				obsListContact.get(index).setName(tfName.getText());
				obsListContact.get(index).setPhone(tfPhone.getText());
				obsListContact.get(index).setMobile(tfMobile.getText());
				obsListContact.get(index).setFax(tfFax.getText());
				obsListContact.get(index).setEmail(tfEmail.getText());
				obsListContact.get(index).setDepartment(tfDepartment.getText());
				
				if(stage != null){
					stage.close();
				}else{
					//TODO RESET FIELDS
				}
				
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
						//TODO RESET FIELDS
					}
				}
				
			}
		});
		
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	public void setContactData(ObservableList<ModelContact> obsListContact, int index){
		
		this.obsListContact = obsListContact;
		this.index = index;
		
		cbSalutation.getSelectionModel().select(obsListContact.get(index).getSalutation());
		tfName.setText(obsListContact.get(index).getName());
		tfPhone.setText(obsListContact.get(index).getPhone());
		tfFax.setText(obsListContact.get(index).getFax());
		tfEmail.setText(obsListContact.get(index).getEmail());
		tfDepartment.setText(obsListContact.get(index).getDepartment());
		
	}
	
	public ObservableList<ModelContact> getObsListContact(){
		return obsListContact;
	}
	
	
}
