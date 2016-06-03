package com.seamless.ers.qa.cucumber.SQLQueries;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.seamless.ers.qa.cucumber.ImplementationClasses.Common.CucumberCommon;

import cucumber.api.java.en.And;
         
public class SQLResultSets {
	
	ResultSet amountRS, ersReference, recordCount, last_password_change_time, resultSetActivationCode, resultSetRFATerminal= null;
	
	DataBaseConnection dbconnect = new DataBaseConnection();
	private static Logger log       = LogManager.getLogger( SQLResultSets.class );
	CucumberCommon common = new CucumberCommon();
	
	public double returnResellerAmount(String resellerID) throws IOException, SQLException{
		String getbalance = null;
		double balance=0;
		log.info("\n Balance Amount from DB for reseller ID "+resellerID);
		
		dbconnect.createConnectionForAccounts();
		String query = "SELECT balance FROM accounts where accountId ='"+resellerID+"' ";
		log.info("Query: "+query);
		amountRS = dbconnect.accountStatement.executeQuery(query);
			while(amountRS.next()){
				getbalance = amountRS.getString("balance");
				balance = Double.parseDouble(getbalance);
				log.info("Balance from Database: "+balance);
			}
		return balance;
	}
	
	public int returnResellerIntegerAmount(String resellerID) throws IOException, SQLException{
		int getbalance = 0;
		log.info("\n Balance Amount from DB for reseller ID "+resellerID);
		
		dbconnect.createConnectionForAccounts();
		String query = "SELECT balance FROM accounts where accountId ='"+resellerID+"' ";
		log.info("Query: "+query);
		amountRS = dbconnect.accountStatement.executeQuery(query);
			while(amountRS.next()){
				getbalance = amountRS.getInt("balance");
				log.info("Balance from Database: "+getbalance);
			}
		return getbalance;
	}
	
	public String returnLastERSreference() throws IOException, SQLException{
		String reference=null;
		
		dbconnect.createConnectionForTransactionLogs();
		ersReference = dbconnect.tlStatement.executeQuery("select transactionlog.txe_transactions_archive_xml.ersReference "
				+ "from transactionlog.txe_transactions_archive_xml "
				+ "order by transactionlog.txe_transactions_archive_xml.ersReference desc limit 1");
			while(ersReference.next()){
				reference = ersReference.getString("ersReference");
			}
		return reference;
	}
	
	public String getErsReference(String range) throws IOException, SQLException{
		String reference=null;
		
		dbconnect.createConnectionForTransactionLogs();
		String query = ("SELECT txe_transactions_archive_xml.ersReference FROM txe_transactions_archive_xml "
				+ "ORDER BY ersReference DESC LIMIT "+range);
		log.info("ers-Refernce Query: "+query);
		ersReference = dbconnect.tlStatement.executeQuery(query);
			while(ersReference.next()){
				reference = ersReference.getString("ersReference");
			}
		return reference;
	}
	
	public String returnCountOfTransactions() throws IOException, SQLException{
		String reference=null;
		
		dbconnect.createConnectionForTransactionLogs();
		ersReference = dbconnect.tlStatement.executeQuery("SELECT txe_transactions_archive_xml.ersReference FROM txe_transactions_archive_xml "
				+ "ORDER BY ersReference DESC LIMIT 3,1 ");
			while(ersReference.next()){
				reference = ersReference.getString("ersReference");
			}
		return reference;
	}
	
	public int returnCountOfRecordsFromSupportSystem(String searchfield, String transactionType, String datediff,String command,String resultcode, String digit) throws IOException, SQLException, ParseException{
		int count = 0;
       
		dbconnect.createConnectionForSupportSystem();
		String query = "select count(*) as totalCount from support_search_index_"+common.returnCurrentMonthAndYear()+" "
				+ " WHERE "
				+ " searchField='"+searchfield+"'"
				+ " AND "
				+ " (searchFieldTypeFlags & 876) != 0 "
				+ " AND "
				+ " startTime >= DATE_ADD(NOW(), INTERVAL "+datediff+" DAY) "
				+ " AND "
				+ " resultCode "+command+" ("+resultcode+")"
				+ " AND "
				+ " ((searchTagsFlags & "+digit+") != 0) "
				+ " ORDER BY startTime DESC";
		
		recordCount = dbconnect.ssStatement.executeQuery(query);
			while(recordCount.next()){
				count = recordCount.getInt("totalCount");
			}
			
			log.info("Interger:"+count);
			log.info("Query: "+query);
		return count;
	}
	public int returnCountOfReverseRecordsFromSupportSystem(String searchfield, String datediff,String command,String resultcode, String profileID) throws IOException, SQLException, ParseException{
		int count = 0;
		
		dbconnect.createConnectionForSupportSystem();
		String query = "SELECT DISTINCT COUNT(*) as totalCount from support_search_index_"+common.returnCurrentMonthAndYear()+" "
				+ " WHERE "
				+ " searchField='"+searchfield+"'"
				+ " AND "
				+ " (searchFieldTypeFlags & 876) != 0 "
				+ " AND "
				+ " startTime >= DATE_ADD(NOW(), INTERVAL "+datediff+" DAY) "
				+ " AND "
				+ " resultCode "+command+" ("+resultcode+")"
				+ " AND "
				+ "(profileId IN ('"+profileID+"'))"
				+ " ORDER BY startTime DESC";
		
		recordCount = dbconnect.ssStatement.executeQuery(query);
			while(recordCount.next()){
				count = recordCount.getInt("totalCount");
			}
			
			log.info("Interger:"+count);
			log.info("Query: "+query);
		return count;
	}
	
	public int resturnActiveVoucherCount(int voucherstatus, int classkey) throws IOException, SQLException, ParseException{
		int count = 0;
		
		dbconnect.createConnectionForRefill();
		String query = "select Count(*) as totalCount from dwa_items where `status` = '"+voucherstatus+"' and class_key="+classkey+"	";
		log.info(query);
		recordCount = dbconnect.refillStatement.executeQuery(query);
			while(recordCount.next()){
				count = recordCount.getInt("totalCount");
			}
			log.info("Query: "+query);
		return count;
	}
	
	public int resturnRevokeVoucherCount(int voucherstatus, int classkey) throws IOException, SQLException, ParseException{
		int count = 0;
		
		dbconnect.createConnectionForRefill();
		String query = "select Count(*) as totalCount from dwa_items where `status` = '"+voucherstatus+"' and class_key="+classkey+"	";
		log.info(query);
		recordCount = dbconnect.refillStatement.executeQuery(query);
			while(recordCount.next()){
				count = recordCount.getInt("totalCount");
			}
			
			
			log.info("Query: "+query);
		return count;
	}
	
	@And("^Product with name (.+) and status (.+) is updated in the Database$")
	public void UnblockProduct(String productname, int productstatus)throws IOException, SQLException, ParseException {
		dbconnect.createConnectionForRefill();

		String query = "update dwa_products set `status` = "+productstatus+" and `name` = '"+productname+"' ";
		log.info(query);
		int i = dbconnect.refillStatement.executeUpdate(query);
		if (i > 0) {
			log.info("Record updated for " + productname + "in database");
		} else {
			log.error("Error while updating the record for " + productname + "in database");
		}		
	}
	
	@And("^I unrevoke batch with status (.+) and with classkey (.+)$")
	public void UnRevokeVouchers(int voucherstatus, int classkey ) throws IOException, SQLException, ParseException{
		
			dbconnect.createConnectionForRefill();

			String query = "update dwa_items set `status` = "+voucherstatus+" and `class_key` = '"+classkey+"' ";
			log.info(query);
			int i = dbconnect.refillStatement.executeUpdate(query);
			if (i > 0) {
				log.info("Record updated for " + voucherstatus+ "in data base");
			} else {
				log.error("Error while updating the record for " + voucherstatus +" in database");
			}
	}

	public String returnPasswordChange(String resellerId, String userType) throws IOException, SQLException, ParseException{
		
		String last_password_change_time_value="";
		
		dbconnect.createConnectionForRefill();
		String query = "SELECT id_domains.DomainKey, id_domains.PathName, id_users.last_password_change_time, id_users.UserKey"
						+ " from id_domains, id_users where id_domains.PathName like '%/"+resellerId+"' and id_users.DomainKey = id_domains.DomainKey "
						+ " and  id_users.UserId ='"+userType+"' ";
		log.info(query);
		last_password_change_time = dbconnect.refillStatement.executeQuery(query);
			while(last_password_change_time.next()){
				last_password_change_time_value = last_password_change_time.getString("last_password_change_time");
			}
			
			log.info("Query: "+query);
			log.info("last_password_change_time_value: "+last_password_change_time_value);
			dbconnect.closeConnectionForRefill();
			
			return last_password_change_time_value;
		
	}
	
	public String returnActivationCode(String resellerMSISDN) throws IOException, SQLException, ParseException{
		
		String activationCode="";
		
		dbconnect.createConnectionForRefill();
		String query = "select activation_code, address from extdev_devices where address = '"+resellerMSISDN+"' ";
		log.info(query);
		resultSetActivationCode = dbconnect.refillStatement.executeQuery(query);
			while(resultSetActivationCode.next()){
				activationCode = resultSetActivationCode.getString("activation_code");
			}
			
			log.info("Query: "+query);
			log.info("Reseller Activation Code From Database: "+activationCode);
			dbconnect.closeConnectionForRefill();
			
			return activationCode;
	}
	
	public String returnRFATerminal(int state) throws IOException, SQLException, ParseException{
		
		String msisdn="";
		
		dbconnect.createConnectionForRefill();
		String query = "SELECT state,address FROM Refill.extdev_devices where state="+state+" Limit 1";
		log.info(query);
		resultSetRFATerminal = dbconnect.refillStatement.executeQuery(query);
			while(resultSetRFATerminal.next()){
				msisdn = resultSetRFATerminal.getString("address");
			}
			
			log.info("Query: "+query);
			log.info("Reseller MSISDN From Database which is RFA: "+msisdn);
			dbconnect.closeConnectionForRefill();
			
			return msisdn;
	}
	
	public ArrayList viewFafList(String msisdn) throws IOException, SQLException
	{
		int i=0;
		String result;
		String fafList;
		ArrayList al = new ArrayList();
		
		dbconnect.createConnectionForVasManager();
		String query = "select fafList from vasmgr_faf_status where subscriberMSISDN='"+msisdn+"'";
		log.info("Query: "+query);
		ResultSet rs = dbconnect.vasManagerStatement.executeQuery(query);
		while(rs.next()){
			result = rs.getString("fafList");
			log.info("fafList: "+result);
			al.add(result);
		}
		dbconnect.closeConnectionForVasManager();
		return al;
	}
}