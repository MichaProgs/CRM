package de.michaprogs.crm.order;

import java.math.BigDecimal;

import de.michaprogs.crm.article.ModelArticle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelOrder {
	
	private int orderID = 0;
	private String orderDate = "";
	private String request = "";
	private String requestDate = "";
	private String notes = "";
	private int supplierID = 0;
	private int clerkID = 0;
	private String clerk = "";
	private BigDecimal total = new BigDecimal("0.00");
	private int amountOfPositions = 0;
	
	private ObservableList<ModelArticle> obsListArticle = FXCollections.observableArrayList();
	private ObservableList<ModelOrder> obsListOrderSearch = FXCollections.observableArrayList();
	private ObservableList<ModelOrder> obsListSupplierOrder = FXCollections.observableArrayList();
	
	/**
	 * Empty Constructor
	 */
	public ModelOrder(){}

	/**
	 * Constructor for ObservableList (SupplierOrder)
	 * @param orderID
	 * @param orderDate
	 * @param request
	 * @param requestDate
	 * @param total
	 * @param amountOfPositions
	 */
	public ModelOrder(	int orderID,
						String orderDate,
						String request,
						String requestDate,
						String clerk,
						BigDecimal total,
						int amountOfPositions){
		
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.request = request;
		this.requestDate = requestDate;
		this.clerk = clerk;
		this.total = total;
		this.amountOfPositions = amountOfPositions;
		
	}
	
	/**	
	 * Constructor for Database (Insert Order) <br>
	 * Constructor for Database (Update Order)
	 * @param orderID
	 * @param orderDate
	 * @param request
	 * @param requestDate
	 * @param notes
	 * @param supplierID
	 * @param clerkID
	 * @param obsListArticle
	 * @param total
	 * @param amountOfPositions
	 */
	public ModelOrder(	int orderID, 
						String orderDate, 
						String request, 
						String requestDate, 
						String notes, 
						int supplierID,
						int clerkID, 
						ObservableList<ModelArticle> obsListArticle,
						BigDecimal total,
						int amountOfPositions) {
		
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.request = request;
		this.requestDate = requestDate;
		this.notes = notes;
		this.supplierID = supplierID;
		this.clerkID = clerkID;
		this.obsListArticle = obsListArticle;
		this.total = total;
		this.amountOfPositions = amountOfPositions;
		
	}
	
	/**
	 * Constructor for Database (Search Order)
	 * @param orderID
	 * @param orderDate
	 * @param request
	 * @param requestDate
	 * @param supplierID
	 * @param clerkID
	 */
	public ModelOrder(	int orderID,
						String orderDate,
						String request,
						String requestDate,
						int supplierID,
						int clerkID){
		
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.request = request;
		this.requestDate = requestDate;
		this.supplierID = supplierID;
		this.clerkID = clerkID;
		
	}
	
	/**
	 * Constructor for Database (Delete Order)
	 * Constructor for Database (Select Order)
	 * @param orderID
	 * @param supplierID
	 */
	public ModelOrder(	int orderID,
						int supplierID){
		this.orderID = orderID;
		this.supplierID = supplierID;
	}

	/**
	 * Constructor for Database (Select Order Supplier)
	 * @param supplierID
	 */
	public ModelOrder(int supplierID){
		this.supplierID = supplierID;
	}


	/*
	 * GETTER & SETTER
	 */
	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
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

	public int getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}

	public int getClerkID() {
		return clerkID;
	}

	public void setClerkID(int clerkID) {
		this.clerkID = clerkID;
	}

	public ObservableList<ModelArticle> getObsListArticle() {
		return obsListArticle;
	}

	public void setObsListArticle(ObservableList<ModelArticle> obsListArticle) {
		this.obsListArticle = obsListArticle;
	}

	public ObservableList<ModelOrder> getObsListOrderSearch() {
		return obsListOrderSearch;
	}

	public void setObsListOrderSearch(ObservableList<ModelOrder> obsListOrderSearch) {
		this.obsListOrderSearch = obsListOrderSearch;
	}

	public ObservableList<ModelOrder> getObsListSupplierOrder() {
		return obsListSupplierOrder;
	}

	public void setObsListSupplierOrder(ObservableList<ModelOrder> obsListSupplierOrder) {
		this.obsListSupplierOrder = obsListSupplierOrder;
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

	public String getClerk() {
		return clerk;
	}

	public void setClerk(String clerk) {
		this.clerk = clerk;
	}	
	
	
	
}
