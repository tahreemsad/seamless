package com.seamless.ers.qa.cucumber.ImplementationClasses.USSD;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.seamless.ers.qa.cucumber.ImplementationClasses.Common.CucumberCommon;
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadConfigFromProperties;
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadUssdAppConfigFromProperties;

import cucumber.api.java.en.When;

public class USSDAppSubscriberRegistration {
	
	ReadConfigFromProperties readConfig = new ReadConfigFromProperties();
	ReadUssdAppConfigFromProperties readUssd = ReadUssdAppConfigFromProperties.getInstance();
	private static Logger log = LogManager.getLogger(USSDAppSubscriberRegistration.class);
	CucumberCommon common = new CucumberCommon();
	public String SenderSubscriberMsisdn;
	USSDAppChangePin changepin = new USSDAppChangePin();
	
	/**********************************
	 * @throws IOException
	 * @throws JSchException
	 * @throws InterruptedException 
	 **********************************/
	
	@When("^User register him self by hitting the shortcode (.+) with msisdn (.+) with menuOption (.+) with activated pin (.+) with new pin (.+) and in response i received the message (.+)$")
	public void UserRegistration(String shortcode, String msisdn, String menuOption, String pin, String newpin, String message) throws IOException, JSchException, InterruptedException{
		log.info("=======================>>>>>>>>>>>>User Registration<<<<<<<<<<<=====================");
    	String CountryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");
		String SubscriberMenuSortCodeFromProp = readUssd.returnValueForUssd(shortcode);
		String SenderSubscriberMsisdnFromProp = readUssd.returnValueForUssd(msisdn); 
		SenderSubscriberMsisdnFromProp = CountryCodeNetworkCodeFromProp+SenderSubscriberMsisdnFromProp;
		String SenderSubscriberMenuOptionFromProp = readUssd.returnValueForUssd(menuOption);
		String SenderSubscriberAlreadyActivatedPin = readUssd.returnValueForUssd(pin);
		String SenderSubscriberAlreadyActivatedNewPin = readUssd.returnValueForUssd(newpin);
		String AlreadyActivatedMsg = readUssd.returnValueForUssd(message);
		
		log.info("SubscriberMenuSortCodeFromProp: "+SubscriberMenuSortCodeFromProp);
		log.info("SenderSubscriberMsisdnFromProp: "+SenderSubscriberMsisdnFromProp);
		log.info("SenderSubscriberMenuOptionFromProp: "+SenderSubscriberMenuOptionFromProp);
		log.info("SenderSubscriberAlreadyActivatedPin: "+SenderSubscriberAlreadyActivatedPin);
		log.info("SenderSubscriberAlreadyActivatedNewPin: "+SenderSubscriberAlreadyActivatedNewPin);
		log.info("AlreadyActivatedMsg: "+AlreadyActivatedMsg);
		log.info("CountryCodeNetworkCodeFromProp: "+CountryCodeNetworkCodeFromProp);

		String fullCurl = common.returnFullCurl( SenderSubscriberMsisdnFromProp, SubscriberMenuSortCodeFromProp);
		String curl = fullCurl + "&p1=" + SenderSubscriberMenuOptionFromProp + "&p2="+ SenderSubscriberAlreadyActivatedPin  + "&p3=" + SenderSubscriberAlreadyActivatedNewPin;
		log.info("Registration.java: " + curl + '"');
		
		/***************** Create SSH Connection and execute the command ****************/
		log.info("===========>>>>Create SSH Connection");
		common.createSSHCoonection();
		((ChannelExec) common.channel).setCommand(curl + "\" \n");
		common.SleepForSomeTime(5000);
		common.createChannel();
		
		/***************** Transaction/Amount Checking ****************/
		boolean state = common.Result.contains(AlreadyActivatedMsg);
		log.info(common.Result.contains(AlreadyActivatedMsg));
		log.info("common.Result: " + common.Result);
		log.info(AlreadyActivatedMsg + "..." + state);
		Assert.assertTrue(state);
		try{
			if(state ==true){
				Assert.assertTrue(AlreadyActivatedMsg, state);
				log.info("USSD Response Verified: "+AlreadyActivatedMsg);
				log.info("User is  registered");
			}else{
				Assert.assertFalse(AlreadyActivatedMsg, state);
				log.info("User is not registered");
				log.error("USSD Response not verified");
				log.error("USSD Response fetched: "+common.Result+" \n USSD Response expected: "+AlreadyActivatedMsg);
				return;
			}
		}
	finally{

		log.info("\n" + common.Result);
		common.closeSSHConnection();
		}
	}
	
	@When("^New subscriber is registered having shortcode (.+) with menu option (.+) with pin (.+) with new pin (.+) and in response i received the message (.+)$")
	public void subscriberRegistration(String shortcode, String menuOption, String pin, String newpin, String message) throws IOException, JSchException, InterruptedException{
		
		log.info("=======================>>>>>>>>>>>>Subscriber Registration Started Nested Function<<<<<<<<<<<=====================");
		String SubscriberMenuSortCodeFromProp = readUssd.returnValueForUssd(shortcode);
		SenderSubscriberMsisdn = common.createNewSubscriber();
		String SenderSubscriberMenuOptionFromProp = readUssd.returnValueForUssd(menuOption);
		String SenderSubscriberAlreadyActivatedPin = readUssd.returnValueForUssd(pin);
		String SenderSubscriberAlreadyActivatedNewPin = readUssd.returnValueForUssd(newpin);
		String SubscriberRegistered = readUssd.returnValueForUssd(message);
		
		log.info("SubscriberMenuSortCodeFromProp: "+SubscriberMenuSortCodeFromProp);
		log.info("SenderSubscriberMsisdnFromProp: "+SenderSubscriberMsisdn);
		log.info("SenderSubscriberMenuOptionFromProp: "+SenderSubscriberMenuOptionFromProp);
		log.info("SenderSubscriberActivatedPin: "+SenderSubscriberAlreadyActivatedPin);
		log.info("SenderSubscriberActivatedNewPin: "+SenderSubscriberAlreadyActivatedNewPin);
		log.info("SubscriberRegistered: "+SubscriberRegistered);

		String fullCurl = common.returnFullCurl(SenderSubscriberMsisdn, SubscriberMenuSortCodeFromProp);
		String curl = fullCurl + "&p1=" + SenderSubscriberMenuOptionFromProp + "&p2="+ SenderSubscriberAlreadyActivatedPin  + "&p3=" + SenderSubscriberAlreadyActivatedNewPin;
		log.info("Registration.java: " + curl + '"');
		
		/***************** Create SSH Connection and execute the command ****************/
		log.info("===========>>>>Create SSH Connection");
		common.createSSHCoonection();
		((ChannelExec) common.channel).setCommand(curl + "\" \n");
		common.SleepForSomeTime(5000);
		common.createChannel();
		
		/***************** Transaction/Amount Checking ****************/
		
		boolean state = common.Result.contains(SubscriberRegistered);
		//boolean state1 = common.Result.contains(SubscriberAlreadyRegistered);
		log.info(common.Result.contains(SubscriberRegistered));
		log.info("common.Result: " + common.Result);
		log.info(SubscriberRegistered + "..." + state);
		Assert.assertTrue(state);
		try{
			if(state==true){
				Assert.assertTrue(SubscriberRegistered, state);
				log.info("USSD Response Verified: "+SubscriberRegistered);
				log.info("User is  registered");
			}else{
				Assert.assertFalse(SubscriberRegistered, state);
				log.info("User is not registered");
				log.error("USSD Response not verified");
				log.error("USSD Response fetched: "+common.Result+" \n USSD Response expected: "+SubscriberRegistered);
				return;
			}
		}
	finally{

		log.info("\n" + common.Result);
		common.closeSSHConnection();
		}
	}
	
	@When("^Subscriber changes his pin with the shortcode (.+) having option (.+) with default password (.+) and new password is (.+) and confirm password is (.+) and in response i received the message (.+)$")
	public void ChangePinForSubscriber(String shortCode, String option, String currentpassword, String newPass, String confimrmPass, String message) throws JSchException, IOException{
    	
		log.info("i am going to call changePinForSubscriber now ");
		String getSubscriberMSISDN = SenderSubscriberMsisdn;
		log.info("getSubscriberMSISDN: "+getSubscriberMSISDN);
		changepin.changePinForSubscriber(shortCode, getSubscriberMSISDN, option, currentpassword, newPass, confimrmPass, message);
		}
}