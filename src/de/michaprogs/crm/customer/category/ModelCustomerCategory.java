package de.michaprogs.crm.customer.category;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelCustomerCategory {

	private int CustomerCategoryID = 0;
	private String CustomerCategory = "";
	
	private ObservableList<ModelCustomerCategory> obsListCustomerCategories = FXCollections.observableArrayList();
	private ObservableList<String> obsListCustomerCategoriesComboBox = FXCollections.observableArrayList();
	
	public ModelCustomerCategory(){}

	/**
	 * Constructor for Database (Insert CustomerCategory) <br>
	 * Constructor for Database (Delete CustomerCategory) <br>
	 * @param CustomerCategory
	 */
	public ModelCustomerCategory(String CustomerCategory){
		this.CustomerCategory = CustomerCategory;
	}
	
	/**
	 * Constructor for Database (Select CustomerCategory) <br>
	 * @param id
	 * @param CustomerCategory
	 */
	public ModelCustomerCategory(int CustomerCategoryID, String CustomerCategory){
		this.CustomerCategoryID = CustomerCategoryID;
		this.CustomerCategory = CustomerCategory;
		
	}
	
	public String getCustomerCategory() {
		return CustomerCategory;
	}

	public void setCustomerCategory(String CustomerCategory) {
		this.CustomerCategory = CustomerCategory;
	}

	public int getCustomerCategoryID() {
		return CustomerCategoryID;
	}

	public void setCustomerCategoryID(int CustomerCategoryID) {
		this.CustomerCategoryID = CustomerCategoryID;
	}

	public ObservableList<ModelCustomerCategory> getObsListCustomerCategories() {
		return obsListCustomerCategories;
	}

	public void setObsListCustomerCategories(ObservableList<ModelCustomerCategory> obsListCustomerCategories) {
		this.obsListCustomerCategories = obsListCustomerCategories;
	}

	public ObservableList<String> getObsListCustomerCategoriesComboBox() {
		return obsListCustomerCategoriesComboBox;
	}

	public void setObsListCustomerCategoriesComboBox(ObservableList<String> obsListCustomerCategoriesComboBox) {
		this.obsListCustomerCategoriesComboBox = obsListCustomerCategoriesComboBox;
	}
	
	
	
	
}
