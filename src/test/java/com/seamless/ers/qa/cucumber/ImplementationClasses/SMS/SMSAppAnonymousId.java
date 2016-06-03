package com.seamless.ers.qa.cucumber.ImplementationClasses.SMS;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.seamless.ers.qa.cucumber.ImplementationClasses.Common.CucumberCommon;
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadConfigFromProperties;
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadSMSAppProperties;
import com.seamless.ers.qa.cucumber.SQLQueries.SQLResultSets;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SMSAppAnonymousId
{
	CucumberCommon common;
	CucumberCommon commonForVerification;
    ReadConfigFromProperties readConfig = new ReadConfigFromProperties();
	ReadSMSAppProperties sms = new ReadSMSAppProperties();

	private static Logger log;
	SQLResultSets resultSet = new SQLResultSets();
	
	String MessageFromPro, NotificationFromProp,pinFromProp,shortcodeFromProp,LogComponenetFromProp,LogLinesFromProp;
	public String msisdn;
	public String AnonymousID;
	private String countryCodeNetworkCodeFromProp;
	
	public SMSAppAnonymousId() throws IOException
	{
		common = new CucumberCommon();
		commonForVerification=new CucumberCommon();
		log = LogManager.getLogger(CucumberCommon.class);
		msisdn = common.createNewSubscriber();
		try {
			readConfig = new ReadConfigFromProperties();
			countryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@When("^I register a user for anonymous id using smsapp msg is (.+) log notification message is (.+) and component is (.+) and number of log lines are (.+)$")
    public void AnonymousIdRegistration(String msg, String notificationMessage,String logComponenet, String loglines) throws JSchException, IOException, InterruptedException
	{
		MessageFromPro = sms.returnPropertyForSMS(msg);
		NotificationFromProp = sms.returnPropertyForSMS(notificationMessage);
		LogComponenetFromProp = sms.returnPropertyForSMS(logComponenet);
		LogLinesFromProp = sms.returnPropertyForSMS(loglines);
		
		log.info("MessageFromPro: "+MessageFromPro);
		log.info("NotificationMessageFromProp: "+NotificationFromProp);
		log.info("LogComponenetFromProp: "+LogComponenetFromProp);
		log.info("LogLinesFromProp: "+LogLinesFromProp);
		
		String returnCurl = common.returnCurlForSMS();
		String fullCurl = returnCurl+"&from="+msisdn+"&msg="+MessageFromPro;
		log.info(fullCurl+'"');
		
        /*****************Create SSH Connection and execute the curl command****************/
        log.info("===========>>>>Create SSH Connection for curl command");
        common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(fullCurl + "\" \n");
        common.createChannel();
        log.info("common.Result..."+common.Result);
        common.closeSSHConnection();
        
        common.SleepForSomeTime(5000);
        
		/*****************Create SSH Connection and execute the log command****************/
        log.info("===========>>>>Create SSH Connection for log command");
        String logData = common.returnCommandForLogReading(LogLinesFromProp, LogComponenetFromProp);
        common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(logData);
        common.createChannel();
        log.info("common.Result..."+common.Result);
        common.closeSSHConnection();
        
        boolean state = common.Result.contains(NotificationFromProp);
        
        log.info("common.Result: "+common.Result);
        log.info("notificationMessage: "+NotificationFromProp);
    
        if(state == true){
        		log.info("Notification message verified from logs");
        }else{
        		log.error("Error validating the notification message");
        }
        
        		log.info("common.Result: "+common.Result);
        		log.info("notificationMessage:"+ notificationMessage);
	}
	
	@Then("^I generate anonymousId using smsapp for subscriber and shortcode is (.+) log notification message is (.+) and (.+) and component is (.+) and number of log lines are (.+)$")
	public void generateAnonymousId(String shortcode, String pin,String notification,String loglines,String logComponenet)throws JSchException, IOException, InterruptedException
	{

	 	shortcodeFromProp = sms.returnPropertyForSMS(shortcode);
		NotificationFromProp = sms.returnPropertyForSMS(notification);
		pinFromProp = sms.returnPropertyForSMS(pin);
		LogComponenetFromProp = sms.returnPropertyForSMS(logComponenet);
		LogLinesFromProp = sms.returnPropertyForSMS(loglines);
		
		String IP = System.getProperty("targetEnvironment");
		String returnCurl1 =  "curl" + " \"http://"+IP+":8991/smsapp";
		String fullcurl1= returnCurl1+"?from="+msisdn+"&to="+shortcodeFromProp+"&msg="+pinFromProp+"+"+NotificationFromProp;
		log.info(fullcurl1+'"');
		/*****************Create SSH Connection and execute the curl command****************/
        log.info("===========>>>>Create SSH Connection for curl command");
        common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(fullcurl1 + "\" \n");
        common.createChannel();
        log.info("common.Result..."+common.Result);
        common.closeSSHConnection();
        
        common.SleepForSomeTime(5000);
        
        commonForVerification.createSSHCoonection();
        
		/*****************Create SSH Connection and execute the log command****************/
        log.info("===========>>>>Create SSH Connection for log command");
        String logData = commonForVerification.returnCommandForLogReading(LogComponenetFromProp,LogLinesFromProp);
        commonForVerification.createSSHCoonection();
        ((ChannelExec) commonForVerification.channel).setCommand(logData);
        commonForVerification.createChannel();
         
        
      	 log.info("=======================>>>>>>>>>>>>Verification of AnonymousID<<<<<<<<<<<=====================");
 
      	 log.info("===========>>>>Create SSH Connection");
      	 commonForVerification.createSSHCoonection();
		 ((ChannelExec) commonForVerification.channel).setCommand(fullcurl1 + "\" \n");
		 commonForVerification.createChannel();
		 
		 String[] splitTheResultValue = commonForVerification.Result.split("\\s+");
		 String AnonymousIdNew = splitTheResultValue[29];
		 log.info("Anonymus ID: "+AnonymousIdNew);
		 log.info("Result: "+commonForVerification.Result);

		 String split = AnonymousIdNew.substring(0,9);
		 AnonymousID=split;
		 log.info("Anonymous ID after splitting.........: "+split);
		 
		 log.info("Result: "+commonForVerification.Result);
	
		 String[] splitTheResultValue2 = commonForVerification.Result.split("\\s+");
		 String AnonymousIdForVerification = splitTheResultValue2[29];
		 log.info("AnonymousIdForVerification: "+AnonymousIdForVerification);
		 log.info("Result: "+commonForVerification.Result);	 
			
	 if (AnonymousIdForVerification.equals(AnonymousIdNew)) {
				log.info("\nAnonymousId is verified"); 
		} else {
				log.error("\nAnonymousId is not verified");
		}
		commonForVerification.closeSSHConnection();
	}
	
    @When("^I Perform R2S through SMS channel to the anonymousId (.+) having resellerID (.+) from reseller number (.+) with an amount (.+) and pin is (.+) and in logs notification message (.+) is returned after execution where componenet is (.+) and log lines are (.+) and curl message was (.+)$")
	public void anonymousIdR2Ssmsapp(String anonymousId, String resellerID ,String resellerNumber, String amount, String pin, String message, String component, String loglines, String curlMsg) throws IOException,JSchException,SQLException,InterruptedException
	{
		String ResellerIdFromProp = sms.returnPropertyForSMS(resellerID);
		String ResellerNumberFromProp = sms.returnPropertyForSMS(resellerNumber);
		ResellerNumberFromProp = countryCodeNetworkCodeFromProp+ResellerNumberFromProp;
		String TransferAmoutFromProp = sms.returnPropertyForSMS(amount);
		String ResellerPinFromProp = sms.returnPropertyForSMS(pin);
		String LogMessageFromProp = sms.returnPropertyForSMS(message);
		String LogComponenetFromProp = sms.returnPropertyForSMS(component);
		String LogLinesFromProp = sms.returnPropertyForSMS(loglines);
		String CurlMessageFromProp = sms.returnPropertyForSMS(curlMsg);
		
		log.info("anonymousId: "+AnonymousID);
		log.info("ResellerIdFromProp: "+ResellerIdFromProp);
		log.info("ResellerNumberFromProp: "+ResellerNumberFromProp);
		log.info("TransferAmoutFromProp: "+TransferAmoutFromProp);
		log.info("ResellerPinFromProp: "+ResellerPinFromProp);
		log.info("LogMessageFromProp: "+LogMessageFromProp);
		log.info("LogComponenetFromProp: "+LogComponenetFromProp);
		log.info("LogLinesFromProp: "+LogLinesFromProp);
		log.info("CurlMessageFromProp: "+CurlMessageFromProp);
				 
    	String generateBasicCurl = common.returnCurlForSMS();
    	String fullCurl = generateBasicCurl+"&from="+ResellerNumberFromProp+"&msg="+CurlMessageFromProp+"+"+TransferAmoutFromProp+"+"+AnonymousID+"+"+ResellerPinFromProp;
    	
    	log.info("R2S.java: "+fullCurl+'"');
    	
		/*****************Reseller Balance Fetched before transaction****************/
    	log.info("*****************Reseller Balance Fetched before transaction****************");
    	log.info("R2R >> resellerID: "+ResellerIdFromProp);
    	double current_amount= resultSet.returnResellerAmount(ResellerIdFromProp);
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
        	if(state == true){
	        	log.info("Message: "+LogMessageFromProp);
	        	log.info("Notification message found in the logs");
	        	log.info("Reseller Balance After Successful Transaction "+resultSet.returnResellerAmount(ResellerIdFromProp));
	        	log.info("Balance is deducted");
	        	
				log.info("\n" + common.Result);
				common.closeSSHConnection();
				
    			return;
			
		    }            	
	}catch(Exception e){
		log.error("Stack: "+e);
		}
	}
}