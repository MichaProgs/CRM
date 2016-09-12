package de.michaprogs.crm.articlecategory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelArticleCategory {

	private int articleCategoryID = 0;
	private String articleCategory = "";
	
	private ObservableList<ModelArticleCategory> obsListArticleCategories = FXCollections.observableArrayList();
	private ObservableList<String> obsListArticleCategoriesComboBox = FXCollections.observableArrayList();
	
	public ModelArticleCategory(){}

	/**
	 * Constructor for Database (Insert ArticleCategory) <br>
	 * Constructor for Database (Delete ArticleCategory) <br>
	 * @param articleCategory
	 */
	public ModelArticleCategory(String articleCategory){
		this.articleCategory = articleCategory;
	}
	
	/**
	 * Constructor for Database (Select ArticleCategory) <br>
	 * @param id
	 * @param articleCategory
	 */
	public ModelArticleCategory(int articleCategoryID, String articleCategory){
		this.articleCategoryID = articleCategoryID;
		this.articleCategory = articleCategory;
		
	}
	
	public String getArticleCategory() {
		return articleCategory;
	}

	public void setArticleCategory(String articleCategory) {
		this.articleCategory = articleCategory;
	}

	public int getArticleCategoryID() {
		return articleCategoryID;
	}

	public void setArticleCategoryID(int articleCategoryID) {
		this.articleCategoryID = articleCategoryID;
	}

	public ObservableList<ModelArticleCategory> getObsListArticleCategories() {
		return obsListArticleCategories;
	}

	public void setObsListArticleCategories(ObservableList<ModelArticleCategory> obsListArticleCategories) {
		this.obsListArticleCategories = obsListArticleCategories;
	}

	public ObservableList<String> getObsListArticleCategoriesComboBox() {
		return obsListArticleCategoriesComboBox;
	}

	public void setObsListArticleCategoriesComboBox(ObservableList<String> obsListArticleCategoriesComboBox) {
		this.obsListArticleCategoriesComboBox = obsListArticleCategoriesComboBox;
	}
	
	
	
	
}
