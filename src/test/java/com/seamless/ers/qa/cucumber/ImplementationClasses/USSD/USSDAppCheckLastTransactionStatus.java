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

import cucumber.api.java.en.And;

public class USSDAppCheckLastTransactionStatus {
	ReadUssdAppConfigFromProperties readUssd = ReadUssdAppConfigFromProperties.getInstance();
	private static Logger log = LogManager.getLogger( USSDAppCheckLastTransactionStatus.class );
    ReadConfigFromProperties readConfig = new ReadConfigFromProperties();

	SQLResultSets resultSet = new SQLResultSets();
	CucumberCommon common = new CucumberCommon();
	USSDAppR2R r2r = new USSDAppR2R();
	public String getERSreference;
	public String ers_reference;
	public String curl;
	public String countryCodeNetworkCodeFromProp;
	
	/**********************************
	 * String Based R2R
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws JSchException 
	 **********************************/
	

	/**********************************
	 * @throws IOException 
	 * @throws JSchException 
	 * @throws SQLException 
	 **********************************/
	
	
	@And("^I check the last transaction for passed status for the reseller (.+) having shortCode (.+) and menuOption (.+) then I see the USSD response which was (.+) ersreference (.+)$")
	public void performTransactionForPassedStatus(String resellerMSISDN, String shortCode, String menuOption, String messagePart1, String messagePart2) throws IOException, JSchException, SQLException
	{
		getERSreference = resultSet.getErsReference("0,1");
		
		log.info("=======================>>>>>>>>>>>>Perform Last Transaction for Passed<<<<<<<<<<<=====================");
		countryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");

		String resellerMSISDN_fromprop = readUssd.returnValueForUssd(resellerMSISDN);
		resellerMSISDN_fromprop = countryCodeNetworkCodeFromProp+resellerMSISDN_fromprop;
		String shortCodefromprop = readUssd.returnValueForUssd(shortCode);
		String menuOption_fromprop = readUssd.returnValueForUssd(menuOption);
		String messagePart1_fromprop = readUssd.returnValueForUssd(messagePart1);
		String messagePart2_fromprop = readUssd.returnValueForUssd(messagePart2);
		
		log.info("resellerMSISDN:" +resellerMSISDN_fromprop);
		log.info("shortcode:" +shortCodefromprop);
		log.info("menuoption:" +menuOption_fromprop);
		log.info("messagePart1:" +messagePart1_fromprop);
		log.info("ERS-Reference: "+getERSreference);
		log.info("messagePart2:" +messagePart2_fromprop);
		
		String fullCurl = common.returnFullCurl(resellerMSISDN_fromprop, shortCodefromprop);
		String ers_reference;
		
		if(System.getProperty("customer").equals("zm_mtn")){
	    	ers_reference = resultSet.getErsReference("0,1"); 
	    	log.info("ERS-REference:"+ ers_reference);
	    	
	    	curl = fullCurl+"&p1="+menuOption_fromprop+"&p2=2&p3="+ers_reference;
	    	log.info("Perform Last Transaction: "+curl+'"');			
		}else 
			if(System.getProperty("customer").equals("ers_std")){
	    	ers_reference = resultSet.getErsReference("0,1"); 
	    	log.info("ERS-REference:"+ ers_reference);
	    	
	    	curl = fullCurl+"&p1="+menuOption_fromprop+"&p2="+ers_reference;
	    	log.info("Perform Last Transaction: "+curl+'"');	
		}else
		if(System.getProperty("customer").equals("ci_mtn")){
	    	ers_reference = resultSet.getErsReference("0,1"); 
	    	log.info("ERS-REference:"+ ers_reference);
	    	
	    	curl = fullCurl+"&p1="+menuOption_fromprop+"&p2=3&p3=6&p4="+ers_reference;
	    	log.info("Perform Last Transaction: "+curl+'"');
		}else 
			if(System.getProperty("customer").equals("gb_mtn")){
	    	ers_reference = resultSet.getErsReference("0,1"); 
	    	log.info("ERS-REference:"+ ers_reference);
	    	
	    	curl = fullCurl+"&p1="+menuOption_fromprop+"&p2="+ers_reference;
	    	log.info("Perform Last Transaction: "+curl+'"');			
		}
		
        /*****************Create SSH Connection and execute the command****************/
        log.info("===========>>>>Create SSH Connection");
        common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(curl + "\" \n");
        common.createChannel();
        
	        boolean state = common.Result.contains(messagePart1_fromprop+" "+getERSreference+" "+messagePart2_fromprop);
	        log.info(messagePart1_fromprop+" "+getERSreference+" "+messagePart2_fromprop);
	        Assert.assertTrue(state);
	        if(state == true){
	        	log.info("USSD response verified");
	        }else{
	        	log.error("Error while matching the USSD response");
	        }
        log.info("\n" + common.Result);
        common.closeSSHConnection();
	}
	
	@And("^I check the last transaction for failed status for the reseller (.+) having shortCode (.+) and menuOption (.+) then I see the USSD response which was (.+) ersreference (.+)$")
	public void performTransactionForFailedStatus(String resellerMSISDN, String shortCode, String menuOption, String messagePart1, String messagePart2) throws IOException, JSchException, SQLException
	{
		log.info("=======================>>>>>>>>>>>>Perform Last Transaction For Failed<<<<<<<<<<<=====================");
		
		countryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");

		String resellerMSISDN_fromprop = readUssd.returnValueForUssd(resellerMSISDN);
		resellerMSISDN_fromprop = countryCodeNetworkCodeFromProp+resellerMSISDN_fromprop;
		String shortCodefromprop = readUssd.returnValueForUssd(shortCode);
		String menuOption_fromprop = readUssd.returnValueForUssd(menuOption);
		String messagePart1_fromprop = readUssd.returnValueForUssd(messagePart1);
		String messagePart2_fromprop = readUssd.returnValueForUssd(messagePart2);
		
		
		log.info("resellerMSISDN:" +resellerMSISDN_fromprop);
		log.info("shortcode:" +shortCodefromprop);
		log.info("menuoption:" +menuOption_fromprop);
		log.info("messagePart1:" +messagePart1_fromprop);
		log.info("messagePart2:" +messagePart2_fromprop);
		log.info("CountryCodeNetworkCodeFromProp:" +countryCodeNetworkCodeFromProp);

		String fullCurl = common.returnFullCurl(resellerMSISDN_fromprop, shortCodefromprop);
		if(System.getProperty("customer").equals("zm_mtn")){
	    	ers_reference = resultSet.getErsReference("0,1"); 
	    	log.info("ERS-REference:"+ ers_reference);
	    	
	    	curl = fullCurl+"&p1="+menuOption_fromprop+"&p2=2&p3="+ers_reference;
	    	log.info("Perform Last Transaction: "+curl+'"');			
		}else
			if (System.getProperty("customer").equals("ers_std")){
	    	ers_reference = resultSet.getErsReference("0,1"); 
	    	log.info("ERS-REference:"+ ers_reference);
	    	
	    	curl = fullCurl+"&p1="+menuOption_fromprop+"&p2="+ers_reference;
	    	log.info("Perform Last Transaction: "+curl+'"');			
		}else
    		if(System.getProperty("customer").equals("ci_mtn")){
    	    	ers_reference = resultSet.getErsReference("0,1"); 
    	    	log.info("ERS-REference:"+ ers_reference);
    	    	
    	    	curl = fullCurl+"&p1="+menuOption_fromprop+"&p2=3&p3=6&p4="+ers_reference;
    	    	log.info("Perform Last Transaction: "+curl+'"');
		}else
			if (System.getProperty("customer").equals("gb_mtn")){
		    	ers_reference = resultSet.getErsReference("0,1"); 
		    	log.info("ERS-REference:"+ ers_reference);
		    	
		    	curl = fullCurl+"&p1="+menuOption_fromprop+"&p2="+ers_reference;
		    	log.info("Perform Last Transaction: "+curl+'"');			
			}	
		
        /*****************Create SSH Connection and execute the command****************/
        log.info("===========>>>>Create SSH Connection");
        common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(curl + "\" \n");
        common.createChannel();
        boolean state = common.Result.contains(ers_reference+" "+messagePart2_fromprop);
        log.info("common.Result: "+common.Result);
        log.info("Custom Message: "+messagePart1_fromprop+" "+ers_reference+" "+messagePart2_fromprop);
			if (state == true) {
				log.info("USSD response verified");
			} else {
				log.error("Error while matching the USSD response");
			}
        log.info("\n" + common.Result);
        common.closeSSHConnection();
	}
	
}
