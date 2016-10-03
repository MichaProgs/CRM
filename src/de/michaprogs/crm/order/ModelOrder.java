package de.michaprogs.crm.order;

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
	
	private ObservableList<ModelArticle> obsListArticle = FXCollections.observableArrayList();
	private ObservableList<ModelOrder> obsListOrderSearch = FXCollections.observableArrayList();
	
	/**
	 * Empty Constructor
	 */
	public ModelOrder(){}

	/**	
	 * Constructor for Database (Insert Order)
	 * @param orderID
	 * @param orderDate
	 * @param request
	 * @param requestDate
	 * @param notes
	 * @param supplierID
	 * @param clerkID
	 * @param obsListArticle
	 */
	public ModelOrder(	int orderID, 
						String orderDate, 
						String request, 
						String requestDate, 
						String notes, 
						int supplierID,
						int clerkID, 
						ObservableList<ModelArticle> obsListArticle) {
		
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.request = request;
		this.requestDate = requestDate;
		this.notes = notes;
		this.supplierID = supplierID;
		this.clerkID = clerkID;
		this.obsListArticle = obsListArticle;
		
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
	 * Constructor for Database (Select Order)
	 * @param orderID
	 */
	public ModelOrder(int orderID){
		this.orderID = orderID;
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
	
}
