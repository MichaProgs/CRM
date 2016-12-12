package de.michaprogs.crm.documents.deliverybill;

import java.math.BigDecimal;

import de.michaprogs.crm.article.ModelArticle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelDeliverybill {

	private int deliverybillID = 0;
	private String deliverybillDate = "";
	private String request = "";
	private String requestDate = "";
	private String notes = "";
	private int customerID = 0;
	private int clerkID = 0;
	private String clerk = "";
	private BigDecimal total = new BigDecimal("0.00");
	private int amountOfPositions = 0;
	private boolean deliverystate = false;
	
	private ObservableList<ModelArticle> obsListArticle = FXCollections.observableArrayList();
	private ObservableList<ModelDeliverybill> obsListDeliverybillSearch = FXCollections.observableArrayList();
	private ObservableList<ModelDeliverybill> obsListSupplierDeliverybill = FXCollections.observableArrayList();
	
	/**
	 * Empty Constructor
	 */
	public ModelDeliverybill(){}

	/**
	 * Constructor for ObservableList (Deliverybill Search)
	 * @param deliverybillID
	 * @param deliverybillDate
	 * @param customerID
	 * @param request
	 * @param requestDate
	 */
	public ModelDeliverybill(	int deliverybillID, 
								String deliverybillDate, 
								int customerID, 
								String request, 
								String requestDate){
		this.deliverybillID = deliverybillID;
		this.deliverybillDate = deliverybillDate;
		this.customerID = customerID;
		this.request = request;
		this.requestDate = requestDate;
	}

	/**
	 * Constructor for Database (Insert Deliverybill) <br>
	 * Constructor for Database (Update Deliverybill)
	 * @param deliverbillID
	 * @param deliverbillDate
	 * @param request
	 * @param requestDate
	 * @param notes
	 * @param customerID
	 * @param clerkID
	 * @param obsListArticles
	 * @param deliverystate
	 */
	public ModelDeliverybill(	int deliverbillID, 
								String deliverbillDate, 
								String request, 
								String requestDate, 
								String notes,
								int customerID, 
								int clerkID,
								int amountOfPositions,
								BigDecimal total,
								ObservableList<ModelArticle> obsListArticles,
								boolean deliverystate) {
		this.deliverybillID = deliverbillID;
		this.deliverybillDate = deliverbillDate;
		this.request = request;
		this.requestDate = requestDate;
		this.notes = notes;
		this.customerID = customerID;
		this.clerkID = clerkID;
		this.amountOfPositions = amountOfPositions;
		this.total = total;
		this.obsListArticle = obsListArticles;
		this.deliverystate = deliverystate;
	}

	/**
	 * Constructor for Databse (Delete Deliverybill) <br>
	 * Constructor for Database (Select specific Deliverybill)
	 * @param deliverbillID
	 * @param customerID
	 */
	public ModelDeliverybill(int deliverbillID, int customerID) {
		this.deliverybillID = deliverbillID;
		this.customerID = customerID;
	}

	/**
	 * Constructor for Database (Select all Deliverybills from Customer)
	 * @param customerID
	 */
	public ModelDeliverybill(int customerID) {
		this.customerID = customerID;
	}

	/**
	 * Constructor for Database (Update DeliverybillState)
	 * @param deliverybillID
	 * @param deliverstate
	 */
	public ModelDeliverybill(int deliverybillID, boolean deliverstate){
		this.deliverybillID = deliverybillID;
		this.deliverystate = deliverstate;
	}
	
	/**
	 * Constructor for ObservableList (Customer Deliverybill)
	 * @param deliverybillID
	 * @param deliverbillDate
	 * @param request
	 * @param requestDate
	 * @param clerk
	 * @param amountOfPositions
	 * @param total
	 */
	public ModelDeliverybill(	int deliverybillID, 
								String deliverbillDate, 
								String request, 
								String requestDate, 
								String clerk,
								int amountOfPositions,
								BigDecimal total,
								boolean deliverystate) {
		this.deliverybillID = deliverybillID;
		this.deliverybillDate = deliverbillDate;
		this.clerk = clerk;
		this.request = request;
		this.requestDate = requestDate;
		this.amountOfPositions = amountOfPositions;
		this.total = total;
		this.deliverystate = deliverystate;
	}

	/*
	 * GETTER & SETTER
	 */
	public int getDeliverybillID() {
		return deliverybillID;
	}

	public void setDeliverybillID(int deliverybillID) {
		this.deliverybillID = deliverybillID;
	}

	public String getDeliverybillDate() {
		return deliverybillDate;
	}

	public void setDeliverybillDate(String deliverybillDate) {
		this.deliverybillDate = deliverybillDate;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
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

	public ObservableList<ModelArticle> getObsListArticle() {
		return obsListArticle;
	}

	public void setObsListArticle(ObservableList<ModelArticle> obsListArticle) {
		this.obsListArticle = obsListArticle;
	}

	public ObservableList<ModelDeliverybill> getObsListDeliverybillSearch() {
		return obsListDeliverybillSearch;
	}

	public void setObsListDeliverybillSearch(ObservableList<ModelDeliverybill> obsListDeliverybillSearch) {
		this.obsListDeliverybillSearch = obsListDeliverybillSearch;
	}

	public ObservableList<ModelDeliverybill> getObsListSupplierDeliverybill() {
		return obsListSupplierDeliverybill;
	}

	public void setObsListSupplierDeliverybill(ObservableList<ModelDeliverybill> obsListSupplierDeliverybill) {
		this.obsListSupplierDeliverybill = obsListSupplierDeliverybill;
	}

	public boolean getDeliverystate() {
		return deliverystate;
	}

	public void setDeliverystate(boolean deliverystate) {
		this.deliverystate = deliverystate;
	}
	
	
	
}
