package de.michaprogs.crm.offer;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import de.michaprogs.crm.article.ModelArticle;
import javafx.collections.ObservableList;

public class DocumentOffer {

	/* 
	 * DOCUMENT FIELDS 
	 */
	
	/* BILLING ADRESS */
	private final String SALUTATION = "SALUTATION";
	private final String CUSTOMERID = "CUSTOMERID";
	private final String NAME1 = "NAME1";
	private final String NAME2 = "NAME2";
	private final String STREET = "STREET";
	private final String LAND = "LAND";
	private final String ZIP = "ZIP";
	private final String LOCATION = "LOCATION";
	
	private final String PHONE = "PHONE";
	private final String FAX = "FAX";
	private final String EMAIL = "EMAIL";
	
	/* DELIVERYADRESS */
	private final String DELIVERYADRESS = "DELIVERYADRESS";
	private final String PAYMENTTIME = "PAYMENTTIME";
	private final String PAYMENTART = "PAYMENTART";
	
	/* OFFER-INFORMATION */
	private final String OFFERID = "OFFERID";
	private final String OFFERDATE = "OFFERDATE";
	private final String REQUESTART = "REQUESTART";
	private final String REQUESTDATE = "REQUESTDATE";	
	private final String CLERKNAME = "CLERKNAME";
	private final String CLERKPHONE = "CLERKPHONE";
	private final String CLERKFAX = "CLERKFAX";
	private final String CLERKEMAIL = "CLERKEMAIL";	
	
	/* ARTICLE */
//	private final String BARRELSIZE = "BARRELSIZE";
//	private final String BOLTING = "BOLTING";	
	private final String ARTICLEPOS = "ARTICLEPOS";
	private final String ARTICLEID = "ARTICLEID";
	private final String DESCRIPTION1 = "DESCRIPTION1";
	private final String DESCRIPTION2 = "DESCRIPTION2";
	private final String AMOUNT = "AMOUNT_";
	private final String PRICEUNIT = "PRICEUNIT";
	private final String AMOUNTUNIT = "AUNIT";
	private final String VKPRICE = "VKPRICE";
	private final String TOTALPRICE = "TOTALPRICE";
	
	private String outputFilepath;
	
	private XWPFDocument doc;
	
	/**
	 * Creates a offer docx
	 * 
	 * @param customerIDBilling
	 * @param salutationBilling
	 * @param name1Billing
	 * @param name2Billing
	 * @param streetBilling
	 * @param zipBilling
	 * @param locationBilling
	 * @param customerIDDelivery
	 * @param salutationDelivery
	 * @param name1Delivery
	 * @param name2Delivery
	 * @param streetDelivery
	 * @param zipDelivery
	 * @param locationDelivery
	 * @param offerID
	 * @param offerDate
	 * @param request
	 * @param requestDate
	 * @param creator
	 * @param amountOfPositions
	 * @param obsListArticle
	 * @param saveAsOffical - boolean if document should be a preview or a offical save
	 */
	public DocumentOffer(	int customerIDBilling,
							String salutationBilling,
							String name1Billing,
							String name2Billing,
							String streetBilling,
							String landBilling,
							int zipBilling,
							String locationBilling,
							String phoneBilling,
							String faxBilling,
							String emailBilling,
							int paymentNettoBilling,
							int paymentSkontoBilling,
							int skontoBilling,
							String paymentArtBilling,
							
							int customerIDDelivery,
							String salutationDelivery,
							String name1Delivery,
							String name2Delivery,
							String streetDelivery,
							String landDelivery,
							int zipDelivery,
							String locationDelivery,
							
							int offerID,
							String offerDate,
							String request,
							String requestDate,
							
							String clerk,
							String clerkphone,
							String clerkfax,
							String clerkemail,
							
							int amountOfPositions,
							ObservableList<ModelArticle> obsListArticle,
							boolean saveAsOffical){
		
		try{
			
			System.out.println(offerDate + " " + requestDate);
			
			/* Load Document with placeholder */
			doc = new XWPFDocument(new FileInputStream("files/docOffer.docx"));
			
			setBillingAdress(
				customerIDBilling,
				name1Billing, 
				name2Billing, 
				streetBilling, 
				landBilling,
				zipBilling, 
				locationBilling,
				phoneBilling,
				faxBilling,
				emailBilling
			);
			
			setDocumentText(
				salutationBilling,
				name1Billing,
				requestDate,
				clerk			 		
			);
			
			setTableDeliveryAndPayment(
				customerIDDelivery, 
				name1Delivery, 
				name2Delivery, 
				streetDelivery, 
				landDelivery,
				zipDelivery, 
				locationDelivery,
				paymentNettoBilling,
				paymentSkontoBilling,
				skontoBilling,
				paymentArtBilling
			);
					
			setTableInfo(	offerID,
							offerDate,
							request,
							requestDate,
							customerIDBilling,
							
							clerk,
							clerkphone,
							clerkfax,
							clerkemail);
			
			
			/* Copy the dummy-rows as much as positions are available */
			copyPositionRow(amountOfPositions - 1); // -1 because one row is already in the document
			
			/* Save after copy rows - Apache POI won't find the new copied row's */
			saveDocumentAsPreview();
			/* Load the saved Document again to fill in the positions */
			loadDoc2();
			
			for(int i = 0; i < amountOfPositions; i++){
				
				setPositions(
					obsListArticle.get(i).getArticleID(),
					obsListArticle.get(i).getDescription1(),
					obsListArticle.get(i).getDescription2(), 
					obsListArticle.get(i).getBarrelsize(),
					obsListArticle.get(i).getBolting(),
					obsListArticle.get(i).getAmount(),
					obsListArticle.get(i).getAmountUnit(), 
					obsListArticle.get(i).getPriceUnit(), 
					obsListArticle.get(i).getVk(),
					obsListArticle.get(i).getTotal(),
					i
				);
				
			}
			
			if(saveAsOffical){
				saveDocumentAsOffical(name1Billing, locationBilling);
				openDocument(outputFilepath);
//				convertToPdf();
			}else{
				saveDocumentAsPreview();
				openDocument(outputFilepath);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private void loadDoc2(){
		
		try{
			
			System.out.println("Try to Load Document");
		
//			doc = new XWPFDocument(new FileInputStream("C:\\Users\\Michi\\Desktop\\docOfferNew.docx"));
			doc = new XWPFDocument(new FileInputStream("files/docOfferPreview.docx"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Sets the Billingadress in the document with the given parameters <br>
	 * The Document-Placeholder will be replaced
	 * @param name1
	 * @param name2
	 * @param street
	 * @param zip
	 * @param location
	 */
	private void setBillingAdress(	int customerID,
									String name1,
									String name2,
									String street,
									String land,
									int zip,
									String location,
									
									String phone,
									String fax,
									String email
									){
		
		String sZip = String.valueOf(zip);
		if(zip == 0){
			sZip = "";
		}
		
		try{
			
			System.out.println("setAdress() Paragraphs: " + doc.getParagraphs());
			
			for(XWPFParagraph par : doc.getParagraphs()){
				
				System.out.println("setAdress() Runs: " + par.getRuns());
				
				for(XWPFRun run : par.getRuns()){
					String text = run.getText(0);
					
					if(text.contains(CUSTOMERID)){
						text = text.replace(CUSTOMERID, String.valueOf(customerID));
						run.setText(text, 0);
					}else if(text.contains(NAME1)){
						text = text.replace(NAME1, name1);
						run.setText(text, 0);
					}else if(text.contains(NAME2)){
						text = text.replace(NAME2, name2);
						run.setText(text, 0);
					}else if(text.contains(STREET)){
						text = text.replace(STREET, street);
						run.setText(text, 0);
					}else if(text.contains(LAND)){
						if(! land.equals("")){
							text = text.replace(LAND, land);
							run.setText(text + "-", 0);
						}
					}else if(text.contains(ZIP)){
						text = text.replace(ZIP, sZip);
						run.setText(text, 0);
					}else if(text.contains(LOCATION)){
						text = text.replace(LOCATION, location);
						run.setText(text, 0);
					}else if(text.contains(PHONE)){
						text = text.replace(PHONE, phone);
						run.setText(text, 0);
					}else if(text.contains(FAX)){
						text = text.replace(FAX, fax);
						run.setText(text, 0);
					}else if(text.contains(EMAIL)){
						text = text.replace(EMAIL, email);
						run.setText(text, 0);
					}
					
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Sets the deliveryadress in the document with the given parameters <br>
	 * The Document-Placeholder will be replaced <br>
	 * <br>
	 * <b>If no deliveryadress is available the deliveryadress is automaticalle set to "gleich wie Rechnung" </b>
	 * @param customerIDDevliery
	 * @param name1Delivery
	 * @param name2Delivery
	 * @param street
	 * @param zip
	 * @param location
	 */
	private void setTableDeliveryAndPayment(	int customerIDDevliery,
												String name1,
												String name2,
												String street,
												String land,
												int zip,
												String location,
												int paymentNetto,
												int paymentSkonto,
												int skonto,
												String paymentart){
		
		String deliveryAdress = "gleich wie Rechnung";
		
		if(	customerIDDevliery != 0||
			! name1.isEmpty() ||
			! name2.isEmpty() ||
			! street.isEmpty() ||
			  zip != 0 ||
			! location.isEmpty()){
			
			String sZip = String.valueOf(zip);
			if(zip == 0){
				sZip = "";
			}
			
			deliveryAdress = 	customerIDDevliery + ", " +
								name1+ ", " +
								name2+ ", " +
								street + ", " +
								land + "-" +
								sZip + " " +
								location;
			
		}
		
		try{
			
			System.out.println("Paragraphs: " + doc.getParagraphs());
			
			XWPFTable tableInfo = doc.getTables().get(2); // 2 = third table with deliveryadress and paymentinformations
			
			int rows = tableInfo.getRows().size();
			
			for(int row = 0; row < rows; row++){
			
				XWPFTableRow tableRow = tableInfo.getRow(row);
				
				int cells = tableRow.getTableCells().size();
				
				for(int cell = 0; cell < cells; cell++){
				
					XWPFTableCell tableCell = tableRow.getCell(cell);
					
					for(XWPFParagraph tablePar : tableCell.getParagraphs()){
						
						System.out.println("Run Table: " + tablePar.getRuns());
						
						for(XWPFRun run : tablePar.getRuns()){
							String text = run.getText(0);
							if(text.contains(DELIVERYADRESS)){
								text = text.replace(DELIVERYADRESS, deliveryAdress);
								run.setText(text, 0);
							}else if(text.contains(PAYMENTART)){
								text = text.replace(PAYMENTART, paymentart);
								run.setText(text, 0);
							}else if(text.contains(PAYMENTTIME)){
								if(skonto != 0 && paymentSkonto != 0){
									text = text.replace(PAYMENTTIME, "Innerhalb von " + paymentSkonto + " Tagen mit " + skonto + "% Skonto - " + paymentNetto + " Tage rein netto.");
								}else{
									text = text.replace(PAYMENTTIME, "Innerhalb von " + paymentNetto + " Tagen rein netto.");
								}
								run.setText(text, 0);
							}
						}
						
					}
					
				}
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/*
	 * The first table of the document inlcudes all kind of information like offerID, customerID or clerk
	 */
	private void setTableInfo(	int offerID,
								String offerDate,
								String request,
								String requestDate,
								int customerID,
								
								String clerk,
								String clerkphone,
								String clerkfax,
								String clerkemail
								){
		
		try{			
		
			XWPFTable tableInfo = doc.getTables().get(0); // 0 = first table with offer informations
			
			int rows = tableInfo.getNumberOfRows();
			
			for(int i = 0; i < rows; i++){
				
				XWPFTableRow tableRow = tableInfo.getRow(i);
				
				int cells = tableRow.getTableCells().size();
				
				for(int c = 0; c < cells; c++){
				
					XWPFTableCell tableCell = tableRow.getCell(c);
					
					for(XWPFParagraph tablePar : tableCell.getParagraphs()){
						
						for(XWPFRun run : tablePar.getRuns()){
							
							System.out.println("Runs TableInfo: " + tablePar.getRuns());
							
							String text = run.getText(0);
							
							if(text.contains(REQUESTART)){
								text = text.replace(REQUESTART, request);
								run.setText(text, 0);
							}else if(text.contains(REQUESTDATE)){
								text = text.replace(REQUESTDATE, requestDate);
								run.setText(text, 0);
							}else if(text.contains(OFFERID)){
								text = text.replace(OFFERID, String.valueOf(offerID));
								run.setText(text, 0);
							}else if(text.contains(OFFERDATE)){
								text = text.replace(OFFERDATE, offerDate);
								run.setText(text, 0);
							}else if(text.contains(CLERKNAME)){
								text = text.replace(CLERKNAME, clerk);
								run.setText(text, 0);
							}else if(text.contains(CLERKPHONE)){
								text = text.replace(CLERKPHONE, clerkphone);
								run.setText(text, 0);
							}else if(text.contains(CLERKFAX)){
								text = text.replace(CLERKFAX, clerkfax);
								run.setText(text, 0);
							}else if(text.contains(CLERKEMAIL)){
								text = text.replace(CLERKEMAIL, clerkemail);
								run.setText(text, 0);
							}else if(text.contains(CUSTOMERID)){
								text = text.replace(CUSTOMERID, String.valueOf(customerID));
								run.setText(text, 0);
							}
							
						}
						
					}
					
				}
				
			}

			
			
//			XWPFTableRow tableRow = tableInfo.getRow(0);	
//			
//			XWPFTableCell tableCell = tableRow.getCell(1); //REQUEST
//			
//			for(XWPFParagraph tablePar : tableCell.getParagraphs()){
//				
//				System.out.println("Run Table: " + tablePar.getRuns());
//				
//				for(XWPFRun run : tablePar.getRuns()){
//					String text = run.getText(0);
//					if(text.contains(REQUEST)){
//						text = text.replace(REQUEST, request);
//						run.setText(text, 0);
//					}else if(text.contains(REQUESTDATE)){
//						text = text.replace(REQUESTDATE, requestDate);
//						run.setText(text, 0);
//					}
//				}
//				
//			}			
//			
//			tableCell = tableRow.getCell(3); // DATE
//			
//			for(XWPFParagraph tablePar : tableCell.getParagraphs()){
//				
//				System.out.println("Run Table: " + tablePar.getRuns());
//				
//				for(XWPFRun run : tablePar.getRuns()){
//					String text = run.getText(0);
//					if(text.contains(OFFERDATE)){
//						text = text.replace(OFFERDATE, offerDate);
//						run.setText(text, 0);
//					}
//				}
//				
//			}
//			
//			tableRow = tableInfo.getRow(1);
//			
//			tableCell = tableRow.getCell(1); //CREATOR
//			
//			for(XWPFParagraph tablePar : tableCell.getParagraphs()){
//				
//				System.out.println("Run Table: " + tablePar.getRuns());
//				
//				for(XWPFRun run : tablePar.getRuns()){
//					String text = run.getText(0);
//					if(text.contains(CLERK)){
//						text = text.replace(CLERK, clerk);
//						run.setText(text, 0);
//					}
//				}
//				
//			}			
//			
//			tableCell = tableRow.getCell(3); //CUSTOMERID
//			
//			for(XWPFParagraph tablePar : tableCell.getParagraphs()){
//				
//				System.out.println("Run Table: " + tablePar.getRuns());
//				
//				for(XWPFRun run : tablePar.getRuns()){
//					String text = run.getText(0);
//					if(text.contains(CUSTOMERID)){
//						text = text.replace(CUSTOMERID, customerIDBilling);
//						run.setText(text, 0);
//					}
//				}
//				
//			}
//			
//			
//			
//			tableRow = tableInfo.getRow(2);
//			
//			tableCell = tableRow.getCell(1); // CLERK PHONE / FAX
//			
//			for(XWPFParagraph tablePar : tableCell.getParagraphs()){
//				
//				System.out.println("Run Table: " + tablePar.getRuns());
//				
//				for(XWPFRun run : tablePar.getRuns()){
//					String text = run.getText(0);
//					if(text.contains(CPHONE)){
//						text = text.replace(CPHONE, clerkphone);
//						run.setText(text, 0);
//					}else if(text.contains(CFAX)){
//						text = text.replace(CFAX, clerkfax);
//						run.setText(text, 0);
//					}
//				}
//				
//			}
//			
//			tableRow = tableInfo.getRow(3);	
//			
//			tableCell = tableRow.getCell(1); //CLERK EMAIL
//			
//			for(XWPFParagraph tablePar : tableCell.getParagraphs()){
//				
//				System.out.println("Run Table: " + tablePar.getRuns());
//				
//				for(XWPFRun run : tablePar.getRuns()){
//					String text = run.getText(0);
//					if(text.contains(CEMAIL)){
//						text = text.replace(CEMAIL, clerkemail);
//						run.setText(text, 0);
//					}
//				}
//				
//			}
//			
//			tableCell = tableRow.getCell(3); //OFFERID
//			
//			for(XWPFParagraph tablePar : tableCell.getParagraphs()){
//				
//				System.out.println("Run Table: " + tablePar.getRuns());
//				
//				for(XWPFRun run : tablePar.getRuns()){
//					String text = run.getText(0);
//					if(text.contains(OFFERID)){
//						text = text.replace(OFFERID, offerID);
//						run.setText(text, 0);
//					}
//				}
//				
//			}
//			
//			System.out.println("Paragraphs: " + doc.getParagraphs());
//			
//			for(XWPFParagraph par : doc.getParagraphs()){
//				
//				System.out.println("Runs: " + par.getRuns());
//				
//				for(XWPFRun run : par.getRuns()){
//					String text = run.getText(0);
//
//					if(text.contains(SALUTATION)){
//						
//						if(salutation.equals("")){
//							salutation = " Damen und Herren";
//						}else if(salutation.equals("Frau")){
//							salutation = " " + salutation + " " + name1;
//						}else if(salutation.equals("Herr")){
//							salutation = "r " + salutation + " " + name1;
//						}else if(salutation.equals("Firma")){
//							salutation = " Damen und Herren";
//						}
//						
//						text = text.replace(SALUTATION, salutation);
//						run.setText(text, 0);
//						
//					}else if(text.contains(REQUESTDATE)){
//						text = text.replace(REQUESTDATE, requestDate);
//						run.setText(text, 0);
//					}
//					
//				}
//			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Copies the position-row as much as needed. 
	 * @param amountOfPositions
	 */
	private void copyPositionRow(int amountOfPositions){
		
		XWPFTable tablePos = doc.getTables().get(1);
		
		//Copy position-row with placeholder in the document as much as needed
		for(int i = 0; i < amountOfPositions; i++){
			
			XWPFTableRow tableRow = tablePos.getRows().get(2); //First Row = Headline; Second Row = Placeholder
			tablePos.addRow(tableRow);
			
		}
		
	}
	
	/**
	 * Every Placeholder in every Tablerow in the Word-Document will be changed to the selected article
	 * @param articleID
	 * @param description1
	 * @param description2
	 * @param amount
	 * @param amountUnit
	 * @param priceUnit
	 * @param vk
	 * @param vkGes
	 * @param row
	 */
	private void setPositions(	int articleID,
								String description1,
								String description2,
								String barrelsize,
								String bolting,
								double amount,
								String amountUnit,
								int priceUnit,
								BigDecimal vk,
								BigDecimal vkGes,
								
								int row
								){
		
		try{
			
			XWPFTable tablePos = doc.getTables().get(1); // 1 = second table with article informations
			
			int columns = tablePos.getRows().get(0).getTableCells().size();
			
			for(int i = 0; i < columns; i++){ // i < number of article columns
				
				XWPFTableRow tableRow = tablePos.getRow(row + 2); // row + 2 because the first tow rows are headline and placeholder			
				XWPFTableCell tableCell = tableRow.getCell(i); 
				
				for(XWPFParagraph tablePar : tableCell.getParagraphs()){
					
					System.out.println("Run Table: " + tablePar.getRuns());
					
					for(XWPFRun run : tablePar.getRuns()){
						
						String text = run.getText(0);
						if(text.contains(ARTICLEPOS)){
							
							int pos = row + 1;
							
							if(row < 9){
								text = text.replace(ARTICLEPOS, String.valueOf("0" + pos));
								run.setText(text, 0);
							}else{
								text = text.replace(ARTICLEPOS, String.valueOf(pos));
								run.setText(text, 0);
							}
							
						}else if(text.contains(ARTICLEID)){
							text = text.replace(ARTICLEID, String.valueOf(articleID));
							run.setText(text, 0);
						}else if(text.contains(DESCRIPTION1)){
							text = text.replace(DESCRIPTION1, description1);
							run.setText(text, 0);
						}else if(text.contains(DESCRIPTION2)){
							
							text = text.replace(DESCRIPTION2, description2);
							run.setText(text, 0);
							
							if(! barrelsize.isEmpty()){
								run.addBreak();
								run.setText(barrelsize);
							}
							
							if(! bolting.isEmpty()){
								run.addBreak();
								run.setText(bolting);
							}
							
//						}else if(text.contains(BARRELSIZE)){
//							
//							text = text.replace(BARRELSIZE, barrelsize);
//							
//							if(barrelsize.equals("")){
//								run.setText(text, 0);
//							}else{								
//								run.setText(text, 0);
//							}
							
						}else if(text.contains(AMOUNT)){
							text = text.replace(AMOUNT, String.valueOf(amount));
							run.setText(text, 0);
						}else if(text.contains(PRICEUNIT)){
							if(priceUnit > 1){
								text = text.replace(PRICEUNIT, String.valueOf(priceUnit));
								run.setText(text, 0);
							}else{
								text = text.replace(PRICEUNIT, "");
								run.setText(text, 0);
							}
						}else if(text.contains(AMOUNTUNIT)){							
							text = text.replace(AMOUNTUNIT, amountUnit);
							run.setText(text, 0);							
						}else if(text.contains(VKPRICE)){
							text = text.replace(VKPRICE, String.valueOf(vk) + " €");
							run.setText(text, 0);
						}else if(text.contains(TOTALPRICE)){
							text = text.replace(TOTALPRICE, String.valueOf(vkGes) + " €");
							run.setText(text, 0);
						}
					}
				}
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void setDocumentText(	String salutation,
									String name1,
									String requestDate,
									String clerk){
		
		try{
			
			System.out.println("setDocumentText() Paragraphs: " + doc.getParagraphs());
			
			for(XWPFParagraph par : doc.getParagraphs()){
				
				System.out.println("setDocumentText() Runs: " + par.getRuns());
				
				for(XWPFRun run : par.getRuns()){
					String text = run.getText(0);
					
					if(text.contains(SALUTATION)){
						if(salutation.equals("Frau")){
							text = text.replace(SALUTATION, " " + salutation + " " + name1);
						}else if(salutation.equals("Herr")){
							text = text.replace(SALUTATION, "r " + salutation + " " + name1);
						}else if(salutation.equals("Firma")){
							text = text.replace(SALUTATION, " " + "Damen und Herren");
						}
						run.setText(text, 0);
					}else if(text.contains(CLERKNAME)){
						text = text.replace(CLERKNAME, clerk);
						run.setText(text, 0);
					}else if(text.contains(REQUESTDATE)){
						text = text.replace(REQUESTDATE, requestDate);
						run.setText(text, 0);
					}
					
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private void saveDocumentAsPreview(){
		
		try{
			
			outputFilepath = "files/docOfferPreview.docx";
			
			doc.write(new FileOutputStream(outputFilepath));
			doc.close();
			
			System.out.println("Angebots-Vorschau erfolgreich erstellt!");
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private void saveDocumentAsOffical(	String name1Billing,
										String locationBilling){
		
		try{
			
			outputFilepath = "files/" + name1Billing + ", " + locationBilling + ".docx"; 
			
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

