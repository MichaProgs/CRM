package de.michaprogs.crm.article;

import java.math.BigDecimal;

public class ModelArticle {

	/* First Block (Main-Data) */
	private int articleID;
	private String description1;
	private String description2;
	private String category;
	private int eanID;
	
	/* Second Block (Weight & Size) */
	private String barrelsize;
	private String bolting;
	private int lenght;
	private int width;
	private int height;
	private double weight;
	private float desity;
	
	/* Third Block (Price) */
	private BigDecimal ek;
	private BigDecimal vk;
	private int priceUnit;
	private String amountUnit;
	private int tax;
	
	/* Notes */
	private String notes;
	
	
	
	
}
