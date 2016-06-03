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
import com.seamless.ers.qa.cucumber.SQLQueries.SQLResultSets;

import cucumber.api.java.en.And;

public class USSDAppCallMeBack {
		
		ReadConfigFromProperties readConfig = new ReadConfigFromProperties();
		ReadUssdAppConfigFromProperties readUssd = ReadUssdAppConfigFromProperties.getInstance();

		private static Logger log = LogManager.getLogger(USSDAppSubscriberRegistration.class);
		CucumberCommon CMBKcommon = new CucumberCommon();
		CucumberCommon common = new CucumberCommon();
		SQLResultSets resultSet = new SQLResultSets();
		String SenderSubscriberMsisdn,countryCodeNetworkCodeFromProp;
			
			@And("^I send CMBK request using shortcode (.+) to the subscriber (.+) and the USSD response should be (.+) with log component (.+) and loglines are (.+)$")
			public void subscriberRegistration(String shortcode,String RecieverMSISDN, String message, String logComponenet, String loglines) throws IOException, JSchException, InterruptedException{
				countryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");

				String SubscriberCMBKShortCodeFromProp = readUssd.returnValueForUssd(shortcode);
				SenderSubscriberMsisdn = CMBKcommon.createNewSubscriber();
				String RecieverSubscriberFromProp = readUssd.returnValueForUssd(RecieverMSISDN);
				RecieverSubscriberFromProp = countryCodeNetworkCodeFromProp+RecieverSubscriberFromProp;
				String CMBKRequestSent = readUssd.returnValueForUssd(message);
				String LogComponenetFromProp = readUssd.returnValueForUssd(logComponenet);
				String LogLinesFromProp = readUssd.returnValueForUssd(loglines);
				
				log.info("SubscriberMenuSortCodeFromProp: "+SubscriberCMBKShortCodeFromProp);
				log.info("SenderSubscriberMsisdnFromProp: "+SenderSubscriberMsisdn);
				log.info("SenderSubscriberMenuOptionFromProp: "+RecieverSubscriberFromProp);

				String fullCurl = CMBKcommon.returnFullCurl(SenderSubscriberMsisdn, SubscriberCMBKShortCodeFromProp);
				String curl = fullCurl + "&p1=" + RecieverSubscriberFromProp;
				log.info("CMBK request curl: " + curl + '"');
				
				/***************** Create SSH Connection and execute the command ****************/
				log.info("===========>>>>Create SSH Connection");
				CMBKcommon.createSSHCoonection();
				((ChannelExec) CMBKcommon.channel).setCommand(curl + "\" \n");
				CMBKcommon.SleepForSomeTime(5000);
				CMBKcommon.createChannel();
				
				/***************** Verification of USSD response ****************/
				
				boolean state = CMBKcommon.Result.contains(CMBKRequestSent);
				log.info(CMBKcommon.Result.contains(CMBKRequestSent));
				log.info("CMBKcommon.Result: " + CMBKcommon.Result);
				log.info(CMBKRequestSent + "..." + state);
				Assert.assertTrue(state);
				try{
					if(state==true){
						Assert.assertTrue(CMBKRequestSent, state);
						log.info("USSD Response Verified: "+CMBKRequestSent);
					}else{
						Assert.assertFalse(CMBKRequestSent, state);
						log.error("USSD Response not verified");
						log.error("USSD Response fetched: "+CMBKcommon.Result+" \n USSD Response expected: "+CMBKRequestSent);
						return;
					}
				}
				finally{
					CMBKcommon.closeSSHConnection();
		}
                if(CMBKRequestSent.contains("CMBK request successful")) {

				/*****************Create SSH Connection and execute the log command****************/
				
		        log.info("===========>>>>Create SSH Connection for log command");
		        String logData = common.returnCommandForLogReading(LogLinesFromProp,LogComponenetFromProp);
		        
		        common.createSSHCoonection();
		        ((ChannelExec) common.channel).setCommand(logData);
		        common.createChannel();

		        boolean Logstate = common.Result.contains("Please call:");
				log.info("common.Result: " + common.Result);
				
		        Assert.assertTrue(Logstate);
				try{
					if(Logstate==true){
						Assert.assertTrue(CMBKRequestSent, Logstate);
						log.info("Kannel message Verified");
					}else{
						Assert.assertFalse(CMBKRequestSent, Logstate);
						log.error("Kannel message not verified");
						log.error("Kannel message fetched: "+common.Result);
						return;
					}
				}finally{
					common.closeSSHConnection();
				}
		}
	}
}
	

