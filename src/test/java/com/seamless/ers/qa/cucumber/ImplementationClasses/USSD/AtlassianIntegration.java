package com.seamless.ers.qa.cucumber.ImplementationClasses.USSD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jcraft.jsch.JSchException;
import com.seamless.ers.qa.cucumber.ImplementationClasses.Common.CucumberCommon;

import cucumber.api.java.en.When;

public class AtlassianIntegration {
	
	CucumberCommon common = new CucumberCommon();
    static String line;
    String strResponse = "";
	public static Logger log = LogManager.getLogger( AtlassianIntegration.class );

	@When("^I Perform to integrate my automation with zephyr$")
	public void hitCurl() throws JSchException, IOException{

	    log.info("Connecting To Jira/Zephyr");
	    
	    String urlGet = "http://192.168.99.100:32768/rest/api/2/issue/TS-2";
	    String httpResponseContent = httpGetUrl(urlGet);
	    log.info(httpResponseContent);
	    	
	    
	     AuthGet();

	}
		
			public  String httpGetUrl(String strUrl) {

	        try {
	            URL url = new URL(strUrl);
	            HttpURLConnection connection = null;
	            HttpURLConnection.setFollowRedirects(true);
	            connection = (HttpURLConnection) url.openConnection();
	            
	            connection.setRequestProperty("Authorization", "Basic dGFocmVlbS5zYWRpcTp0YWhyZWVtMTIz");
	            connection.setRequestProperty("Content-Type", "application/json");
	            
	            connection.setRequestMethod("GET");
	            connection.setDoOutput(true);
	            connection.setDoInput(true);

	            connection.setAllowUserInteraction(true);
	            connection.setDefaultUseCaches(false);

	            log.info("URL : " + strUrl);
	            int responseCode = connection.getResponseCode();

	            if (responseCode != 200) {
	            	
	            	 log.info("ResponseCode validation failed. . . . ");
	            	 log.info("ResponseCode: " + responseCode);
	                strResponse = "-1";
	                return strResponse;
	            }
	            
	            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            String inputLine;
	            while ((inputLine = in.readLine()) != null) {
	                strResponse += inputLine;
	            }
	            in.close();

	            connection.disconnect();
	            connection = null;       

	        } catch (Exception e) {
	            e.printStackTrace();
	            strResponse = "-1";
	        }
	        return strResponse;
	    
	}

			public void AuthGet() {
			      
			        String line1 = strResponse;
			      // static StringBuffer jsonString = new StringBuffer();
			        String s = new String();
			     BufferedReader rd = null;
			     URL url;
			    String urlString = "http://192.168.99.100:32768/flex/services/rest/latest/execution/2"; 
			  //static String payload="{\"lastTestResult\":[{ \"executionStatus\": \"2\"}]}";


			 try{
			            // retrieving data from server
			            url = new URL(urlString);
			            log.info("URL GET . . "+url);
			            String payload="{\"lastTestResult\":{ \"executionStatus\": \"1\"}}";           // 2 = fail, 1= pass and 3= WIP 
			            
			            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
			      
			            urlConnection.setConnectTimeout(15000);
			            urlConnection.setDoOutput(true);
			            urlConnection.setDoInput(true);
			            urlConnection.setUseCaches(false);
			            urlConnection.setDefaultUseCaches(false);
			            urlConnection.setAllowUserInteraction(true);
			            urlConnection.setRequestProperty("Content-Type", "application/json");
			            urlConnection.setRequestProperty("Accept", "application/json");
			            urlConnection.setRequestMethod("PUT");
			            urlConnection.setRequestProperty ("Authorization", "Basic dGFocmVlbS5zYWRpcTp0YWhyZWVtMTIz");
			            urlConnection.connect();
			            OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
			            writer.write(payload);
			            writer.close();
			 
			         System.out.println("~~~~~~~~~~~~~~~~~~~~");
			        rd = new BufferedReader (new InputStreamReader(urlConnection.getInputStream()));
			      
			              while ((line = rd.readLine()) != null)
			            	  log.info(line);
			             
			              	  log.info(s);
			       
			                urlConnection.disconnect();
				                
				           }catch (Exception e){
				    	   throw new RuntimeException(e.getMessage());
			       }	      
		 }
}
