package com.seamless.ers.qa.cucumber.ImplementationClasses.USSD;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class USSDAppCustomerSpecificVoucherCurlGenerator {
	
	public static Logger log = LogManager.getLogger(USSDAppCustomerSpecificVoucherCurlGenerator.class);
	
	public String prepareCurlForERS_STD(String confirmationFromProp, String resellerMsisdnFromProp, String shortCodeFromProp, String menuOptionFromProp, String voucherOptionFromProp, String voucherDenominationFromProp, String pinFromProp) throws Exception{
		
		USSDAppVoucherSelling ussdappvouchersell = new USSDAppVoucherSelling();
		
		log.info("ConfirmationFromProp: " + confirmationFromProp);

		if (confirmationFromProp.equals("1")) {
			String fullCurl = ussdappvouchersell.common.returnFullCurl(resellerMsisdnFromProp,shortCodeFromProp);
			String curl = fullCurl + "&p1=" + menuOptionFromProp + "&p2="+ voucherOptionFromProp + "&p3="+ voucherDenominationFromProp + "&p4=" + pinFromProp+ "&p5=" + confirmationFromProp;
			log.info("USSDAppCustomerSpecificCurlGenerator.java: " + curl + '"');
			return curl;
		} 
		else{
			String fullCurl = ussdappvouchersell.common.returnFullCurl(resellerMsisdnFromProp,shortCodeFromProp);
			String curl = fullCurl + "&p1=" + menuOptionFromProp + "&p2="+ voucherOptionFromProp + "&p3="+ voucherDenominationFromProp + "&p4=" + pinFromProp;
			log.info("Confirmation is not required");
			log.info("USSDAppCustomerSpecificCurlGenerator.java: " + curl + '"');
			return curl;
		}
	}
	
	public String prepareCurlForZM_MTN(String confirmation, String resellerMsisdnFromProp, String shortCodeFromProp, String menuOptionFromProp, String voucherDenominationFromProp, String pinFromProp, String confirmationFromProp) throws Exception{
		
		USSDAppVoucherSelling ussdappvouchersell = new USSDAppVoucherSelling();
		
		log.info("It's Zambia");
		
		log.info("ConfirmationFromProp: " + confirmation);
		if (confirmationFromProp.equals("1")) {
			String fullCurl = ussdappvouchersell.common.returnFullCurl(resellerMsisdnFromProp,shortCodeFromProp);
			String curl = fullCurl + "&p1=" + menuOptionFromProp + "&p2="+ voucherDenominationFromProp + "&p3=" + pinFromProp+ "&p4=" + confirmationFromProp;
			log.info("Confirmation is required");
			log.info("USSDAppCustomerSpecificCurlGenerator.java: " + curl + '"');
			return curl;
		
		} else {
			String fullCurl = ussdappvouchersell.common.returnFullCurl(resellerMsisdnFromProp,shortCodeFromProp);
			String curl = fullCurl + "&p1=" + menuOptionFromProp + "&p2=" + voucherDenominationFromProp + "&p3=" + pinFromProp;
			log.info("Confirmation is not required");
			log.info("USSDAppCustomerSpecificCurlGenerator.java: " + curl + '"');
			return curl;
		}
	}
	
	public String prepareCurlForGB_MTN(String confirmationFromProp, String resellerMsisdnFromProp, String shortCodeFromProp, String menuOptionFromProp, String voucherOptionFromProp, String voucherDenominationFromProp, String pinFromProp) throws Exception{
		
		USSDAppVoucherSelling ussdappvouchersell = new USSDAppVoucherSelling();
		
		log.info("It's GB");
		
		log.info("ConfirmationFromProp: " + confirmationFromProp);
		if (confirmationFromProp.equals("1")) {
			String fullCurl = ussdappvouchersell.common.returnFullCurl(resellerMsisdnFromProp,shortCodeFromProp);
			String curl = fullCurl + "&p1=" + menuOptionFromProp + "&p2="+ voucherOptionFromProp + "&p3="+ voucherDenominationFromProp + "&p4=" + pinFromProp+ "&p5=" + confirmationFromProp;
			log.info("Confirmation is required");
			log.info("USSDAppCustomerSpecificCurlGenerator.java: " + curl + '"');
			return curl;
		
		} else {
			String fullCurl = ussdappvouchersell.common.returnFullCurl(ussdappvouchersell.resellerMsisdnFromProp,ussdappvouchersell.shortCodeFromProp);
			String curl = fullCurl + "&p1=" + menuOptionFromProp + "&p2="+ voucherOptionFromProp + "&p3="+ voucherDenominationFromProp + "&p4=" + pinFromProp;
			log.info("Confirmation is not required");
			log.info("USSDAppCustomerSpecificCurlGenerator.java: " + curl + '"');
			return curl;
		}
	}
}