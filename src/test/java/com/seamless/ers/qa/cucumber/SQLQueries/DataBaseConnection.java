package com.seamless.ers.qa.cucumber.SQLQueries;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.seamless.ers.qa.cucumber.ImplementationClasses.Common.CucumberCommon;
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadConfigFromProperties;
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadDBconfigFromProperties;


public class DataBaseConnection {

	ReadConfigFromProperties readConfig = new ReadConfigFromProperties();
	ReadDBconfigFromProperties readDBConfig = new ReadDBconfigFromProperties();
	CucumberCommon common = new CucumberCommon();
	private static Logger log       = LogManager.getLogger( DataBaseConnection.class );

	Connection connectionAccounts,connectionTL, connectionSS, connectionREFILL, connectionVasManager= null;
	Statement accountStatement, tlStatement, ssStatement, refillStatement, vasManagerStatement;
	
		/****************create/closeConnectionForAccounts()********************/	
	public void createConnectionForAccounts() throws SQLException{
		try {
			
			String DBName = readDBConfig.readPropertiesforDataBase("DBName_Accounts");
			String DBUser = readDBConfig.readPropertiesforDataBase("DBUsername_Accounts");
			String DBPassword = readDBConfig.readPropertiesforDataBase("DBPassword_Accounts");
			String Server = System.getProperty("targetEnvironment");
			
			log.info("DBName: "+DBName);
			log.info("DBUser: "+DBUser);
			log.info("DB Password: "+DBPassword);
			log.info("Server: "+Server);
			
			common.CheckInternetConnectivity();
			connectionAccounts = DriverManager.getConnection("jdbc:mysql://"+Server+":3306/"+DBName+"?user="+DBUser+"&password="+DBPassword);
			accountStatement = connectionAccounts.createStatement();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/****************create/closeConnectionForAccounts()********************/	
	public void createConnectionForTransactionLogs() throws SQLException{
		try {
		
			String DBName = readDBConfig.readPropertiesforDataBase("DBName_TL");
			String DBUser = readDBConfig.readPropertiesforDataBase("DBUsername_TL");
			String DBPassword = readDBConfig.readPropertiesforDataBase("DBPassword_TL");
			String Server = System.getProperty("targetEnvironment");
			
			log.info("DBName: "+DBName);
			log.info("DBUser: "+DBUser);
			log.info("DB Password: "+DBPassword);
			log.info("Server: "+Server);
			
			common.CheckInternetConnectivity();
			connectionTL = DriverManager.getConnection("jdbc:mysql://"+Server+":3306/"+DBName+"?user="+DBUser+"&password="+DBPassword);
			tlStatement = connectionTL.createStatement();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/****************create/closeConnectionForAccounts()********************/	
	public void createConnectionForSupportSystem() throws SQLException{
		try {
		
			String DBName = readDBConfig.readPropertiesforDataBase("DBName_SS");
			String DBUser = readDBConfig.readPropertiesforDataBase("DBUsername_SS");
			String DBPassword = readDBConfig.readPropertiesforDataBase("DBPassword_SS");
			String Server = System.getProperty("targetEnvironment");
			
			log.info("DBName: "+DBName);
			log.info("DBUser: "+DBUser);
			log.info("DB Password: "+DBPassword);
			log.info("Server: "+Server);
			
			common.CheckInternetConnectivity();
			connectionSS = DriverManager.getConnection("jdbc:mysql://"+Server+":3306/"+DBName+"?user="+DBUser+"&password="+DBPassword);
			ssStatement = connectionSS.createStatement();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/****************create/closeConnectionForAccounts()********************/	
	public void createConnectionForRefill() throws SQLException{
		try {
		
			String DBName = readDBConfig.readPropertiesforDataBase("DBName_REFILL");
			String DBUser = readDBConfig.readPropertiesforDataBase("DBUsername_REFILL");
			String DBPassword = readDBConfig.readPropertiesforDataBase("DBPassword_REFILL");
			String Server = System.getProperty("targetEnvironment");
			
			log.info("DBName: "+DBName);
			log.info("DBUser: "+DBUser);
			log.info("DB Password: "+DBPassword);
			log.info("Server: "+Server);
			
			common.CheckInternetConnectivity();
			connectionREFILL = DriverManager.getConnection("jdbc:mysql://"+Server+":3306/"+DBName+"?user="+DBUser+"&password="+DBPassword);
			refillStatement = connectionREFILL.createStatement();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	/****************END create/closeConnectionForAccounts()********************/
	
	
	/****************Create/Close Connection For VasManager ********************/	
	public void createConnectionForVasManager() throws SQLException {
		try {

			String DBName = readDBConfig
					.readPropertiesforDataBase("DBName_VASMANAGER");
			String DBUser = readDBConfig
					.readPropertiesforDataBase("DBUsername_VASMANAGER");
			String DBPassword = readDBConfig
					.readPropertiesforDataBase("DBPassword_VASMANAGER");
			String Server = System.getProperty("targetEnvironment");

			log.info("DBName: " + DBName);
			log.info("DBUser: " + DBUser);
			log.info("DB Password: " + DBPassword);
			log.info("Server: " + Server);

			common.CheckInternetConnectivity();
			connectionVasManager = DriverManager.getConnection("jdbc:mysql://"
					+ Server + ":3306/" + DBName + "?user=" + DBUser
					+ "&password=" + DBPassword);
			vasManagerStatement = connectionVasManager.createStatement();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnectionForAccounts() throws SQLException{
		connectionAccounts.close();
	}
	
	
	public void closeConnectionForVasManager() throws SQLException{
		connectionVasManager.close();
	}
	
	public void closeConnectionForTransactionLog() throws SQLException{
		connectionTL.close();
	}
	
	public void closeConnectionForSupportSystem() throws SQLException{
		connectionSS.close();
	}	
	
	public void closeConnectionForRefill() throws SQLException{
		connectionREFILL.close();
	}		
}