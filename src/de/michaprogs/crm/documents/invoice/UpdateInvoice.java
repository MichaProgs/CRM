package de.michaprogs.crm.documents.invoice;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.michaprogs.crm.database.DBConnect;

public class UpdateInvoice {

	/* DATABASE */
	private Connection con;
	private PreparedStatement ps;

	public UpdateInvoice(ModelInvoice model) {

		try {

			String stmt = "UPDATE INVOICE SET invoiceDate = ?," 
												+ "deliverybilLID = ?," 
												+ "deliveryDate = ?,"
												+ "notes = ?," 
												+ "customerID = ?," 
												+ "clerkID = ?, " 
												+ "amountOfPositions = ?," 
												+ "total = ? "

												+ "WHERE invoiceID = ?"; // ALWAYS LAST!

			con = new DBConnect().getConnection();
			ps = con.prepareStatement(stmt);
			int i = 1;
			ps.setString(i, model.getInvoiceDate());
			i++;
			ps.setInt(i, model.getDeliverybillID());
			i++;
			ps.setString(i, model.getDeliveryDate());
			i++;
			ps.setString(i, model.getNotes());
			i++;
			ps.setInt(i, model.getCustomerID());
			i++;
			ps.setInt(i, model.getClerkID());
			i++;
			ps.setInt(i, model.getAmountOfPositions());
			i++;
			ps.setBigDecimal(i, model.getTotal());
			i++;

			// ALWAYS LAST!
			ps.setInt(i, model.getDeliverybillID());
			i++;

			ps.execute();

			System.out.println("Änderungen an Rechnung " + model.getInvoiceID() + " wurden in Datenbank gespeichert!");

			/* ARTICLE */
			String stmtPositions  = "DELETE FROM INVOICEARTICLE WHERE invoiceID = ?";
			ps = con.prepareStatement(stmtPositions);
			ps.setInt(1, model.getInvoiceID());
			ps.execute();

			System.out.println("Rechnungs-Artikel zu Rechnung " + model.getInvoiceID() + " wurden aus Datenbank gelöscht!");

			String stmtDeliverybillArticle = "INSERT INTO INVOICEARTICLE (	invoiceID," 
																			+ "articleID,"
																			+ "amdunt," 
																			+ "ek," 
																			+ "vk," 
																			+ "total)" 
																			+ "VALUES(?,?,?,?,?,?)"; // 6

			for (int index = 0; index < model.getObsListPositions().size(); index++) {

				con = new DBConnect().getConnection();
				ps = con.prepareStatement(stmtDeliverybillArticle);
				int count = 1;
				ps.setInt(count, model.getInvoiceID());
				count++;
				ps.setInt(count, model.getObsListPositions().get(index).getArticleID());
				count++;
				ps.setBigDecimal(count, model.getObsListPositions().get(index).getAmount());
				count++;
				ps.setBigDecimal(count, model.getObsListPositions().get(index).getEk());
				count++;
				ps.setBigDecimal(count, model.getObsListPositions().get(index).getVk());
				count++;
				ps.setBigDecimal(count, model.getObsListPositions().get(index).getTotal());
				count++;

				ps.execute();

				System.out.println("Rechnungs-Artikel " 
									+ model.getObsListPositions().get(index).getArticleID() + " "
									+ model.getObsListPositions().get(index).getDescription1() 
									+ " wurde der Datenbank hinzugefügt!");

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
