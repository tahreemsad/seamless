package com.seamless.ers.qa.cucumber.ImplementationClasses.USSD;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.seamless.ers.qa.cucumber.ImplementationClasses.Common.CucumberCommon;
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadConfigFromProperties;
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadUssdAppConfigFromProperties;
import com.seamless.ers.qa.cucumber.SQLQueries.SQLResultSets;

import cucumber.api.java.en.When;

public class USSDAppResellerAccountManagement{   
	          
	ReadConfigFromProperties readConfig = new ReadConfigFromProperties();
	ReadUssdAppConfigFromProperties readUssd = ReadUssdAppConfigFromProperties.getInstance();
	private String countryCodeNetworkCodeFromProp;
	public String curl;
	
	public static Logger log = LogManager.getLogger( USSDAppResellerAccountManagement.class );     
	SQLResultSets resultSet = new SQLResultSets();
	CucumberCommon common = new CucumberCommon();
	
	/***************************USSD***************************/
	
	/**********************************
	 * String Based R2R
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws JSchException 
	 **********************************/
	
	@When("^User performs Create Reseller from reseller id (.+) having (.+) to child (.+) with valid data with (.+) and first name (.+) and last name (.+) and (.+) in message is returned after execution having shortcode (.+) and pin (.+)$")
	public void PerformCreateReseller(String parent_id, String parent_MSISDN, String child_type, String reseller_type, String fname, String lname, String message, String shortcode, String pin)  throws IOException, SQLException, JSchException {
	    
		log.info("=======================>>>>>>>>>>>>Perform Create Reseller<<<<<<<<<<<=====================");
		
		countryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");
		
		String parent_id_from_prop = readUssd.returnValueForUssd(parent_id);
		String parent_MSISDN_from_prop = readUssd.returnValueForUssd(parent_MSISDN);
		parent_MSISDN_from_prop = countryCodeNetworkCodeFromProp+parent_MSISDN_from_prop;
		String child_type_from_prop = readUssd.returnValueForUssd(child_type);
		String random_child_MSISDN = common.createNewReseller();
		String reseller_type_from_prop =readUssd.returnValueForUssd(reseller_type);
		String fname_from_prop =readUssd.returnValueForUssd(fname);
		String lname_from_prop =readUssd.returnValueForUssd(lname);
		String message_from_prop =readUssd.returnValueForUssd(message);
		String shortcode_from_prop =readUssd.returnValueForUssd(shortcode);
		String pin_from_prop =readUssd.returnValueForUssd(pin);
		
		log.info("parent_id:" +parent_id_from_prop);
		log.info("parent_MSISDN:" +parent_MSISDN_from_prop);
		log.info("random_child_MSISDN:" +random_child_MSISDN);
		log.info("child_type:" +child_type_from_prop);
		log.info("fname:" +fname_from_prop);
		log.info("lname:" +lname_from_prop);
		log.info("message:" +message_from_prop);
		log.info("shortcode:" +shortcode_from_prop);
		log.info("pin:" +pin_from_prop);
		
		/***************** Prepare Customer specific curl command ****************/
		String fullCurl = common.returnFullCurl(parent_MSISDN_from_prop, shortcode_from_prop); 
		
		if (System.getProperty("customer").equals("ng_glo"))
		{
			log.info("I am verifying Create Reseller for "+System.getProperty("customer"));
			curl = fullCurl+"&p1=6"+"&p2=1"+"&p3="+random_child_MSISDN+"&p4="+pin_from_prop+"&p5="+reseller_type_from_prop + "&p6="+fname_from_prop + "&p7="+lname_from_prop;
			log.info("Create Reseller: " + curl + '"');
		}
		else {
			curl = fullCurl+"&p1=6"+"&p2=1"+"&p3="+random_child_MSISDN+"&p4="+pin_from_prop+"&p5="+reseller_type_from_prop + "&p6="+fname_from_prop + "&p7="+lname_from_prop;
			log.info("Create Reseller: " + curl + '"');			
		}
		
		/*****************Create SSH Connection and execute the command****************/
        log.info("===========>>>>Create SSH Connection");
        common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(curl + "\" \n");
        common.createChannel();
        
        boolean state = common.Result.contains(message_from_prop);
        log.info(message_from_prop+"..."+state);
        
        try{    
	    	Assert.assertTrue(state);
        	if(state == true){
	        	log.info(parent_id_from_prop +" successfully created a child reseller having reseller type as "+child_type_from_prop+ " having MSISDN : "+random_child_MSISDN);
	        	log.info("Notification message found in the logs");	        	            	
	        }
	        else{
	        	log.error("Notification message not found in the logs");
	        	log.error("\nTransaction Not Successfull ");
	        	return;
			}
    	}finally{
		log.info("\n"+ common.Result);
		common.closeSSHConnection();
		
		}       
	}	
}