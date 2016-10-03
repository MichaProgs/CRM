package de.michaprogs.crm.article;

public class ModelArticleComparison {

	private int articleID;
	private int comparisonArticleID;
	
	/**
	 * Empty Constructor
	 */
	public ModelArticleComparison(){}
	
	/**
	 * Constructor for Database (Insert Comparison Article) <br>
	 * Constructor for Database (Update Comparison Article)
	 * @param articleID
	 * @param comparisonArticleID
	 */
	public ModelArticleComparison(	int articleID,
									int comparisonArticleID){
		this.articleID = articleID;
		this.comparisonArticleID = comparisonArticleID;
	}

	/*
	 * GETTER & SETTER
	 */
	public int getArticleID() {
		return articleID;
	}

	public void setArticleID(int articleID) {
		this.articleID = articleID;
	}

	public int getComparisonArticleID() {
		return comparisonArticleID;
	}

	public void setComparisonArticleID(int comparisonArticleID) {
		this.comparisonArticleID = comparisonArticleID;
	}
	
	
	
}
