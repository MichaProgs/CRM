package de.michaprogs.crm.paths;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PathProperties {

	Properties prop = new Properties();
	
	private OutputStream os;
	private InputStream is;
	
	public static final String FILENAME = "paths.properties";
	public static final String KEY_OFFER_TEMPLATE = "pathOfferTemplate";
	public static final String KEY_OFFER_SAVING = "pathOfferSaving";
	
	public void writeProperty(String propertyKey, String path){
		
		try{
			
			is = new FileInputStream(FILENAME);
			prop.load(is);
			
			os = new FileOutputStream(FILENAME);
			
			prop.setProperty(propertyKey, path);
			prop.store(os, null);
			
			System.out.println("Pfad " + path + " wurde als " + propertyKey + " in " + FILENAME + " gespeichert.");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(os != null)
					os.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	public String loadProperty(String propertyKey){
		
		try{
			
			is = new FileInputStream(FILENAME);
			
			prop.load(is);
			return prop.getProperty(propertyKey);
						
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "";
		
	}
	
}
