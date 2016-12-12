package de.michaprogs.crm.documents.offer;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import de.michaprogs.crm.ErrorAlert;
import de.michaprogs.crm.clerk.ModelClerk;
import de.michaprogs.crm.customer.ModelCustomer;
import de.michaprogs.crm.paths.PathProperties;

public class DocumentOffer {

	/* 
	 * DOCUMENT FIELDS 
	 */
	
	/* BILLING ADRESS */
	private final String GREETING_BILLING = "GREETING_BILLING";
	private final String SALUTATION_BILLING = "SALUTATION_BILLING";
	private final String CUSTOMERID_BILLING = "CUSTOMERID_BILLING";
	private final String NAME1_BILLING = "NAME1_BILLING";
	private final String NAME2_BILLING = "NAME2_BILLING";
	private final String STREET_BILLING = "STREET_BILLING";
	private final String LAND_BILLING = "LAND_BILLING";
	private final String ZIP_BILLING = "ZIP_BILLING";
	private final String LOCATION_BILLING = "LOCATION_BILLING";
	
	private final String PHONE_BILLING = "PHONE_BILLING";
	private final String MOBILE_BILLING = "MOBILE_BILLING";
	private final String FAX_BILLING = "FAX_BILLING";
	private final String EMAIL_BILLING = "EMAIL_BILLING";
	
	private final String PAYMENTART_BILLING = "PAYMENTART_BILLING";
	private final String IBAN_BILLING = "IBAN_BILLING";
	private final String BIC_BILLING = "BIC_BILLING";
	private final String BANK_BILLING = "BANK_BILLING";
	private final String PAYMENTTIME_SKONTO_BILLING = "PAYMENTTIME_SKONTO_BILLING";
	private final String PAYMENTTIME_NETTO_BILLING = "PAYMENTTIME_NETTO_BILLING";
	private final String SKONTO_BILLING = "PAYMENTTIME_SKONTO_BILLING";
	private final String PAYMENTTIME_SKONTO_NETTO_BILLING = "PAYMENTTIME_SKONTO_NETTO_BILLING";
	
	/* DELIVERYADRESS */
	private final String GREETING_DELIVERY = "GREETING_DELIVERY";
	private final String SALUTATION_DELIVERY = "SALUTATION_DELIVERY";
	private final String CUSTOMERID_DELIVERY = "CUSTOMERID_DELIVERY";
	private final String NAME1_DELIVERY = "NAME1_DELIVERY";
	private final String NAME2_DELIVERY = "NAME2_DELIVERY";
	private final String STREET_DELIVERY = "STREET_DELIVERY";
	private final String LAND_DELIVERY = "LAND_DELIVERY";
	private final String ZIP_DELIVERY = "ZIP_DELIVERY";
	private final String LOCATION_DELIVERY = "LOCATION_DELIVERY";
	
	private final String PHONE_DELIVERY = "PHONE_DELIVERY";
	private final String MOBILE_DELIVERY= "MOBILE_DELIVERY";
	private final String FAX_DELIVERY = "FAX_DELIVERY";
	private final String EMAIL_DELIVERY = "EMAIL_DELIVERY";
	
	private final String PAYMENTART_DELIVERY = "PAYMENTART_DELIVERY";
	private final String IBAN_DELIVERY = "IBAN_DELIVERY";
	private final String BIC_DELIVERY = "BIC_DELIVERY";
	private final String BANK_DELIVERY = "BANK_DELIVERY";
	private final String PAYMENTTIME_SKONTO_DELIVERY = "PAYMENTTIME_SKONTO_DELIVERY";
	private final String PAYMENTTIME_NETTO_DELIVERY = "PAYMENTTIME_NETTO_DELIVERY";
	private final String SKONTO_DELIVERY = "PAYMENTTIME_SKONTO_DELIVERY";	
	private final String PAYMENTTIME_SKONTO_NETTO_DELIVERY = "PAYMENTTIME_SKONTO_NETTO_DELIVERY";
	
	/* OFFER-INFORMATION */
	private final String OFFERID = "OFFERID";
	private final String OFFERDATE = "OFFERDATE";
	private final String REQUESTART = "REQUESTART";
	private final String REQUESTDATE = "REQUESTDATE";
	private final String NOTES = "NOTES";
	
	/* CLERK */
	private final String CLERKNAME = "CLERKNAME";
	private final String CLERKPHONE = "CLERKPHONE";
	private final String CLERKFAX = "CLERKFAX";
	private final String CLERKEMAIL = "CLERKEMAIL";	
	
	/* ARTICLE */
	private final String BARRELSIZE = "BARRELSIZE";
	private final String BOLTING = "BOLTING";	
	private final String ARTICLEPOS = "ARTICLEPOS";
	private final String ARTICLEID = "ARTICLEID";
	private final String DESCRIPTION1 = "DESCRIPTION1";
	private final String DESCRIPTION2 = "DESCRIPTION2";
	private final String LONGTEXT = "LONGTEXT";
	private final String AMOUNT = "AMOUNT_";
	private final String PRICEUNIT = "PRICEUNIT";
	private final String AMOUNTUNIT = "AUNIT";
	private final String VKPRICE = "VKPRICE";
	private final String TOTALPRICE = "TOTALPRICE";
	
	private String inputFilepath;
	private String outputFilepath;
	
	private XWPFDocument doc;
	
	/**
	 * Created a offer *.docx
	 * @param modelCustomerBilling
	 * @param modelCustomerDelivery
	 * @param modelOffer
	 * @param modelClerk
	 */
	public DocumentOffer(	ModelCustomer modelCustomerBilling,
							ModelCustomer modelCustomerDelivery,
							ModelOffer modelOffer,
							ModelClerk modelClerk
							){
		
		if(	new PathProperties().loadProperty(PathProperties.KEY_OFFER_TEMPLATE).equals("")){
			new ErrorAlert("Es wurde keine *.docx-Datei als Vorlage angegeben.");
			System.out.println("Keine *.docx Datei vorhanden!");
			return;
		}else{
			inputFilepath = new PathProperties().loadProperty(PathProperties.KEY_OFFER_TEMPLATE);
		}
		
		if(new PathProperties().loadProperty(PathProperties.KEY_OFFER_SAVING).equals("")){
			new ErrorAlert("Es ist kein gültiger Speicherort angegeben.");
			System.out.println("Es ist kein gültiger Speicherort angegeben.");
			return;
		}else{
			outputFilepath = new PathProperties().loadProperty(PathProperties.KEY_OFFER_SAVING);
		}
				
		//IF THERE IS NO BILLINGADRESS THE DELIVERYADRESS IS USED FOR BOTH
		if(modelCustomerDelivery.getBillingID() == 0){
			modelCustomerBilling = modelCustomerDelivery;
		}
		
		try{
			
			/* Load Document with placeholder */
//			doc = new XWPFDocument(new FileInputStream("files/docOffer.docx"));
			doc = new XWPFDocument(new FileInputStream(inputFilepath));
			
			/* SET DOCUMENT DATA */
			for(XWPFParagraph p : doc.getParagraphs()){
				
				System.out.println("Document Runs: " + p.getRuns());
				
				for(XWPFRun r : p.getRuns()){
					checkFields(r, modelCustomerBilling, modelCustomerDelivery, modelOffer, modelClerk, 0);					
				}
				
			}
			
			/* COPY ROWS OF ARTICLE/POSITION TABLE
			   IMPORTANT: THE ARTICLE/POSITION TABLE MUST HAVE A HEADLINE-ROW WITH MINIMAL ONE OF THE FOLLOWING DESCRIPTION:
			   - Pos.
			   - Art-Nr.
			   - Bezeichnung
			   - Menge
			   - Einheit / ME / PE
			   - VK / E-Preis / Preis
			   - VK-Gesamt / G-Preis / Total
			   
			   THE PROGRAM WILL DETECT THE ARTICLE-TABLE THROUGH THESE DESCRIPTION AND COPY THE ROWS IN AMOUNT OF THE ARTICLES/POSITIONS
			   
			   AFTER THE ROWS WERE COPIED THE DOCUMENT MUST BE SAVED - OTHERWISE THE COPIED ROWS ARE NOT FOUND
			 */
			int numberOfTables = doc.getTables().size();
			int iTableWithArticles = 0;
			
			// DETECT THE TABLE WITH ARTICLES
			for(int tables = 0; tables < numberOfTables; tables++){
				
				if(	doc.getTables().get(tables).getRows().get(0).getTableCells().get(0).getText().equals("Pos.") ||
					doc.getTables().get(tables).getRows().get(0).getTableCells().get(0).getText().equals("ART-NR.") ||
					doc.getTables().get(tables).getRows().get(0).getTableCells().get(0).getText().equals("BEZEICHNUNG") ||
					doc.getTables().get(tables).getRows().get(0).getTableCells().get(0).getText().equals("MENGE") ||
					doc.getTables().get(tables).getRows().get(0).getTableCells().get(0).getText().equals("EINHEIT") ||
					doc.getTables().get(tables).getRows().get(0).getTableCells().get(0).getText().equals("ME") ||
					doc.getTables().get(tables).getRows().get(0).getTableCells().get(0).getText().equals("PE") ||
					doc.getTables().get(tables).getRows().get(0).getTableCells().get(0).getText().equals("VK") ||
					doc.getTables().get(tables).getRows().get(0).getTableCells().get(0).getText().equals("E-PREIS") ||
					doc.getTables().get(tables).getRows().get(0).getTableCells().get(0).getText().equals("PREIS") ||
					doc.getTables().get(tables).getRows().get(0).getTableCells().get(0).getText().equals("VK-GES") ||
					doc.getTables().get(tables).getRows().get(0).getTableCells().get(0).getText().equals("G-PREIS") ||
					doc.getTables().get(tables).getRows().get(0).getTableCells().get(0).getText().equals("TOTAL")){
					iTableWithArticles = tables;
				}
				
			}
			
			// COPY THE ROW WITH THE PLACEHOLDER AS MUCH AS NEEDED
			XWPFTable tableWithArticles = doc.getTables().get(iTableWithArticles);
			
			for(int i = 1; i < modelOffer.getObsListArticle().size(); i++){ // i = 1 BECAUSE ONE ROW EXISTS ALREADY IN THE DOCUMENT
				
				XWPFTableRow articleRow = tableWithArticles.getRows().get(2); 	// 0 = FIRST ROW - FIRST ROW IS THE HEADLINE 
																				// 1 = SECOND ROW - SECOND ROW IS EMPTY
																				// 2 = THIRD ROW - THIRD ROW CONTAINS THE PLACEHOLDER 
				tableWithArticles.addRow(articleRow);
				
			}	
			
			//HERE MUST BE SAVED OTHERWISE APACHE POI CAN'T FIND THE COPIED ROWS
//			saveDocumentAsPreview();
			doc.write(new FileOutputStream("files/docOfferPreview.docx"));
			doc.close();
			//RELOAD THE CURRENTLY SAVED DOCUMENT
			doc = new XWPFDocument(new FileInputStream("files/docOfferPreview.docx"));
			
			/* TABLES */			
			for(int table = 0; table < numberOfTables; table++){
				
				XWPFTable t = doc.getTables().get(table);
				int numberOfRows = t.getRows().size();
				
				for(int row = 0; row < numberOfRows; row++){
					
					XWPFTableRow tr = t.getRow(row);
					int numberOfCells = tr.getTableCells().size();
					
					for(int cell = 0; cell < numberOfCells; cell++){
						
						XWPFTableCell tc = tr.getCell(cell);
						
						for(XWPFParagraph tableP : tc.getParagraphs()){
							
							System.out.println("Table Runs: " + tableP.getRuns());
							
							for(XWPFRun tableR : tableP.getRuns()){
								checkFields(tableR, modelCustomerBilling, modelCustomerDelivery, modelOffer, modelClerk, row - 2); // ROW - 2 BECAUSE THE FRIST ROW IS THE HEADLINE SECOND ROW IS EMPTY																
							}							
						}						
					}					
				}				
			}
			
			/* SAVE AND OPEN */
			saveDocumentAsOffical(modelCustomerBilling.getName1(), modelCustomerBilling.getLocation());
			openDocument(outputFilepath);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private void checkFields(	XWPFRun r,
								ModelCustomer modelCustomerBilling,
								ModelCustomer modelCustomerDelivery,
								ModelOffer modelOffer,
								ModelClerk modelClerk,
								int indexOfArticle){
		
		String text = r.getText(0);
		
		/* BILLING */
		if(text.contains(GREETING_BILLING)){
			if(modelCustomerBilling.getSalutation().equals("Herr")){
				r.setText(text.replace(GREETING_BILLING, "r " + modelCustomerBilling.getSalutation()), 0);
			}else if(modelCustomerBilling.getSalutation().equals("Frau")){
				r.setText(text.replace(GREETING_BILLING, " " + modelCustomerBilling.getSalutation()), 0);
			}else{
				r.setText(text.replace(GREETING_BILLING, " Damen und Herren"), 0);
			}
		}else if(text.contains(SALUTATION_BILLING)){
			r.setText(text.replace(SALUTATION_BILLING, modelCustomerBilling.getSalutation()), 0);
		}else if(text.contains(CUSTOMERID_BILLING)){
			r.setText(text.replace(CUSTOMERID_BILLING, String.valueOf(modelCustomerBilling.getCustomerID())), 0);
		}else if(text.contains(NAME1_BILLING)){
			r.setText(text.replace(NAME1_BILLING, modelCustomerBilling.getName1()), 0);
		}else if(text.contains(NAME2_BILLING)){
			r.setText(text.replace(NAME2_BILLING, modelCustomerBilling.getName2()), 0);
		}else if(text.contains(STREET_BILLING)){
			r.setText(text.replace(STREET_BILLING, modelCustomerBilling.getStreet()), 0);
		}else if(text.contains(LAND_BILLING)){
			r.setText(text.replace(LAND_BILLING, modelCustomerBilling.getLand()), 0);
		}else if(text.contains(ZIP_BILLING)){
			r.setText(text.replace(ZIP_BILLING, String.valueOf(modelCustomerBilling.getZip())), 0);
		}else if(text.contains(LOCATION_BILLING)){
			r.setText(text.replace(LOCATION_BILLING, modelCustomerBilling.getLocation()), 0);
		}else if(text.contains(PHONE_BILLING)){
			r.setText(text.replace(PHONE_BILLING, modelCustomerBilling.getPhone()), 0);
		}else if(text.contains(MOBILE_BILLING)){
			r.setText(text.replace(MOBILE_BILLING, modelCustomerBilling.getMobile()), 0);
		}else if(text.contains(FAX_BILLING)){
			r.setText(text.replace(FAX_BILLING, modelCustomerBilling.getFax()), 0);
		}else if(text.contains(EMAIL_BILLING)){
			r.setText(text.replace(EMAIL_BILLING, modelCustomerBilling.getEmail()), 0);
		}else if(text.contains(PAYMENTART_BILLING)){
			r.setText(text.replace(PAYMENTART_BILLING, modelCustomerBilling.getPayment()), 0);
		}else if(text.contains(PAYMENTTIME_NETTO_BILLING)){
			r.setText(text.replace(PAYMENTTIME_NETTO_BILLING, String.valueOf(modelCustomerBilling.getPaymentNetto())), 0);
		}else if(text.contains(PAYMENTTIME_SKONTO_BILLING)){
			r.setText(text.replace(PAYMENTTIME_SKONTO_BILLING, String.valueOf(modelCustomerBilling.getPaymentSkonto())), 0);
		}else if(text.contains(SKONTO_BILLING)){
			r.setText(text.replace(SKONTO_BILLING, String.valueOf(modelCustomerBilling.getSkonto())), 0);
		}else if(text.contains(PAYMENTTIME_SKONTO_NETTO_BILLING)){
			if(modelCustomerBilling.getPaymentSkonto() != 0){
				r.setText(text.replace(PAYMENTTIME_SKONTO_NETTO_BILLING, modelCustomerBilling.getPaymentSkonto() + " Tage " + modelCustomerBilling.getSkonto() + "% Skonto. " + modelCustomerBilling.getPaymentNetto() + " Tage rein netto."), 0);
			}else{
				r.setText(text.replace(PAYMENTTIME_SKONTO_NETTO_BILLING, modelCustomerBilling.getPaymentNetto() + " Tage rein netto."), 0);
			}	
		}else if(text.contains(IBAN_BILLING)){
			r.setText(text.replace(IBAN_BILLING, modelCustomerBilling.getIBAN()), 0);
		}else if(text.contains(BIC_BILLING)){
			r.setText(text.replace(BIC_BILLING, modelCustomerBilling.getBIC()), 0);
		}else if(text.contains(BANK_BILLING)){
			r.setText(text.replace(BANK_BILLING, modelCustomerBilling.getBank()), 0);
		}
		
		/* DELIVERY */
		else if(text.contains(GREETING_DELIVERY)){
			if(modelCustomerDelivery.getSalutation().equals("Herr")){
				r.setText(text.replace(GREETING_DELIVERY, "r " + modelCustomerDelivery.getSalutation()), 0);
			}else if(modelCustomerDelivery.getSalutation().equals("Frau")){
				r.setText(text.replace(GREETING_DELIVERY, " " + modelCustomerDelivery.getSalutation()), 0);
			}else{
				r.setText(text.replace(GREETING_DELIVERY, " Damen und Herren"), 0);
			}
		}else if(text.contains(SALUTATION_DELIVERY)){
			r.setText(text.replace(SALUTATION_DELIVERY, modelCustomerDelivery.getSalutation()), 0);
		}else if(text.contains(CUSTOMERID_DELIVERY)){
			r.setText(text.replace(CUSTOMERID_DELIVERY, String.valueOf(modelCustomerDelivery.getCustomerID())), 0);
		}else if(text.contains(NAME1_DELIVERY)){
			r.setText(text.replace(NAME1_DELIVERY, modelCustomerDelivery.getName1()), 0);
		}else if(text.contains(NAME2_DELIVERY)){
			r.setText(text.replace(NAME2_DELIVERY, modelCustomerDelivery.getName2()), 0);
		}else if(text.contains(STREET_DELIVERY)){
			r.setText(text.replace(STREET_DELIVERY, modelCustomerDelivery.getStreet()), 0);
		}else if(text.contains(LAND_DELIVERY)){
			r.setText(text.replace(LAND_DELIVERY, modelCustomerDelivery.getLand()), 0);
		}else if(text.contains(ZIP_DELIVERY)){
			r.setText(text.replace(ZIP_DELIVERY, String.valueOf(modelCustomerDelivery.getZip())), 0);
		}else if(text.contains(LOCATION_DELIVERY)){
			r.setText(text.replace(LOCATION_DELIVERY, modelCustomerDelivery.getLocation()), 0);
		}else if(text.contains(PHONE_DELIVERY)){
			r.setText(text.replace(PHONE_DELIVERY, modelCustomerDelivery.getPhone()), 0);
		}else if(text.contains(MOBILE_DELIVERY)){
			r.setText(text.replace(MOBILE_DELIVERY, modelCustomerDelivery.getMobile()), 0);
		}else if(text.contains(FAX_DELIVERY)){
			r.setText(text.replace(FAX_DELIVERY, modelCustomerDelivery.getFax()), 0);
		}else if(text.contains(EMAIL_DELIVERY)){
			r.setText(text.replace(EMAIL_DELIVERY, modelCustomerDelivery.getEmail()), 0);
		}else if(text.contains(PAYMENTART_DELIVERY)){
			r.setText(text.replace(PAYMENTART_DELIVERY, modelCustomerDelivery.getPayment()), 0);
		}else if(text.contains(PAYMENTTIME_NETTO_DELIVERY)){
			r.setText(text.replace(PAYMENTTIME_NETTO_DELIVERY, String.valueOf(modelCustomerDelivery.getPaymentNetto())), 0);
		}else if(text.contains(PAYMENTTIME_SKONTO_DELIVERY)){
			r.setText(text.replace(PAYMENTTIME_SKONTO_DELIVERY, String.valueOf(modelCustomerDelivery.getPaymentSkonto())), 0);
		}else if(text.contains(SKONTO_DELIVERY)){
			r.setText(text.replace(SKONTO_DELIVERY, String.valueOf(modelCustomerDelivery.getSkonto())), 0);
		}else if(text.contains(PAYMENTTIME_SKONTO_NETTO_DELIVERY)){
			if(modelCustomerDelivery.getPaymentSkonto() != 0){
				r.setText(text.replace(PAYMENTTIME_SKONTO_NETTO_DELIVERY, modelCustomerDelivery.getPaymentSkonto() + " Tage " + modelCustomerDelivery.getSkonto() + "% Skonto. " + modelCustomerDelivery.getPaymentNetto() + " Tage rein netto."), 0);
			}else{
				r.setText(text.replace(PAYMENTTIME_SKONTO_NETTO_DELIVERY, modelCustomerDelivery.getPaymentNetto() + " Tage rein netto."), 0);
			}	
		}else if(text.contains(IBAN_DELIVERY)){
			r.setText(text.replace(IBAN_DELIVERY, modelCustomerDelivery.getIBAN()), 0);
		}else if(text.contains(BIC_DELIVERY)){
			r.setText(text.replace(BIC_DELIVERY, modelCustomerDelivery.getBIC()), 0);
		}else if(text.contains(BANK_DELIVERY)){
			r.setText(text.replace(BANK_DELIVERY, modelCustomerDelivery.getBank()), 0);
		}
		
		/* OFFER DATA */
		else if(text.contains(OFFERID)){
			r.setText(text.replace(OFFERID, String.valueOf(modelOffer.getOfferID())), 0);
		}else if(text.contains(OFFERDATE)){
			r.setText(text.replace(OFFERDATE, modelOffer.getOfferDate()), 0);
		}else if(text.contains(REQUESTART)){
			r.setText(text.replace(REQUESTART, modelOffer.getRequest()), 0);
		}else if(text.contains(REQUESTDATE)){
			r.setText(text.replace(REQUESTDATE, modelOffer.getRequestDate()), 0);
		}else if(text.contains(NOTES)){
			r.setText(text.replace(NOTES, modelOffer.getNotes()), 0);
		}
		
		/* CLERK */
		else if(text.contains(CLERKNAME)){
			r.setText(text.replace(CLERKNAME, modelClerk.getName()), 0);
		}else if(text.contains(CLERKPHONE)){
			r.setText(text.replace(CLERKPHONE, modelClerk.getPhone()), 0);
		}else if(text.contains(CLERKFAX)){
			r.setText(text.replace(CLERKFAX, modelClerk.getFax()), 0);
		}else if(text.contains(CLERKEMAIL)){
			r.setText(text.replace(CLERKEMAIL, modelClerk.getEmail()), 0);
		}
		
		/* ARTICLE */
		else if(text.contains(ARTICLEPOS)){
			if(indexOfArticle + 1 >= 10){
				r.setText(text.replace(ARTICLEPOS, String.valueOf(indexOfArticle + 1)), 0);
			}else{
				r.setText(text.replace(ARTICLEPOS, 0 + String.valueOf(indexOfArticle + 1)), 0);
			}
		}else if(text.contains(ARTICLEID)){
			r.setText(text.replace(ARTICLEID, String.valueOf(modelOffer.getObsListArticle().get(indexOfArticle).getArticleID())), 0);
		}else if(text.contains(DESCRIPTION1)){
			r.setText(text.replace(DESCRIPTION1, modelOffer.getObsListArticle().get(indexOfArticle).getDescription1()), 0);
		}else if(text.contains(DESCRIPTION2)){
			r.setText(text.replace(DESCRIPTION2, modelOffer.getObsListArticle().get(indexOfArticle).getDescription2()), 0);
		}else if(text.contains(LONGTEXT)){
			r.setText(text.replace(LONGTEXT, modelOffer.getObsListArticle().get(indexOfArticle).getLongtext()), 0);
		}else if(text.contains(BARRELSIZE)){
			r.setText(text.replace(BARRELSIZE, modelOffer.getObsListArticle().get(indexOfArticle).getBarrelsize()), 0);
		}else if(text.contains(BOLTING)){
			r.setText(text.replace(BOLTING, modelOffer.getObsListArticle().get(indexOfArticle).getBolting()), 0);
		}else if(text.contains(AMOUNT)){
			r.setText(text.replace(AMOUNT, String.valueOf(modelOffer.getObsListArticle().get(indexOfArticle).getAmount())), 0);
		}else if(text.contains(AMOUNTUNIT)){
			r.setText(text.replace(AMOUNTUNIT, modelOffer.getObsListArticle().get(indexOfArticle).getAmountUnit()), 0);
		}else if(text.contains(PRICEUNIT)){
			if(modelOffer.getObsListArticle().get(indexOfArticle).getPriceUnit() == 1){
				r.setText(text.replace(PRICEUNIT, ""), 0);
			}else{
				r.setText(text.replace(PRICEUNIT, String.valueOf(modelOffer.getObsListArticle().get(indexOfArticle).getPriceUnit())), 0);
			}
		}else if(text.contains(VKPRICE)){
			r.setText(text.replace(VKPRICE, String.valueOf(modelOffer.getObsListArticle().get(indexOfArticle).getVk() + " €")), 0);
		}else if(text.contains(TOTALPRICE)){
			r.setText(text.replace(TOTALPRICE, String.valueOf(modelOffer.getObsListArticle().get(indexOfArticle).getTotal() + " €")), 0);
		}
		
	}
	
	private void saveDocumentAsPreview(){
		
		try{
			
//			outputFilepath = "files/docOfferPreview.docx";
			
			doc.write(new FileOutputStream(outputFilepath));
			doc.close();
			
			System.out.println("Angebots-Vorschau erfolgreich gespeichert!");
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private void saveDocumentAsOffical(	String name1Billing,
										String locationBilling){
		
		try{
			
//			outputFilepath = "files/" + name1Billing + ", " + locationBilling + ".docx"; 
			outputFilepath = outputFilepath.concat("\\" + name1Billing + " " + locationBilling + ".docx");
			System.out.println(outputFilepath);
			
			doc.write(new FileOutputStream(outputFilepath));
			doc.close();
			
			System.out.println("Angebot erfolgreich erstellt!");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private void openDocument(String filepath){
		
		try{
			if(Desktop.isDesktopSupported()){
				Desktop.getDesktop().open(new File(filepath));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
//	private void convertToPdf(){
//		
//		try{
//			
//			InputStream is = new FileInputStream(new File("files/Mustermann, Musterstadt.docx"));
//			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(is);
//			
//			PdfSettings pdfSettings = new PdfSettings();
//			
//			OutputStream out = new FileOutputStream(new File("files/pdf_test.pdf"));
//			
//			PdfConversion converter = new Conversion(wordMLPackage);
//			converter.output(out, pdfSettings);
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//	}
	
}

