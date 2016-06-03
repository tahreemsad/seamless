package com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ReadDBconfigFromProperties {

	Properties properties = new Properties();
	InputStream fileInput = null;
	private String config ;
	
	public ReadDBconfigFromProperties(){
		try {
			fileInput = new FileInputStream("src/test/java/com/seamless/ers/qa/cucumber/Properties/Database/DBConnection.properties");
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
	
	public String readPropertiesforDataBase(String config) throws IOException {
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
