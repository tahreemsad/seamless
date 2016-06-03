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

import cucumber.api.java.en.Given;

public class USSDAppViewMenu {

	ReadConfigFromProperties readConfig = new ReadConfigFromProperties();
	ReadUssdAppConfigFromProperties readUssd = ReadUssdAppConfigFromProperties.getInstance();
	private static Logger log = LogManager.getLogger(USSDAppViewMenu.class);
	CucumberCommon common = new CucumberCommon();
	private String countryCodeNetworkCodeFromProp;
	private String curl;
	private String matches[];
	
	/************************ 
	 * Show Full USSD Menu 
	 * @throws IOException 
	 * *******************************/
	
	@Given("^User performs the USSD from my cell number (.+) by entering the shortcode (.+) and menuOption (.+) to verify the menu (.+)$")
	public void verifyUSSDMenu(String reseller_number_source, String shortcode, String menuOption, String menuList) throws IOException, JSchException {
		
		try {
			countryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");

			String senderMsisdnFromProp = readUssd.returnValueForUssd(reseller_number_source);
			senderMsisdnFromProp = countryCodeNetworkCodeFromProp+senderMsisdnFromProp;
			String ussdShortCodeFromProp = readUssd.returnValueForUssd(shortcode);
			String ussdMenuOptionFromPrp = readUssd.returnValueForUssd(menuOption);
			
			log.info("senderMsisdnFromProp: " + senderMsisdnFromProp);
			log.info("ussdShortCodeFromProp: " + ussdShortCodeFromProp);
			log.info("ussdMenuOptionFromPrp: " + ussdMenuOptionFromPrp);

			String fullCurl = common.returnFullCurl(senderMsisdnFromProp,ussdShortCodeFromProp);
			if (System.getProperty("customer").equals("ers_std") || (System.getProperty("customer").equals("gh_glo")))
			{
				log.info("I am verifying the menu list for "+System.getProperty("customer"));
				if (ussdMenuOptionFromPrp.equals("NO-MENU")) {
					log.info("I am going to verify the USSD Menu");
					curl = fullCurl;
					log.info("ViewUSSDMenu.java: " + curl + '"');
					log.info(" \n " + readUssd.returnValueForUssd(menuList));
					matches = readUssd.readPropertiesForMenuVariablesToSplit(menuList);
					
					} if (ussdMenuOptionFromPrp.equals("7")) {
						log.info("I am going to verify the USSD Reports Menu");
						curl = fullCurl + "&p1=" + ussdMenuOptionFromPrp;
						log.info("ViewUSSDMenu.java: " + curl + '"');
						log.info(" \n " + readUssd.returnValueForUssd(menuList));
						matches = readUssd.readPropertiesForMenuVariablesToSplit(menuList);

					} if (ussdMenuOptionFromPrp.equals("10")) {
						log.info("I am going to verify the USSD Voucher Menu");
						curl = fullCurl + "&p1=" + ussdMenuOptionFromPrp;
						log.info("ViewUSSDMenu.java: " + curl + '"');
						log.info(" \n " + readUssd.returnValueForUssd(menuList));
						matches = readUssd.readPropertiesForMenuVariablesToSplit(menuList);
					}
				}
				else 
						if (System.getProperty("customer").equals("gb_mtn")){
							if (ussdMenuOptionFromPrp.equals("NO-MENU")) {
								log.info("I am going to verify the USSD Menu");
								curl = fullCurl;
								log.info("ViewUSSDMenu.java: " + curl + '"');
								log.info(" \n " + readUssd.returnValueForUssd(menuList));
								matches = readUssd.readPropertiesForMenuVariablesToSplit(menuList);
							
							}
							if (ussdMenuOptionFromPrp.equals("7")) {
									log.info("I am going to verify the USSD Reports Menu");
									curl = fullCurl + "&p1=" + ussdMenuOptionFromPrp;
									log.info("ViewUSSDMenu.java: " + curl + '"');
									log.info(" \n " + readUssd.returnValueForUssd(menuList));
									matches = readUssd.readPropertiesForMenuVariablesToSplit(menuList);
									
								} 
								if (ussdMenuOptionFromPrp.equals("10")) {
									log.info("I am going to verify the USSD Voucher Menu");
									curl = fullCurl + "&p1=" + ussdMenuOptionFromPrp;
									log.info("ViewUSSDMenu.java: " + curl + '"');
									log.info(" \n " + readUssd.returnValueForUssd(menuList));
									matches = readUssd.readPropertiesForMenuVariablesToSplit(menuList);
								}
							}
					else 
					if (System.getProperty("customer").equals("zm_mtn")) {
						if (ussdMenuOptionFromPrp.equals("NO-MENU")) {
							log.info("I am going to verify the USSD Menu");
							curl = fullCurl;
							log.info("ViewUSSDMenu.java: " + curl + '"');
							log.info(" \n " + readUssd.returnValueForUssd(menuList));
							matches = readUssd.readPropertiesForMenuVariablesToSplit(menuList);
						}
						if (ussdMenuOptionFromPrp.equals("5")) {
							log.info("I am going to verify the USSD Reports Menu");
							curl = fullCurl + "&p1=" + ussdMenuOptionFromPrp;
							log.info("ViewUSSDMenu.java: " + curl + '"');
								log.info(" \n "+ readUssd.returnValueForUssd(menuList));
								matches = readUssd.readPropertiesForMenuVariablesToSplit(menuList);
							}
					
							if(ussdMenuOptionFromPrp.equals("2")){
									log.info("I am going to verify the Voucher Menu");
									curl = fullCurl + "&p1=" + ussdMenuOptionFromPrp;
									log.info("ViewUSSDMenu.java: " + curl + '"');
									log.info(" \n "+ readUssd.returnValueForUssd(menuList));
									matches = readUssd.readPropertiesForMenuVariablesToSplit(menuList);
								}
							}
					
					else 
						if (System.getProperty("customer").equals("ng_glo")) {
							if (ussdMenuOptionFromPrp.equals("NO-MENU")) {
								log.info("I am going to verify the USSD Menu");
								curl = fullCurl;
								log.info("ViewUSSDMenu.java: " + curl + '"');
								log.info(" \n " + readUssd.returnValueForUssd(menuList));
								matches = readUssd.readPropertiesForMenuVariablesToSplit(menuList);
							}
							if (ussdMenuOptionFromPrp.equals("5")) {
								log.info("I am going to verify the USSD Reports Menu");
								curl = fullCurl + "&p1=" + ussdMenuOptionFromPrp;
								log.info("ViewUSSDMenu.java: " + curl + '"');
									log.info(" \n "+ readUssd.returnValueForUssd(menuList));
									matches = readUssd.readPropertiesForMenuVariablesToSplit(menuList);
								}
	
								}
						else 
							if (System.getProperty("customer").equals("ci_mtn")) {
								if (ussdMenuOptionFromPrp.equals("NO-MENU")) {
									log.info("I am going to verify the USSD Menu");
									curl = fullCurl;
									log.info("ViewUSSDMenu.java: " + curl + '"');
									log.info(" \n " + readUssd.returnValueForUssd(menuList));
									matches = readUssd.readPropertiesForMenuVariablesToSplit(menuList);
								}else
								if (ussdMenuOptionFromPrp.equals("6")) {
									if(menuList.equals("SHOW_USSD_MENU_REPORTS")){
										log.info("I am going to verify the Reseller Self Care Menu for Reports");
										curl = fullCurl + "&p1=" + ussdMenuOptionFromPrp +"&p2=3";
										log.info("ViewUSSDMenu.java: " + curl + '"');
										log.info(" \n "+ readUssd.returnValueForUssd(menuList));
										matches = readUssd.readPropertiesForMenuVariablesToSplit(menuList);
									}else
										if(menuList.equals("SHOW_USSD_RESELLER_SELFCARE_MENU")){
											log.info("I am going to verify the Reseller Self Care Menu for Reseller Self Care");
											curl = fullCurl + "&p1=" + ussdMenuOptionFromPrp +"&p1=6";
											log.info("ViewUSSDMenu.java: " + curl + '"');
											log.info(" \n "+ readUssd.returnValueForUssd(menuList));
											matches = readUssd.readPropertiesForMenuVariablesToSplit(menuList);
										}
									}
								else
									if (ussdMenuOptionFromPrp.equals("1")) {
										log.info("I am going to verify the Reseller Register Menu");
										curl = fullCurl + "&p1=" + ussdMenuOptionFromPrp;
										log.info("ViewUSSDMenu.java: " + curl + '"');
											log.info(" \n "+ readUssd.returnValueForUssd(menuList));
											matches = readUssd.readPropertiesForMenuVariablesToSplit(menuList);
										}
									}
						else{
								log.info("No customr found to verify the list of menu");
							}

			/***************** Create SSH Connection and execute the command ****************/
			log.info("===========>>>>Create SSH Connection");
			common.createSSHCoonection();
			((ChannelExec) common.channel).setCommand(curl + "\" \n");
            common.createChannel();
            
            for (String s : matches) {
                boolean state =common.Result.contains(s);
                Assert.assertTrue(state);
				if (state == true) {
					log.info(s + " Found \n");
				} else {
					log.error(s + " Not Found \n");
				}
			}
					
			log.info("Menu\n" + common.Result);
			common.closeSSHConnection();
			log.info("\n ================================================");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}