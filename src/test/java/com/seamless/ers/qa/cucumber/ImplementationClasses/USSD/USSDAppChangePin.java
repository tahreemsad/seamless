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

public class USSDAppChangePin {
	
	ReadConfigFromProperties readConfig = new ReadConfigFromProperties();
	ReadUssdAppConfigFromProperties readUssd = ReadUssdAppConfigFromProperties.getInstance();
	private static Logger log = LogManager.getLogger( USSDAppR2R.class );
	SQLResultSets resultSet = new SQLResultSets();
	CucumberCommon common = new CucumberCommon();
	public String curl;
	public String countryCodeNetworkCodeFromProp;
	public String subscribermsisdn;
	
	/**********************************
	 * String Based R2R
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws JSchException 
	 **********************************/
	
	@When("^I change the password by hitting the curl command with short code (.+) for the (.+) with MSISDN (.+) and optionMenu is (.+) and currentpassword is (.+) and new password is (.+) and confirm password is (.+) then i recieved the response as (.+)$")
	public void pinChange(String shortcode, String usertype ,String msisdn, String optionMenu, String currentpassword, String newpassword, String confirmpassword, String message) throws JSchException, IOException{
		log.info("=======================>>>>>>>>>>>>Perform password change for "+usertype+"<<<<<<<<<<<=====================");
		String CountryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");
		String shortcodeFromProp = readUssd.returnValueForUssd(shortcode);
		String usertypeFromProp = readUssd.returnValueForUssd(usertype);
		String subscribermsisdnfromprop = readUssd.returnValueForUssd(msisdn);
		String subscribermsisdnfromprop1 = CountryCodeNetworkCodeFromProp+subscribermsisdnfromprop;
		String optionMenufromprop = readUssd.returnValueForUssd(optionMenu);
		String currentpasswordfromprop = readUssd.returnValueForUssd(currentpassword);
		String newpasswordfromprop = readUssd.returnValueForUssd(newpassword);
		String confirmpasswordfromprop = readUssd.returnValueForUssd(confirmpassword);
		String messagefromprop = readUssd.returnValueForUssd(message);
		
		log.info("shortcode: "+shortcodeFromProp);
		log.info("usertype: "+usertypeFromProp);
		log.info("msisdn: "+subscribermsisdnfromprop);
		log.info("optionMenu: "+optionMenufromprop);
		log.info("currentpassword: "+currentpasswordfromprop);
		log.info("newpassword: "+newpasswordfromprop);
		log.info("confirmpassword: "+confirmpasswordfromprop);
		log.info("message: "+messagefromprop);
		
		
    	String fullCurl = common.returnFullCurl(subscribermsisdnfromprop1, shortcodeFromProp); 
    	
		curl = fullCurl+"&p1="+optionMenufromprop+"&p2="+currentpasswordfromprop+"&p3="+newpasswordfromprop+"&p4="+confirmpasswordfromprop;
    	 
    	log.info("ChanePin.java: "+curl+'"');
        
        /*****************Create SSH Connection and execute the command****************/
        log.info("===========>>>>Create SSH Connection");
        common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(curl + "\" \n");
        common.createChannel();
        
        boolean state = common.Result.contains(messagefromprop);
        log.info("common.Result: "+common.Result);
        log.info(common.Result.contains(messagefromprop));
        log.info(messagefromprop+"..."+state);
        log.info("\n=====================Transaction Status===========================\n");
        Assert.assertTrue(state);
        if(state == true){
        	log.info("USSD Response verified: "+messagefromprop);
        }
        else{
        	log.error("USSD reponse is not verified: \n Actual: "+common.Result +"\n Expected: "+messagefromprop);
		}
		log.info("\n" + common.Result);
		common.closeSSHConnection();
	}
	
	
	@When("^Subscriber change the password by hitting the curl command with short code (.+) and optionMenu is (.+) and currentpassword is (.+) and new password is (.+) and confirm password is (.+) then i recieved the response as (.+)$")
	public void changePinForSubscriber(String shortcode ,String msisdn, String optionMenu, String currentpassword, String newpassword, String confirmpassword, String message) throws JSchException, IOException{
		log.info("=======================>>>>>>>>>>>>Perform password change for Subscriber<<<<<<<<<<<=====================");
		String CountryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");
		String SubscriberMenuSortCodeFromProp = readUssd.returnValueForUssd(shortcode);
		String SenderSubscriberMsisdn = msisdn;
		String subscribermsisdnfromprop1 = CountryCodeNetworkCodeFromProp+SenderSubscriberMsisdn;
		String SenderSubscriberMenuOptionFromProp = readUssd.returnValueForUssd(optionMenu);
		String SenderSubscriberAlreadyActivatedPin = readUssd.returnValueForUssd(currentpassword);
		String SenderSubscriberNewPin = readUssd.returnValueForUssd(confirmpassword);
		String SenderSubscriberconfirmNewPin = readUssd.returnValueForUssd(newpassword);
		String messagefromProp = readUssd.returnValueForUssd(message);
		
		log.info("shortcode: "+SubscriberMenuSortCodeFromProp);
		log.info("msisdn: "+SenderSubscriberMsisdn);
		log.info("optionMenu: "+SenderSubscriberMenuOptionFromProp);
		log.info("currentpassword: "+SenderSubscriberAlreadyActivatedPin);
		log.info("confirmpassword: "+SenderSubscriberNewPin);
		log.info("newpassword: "+SenderSubscriberconfirmNewPin);
		log.info("message: "+messagefromProp);
		
		String fullCurl = common.returnFullCurl(SenderSubscriberMsisdn, SubscriberMenuSortCodeFromProp);
		String curl = fullCurl + "&p1=" + subscribermsisdnfromprop1 + "&p2="+ SenderSubscriberAlreadyActivatedPin  + "&p3=" + SenderSubscriberNewPin+ "&p4="+ SenderSubscriberconfirmNewPin;
		log.info("ChanePin.java: "+curl+'"');
        
        /*****************Create SSH Connection and execute the command****************/
        log.info("===========>>>>Create SSH Connection");
        common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(curl + "\" \n");
        common.createChannel();
        
        boolean state = common.Result.contains(messagefromProp);
        log.info("common.Result: "+common.Result);
        log.info(common.Result.contains(messagefromProp));
        log.info(messagefromProp+"..."+state);
        log.info("\n=====================Transaction Status===========================\n");
        Assert.assertTrue(state);
        if(state == true){
        	log.info("USSD Response verified: "+messagefromProp);
        }
        else{
        	log.error("USSD reponse is not verified: \n Actual: "+common.Result +"\n Expected: "+messagefromProp);
		}
		log.info("\n" + common.Result);
		common.closeSSHConnection();
	}
}