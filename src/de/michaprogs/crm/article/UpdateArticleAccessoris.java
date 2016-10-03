package de.michaprogs.crm.article;

import javafx.collections.ObservableList;

public class UpdateArticleAccessoris {

	public UpdateArticleAccessoris(int articleID, ObservableList<ModelArticle> obsListArticleAccessoris){
		
		try{
			
			new DeleteArticleAccessoris(articleID, obsListArticleAccessoris);
			new InsertArticleAccessoris(articleID, obsListArticleAccessoris);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				
			}catch(Exception e){
				
			}
		}
		
	}
	
}
