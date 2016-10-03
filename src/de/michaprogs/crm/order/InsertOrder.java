package de.michaprogs.crm.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

import de.michaprogs.crm.components.Notification;
import de.michaprogs.crm.database.DBConnect;
import tray.notification.NotificationType;

public class InsertOrder {

	private boolean wasSuccessful = false;
	
	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public InsertOrder(ModelOrder mo){
		
		try{
			
			String stmt = "INSERT INTO supplierOrder ("
												+ "ORDERID,"
												+ "ORDERDATE,"
												+ "REQUEST,"
												+ "REQUESTDATE,"
												+ "NOTES,"
												+ "SUPPLIERID,"
												+ "CLERKID)"
												+ "VALUES("
												+ "?,?,?,?,?,?,?" //7
												+ ")";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, mo.getOrderID());
			i++;
			ps.setString(i, mo.getOrderDate());
			i++;
			ps.setString(i, mo.getRequest());
			i++;
			ps.setString(i, mo.getRequestDate());
			i++;
			ps.setString(i, mo.getNotes());
			i++;
			ps.setInt(i, mo.getSupplierID());
			i++;
			ps.setInt(i, mo.getClerkID());
			i++;
			
			ps.execute();
			
			/* ORDER ARTICLES */
			String stmtOfferArticle = "INSERT INTO orderarticle ("
					+ "orderID,"
					+ "articleID,"
					+ "amount,"
					+ "ek,"
					+ "vk,"
					+ "total"
					+ ")"
					+ "VALUES(?,?,?,?,?,?)"; //6

			for(int index = 0; index < mo.getObsListArticle().size(); index++){
				
				con = new DBConnect().getConnection();
				ps = con.prepareStatement(stmtOfferArticle);
				int count = 1;
				ps.setInt(count, mo.getOrderID());
				count++;
				ps.setInt(count, mo.getObsListArticle().get(index).getArticleID());
				count++;
				ps.setBigDecimal(count, mo.getObsListArticle().get(index).getAmount());
				count++;
				ps.setBigDecimal(count, mo.getObsListArticle().get(index).getEk());
				count++;
				ps.setBigDecimal(count, mo.getObsListArticle().get(index).getVk());
				count++;
				ps.setBigDecimal(count, mo.getObsListArticle().get(index).getTotal());
				count++;
				
				ps.execute();
				
			}
			
			System.out.println("Alle Bestellartikel zu Bestellung-Nr. " + mo.getOrderID() + " wurde zur Datenbank hinzugefügt!");
			
			wasSuccessful = true;
			
			new Notification(	"Bestellung wurde gespeichert!", 
								mo.getOrderID() + " TODO", //TODO 
								NotificationType.SUCCESS);
			
			System.out.println("Bestellung " + mo.getOrderID() + " wurd der Datenbank hinzugefügt!");
			
		}catch(SQLIntegrityConstraintViolationException e){

			new Notification(	"Speichern nicht möglich!", 
								"Bestellnummer bereits vergeben!", 
								NotificationType.ERROR);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean wasSuccessful(){
		return wasSuccessful;
	}
	
}
