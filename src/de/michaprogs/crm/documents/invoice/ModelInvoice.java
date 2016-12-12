package de.michaprogs.crm.documents.invoice;

import java.math.BigDecimal;

import de.michaprogs.crm.article.ModelArticle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelInvoice {

	private int invoiceID = 0;
	private String invoiceDate = "";
	private int deliverybillID = 0;
	private String deliveryDate = "";
	private String notes = "";
	private int customerID = 0;
	private int clerkID = 0;
	private String clerk = "";
	private BigDecimal total = new BigDecimal("0.00");
	private int amountOfPositions = 0;
	
	private ObservableList<ModelArticle> obsListPositions = FXCollections.observableArrayList();
	private ObservableList<ModelInvoice> obsListInvoiceSearch = FXCollections.observableArrayList();
	private ObservableList<ModelInvoice> obsListCustomerInvoice = FXCollections.observableArrayList();
	
	public ModelInvoice(){}

	/**
	 * Constructor for Database (Insert Invoice)
	 * @param invoiceID
	 * @param invoiceDate
	 * @param deliverybillID
	 * @param deliveryDate
	 * @param notes
	 * @param customerID
	 * @param clerkID
	 * @param total
	 * @param amountOfPositions
	 * @param obsListPositions
	 */
	public ModelInvoice(int invoiceID,
						String invoiceDate,
						int deliverybillID,
						String deliveryDate,
						String notes,
						int customerID,
						int clerkID,
						int amountOfPositions,
						BigDecimal total,
						ObservableList<ModelArticle> obsListPositions){
		this.invoiceID = invoiceID;
		this.invoiceDate = invoiceDate;
		this.deliverybillID = deliverybillID;
		this.deliveryDate = deliveryDate;
		this.notes = notes;
		this.customerID = customerID;
		this.clerkID = clerkID;
		this.total = total;
		this.amountOfPositions = amountOfPositions;
		this.obsListPositions = obsListPositions;
	}
	
	/**
	 * Constructor for Database (Search Invoice)
	 * @param invoiceID
	 * @param invoiceDate
	 * @param customerID
	 * @param deliverybillID
	 * @param deliveryDate
	 */
	public ModelInvoice(int invoiceID,
						String invoiceDate,
						int customerID,
						int deliverybillID,
						String deliveryDate){
		this.invoiceID = invoiceID;
		this.invoiceDate = invoiceDate;
		this.customerID = customerID;
		this.deliverybillID = deliverybillID;
		this.deliveryDate = deliveryDate;
	}
	
	/**
	 * Constructor for Database (Select Invoice)
	 * Constructor for Database (Delete Invoice)
	 * @param invoiceID
	 * @param customerID
	 */
	public ModelInvoice(int invoiceID,
						int customerID){
		this.invoiceID = invoiceID;
		this.customerID = customerID;
	}
	
	/**
	 * Constructor for Database (Select Invoice Customer)
	 * @param invoiceID
	 */
	public ModelInvoice(int customerID){
		this.customerID = customerID;
	}
	
	/**
	 * Constructor for ObservableList (Search Invoice)
	 * @param invoiceID
	 * @param invoiceDate
	 * @param customerID
	 * @param clerkID
	 * @param deliverybillID
	 * @param deliveryDate
	 */
	public ModelInvoice(int invoiceID,
						String invoiceDate,
						int customerID,
						int clerkID,
						int deliverybillID,
						String deliveryDate){
		this.invoiceID = invoiceID;
		this.invoiceDate = invoiceDate;
		this.customerID = customerID;
		this.clerkID = clerkID;
		this.deliverybillID = deliverybillID;
		this.deliveryDate = deliveryDate;		
	}
	
	/**
	 * Constructor for ObservableList (Customer Invoice)
	 * @param invoiceID
	 * @param invoiceDate
	 * @param deliverybillID
	 * @param deliveryDate
	 * @param clerk
	 * @param amountOfPositions
	 * @param total
	 */
	public ModelInvoice(int invoiceID,
						String invoiceDate,
						int deliverybillID,
						String deliveryDate,
						String clerk,
						int amountOfPositions,
						BigDecimal total){
		this.invoiceID = invoiceID;
		this.invoiceDate = invoiceDate;
		this.deliverybillID = deliverybillID;
		this.deliveryDate = deliveryDate;
		this.clerk = clerk;
		this.amountOfPositions = amountOfPositions;
		this.total = total;
	}
	
	/*
	 * GETTER & SETTER
	 */
	public int getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public int getDeliverybillID() {
		return deliverybillID;
	}

	public void setDeliverybillID(int deliverybillID) {
		this.deliverybillID = deliverybillID;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getClerkID() {
		return clerkID;
	}

	public void setClerkID(int clerkID) {
		this.clerkID = clerkID;
	}

	public String getClerk() {
		return clerk;
	}

	public void setClerk(String clerk) {
		this.clerk = clerk;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public int getAmountOfPositions() {
		return amountOfPositions;
	}

	public void setAmountOfPositions(int amountOfPositions) {
		this.amountOfPositions = amountOfPositions;
	}

	public ObservableList<ModelArticle> getObsListPositions() {
		return obsListPositions;
	}

	public void setObsListPositions(ObservableList<ModelArticle> obsListPositions) {
		this.obsListPositions = obsListPositions;
	}

	public ObservableList<ModelInvoice> getObsListInvoiceSearch() {
		return obsListInvoiceSearch;
	}

	public void setObsListInvoiceSearch(ObservableList<ModelInvoice> obsListInvoiceSearch) {
		this.obsListInvoiceSearch = obsListInvoiceSearch;
	}

	public ObservableList<ModelInvoice> getObsListCustomerInvoice() {
		return obsListCustomerInvoice;
	}

	public void setObsListCustomerInvoice(ObservableList<ModelInvoice> obsListCustomerInvoice) {
		this.obsListCustomerInvoice = obsListCustomerInvoice;
	}
	
	
}
