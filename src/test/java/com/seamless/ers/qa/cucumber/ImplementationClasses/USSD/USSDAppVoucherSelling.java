package com.seamless.ers.qa.cucumber.ImplementationClasses.USSD;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import com.jcraft.jsch.ChannelExec;
import com.seamless.ers.qa.cucumber.ImplementationClasses.Common.CucumberCommon;
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadConfigFromProperties;
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadUssdAppConfigFromProperties;
import com.seamless.ers.qa.cucumber.SQLQueries.SQLResultSets;

import cucumber.api.java.en.When;

public class USSDAppVoucherSelling {
	ReadUssdAppConfigFromProperties readUssd = ReadUssdAppConfigFromProperties.getInstance();

	public CucumberCommon common = new CucumberCommon();
	public USSDAppCustomerSpecificVoucherCurlGenerator customCurlGennerator = new USSDAppCustomerSpecificVoucherCurlGenerator();
	CucumberCommon commonForSMS = new CucumberCommon();
	public static Logger log = LogManager.getLogger( USSDAppVoucherSelling.class );     
	SQLResultSets resultSet = new SQLResultSets();
	public String curl;
	int voucher_stock_before_sale;
    ReadConfigFromProperties readConfig = new ReadConfigFromProperties();
    public double Delta = 1e-8;
    
    public String 
    CountryCodeNetworkCodeFromProp, 
    resellerIdFromProp, 
    resellerMsisdnFromProp, 
    voucherDenominationFromProp, 
    shortCodeFromProp, 
    menuOptionFromProp
    ,pinFromProp 
    ,confirmationFromProp 
    ,message_from_prop
	,logComponentFromProp
	,LogLineFromProp
	,voucherPriceFromProp
	,voucherCountFromProp
	,smsMessageFromProp
	,voucherOptionFromProp
	,class_typeFromProp;

	@When("^Reseller with (.+) and (.+) from USSDapp using (.+) and (.+) and sub menu is (.+) and sell a voucher of (.+) and (.+) and (.+) and (.+) and in response (.+) message is received also message is send to reseller from (.+) logs lines (.+) where class type is (.+) and success message is (.+)$")
	public void SellVouhcerFromUSSD(String resellerId, String reseller_msisdn, String short_code,String voucherOption, String menu_option, String vouhcer_denomination, String pin, String confirmation, String voucherPrice,  String success_message, String component, String lines,String class_type, String smsMessage) throws Exception{
		
		log.info("=======================>>>>>>>>>>>>Perform String Base VOT<<<<<<<<<<<=====================");
    	CountryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");
		resellerIdFromProp = readUssd.returnValueForUssd(resellerId);
		resellerMsisdnFromProp = readUssd.returnValueForUssd(reseller_msisdn);
		resellerMsisdnFromProp=CountryCodeNetworkCodeFromProp+resellerMsisdnFromProp;
		voucherDenominationFromProp = readUssd.returnValueForUssd(vouhcer_denomination);
		shortCodeFromProp = readUssd.returnValueForUssd(short_code);
		menuOptionFromProp = readUssd.returnValueForUssd(menu_option);
		pinFromProp = readUssd.returnValueForUssd(pin);
		confirmationFromProp = readUssd.returnValueForUssd(confirmation);
		message_from_prop = readUssd.returnValueForUssd(success_message);
		logComponentFromProp = readUssd.returnValueForUssd(component);
		LogLineFromProp = readUssd.returnValueForUssd(lines);
		voucherPriceFromProp = readUssd.returnValueForUssd(voucherPrice);
		voucherCountFromProp = readUssd.returnValueForUssd("VOUCHER_COUNT");
		smsMessageFromProp = readUssd.returnValueForUssd(smsMessage);
		voucherOptionFromProp = readUssd.returnValueForUssd(voucherOption);
		class_typeFromProp = readUssd.returnValueForUssd(class_type);

		String InvalidHierarchy = readUssd.returnValueForUssd("InvalidHierarchy");
    	String ContractViolation = readUssd.returnValueForUssd("ContractViolation");
    	String Invalidpin = readUssd.returnValueForUssd("InvalidPin");
    	String UnRegisterUserCreditTrasnferViolation = readUssd.returnValueForUssd("UnRegisterUserCreditTrasnferViolation");
    	String InvalidR2RTransfer = readUssd.returnValueForUssd("InvalidR2RTransfer");
    	String BlockProduct = readUssd.returnValueForUssd("BLOCKED_PRODUCT_MESSAGE");
    	String RevokeProduct = readUssd.returnValueForUssd("OUTOFSTOCK_PRODUCT_MESSAGE");
		
		log.info("resellerIdFromProp: "+resellerIdFromProp);
		log.info("resellerMsisdnFromProp: "+resellerMsisdnFromProp);
		log.info("voucherDenominationFromProp: "+voucherDenominationFromProp);
		log.info("shortCodeFromProp: "+shortCodeFromProp);
		log.info("menuOptionFromProp: "+menuOptionFromProp);
		log.info("pinFromProp: "+pinFromProp);
		log.info("confirmationFromProp: "+confirmationFromProp);
		log.info("message_from_prop: "+message_from_prop);
		log.info("logComponentFromProp: "+logComponentFromProp);
		log.info("LogLineFromProp: "+LogLineFromProp);
		log.info("VoucherPriceFromProp: "+voucherPriceFromProp);
		log.info("VoucherCountFromProp: "+voucherCountFromProp);
		log.info("SmsMessageFromProp: "+smsMessageFromProp);
		log.info("voucherOptionFromProp: "+voucherOptionFromProp);

		
		log.info("InvalidHierarchy: "+InvalidHierarchy);
		log.info("ContractViolation: "+ContractViolation);
		log.info("Invalidpin: "+Invalidpin);
		log.info("UnRegisterUserCreditTrasnferViolation: "+UnRegisterUserCreditTrasnferViolation);
		log.info("InvalidR2RTransfer: "+InvalidR2RTransfer);
		log.info("InvalidR2RTransfer: "+BlockProduct);
		log.info("InvalidR2RTransfer: "+RevokeProduct);
		
		if (System.getProperty("customer").equals("ers_std")){
				curl = customCurlGennerator.prepareCurlForERS_STD(confirmationFromProp, resellerMsisdnFromProp, shortCodeFromProp, menuOptionFromProp, voucherOptionFromProp, voucherDenominationFromProp, pinFromProp);
		}
		else
			if (System.getProperty("customer").equals("gb_mtn")){
				curl = customCurlGennerator.prepareCurlForGB_MTN(confirmationFromProp, resellerMsisdnFromProp, shortCodeFromProp, menuOptionFromProp, voucherOptionFromProp, voucherDenominationFromProp, pinFromProp);
			}
		else
			if (System.getProperty("customer").equals("zm_mtn")){
				curl = customCurlGennerator.prepareCurlForZM_MTN(confirmationFromProp, resellerMsisdnFromProp, shortCodeFromProp, menuOptionFromProp, voucherDenominationFromProp, pinFromProp, confirmationFromProp);
			}

    	/*****************Reseller Balance Fetched before transaction****************/
    	log.info("*****************Reseller Balance Fetched before transaction****************");
    	double beforeTrans = resultSet.returnResellerAmount(resellerIdFromProp);
        log.info("Reseller current balance before transaction: "+beforeTrans);
        
        voucher_stock_before_sale= resultSet.resturnActiveVoucherCount(1,Integer.parseInt(class_typeFromProp));
		log.info("Voucher stock before sale="+voucher_stock_before_sale);
        
        /*****************Create SSH Connection and perform the transaction****************/
        log.info("===========>>>>Create SSH Connection and perform the transaction");
        common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(curl + "\" \n");
        common.createChannel();
    	
        
        /*****************Transaction/Amount Checking****************/
        boolean state = common.Result.contains(message_from_prop);
        log.info(message_from_prop+"..."+state);
        log.info("USSD reponse and custom response is verfied: "+common.Result.contains(message_from_prop));
        log.info("\n=====================Transaction Status===========================\n");
        try{
        	Assert.assertTrue(state);
            if(state == true){
            	//Fetching Reseller Balance
            	double amountFromDBAfterTran = resultSet.returnResellerAmount(resellerIdFromProp);
            	log.info("\nTransaction is Successfull ");
            	log.info("Reseller Balance After Successfull Transaction: "+amountFromDBAfterTran);
            	
            	//Fetching Voucher Count
            	int VoucherBatchFromDBAfterTran = resultSet.resturnActiveVoucherCount(1, Integer.parseInt(class_typeFromProp));
            	log.info("\nTransaction is Successfull ");
            	log.info("Reseller Voucher After Successfull Transaction: "+VoucherBatchFromDBAfterTran);
                
            	/*****************Create SSH Connection and execute the log command****************/
                log.info("===========>>>>Create SSH Connection for log command");
                String logData = common.returnCommandForLogReading(LogLineFromProp, logComponentFromProp);
                commonForSMS.createSSHCoonection();
                ((ChannelExec) commonForSMS.channel).setCommand(logData);
                commonForSMS.createChannel();
                log.info("commonForSMS.Result..."+common.Result);
                commonForSMS.closeSSHConnection();
                
                boolean stateForSMS = commonForSMS.Result.contains(smsMessageFromProp);
                
                Assert.assertTrue(stateForSMS);
                
                if(stateForSMS == true){
                	log.info("Notification message verified from logs");
                }else{
                	log.error("Error validating the notfication message");
                }
            	
            	double remainingBalManualCalculated = beforeTrans - Integer.parseInt(voucherPriceFromProp);
            	
                int remainingVoucherCount = voucher_stock_before_sale - Integer.parseInt(voucherCountFromProp);
                
                log.info("remainingBalManualCalculated: "+remainingBalManualCalculated);
                log.info("remainingVoucherCountManualCalculated: "+remainingVoucherCount);
                
                if(message_from_prop.contains(Invalidpin) 
                		|| message_from_prop.contains(ContractViolation) 
                		|| message_from_prop.contains(InvalidHierarchy) 
                		|| message_from_prop.contains(UnRegisterUserCreditTrasnferViolation)
                		|| message_from_prop.contains(InvalidR2RTransfer)
                		|| message_from_prop.contains(RevokeProduct)
                		|| message_from_prop.contains(BlockProduct)){
                	
                	Assert.assertEquals(beforeTrans, resultSet.returnResellerAmount(resellerIdFromProp), Delta);
                	log.info("Balance is not deducted");
                	
                	Assert.assertEquals(voucher_stock_before_sale, resultSet.resturnActiveVoucherCount(1, Integer.parseInt(class_typeFromProp)));
                	log.info("Voucher is not deducted");
                	
                }else{
                	log.info("remainingBalManualCalculated, amountFromDBAfterTran"+remainingBalManualCalculated +"........"+ amountFromDBAfterTran);
                	Assert.assertEquals(remainingBalManualCalculated, amountFromDBAfterTran, Delta);
                	log.info("Balance is deducted");
                	
                	log.info("remainingVoucherCountManualCalculated, VoucherCountFromDBAfterTran: "+remainingVoucherCount +"........"+ VoucherBatchFromDBAfterTran);
                	Assert.assertEquals(remainingVoucherCount, VoucherBatchFromDBAfterTran);
                	log.info("Vouhcer is deducted");
                }
            }
            
            else{
            	log.error("\nTransaction Not Successfull ");
            	log.info("Reseller Balance After Failed Transaction "+resultSet.returnResellerAmount(resellerIdFromProp));
            	log.info("Reseller Voucher Count After Failed Transaction "+resultSet.resturnActiveVoucherCount(1, Integer.parseInt(class_typeFromProp)));
            	return;
		}
	}
        finally{
		log.info("\n" + common.Result);
    common.closeSSHConnection();
        }
	}
}