package de.michaprogs.crm.article.supplier;

public class UpdateArticleSupplier {

	public UpdateArticleSupplier(ModelArticleSupplier mas){
		
		try{
			
//			new DeleteArticleSupplier(mas.getArticleID());
			new InsertArticleSupplier(mas);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
