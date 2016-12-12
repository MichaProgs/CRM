package de.michaprogs.crm.contact.add;

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

public class ControllerContactAdd {

	/* FIELDS */
	@FXML private ComboBox<String> cbSalutation;
	@FXML private TextField tfName;
	@FXML private TextField tfPhone;
	@FXML private TextField tfMobile;
	@FXML private TextField tfFax;
	@FXML private TextField tfEmail;
	@FXML private TextField tfDepartment;
	
	/* BUTTONS */
	@FXML private Button btnAdd;
	@FXML private Button btnAbort;
	
	private Main main;
	private Stage stage;
	private ObservableList<ModelContact> obsListContact = FXCollections.observableArrayList();
	
	public ControllerContactAdd(ObservableList<ModelContact> obsListContact){
		
		this.obsListContact = obsListContact;
		
	}
	
	@FXML private void initialize(){
		
		/* COMBO BOXES */
		new InitCombos().initComboSalutation(cbSalutation);
		
		/* BUTTONS */
		initBtnAdd();
		initBtnAbort();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnAdd(){
		
		btnAdd.setGraphic(new GraphicButton("save_32.png").getGraphicButton());
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				obsListContact.add(new ModelContact(
					0,
					cbSalutation.getSelectionModel().getSelectedItem(),
					tfName.getText(),
					tfPhone.getText(),
					tfMobile.getText(),
					tfFax.getText(),
					tfEmail.getText(),
					tfDepartment.getText()
				));
				
				if(stage != null){
					stage.close();
				}else{
					clearFields();
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
						clearFields();
					}
				}
				
			}
		});
		
	}
	
	/*
	 * UI METHODS
	 */
	public void clearFields(){
		
		tfName.clear();
		tfPhone.clear();
		tfMobile.clear();
		tfFax.clear();
		tfEmail.clear();
		tfDepartment.clear();
		
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
	
	public void setObsListContact(ObservableList<ModelContact> obsListContact){
		this.obsListContact = obsListContact;
	}
	
	public ObservableList<ModelContact> getObsListContact(){
		return obsListContact;
	}
	
}
