package de.michaprogs.crm.article;

import javafx.collections.ObservableList;

public class UpdateArticleComparison {

	public UpdateArticleComparison(int articleID, ObservableList<ModelArticle> obsListArticleComparison){
		
		try{
			
			new DeleteArticleComparison(articleID, obsListArticleComparison);
			new InsertArticleComparison(articleID, obsListArticleComparison);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				
			}catch(Exception e){
				
			}
		}
		
	}
	
}
