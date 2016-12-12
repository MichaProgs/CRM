package de.michaprogs.crm.documents.invoice;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class DeleteInvoice {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;
	
	public DeleteInvoice(ModelInvoice md){
		
		try{
			
			String stmt = "DELETE FROM INVOICE WHERE invoiceID = ?";
			
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, md.getInvoiceID());
			i++;
			
			ps.execute();
			
			System.out.println("Rechnung " + md.getInvoiceID() + " wurde aus Datenbank gelöscht!");
			
			/* ARTICLE */
			String stmtPositions = "DELETE FROM INVOICEARTICLE WHERE invoiceID = ?";
				
			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmtPositions);
			int count = 1;
			ps.setInt(count, md.getInvoiceID());
			count++;
			
			ps.execute();
			
			System.out.println("Rechnungs-Artikel von Rechnung " + md.getInvoiceID() + " wurden aus Datenbank gelöscht!");
		
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
