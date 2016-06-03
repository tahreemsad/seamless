package com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadWSProperties {
	Properties properties = new Properties();
	InputStream fileInput = null;
	private String config ;
	
	public ReadWSProperties(){
		try {
			String customer = System.getProperty("customer");
			System.out.println("Customer: "+customer);
			fileInput = new FileInputStream("src/test/java/com/seamless/ers/qa/cucumber/Properties/Webservices/ws_"+customer+".properties");
			System.out.println("Load Properties :"+"src/test/java/com/seamless/ers/qa/cucumber/Properties/Webservices/ws_"+customer+".properties");
			properties.load(fileInput);
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally{
			if(fileInput != null){
				try {
					fileInput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String returnPropertyForWS(String config) throws IOException {
		config = properties.getProperty(config);
		return config;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}
}
