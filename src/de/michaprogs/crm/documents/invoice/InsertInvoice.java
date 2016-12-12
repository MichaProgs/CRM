package de.michaprogs.crm.documents.invoice;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;
import de.michaprogs.crm.documents.deliverybill.ModelDeliverybill;
import de.michaprogs.crm.documents.deliverybill.UpdateDeliverybillState;

public class InsertInvoice {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;

	public InsertInvoice(ModelInvoice md) {

		try {

			String stmt = "INSERT INTO INVOICE (invoiceID, "
												+ "invoiceDate,"
												+ "deliverybillID,"
												+ "deliveryDate,"
												+ "notes,"
												+ "customerID,"
												+ "clerkID,"
												+ "amountOfPositions,"
												+ "total) "
												+ "VALUES (?,?,?,?,?,?,?,?,?)"; //9

			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setInt(i, md.getInvoiceID());
			i++;
			ps.setString(i, md.getInvoiceDate());
			i++;
			ps.setInt(i, md.getDeliverybillID());
			i++;
			ps.setString(i, md.getDeliveryDate());
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

			ps.execute();

			System.out.println("Rechnung " + md.getInvoiceID() + " wurde der Datenbank hinzugefügt!");

			/* ARTICLE */
			String stmtArticle = "INSERT INTO INVOICEARTICLE (	invoiceID," 
																+ "articleID," 
																+ "amount,"
																+ "ek," 
																+ "vk," 
																+ "total)" 
																+ "VALUES(?,?,?,?,?,?)"; // 6

			for (int index = 0; index < md.getObsListPositions().size(); index++) {

				con = new DBConnect().getConnection();
				ps = con.prepareStatement(stmtArticle);
				int count = 1;
				ps.setInt(count, md.getInvoiceID());
				count++;
				ps.setInt(count, md.getObsListPositions().get(index).getArticleID());
				count++;
				ps.setBigDecimal(count, md.getObsListPositions().get(index).getAmount());
				count++;
				ps.setBigDecimal(count, md.getObsListPositions().get(index).getEk());
				count++;
				ps.setBigDecimal(count, md.getObsListPositions().get(index).getVk());
				count++;
				ps.setBigDecimal(count, md.getObsListPositions().get(index).getTotal());
				count++;

				ps.execute();

				System.out.println("Rechnungs-Artikel " + md.getObsListPositions().get(index).getArticleID() + " "
						+ md.getObsListPositions().get(index).getDescription1() + " wurde der Datenbank hinzugefügt!");

			}
			
			/* UPDATE DELIVERYBILL STATE */
			if(md.getDeliverybillID() != 0){
				new UpdateDeliverybillState(new ModelDeliverybill(md.getDeliverybillID(), true));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
