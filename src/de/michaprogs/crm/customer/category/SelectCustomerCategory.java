package de.michaprogs.crm.customer.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.michaprogs.crm.database.DBConnect;

public class SelectCustomerCategory {

	private ModelCustomerCategory mcc;
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SelectCustomerCategory(ModelCustomerCategory mcc){
		
		try{
			
			this.mcc = mcc;
			
			String stmt = "SELECT * FROM CUSTOMERCATEGORY";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				/* TABLES */
				mcc.getObsListCustomerCategories().add(new ModelCustomerCategory(
														rs.getInt("customerCategoryID"),
														rs.getString("customerCategory"))
														);
				
				/* COMBO BOXES */
				mcc.getObsListCustomerCategoriesComboBox().add(rs.getString("customercategory"));
				
			}
			
			System.out.println("Alle Kunden-Kategorien aus Datenbank geladen!");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(con != null)
					con.close();
				if(ps != null)
					ps.close();
				if(rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
	}
	
	public ModelCustomerCategory getModelCustomerCategory(){
		return mcc;
	}
	
}
