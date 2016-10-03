package de.michaprogs.crm.article;

public class ModelArticleAccessoris {

	private int articleID;
	private int AccessorisArticleID;
	
	/**
	 * Empty Constructor
	 */
	public ModelArticleAccessoris(){}
	
	/**
	 * Constructor for Database (Insert Accessoris Article) <br>
	 * Constructor for Database (Update Accessoris Article)
	 * @param articleID
	 * @param AccessorisArticleID
	 */
	public ModelArticleAccessoris(	int articleID,
									int AccessorisArticleID){
		this.articleID = articleID;
		this.AccessorisArticleID = AccessorisArticleID;
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

	public int getAccessorisArticleID() {
		return AccessorisArticleID;
	}

	public void setAccessorisArticleID(int AccessorisArticleID) {
		this.AccessorisArticleID = AccessorisArticleID;
	}
	
	
	
}
