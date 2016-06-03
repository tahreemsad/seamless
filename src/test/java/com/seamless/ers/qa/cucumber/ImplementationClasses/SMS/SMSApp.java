package com.seamless.ers.qa.cucumber.ImplementationClasses.SMS;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.seamless.ers.qa.cucumber.ImplementationClasses.Common.CucumberCommon;
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadConfigFromProperties;
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadSMSAppProperties;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SMSApp {

	CucumberCommon common = new CucumberCommon();
	public static ReadSMSAppProperties sms = new ReadSMSAppProperties();
	ReadConfigFromProperties readConfig = new ReadConfigFromProperties();
	private static Logger log = LogManager.getLogger(CucumberCommon.class);
	private String countryCodeNetworkCodeFromProp;


	public String newSubscriber;
	String MessageFromPro, NotificationMessageFromProp, LogComponenetFromProp, LogLinesFromProp;
	
	@When("^I register a new subscriber using smsapp msg is (.+) log notification message is (.+) and component is (.+) and number of log lines are (.+)$")
	public void performP2Pregistration(String msg, String notificationMessage, String logComponenet, String loglines) throws JSchException, IOException, InterruptedException{
    	
 		MessageFromPro = sms.returnPropertyForSMS(msg);
		NotificationMessageFromProp = sms.returnPropertyForSMS(notificationMessage);
		LogComponenetFromProp = sms.returnPropertyForSMS(logComponenet);
		LogLinesFromProp = sms.returnPropertyForSMS(loglines);
		
		log.info("MessageFromPro: "+MessageFromPro);
		log.info("NotificationMessageFromProp: "+NotificationMessageFromProp);
		log.info("LogComponenetFromProp: "+LogComponenetFromProp);
		log.info("LogLinesFromProp: "+LogLinesFromProp);
		
		log.info("===========>>>>>>PerformP2PRegistrationThroughSMS<<<<<<===========");
		newSubscriber =  common.createNewSubscriber();
		
		String returnCurl = common.returnCurlForSMS();
		String fullCurl = returnCurl+"&from="+newSubscriber+"&msg="+MessageFromPro;
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
        
        boolean state = common.Result.contains(NotificationMessageFromProp);
        
        log.info("common.Result: "+common.Result);
        log.info("notificationMessage: "+NotificationMessageFromProp);
        
        Assert.assertTrue(state);
        
        if(state == true){
        	log.info("Notification message verified from logs");
        }else{
        	log.error("Error validating the notfication message");
        }
        
        log.info("common.Result: "+common.Result);
        log.info("notoficationMessage:"+notificationMessage);
	}
	
	@When("^I register a new subscriber (.+) using smsapp msg is (.+) log notification message is (.+) and component is (.+) and number of log lines are (.+)$")
	public void performP2PregistrationWithAlreadyRegisteredMSISDN(String msisdn, String msg, String notificationMessage, String logComponenet, String loglines) throws JSchException, IOException, InterruptedException{
		log.info("===========>>>>>>PerformP2PRegistrationThroughSMS<<<<<<===========");
		
		String returnCurl = common.returnCurlForSMS();
		String fullCurl = returnCurl+"&from="+msisdn+"&msg="+msg;
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
        String logData = common.returnCommandForLogReading(loglines, logComponenet);
        common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(logData);
        common.createChannel();
        log.info("common.Result..."+common.Result);
        common.closeSSHConnection();
        
        boolean state = common.Result.contains(notificationMessage);
        
        Assert.assertTrue(state);
        
        if(state == true){
        	log.info("Notification message verified from logs");
        }else{
        	log.error("Error validating the notfication message");
        }
        
        log.info("common.Result: "+common.Result);
        log.info("NotoficationMessage: "+notificationMessage);
	}
	
	public void perfromTransferThroughSMSChannel(String msg, String amount, String receiver, String password, String notificationMessage, String logcommponenet, String loglines) throws JSchException, IOException, InterruptedException{
		log.info("===========>>>>>>PerformP2PRegistrationThroughSMS<<<<<<===========");
		
		String returnCurl = common.returnCurlForSMS();
		String fullCurl = returnCurl+"&from="+newSubscriber+"&msg="+msg+"+"+amount+"+"+receiver+"+"+password;
		log.info(fullCurl+'"');
		
        /*****************Create SSH Connection and execute the curl command****************/
        common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(fullCurl + "\" \n");
        common.createChannel();
        log.info("common.Result..."+common.Result);
        common.closeSSHConnection();
        
        common.SleepForSomeTime(5000);
        
		/*****************Create SSH Connection and execute the log command****************/
        log.info("===========>>>>Create SSH Connection for log command");
        String logData = common.returnCommandForLogReading(loglines, logcommponenet);
        common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(logData);
        common.createChannel();
        log.info("common.Result..."+common.Result);
        common.closeSSHConnection();
        
        boolean state = common.Result.contains(notificationMessage);
        
        Assert.assertTrue(state);
        
        if(state == true){
        	log.info("Notification message verified from logs");
        }else{
        	log.error("Error validating the notfication message");
        }
        
        log.info("common.Result: "+common.Result);
        log.info("");
	}
	
	public void chanePinForSubscriber(String msg, String oldPassword, String newPassword, String confirmPassword, String notificationMessage, String logcommponenet, String loglines) throws JSchException, IOException, InterruptedException{
		log.info("===========>>>>>>PerformChangePinThroughSMS<<<<<<===========");
		
		String returnCurl = common.returnCurlForSMS();
		String fullCurl = returnCurl+"&from="+newSubscriber+"&msg="+msg+"+"+oldPassword+"+"+newPassword+"+"+confirmPassword;
		log.info(fullCurl+'"');
		
        /*****************Create SSH Connection and execute the curl command****************/
        common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(fullCurl + "\" \n");
        common.createChannel();
        log.info("common.Result..."+common.Result);
        common.closeSSHConnection();
        
        common.SleepForSomeTime(5000);
        
		/*****************Create SSH Connection and execute the log command****************/
        log.info("===========>>>>Create SSH Connection for log command");
        String logData = common.returnCommandForLogReading(loglines, logcommponenet);
        common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(logData);
        common.createChannel();
        log.info("common.Result..."+common.Result);
        common.closeSSHConnection();
        
        boolean state = common.Result.contains(notificationMessage);
        
        Assert.assertTrue(state);
        
        if(state == true){
        	log.info("Notification message verified from logs");
        }else{
        	log.error("Error validating the notfication message");
        }
        
        log.info("common.Result: "+common.Result);
        log.info("");
	}
	
	@Given("^User performs the change pin using msg (.+) having number (.+) and old password is (.+) and new password is (.+) and confirm password is (.+) and notification message is (.+) and component is (.+) and log lines are (.+)$")
	public void chanePinForReseller(String msg, String msisdn, String oldPassword, String newPassword, String confirmPassword, String notificationMessage, String logcommponenet, String loglines) throws JSchException, IOException, InterruptedException{

		String ResellerMessageFromProp = sms.returnPropertyForSMS(msg);
		String ResellerMSISDNFromProp = sms.returnPropertyForSMS(msisdn);
		ResellerMSISDNFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE")+ResellerMSISDNFromProp;
		String ResellerOldPwdFromProp = sms.returnPropertyForSMS(oldPassword); 
		String ResellerNewPwdFromProp = sms.returnPropertyForSMS(newPassword);
		String ResellerConfirmPwdFromProp = sms.returnPropertyForSMS(confirmPassword);
		String NotificationMsgFromProp = sms.returnPropertyForSMS(notificationMessage);
		String LogComponentFromProp = sms.returnPropertyForSMS(logcommponenet);
		String LogLinesFromProp = sms.returnPropertyForSMS(loglines);
		
		log.info("ResellerMessageFromProp: "+ResellerMessageFromProp);
		log.info("ResellerMSISDNFromProp: "+ResellerMSISDNFromProp);
		log.info("ResellerOldPwdFromProp: "+ResellerOldPwdFromProp);
		log.info("ResellerNewPwdFromProp: "+ResellerNewPwdFromProp);
		log.info("ResellerConfirmPwdFromProp: "+ResellerConfirmPwdFromProp);
		log.info("NotificationMsgFromProp: "+NotificationMsgFromProp);
		log.info("LogComponentFromProp: "+LogComponentFromProp);
		log.info("LogLinesFromProp: "+LogLinesFromProp);
		
		
		log.info("===========>>>>>>PerformChangePinThroughSMForResellee<<<<<<===========");
		
		String returnCurl = common.returnCurlForSMS();
		String fullCurl = returnCurl+"&from="+ResellerMSISDNFromProp+"&msg="+ResellerMessageFromProp+"+"+ResellerOldPwdFromProp+"+"+ResellerNewPwdFromProp+"+"+ResellerConfirmPwdFromProp;
		log.info(fullCurl+'"');
		
        /*****************Create SSH Connection and execute the curl command****************/
        common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(fullCurl + "\" \n");
        common.createChannel();
        log.info("common.Result..."+common.Result);
        common.closeSSHConnection();
        
        common.SleepForSomeTime(5000);
        
		/*****************Create SSH Connection and execute the log command****************/
        log.info("===========>>>>Create SSH Connection for log command");
        String logData = common.returnCommandForLogReading(LogLinesFromProp, LogComponentFromProp);
        common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(logData);
        common.createChannel();
        log.info("common.Result..."+common.Result);
        common.closeSSHConnection();
        
        boolean state = common.Result.contains(NotificationMsgFromProp);
        
        Assert.assertTrue(state);
        
        if(state == true){
        	log.info("Notification message verified from logs");
        }else{
        	log.error("Error validating the notfication message");
        }
        
        log.info("common.Result: "+common.Result);
        log.info("");
	}
	
	@Then("^I perform p2p using msg (.+) amunt is (.+) and receiver is (.+) and password is (.+) log notification message is (.+) and component is (.+) and numebr of log lines are (.+)$")
	public void performP2PTransferthroughSMSChannel(String msg, String amount, String receiver, String password, String notificationMessage, String logcomponent, String loglines) throws JSchException, IOException, InterruptedException{
		
		countryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE"); 	
		String MessageFromProp = sms.returnPropertyForSMS(msg);
		String AmountFromProp = sms.returnPropertyForSMS(amount);
		String ReceiverFromProp = sms.returnPropertyForSMS(receiver);
		ReceiverFromProp = countryCodeNetworkCodeFromProp+ReceiverFromProp;
		String PwdFromProp = sms.returnPropertyForSMS(password);
		String NotificationMsgFromProp = sms.returnPropertyForSMS(notificationMessage);
		String LogComponentFromProp = sms.returnPropertyForSMS(logcomponent);
		String LogLinesFromProp = sms.returnPropertyForSMS(loglines);
		
		log.info("MessageFromProp: "+MessageFromProp);
		log.info("AmountFromProp: "+AmountFromProp);
		log.info("ReceiverFromProp: "+ReceiverFromProp);
		log.info("PwdFromProp: "+PwdFromProp);
		log.info("NotificationMsgFromProp: "+NotificationMsgFromProp);
		log.info("LogComponentFromProp: "+LogComponentFromProp);
		log.info("LogLinesFromProp: "+LogLinesFromProp);
		
		perfromTransferThroughSMSChannel(MessageFromProp, AmountFromProp, ReceiverFromProp, PwdFromProp, NotificationMsgFromProp, LogComponentFromProp, LogLinesFromProp);
	}
	
	@Then("^I perform the change pin using msg (.+) and old password is (.+) and new password is (.+) and confirm password is (.+) and notification message is (.+) and component is (.+) and log lines are (.+)$")
	public void performPasswordChangeFromSMSChannel(String msg, String oldpassword, String newpassword, String confirmpassword, String notification, String component, String loglines) throws JSchException, IOException, InterruptedException{
		
		String MessageFromProp = sms.returnPropertyForSMS(msg);
		String OldPwdFromProp = sms.returnPropertyForSMS(oldpassword);
		String NewPwdFromProp = sms.returnPropertyForSMS(newpassword);
		String ConfirmPwdFromProp = sms.returnPropertyForSMS(confirmpassword);
		String NotificationFromProp = sms.returnPropertyForSMS(notification);
		String ComponentFromProp = sms.returnPropertyForSMS(component);
		String LogLinesFromProp = sms.returnPropertyForSMS(loglines);
		
		log.info("MessageFromProp: "+MessageFromProp);
		log.info("OldPwdFromProp: "+OldPwdFromProp);
		log.info("NewPwdFromProp: "+NewPwdFromProp);
		log.info("ConfirmPwdFromProp: "+ConfirmPwdFromProp);
		log.info("NotificationFromProp: "+NotificationFromProp);
		log.info("ComponentFromProp: "+ComponentFromProp);
		log.info("LogLinesFromProp: "+LogLinesFromProp);
		
		chanePinForSubscriber(MessageFromProp, OldPwdFromProp, NewPwdFromProp, ConfirmPwdFromProp, NotificationFromProp, ComponentFromProp, LogLinesFromProp);
	}
}