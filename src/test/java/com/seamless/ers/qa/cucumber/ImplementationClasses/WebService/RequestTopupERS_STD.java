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
public class RequestTopupERS_STD {
	
	public static Node getAmount,responseMessage;
	
	public static Logger log = LogManager.getLogger( RequestTopupERS_STD.class );
	public static SQLResultSets resultSet = new SQLResultSets();
	public static ReadConfigFromProperties readconfig = new ReadConfigFromProperties();
	public static ReadWSProperties ws = new ReadWSProperties();
	public double Delta = 1e-8;
	public double resellerBeforeBalance; 
	public static String ResellerBeforeBalanceConvertedToString; 
	public static String ResellerId1FromProp, ResellerTypeFromProp, ResellerUserIDFromProp, ResellerPwdFromProp, ResellerAmountFromProp, ReceiverMSISDNFromProp, ReceiverTypeFromProp, ResponseMessageFromProp;
	
	@When("^Reseller topups to subscriber having resellerId (.+) resellerType (.+) userId (.+) password (.+) amount (.+) and receiverMSISDN is (.+) and type is (.+) and in response (.+) message is received$")
	public void SOAP(String resellerId, String resellertype, String userId, String password, String amount, String receiverMSISDN, String subscriberType, String CucmberMessage) throws Exception {
    	// Create SOAP Connection
    	String CountryCodeNetworkCodeFromProp = readconfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");

    	try{
			ResellerId1FromProp = ws.returnPropertyForWS(resellerId);
			ResellerTypeFromProp = ws.returnPropertyForWS(resellertype);
			ResellerUserIDFromProp = ws.returnPropertyForWS(userId);
			ResellerPwdFromProp = ws.returnPropertyForWS(password);
			ResellerAmountFromProp = ws.returnPropertyForWS(amount);
			ReceiverMSISDNFromProp = ws.returnPropertyForWS(receiverMSISDN);
			ReceiverMSISDNFromProp = CountryCodeNetworkCodeFromProp+ReceiverMSISDNFromProp;
			ReceiverTypeFromProp = ws.returnPropertyForWS(subscriberType);
			ResponseMessageFromProp = ws.returnPropertyForWS(CucmberMessage);
			
			log.info("ResellerId1FromProp "+ResellerId1FromProp);
			log.info("ResellerTypeFromProp "+ResellerTypeFromProp);
			log.info("ResellerUserIDFromProp "+ResellerUserIDFromProp);
			log.info("ResellerPwdFromProp "+ResellerPwdFromProp);
			log.info("ResellerAmountFromProp "+ResellerAmountFromProp);
			log.info("ReceiverMSISDNFromProp "+ReceiverMSISDNFromProp);
			log.info("ReceiverTypeFromProp "+ReceiverTypeFromProp);
			log.info("ResponseMessageFromProp "+ResponseMessageFromProp);
			
	    	resellerBeforeBalance = resultSet.returnResellerAmount(ResellerId1FromProp);
	    	
	    	ResellerBeforeBalanceConvertedToString = Double.toString(resellerBeforeBalance);
	    	
	        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
	        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
	        // Send SOAP Message to SOAP Server
	        String wsdl = "http://"+System.getProperty("targetEnvironment")+":8913/topupservice/service?wsdl";
	        log.info("WSDL: "+wsdl);
	        SOAPMessage WebserviceResponse = soapConnection.call(requestTopup(ResellerId1FromProp, ResellerTypeFromProp, ResellerUserIDFromProp, ResellerPwdFromProp, ResellerAmountFromProp, ReceiverMSISDNFromProp, ReceiverTypeFromProp), wsdl);
	        log.info("\nWebserviceResponse: "+WebserviceResponse.getSOAPPart());
	        printOutput(WebserviceResponse, ResponseMessageFromProp, ResellerId1FromProp);
	        soapConnection.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }

    public static SOAPMessage requestTopup(String resellerId, String resellertype, String user_Id, String resellerpassword, String topupamount, String receiverMSISDN, String subscriberType) throws Exception {
    	
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        
        String xmlns_soapen = readconfig.readPropertiesforConfig("xmlns_soapen");
        String xmlns_ex = readconfig.readPropertiesforConfig("xmlns_ex");
        
        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.setPrefix("soapenv");
        envelope.removeNamespaceDeclaration("SOAP-ENV");
        //envelope.addNamespaceDeclaration("ns0", xmlns_soapen);
        envelope.addNamespaceDeclaration("ext", xmlns_ex );
        SOAPHeader header = envelope.getHeader();
        header.setPrefix("soapenv");
        SOAPBody ext = envelope.getBody();
        ext.setPrefix("soapenv");
        
        SOAPElement RequestPurchase = ext.addChildElement("requestTopup","ext");
        
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
				senderPrincipalId_UserId.addTextNode(user_Id);
        		
		SOAPElement topupPrincipalId = RequestPurchase.addChildElement("topupPrincipalId");
    			
			SOAPElement topupPrnId = topupPrincipalId.addChildElement("id");
				topupPrnId.addTextNode(receiverMSISDN);
			SOAPElement topupPrntype = topupPrincipalId.addChildElement("type");
				topupPrntype.addTextNode(subscriberType);
			SOAPElement topupPrnUserId = topupPrincipalId.addChildElement("userId");
				topupPrnUserId.addTextNode("?");	
				
    	SOAPElement senderAccountSpecifier = RequestPurchase.addChildElement("senderAccountSpecifier");
    		
    		SOAPElement accountId_Id = senderAccountSpecifier.addChildElement("accountId");
        		accountId_Id.addTextNode("RESELLERMSISDN");    		
    		SOAPElement accountId_Type = senderAccountSpecifier.addChildElement("accountTypeId");
        		accountId_Type.addTextNode("RESELLER");
        		
    	SOAPElement topupAccountSpecifier = RequestPurchase.addChildElement("topupAccountSpecifier");
        		
    		SOAPElement Topup_accountId_Id = topupAccountSpecifier.addChildElement("accountId");
        		Topup_accountId_Id.addTextNode("SUBSCRIBERMSISDN");    		
    		SOAPElement TopupaccountId_Type = topupAccountSpecifier.addChildElement("accountTypeId");
        		TopupaccountId_Type.addTextNode("AIRTIME");
        		
		SOAPElement productId = RequestPurchase.addChildElement("productId");
			productId.addTextNode("TOPUP");
		
    	SOAPElement amount = RequestPurchase.addChildElement("amount");
        		
    		SOAPElement amount_currency = amount.addChildElement("currency");
        		amount_currency.addTextNode("USD");    		
    		SOAPElement amount_value = amount.addChildElement("value");
        		amount_value.addTextNode(topupamount);
        		
		//MimeHeaders headers = soapMessage.getMimeHeaders();    
        //headers.addHeader("SOAPAction", xmlns_ex);

        soapMessage.saveChanges();
        soapMessage.writeTo(System.out);
        return soapMessage;
    }
    
	public static SOAPMessage printOutput(SOAPMessage response, String cucmberResponse, String resellerId)throws Exception {
		SOAPBody soapBody = response.getSOAPBody();
		
		if (response != null) {
			log.info("\nresponse: "+response);
			
			String amount=null,tokenizedAmount=null;
			double resellerBalanceAfter = resultSet.returnResellerAmount(resellerId);
			String ConvertTheAfterBalanceToString = Double.toString(resellerBalanceAfter);
			
			String INVALID_RESELLER  =  ws.returnPropertyForWS("INVALID_RESELLER");
			String INVALID_PASSWORD = ws.returnPropertyForWS("INVALID_PASSWORD");
			String INVALID_USERID = ws.returnPropertyForWS("INVALID_USERID");
			String INVALID_AMOUNT = ws.returnPropertyForWS("INVALID_AMOUNT");
			String INVALID_PRINCIPAL_TYPE = ws.returnPropertyForWS("INVALID_PRINCIPAL_TYPE");
			
			log.info("INVALID_RESELLER: "+INVALID_RESELLER);
			log.info("INVALID_PASSWORD: "+INVALID_PASSWORD);
			log.info("INVALID_USERID: "+INVALID_USERID);
			log.info("INVALID_AMOUNT: "+INVALID_AMOUNT);
			log.info("INVALID_PRINCIPAL_TYPE: "+INVALID_PRINCIPAL_TYPE);
					
			log.info("response.getSOAPPart(): "+response.getSOAPPart().getContent());
			
			TransformerFactory tff = TransformerFactory.newInstance();
	        Transformer tf = tff.newTransformer();
	        
			Source sc = response.getSOAPPart().getContent();
			StreamResult result = new StreamResult(System.out);
			tf.transform(sc, result);
	     	
			NodeList bodyChild = response.getSOAPBody().getChildNodes();
			log.info("test"+bodyChild);
			NodeList requestPurchaser = (NodeList) bodyChild.item(0);

			responseMessage = requestPurchaser.item(0).getChildNodes().item(2);
			String webServiceResponse = responseMessage.getTextContent();
			
			Assert.assertEquals(cucmberResponse, webServiceResponse);
			log.info("\nResponse from webService=" + webServiceResponse+ "\nResponse from Cucumber= "+cucmberResponse);
				
			Assert.assertEquals(cucmberResponse, webServiceResponse);
			
			if(INVALID_RESELLER.equals(webServiceResponse)){
				log.info("Reseller Not Found");
			}else{
				if(
					    INVALID_PASSWORD.equals(webServiceResponse)
					|| (INVALID_USERID.equals(webServiceResponse) 
					|| (INVALID_AMOUNT.equals(webServiceResponse)
					|| (INVALID_PRINCIPAL_TYPE.equals(webServiceResponse))))){
					    Assert.assertEquals(ConvertTheAfterBalanceToString, ResellerBeforeBalanceConvertedToString);
						log.info("Reason: "+webServiceResponse);
						log.info("Reseller Balance before transaction: "+ResellerBeforeBalanceConvertedToString+"\nReseller Balance after transaction: "+ConvertTheAfterBalanceToString);
						}else{
						    getAmount = requestPurchaser.item(0).getChildNodes().item(4).getChildNodes().item(2).getChildNodes().item(0).getChildNodes().item(1).getChildNodes().item(1);
						    amount = getAmount.getTextContent().toString();
						    tokenizedAmount = amount.substring(0,amount.indexOf("."));
						    
						    log.info("\nAmount returned from webService=" + tokenizedAmount+"\nAmount returned from DataBase: "+resultSet.returnResellerAmount(resellerId));
						    Assert.assertEquals(ConvertTheAfterBalanceToString, tokenizedAmount);
							log.info("\nAmount returned from webService=" + tokenizedAmount+"\nAmount returned from DataBase: "+resultSet.returnResellerAmount(resellerId));
						}
					}
			
			log.info("\nResponse from webService=" + webServiceResponse+ "\nResponse from Cucumber= "+cucmberResponse);
		} else {
			log.error("Body is Null\n");
		}
		return null;
	}
}