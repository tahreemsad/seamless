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
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadConfigFromProperties;
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadUssdAppConfigFromProperties;
import com.seamless.ers.qa.cucumber.SQLQueries.SQLResultSets;

import cucumber.api.java.en.When;

public class USSDAppRequestActivationCode {

	ReadUssdAppConfigFromProperties readussd = ReadUssdAppConfigFromProperties.getInstance();
	ReadConfigFromProperties readConfig = new ReadConfigFromProperties();
	SQLResultSets resultset = new SQLResultSets();
	public static Logger log = LogManager.getLogger( USSDAppRequestActivationCode.class );
	CucumberCommon common = new CucumberCommon();
	
	private String countryCodeNetworkCodeFromProp, curl;
			
	@When("^Reseller (.+) having reseller msisdn (.+) request for the activation code he recieves the activation code and then I verify the activation code by hitting the short code (.+) and in response I received the message (.+)$")
	public void requestActivationCode(String resellerId, String msisdn, String shortcode, String message) throws IOException, JSchException, SQLException, ParseException{
		
		log.info("=======================>>>>>>>>>>>>Perform String Base R2R<<<<<<<<<<<=====================");
		
		countryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");
		
		String resellerIdFromProp = readussd.returnValueForUssd(resellerId);
		String msisnFromProp = readussd.returnValueForUssd(msisdn);
		String countrycode_msisdn = countryCodeNetworkCodeFromProp+msisnFromProp;
		String shortcodeFromProp = readussd.returnValueForUssd(shortcode);
		String messageFromProp = readussd.returnValueForUssd(message);
		
		String activationCodeFromSQLResult = resultset.returnActivationCode(countrycode_msisdn);
		String completeSuccessMessage = messageFromProp+" "+activationCodeFromSQLResult;
		
		log.info("resellerIdFromProp: "+resellerIdFromProp);
		log.info("resellerMSISDNFromProp: "+countrycode_msisdn);
		log.info("msisnFromProp: "+msisnFromProp);
		log.info("shortcodeFromProp: "+shortcodeFromProp);
		log.info("messageFromProp: "+completeSuccessMessage);
		
		String fullCurl = common.returnFullCurl(countrycode_msisdn, shortcodeFromProp);

		if (System.getProperty("customer").equals("ci_mtn")) {
			curl = fullCurl + "&p1=1&p2=2";
			log.info("R2R.java: " + curl + '"');
		}
    			
		common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(curl + "\" \n");
        common.createChannel();
        
        boolean state = common.Result.contains(completeSuccessMessage);
        log.info(common.Result.contains(completeSuccessMessage));
        
        Assert.assertEquals(completeSuccessMessage, common.Result);
        Assert.assertTrue(state);
        if(state == true){
        	log.info("Reseller Activation is passed successfully");
        }else{
        	log.error("Reseller activation failed");
        }
        
	}
}