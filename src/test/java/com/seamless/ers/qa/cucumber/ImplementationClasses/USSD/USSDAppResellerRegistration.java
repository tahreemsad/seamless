package com.seamless.ers.qa.cucumber.ImplementationClasses.USSD;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.seamless.ers.qa.cucumber.ImplementationClasses.Common.CucumberCommon;
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadUssdAppConfigFromProperties;
import com.seamless.ers.qa.cucumber.SQLQueries.SQLResultSets;

import cucumber.api.java.en.Given;

public class USSDAppResellerRegistration {
	
	CucumberCommon common = new CucumberCommon();
	ReadUssdAppConfigFromProperties readUssd = ReadUssdAppConfigFromProperties.getInstance();
	SQLResultSets resultSet = new SQLResultSets();
	private static Logger log = LogManager.getLogger(USSDAppResellerRegistration.class);
	String curl;
	
	@Given("^Reseller register himself for the first time by hitting the shortcode (.+) and reseller menu was (.+) and reseller pin was (.+) and I get the reseller State (.+) from Database and then I see the message on ussdapp (.+)$")
	public void registerReseller(String shortcode, String registerOption, String pin, String resellerstate, String message) throws IOException, NumberFormatException, SQLException, ParseException, JSchException{
		
		String shortcodeFromProp=readUssd.returnValueForUssd(shortcode);
		String registerOptionFromProp=readUssd.returnValueForUssd(registerOption);
		String resellerPinFromProp = readUssd.returnValueForUssd(pin);
		String messageFromProp=readUssd.returnValueForUssd(message);
		String stateBitFromProp = readUssd.returnValueForUssd(resellerstate);
		
		log.info("shortcodeFromProp "+shortcodeFromProp);
		log.info("regsiterOptionFromProp "+registerOptionFromProp);
		log.info("messageFromProp "+messageFromProp);
		log.info("stateBitFromProp "+stateBitFromProp);
		log.info("resellerPinFromProp "+resellerPinFromProp);
		
		String readyForActivationMSISDN = resultSet.returnRFATerminal(Integer.parseInt(stateBitFromProp));
		String activationCode = resultSet.returnActivationCode(readyForActivationMSISDN);
		
		String fullCurl = common.returnFullCurl(readyForActivationMSISDN, shortcodeFromProp);
		
		if (System.getProperty("customer").equals("ci_mtn")) {
			curl = fullCurl + "&p1="+registerOptionFromProp+"&p2=1&p3="+activationCode+"&p4="+resellerPinFromProp;
			log.info("R2R.java: " + curl + '"');
		}
    			
		common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(curl + "\" \n");
        common.createChannel();
        
        boolean state = common.Result.contains(messageFromProp);
        log.info(common.Result.contains(messageFromProp));
        
        //Assert.assertEquals(messageFromProp, common.Result);
        Assert.assertTrue(state);
        if(state == true){
        	log.info("Reseller is activated successfully successfully");
        }else{
        	log.error("Reseller activation failed");
        }
	}
}
