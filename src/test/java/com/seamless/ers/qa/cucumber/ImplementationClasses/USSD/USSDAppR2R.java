package com.seamless.ers.qa.cucumber.ImplementationClasses.USSD;

import java.io.IOException;
import java.sql.SQLException;

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

import cucumber.api.java.en.When;

public class USSDAppR2R{   
	          
	ReadSMSAppProperties sms = new ReadSMSAppProperties();
	ReadConfigFromProperties readConfig = new ReadConfigFromProperties();
	ReadUssdAppConfigFromProperties readUssd = ReadUssdAppConfigFromProperties.getInstance();
	private String countryCodeNetworkCodeFromProp;
	public String curl;
	public double Delta = 1e-8;
	
	public static Logger log = LogManager.getLogger( USSDAppR2R.class );     
	SQLResultSets resultSet = new SQLResultSets();
	CucumberCommon common = new CucumberCommon();
	
	/***************************USSD***************************/
	
	/**********************************
	 * String Based R2R
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws JSchException 
	 **********************************/
	
	@When("^I Perform R2R with valid data to the reseller number (.+) having reseller id (.+) from reseller number (.+) with reseller id (.+) with an amount (.+) and pin is (.+) and (.+) in message is returned after execution having shortcode (.+) and option was (.+)$")
	public void PerformR2RWithValidData(String reseller_number_destination,String reciever_resellerID ,String reseller_number_source, String resellerID, String amount, String pin, String message, String shortcode, String menuOption) throws IOException, SQLException, JSchException {        
			
			log.info("=======================>>>>>>>>>>>>Perform String Base R2R<<<<<<<<<<<=====================");
				
			countryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");
			 	
			String reseller_destination_from_prop = readUssd.returnValueForUssd(reseller_number_destination);
			String to_reseller_numberFromProp = readUssd.returnValueForUssd(reseller_number_destination);
			String reseller_destination_from_prop1 = countryCodeNetworkCodeFromProp+to_reseller_numberFromProp;
			String resellerID_from_prop =readUssd.returnValueForUssd(resellerID);
			String from_reseller_from_prop =readUssd.returnValueForUssd(reseller_number_source);
			from_reseller_from_prop = countryCodeNetworkCodeFromProp+from_reseller_from_prop;
			String amount_from_prop =readUssd.returnValueForUssd(amount);
			String pin_from_prop =readUssd.returnValueForUssd(pin);
			String message_from_prop =readUssd.returnValueForUssd(message);
			String shortcode_from_prop =readUssd.returnValueForUssd(shortcode);
			String menuOption_from_prop =readUssd.returnValueForUssd(menuOption);
			String reciever_resellerID_from_prop = readUssd.returnValueForUssd(reciever_resellerID);


			log.info("ToReseller:" +reseller_destination_from_prop);
			log.info("resellerID:" + resellerID_from_prop);
			log.info("From Reseller:" +from_reseller_from_prop);
			log.info("amount:" +amount_from_prop);
			log.info("pin:" +pin_from_prop);
			log.info("message:" +message_from_prop);
			log.info("shortcode:" +shortcode_from_prop);
			log.info("menuOption:" +menuOption_from_prop);
			log.info("RecieverResellerID:"+reciever_resellerID_from_prop);
			log.info("CountryCodeNetworkCodeFromProp:" +countryCodeNetworkCodeFromProp);
			log.info("reseller_numberFromProp:" +to_reseller_numberFromProp);
			
        	String InvalidHierarchy = readUssd.returnValueForUssd("InvalidHierarchy");
        	String ContractViolation = readUssd.returnValueForUssd("ContractViolation");
        	String Invalidpin = readUssd.returnValueForUssd("InvalidPin");
        	String UnRegisterUserCreditTrasnferViolation = readUssd.returnValueForUssd("UnRegisterUserCreditTrasnferViolation");
        	String InvalidR2RTransfer = readUssd.returnValueForUssd("InvalidR2RTransfer");
			
        	
        	/***************** Prepare Customer specific curl command ****************/
        	String fullCurl = common.returnFullCurl(from_reseller_from_prop, shortcode_from_prop); 
    		if (System.getProperty("customer").equals("ng_glo"))
    		{
    			log.info("I am verifying the R2R for "+System.getProperty("customer"));
    			curl = fullCurl+"&p1="+menuOption_from_prop+ "&p2=1"+ "&p3="+reseller_destination_from_prop1+"&p4="+amount_from_prop+"&p5="+pin_from_prop + "&p6=1";
    			log.info("R2R.java: " + curl + '"');
    			}
    			else{				
    				curl = fullCurl+"&p1="+menuOption_from_prop+"&p2="+reseller_destination_from_prop1+"&p3="+amount_from_prop+"&p4="+pin_from_prop;
    	        	log.info("R2R.java: "+curl+'"');		
    				}

        	/*****************Reseller Balance Fetched before transaction****************/
        	log.info("*****************Reseller Balance Fetched before transaction****************");
        	
        	double beforeTrans=resultSet.returnResellerAmount(resellerID_from_prop);
        	double beforetrans_reciverReseller=resultSet.returnResellerAmount(reciever_resellerID_from_prop);
            log.info("Sender Reseller current balance before transaction: "+beforeTrans); 
            log.info("Reciever Reseller current balance before transaction: "+beforetrans_reciverReseller);
            
            /*****************Create SSH Connection and execute the command****************/
            log.info("===========>>>>Create SSH Connection");
            common.createSSHCoonection();
            ((ChannelExec) common.channel).setCommand(curl + "\" \n");
            common.createChannel();
            
            /*****************Transaction/Amount Checking****************/
            boolean state = common.Result.contains(message_from_prop);
            log.info(common.Result.contains(message_from_prop));
            log.info(message_from_prop+"..."+state);
            log.info("\n=====================Transaction Status===========================\n");
            try{
            	Assert.assertTrue(state);
	            if(state == true){
	            	double amountFromDBAfterTran = (double) resultSet.returnResellerAmount(resellerID_from_prop);
	            	double amountFromDBafterTranRecieverReseller=resultSet.returnResellerAmount(reciever_resellerID_from_prop);
	            	log.info("\nTransaction is Successfull ");
	            	log.info("Sender Reseller Balance After Successfull Transaction: "+amountFromDBAfterTran);
	            	log.info("Reciever Reseller Balance After Successfull Transaction: "+amountFromDBafterTranRecieverReseller);
	                double remainingBalManualCalculated = beforeTrans - Integer.parseInt(amount_from_prop);
	                log.info("Remaining Balance calulated for Sender reseller: "+remainingBalManualCalculated);
	                double newBalanceManuallycaluculatedRecieverReseller= beforetrans_reciverReseller + Integer.parseInt(amount_from_prop);
	                log.info("New Balance calculate for reciever reseller after transaction calculated manually: " + newBalanceManuallycaluculatedRecieverReseller);
	                
	                if(message_from_prop.contains(Invalidpin) 
	                		|| message_from_prop.contains(ContractViolation) 
	                		|| message_from_prop.contains(InvalidHierarchy) 
	                		|| message_from_prop.contains(UnRegisterUserCreditTrasnferViolation)
	                		|| message_from_prop.contains(InvalidR2RTransfer)){
	                	double getSenderAmountFromDB = (double) resultSet.returnResellerAmount(resellerID_from_prop);
	                	double getReceiverAmountFromDB = (double) resultSet.returnResellerAmount(reciever_resellerID_from_prop);
	                	Assert.assertEquals(beforeTrans, getSenderAmountFromDB, Delta);
	                	log.info("Balance is not deducted Sender Reseller");
	                	Assert.assertEquals(beforetrans_reciverReseller,getReceiverAmountFromDB, Delta);
	                	log.info("Balance is not deducted Sender Reseller");
	                	
	                }else{
	                	log.info("Remaining Balance calulated for Sender reseller: "+remainingBalManualCalculated +".."+"Remaining Balance from database for sender reseller: "+ amountFromDBAfterTran);
	                	Assert.assertEquals(remainingBalManualCalculated, amountFromDBAfterTran, Delta);
	                	log.info("Balance is deducted from Sender Reseller Account");
	                
	                	log.info("New Balance calculated for Reciever Reseller: "+newBalanceManuallycaluculatedRecieverReseller+".." +"New Balance from database for Reciver reseller: " + amountFromDBafterTranRecieverReseller);
	                	Assert.assertEquals(newBalanceManuallycaluculatedRecieverReseller, amountFromDBafterTranRecieverReseller, Delta);
	                	log.info("Balance is added to Reciever Reseller Account");
	                }
	            }
	            
	            else{
	            	log.error("\nTransaction Not Successfull ");
	            	log.info("Sender Reseller Balance After Failed Transaction "+resultSet.returnResellerAmount(resellerID_from_prop));
	            	log.info("Reciver Reseller Balance After Failed Transaction "+resultSet.returnResellerAmount(reciever_resellerID_from_prop));
	            	
	            	
	            	return;
			}
		}
            finally{
			log.info("\n" + common.Result);
        common.closeSSHConnection();
		}
	}
	
	

	@When("^I Perform R2R with invalid data to the reseller number (.+) having reseller id (.+) from reseller number (.+) with reseller id (.+) with an amount (.+) and pin is (.+) and (.+) in message is returned after execution having shortcode (.+) and option was (.+)$")
	public void PerformR2RWithInvalidData(String reseller_number_destination,String reciever_resellerID ,String reseller_number_source, String resellerID, String amount, String pin, String message, String shortcode, String menuOption) throws IOException, SQLException, JSchException {

		log.info("=======================>>>>>>>>>>>>Perform String Base R2R with Invlalid Data<<<<<<<<<<<=====================");
		String reseller_numberFromProp = readUssd.returnValueForUssd(reseller_number_destination);
		countryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE"); 
        String reseller_destination_from_prop = countryCodeNetworkCodeFromProp+reseller_numberFromProp;     
        String resellerID_from_prop =readUssd.returnValueForUssd(resellerID);
        String from_reseller_from_prop =readUssd.returnValueForUssd(reseller_number_source);
        from_reseller_from_prop = countryCodeNetworkCodeFromProp+from_reseller_from_prop;
        String amount_from_prop =readUssd.returnValueForUssd(amount);
        String pin_from_prop =readUssd.returnValueForUssd(pin);
        String message_from_prop =readUssd.returnValueForUssd(message);
        String shortcode_from_prop =readUssd.returnValueForUssd(shortcode);
        String menuOption_from_prop =readUssd.returnValueForUssd(menuOption);
        String reciever_resellerID_from_prop = readUssd.returnValueForUssd(reciever_resellerID);
        
        log.info("toReseller:" +reseller_destination_from_prop);
        log.info("resellerID:" + resellerID_from_prop);
        log.info("fromReseller:" +from_reseller_from_prop);
		log.info("amount:" +amount_from_prop);
		log.info("pin:" +pin_from_prop);
		log.info("message:" +message_from_prop);
		log.info("shortcode:" +shortcode_from_prop);
		log.info("menuOption:" +menuOption_from_prop);
		log.info("CountryCodeNetworkCodeFromProp:" +countryCodeNetworkCodeFromProp);
		log.info("reseller_numberFromProp:" +reseller_numberFromProp);
        log.info("reseller_destination_from_prop:" +reseller_destination_from_prop);
		log.info("RecieverResellerID:"+reciever_resellerID_from_prop);

    	String InvalidHierarchy = readUssd.returnValueForUssd("InvalidHierarchy");
    	String ContractViolation = readUssd.returnValueForUssd("ContractViolation");
    	String Invalidpin = readUssd.returnValueForUssd("USSD_INVALID_PIN");
    	String UnRegisterUserCreditTrasnferViolation = readUssd.returnValueForUssd("UnRegisterUserCreditTrasnferViolation");
    	String InvalidR2RTransfer = readUssd.returnValueForUssd("InvalidR2RTransfer");
		
    	String fullCurl = common.returnFullCurl(from_reseller_from_prop, shortcode_from_prop); 
    	String curl = fullCurl+"&p1="+menuOption_from_prop+"&p2="+reseller_destination_from_prop+"&p3="+amount_from_prop+"&p4="+pin_from_prop;
    	log.info("R2R.java: "+curl+'"');
    	
    	/*****************Reseller Balance Fetched before transaction****************/
    	log.info("*****************Reseller Balance Fetched before transaction****************");
    	log.info("R2R >> resellerID: "+resellerID_from_prop);
    	
    	double current_amount=(double) resultSet.returnResellerAmount(resellerID_from_prop);
        log.info("Reseller current balance before transaction: "+current_amount);        	
        
        /*****************Create SSH Connection and execute the command****************/
        log.info("===========>>>>Create SSH Connection");
        common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(curl + "\" \n");
        common.createChannel();

        	/*****************Reseller Balance Fetched before transaction****************/
        	log.info("*****************Reseller Balance Fetched before transaction****************");
        	
        	double beforeTrans=(double) resultSet.returnResellerAmount(resellerID_from_prop);
        	double beforetrans_reciverReseller=(double) resultSet.returnResellerAmount(reciever_resellerID_from_prop);
            log.info(" Sender Reseller current balance before transaction: "+beforeTrans); 
            log.info("Reciever Reseller current balance before transaction: "+beforetrans_reciverReseller);
            
            /*****************Create SSH Connection and execute the command****************/
            log.info("===========>>>>Create SSH Connection");
            common.createSSHCoonection();
            ((ChannelExec) common.channel).setCommand(curl + "\" \n");
            common.createChannel();
            
            /*****************Transaction/Amount Checking****************/
            boolean state = common.Result.contains(message_from_prop);
            log.info(common.Result.contains(message_from_prop));
            log.info(message_from_prop+"..."+state);
            log.info("\n=====================Transaction Status===========================\n");
            try{
            	Assert.assertTrue(state);
	            if(state == true){
	            	double amountFromDBAfterTran = (double) resultSet.returnResellerAmount(resellerID_from_prop);
	            	double amountFromDBafterTranRecieverReseller=(double) resultSet.returnResellerAmount(reciever_resellerID_from_prop);
	            	log.info("\nTransaction is Successfull ");
	            	log.info("Sender Reseller Balance After Successfull Transaction: "+amountFromDBAfterTran);
	            	log.info("Reciever Reseller Balance After Successfull Transaction: "+amountFromDBafterTranRecieverReseller);
	                double remainingBalManualCalculated =  beforeTrans - Integer.parseInt(amount_from_prop);
	                log.info("Remaining Balance calulated for Sender reseller: "+remainingBalManualCalculated);
	                double newBalanceManuallycaluculatedRecieverReseller=beforetrans_reciverReseller + Integer.parseInt(amount_from_prop);
	                log.info("New Balance calculate for reciever reseller after transaction calculated manually: " + newBalanceManuallycaluculatedRecieverReseller);
	                
	                if(message_from_prop.contains(Invalidpin) 
	                		|| message_from_prop.contains(ContractViolation) 
	                		|| message_from_prop.contains(InvalidHierarchy) 
	                		|| message_from_prop.contains(UnRegisterUserCreditTrasnferViolation)
	                		|| message_from_prop.contains(InvalidR2RTransfer)){
	                	double getSenderAmount = (double) resultSet.returnResellerAmount(resellerID_from_prop);
	                	double getReceiverAmount = (double) resultSet.returnResellerAmount(reciever_resellerID_from_prop);
	                	Assert.assertEquals(beforeTrans, getSenderAmount, Delta);
	                	log.info("Balance is not deducted For Sender Reseller");
	                	Assert.assertEquals(amountFromDBafterTranRecieverReseller, (double) getReceiverAmount, Delta);
	                	log.info("Balance is not deducted For Reciever Reseller");
	                }else{
	                	log.info("Remaining Balance calulated for Sender reseller: "+remainingBalManualCalculated +".."+"Remaining Balance from database for sender reseller: "+ amountFromDBAfterTran);
	                	Assert.assertEquals(remainingBalManualCalculated, amountFromDBAfterTran, Delta);
	                	log.info("Balance is deducted from Sender Reseller Account");
	                
	                	log.info("New Balance calculated for Reciever Reseller: "+newBalanceManuallycaluculatedRecieverReseller+".." +"New Balance from database for Reciver reseller: " + amountFromDBafterTranRecieverReseller);
	                	Assert.assertEquals(newBalanceManuallycaluculatedRecieverReseller, amountFromDBafterTranRecieverReseller, Delta);
	                	log.info("Balance is added to Reciever Reseller Account");
	                }
	            }
	            
	            else{
	            	log.error("\nTransaction Not Successfull ");
	            	log.info(" Sender Reseller Balance After Failed Transaction "+resultSet.returnResellerAmount(resellerID_from_prop));
	            	log.info("Reciever Reseller Balance After Failed Transaction "+resultSet.returnResellerAmount(reciever_resellerID_from_prop));
	            	return;
			}
		}
            finally{
			log.info("\n" + common.Result);
        common.closeSSHConnection();
		}
	}
	
	/***************************USSD***************************/

	/***************************
	 * SMS
	 * @throws InterruptedException 
	 * ***************************/
	
	@When("^User Perform R2R with valid data through SMS channel to the reciever reseller number (.+) having reciever resellerID (.+) from reseller number (.+) having reseller id (.+) with an amount (.+) and pin is (.+) and in logs notification message (.+) is returned after execution where componenet is (.+) and log lines are (.+) and curl message was (.+)$")
	public void PerformR2RWithValidDataThroughSMSapp(
			String receiverNumber,
			String resellerRecieverID,
			String SenderNumber,
			String senderResellerID,
			String amount,
			String pin,
			String message,
			String component,
			String loglines,
			String curlMsg) throws IOException, SQLException, JSchException, InterruptedException {
        
		String ReceiverNumberFromProp = sms.returnPropertyForSMS(receiverNumber);
		String ReceiverResellerIDFromProp= sms.returnPropertyForSMS(resellerRecieverID);
		countryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE"); 
		String SenderNumberFromProp = sms.returnPropertyForSMS(SenderNumber);
		String ResellerIdFromProp = sms.returnPropertyForSMS(senderResellerID);
		String reseller_numberFromProp = sms.returnPropertyForSMS(receiverNumber);
        String reseller_destination_from_prop = countryCodeNetworkCodeFromProp+ReceiverNumberFromProp;		
		SenderNumberFromProp = countryCodeNetworkCodeFromProp+SenderNumberFromProp;
		String AmountFromProp = sms.returnPropertyForSMS(amount);
		String ResellerPinFromProp = sms.returnPropertyForSMS(pin);
		String ResponseMessageFromProp = sms.returnPropertyForSMS(message);
		String LogComponenetFromProp = sms.returnPropertyForSMS(component);
		String LogLinesFromProp = sms.returnPropertyForSMS(loglines);
		String CurlMessageParam = sms.returnPropertyForSMS(curlMsg);
		
		log.info("ReceiverNumberFromProp: "+ReceiverNumberFromProp);
		log.info("ReceiverResellerIDFromProp: "+ReceiverResellerIDFromProp);
		log.info("ReceiverNumberFromProp: "+reseller_destination_from_prop);
		log.info("ResellerIdFromProp: "+ResellerIdFromProp);
		log.info("SenderNumberFromProp: "+SenderNumberFromProp);
		log.info("AmountFromProp: "+AmountFromProp);
		log.info("ResellerPinFromProp: "+ResellerPinFromProp);
		log.info("ResponseMessageFromProp: "+ResponseMessageFromProp);
		log.info("LogComponenetFromProp: "+LogComponenetFromProp);
		log.info("LogLinesFromProp: "+LogLinesFromProp);
		log.info("CurlMessageParam: "+CurlMessageParam);
		log.info("CountryCodeNetworkCodeFromProp:" +countryCodeNetworkCodeFromProp);
		log.info("reseller_numberFromProp:" +reseller_numberFromProp);
		log.info("reseller_destination_from_prop:" +reseller_destination_from_prop);

		
		log.info("=======================>>>>>>>>>>>>Perform String Base R2R with Valid Data through SMS<<<<<<<<<<<=====================");		 
    	String generateBasicCurl = common.returnCurlForSMS();
    	String fullCurl = generateBasicCurl+"&from="+SenderNumberFromProp+"&msg="+CurlMessageParam+"+"+AmountFromProp+"+"+reseller_destination_from_prop+"+"+ResellerPinFromProp;	
    	log.info("R2R.java: "+fullCurl+'"');
    	
    	/*****************Reseller Balance Fetched before transaction****************/
    	log.info("*****************Reseller Balance Fetched before transaction****************");
    	log.info("R2R >> Sender resellerID: "+ResellerIdFromProp);
    	log.info("R2R >> Reciver ResellerID: "+ReceiverResellerIDFromProp);
    	
    	double current_amount_sender=(double) resultSet.returnResellerAmount(ResellerIdFromProp);
    	log.info("Sender Reseller current balance before transaction: "+current_amount_sender); 
    	
    	double current_amount_reciever=(double) resultSet.returnResellerAmount(ReceiverResellerIDFromProp);
    	log.info("Reciver Reseller current balance before transaction: "+current_amount_reciever);
        
        /*****************Create SSH Connection and execute the command****************/
        log.info("===========>>>>Create SSH Connection");
        common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(fullCurl + "\" \n");
        common.createChannel();

        common.SleepForSomeTime(5000);
        common.closeSSHConnection();
        
        /*****************Create SSH Connection and execute the command****************/
        log.info("===========>>>>Create SSH Connection");
        String logreading = common.returnCommandForLogReading(LogLinesFromProp, LogComponenetFromProp);
        common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(logreading);
        common.createChannel();
       
        
        /*****************Transaction/Amount Checking****************/
        boolean state = common.Result.contains(ResponseMessageFromProp);
        log.info(common.Result.contains(ResellerIdFromProp));
        log.info(ResellerIdFromProp+"..."+state);
        log.info(common.Result);
        
        log.info("\n=====================Transaction Status===========================\n");
	    try{    
	    	Assert.assertTrue(state);
        	if(state == true){
	        	log.info("Message: "+ResponseMessageFromProp);
	        	log.info("Notification message found in the logs");
	        	log.info("Sender Reseller Balance After Successfull Transaction "+resultSet.returnResellerAmount(ResellerIdFromProp));
	        	log.info("Receiver Reseller Balance After Successfull Transaction "+resultSet.returnResellerAmount(ReceiverResellerIDFromProp));
	            double remainingBal = current_amount_sender - Integer.parseInt(AmountFromProp);
	            double newBalanceReciever=current_amount_reciever + Integer.parseInt(AmountFromProp);

	            log.info("its in else state");
	            double getSenderAmount = resultSet.returnResellerAmount(ResellerIdFromProp);
	            double getReceiverAmount = resultSet.returnResellerAmount(ReceiverResellerIDFromProp);
	            
            	Assert.assertEquals(remainingBal, getSenderAmount, Delta);
            	log.info(" Sender Balance is deducted");
            	Assert.assertEquals(newBalanceReciever, getReceiverAmount, Delta);
            	log.info("Reciver Balance is added");
	        }
	        else{
	        	log.error("Notification message not found in the logs");
	        	log.error("\nTransaction Not Successfull ");
	        	log.info(" Sender Reseller Balance After Failed Transaction "+resultSet.returnResellerAmount(ResellerIdFromProp));
	        	log.info("Reciever Reseller Balance After Failed Transaction "+resultSet.returnResellerAmount(ReceiverResellerIDFromProp));
	        	return;
			}
    	}finally{
		log.info("\n"+ common.Result);
		common.closeSSHConnection();
		
		}
	}
}