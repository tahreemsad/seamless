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
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadSMSAppProperties;
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadUssdAppConfigFromProperties;
import com.seamless.ers.qa.cucumber.SQLQueries.SQLResultSets;

import cucumber.api.java.en.When;

public class USSDAppR2S {

	ReadConfigFromProperties readConfig = new ReadConfigFromProperties();
	ReadSMSAppProperties sms = new ReadSMSAppProperties();
	ReadUssdAppConfigFromProperties readUssd = ReadUssdAppConfigFromProperties.getInstance();
	private static Logger log = LogManager.getLogger(USSDAppR2R.class);
	SQLResultSets resultSet = new SQLResultSets();
	CucumberCommon common = new CucumberCommon();
	public String getERSreference, curl;
	private String countryCodeNetworkCodeFromProp;
	public double Delta = 1e-8;

	/**********************************
	 * String Based R2S
	 * @throws IOException
	 * @throws SQLException
	 * @throws JSchException
	 **********************************/

	@When("^User Performs R2S with valid data to the subscriber number (.+) from reseller number (.+) having resellerID (.+) with an amount (.+) and pin is (.+) and (.+) in message is returned after execution having shortcode (.+) and option was (.+)$")
	public void PerformR2RwithValidData(String subscriberMSISDN, String resellerMSISDN, String resellerID, String amount, String pin, String message, String shortcode, String menuOption)
			throws IOException, SQLException, JSchException {

		log.info("=======================>>>>>>>>>>>>Perform String Base R2S<<<<<<<<<<<=====================");
		countryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");

		String InvalidHierarchy = readUssd.returnValueForUssd("InvalidHierarchy");
		String ContractViolation = readUssd.returnValueForUssd("ContractViolation");
		String Invalidpin = readUssd.returnValueForUssd("InvalidPin");		
		String InvalidR2RTransfer = readUssd.returnValueForUssd("InvalidR2RTransfer");
		
		String SubscriberMSISDNFromConfig = readUssd.returnValueForUssd(subscriberMSISDN);
		SubscriberMSISDNFromConfig = countryCodeNetworkCodeFromProp+SubscriberMSISDNFromConfig;
		String ResellerMSISDNFromConfig = readUssd.returnValueForUssd(resellerMSISDN);
		ResellerMSISDNFromConfig = countryCodeNetworkCodeFromProp+ResellerMSISDNFromConfig;
		String ResellerIDFromConfig = readUssd.returnValueForUssd(resellerID);
		String AmountFromConfig = readUssd.returnValueForUssd(amount);
		String PinFromConfig = readUssd.returnValueForUssd(pin);
		String MessageFromConfig = readUssd.returnValueForUssd(message);
		String ShortCodeFromConfig = readUssd.returnValueForUssd(shortcode);
		String MenuOptionFromConfig = readUssd.returnValueForUssd(menuOption);
		

		log.info("CountryCodeNetworkCodeFromProp: "+countryCodeNetworkCodeFromProp);
		log.info("SubscriberMSISDNFromConfig: "+SubscriberMSISDNFromConfig);
		log.info("ResellerMSISDNFromConfig: "+ResellerMSISDNFromConfig);
		log.info("ResellerIDFromConfig: "+ResellerIDFromConfig);
		log.info("AmountFromConfig: "+AmountFromConfig);
		log.info("PinFromConfig: "+PinFromConfig);
		log.info("MessageFromConfig: "+MessageFromConfig);
		log.info("ShortCodeFromConfig: "+ShortCodeFromConfig);
		log.info("MenuOptionFromConfig: "+MenuOptionFromConfig);
		
		/***************** Prepare Customer specific curl command ****************/
		String fullCurl = common.returnFullCurl(ResellerMSISDNFromConfig, ShortCodeFromConfig);
		if (System.getProperty("customer").equals("ng_glo"))
		{
			log.info("I am verifying the R2S for "+System.getProperty("customer"));
			curl = fullCurl + "&p1=" + MenuOptionFromConfig + "&p2="+ SubscriberMSISDNFromConfig + "&p3=" + AmountFromConfig + "&p4=" + PinFromConfig + "&p5=1";
			log.info("R2S.java: " + curl + '"');
			}
			else{				
				curl = fullCurl + "&p1=" + MenuOptionFromConfig + "&p2="+ SubscriberMSISDNFromConfig + "&p3=" + AmountFromConfig + "&p4=" + PinFromConfig;
				log.info("R2S.java: " + curl + '"');
				}

		/***************** Reseller Balance Fetched before transaction ****************/
		log.info("*****************Reseller Balance Fetched before transaction****************");
		log.info("R2S >> resellerID: " + ResellerIDFromConfig);

		double current_amount = resultSet.returnResellerAmount(ResellerIDFromConfig);
		log.info("Reseller current balance before transaction: "+ current_amount);

		
		/***************** Create SSH Connection and execute the command ****************/
		log.info("===========>>>>Create SSH Connection");
		common.createSSHCoonection();
		((ChannelExec) common.channel).setCommand(curl + "\" \n");
		common.createChannel();

		/***************** Transaction/Amount Checking ****************/
		boolean state = common.Result.contains(MessageFromConfig);
		log.info(common.Result.contains(MessageFromConfig));
		log.info("common.Result: " + common.Result);
		log.info(MessageFromConfig + "..." + state);
		log.info("\n=====================Transaction Status===========================\n");
		Assert.assertTrue(state);
		if (state == true) {
			log.info("\nTransaction is Successfull ");
			log.info("Reseller Balance After Successfull Transaction "
					+ resultSet.returnResellerAmount(ResellerIDFromConfig));
			double remainingBal = current_amount - Integer.parseInt(AmountFromConfig);

			if (MessageFromConfig.contains(Invalidpin)
					|| MessageFromConfig.contains(ContractViolation)
					|| MessageFromConfig.contains(InvalidHierarchy)
					|| MessageFromConfig.contains(InvalidR2RTransfer)) {
				Assert.assertEquals(current_amount,resultSet.returnResellerAmount(ResellerIDFromConfig), Delta);
				log.info("Balance is not deducted");
			} else {
				Assert.assertEquals(remainingBal,resultSet.returnResellerAmount(ResellerIDFromConfig), Delta);
				log.info("Balance is deducted");
			}
		} else {
			log.error("\nTransaction Not Successfull ");
			log.info("Reseller Balance After Failed Transaction "
					+ resultSet.returnResellerAmount(ResellerIDFromConfig));

		}
		log.info("\n" + common.Result);
		common.closeSSHConnection();
	}

	
	@When("^User Perform R2S with invalid data to the subcriber number (.+) from reseller number (.+) having resellerID (.+) with an amount (.+) and pin is (.+) and (.+) in message is returned after execution having shortcode (.+) and option was (.+)$")
	public void PerformR2RWithInvalidData(String subscriberMSISDN,
			String resellerMSISDN, String resellerID, String amount,
			String pin, String message, String shortcode, String menuOption)
			throws IOException, SQLException, JSchException {
		countryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");

		log.info("=======================>>>>>>>>>>>>Perform String Base R2R with Invlalid Data<<<<<<<<<<<=====================");
		String InvalidHierarchy = readUssd.returnValueForUssd("InvalidHierarchy");
		String ContractViolation = readUssd.returnValueForUssd("ContractViolation");
		String Invalidpin = readUssd.returnValueForUssd("InvalidPin");
		String InvalidR2RTransfer = readUssd.returnValueForUssd("InvalidR2RTransfer");
		String SenderResellerIDFromConfig = readUssd.returnValueForUssd(resellerID);
		String RecieverSubscriberFromConfig = readUssd.returnValueForUssd(subscriberMSISDN);
		RecieverSubscriberFromConfig = countryCodeNetworkCodeFromProp+RecieverSubscriberFromConfig;
		String SenderResellerMsisdnFromConfig = readUssd.returnValueForUssd(resellerMSISDN);
		SenderResellerMsisdnFromConfig = countryCodeNetworkCodeFromProp+SenderResellerMsisdnFromConfig;
		String TopupAmountFromConfig = readUssd.returnValueForUssd(amount);
		String ResellerPinFromConfig = readUssd.returnValueForUssd(pin);
		String ResellerMessageFromConfig = readUssd.returnValueForUssd(message);
		String ShortCodeFromConfig = readUssd.returnValueForUssd(shortcode);
		String MenuOptionFromConfig = readUssd.returnValueForUssd(menuOption);
		
		log.info("SenderResellerIDFromConfig:"+SenderResellerIDFromConfig);
		log.info("RecieverSubscriberFromConfig:"+RecieverSubscriberFromConfig);
		log.info("SenderResellerMsisdnFromConfig:"+SenderResellerMsisdnFromConfig);
		log.info("TopupAmountFromConfig:"+TopupAmountFromConfig);
		log.info("ResellerPinFromConfig:"+ResellerPinFromConfig);
		log.info("ResellerMessageFromConfig:"+ResellerMessageFromConfig);
		log.info("ShortCodeFromConfig:"+ShortCodeFromConfig);
		log.info("MenuOptionFromConfig:"+MenuOptionFromConfig);
		log.info("CountryCodeNetworkCodeFromProp:"+countryCodeNetworkCodeFromProp);

		/***************** Prepare Customer specific curl command ****************/
		String fullCurl = common.returnFullCurl(SenderResellerMsisdnFromConfig, ShortCodeFromConfig);
		if (System.getProperty("customer").equals("ng_glo"))
		{
			log.info("I am verifying the R2S for "+System.getProperty("customer"));
			curl = fullCurl + "&p1=" + MenuOptionFromConfig + "&p2=" + RecieverSubscriberFromConfig + "&p3=" + TopupAmountFromConfig + "&p4=" + ResellerPinFromConfig + "&p5=1";
			log.info("R2S.java: " + curl + '"');
			}
			else{				
				curl = fullCurl + "&p1=" + MenuOptionFromConfig + "&p2=" + RecieverSubscriberFromConfig + "&p3=" + TopupAmountFromConfig + "&p4=" + ResellerPinFromConfig;
				log.info("R2S.java: " + curl + '"');
				}

		/***************** Reseller Balance Fetched before transaction ****************/
		log.info("*****************Reseller Balance Fetched before transaction****************");
		log.info("R2R >> resellerID: " + SenderResellerIDFromConfig);

		double current_amount = resultSet.returnResellerAmount(SenderResellerIDFromConfig);
		log.info("Reseller current balance before transaction: "
				+ current_amount);

		/***************** Create SSH Connection and execute the command ****************/
		log.info("===========>>>>Create SSH Connection");
		common.createSSHCoonection();
		((ChannelExec) common.channel).setCommand(curl + "\" \n");
		common.createChannel();

		/***************** Transaction/Amount Checking ****************/
		boolean state = common.Result.contains(ResellerMessageFromConfig);
		log.info(("ResellerMessageFromConfigTransaction: "+ResellerMessageFromConfig));
		log.info(ResellerMessageFromConfig + "..." + common.Result);
		log.info("\n=====================Transaction Status===========================\n");
		Assert.assertTrue(state);
		try {
			if (state == true) {
				log.info("Message: " + ResellerMessageFromConfig);
				log.info("Reseller Balance After Successfull Transaction "
						+ resultSet.returnResellerAmount(SenderResellerIDFromConfig));
				double remainingBal = current_amount - Integer.parseInt(TopupAmountFromConfig);

				if (ResellerMessageFromConfig.contains(Invalidpin)
						|| ResellerMessageFromConfig.contains(ContractViolation)
						|| ResellerMessageFromConfig.contains(InvalidHierarchy)
						|| ResellerMessageFromConfig.contains(InvalidR2RTransfer)) {
					Assert.assertEquals(current_amount,resultSet.returnResellerAmount(SenderResellerIDFromConfig), Delta);
					log.info("Balance is not deducted");
				} else {
					Assert.assertEquals(remainingBal,resultSet.returnResellerAmount(SenderResellerIDFromConfig), Delta);
					log.info("Balance is deducted");
				}
			} else {
				log.error("\nTransaction Not Successfull ");
				log.info("Reseller Balance After Failed Transaction "
						+ resultSet.returnResellerAmount(SenderResellerIDFromConfig));
				return;
			}
		} finally {
			log.info("\n" + common.Result);
			common.closeSSHConnection();
		}		
	}
	
	/***************************
	 * SMS
	 * @throws InterruptedException 
	 * ***************************/
	
	@When("^User Perform R2S with valid data through SMS channel to the subscriber number (.+) having resellerID (.+) from reseller number (.+) with an amount (.+) and pin is (.+) and in logs notification message (.+) is returned after execution where componenet is (.+) and log lines are (.+) and curl message was (.+)$")
	public void PerformR2SWithValidDataThroughSMSapp(String subcriberNumber, String resellerID ,String resellerNumber, String amount, String pin, String message, String component, String loglines, String curlMsg) throws IOException, SQLException, JSchException, InterruptedException {
		countryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");

		String SubscriberNumberFromProp = sms.returnPropertyForSMS(subcriberNumber);
		SubscriberNumberFromProp = countryCodeNetworkCodeFromProp+SubscriberNumberFromProp;
		String ResellerIdFromProp = sms.returnPropertyForSMS(resellerID);
		String ResellerNumberFromProp = sms.returnPropertyForSMS(resellerNumber);
		ResellerNumberFromProp = countryCodeNetworkCodeFromProp+ResellerNumberFromProp;
		String TransferAmoutFromProp = sms.returnPropertyForSMS(amount);
		String ResellerPinFromProp = sms.returnPropertyForSMS(pin);
		String LogMessageFromProp = sms.returnPropertyForSMS(message);
		String LogComponenetFromProp = sms.returnPropertyForSMS(component);
		String LogLinesFromProp = sms.returnPropertyForSMS(loglines);
		String CurlMessageFromProp = sms.returnPropertyForSMS(curlMsg);
		
		log.info("SubscriberNumberFromProp: "+SubscriberNumberFromProp);
		log.info("ResellerIdFromProp: "+ResellerIdFromProp);
		log.info("ResellerNumberFromProp: "+ResellerNumberFromProp);
		log.info("TransferAmoutFromProp: "+TransferAmoutFromProp);
		log.info("ResellerPinFromProp: "+ResellerPinFromProp);
		log.info("LogMessageFromProp: "+LogMessageFromProp);
		log.info("LogComponenetFromProp: "+LogComponenetFromProp);
		log.info("LogLinesFromProp: "+LogLinesFromProp);
		log.info("CurlMessageFromProp: "+CurlMessageFromProp);
		log.info("CountryCodeNetworkCodeFromProp: "+countryCodeNetworkCodeFromProp);
		
		log.info("=======================>>>>>>>>>>>>Perform String Base R2S with Valid Data through SMS<<<<<<<<<<<=====================");
			 
    	String generateBasicCurl = common.returnCurlForSMS();
    	String fullCurl = generateBasicCurl+"&from="+ResellerNumberFromProp+"&msg="+CurlMessageFromProp+"+"+TransferAmoutFromProp+"+"+SubscriberNumberFromProp+"+"+ResellerPinFromProp;
    	
    	log.info("R2S.java: "+fullCurl+'"');
    	
    	/*****************Reseller Balance Fetched before transaction****************/
    	log.info("*****************Reseller Balance Fetched before transaction****************");
    	log.info("R2R >> resellerID: "+ResellerIdFromProp);    	
    	double current_amount=resultSet.returnResellerAmount(ResellerIdFromProp);
        log.info("Reseller current balance before transaction: "+current_amount);        	
        
        /*****************Create SSH Connection and execute the command for curl command****************/
        log.info("===========>>>>Create SSH Connection");
        common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(fullCurl + "\" \n");
        common.createChannel();
        common.closeSSHConnection();
        
        common.SleepForSomeTime(5000);
        
        /*****************Create SSH Connection and execute the command for log command****************/
        log.info("===========>>>>Create SSH Connection");
        String logreading = common.returnCommandForLogReading(LogLinesFromProp, LogComponenetFromProp);
        common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(logreading);
        common.createChannel();
        
        /*****************Transaction/Amount Checking****************/
        boolean state = common.Result.contains(LogMessageFromProp);
        log.info(common.Result.contains(LogMessageFromProp));
        log.info(message+"..."+state);
        log.info(common.Result);
        log.info("\n=====================Transaction Status===========================\n");
	    try{    
	    	Assert.assertTrue(state);
        	if(state == true){
	        	log.info("Message: "+LogMessageFromProp);
	        	log.info("Notification message found in the logs");
	        	log.info("Reseller Balance After Successfull Transaction "+resultSet.returnResellerAmount(ResellerIdFromProp));
	            double remainingBal = current_amount - Integer.parseInt(TransferAmoutFromProp);
	            
            	Assert.assertEquals(remainingBal, resultSet.returnResellerAmount(ResellerIdFromProp), Delta);
            	log.info("Balance is deducted");
	        }
	        else{
	        	log.error("Notification message not found in the logs");
	        	log.error("\nTransaction Not Successfull ");
	        	log.info("Reseller Balance After Failed Transaction "+resultSet.returnResellerAmount(ResellerIdFromProp));
	        	return;
			}
    	}finally{
		log.info("\n" + common.Result);
		common.closeSSHConnection();
		}
	}
}