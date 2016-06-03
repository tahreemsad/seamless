package com.seamless.ers.qa.cucumber.ImplementationClasses.USSD;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.seamless.ers.qa.cucumber.ImplementationClasses.Common.CucumberCommon;
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadConfigFromProperties;
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadUssdAppConfigFromProperties;
import com.seamless.ers.qa.cucumber.SQLQueries.SQLResultSets;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class USSDAppFAF {
	ReadConfigFromProperties readConfig = new ReadConfigFromProperties();
	ReadUssdAppConfigFromProperties readUssd = ReadUssdAppConfigFromProperties.getInstance();
	public String SenderSubscriberMsisdn2;
	public String msisdn;

	public static Logger log = LogManager.getLogger(USSDAppFAF.class);
	SQLResultSets resultSet = new SQLResultSets();
	CucumberCommon common = new CucumberCommon();
	private String countryCodeNetworkCodeFromProp;
	private ArrayList<String> fafList;

	@When("^I hit shortcode (.+) from subscriber (.+) and request (.+) to add FAF number to my list and ussd response is (.+) and component is (.+) and number of log lines are (.+) and message in logs (.+)$")
	public void addFafNumber(String shortcode, String SenderSubscriberMsisdn1,
			String menuOption, String message, String logComponenet, String loglines, String AddConfirmationFromLogs) throws IOException,
			JSchException, InterruptedException {

		countryCodeNetworkCodeFromProp = readConfig
				.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");

		String SubscriberMenuShortCodeFromProp = readUssd
				.returnValueForUssd(shortcode);
		SenderSubscriberMsisdn2 = common.createNewSubscriber();
		msisdn = SenderSubscriberMsisdn2;
		String SenderSubscriberMsisdn1FromProp = readUssd
				.returnValueForUssd(SenderSubscriberMsisdn1);
		SenderSubscriberMsisdn1FromProp = countryCodeNetworkCodeFromProp
				+ SenderSubscriberMsisdn1FromProp;
		String SenderSubscriberMenuOptionFromProp = readUssd
				.returnValueForUssd(menuOption);
		String LogComponenetFromProp = readUssd
				.returnValueForUssd(logComponenet);
		String LogLinesFromProp = readUssd.returnValueForUssd(loglines);
		String AddConfirmationFromLogsFromProp = readUssd
				.returnValueForUssd(AddConfirmationFromLogs);
		String SubscriberNumberAdded = readUssd.returnValueForUssd(message);

		log.info("SubscriberMenuSortCodeFromProp: "
				+ SubscriberMenuShortCodeFromProp);
		log.info("SenderSubscriberMsisdn2: " + msisdn);
		log.info("SenderSubscriberMenuOptionFromProp: "
				+ SenderSubscriberMenuOptionFromProp);
		log.info("SubscriberNumberAdded: " + SubscriberNumberAdded);

		String fullCurl = common
				.returnFullCurl(SenderSubscriberMsisdn1FromProp,
						SubscriberMenuShortCodeFromProp);
		String curl = fullCurl + "&p1=" + SenderSubscriberMenuOptionFromProp
				+ "&p2=" + msisdn;
		log.info("FAF.java: " + curl + '"');

		/***************** Create SSH Connection and execute the command ****************/
		log.info("===========>>>>Create SSH Connection");
		common.createSSHCoonection();
		((ChannelExec) common.channel).setCommand(curl + "\" \n");
		common.SleepForSomeTime(5000);
		common.createChannel();

		/***************** Verification ****************/

		boolean state = common.Result.contains(SubscriberNumberAdded);
		log.info(common.Result.contains(SubscriberNumberAdded));
		log.info("common.Result: " + common.Result);
		log.info(SubscriberNumberAdded + "..." + state);
		Assert.assertTrue(state);
		try {
			if (state == true) {
				Assert.assertTrue(SubscriberNumberAdded, state);
				log.info("USSD Response Verified: " + SubscriberNumberAdded);
			} else {
				Assert.assertFalse(SubscriberNumberAdded, state);
				log.error("USSD Response not verified");
				log.error("USSD Response fetched: " + common.Result
						+ " \n USSD Response expected: "
						+ SubscriberNumberAdded);
				return;
			}
			
			/***************** Create SSH Connection and execute the log command ****************/
			log.info("===========>>>>Create SSH Connection for log command");
			String logMessage = common.returnCommandForLogReading(LogLinesFromProp,
					LogComponenetFromProp);
			common.createSSHCoonection();
			((ChannelExec) common.channel).setCommand(logMessage);
			common.createChannel();

			boolean removalstate = common.Result.contains(AddConfirmationFromLogsFromProp);
			log.info(common.Result.contains(AddConfirmationFromLogsFromProp));
			log.info(AddConfirmationFromLogsFromProp + "..." + removalstate);

		} finally {

			common.closeSSHConnection();
		}
	}

	@Then("^I request from subscriber (.+) shortcode (.+) and request (.+) ussd response is (.+)$")
	public void viewFafList(String SenderSubscriberMsisdn1, String shortcode,
			String menuOption, String message) throws IOException,
			JSchException, InterruptedException {

		countryCodeNetworkCodeFromProp = readConfig
				.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");

		String SubscriberMenuSortCodeFromProp = readUssd
				.returnValueForUssd(shortcode);
		String SenderSubscriberMsisdn1FromProp = readUssd
				.returnValueForUssd(SenderSubscriberMsisdn1);
		SenderSubscriberMsisdn1FromProp = countryCodeNetworkCodeFromProp
				+ SenderSubscriberMsisdn1FromProp;
		String SenderSubscriberMenuOptionFromProp = readUssd
				.returnValueForUssd(menuOption);
		String SubscriberList = readUssd.returnValueForUssd(message);

		log.info("SenderSubscriberMsisdn1FromProp: "
				+ SenderSubscriberMsisdn1FromProp);
		log.info("SubscriberMenuSortCodeFromProp: "
				+ SubscriberMenuSortCodeFromProp);
		log.info("SenderSubscriberMenuOptionFromProp: "
				+ SenderSubscriberMenuOptionFromProp);
		log.info("SubscriberRegistered: " + SubscriberList);

		String fullCurl2 = common
				.returnFullCurl(SenderSubscriberMsisdn1FromProp,
						SubscriberMenuSortCodeFromProp);
		String curl2 = fullCurl2 + "&p1=" + SenderSubscriberMenuOptionFromProp;
		log.info("FAF.java: " + curl2 + '"');

		/***************** Create SSH Connection and execute the command ****************/
		log.info("===========>>>>Create SSH Connection");
		common.createSSHCoonection();
		((ChannelExec) common.channel).setCommand(curl2 + "\" \n");
		common.SleepForSomeTime(5000);
		common.createChannel();

		/***************** Verification ****************/

		try {
			fafList = resultSet.viewFafList(SenderSubscriberMsisdn1FromProp);

			for (String s : fafList) {
				boolean state = common.Result.contains(s);
				Assert.assertTrue(state);
				if (state == true) {
					log.info(s + " Found \n");
				} else {
					log.error(s + " Not Found \n");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		log.info("FAF List\n" + common.Result);
		common.closeSSHConnection();

		boolean state = common.Result.contains(SubscriberList);
		log.info(common.Result.contains(SubscriberList));
		log.info("common.Result: " + common.Result);
		log.info(SubscriberList + "..." + state);
		Assert.assertTrue(state);
		try {
			if (state == true) {
				Assert.assertTrue(SubscriberList, state);
				log.info("USSD Response Verified: " + SubscriberList);
			} else {
				Assert.assertFalse(SubscriberList, state);
				log.error("USSD Response not verified");
				log.error("USSD Response fetched: " + common.Result
						+ " \n USSD Response expected: " + SubscriberList);
				return;
			}
		} finally {

			common.closeSSHConnection();
		}
	}

	@When("^I request shortcode (.+) from subscriber (.+) and request (.+) to add FAF number to my list and ussd response is (.+) and component is (.+) and number of log lines are (.+) and message in logs (.+)$")
	public void removeFafNumber(String shortcode,
			String SenderSubscriberMsisdn1, String menuOption, String message,
			String logComponenet, String loglines, String RemovalConfirmationFromLogs) throws IOException,
			JSchException, InterruptedException {
		countryCodeNetworkCodeFromProp = readConfig
				.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");

		String SubscriberMenuSortCodeFromProp = readUssd
				.returnValueForUssd(shortcode);
		String SenderSubscriberMsisdn1FromProp = readUssd
				.returnValueForUssd(SenderSubscriberMsisdn1);
		SenderSubscriberMsisdn1FromProp = countryCodeNetworkCodeFromProp
				+ SenderSubscriberMsisdn1FromProp;
		String SenderSubscriberMenuOptionFromProp = readUssd
				.returnValueForUssd(menuOption);
		String SubscriberNumberRemoved = readUssd.returnValueForUssd(message);
		String LogComponenetFromProp = readUssd
				.returnValueForUssd(logComponenet);
		String LogLinesFromProp = readUssd.returnValueForUssd(loglines);
		String RemovalConfirmationFromProp = readUssd.returnValueForUssd(RemovalConfirmationFromLogs);
		
		
		log.info("SubscriberMenuSortCodeFromProp: "
				+ SubscriberMenuSortCodeFromProp);
		log.info("SenderSubscriberMenuOptionFromProp: "
				+ SenderSubscriberMenuOptionFromProp);
		log.info("SubscriberNumberAdded: " + SubscriberNumberRemoved);

		/***************** Create SSH Connection and execute the log command ****************/
		log.info("===========>>>>Create SSH Connection for log command");
		String logData = common.returnCommandForLogReading(LogLinesFromProp,
				LogComponenetFromProp);
		common.createSSHCoonection();
		((ChannelExec) common.channel).setCommand(logData);
		common.createChannel();
		
		log.info(" Result :" + common.Result);
		String[] splitTheResultValue = common.Result.split("\\s+");
		String[] msisdns = splitTheResultValue[splitTheResultValue.length - 1].split(",");
		log.info("msisdns : " + Arrays.toString(msisdns));
		String msisdn = msisdns[0].toString();
		log.info("msisdn: " + msisdn);
		log.info("Result: " + common.Result);

		String fullCurl1 = common
				.returnFullCurl(SenderSubscriberMsisdn1FromProp,
						SubscriberMenuSortCodeFromProp);
		String curl1 = fullCurl1 + "&p1=" + SenderSubscriberMenuOptionFromProp
				+ "&p2=" + msisdn;
		log.info("FAF.java: " + curl1 + '"');

		/***************** Create SSH Connection and execute the command ****************/
		log.info("===========>>>>Create SSH Connection");
		common.createSSHCoonection();
		((ChannelExec) common.channel).setCommand(curl1 + "\" \n");
		common.SleepForSomeTime(5000);
		common.createChannel();

		/***************** Verification ****************/

		boolean state = common.Result.contains(SubscriberNumberRemoved);
		log.info(common.Result.contains(SubscriberNumberRemoved));
		log.info("common.Result: " + common.Result);
		log.info(SubscriberNumberRemoved + "..." + state);
		Assert.assertTrue(state);
		try {
			if (state == true) {
				Assert.assertTrue(SubscriberNumberRemoved, state);
				log.info("USSD Response Verified: " + SubscriberNumberRemoved);
			} else {
				Assert.assertFalse(SubscriberNumberRemoved, state);
				log.error("USSD Response not verified");
				log.error("USSD Response fetched: " + common.Result
						+ " \n USSD Response expected: "
						+ SubscriberNumberRemoved);
				return;
			}	
			
			/***************** Create SSH Connection and execute the log command ****************/
			log.info("===========>>>>Create SSH Connection for log command");
			String logMessage = common.returnCommandForLogReading(LogLinesFromProp,
					LogComponenetFromProp);
			common.createSSHCoonection();
			((ChannelExec) common.channel).setCommand(logMessage);
			common.createChannel();

			boolean removalstate = common.Result.contains(RemovalConfirmationFromProp);
			log.info(common.Result.contains(RemovalConfirmationFromProp));
			log.info(RemovalConfirmationFromProp + "..." + removalstate);

			} finally {
			common.closeSSHConnection();
		}
	}
}
