package com.seamless.ers.qa.cucumber.ImplementationClasses.WebService;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadConfigFromProperties;
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadWSProperties;
import com.seamless.ers.qa.cucumber.SQLQueries.SQLResultSets;

import cucumber.api.java.en.When;

public class RequestTransferERS_STD {
	public static Node getAmount,receiverAmountFromTree,responseMessage;
	
	public static Logger log = LogManager.getLogger( RequestTopupERS_STD.class );
	public static SQLResultSets resultSet = new SQLResultSets();
	public static ReadConfigFromProperties readconfig = new ReadConfigFromProperties();
	public static ReadWSProperties ws = new ReadWSProperties();
	public static double Delta = 1e-8;
	public static double senderResellerBeforeBalance; 
	public static double recieverResellerBeforeBalance;
	public static double ConvertSenderResellerBeforeBalanceToString; 
	public static double ConvertRecieverResellerBeforeBalanceToString;
	public static String ResellerId1FromProp, ResellerTypeFromProp, ResellerUserIDFromProp, ResellerPwdFromProp, ResellerAmountFromProp, ReceiverIdFromProp, ResponseMessageFromProp;
	
	@When("^Reseller transfers to reseller having resellerId (.+) resellerType (.+) userId (.+) password (.+) amount (.+) and receiverId is (.+) and in response (.+) message is received$")
	public static void SOAP(String resellerId, String resellertype, String userId, String password, String amount, String receiverId, String CucmberMessage) throws Exception {
    	// Create SOAP Connection

    	try{
			ResellerId1FromProp = ws.returnPropertyForWS(resellerId);
			ResellerTypeFromProp = ws.returnPropertyForWS(resellertype);
			ResellerUserIDFromProp = ws.returnPropertyForWS(userId);
			ResellerPwdFromProp = ws.returnPropertyForWS(password);
			ResellerAmountFromProp = ws.returnPropertyForWS(amount);
			ReceiverIdFromProp = ws.returnPropertyForWS(receiverId);
			ResponseMessageFromProp = ws.returnPropertyForWS(CucmberMessage);
			
			log.info("ResellerId1FromProp: "+ResellerId1FromProp);
			log.info("ResellerTypeFromProp: "+ResellerTypeFromProp);
			log.info("ResellerUserIDFromProp: "+ResellerUserIDFromProp);
			log.info("ResellerPwdFromProp: "+ResellerPwdFromProp);
			log.info("ResellerAmountFromProp: "+ResellerAmountFromProp);
			log.info("ReceiverIdFromProp: "+ReceiverIdFromProp);
			log.info("ResponseMessageFromProp: "+ResponseMessageFromProp);
	    	
			senderResellerBeforeBalance = resultSet.returnResellerAmount(ResellerId1FromProp);
			
			ConvertSenderResellerBeforeBalanceToString = senderResellerBeforeBalance;
			recieverResellerBeforeBalance=resultSet.returnResellerAmount(ReceiverIdFromProp);
			ConvertRecieverResellerBeforeBalanceToString = recieverResellerBeforeBalance;
			
	        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
	        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
	        // Send SOAP Message to SOAP Server
	        String wsdl = "http://"+System.getProperty("targetEnvironment")+":8913/topupservice/service?wsdl";
	        log.info("WSDL: "+wsdl);
	   
	        SOAPMessage WebserviceResponse = soapConnection.call(requestVoucherPurchase(ResellerId1FromProp, ResellerTypeFromProp, ResellerUserIDFromProp, ResellerPwdFromProp, ResellerAmountFromProp, ReceiverIdFromProp), wsdl);
	        printOutput(WebserviceResponse, ResponseMessageFromProp, ResellerId1FromProp);
	        soapConnection.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }

    public static SOAPMessage requestVoucherPurchase(String resellerId, String resellertype, String user_Id, String resellerpassword, String transferAmount, String receiverId) throws Exception {
    	
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        
        String xmlns_soapen = readconfig.readPropertiesforConfig("xmlns_soapen");
        String xmlns_ex = readconfig.readPropertiesforConfig("xmlns_ex");
        
        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.setPrefix("soapenv");
        envelope.removeNamespaceDeclaration("SOAP-ENV");
        envelope.addNamespaceDeclaration("ns0", xmlns_soapen);
        envelope.addNamespaceDeclaration("ext", xmlns_ex );
        SOAPHeader header = envelope.getHeader();
        header.setPrefix("soapenv");
        SOAPBody ext = envelope.getBody();
        ext.setPrefix("soapenv");
        
        SOAPElement RequestPurchase = ext.addChildElement("requestTransfer","ext");
        
        SOAPElement context = RequestPurchase.addChildElement("context");
        
        SOAPElement initiatorPrincipalId = context.addChildElement("initiatorPrincipalId");
        	
        	SOAPElement id = initiatorPrincipalId.addChildElement("id");
        		id.addTextNode(resellerId);
    		SOAPElement type = initiatorPrincipalId.addChildElement("type");
        		type.addTextNode(resellertype);
    		SOAPElement userId = initiatorPrincipalId.addChildElement("userId");
        		userId.addTextNode(user_Id);
		
		SOAPElement password = context.addChildElement("password");
    		password.addTextNode(resellerpassword);
        
		SOAPElement senderPrincipalId = RequestPurchase.addChildElement("senderPrincipalId");
				
			SOAPElement senderPrincipalId_ID = senderPrincipalId.addChildElement("id");
				senderPrincipalId_ID.addTextNode(resellerId);
			SOAPElement senderPrincipalId_Type = senderPrincipalId.addChildElement("type");
				senderPrincipalId_Type.addTextNode(resellertype);
			SOAPElement senderPrincipalId_UserId = senderPrincipalId.addChildElement("userId");
				senderPrincipalId_UserId.addTextNode("9900");
        		
		SOAPElement receiverPrincipalId = RequestPurchase.addChildElement("receiverPrincipalId");
    			
			SOAPElement transferPrnId = receiverPrincipalId.addChildElement("id");
				transferPrnId.addTextNode(receiverId);
			SOAPElement transferPrntype = receiverPrincipalId.addChildElement("type");
				transferPrntype.addTextNode(resellertype);
			SOAPElement transferPrnUserId = receiverPrincipalId.addChildElement("userId");
				transferPrnUserId.addTextNode("9900");	
				
    	SOAPElement senderAccountSpecifier = RequestPurchase.addChildElement("senderAccountSpecifier");
    		
    		SOAPElement accountId_Id = senderAccountSpecifier.addChildElement("accountId");
        		accountId_Id.addTextNode(resellerId);    		
    		SOAPElement accountId_Type = senderAccountSpecifier.addChildElement("accountTypeId");
        		accountId_Type.addTextNode("RESELLER");
        		
    	SOAPElement receiverAccountSpecifier = RequestPurchase.addChildElement("receiverAccountSpecifier");
        		
    		SOAPElement Transfer_accountId_Id = receiverAccountSpecifier.addChildElement("accountId");
        		Transfer_accountId_Id.addTextNode(receiverId);    		
    		SOAPElement TransferaccountId_Type = receiverAccountSpecifier.addChildElement("accountTypeId");
        		TransferaccountId_Type.addTextNode("RESELLER");
        		
		SOAPElement productId = RequestPurchase.addChildElement("productId");
			productId.addTextNode("Transfer");
		
    	SOAPElement amount = RequestPurchase.addChildElement("amount");
        		
    		SOAPElement amount_currency = amount.addChildElement("currency");
        		amount_currency.addTextNode("USD");    		
    		SOAPElement amount_value = amount.addChildElement("value");
        		amount_value.addTextNode(transferAmount);
        		
//        MimeHeaders headers = soapMessage.getMimeHeaders();
//        headers.addHeader("SOAPAction", xmlns_ex  + "requestTransfer");

        soapMessage.saveChanges();
        soapMessage.writeTo(System.out);
        return soapMessage;
    }
        
	public static SOAPMessage printOutput(SOAPMessage Serviceresponse, String cucmberResponse, String resellerId)throws Exception {
		SOAPBody soapBody = Serviceresponse.getSOAPBody();
		if (Serviceresponse != null) {
			String amount=null,receiverAmount=null,tokenizedAmount=null, tokenizedRecieverAmount=null;
			double SenderResellerBalanceAfter = resultSet.returnResellerAmount(resellerId);
			String ConvertAfterBalanceToString = Double.toString(SenderResellerBalanceAfter);
			double ReciveverResellerBalanceAfter= resultSet.returnResellerAmount(ReceiverIdFromProp);
			String ConvertAfterBalanceToStringForReciever=Double.toString(ReciveverResellerBalanceAfter);
			
			String INVALID_RESELLER  =  ws.returnPropertyForWS("INVALID_RESELLER");
			String INVALID_PASSWORD = ws.returnPropertyForWS("INVALID_PASSWORD");
			String INVALID_USERID = ws.returnPropertyForWS("INVALID_USERID");
			String INVALID_AMOUNT = ws.returnPropertyForWS("INVALID_AMOUNT");
			String INVALID_PRINCIPAL_TYPE = ws.returnPropertyForWS("INVALID_PRINCIPAL_TYPE");
			String INVALID_HIERARCHY = ws.returnPropertyForWS("INVALID_HIERARCHY");
			
			log.info("response.getSOAPPart(): "+Serviceresponse.getSOAPPart().getContent());
			
			TransformerFactory tff = TransformerFactory.newInstance();
	        Transformer tf = tff.newTransformer();
	        
			Source sc = Serviceresponse.getSOAPPart().getContent();
			StreamResult result = new StreamResult(System.out);
			tf.transform(sc, result);
			
			NodeList bodyChild = Serviceresponse.getSOAPBody().getChildNodes();
			log.info("test"+bodyChild);
			NodeList requestPurchaser = (NodeList) bodyChild.item(0);

			responseMessage = requestPurchaser.item(0).getChildNodes().item(2);
			String webServiceResponse = responseMessage.getTextContent();
			
			Assert.assertEquals(cucmberResponse, webServiceResponse);
			log.info("\nResponse from webService=" + webServiceResponse+ "\nResponse from Cucumber= "+cucmberResponse);
		
			if(INVALID_RESELLER.equals(webServiceResponse)){
				log.info("Reseller Not Found");
			}else{
				if(
					    INVALID_PASSWORD.equals(webServiceResponse) 
					|| (INVALID_USERID.equals(webServiceResponse) 
					|| (INVALID_AMOUNT.equals(webServiceResponse)
					|| (INVALID_PRINCIPAL_TYPE.equals(webServiceResponse)
					|| (INVALID_HIERARCHY.equals(webServiceResponse)))))){
					    Assert.assertEquals(SenderResellerBalanceAfter, ConvertSenderResellerBeforeBalanceToString, Delta);
						log.info("Reason: "+webServiceResponse);
						  Assert.assertEquals(ReciveverResellerBalanceAfter, ConvertRecieverResellerBeforeBalanceToString, Delta);
							log.info("Reason: "+webServiceResponse);
						log.info("Sender Reseller Balance before transaction: "+ConvertSenderResellerBeforeBalanceToString+"\nSender Reseller Balance after transaction: "+ConvertAfterBalanceToString);
						log.info("Reciever Reseller Balance before transaction: "+ConvertRecieverResellerBeforeBalanceToString+"\nReciever Reseller Balance after transaction: "+ConvertAfterBalanceToStringForReciever);
						
				}else{

						    getAmount = requestPurchaser.item(0).getChildNodes().item(6).getChildNodes().
						    		item(2).getChildNodes().item(0).getChildNodes().item(1).getChildNodes().item(1).getChildNodes().item(0);						   
						    amount = getAmount.getTextContent().toString();
						    
						    receiverAmountFromTree = requestPurchaser.item(0).getChildNodes().item(4).getChildNodes().item(2).getChildNodes().item(0).getChildNodes().item(1).
						    		getChildNodes().item(1).getChildNodes().item(0);
						    receiverAmount = receiverAmountFromTree.getTextContent().toString();
						    
						    log.info("receiver amount from tree: "+receiverAmount);
						    
						    tokenizedRecieverAmount=receiverAmount.substring(0,receiverAmount.indexOf("."));
						    
						    tokenizedAmount = amount.substring(0,amount.indexOf("."));
						    
						    log.info("tokenizedRecieverAmount:"+tokenizedRecieverAmount);
							Assert.assertEquals(ConvertAfterBalanceToString, tokenizedAmount);
							log.info("\nAmount returned from webService:" + tokenizedAmount+"\nAmount returned from DataBase: "+resultSet.returnResellerAmount(resellerId));
							Assert.assertEquals(tokenizedRecieverAmount,Double.toString(resultSet.returnResellerAmount(ReceiverIdFromProp)));
							log.info("\n Reciever balance from Web Service after transaction:"+tokenizedRecieverAmount+ "\nAmount returned Reciver Reseller from database after transaction :"+  resultSet.returnResellerAmount(ReceiverIdFromProp));
							
						}
					}
			
			log.info("\nResponse from webService=" + webServiceResponse+ "\nResponse from Cucumber= "+cucmberResponse);
		} else {
			log.error("Body is Null\n");
		}
		return null;
	}
}
