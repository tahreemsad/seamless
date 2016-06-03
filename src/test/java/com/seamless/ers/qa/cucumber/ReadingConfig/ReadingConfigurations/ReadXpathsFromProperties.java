package com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadXpathsFromProperties {
	Properties properties = new Properties();
	InputStream fileInputxptah = null;
	private String xpaths ;
	
	public ReadXpathsFromProperties(){
		try {
			String customer = System.getProperty("customer");
			System.out.println("Customer: "+customer);
			fileInputxptah = new FileInputStream("src/test/java/com/seamless/ers/qa/cucumber/Properties/Xpath/xpaths_"+customer+".properties");
			System.out.println("Load Properties :"+"src/test/java/com/seamless/ers/qa/cucumber/Properties/Xpath/xpaths_"+customer+".properties");
			properties.load(fileInputxptah);
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally{
			if(fileInputxptah != null){
				try {
					fileInputxptah.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String readPropertiesForXpaths(String xpath) throws IOException {
		xpaths = properties.getProperty(xpath);
		return xpaths;
	}
	
	public String[] readPropertiesForXpathsToSplit(String xpath) throws IOException {
	    xpaths = properties.getProperty(xpath);
	    String[] list = xpaths.split(",");
	    return list;
	}

	public String getXpath() {
		return xpaths;
	}

	public void setXpath(String xpaths) {
		this.xpaths = xpaths;
	}

}
