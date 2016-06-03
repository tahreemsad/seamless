package com.seamless.ers.qa.cucumber.ImplementationClasses.USSD;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.seamless.ers.qa.cucumber.ImplementationClasses.Common.CucumberCommon;
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadConfigFromProperties;
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadUssdAppConfigFromProperties;
import com.seamless.ers.qa.cucumber.SQLQueries.SQLResultSets;

import cucumber.api.java.en.When;

public class USSDAppRequestReversal {
	
	
	ReadUssdAppConfigFromProperties readUssd = ReadUssdAppConfigFromProperties.getInstance();
    ReadConfigFromProperties readConfig = new ReadConfigFromProperties();

	public static Logger log = LogManager.getLogger( USSDAppRequestReversal.class );     
	SQLResultSets resultSet = new SQLResultSets();
	CucumberCommon common = new CucumberCommon();
	CucumberCommon commonForReversal = new CucumberCommon();
	CucumberCommon commonForApproval = new CucumberCommon();
	CucumberCommon commonForDenial = new CucumberCommon();
	public WebDriver webdriver;
	String countryCodeNetworkCodeFromProp;
	public double Delta = 1e-8;

        @When ("^I Request Reversal from reseller (.+) having resellerID (.+) with shortcode (.+) and menuoption (.+) and pin is (.+) and message is (.+) for amount (.+) component is (.+) and number of log lines are (.+) and notification message is (.+)$")
        public void Request_Reversal(String reseller_number,String resellerID, String RRshortcode, String RRmenuoption, String RRpin, String message, String amount, String logComponenet, String loglines,String notification) throws IOException, SQLException, JSchException{
    		
        	
        	log.info("=======================>>>>>>>>>>>>Perform String Base RR<<<<<<<<<<<=====================");
	            String resellernumberFromProp = readUssd.returnValueForUssd(reseller_number);
				countryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");
	            String reseller_numberFromProp = countryCodeNetworkCodeFromProp+resellernumberFromProp;
    		 	String resellerIDFromProp = readUssd.returnValueForUssd(resellerID);
    	        String RRshortcodeFromProp =readUssd.returnValueForUssd(RRshortcode);
    	        String RRmenuoptionFromProp =readUssd.returnValueForUssd(RRmenuoption);
    	        String RRpinFromProp =readUssd.returnValueForUssd(RRpin);
    	        String message_from_prop =readUssd.returnValueForUssd(message);
   	            String amount_from_prop =readUssd.returnValueForUssd(amount);
   	            String LogComponenetFromProp = readUssd.returnValueForUssd(logComponenet);
   	            String LogLinesFromProp = readUssd.returnValueForUssd(loglines);
   	            String NotificationFromProp = readUssd.returnValueForUssd(notification);
	        
    	        log.info("Sender:" +reseller_numberFromProp);
    	        log.info("Sender ID:" +resellerIDFromProp);
    	        log.info("RRshortcode:" + RRshortcodeFromProp);
    	        log.info("RRmenuoption:" +RRmenuoptionFromProp);
    			log.info("RRpin:" +RRpinFromProp);
    			log.info("MessageFromProp:" +message_from_prop);
    			log.info("AmountFromProp:" +amount_from_prop);
    			log.info("LogComponenetFromProp:" +LogComponenetFromProp);
    			log.info("LogLinesFromProp:" +LogLinesFromProp);
    			log.info("NotificationFromProp:" +NotificationFromProp);
    			log.info("CountryCodeNetworkCodeFromProp:" +countryCodeNetworkCodeFromProp);
    			log.info("reseller_numberFromProp:" +reseller_numberFromProp);

    			String InvalidHierarchy = readUssd.returnValueForUssd("InvalidHierarchy");
            	String ContractViolation = readUssd.returnValueForUssd("ContractViolation");
            	String Invalidpin = readUssd.returnValueForUssd("InvalidPin");
            	String UnRegisterUserCreditTrasnferViolation = readUssd.returnValueForUssd("UnRegisterUserCreditTrasnferViolation");
            	String InvalidR2RTransfer = readUssd.returnValueForUssd("InvalidR2RTransfer");
            	String InvalidStateMessage = readUssd.returnValueForUssd("INVALID_STATE_MESSAGE");
            	String RrTransferAgainMessage = readUssd.returnValueForUssd("RR_TRANSFER_AGAIN_MESSAGE");
            	String RrOwnReversalMessage = readUssd.returnValueForUssd("RR_OWN_REVERSAL_MESSAGE");
                
    		
    			/***************** Create SSH Connection and execute the command ****************/
    			log.info("===========>>>>Create SSH Connection");
    			commonForReversal.createSSHCoonection();
    			commonForReversal.createChannel();
    			
    			String transactionID = resultSet.getErsReference("0,1");
    			log.info("transactionID: " +transactionID);
    			
    			/*****************Reseller Balance Fetched before transaction****************/
            	log.info("*****************Reseller Balance Fetched before transaction****************");
            	
            	double beforeTransforsender=resultSet.returnResellerAmount(resellerIDFromProp);
                log.info("Reseller current balance before transaction: "+beforeTransforsender);
				
    			String fullCurl1 = commonForReversal.returnFullCurl(reseller_numberFromProp,RRshortcodeFromProp); 
    	    	String curl1 = fullCurl1+"&p1="+RRmenuoptionFromProp+"&p2="+transactionID+"&p3="+RRpinFromProp;
    	    	log.info("RR.java: "+curl1+'"');
    	    	
    			commonForReversal.createSSHCoonection();
    	        ((ChannelExec) commonForReversal.channel).setCommand(curl1 + "\" \n");

    			commonForReversal.createChannel();
    			log.info("\n" + commonForReversal.Result);
    	    	
    			/*****************Create SSH Connection and execute the log command****************/
    	        log.info("===========>>>>Create SSH Connection for log command");
    	        String logData = commonForDenial.returnCommandForLogReading(LogLinesFromProp, LogComponenetFromProp);
    	        commonForDenial.createSSHCoonection();
    	        ((ChannelExec) commonForDenial.channel).setCommand(logData);
    	        commonForDenial.createChannel();
    	        log.info("commonForDenial.Result..."+commonForDenial.Result);        
    	        boolean stateoflog = commonForDenial.Result.contains(NotificationFromProp);
    	        commonForDenial.closeSSHConnection();
    	        
    	        log.info("message from log: "+commonForDenial.Result);
    	        log.info("notificationMessage: "+NotificationFromProp);
    	    
    	        if(stateoflog == true){
    	        		log.info("Notification message verified from logs");
    	        }else{
    	        		log.error("Error validating the notification message");
    	        }
    	        
				/*****************Transaction/Amount Checking****************/
	            boolean state = commonForReversal.Result.contains(message_from_prop);
	            log.info(commonForReversal.Result.contains(message_from_prop));
	            log.info(message_from_prop+"..."+state);
	            
	            log.info("\n=====================Transaction Status===========================\n");
	            try{
	            	Assert.assertTrue(state);
		            if(state == true){
		            	double amountFromDBAfterTran = resultSet.returnResellerAmount(resellerIDFromProp);
		            	log.info("\nTransaction is Successfull ");
		            	log.info("Reseller Balance After Successfull Transaction "+amountFromDBAfterTran);

		                if(message_from_prop.contains(Invalidpin) 
		                		|| message_from_prop.contains(ContractViolation) 
		                		|| message_from_prop.contains(InvalidHierarchy) 
		                		|| message_from_prop.contains(UnRegisterUserCreditTrasnferViolation)
		                		|| message_from_prop.contains(InvalidR2RTransfer)
		                		|| message_from_prop.contains(InvalidStateMessage)
		                		|| message_from_prop.contains(RrTransferAgainMessage)
		                		|| message_from_prop.contains(RrOwnReversalMessage)){
		                	Assert.assertEquals(beforeTransforsender, resultSet.returnResellerAmount(resellerIDFromProp), Delta);
		                	log.info("Balance is not deducted");
		                }else{
		                	Assert.assertEquals(beforeTransforsender, amountFromDBAfterTran, Delta);
		                	log.info("Balance is deducted");
		                }
		            }
		            
		            else{
		            	log.error("\nTransaction Not Successfull ");
		            	log.info("Reseller Balance After Failed Transaction "+resultSet.returnResellerAmount(resellerIDFromProp));
		            	return;
				}
			}
	            finally{
				log.info("\n" + commonForReversal.Result);
				commonForReversal.closeSSHConnection();
	            }
        }

        @When ("^I request to approve from reseller (.+) with reseller id (.+) to reseller id (.+) with shortcode (.+) and menuoption (.+) and pin is (.+) and message is (.+) for amount (.+) component is (.+) and number of log lines are (.+) and notification message is (.+)$")
        public void ApproveRequest(String reciever_number,String resellerID,String senderID,String RRshortcode1, String RRmenuoption1, String RRpin1,String message, String amount, String logComponenet, String loglines,String notification) throws IOException, SQLException, JSchException{
        
        log.info("=======================>>>>>>>>>>>>Perform String Base RR<<<<<<<<<<<=====================");
	 	String reciever_numberFromProp = readUssd.returnValueForUssd(reciever_number);
		countryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");
	 	reciever_numberFromProp = countryCodeNetworkCodeFromProp+reciever_numberFromProp;
        String resellerIDFromProp =readUssd.returnValueForUssd(resellerID);
        String SenderIDFromProp =readUssd.returnValueForUssd(senderID);
        String RRshortcodeFromProp1 =readUssd.returnValueForUssd(RRshortcode1);
        String RRmenuoptionFromProp1 =readUssd.returnValueForUssd(RRmenuoption1);
        String RRpinFromProp1 =readUssd.returnValueForUssd(RRpin1);
        String message_from_prop =readUssd.returnValueForUssd(message);
        String amount_from_prop =readUssd.returnValueForUssd(amount);
        String LogLinesFromProp = readUssd.returnValueForUssd(loglines);
        String LogComponenetFromProp = readUssd.returnValueForUssd(logComponenet);
        String NotificationFromProp = readUssd.returnValueForUssd(notification);
        
        log.info("Reciever:" +reciever_numberFromProp);
        log.info("Sender:" +SenderIDFromProp);
        log.info("resellerID:" +resellerIDFromProp);
        log.info("RRshortcode:" + RRshortcodeFromProp1);
        log.info("RRmenuoption:" +RRmenuoptionFromProp1);
		log.info("RRpin:" +RRpinFromProp1);
		log.info("MessageFromProp:" +message_from_prop);
		log.info("AmountFromProp:" +amount_from_prop);
		log.info("LogComponenetFromProp:" +LogComponenetFromProp);
		log.info("LogLinesFromProp:" +LogLinesFromProp);
		log.info("NotificationFromProp:" +NotificationFromProp);
		
		String InvalidHierarchy = readUssd.returnValueForUssd("InvalidHierarchy");
    	String ContractViolation = readUssd.returnValueForUssd("ContractViolation");
    	String Invalidpin = readUssd.returnValueForUssd("InvalidPin");
    	String UnRegisterUserCreditTrasnferViolation = readUssd.returnValueForUssd("UnRegisterUserCreditTrasnferViolation");
    	String InvalidR2RTransfer = readUssd.returnValueForUssd("InvalidR2RTransfer");
    	String InvalidStateMessage = readUssd.returnValueForUssd("INVALID_STATE_MESSAGE");
    	String RrTransferAgainMessage = readUssd.returnValueForUssd("RR_TRANSFER_AGAIN_MESSAGE");
    	String RrOwnReversalMessage = readUssd.returnValueForUssd("RR_OWN_REVERSAL_MESSAGE");
        
		/***************** Create SSH Connection and execute the command ****************/
		log.info("===========>>>>Create SSH Connection");
		commonForApproval.createSSHCoonection();
		commonForApproval.createChannel();
	 
		String transactionID = resultSet.getErsReference("0,1");
		log.info("transactionID: " +transactionID);

		/*****************Reseller Balance Fetched before transaction****************/
    	log.info("*****************Reseller Balance Fetched before transaction****************");
    	
    	double beforeTransforsender=resultSet.returnResellerAmount(resellerIDFromProp);
        log.info("Reciever current balance before transaction: "+beforeTransforsender);		
        
        double beforeTransforreciever=resultSet.returnResellerAmount(SenderIDFromProp);
        log.info("Sender current balance before transaction: "+beforeTransforreciever);
        
		String fullCurl = commonForApproval.returnFullCurl(reciever_numberFromProp,RRshortcodeFromProp1); 
    	String curl = fullCurl+"&p1="+RRmenuoptionFromProp1+"&p2="+transactionID+"&p3="+RRpinFromProp1;
    	log.info("RR.java: "+curl+'"');
    	
		commonForApproval.createSSHCoonection();
        ((ChannelExec) commonForApproval.channel).setCommand(curl + "\" \n");
		commonForApproval.createChannel();
		log.info("\n" + commonForApproval.Result);
    	
    	/*****************Transaction/Amount Checking****************/
        boolean state = commonForApproval.Result.contains(message_from_prop);
        log.info(commonForApproval.Result.contains(message_from_prop));
        log.info(message_from_prop+"..."+state);
        
        log.info("\n=====================Transaction Status===========================\n");
        try{
        	Assert.assertTrue(state);
            if(state == true){
            	double amountFromDBAfterTran = resultSet.returnResellerAmount(resellerIDFromProp);
            	double amountFromDBAfterTran1 = resultSet.returnResellerAmount(SenderIDFromProp);

                double remainingBalManualCalculatedforsender = beforeTransforsender - Integer.parseInt(amount_from_prop);
                log.info("Reseller Balance After Successfull Transaction of reciever "+amountFromDBAfterTran);
                
                double remainingBalManualCalculatedforreciever = beforeTransforreciever + Integer.parseInt(amount_from_prop);
                log.info("Reseller Balance After Successfull Transaction of sender "+amountFromDBAfterTran);

            	log.info("\nTransaction is Successfull ");
            	
            	/*****************Create SSH Connection and execute the log command****************/
                log.info("===========>>>>Create SSH Connection for log command");
                String logData = commonForDenial.returnCommandForLogReading(LogLinesFromProp, LogComponenetFromProp);
                commonForDenial.createSSHCoonection();
                ((ChannelExec) commonForDenial.channel).setCommand(logData);
                commonForDenial.createChannel();
                log.info("commonForDenial.Result..."+commonForDenial.Result);        
                boolean stateoflog = commonForDenial.Result.contains(NotificationFromProp);
                commonForDenial.closeSSHConnection();
                
                log.info("message from log: "+commonForDenial.Result);
                log.info("notificationMessage: "+NotificationFromProp);
            
                if(stateoflog == true){
                		log.info("Notification message verified from logs");
                }else{
                		log.error("Error validating the notification message");
                }

                if(message_from_prop.contains(Invalidpin) 
                		|| message_from_prop.contains(ContractViolation) 
                		|| message_from_prop.contains(InvalidHierarchy) 
                		|| message_from_prop.contains(UnRegisterUserCreditTrasnferViolation)
                		|| message_from_prop.contains(InvalidR2RTransfer)
                		|| message_from_prop.contains(InvalidStateMessage)
                		|| message_from_prop.contains(RrTransferAgainMessage)
                		|| message_from_prop.contains(RrOwnReversalMessage)){
                	
                	Assert.assertEquals(beforeTransforsender, resultSet.returnResellerAmount(resellerIDFromProp),Delta);
                	log.info("Balance is not deducted");
                }else{
                 	Assert.assertEquals(remainingBalManualCalculatedforsender, amountFromDBAfterTran, Delta);
                	Assert.assertEquals(remainingBalManualCalculatedforreciever, amountFromDBAfterTran1, Delta);
                	log.info("Balance is deducted");
                }
            }              
            
            else{
            	log.error("\nTransaction Not Successfull ");
            	log.info("Reseller Balance After Failed Transaction "+resultSet.returnResellerAmount(resellerIDFromProp));
            	return;
            	}
    	
        }finally{
		log.info("\n" + commonForApproval.Result);
		commonForApproval.closeSSHConnection();
        }
        
    }
        
        @When ("^I request to deny from reseller (.+) with reseller id (.+) to reseller id (.+) with shortcode (.+) and menuoption (.+) and pin is (.+) and message is (.+) for amount (.+) component is (.+) and number of log lines are (.+) and notification message is (.+)$")
        public void DenyRequest(String reciever_number,String resellerID,String senderID,String RRshortcode1, String RRmenuoption1, String RRpin1, String message, String amount, String logComponenet, String loglines,String notification) throws IOException, SQLException, JSchException{
        
        log.info("=======================>>>>>>>>>>>>Perform String Base RR<<<<<<<<<<<=====================");
	 	String reciever_numberFromProp = readUssd.returnValueForUssd(reciever_number);
		countryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");
	 	reciever_numberFromProp=countryCodeNetworkCodeFromProp+reciever_numberFromProp;
        String RRshortcodeFromProp1 =readUssd.returnValueForUssd(RRshortcode1);
        String resellerIDFromProp =readUssd.returnValueForUssd(resellerID);
        String senderIDFromProp =readUssd.returnValueForUssd(senderID);
        String RRmenuoptionFromProp1 =readUssd.returnValueForUssd(RRmenuoption1);
        String RRpinFromProp1 =readUssd.returnValueForUssd(RRpin1);
        String message_from_prop =readUssd.returnValueForUssd(message);
        String amount_from_prop =readUssd.returnValueForUssd(amount);
        String LogComponenetFromProp = readUssd.returnValueForUssd(logComponenet);
        String LogLinesFromProp = readUssd.returnValueForUssd(loglines);
        String NotificationFromProp = readUssd.returnValueForUssd(notification);
       
        String InvalidHierarchy = readUssd.returnValueForUssd("InvalidHierarchy");
    	String ContractViolation = readUssd.returnValueForUssd("ContractViolation");
    	String Invalidpin = readUssd.returnValueForUssd("InvalidPin");
    	String UnRegisterUserCreditTrasnferViolation = readUssd.returnValueForUssd("UnRegisterUserCreditTrasnferViolation");
    	String InvalidR2RTransfer = readUssd.returnValueForUssd("InvalidR2RTransfer");
    	String InvalidStateMessage = readUssd.returnValueForUssd("INVALID_STATE_MESSAGE");
    	String RrTransferAgainMessage = readUssd.returnValueForUssd("RR_TRANSFER_AGAIN_MESSAGE");
    	String RrOwnReversalMessage = readUssd.returnValueForUssd("RR_OWN_REVERSAL_MESSAGE");
        
        log.info("Sender:" +reciever_numberFromProp);
        log.info("resellerID:" +resellerIDFromProp);
        log.info("RRshortcode:" + RRshortcodeFromProp1);
        log.info("RRmenuoption:" +RRmenuoptionFromProp1);
		log.info("RRpin:" +RRpinFromProp1);
		log.info("MessageFromProp:" +message_from_prop);
		log.info("AmountFromProp:" +amount_from_prop);
		log.info("LogComponenetFromProp:" +LogComponenetFromProp);
		log.info("LogLinesFromProp:" +LogLinesFromProp);
		log.info("NotificationFromProp:" +NotificationFromProp);
		
		/*****************Reseller Balance Fetched before transaction****************/
    	log.info("*****************Reseller Balance Fetched before transaction****************");
    	
    	double beforeTransforsender=resultSet.returnResellerAmount(resellerIDFromProp);
        log.info("Reciever current balance before transaction: "+beforeTransforsender);		
        
        double beforeTransforreciever=resultSet.returnResellerAmount(senderIDFromProp);
        log.info("Sender current balance before transaction: "+beforeTransforreciever);
		
		
		/***************** Create SSH Connection and execute the command ****************/
		log.info("===========>>>>Create SSH Connection");
		commonForDenial.createSSHCoonection();
		commonForDenial.createChannel();
	
		String transactionID = resultSet.getErsReference("0,1");
		log.info("transactionID: " +transactionID);

		commonForDenial.closeSSHConnection(); 
		
		String fullCurl = commonForDenial.returnFullCurl(reciever_numberFromProp,RRshortcodeFromProp1); 
    	String curl = fullCurl+"&p1="+RRmenuoptionFromProp1+"&p2="+transactionID+"&p3="+RRpinFromProp1;
    	log.info("RR.java: "+curl+'"');
    	
    	commonForDenial.createSSHCoonection();
        ((ChannelExec) commonForDenial.channel).setCommand(curl + "\" \n");
        commonForDenial.createChannel();
		log.info("\n" + commonForDenial.Result);
		

    	/*****************Transaction/Amount Checking****************/
        boolean state = commonForDenial.Result.contains(message_from_prop);
        log.info(commonForDenial.Result.contains(message_from_prop));
        log.info(message_from_prop+"..."+state);
		
		try{
        	Assert.assertTrue(state);
            if(state == true){
            	double amountFromDBAfterTranforsender = resultSet.returnResellerAmount(resellerIDFromProp);
            	double amountFromDBAfterTranforreciever = resultSet.returnResellerAmount(senderIDFromProp);

            	log.info("Reseller Balance After Successfull Transaction "+amountFromDBAfterTranforsender);

                if(message_from_prop.contains(Invalidpin) 
                		|| message_from_prop.contains(ContractViolation) 
                		|| message_from_prop.contains(InvalidHierarchy) 
                		|| message_from_prop.contains(UnRegisterUserCreditTrasnferViolation)
                		|| message_from_prop.contains(InvalidR2RTransfer)
                		|| message_from_prop.contains(InvalidStateMessage)
                		|| message_from_prop.contains(RrTransferAgainMessage)
                		|| message_from_prop.contains(RrOwnReversalMessage)){
                	
                	Assert.assertEquals(beforeTransforsender, resultSet.returnResellerAmount(resellerIDFromProp),Delta);
                	Assert.assertEquals(beforeTransforreciever, resultSet.returnResellerAmount(senderIDFromProp), Delta);
                	

                }else{
                	Assert.assertEquals(beforeTransforsender, amountFromDBAfterTranforsender, Delta);
                	Assert.assertEquals(beforeTransforreciever, amountFromDBAfterTranforreciever, Delta);

                	log.info("Balance is not deducted");
                }

                	log.info("Balance is not deducted");
                	log.error("\nTransaction Not Successfull ");
            }
            else{
            	log.error("\nTransaction Not Successfull ");
            	log.info("Reseller Balance After Failed Transaction "+resultSet.returnResellerAmount(resellerIDFromProp));
            	return;
            	}
            
        if(state==true){
    	/*****************Create SSH Connection and execute the log command****************/
        log.info("===========>>>>Create SSH Connection for log command");
        String logData = commonForDenial.returnCommandForLogReading(LogLinesFromProp, LogComponenetFromProp);
        commonForDenial.createSSHCoonection();
        ((ChannelExec) commonForDenial.channel).setCommand(logData);
        commonForDenial.createChannel();
        log.info("commonForDenial.Result..."+commonForDenial.Result);        
        boolean stateoflog = commonForDenial.Result.contains(NotificationFromProp);
        commonForDenial.closeSSHConnection();
        
        log.info("message from log: "+commonForDenial.Result);
        log.info("notificationMessage: "+NotificationFromProp);
    
        if(stateoflog == true){
        		log.info("Notification message verified from logs");
        }else{
        		log.error("Error validating the notification message");
        }
             }
    	
        }
	        finally{
			log.info("\n" + commonForDenial.Result);
			commonForDenial.closeSSHConnection();
        }

     }

 }    



