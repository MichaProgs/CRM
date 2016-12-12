package de.michaprogs.crm.documents.deliverybill;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class InsertDeliverybill {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public InsertDeliverybill(ModelDeliverybill md){
		
		try{
			
			String stmt = "INSERT INTO DELIVERYBILL ("
					+ "deliverybillID,"
					+ "deliverybillDate,"
					+ "request,"
					+ "requestDate,"
					+ "notes,"
					+ "customerID,"
					+ "clerkID,"
					+ "amountOfPositions,"
					+ "total,"
					+ "deliverystate"
					+ ")"
					+ "VALUES(?,?,?,?,?,?,?,?,?,?)"; //10
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, md.getDeliverybillID());
			i++;
			ps.setString(i, md.getDeliverybillDate());
			i++;
			ps.setString(i, md.getRequest());
			i++;
			ps.setString(i, md.getRequestDate());
			i++;
			ps.setString(i, md.getNotes());
			i++;
			ps.setInt(i, md.getCustomerID());
			i++;
			ps.setInt(i, md.getClerkID());
			i++;
			ps.setInt(i, md.getAmountOfPositions());
			i++;
			ps.setBigDecimal(i, md.getTotal());
			i++;
			ps.setBoolean(i, md.getDeliverystate());
			i++;
			
			ps.execute();
			
			System.out.println("Lieferschein " + md.getDeliverybillID() + " wurde der Datenbank hinzugefügt!");
			
			/* ARTICLE */
			String stmtArticle = "INSERT INTO deliverybillarticle ("
													+ "deliverybillID,"
													+ "articleID,"
													+ "amount,"
													+ "ek,"
													+ "vk,"
													+ "total"
													+ ")"
													+ "VALUES(?,?,?,?,?,?)"; //6
			
			for(int index = 0; index < md.getObsListArticle().size(); index++){
				
				con = new DBConnect().getConnection();
				ps = con.prepareStatement(stmtArticle);
				int count = 1;
				ps.setInt(count, md.getDeliverybillID());
				count++;
				ps.setInt(count, md.getObsListArticle().get(index).getArticleID());
				count++;
				ps.setBigDecimal(count, md.getObsListArticle().get(index).getAmount());
				count++;
				ps.setBigDecimal(count, md.getObsListArticle().get(index).getEk());
				count++;
				ps.setBigDecimal(count, md.getObsListArticle().get(index).getVk());
				count++;
				ps.setBigDecimal(count, md.getObsListArticle().get(index).getTotal());
				count++;
				
				ps.execute();
				
				System.out.println("Lieferschein-Artikel " + md.getObsListArticle().get(index).getArticleID() + " " + md.getObsListArticle().get(index).getDescription1() + " wurde der Datenbank hinzugefügt!");
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(con != null)
					con.close();
				if(ps != null)
					ps.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
}
