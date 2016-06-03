package com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadWebVariablesFromProperties {

	Properties properties = new Properties();
	InputStream fileInput = null;
	private String webvariables ;
	
	public ReadWebVariablesFromProperties(){
		try {
			String customer = System.getProperty("customer");
			System.out.println("Customer: "+customer);
			fileInput = new FileInputStream("src/test/java/com/seamless/ers/qa/cucumber/Properties/WebVariables/webvariables_"+customer+".properties");
			System.out.println("Load Properties :"+"src/test/java/com/seamless/ers/qa/cucumber/Properties/WebVariables/webvariables_"+customer+".properties");
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
	
	public String readPropertiesForWebVariables(String webvariable) throws IOException {
		webvariables = properties.getProperty(webvariable);
		return webvariables;
	}
	
	public String[] readPropertiesForWebVariablesToSplit(String webvariable) throws IOException {
		webvariables = properties.getProperty(webvariable);
	    String[] list = webvariables.split(",");
	    return list;
	}

	public String getXpath() {
		return webvariables;
	}

	public void setXpath(String webvariables) {
		this.webvariables = webvariables;
	}
}