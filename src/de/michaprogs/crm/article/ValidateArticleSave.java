package de.michaprogs.crm.article;

public class ValidateArticleSave {

	private boolean isValid = false;
	
	public ValidateArticleSave(	int articleID,
								String description1){
		
		if(	articleID != 0 &&
			! description1.isEmpty()){
				isValid = true;
			}else if(articleID == 0){
				System.out.println("Bitte gültige 'Artikelnummer' wählen!");
				isValid = false;
			}else if(description1.isEmpty()){
				System.out.println("Bitte gültige 'Bezeichnung1' wählen!");
				isValid = false;
			}else{
				System.err.println("***ModelArticle.java -> validate: Unbekannter Fehler!");
				isValid = false;
			}
		
		}
	
	public boolean isValid(){
		return isValid;
	}
	
}
