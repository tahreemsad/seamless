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

public class USSDAppP2P {
	
	ReadConfigFromProperties readConfig = new ReadConfigFromProperties();
	ReadUssdAppConfigFromProperties readUssd = ReadUssdAppConfigFromProperties.getInstance();
	private static Logger log = LogManager.getLogger( USSDAppR2R.class );
	SQLResultSets resultSet = new SQLResultSets();
	CucumberCommon common = new CucumberCommon();
	USSDAppSubscriberRegistration userReg = new USSDAppSubscriberRegistration();
	USSDAppChangePin changePin = new USSDAppChangePin();
	private String countryCodeNetworkCodeFromProp;

	/**********************************
	 * String Based R2R
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws JSchException 
	 **********************************/

	@When("^I Perform P2P to the subscriber number (.+) from subscriber number (.+) with an amount (.+) and pin is (.+) and (.+) in message is returned after execution having shortcode (.+) and option was (.+)$")
	public void PerformP2PWithValidData(String toSubscriber, String fromSubscriber, String amount, String pin, String message, String shortcode, String menuOption) throws IOException, SQLException, JSchException {
        
				log.info("=======================>>>>>>>>>>>>Perform String Base P2P<<<<<<<<<<<=====================");
				
	 			countryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE"); 	

				String toSubscriberfromprop = readUssd.returnValueForUssd(toSubscriber);
				toSubscriberfromprop = countryCodeNetworkCodeFromProp+toSubscriberfromprop;
				String fromSubscriberfromprop = readUssd.returnValueForUssd(fromSubscriber);
				fromSubscriberfromprop = countryCodeNetworkCodeFromProp+fromSubscriberfromprop;
	
				String amountfromprop = readUssd.returnValueForUssd(amount);
				String pinfromprop = readUssd.returnValueForUssd(pin);
				String messagefromprop = readUssd.returnValueForUssd(message);
				String shortcodefromprop = readUssd.returnValueForUssd(shortcode);
				String menuOptionfromprop = readUssd.returnValueForUssd(menuOption);
				
				log.info("toSubscriber:" +toSubscriberfromprop);
				log.info("fromSubscriber:" +fromSubscriberfromprop);
				log.info("amount:" +amountfromprop);
				log.info("pin:" +pinfromprop);
				log.info("message:" +messagefromprop);
				log.info("shortcode:" +shortcodefromprop);
				log.info("menuOption:" +menuOptionfromprop);

				String fullCurl = common.returnFullCurl(fromSubscriberfromprop, shortcodefromprop); 
	        	String curl = fullCurl+"&p1="+menuOptionfromprop+"&p2="+toSubscriberfromprop+"&p3="+amountfromprop+"&p4="+pinfromprop;
	        	log.info("P2P.java: "+curl+'"');
	        	        	            
	            /*****************Create SSH Connection and execute the command****************/
	            log.info("===========>>>>Create SSH Connection");
	            common.createSSHCoonection();
	            ((ChannelExec) common.channel).setCommand(curl + "\" \n");
	            common.createChannel();
	            
	            /*****************Transaction/Amount Checking****************/
	            boolean state = common.Result.contains(messagefromprop);
	            log.info(common.Result.contains(messagefromprop));
	            log.info(messagefromprop+"..."+state);
	            log.info("\n=====================Transaction Status===========================\n");
	            try{
            	Assert.assertTrue(state);
	            if(state == true){
	            	log.error("\nTransaction is Successfull ");
	            }	            
	            else{
	            	log.error("\nTransaction Not Successfull ");
	            	return;
			}
		}
            finally{
			log.info("\n" + common.Result);
        common.closeSSHConnection();
		}
	}
}
