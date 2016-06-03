package com.seamless.ers.qa.cucumber.ImplementationClasses.USSD;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

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

import cucumber.api.java.en.Then;

public class USSDAppCheckResellerBalance {
	ReadUssdAppConfigFromProperties readUssd = ReadUssdAppConfigFromProperties.getInstance();
	public static ReadSMSAppProperties sms = new ReadSMSAppProperties();

	private static Logger log = LogManager.getLogger( USSDAppCheckResellerBalance.class );
    ReadConfigFromProperties readConfig = new ReadConfigFromProperties();
	SQLResultSets resultSet = new SQLResultSets();
	CucumberCommon common = new CucumberCommon();
	private String countryCodeNetworkCodeFromProp;
	private String curl;
	/*************************	
	 * Reseller Balance Check
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws JSchException 
	 * 
	 *************************/
	
	
	@Then("^I perform the transaction from MSISDN (.+) having resellerID (.+) shortcode (.+) and menuOption (.+)$")
	public void checkBalanceFromUSSD(String msisdn, String resellerID, String shortCode, String optionMenu) throws IOException, SQLException, JSchException{
		log.info("=======================>>>>>>>>>>>>Perform Balance Inquiry from USSD<<<<<<<<<<<=====================");
		countryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");

		String msisdnfromProp =readUssd.returnValueForUssd(msisdn);
		msisdnfromProp = countryCodeNetworkCodeFromProp+msisdnfromProp;
		String resellerIDfromProp = readUssd.returnValueForUssd(resellerID);
		String shortCodefromProp = readUssd.returnValueForUssd(shortCode);		
		String menuOptionfromProp = readUssd.returnValueForUssd(optionMenu);
		
		log.info("countryCodeNetworkCodeFromProp:"+countryCodeNetworkCodeFromProp);
		log.info("msisdn:" +msisdnfromProp);
		log.info("resellerID" +resellerIDfromProp);	
		log.info("shortCode:" +shortCodefromProp);
		log.info("optionMenu" +menuOptionfromProp);	
    	
		String fullCurl = common.returnFullCurl(msisdnfromProp, shortCodefromProp); 
		
		if(System.getProperty("customer").equals("ci_mtn")){
			curl = fullCurl+"&p1="+menuOptionfromProp+"&p2=1";
			log.info("Balance Inquiry: "+curl+'"');
		}else
		{
			curl = fullCurl+"&p1="+menuOptionfromProp;
			log.info("Balance Inquiry: "+curl+'"');
		}
    	    	
    	
    	/*****************Reseller Balance Fetched before transaction****************/
    	log.info("*****************Reseller Balance Fetched before transaction****************");
    	log.info("CheckResellerBalance.java >> resellerID: "+resellerIDfromProp);
    	
    	int current_amount=resultSet.returnResellerIntegerAmount(resellerIDfromProp);
    	String current_amount_string=Integer.toString(current_amount);
        log.info("Reseller current balance before transaction: "+current_amount);        	
        
        /*****************Create SSH Connection and execute the command****************/
        log.info("===========>>>>Create SSH Connection");
        common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(curl + "\" \n");
        common.createChannelToExtractAmount();
           
        /*****************Transaction/Amount Checking****************/
        String customer = System.getProperty("customer");
		System.out.println("Customer: "+customer);
		
		if(customer.equals("ers_std"))
		{
			String getValueFromResult = common.Result.substring(34);
			String[] splitTheResultValue = getValueFromResult.split(Pattern.quote(","));
			String splittedValue = splitTheResultValue[0] + splitTheResultValue[1];
			log.info("splitedValue " + splittedValue);
			String[] splitOnDot = splittedValue.split(Pattern.quote("."));
			log.info("splitOnDot Value " + splitOnDot[0]);
			log.info("\n=====================Transaction Status===========================\n");
			log.info(splitOnDot[0] + "............." + current_amount_string);
			Assert.assertEquals(splitOnDot[0],current_amount_string);
			if (splitOnDot[0].equals(current_amount_string)) {
				log.info("\nTransaction is Successfull ");
				log.info("Reseller Balance After Successfull Transaction "+ resultSet.returnResellerAmount(resellerIDfromProp));
				Assert.assertEquals(splitOnDot[0],current_amount_string);
		}
			}
		else
		if (customer.equals("gb_mtn"))
		{
			String getValueFromResult1 = common.Result.substring(34);
			String[] splitTheResultValue1 = getValueFromResult1.split(Pattern.quote(" "));
			String splittedValue1 = splitTheResultValue1[0] + splitTheResultValue1[1];
			log.info("splitedValue " + splittedValue1);
			String[] splitOnDot1 = splittedValue1.split(Pattern.quote("."));
			log.info("splitOnDot Value " + splitOnDot1[0]);
			log.info("\n=====================Transaction Status===========================\n");
			log.info(splitOnDot1[0] + "............." + current_amount_string);
			Assert.assertEquals(splitOnDot1[0],current_amount_string);
			if (splitOnDot1[0].equals(current_amount_string)) {
				log.info("\nTransaction is Successfull ");
				log.info("Reseller Balance After Successfull Transaction "+ resultSet.returnResellerAmount(resellerIDfromProp));
				Assert.assertEquals(splitOnDot1[0],current_amount_string);
			}
		} else 
		if (customer.equals("ci_mtn")){
			String getValueFromResult1 = common.Result.substring(34);
			String[] splitTheResultValue1 = getValueFromResult1.split(Pattern.quote(" "));
			String splittedValue1 = splitTheResultValue1[0] + splitTheResultValue1[1];
			log.info("splitedValue " + splittedValue1);
			String[] splitOnDot1 = splittedValue1.split(Pattern.quote("."));
			log.info("splitOnDot Value " + splitOnDot1[0]);
			String[] splittedAmount = splitOnDot1[0].split(Pattern.quote(","));
			log.info(splittedAmount[0]);
			log.info(splittedAmount[1]);
			log.info(splittedAmount[2]);
			String TokenisedAmountFromUSSDresponse = splittedAmount[0].toString()+splittedAmount[1].toString()+splittedAmount[2].toString();
			log.info("TokenisedAmountFromUSSDresponse: "+TokenisedAmountFromUSSDresponse);
			log.info("\n=====================Transaction Status===========================\n");
			log.info("Current Amount: "+ current_amount_string);
			log.info(TokenisedAmountFromUSSDresponse + "............." + current_amount_string);
			Assert.assertEquals(TokenisedAmountFromUSSDresponse,current_amount_string);
			if (TokenisedAmountFromUSSDresponse.equals(current_amount_string)) {
				log.info("\nTransaction is Successfull ");
				log.info("Reseller Balance After Successfull Transaction "+ resultSet.returnResellerAmount(resellerIDfromProp));
				Assert.assertEquals(TokenisedAmountFromUSSDresponse,current_amount_string);
			}
		}
		else {
			log.error("\nTransaction is Not Successfull ");
		}
		log.info("\n" + common.Result);
		common.closeSSHConnection();
		
	}
	
	/*************************	
	 * Reseller Balance Check through SMS app
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws JSchException 
	 * 
	 *************************/
	
	@Then("^I check the balance through smsapp from msisdn (.+) having reseller id (.+) having curlmsg (.+) having pin (.+) and in response i get my balance from the (.+) logs in log lines (.+)$")
	public void checkBalanceFromSMSApp(String msisdn, String resellerID, String curlMsg, String pin, String component, String loglines) throws IOException, SQLException, JSchException, InterruptedException{
		
		log.info("=======================>>>>>>>>>>>>Perform Balance Inquiry from SMSapp <<<<<<<<<<<=====================");
		countryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");

		String msisdnfromProp =sms.returnPropertyForSMS(msisdn);
		msisdnfromProp = countryCodeNetworkCodeFromProp+msisdnfromProp;
		String resellerIDfromProp = sms.returnPropertyForSMS(resellerID);
		String curlMsgfromProp = sms.returnPropertyForSMS(curlMsg);		
		String pinfromProp = sms.returnPropertyForSMS(pin);
		String componentfromProp =sms.returnPropertyForSMS(component);
		String loglinesfromProp = sms.returnPropertyForSMS(loglines);
		
		log.info("msisdn:" +msisdnfromProp);
		log.info("resellerID" +resellerIDfromProp);	
		log.info("curlMsg:" +curlMsgfromProp);
		log.info("pin" +pinfromProp);	
		log.info("component:" +componentfromProp);
		log.info("loglines" +loglinesfromProp);	
				
		
		
    	String generateBasicCurl = common.returnCurlForSMS();
    	String fullCurl = generateBasicCurl+"&from="+msisdnfromProp+"&msg="+curlMsgfromProp+"+"+pinfromProp;
    	log.info(fullCurl+"\" \n");
    	
    	/*****************Reseller Balance Fetched before transaction****************/
    	log.info("*****************Reseller Balance Fetched before transaction****************");
    	log.info("CheckResellerBalance.java >> resellerID: "+resellerIDfromProp);
    	
    	double current_amount=resultSet.returnResellerAmount(resellerIDfromProp);
    	String current_amount_string=Double.toString(current_amount);
        log.info("Reseller current balance before transaction: "+current_amount);        	
        
        /*****************Create SSH Connection and execute the command****************/
        log.info("===========>>>>Create SSH Connection");
        common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(fullCurl+ "\" \n");
        common.createChannelToExtractAmount();
        
        common.SleepForSomeTime(5000);
        
        /*****************Create SSH Connection and execute the command for log command****************/
        log.info("===========>>>>Create SSH Connection");
        String logreading = common.returnCommandForLogReading(loglinesfromProp, componentfromProp);
        common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(logreading);
        common.createChannel();
        
        
        /*****************Transaction/Amount Checking****************/
        
		String[] getValueFromResult = common.Result.split(" ");
		String[] splittedValue = getValueFromResult[21].split(",");
		String getBalancefromLogs = splittedValue[0]+splittedValue[1];

		Assert.assertEquals(getBalancefromLogs,current_amount_string+".00");
		log.info("Balance from Logs:"+getBalancefromLogs);
		log.info("Balance from Accounts Database:"+current_amount_string+".00");
		if (getBalancefromLogs.equals(current_amount_string+".00")) {
			log.info("\nTransaction is Successfull ");
			log.info("\nBalance is verified ");
			log.info("Reseller Balance After Successfull Transaction "+ resultSet.returnResellerAmount(resellerID));
		} else {
			log.error("\nTransaction is Not Successfull ");
			log.error("\nBalance is not verified ");
		}
		log.info("\n" + common.Result);
		common.closeSSHConnection();
	}
}