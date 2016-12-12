package de.michaprogs.crm.clerk.data;

import de.michaprogs.crm.clerk.ModelClerk;
import de.michaprogs.crm.clerk.SelectClerk;
import de.michaprogs.crm.clerk.search.LoadClerkDataSearch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControllerClerkData {

	/* CLERK */
	@FXML private TextField tfClerkID;
	@FXML private TextField tfClerk;
	@FXML private TextField tfClerkPhone;
	@FXML private TextField tfClerkFax;
	@FXML private TextField tfClerkEmail;
	
	/* BUTTONS */
	@FXML private Button btnClerkSearch;
	
	public ControllerClerkData(){}
	
	@FXML private void initialize(){
		
		//BUTTONS
		initBtnClerkSearch();
		
	}
	
	/*
	 * BUTTONS
	 */	
	private void initBtnClerkSearch(){
		
//		btnClerkSearch.setGraphic() -> see CSS #btnSearchSmall
		btnClerkSearch.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LoadClerkDataSearch clerkData = new LoadClerkDataSearch(true);
				if(clerkData.getController().getSelectedClerkID() != 0){
					selectClerk(clerkData.getController().getSelectedClerkID());
				}
				
			}
		});
		
	}
	
	/*
	 * UI METHODS
	 */
	public void clearFields(){
		
		tfClerkID.clear();
		tfClerk.clear();
		tfClerkEmail.clear();
		tfClerkFax.clear();
		tfClerkPhone.clear();
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	public void selectClerk(int clerkID){
		
		ModelClerk clerk = new SelectClerk(new ModelClerk(clerkID), de.michaprogs.crm.clerk.SelectClerk.Selection.SELECT_SPECIFIC).getModelClerk();
		tfClerkID.setText(String.valueOf(clerk.getClerkID()));
		tfClerk.setText(clerk.getName());
		tfClerkPhone.setText(clerk.getPhone());
		tfClerkFax.setText(clerk.getFax());
		tfClerkEmail.setText(clerk.getEmail());		
		
	}

	/*
	 * GETTER & SETTER
	 */
	public TextField getTfClerkID() {
		return tfClerkID;
	}

	public void setTfClerkID(TextField tfClerkID) {
		this.tfClerkID = tfClerkID;
	}

	public TextField getTfClerk() {
		return tfClerk;
	}

	public void setTfClerk(TextField tfClerk) {
		this.tfClerk = tfClerk;
	}

	public TextField getTfClerkPhone() {
		return tfClerkPhone;
	}

	public void setTfClerkPhone(TextField tfClerkPhone) {
		this.tfClerkPhone = tfClerkPhone;
	}

	public TextField getTfClerkFax() {
		return tfClerkFax;
	}

	public void setTfClerkFax(TextField tfClerkFax) {
		this.tfClerkFax = tfClerkFax;
	}

	public TextField getTfClerkEmail() {
		return tfClerkEmail;
	}

	public void setTfClerkEmail(TextField tfClerkEmail) {
		this.tfClerkEmail = tfClerkEmail;
	}

	public Button getBtnClerkSearch() {
		return btnClerkSearch;
	}

	public void setBtnClerkSearch(Button btnClerkSearch) {
		this.btnClerkSearch = btnClerkSearch;
	}
	
	
	
}
