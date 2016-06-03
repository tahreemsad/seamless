package com.seamless.ers.qa.cucumber.ImplementationClasses.Common;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadConfigFromProperties;
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadUssdAppConfigFromProperties;
public class CucumberCommon {
	
	ReadConfigFromProperties readConfig = new ReadConfigFromProperties();
	ReadUssdAppConfigFromProperties readUssd = ReadUssdAppConfigFromProperties.getInstance();
	
	public static Logger log = LogManager.getLogger(CucumberCommon.class);
	public String command1 = "curl";
	public String command2;
	public String fullCurl;
	public String Result = "";
	public String host, user, password;
	public Session session;
	public Channel channel;
	public String yearMonth;
	public String IP;
	
	public CucumberCommon()
	{
		 try {
			IP = System.getProperty("targetEnvironment");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String returnFullCurl(String reseller_number_source, String shortcode) throws IOException{
		
		command2 = "\"http://"+IP+":8680/cc/CC?src="+ reseller_number_source + "&sessionid=" + this.CreateRandomSessionID()+ "&cmd=" + shortcode;
		log.info("Command Executing: " + command1 + " " + command2 + "\"");
		fullCurl = command1+" "+command2;
		return fullCurl;
	}
	
	public String returnCurlForSMS(){
		String curlCommand = command1 + " \"http://"+IP+":8991/cc/CC?app=smsapp";
		return curlCommand;
	}
	
	public String returnCommandForLogReading(String lines, String component){
		String readlog = "tail -n "+lines+" /var/seamless/log/"+component+"	";
		log.info("readlog command: "+readlog);
		return readlog;
	}
		
	public int CreateRandomSessionID() {
		Random random = new Random();
		int START = 1;
		int END = 999999999;
		int randomInt = random.nextInt(END) + START;
		log.info("Generated Session ID: " + randomInt);

		return randomInt;
	}
	
	public int CreateRandomNumberForAdminUser() {
		Random random = new Random();
		int START = 1;
		int END = 9999;
		int randomInt = random.nextInt(END) + START;
		log.info("Generated Random Number For WebAdmin: " + randomInt);

		return randomInt;
	}
	
	public void createSSHCoonection() throws JSchException {
		try {
			host = System.getProperty("targetEnvironment");
			user = readConfig.readPropertiesforConfig("user1");
			password = readConfig.readPropertiesforConfig("password1");
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			JSch jsch = new JSch();
			session = jsch.getSession(user, host, 22);
			session.setPassword(password);
			session.setConfig(config);
			session.setConfig("PreferredAuthentications","publickey,keyboard-interactive,password");
			session.connect();
			channel=session.openChannel("exec"); //Assigning session to channel
			log.info("Connected to: " + host);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeSSHConnection() {
		if (session.isConnected()) {
			session.disconnect();
			channel.disconnect();
			log.info("SSH Connection closed");
		} else {
			log.error("Session is not connected");
		}
	}
	
	public void SleepForSomeTime(int time) throws InterruptedException{
		log.info("System is going to sleep for 5 seconds ..... zzzzzZZZZZZZ");
		Thread.sleep(time);
	}
	
	public void createChannel() throws IOException, JSchException{
        channel.setInputStream(null);
        ((ChannelExec)channel).setErrStream(System.err);
         
        InputStream in=channel.getInputStream();
        log.info("Reading result from channel :..............");
        channel.connect();
        byte[] tmp=new byte[1024];
        while(true){
          while(in.available()>0){
            int i=in.read(tmp, 0, 1024);
            if(i<0)break;
            Result = Result + new String(tmp, 0, i);
            log.info("Result of createChannel: " + Result);
          }
          
          if(channel.isClosed()){
           log.info("channel.getExitStatus() "+channel.getExitStatus()+"\n");
            break;
          }
          
        }
	}
	
	public  int createNewSubscriberNumber() throws IOException{
		Random random = new Random();
    	String SUBSCRIBER_MSISDN_LENGTH = readConfig.readPropertiesforConfig("SUBSCRIBER_MSISDN_LENGTH");

		int START = 1;
		int END = Integer.parseInt(SUBSCRIBER_MSISDN_LENGTH);
		int randomInt = (random.nextInt(END) + START);
		log.info("Generated SubsriberMSISDN: " + randomInt);

		return randomInt;
	}
	
	public void createChannelToExtractAmount() throws IOException,JSchException {
		channel.setInputStream(null);
		((ChannelExec) channel).setErrStream(System.err);

		InputStream in = channel.getInputStream();
		channel.connect();
		byte[] tmp = new byte[1024];
		while (true) {
			while (in.available() > 0) {
				int i = in.read(tmp, 0, 1024);
				if (i < 0)
					break;
				Result = Result + new String(tmp, 0, i);
				log.info("Result of createChannelToExtractAmount: " + Result);
			}

			if (channel.isClosed()) {
				log.info("channel.getExitStatus() " + channel.getExitStatus()
						+ "\n");
				break;
			}

		}
	}
	
	public String returnCurrentMonthAndYear()throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyyMM" );
		yearMonth = formatter.format( new java.util.Date() );
		log.info(yearMonth);
		return yearMonth;
	}
	
	public void CheckInternetConnectivity() throws UnknownHostException,IOException {
		byte[] bytes;
		log.info("IP ping check start: " + IP);

		InetAddress inet;

		InetAddress ip = InetAddress.getByName(IP);
		bytes = ip.getAddress();
//		for (byte b : bytes) {
//			log.info(b & 0xFF);
//		}
		inet = InetAddress.getByAddress(new byte[] { bytes[0], bytes[1],
				bytes[2], bytes[3] });
		log.info("Sending Ping Request to "+IP);
		if (inet.isReachable(5000)) {
			log.info("Host is reachable and Connection established to the machine "
					+ IP);
		} else {
			log.error("No Connection established with the machine: " + IP
					+ " may be Internet is down and VM is down");
		}
		log.info("IP ping check end: " + IP);
	}
	
	public String createNewSubscriber() throws IOException{
    	String CountryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");
		String msisdn = CountryCodeNetworkCodeFromProp+createNewSubscriberNumber();
		log.info("Generated Subscriber MSISDN: "+msisdn);
		return msisdn;
	}
	
	public String createNewReseller() throws IOException{
    	String CountryCodeNetworkCodeFromProp = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");
		String msisdn = CountryCodeNetworkCodeFromProp+createNewResellerNumber();
		log.info("Generated Reseller MSISDN with Country code and Network code: "+msisdn);
		return msisdn;
	}
	
	public int createNewResellerNumber() throws IOException{
		Random random = new Random();
    	String RESELLER_MSISDN_LENGTH = readConfig.readPropertiesforConfig("RESELLER_MSISDN_LENGTH");
    	String RESELLER_MSISDN_LENGTH_START = readConfig.readPropertiesforConfig("RESELLER_MSISDN_LENGTH_START");

		int START = Integer.parseInt(RESELLER_MSISDN_LENGTH_START);
		int END = Integer.parseInt(RESELLER_MSISDN_LENGTH);
		int randomInt = random.nextInt(END) + START;
		log.info("Generated Reseller MSISDN: " + randomInt);
		return randomInt;
	}
	
}
