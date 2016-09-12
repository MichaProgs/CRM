package de.michaprogs.crm.article.supplier;

import javafx.collections.ObservableList;

public class UpdateArticleSupplier {

	public UpdateArticleSupplier(int articleID, ObservableList<ModelArticleSupplier> obsListArticleSupplier){
		
		try{
			
			new DeleteArticleSupplier(articleID);			
			new InsertArticleSupplier(articleID, obsListArticleSupplier);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
