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

public class USSDVoucherOverSms {
	int voucher_stock_after;
	int Stock_Before_Sale;
	int remainingstock;
	int multipleVoucherAmount;
	double remainingBalSingleVoucher;
	int unrevoked_vouchers;
	double remainingBalMultipleVoucher;
	int revoked_vouchers;
	public String fullCurl;
	public String curl;
	public double Delta = 1e-8;
	
    ReadConfigFromProperties readConfg = new ReadConfigFromProperties();
    ReadUssdAppConfigFromProperties readUssd = ReadUssdAppConfigFromProperties.getInstance();

	public static Logger log = LogManager.getLogger(USSDVoucherOverSms.class);
	CucumberCommon common = new CucumberCommon();
	CucumberCommon commonForSMS = new CucumberCommon();
	SQLResultSets resultSet = new SQLResultSets();

	@When("^User sell vouchers with reseller msisdn (.+) using shortcode (.+) by choosing menu option (.+) and sub menu option (.+) and selecting denomination option (.+) for subscriber msisdn (.+) entering no of vouchers (.+) with reseller pin (.+) get ussd notification (.+) after confirmation by (.+) for reseller id (.+) and denomination is (.+) sender reseller recieve sms (.+) and subscriber recieve sms (.+) and sms from kannel (.+) and line from log (.+)$")
	public void PurchaseVoSfromUSSD( 
			String resellerMSISDN ,
			String shortcode ,
			String menuOption,
			String voucherOption,
			String denominationOption, 
			String subscriberMSISDN,
			String noOfVouchers,
			String pin,
			String message, 
			String confirm, 
			String resellerID,
			String denomination,
			String senderSmsMessage,
			String recieverSmsMessage,
			String component,
			String loglines
			)
			throws IOException, SQLException, JSchException, ParseException {

		log.info("=======================>>>>>>>>>>>>Perform String Base Vos<<<<<<<<<<<=====================");
    	String CountryCodeNetworkCodeFromProp = readConfg.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");
		String InvalidHierarchy = readUssd.returnValueForUssd("InvalidHierarchy");
		String ContractViolation = readUssd.returnValueForUssd("ContractViolation");
		String Invalidpin = readUssd.returnValueForUssd("InvalidPin");		
		String InvalidR2RTransfer = readUssd.returnValueForUssd("InvalidR2RTransfer");
		String ProductoutofStock = readUssd.returnValueForUssd("OUTOFSTOCK_PRODUCT_MESSAGE");
		String BlockedProduct =readUssd.returnValueForUssd("BLOCKED_PRODUCT_MESSAGE");
		String ResellerIDFromProp= readUssd.returnValueForUssd(resellerID);
		String ResellerMSISDNFromProp = readUssd.returnValueForUssd(resellerMSISDN);
		ResellerMSISDNFromProp = CountryCodeNetworkCodeFromProp+ResellerMSISDNFromProp;
		String SubscriberMSISDNFromProp = readUssd.returnValueForUssd(subscriberMSISDN);		
		SubscriberMSISDNFromProp = CountryCodeNetworkCodeFromProp+SubscriberMSISDNFromProp;
	    String DenominationFromProp = readUssd.returnValueForUssd(denomination);
		String DenominationOptionFromProp =readUssd.returnValueForUssd(denominationOption);
		String NoOfVouchersFromProp = readUssd.returnValueForUssd(noOfVouchers);
		String ConfirmFromProp = readUssd.returnValueForUssd(confirm);
		String PinFromProp = readUssd.returnValueForUssd(pin);
		String MessageFromProp = readUssd.returnValueForUssd(message);
		String ShortCodeFromProp = readUssd.returnValueForUssd(shortcode);
		String USSD_VoS_OptionFromProp = readUssd.returnValueForUssd(menuOption);
		String commonSenderSMSFromProp = readUssd.returnValueForUssd(senderSmsMessage);
		String commonReciervSMSFromProp = readUssd.returnValueForUssd(recieverSmsMessage);
		String LoglinesFromProp = readUssd.returnValueForUssd(loglines);
		String LoglinesforcomponentFromProp = readUssd.returnValueForUssd(component);
		String voucherOptionFromProp = readUssd.returnValueForUssd(voucherOption);

		
		log.info("ResellerID:"+ResellerIDFromProp );
		log.info("ResellerMSISDN:"+ResellerMSISDNFromProp);
		log.info("SubscriberMSISDN:"+SubscriberMSISDNFromProp);
		log.info("Denomination:"+DenominationFromProp);
		log.info("DenominationOption:"+ DenominationOptionFromProp);
		log.info("NoOfVouchers:"+ NoOfVouchersFromProp);
		log.info("Pin:"+PinFromProp);
		log.info("Message:"+MessageFromProp);
		log.info("ShortCode:"+ShortCodeFromProp);
		log.info("USSD_VoS_Option:"+USSD_VoS_OptionFromProp);
		log.info("Confirm:"+ ConfirmFromProp);
		log.info("commonSenderSMS" + commonSenderSMSFromProp);
		log.info("commonReciervSMS" +commonReciervSMSFromProp);
		log.info("log lines for sms "+LoglinesFromProp);
		log.info("Loglinesforcomponent"+LoglinesforcomponentFromProp);
		
		if(ConfirmFromProp.equals("1")){
			 fullCurl = common.returnFullCurl(ResellerMSISDNFromProp, ShortCodeFromProp);
			 curl = fullCurl+"&p1="+USSD_VoS_OptionFromProp+"&p2="+voucherOptionFromProp+"&p3="+DenominationOptionFromProp+"&p4="+PinFromProp+"&p5="+ConfirmFromProp;
			 log.info("Confirmation is required");
			 log.info("VoS.java: "+curl+'"');
		}else{
			 fullCurl = common.returnFullCurl(ResellerMSISDNFromProp, ShortCodeFromProp);
			String curl = fullCurl+"&p1="+USSD_VoS_OptionFromProp+"&p2="+voucherOptionFromProp+"&p3="+DenominationOptionFromProp+"&p4="+PinFromProp;
			log.info("Confirmation is not required");
			log.info("VoS.java: "+curl+'"');
		}
		
		log.info("fullCurl "+fullCurl);
		 curl =fullCurl+"&p1="+USSD_VoS_OptionFromProp+"&p2="+voucherOptionFromProp +"&p3="
				+ DenominationOptionFromProp  + "&p4=" + SubscriberMSISDNFromProp + "&p5=" +  NoOfVouchersFromProp + "&p6=" +  PinFromProp + "&p7=" + ConfirmFromProp;
		log.info("VoS.java: " + curl + '"');

		/***************** Reseller Balance Fetched before transaction ****************/
		log.info("*****************Reseller Balance Fetched before transaction****************");
		log.info("VoS >> resellerID: " + ResellerIDFromProp);

		double current_amount = resultSet.returnResellerAmount(ResellerIDFromProp);
		log.info("Reseller current balance before transaction: "+ current_amount);
		
		Stock_Before_Sale= resultSet.resturnActiveVoucherCount(1, 12);
		log.info("Voucher stock before sale="+Stock_Before_Sale);	
			
		/***************** Create SSH Connection and execute the command ****************/
		log.info("===========>>>>Create SSH Connection");
		common.createSSHCoonection();
		((ChannelExec) common.channel).setCommand(curl + "\" \n");
		common.createChannel();

		/***************** Transaction/Amount Checking ****************/
		boolean state = common.Result.contains(MessageFromProp);
		log.info(common.Result.contains(MessageFromProp));
		log.info("common.Result: " + common.Result);
		log.info(MessageFromProp + "..." + state);
		log.info("\n=====================Transaction Status===========================\n");
		Assert.assertTrue(state);
		if (state == true) {
			log.info("\nTransaction is Successfull ");
			log.info("Reseller Balance After Successfull Transaction "
					+ resultSet.returnResellerAmount(ResellerIDFromProp));
			
			if(Integer.parseInt(NoOfVouchersFromProp)==1){
				 remainingBalSingleVoucher = current_amount - Integer.parseInt(DenominationFromProp);
				log.info("Balance after Transaction for single voucher: "+remainingBalSingleVoucher);
			}else{
				if(Integer.parseInt(NoOfVouchersFromProp) > 1){
					 remainingBalMultipleVoucher = current_amount - (Integer.parseInt(NoOfVouchersFromProp)*Integer.parseInt(DenominationFromProp));
					 log.info("Balance after Transaction for multiple vouchers: "+remainingBalMultipleVoucher);
				}
				/*****************Create SSH Connection and execute the log command****************/
                log.info("===========>>>>Create SSH Connection for log command");
                String logData = common.returnCommandForLogReading(LoglinesFromProp, LoglinesforcomponentFromProp);
                commonForSMS.createSSHCoonection();
                ((ChannelExec) commonForSMS.channel).setCommand(logData);
                commonForSMS.createChannel();
                log.info("commonForSMS.Result..."+common.Result);
                commonForSMS.closeSSHConnection();
                
                boolean stateForSenderSMS = commonForSMS.Result.contains(commonSenderSMSFromProp);
                boolean stateForReciverSMS = commonForSMS.Result.contains(commonReciervSMSFromProp);
                
                Assert.assertTrue(stateForSenderSMS);
                Assert.assertTrue(stateForReciverSMS);
                
                if(stateForSenderSMS == true && stateForReciverSMS==true){
                	log.info("Notification messages verified from logs");
                }else{
                	log.error("Error validating the notfication message");
                }
            	
			}
			
			 voucher_stock_after= resultSet.resturnActiveVoucherCount(1, 12);
			 
			log.info("Voucher stock after sale="+voucher_stock_after);
			
			unrevoked_vouchers=Stock_Before_Sale-resultSet.resturnRevokeVoucherCount(5, 12);
			revoked_vouchers=resultSet.resturnRevokeVoucherCount(5, 12);

			if (MessageFromProp.contains(Invalidpin)
					|| MessageFromProp.contains(ContractViolation)
					|| MessageFromProp.contains(InvalidHierarchy)
					|| MessageFromProp.contains(InvalidR2RTransfer)
					|| MessageFromProp.contains(ProductoutofStock)
					|| MessageFromProp.contains(BlockedProduct)){
				
				if(Integer.parseInt(NoOfVouchersFromProp) == 1){
					Assert.assertEquals(current_amount,resultSet.returnResellerAmount(ResellerIDFromProp),Delta);
					log.info("Balance is not deducted");
					
					Assert.assertEquals(Stock_Before_Sale,resultSet.resturnActiveVoucherCount(1, 12));
					log.info("Vouchers is not sold successfully");	
				}
			}
			else {
					if(Integer.parseInt(NoOfVouchersFromProp)==1){ //Single Voucher
						Assert.assertEquals(remainingBalSingleVoucher,resultSet.returnResellerAmount(ResellerIDFromProp),Delta);
						log.info("Balance is deducted");
						Assert.assertEquals(voucher_stock_after, resultSet.resturnActiveVoucherCount(1, 12));
						
					}else{
						if(Integer.parseInt(NoOfVouchersFromProp) > 1){ //Multiple Vouchers
						Assert.assertEquals(remainingBalMultipleVoucher,resultSet.returnResellerAmount(ResellerIDFromProp),Delta);
						log.info("Balance is deducted");
						Assert.assertEquals(voucher_stock_after, resultSet.resturnActiveVoucherCount(1, 12));
						}
					}
			}
		} 
		
	else {
			log.error("\nTransaction Not Successfull ");
			log.info("Reseller Balance After Failed Transaction " + resultSet.returnResellerAmount(ResellerIDFromProp));
		}

		log.info("\n" + common.Result);
		common.closeSSHConnection();
	}
		
	
	
}
