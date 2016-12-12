package de.michaprogs.crm.customer.data;

import java.math.BigDecimal;
import java.time.LocalDate;

import de.michaprogs.crm.DeleteAlert;
import de.michaprogs.crm.GraphicButton;
import de.michaprogs.crm.Main;
import de.michaprogs.crm.Validate;
import de.michaprogs.crm.contact.data.ControllerContactData;
import de.michaprogs.crm.customer.DeleteCustomer;
import de.michaprogs.crm.customer.ModelCustomer;
import de.michaprogs.crm.customer.SelectCustomer;
import de.michaprogs.crm.customer.UpdateCustomer;
import de.michaprogs.crm.customer.ValidateCustomerSave;
import de.michaprogs.crm.customer.add.LoadCustomerAdd;
import de.michaprogs.crm.customer.search.LoadCustomerSearch;
import de.michaprogs.crm.customer.ui.billingadress.ControllerBillingAdress;
import de.michaprogs.crm.customer.ui.deliveryadress.ControllerDeliveryAdress;
import de.michaprogs.crm.documents.deliverybill.ModelDeliverybill;
import de.michaprogs.crm.documents.deliverybill.SelectDeliverybill;
import de.michaprogs.crm.documents.deliverybill.SelectDeliverybill.DeliverybillSelection;
import de.michaprogs.crm.documents.deliverybill.add.LoadDeliverybillAdd;
import de.michaprogs.crm.documents.deliverybill.data.LoadDeliverybillData;
import de.michaprogs.crm.documents.invoice.ModelInvoice;
import de.michaprogs.crm.documents.invoice.SelectInvoice;
import de.michaprogs.crm.documents.invoice.SelectInvoice.InvoiceSelection;
import de.michaprogs.crm.documents.invoice.add.LoadInvoiceAdd;
import de.michaprogs.crm.documents.invoice.data.LoadInvoiceData;
import de.michaprogs.crm.documents.offer.ModelOffer;
import de.michaprogs.crm.documents.offer.SelectOffer;
import de.michaprogs.crm.documents.offer.SelectOffer.OfferSelection;
import de.michaprogs.crm.documents.offer.add.LoadOfferAdd;
import de.michaprogs.crm.documents.offer.data.LoadOfferData;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class ControllerCustomerData {

	@FXML private Label lblSubHeadline;
	
	/* DELIVERYADRESS - NESTED CONTROLLER */
	@FXML private ControllerDeliveryAdress deliveryAdressController; //fx:id + 'Controller'
	
	/* BILLINGADRESS - NESTED CONTROLLER */
	@FXML private ControllerBillingAdress billingAdressController; //fx:id + 'Controller'
	
	/* CONTACTS - NESTED CONTROLLER */
	@FXML private ControllerContactData contactDataController; //fx:id + 'Controller'
	
	/* NOTES */
	@FXML private TextArea taNotes;
	
	/* LAST CHANGE */
	@FXML private Label lblLastChange;
	
	/* OFFER */
	@FXML private TableView<ModelOffer> tvOffer;
	@FXML private TableColumn<ModelOffer, Integer> tcOfferID;
	@FXML private TableColumn<ModelOffer, String> tcOfferClerk;
	@FXML private TableColumn<ModelOffer, String> tcOfferRequest;
	@FXML private TableColumn<ModelOffer, String> tcOfferDate;
	@FXML private TableColumn<ModelDeliverybill, Integer> tcOfferAmountOfPositions;
	@FXML private TableColumn<ModelDeliverybill, BigDecimal> tcOfferTotal;
	
	/* DELIVERYBILL */
	@FXML private TableView<ModelDeliverybill> tvDeliverybill;
	@FXML private TableColumn<ModelDeliverybill, Integer> tcDeliverybillID;
	@FXML private TableColumn<ModelDeliverybill, String> tcDeliverybillClerk;
	@FXML private TableColumn<ModelDeliverybill, String> tcDeliverybillRequest;
	@FXML private TableColumn<ModelDeliverybill, String> tcDeliverybillDate;
	@FXML private TableColumn<ModelDeliverybill, Integer> tcDeliverybillAmountOfPositions;
	@FXML private TableColumn<ModelDeliverybill, BigDecimal> tcDeliverybillTotal;
	@FXML private TableColumn<ModelDeliverybill, Boolean> tcDeliverybillState;
	
	/* INVOICE */
	@FXML private TableView<ModelInvoice> tvInvoice;
	@FXML private TableColumn<ModelInvoice, Integer> tcInvoiceID;
	@FXML private TableColumn<ModelInvoice, String> tcInvoiceDate;
	@FXML private TableColumn<ModelInvoice, String> tcInvoiceClerk;
	@FXML private TableColumn<ModelInvoice, Integer> tcInvoiceDeliverybillID;
	@FXML private TableColumn<ModelInvoice, String> tcInvoiceDeliveryDate;
	@FXML private TableColumn<ModelInvoice, Integer> tcInvoiceAmountOfPositions;
	@FXML private TableColumn<ModelInvoice, BigDecimal> tcInvoiceTotal;
	
	/* BUTTONS */
	@FXML private Button btnSearch;
	@FXML private Button btnNew;
	@FXML private Button btnEdit;
	      private Button btnEditSave = new Button("Speichern");
	      private Button btnEditAbort = new Button("Abbrechen");
	@FXML private Button btnDelete;
	
	
	
	@FXML private HBox hboxBtnTopbar;
	
	private Stage stage;
	private Main main;
	
	public ControllerCustomerData(){}
	
	@FXML private void initialize(){
		
		deliveryAdressController.showSearchButtonSmall(false);
		
		/* BUTTONS */
		initBtnSearch();
		initBtnNew();
		initBtnEdit();
		initBtnEditSave();
		initBtnEditAbort();
		initBtnDelete();
		
		/* TABLES */
		initTableOffer();
		initTableDeliverybill();
		initTableInvoice();
		
		setButtonState();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnSearch(){
		
		btnSearch.setGraphic(new GraphicButton("search_32.png").getGraphicButton());
		btnSearch.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LoadCustomerSearch customerSearch = new LoadCustomerSearch(true);
				if(customerSearch.getController().getSelectedCustomerID() != 0){
					selectCustomer(customerSearch.getController().getSelectedCustomerID());
				}
				
			}
		});
		
	}
	
	private void initBtnNew(){
		
		btnNew.setGraphic(new GraphicButton("new_32.png").getGraphicButton());
		btnNew.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				LoadCustomerAdd customerAdd = new LoadCustomerAdd(true);
				if(customerAdd.getController().getCreatedCustomerID() != 0){
					selectCustomer(customerAdd.getController().getCreatedCustomerID());
				}
				
			}
		});
		
	}
	
	private void initBtnEdit(){
		
		btnEdit.setGraphic(new GraphicButton("edit_32.png").getGraphicButton());
		btnEdit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
			
				hboxBtnTopbar.getChildren().add(hboxBtnTopbar.getChildren().indexOf(btnEdit) + 1, btnEditSave);
				hboxBtnTopbar.getChildren().add(hboxBtnTopbar.getChildren().indexOf(btnEdit) + 2, btnEditAbort);
				
				enableFields();				
				setButtonState();
				
			}
		});		
		
	}
	
	private void initBtnEditSave(){
		
		btnEditSave.getStyleClass().add("btnTopbar");
		btnEditSave.setGraphic(new GraphicButton("save_32.png").getGraphicButton());
		btnEditSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				/* UPDATE CUSTOMER */
				if(new ValidateCustomerSave(new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()), 
											deliveryAdressController.getTfName1().getText()).isValid()){
					
					new UpdateCustomer(
						new ModelCustomer(
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()),
							deliveryAdressController.getCbSalutation().getSelectionModel().getSelectedItem(),
							deliveryAdressController.getTfName1().getText(), 
							deliveryAdressController.getTfName2().getText(),
							deliveryAdressController.getTfStreet().getText(), 
							deliveryAdressController.getCbLand().getSelectionModel().getSelectedItem(), 
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfZip().getText()), 
							deliveryAdressController.getTfLocation().getText(), 
							
							deliveryAdressController.getTfPhone().getText(), 
							deliveryAdressController.getTfMobile().getText(), 
							deliveryAdressController.getTfFax().getText(), 
							deliveryAdressController.getTfEmail().getText(),
							deliveryAdressController.getTfWeb().getText(), 
							deliveryAdressController.getTfTaxID().getText(), 
							deliveryAdressController.getTfUstID().getText(), 
							
							deliveryAdressController.getCbPayment().getSelectionModel().getSelectedItem(), 
							deliveryAdressController.getTfIBAN().getText(), 
							deliveryAdressController.getTfBIC().getText(), 
							deliveryAdressController.getTfBank().getText(), 
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfPaymentSkonto().getText()),
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfPaymentNetto().getText()), 
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfSkonto().getText()),
							deliveryAdressController.getCbCategory().getSelectionModel().getSelectedItem(),
							
							String.valueOf(LocalDate.now()), 
							taNotes.getText(),
							
							new Validate().new ValidateOnlyInteger().validateOnlyInteger(billingAdressController.getTfCustomerIDBilling().getText())),
						contactDataController.getObsListContact()
					);
					
					hboxBtnTopbar.getChildren().remove(btnEditAbort);
					hboxBtnTopbar.getChildren().remove(btnEditSave);
					
					disableFields();
					setButtonState();
					
					//Reload CustomerData
					if(stage != null){
						stage.close();
					}else{
						selectCustomer(new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()));
					}
					
				}
			}
			
		});		
	}
	
	private void initBtnEditAbort(){
		
		btnEditAbort.getStyleClass().add("btnTopbar");
		btnEditAbort.setGraphic(new GraphicButton("cancel_32.png").getGraphicButton());
		btnEditAbort.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				hboxBtnTopbar.getChildren().remove(btnEditAbort);
				hboxBtnTopbar.getChildren().remove(btnEditSave);
				
				disableFields();
				setButtonState();

				//Reload CustomerData
				selectCustomer(new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()));				
				
			}
		});
		
	}
	
	private void initBtnDelete(){
		
		btnDelete.setGraphic(new GraphicButton("delete_32.png").getGraphicButton());
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				DeleteAlert delete = new DeleteAlert();
				if(delete.getDelete()){
					
					new DeleteCustomer(new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()));
					
					resetFields();
					setButtonState();
					
				}
				
			}
		});
		
	}
	
	
	
	/*
	 * TABLES
	 */
	private void initTableOffer(){
		
		tcOfferID.setCellValueFactory(new PropertyValueFactory<>("offerID"));
		tcOfferClerk.setCellValueFactory(new PropertyValueFactory<>("clerk"));
		tcOfferRequest.setCellValueFactory(new PropertyValueFactory<>("request"));
		tcOfferDate.setCellValueFactory(new PropertyValueFactory<>("offerDate"));
		tcOfferAmountOfPositions.setCellValueFactory(new PropertyValueFactory<>("amountOfPositions"));
		tcOfferTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
		
		tvOffer.setContextMenu(new ContextMenuTableOffer());
		
		tvOffer.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if(event.getClickCount() == 2){
					goToOffer();
				}
				
			}
		});
		
	}
		
	private void initTableDeliverybill(){
		
		tcDeliverybillID.setCellValueFactory(new PropertyValueFactory<>("deliverybillID"));
		tcDeliverybillClerk.setCellValueFactory(new PropertyValueFactory<>("clerk"));
		tcDeliverybillRequest.setCellValueFactory(new PropertyValueFactory<>("request"));
		tcDeliverybillDate.setCellValueFactory(new PropertyValueFactory<>("deliverybillDate"));
		tcDeliverybillAmountOfPositions.setCellValueFactory(new PropertyValueFactory<>("amountOfPositions"));
		tcDeliverybillTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
		tcDeliverybillState.setCellValueFactory(new PropertyValueFactory<>("deliverystate"));
//		tcDeliverybillState.setCellFactory(CheckBoxTableCell.forTableColumn(tcDeliverybillState));TODO
		
		tvDeliverybill.setContextMenu(new ContextMenuTableDeliverybill());
		
		tvDeliverybill.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if(event.getClickCount() == 2){
					goToDeliverybill();
				}
				
			}
		});
		
	}
	
	private void initTableInvoice(){
		
		tcInvoiceID.setCellValueFactory(new PropertyValueFactory<>("invoiceID"));
		tcInvoiceDate.setCellValueFactory(new PropertyValueFactory<>("invoiceDate"));
		tcInvoiceClerk.setCellValueFactory(new PropertyValueFactory<>("clerk"));
		tcInvoiceDeliverybillID.setCellValueFactory(new PropertyValueFactory<>("deliverybillID"));
		tcInvoiceDeliveryDate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
		tcInvoiceAmountOfPositions.setCellValueFactory(new PropertyValueFactory<>("amountOfPositions"));
		tcInvoiceTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
		
		tvInvoice.setContextMenu(new ContextMenuTableInvoice());
		
		tvInvoice.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if(event.getClickCount() == 2){
					goToInvoice();
				}
				
			}
		});
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	private void selectCustomer(int customerID){
		
		ModelCustomer customer = new SelectCustomer(new ModelCustomer(customerID)).getModelCustomer();
		taNotes.setText(customer.getNotes());
		lblLastChange.setText(customer.getLastChange());
		
		/* DELIVERYADRESS */
		deliveryAdressController.selectDeliveryAdress(customerID);
		
		/* CONTACTS */
		contactDataController.setTableData(customer.getObsListContacts());
		
		/* TITLE */
		main.getStage().setTitle((main.getProgramName().concat(" - Kundenstamm " + customer.getCustomerID() + " " + customer.getName1() + ", " + customer.getZip() + " " + customer.getLocation())));
		lblSubHeadline.setText("- " + customer.getCustomerID() + " " + customer.getName1() + ", " + customer.getZip() + " " + customer.getLocation());
		
		/* OFFER */
		selectOffer(customerID);
		
		/* DELIVERYBILL */
		selectDeliverybill(customerID);
		
		/* INVOICE */
		selectInvoice(customerID);
		
		/* BILLING */
		//ALWAYS LAST - OTHERWISE THE DATA IN THE MODEL WOULD BE OVERWRITTEN
		if(customer.getBillingID() != 0){
			billingAdressController.selectBillingAdress(customer.getBillingID());
		}else{				
			billingAdressController.clearFields();
		}
		
		setButtonState();
		
	}
	
	private void selectOffer(int customerID){
		
		if(customerID != 0){
			tvOffer.setItems(new SelectOffer(new ModelOffer(customerID), OfferSelection.ALL_OFFER_FROM_CUSTOMER).getObsListOffer());
		}else{
			System.out.println("Bitte gültige Kundennummer wählen!");
		}
		
	}
	
	private void selectDeliverybill(int customerID){
		
		if(customerID != 0){
			tvDeliverybill.setItems(new SelectDeliverybill(new ModelDeliverybill(customerID), DeliverybillSelection.ALL_DELIVERYBILL_FROM_CUSTOMER).getObsListDeliverybill());
		}else{
			System.out.println("Bitte gültige Kundennummer wählen!");
		}
		
	}
	
	private void selectInvoice(int customerID){
		
		if(customerID != 0){
			tvInvoice.setItems(new SelectInvoice(new ModelInvoice(customerID), InvoiceSelection.ALL_INVOICE_FROM_CUSTOMER).getModelInvoice().getObsListCustomerInvoice());
		}else{
			System.out.println("Bitte gültige Kundennummer wählen!");
		}
		
	}
	
	/*
	 * UI METHODS
	 */
	private void enableFields(){
		
		deliveryAdressController.enableFields();
		taNotes.setEditable(true);
		
	}
	
	private void disableFields(){
		
		deliveryAdressController.disableFields();		
		taNotes.setEditable(false);
		
	}
	
	private void resetFields(){
		
		deliveryAdressController.clearFields();		
		billingAdressController.clearFields();
		
		taNotes.clear();
		lblLastChange.setText("Letzte Änderung: "); //Same is in the FXML-File
		
		lblSubHeadline.setText("");
		
	}
			
	private void setButtonState(){
		
		if(deliveryAdressController.getTfCustomerID().getText().equals("")){
			
			btnEdit.setDisable(true);
			btnDelete.setDisable(true);
			
			/* BILLING */
			billingAdressController.getBtnBillingAdd().setDisable(true);
			billingAdressController.getBtnBillingDelete().setDisable(true);
			
			/* CONTACTS */
			contactDataController.getButtonContactAdd().setDisable(true);
			
		}else{
			
			btnEdit.setDisable(false);
			btnDelete.setDisable(false);
			
			//if the hboxBtnTopbar contains btnEditAbort and btnEditSave it means btnEdit was pressed 
			if(hboxBtnTopbar.getChildren().contains(btnEditAbort) && hboxBtnTopbar.getChildren().contains(btnEditSave)){
				
				btnDelete.setDisable(true);
				btnNew.setDisable(true);
				btnSearch.setDisable(true);
				btnEdit.setDisable(true);
				
				/* BILLING */
				billingAdressController.setButtonState();				
								
				/* CONTACTS */
				contactDataController.getButtonContactAdd().setDisable(false);
				if(contactDataController.getObsListContact().size() > 0){
					contactDataController.getButtonContactDelete().setDisable(false);
					contactDataController.getButtonContactEdit().setDisable(false);
				}
				
			}else{
				
				btnSearch.setDisable(false);
				btnNew.setDisable(false);
				btnEdit.setDisable(false);
				btnDelete.setDisable(false);
				
				/* BILLING */
				billingAdressController.getBtnBillingAdd().setDisable(true);
				billingAdressController.getBtnBillingDelete().setDisable(true);
				
				/* CONTACTS */
				contactDataController.getButtonContactAdd().setDisable(true);
				contactDataController.getButtonContactDelete().setDisable(true);
				contactDataController.getButtonContactEdit().setDisable(true);
				
			}
		}
	}
	
	private boolean editable(){
		
		if(hboxBtnTopbar.getChildren().contains(btnEditSave)){
			return true;
		}else{
			return false;
		}
		
	}
	
	private void goToOffer(){
		
		if(tvOffer.getSelectionModel().getSelectedItems().size() == 1){
			main.getContentPane().setCenter(new LoadOfferData(	false, 
																tvOffer.getItems().get(tvOffer.getSelectionModel().getSelectedIndex()).getOfferID(), 
																Integer.valueOf(deliveryAdressController.getTfCustomerID().getText()),
																main
											).getContent());				
		}else{
			System.out.println("Bitte 1 Zeile markieren!");
		}
		
	}
	
	private void goToDeliverybill(){
		
		if(tvDeliverybill.getSelectionModel().getSelectedItems().size() == 1){
			
			main.getContentPane().setCenter(new LoadDeliverybillData(	false, 
																tvDeliverybill.getItems().get(tvDeliverybill.getSelectionModel().getSelectedIndex()).getDeliverybillID(), 
																Integer.valueOf(deliveryAdressController.getTfCustomerID().getText()),
																main
											).getContent());				
		}else{
			System.out.println("Bitte 1 Zeile markieren!");
		}
		
	}
	
	private void goToInvoice(){
		
		if(tvInvoice.getSelectionModel().getSelectedItems().size() == 1){
			
			main.getContentPane().setCenter(new LoadInvoiceData(	false, 
																	tvInvoice.getItems().get(tvInvoice.getSelectionModel().getSelectedIndex()).getInvoiceID(), 
																	Integer.valueOf(deliveryAdressController.getTfCustomerID().getText()),
																	main)
																	.getContent());				
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
	
	public void setMain(Main main){
		this.main = main;
	}

	/*
	 * CONTEXT MENU
	 */
	private class ContextMenuTableOffer extends ContextMenu{
		
		private MenuItem itemGoTo = new MenuItem("Gehe zu..");
		private MenuItem itemNew = new MenuItem("Hinzufügen..");
		
		public ContextMenuTableOffer(){
			
			initItemGoTo();
			initItemNew();
			
			this.getItems().addAll(	itemGoTo,
									itemNew);
			
			this.setOnShowing(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					
					if(deliveryAdressController.getTfCustomerID().getText().equals("")){
						itemNew.setDisable(true);
						itemGoTo.setDisable(true);
					}else{
						
						if(editable()){
							itemNew.setDisable(true);
							itemGoTo.setDisable(true);
						}else{
						
							itemNew.setDisable(false);
							if(tvOffer.getItems().size() > 0){
								itemGoTo.setDisable(false);
							}else{
								itemGoTo.setDisable(true);
							}
						}
					}
					
				}
			});
			
		}
		
		private void initItemGoTo(){
			
			itemGoTo.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {	
					goToOffer();
				}
			});
			
		}
		
		private void initItemNew(){
			
			itemNew.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					LoadOfferAdd offerAdd = new LoadOfferAdd(true, new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()));
					if(offerAdd.getController().getCreatedOfferID() != 0){
						selectOffer(Integer.valueOf(deliveryAdressController.getTfCustomerID().getText()));
					}
				}
			});
			
		}
		
	}
	
	private class ContextMenuTableDeliverybill extends ContextMenu{
		
		private MenuItem itemGoTo = new MenuItem("Gehe zu..");
		private MenuItem itemNew = new MenuItem("Hinzufügen..");
		
		public ContextMenuTableDeliverybill(){
			
			initItemGoTo();
			initItemNew();
			
			this.getItems().addAll(	itemGoTo,
									itemNew);
			
			this.setOnShowing(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					
					if(deliveryAdressController.getTfCustomerID().getText().equals("")){
						itemNew.setDisable(true);
						itemGoTo.setDisable(true);
					}else{
						
						if(editable()){
							itemNew.setDisable(true);
							itemGoTo.setDisable(true);
						}else{
						
							itemNew.setDisable(false);
							if(tvDeliverybill.getItems().size() > 0){
								itemGoTo.setDisable(false);
							}else{
								itemGoTo.setDisable(true);
							}
						}
					}
					
				}
			});
			
		}
		
		private void initItemGoTo(){
			
			itemGoTo.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {	
					goToDeliverybill();
				}
			});
			
		}
		
		private void initItemNew(){
			
			itemNew.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					LoadDeliverybillAdd DeliverybillAdd = new LoadDeliverybillAdd(true, main, new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()));
					if(DeliverybillAdd.getController().getCreatedDeliverybillID() != 0){
						selectDeliverybill(Integer.valueOf(deliveryAdressController.getTfCustomerID().getText()));
					}
				}
			});
			
		}
		
	}
	
	private class ContextMenuTableInvoice extends ContextMenu{
		
		private MenuItem itemGoTo = new MenuItem("Gehe zu..");
		private MenuItem itemNew = new MenuItem("Hinzufügen..");
		
		public ContextMenuTableInvoice(){
			
			initItemGoTo();
			initItemNew();
			
			this.getItems().addAll(	itemGoTo,
									itemNew);
			
			this.setOnShowing(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					
					if(deliveryAdressController.getTfCustomerID().getText().equals("")){
						itemNew.setDisable(true);
						itemGoTo.setDisable(true);
					}else{
						
						if(editable()){
							itemNew.setDisable(true);
							itemGoTo.setDisable(true);
						}else{
						
							itemNew.setDisable(false);
							if(tvInvoice.getItems().size() > 0){
								itemGoTo.setDisable(false);
							}else{
								itemGoTo.setDisable(true);
							}
						}
					}
					
				}
			});
			
		}
		
		private void initItemGoTo(){
			
			itemGoTo.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {	
					goToInvoice();
				}
			});
			
		}
		
		private void initItemNew(){
			
			itemNew.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					LoadInvoiceAdd InvoiceAdd = new LoadInvoiceAdd(true, main, new Validate().new ValidateOnlyInteger().validateOnlyInteger(deliveryAdressController.getTfCustomerID().getText()));
					if(InvoiceAdd.getController().getCreatedInvoiceID() != 0){
						selectInvoice(Integer.valueOf(deliveryAdressController.getTfCustomerID().getText()));
					}
				}
			});
			
		}
		
	}
	
}
