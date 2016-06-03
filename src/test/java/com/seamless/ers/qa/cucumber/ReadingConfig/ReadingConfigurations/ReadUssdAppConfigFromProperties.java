package com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadUssdAppConfigFromProperties {
	
	private static Properties properties = new Properties();
	private static Logger log = LogManager.getLogger( ReadUssdAppConfigFromProperties.class );
	private static InputStream fileInput = null;
	private static String variables, customer, config;
	private static ReadUssdAppConfigFromProperties instance;
	
	/*
	 * A private Constructor prevents any other class from instantiating.
	 */
	private ReadUssdAppConfigFromProperties() {
		customer = System.getProperty("customer");
		log.info("Loaded USSDApp Config: "+"src/test/java/com/seamless/ers/qa/cucumber/Properties/USSD/ussdapp_"+ customer + ".properties");
	}

	/* Static 'instance' method */
	public static ReadUssdAppConfigFromProperties getInstance() {
		if(instance == null)
			instance = new ReadUssdAppConfigFromProperties();
		return instance;
	}

	public static void returnProperties() {
		try {
			fileInput = new FileInputStream("src/test/java/com/seamless/ers/qa/cucumber/Properties/USSD/ussdapp_"+ customer + ".properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			properties.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String[] readPropertiesForMenuVariablesToSplit(String variable) throws IOException {
		returnProperties();
		variables = properties.getProperty(variable);
	    String[] list = variables.split(",");
	    return list;
	}
	
	public String returnValueForUssd(String config) throws IOException {
		returnProperties();
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