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
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class USSDAppAnonymousId {
	
	ReadConfigFromProperties readConfig = new ReadConfigFromProperties();
	ReadUssdAppConfigFromProperties readUssd = ReadUssdAppConfigFromProperties.getInstance();

	private static Logger log = LogManager.getLogger(USSDAppSubscriberRegistration.class);
	CucumberCommon common = new CucumberCommon();
	CucumberCommon commonForAnonymousID = new CucumberCommon();
	CucumberCommon commonForAnonymousIDverification = new CucumberCommon();
	CucumberCommon commonForR2S = new CucumberCommon();
	SQLResultSets resultSet = new SQLResultSets();
	public String SenderSubscriberMsisdn;
	public String SubscriberAnonymousID;
	String countryCodeNetworkCodeFromProp;
	public double Delta = 1e-8;
		
	@When("^A New subscriber is generated and registered having shortcode (.+) with menu option (.+) with pin (.+) with new pin (.+) and in response i received the message (.+)$")
	
		public void subscriberRegistration(String shortcode, String menuOption, String pin, String newpin, String message) throws IOException, JSchException, InterruptedException{
			log.info("=======================>>>>>>>>>>>>Subscriber Registration Started Nested Function<<<<<<<<<<<=====================");
			countryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");

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
			
			/***************** Verification  ****************/
			
			boolean state = common.Result.contains(SubscriberRegistered);
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

			common.closeSSHConnection();
		}
			}

	@And("^I request to generate AnonymousID from MSISDN (.+) and shortcode (.+)$")
	public void GenerateAnonymousIDFromUSSD(String msisdn, String shortCode) throws IOException, SQLException, JSchException{
			log.info("=======================>>>>>>>>>>>>Generate AnonymousID from USSD<<<<<<<<<<<=====================");

			String MSISDN=SenderSubscriberMsisdn;
			String shortCodefromProp = readUssd.returnValueForUssd(shortCode);		
		
			log.info("msisdn:" +MSISDN);	
			log.info("shortCode:" +shortCodefromProp);
	
			String fullCurl = commonForAnonymousID.returnFullCurl(MSISDN, shortCodefromProp); 
			log.info("AnonymousID: "+fullCurl+'"');
    	
			/***************** Create SSH Connection and execute the command ****************/
			log.info("===========>>>>Create SSH Connection");
			commonForAnonymousID.createSSHCoonection();
			((ChannelExec) commonForAnonymousID.channel).setCommand(fullCurl + "\" \n");
			commonForAnonymousID.createChannel();
		 
			String[] splitTheResultValue = commonForAnonymousID.Result.split("\\s+");
			String AnonymousIdNew = splitTheResultValue[10];
			SubscriberAnonymousID=AnonymousIdNew;
			log.info("Anonymus ID: "+AnonymousIdNew);
			log.info("Result: "+commonForAnonymousID.Result);
		 
			commonForAnonymousID.closeSSHConnection();
			
			log.info("=======================>>>>>>>>>>>>Verification of AnonymousID from USSD<<<<<<<<<<<=====================");
			String curl = commonForAnonymousIDverification.returnFullCurl(MSISDN, shortCodefromProp); 
	    	log.info("AnonymousIdForVarification: "+curl+'"');
	    	
	    	/***************** Create SSH Connection and execute the command ****************/
			log.info("===========>>>>Create SSH Connection");
			commonForAnonymousIDverification.createSSHCoonection();
			((ChannelExec) commonForAnonymousIDverification.channel).setCommand(fullCurl + "\" \n");
			commonForAnonymousIDverification.createChannel();
			 
			 String[] splitTheResultValue2 = commonForAnonymousIDverification.Result.split("\\s+");
			 log.info("AnonymousIdForVarification: "+splitTheResultValue2[8]);
			 String AnonymousIdForVarification = splitTheResultValue2[8];
			 log.info("Result: "+commonForAnonymousIDverification.Result);
			 
					if (AnonymousIdForVarification.equals(AnonymousIdNew+".")) 
					{
						log.info("\nAnonymousId is verified");
				}	else {
						log.error("\nAnonymousId is not verified");
			
				}
					commonForAnonymousIDverification.closeSSHConnection();
			}
	
	@Then("^User Performs R2S with valid data to the subcriber anonymousid (.+) from reseller number (.+) having resellerID (.+) with an amount (.+) and pin is (.+) and You have topped up (.+) FCFA in message is returned after execution having shortcode (.+) and option was (.+)$")
	public void PerformR2RwithValidData(String Anonymousid, String resellerMSISDN, String resellerID, String amount, String pin, String message, String shortcode, String menuOption)
			throws IOException, SQLException, JSchException {
		
		log.info("=======================>>>>>>>>>>>>Perform String Base R2S<<<<<<<<<<<=====================");
		countryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");

			String SubscriberAnonymousid = SubscriberAnonymousID;
			String ResellerMSISDNFromConfig = readUssd.returnValueForUssd(resellerMSISDN);
			ResellerMSISDNFromConfig = countryCodeNetworkCodeFromProp+ResellerMSISDNFromConfig;
			String ResellerIDFromConfig = readUssd.returnValueForUssd(resellerID);
			String AmountFromConfig = readUssd.returnValueForUssd(amount);
			String PinFromConfig = readUssd.returnValueForUssd(pin);
			String MessageFromConfig = readUssd.returnValueForUssd(message);
			String ShortCodeFromConfig = readUssd.returnValueForUssd(shortcode);
			String MenuOptionFromConfig = readUssd.returnValueForUssd(menuOption);
			String InvalidHierarchy = readUssd.returnValueForUssd("InvalidHierarchy");
			String ContractViolation = readUssd.returnValueForUssd("ContractViolation");
			String Invalidpin = readUssd.returnValueForUssd("InvalidPin");		
			String InvalidR2RTransfer = readUssd.returnValueForUssd("InvalidR2RTransfer");
		
			log.info("SubscriberAnonymousID: "+SubscriberAnonymousid);
			log.info("ResellerMSISDNFromConfig: "+ResellerMSISDNFromConfig);
			log.info("ResellerIDFromConfig: "+ResellerIDFromConfig);
			log.info("AmountFromConfig: "+AmountFromConfig);
			log.info("PinFromConfig: "+PinFromConfig);
			log.info("MessageFromConfig: "+MessageFromConfig);
			log.info("ShortCodeFromConfig: "+ShortCodeFromConfig);
			log.info("MenuOptionFromConfig: "+MenuOptionFromConfig);
		
			String fullCurl = commonForR2S.returnFullCurl(ResellerMSISDNFromConfig, ShortCodeFromConfig);
			String curl = fullCurl + "&p1=" + MenuOptionFromConfig + "&p2="
				+SubscriberAnonymousid + "&p3=" + AmountFromConfig + "&p4=" + PinFromConfig;
			log.info("R2S.java: " + curl + '"');

		/***************** Reseller Balance Fetched before transaction ****************/
			log.info("*****************Reseller Balance Fetched before transaction****************");
			log.info("R2S >> resellerID: " + ResellerIDFromConfig);

			double current_amount = resultSet.returnResellerAmount(ResellerIDFromConfig);
			log.info("Reseller current balance before transaction: "+ current_amount);

		/***************** Create SSH Connection and execute the command ****************/
			log.info("===========>>>>Create SSH Connection");
			commonForR2S.createSSHCoonection();
			((ChannelExec) commonForR2S.channel).setCommand(curl + "\" \n");
			commonForR2S.createChannel();

		/***************** Transaction/Amount Checking ****************/
			boolean state = commonForR2S.Result.contains(MessageFromConfig);
			log.info(commonForR2S.Result.contains(MessageFromConfig));
			log.info("commonForR2S.Result: " + commonForR2S.Result);
			log.info(MessageFromConfig + "..." + state);
			log.info("\n=====================Transaction Status===========================\n");
			Assert.assertTrue(state);
			if (state == true) {
					log.info("\nTransaction is Successfull ");
					log.info("Reseller Balance After Successfull Transaction: "+ resultSet.returnResellerAmount(ResellerIDFromConfig));
			double remainingBal = current_amount - Integer.parseInt(AmountFromConfig);

			if (MessageFromConfig.contains(Invalidpin)
					|| MessageFromConfig.contains(ContractViolation)
					|| MessageFromConfig.contains(InvalidHierarchy)
					|| MessageFromConfig.contains(InvalidR2RTransfer)) {
			
				double getSenderAmount = resultSet.returnResellerAmount(ResellerIDFromConfig);
				
					Assert.assertEquals(current_amount,getSenderAmount, Delta);
					log.info("Balance is not deducted");
			} else {
					double getSenderAmount = resultSet.returnResellerAmount(ResellerIDFromConfig);
					Assert.assertEquals(remainingBal,getSenderAmount,Delta);
					log.info("Balance is deducted");
			}
		} 
				else {
					log.error("\nTransaction Not Successfull ");
					log.info("Reseller Balance After Failed Transaction "
					+ resultSet.returnResellerAmount(ResellerIDFromConfig));

		}
	
	}


	
}