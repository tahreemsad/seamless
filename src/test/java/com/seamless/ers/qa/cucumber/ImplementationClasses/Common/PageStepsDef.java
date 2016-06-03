package com.seamless.ers.qa.cucumber.ImplementationClasses.Common;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.seamless.ers.qa.cucumber.ImplementationClasses.USSD.USSDAppViewMenu;
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadConfigFromProperties;
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadWebVariablesFromProperties;
import com.seamless.ers.qa.cucumber.ReadingConfig.ReadingConfigurations.ReadXpathsFromProperties;
import com.seamless.ers.qa.cucumber.SQLQueries.SQLResultSets;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class PageStepsDef {

	public WebDriver webdriver;
	public Double beforeBalance = 0.0;
	public Double afterBalance = 0.0;
	public Double afterBalanceOfSender = 0.0;
	public String ersReference, webAdminIP, webAdminPort, webAdminProtocol, URL, agentportalPort, IP, relativePath, textfromProp;
	public double Delta = 1e-8;
	public HashMap<String, Double> resellerInfoMap = new HashMap<String, Double>();
	public HashMap<String, Double> resellerBalanceMap = new HashMap<String, Double>();
	ReadConfigFromProperties readConfig = new ReadConfigFromProperties();
	ReadWebVariablesFromProperties readWebVariables = new ReadWebVariablesFromProperties();
	ReadXpathsFromProperties readXpaths = new ReadXpathsFromProperties();
	USSDAppViewMenu viewMenu = new USSDAppViewMenu();
	SQLResultSets resultset = new SQLResultSets();
	CucumberCommon common = new CucumberCommon();
	private static Logger log = LogManager.getLogger(PageStepsDef.class);

	public PageStepsDef() throws IOException {
		String browserName = readConfig.readPropertiesforConfig("browser");
		String chromeDriverPath = readConfig.readPropertiesforConfig("CHROME_DRIVER_PATH");
		
		if (browserName.toLowerCase().equals("googlechrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+chromeDriverPath);
			webdriver = new ChromeDriver();
			webdriver.manage().window().maximize();
			webdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		} else if (browserName.toLowerCase().equals("firefox")) {
			webdriver = new FirefoxDriver();
			webdriver.manage().window().maximize();
			webdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		} else if (browserName.toLowerCase().equals("htmlunit")) {
			webdriver = new HtmlUnitDriver(true);
		}
	}

	// @After()
	// public void getScreenshot(Scenario scenario) throws WebDriverException,
	// IOException {
	// if (scenario.isFailed()) {
	// final byte[] screenshot = ((TakesScreenshot)
	// webdriver).getScreenshotAs(OutputType.BYTES);
	// scenario.embed(screenshot, "image/png"); // stick it in the report
	// }
	// }

	@Given("^I browse to the (.+) page$")
	public void openWebPage(String path) {
		try {
			webAdminProtocol = readConfig
					.readPropertiesforConfig("webAdminProtocol");
			IP = System.getProperty("targetEnvironment");
			agentportalPort = readConfig
					.readPropertiesforConfig("agentportalPort");
			webAdminPort = readConfig.readPropertiesforConfig("webAdminPort");
			relativePath = readConfig.readPropertiesforConfig(path);
			log.info(path);
			if (path.equals("Agentportal")) {
				URL = webAdminProtocol + IP + ":" + agentportalPort
						+ relativePath;
				log.info("Opening " + URL);
				webdriver.get(URL);
			} else {
				URL = webAdminProtocol + IP + ":" + webAdminPort + relativePath;
				log.info("Opening " + URL);
				webdriver.get(URL);
			}
		} catch (IOException e) {
			log.error("Exception" + e);
		}
	}

	@And("^User clicks on the button (.+)$")
	public void clickWebElement(String buttonXpath) throws IOException {
		String buttonPath = readXpaths.readPropertiesForXpaths(buttonXpath);
		webdriver.findElement(By.xpath(buttonPath)).click();
		log.info("buttonXPath: " + buttonPath);
	}

	@And("^I input (.+) in the (.+) field with (.+)$")
	public void insertMSISDNintoField(String msisdn, String xpath , String prefix) throws IOException{
		String getPrefix = readWebVariables.readPropertiesForWebVariables(prefix);
		//log.info("Prefix of plus for MSISDN is required or not? "+ getPrefix);
		
		String phoneNumber = readWebVariables.readPropertiesForWebVariables(msisdn);
		String getXpath = readXpaths.readPropertiesForXpaths(xpath);
		String msisdnPrefix="";
		
		if(getPrefix.equals("yes")){
			msisdnPrefix = readConfig.readPropertiesforConfig("PLUSCOUNTRYCODE_NETWORKCODE");
			log.info(msisdnPrefix+phoneNumber);
			webdriver.findElement(By.xpath(getXpath)).sendKeys(msisdnPrefix+phoneNumber);
		}else
			if(getPrefix.equals("no")){
				msisdnPrefix = readConfig.readPropertiesforConfig("COUNTRYCODE_NETWORKCODE");
				log.info(msisdnPrefix+phoneNumber);
				webdriver.findElement(By.xpath(getXpath)).sendKeys(msisdnPrefix+phoneNumber);
			}else
				if(getPrefix.equals("national")){
					log.info("Prefix: "+ getPrefix);
					msisdnPrefix = readConfig.readPropertiesforConfig("NATIONAL_NETWORK_CODE");
					log.info(msisdnPrefix+phoneNumber);
					webdriver.findElement(By.xpath(getXpath)).sendKeys(msisdnPrefix+phoneNumber);
				}
	}
	
	@And("^I enter (.+) in the (.+) field$")
	public void inputText(String inputText, String xpathOfTextfield) throws IOException {
		String textFromProp = readWebVariables.readPropertiesForWebVariables(inputText);
		WebElement textfield = webdriver.findElement(By.xpath(readXpaths.readPropertiesForXpaths(xpathOfTextfield)));
		textfield.sendKeys(textFromProp);
		log.info("textFromProp-- Input: " + textFromProp);
		log.info("textFromProp-- Xpath: " + textfield);
	}

	@And("^User enter ersReference in the textfield (.+)$")
	public void inputERSreference(String xpathOfTextfield) throws IOException {
		WebElement textfiled = webdriver.findElement(By.xpath(readXpaths
				.readPropertiesForXpaths(xpathOfTextfield)));
		textfiled.sendKeys(ersReference);
	}

	@And("^I enter (.+) in the (.+) field after reset$")
	public void inputTextAfterResetField(String inputText,
			String xpathOfTextfield) throws IOException {
		WebElement textfiled = webdriver.findElement(By.xpath(readXpaths
				.readPropertiesForXpaths(xpathOfTextfield)));
		;
		textfiled.clear();
		textfiled.sendKeys(inputText);
	}

	@And("^User compare the value (.+) with the text field (.+) value$")
	public void fetchValueFromInputAndCompareIt(String value, String xpath)
			throws IOException {
		String getTextFrominputField = webdriver.findElement(
				By.xpath(readXpaths.readPropertiesForXpaths(xpath)))
				.getAttribute("value");
		Assert.assertEquals(value, getTextFrominputField);
		log.info("UserValue: " + value + "\ngetTextFrominputField: "
				+ getTextFrominputField);
	}

	@And("^I compare the value of ERSreference with the input field (.+)$")
	public void compareERSreferenceWithInputFieldOfReference(String xpath)
			throws IOException {
		String getTextFrominputField = webdriver.findElement(By.xpath(readXpaths.readPropertiesForXpaths(xpath))).getAttribute("value");
		Assert.assertEquals(ersReference, getTextFrominputField);
		log.info("UserValue: " + ersReference + "\ngetTextFrominputField: "+ getTextFrominputField);
	}

	@And("^I enter (.+) in the (.+) field by csspath$")
	public void enterValuebycssSelector(String value, String csspath) {
		System.out.println(csspath);
		webdriver.findElement(By.cssSelector(csspath)).sendKeys(value);
	}

	@And("^I deselect (.+) from dropdown (.+)$")
	public void deselectValueFromDropdown(String text, String path)
			throws IOException, InterruptedException {
		Select dropdown = null;
		textfromProp = readWebVariables.readPropertiesForWebVariables(text);
		dropdown = new Select(webdriver.findElement(By.xpath(readXpaths.readPropertiesForXpaths(path))));
		dropdown.deselectByVisibleText(textfromProp);
	}

	@And("^I select (.+) from dropdown (.+)$")
	public void selectValueFromDropdown(String Value, String path)throws IOException, InterruptedException {
		Select dropdown = null;
		String ValueFromProp = readWebVariables.readPropertiesForWebVariables(Value);
		log.info("ValueFromProp" + ValueFromProp);
		try {
			dropdown = new Select(webdriver.findElement(By.xpath(readXpaths.readPropertiesForXpaths(path))));
			log.info("dropdown" + dropdown);

		} catch (IOException e) {
			log.error(e);
		}
		dropdown.selectByVisibleText(ValueFromProp);
	}

	@And("^System sleeps for (.+) seconds$")
	public void SystemSleeps(int time) throws InterruptedException {
		Thread.sleep(time);
	}

	@And("^I wait for (.+) to visible$")
	public void i_wait_for_element_to_visible(final String xpathOfTextfield) {
		WebDriverWait myWait = new WebDriverWait(webdriver, 5);
		ExpectedCondition<Boolean> conditionToCheck = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				try {
					return (driver.findElement(By.xpath(readXpaths
							.readPropertiesForXpaths(xpathOfTextfield))))
							.isDisplayed();
				} catch (IOException e) {
					log.error(e);
				}
				return null;
			}
		};
		myWait.until(conditionToCheck);
	}

	@Then("^I switch to frame$")
	public void frameSwitch() throws IOException {
		webdriver.switchTo().frame(0);
	}

	@Then("^I should see (.+) on the page (.+)$")
	public void confirmTextOnPage(String text, String path) throws IOException {

		String TextFromProp = readWebVariables.readPropertiesForWebVariables(text);
		String PathFromProp = readXpaths.readPropertiesForXpaths(path);

		String bodyText = webdriver.findElement(By.xpath(PathFromProp)).getText();
		Assert.assertTrue(bodyText.contains(TextFromProp));

		Assert.assertTrue(bodyText.contains(TextFromProp));

		log.info("Xpath: " + PathFromProp + " found on the page");
		log.info("bodyText: " + TextFromProp + " found on the page");
	}

	@Then("^I should not see (.+) on the page$")
	public void confirmTextNotOnThePage(String text) throws IOException {
		String TextFromProp = readWebVariables.readPropertiesForWebVariables(text);

		String bodyText = webdriver.findElement(By.tagName("body")).getText();
		Assert.assertFalse(bodyText.contains(TextFromProp));
		log.info("BodyText: " + TextFromProp + " not found on the page");
	}

	@Then("^I verify the popup text (.+) with the user text (.+) on the page$")
	public void handlingPopupText(String xpath, String textFromUser)throws IOException {
		
		String valueFromProp = readXpaths.readPropertiesForXpaths(xpath);
		String TextFromProp = readWebVariables.readPropertiesForWebVariables(textFromUser);
		String getTextFromPopup = webdriver.findElement(By.xpath(valueFromProp)).getText();
		Assert.assertEquals(getTextFromPopup, TextFromProp);
		log.info("getTextFromPopup: " + getTextFromPopup + "\ntextFromUser: "+ TextFromProp);
	}

	@And("^User accept the alert box$")
	public void checkAlert() {
		try {
			WebDriverWait wait = new WebDriverWait(webdriver, 3);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = webdriver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			log.info(e);
		}
	}

	@And("^I create a new (.+) user and input it in the textfield with the xpath (.+)$")
	public void CreateWebAdminUser(String username, String xpath) throws IOException {
		
		String XpathFromProp = readXpaths.readPropertiesForXpaths(xpath);

		int generateNumber = common.CreateRandomNumberForAdminUser();
		String convertIntoString = Integer.toString(generateNumber);
		String newAdminUser = username + convertIntoString;

		webdriver.findElement(By.xpath(XpathFromProp)).sendKeys(newAdminUser);
		log.info("Generated New WebAdmin: " + newAdminUser);
	}

	@Given("^I verify the balance of the (.+) reseller (.+) before the transaction$")
	public void checkBalanceBeforeTransaction(String resellerType, String resellerId) throws IOException, SQLException {
		
		String ResellerIdFromProp = readWebVariables.readPropertiesForWebVariables(resellerId);
		double beforeBalance = resultset.returnResellerAmount(ResellerIdFromProp);
		log.info("Reseller ID" + ResellerIdFromProp);
		log.info("senderbeforeBalance: " + beforeBalance);

		resellerInfoMap.put(ResellerIdFromProp, beforeBalance);
		log.info("Reseller ID: " + ResellerIdFromProp);
		log.info(resellerType + " Amount :"+ resellerInfoMap.get(ResellerIdFromProp));
	}

	@Then("^I verify the balance of the sender reseller (.+) after the transaction of amount (.+)$")
	public void verifyBalanceAfterTransaction(String resellerId, String amount) throws IOException {
		
		String ResellerIdFromProp = readWebVariables.readPropertiesForWebVariables(resellerId);
		String AmountFromProp = readWebVariables.readPropertiesForWebVariables(amount);

		log.info(ResellerIdFromProp+": ResellerIdFromProp");
		log.info(AmountFromProp+": AmountFromProp");
		afterBalanceOfSender = resellerInfoMap.get(ResellerIdFromProp) - Integer.parseInt(AmountFromProp);
		log.info("Transaction Amount: " + AmountFromProp);
		log.info("After Balance of sender: " + afterBalanceOfSender);
		try {
			Double getCurrentBalance = (double) resultset.returnResellerAmount(ResellerIdFromProp);
			log.info("getCurrentBalance: " + getCurrentBalance);
			Assert.assertEquals(getCurrentBalance, afterBalanceOfSender);
		} catch (IOException e) {
			log.error(e);
		} catch (SQLException e) {
			log.error(e);
		}
	}

	@Then("^I verify the balance of the receiver reseller (.+) after the transaction of amount (.+)$")
	public void verifyRecieverBalanceAfterTransaction(String resellerId, String amount) throws IOException {
		
		String ResellerIdFromProp = readWebVariables.readPropertiesForWebVariables(resellerId);
		String AmountFromProp = readWebVariables.readPropertiesForWebVariables(amount);

		afterBalance = resellerInfoMap.get(ResellerIdFromProp)+ Integer.parseInt(AmountFromProp);
		log.info("Transaction Amount: " + AmountFromProp);
		log.info("After Balance of sender: " + afterBalance);
		try {
			Double getCurrentBalance = (double) resultset.returnResellerAmount(ResellerIdFromProp);
			log.info("getCurrentBalance: " + getCurrentBalance);
			Assert.assertEquals(getCurrentBalance, afterBalance);
		} catch (IOException e) {
			log.error(e);
		} catch (SQLException e) {
			log.error(e);
		}
	}

	@And("^I clear hashmap$")
	public void clearHaspmap() {
		resellerInfoMap.clear();
	}

	@And("^I put the reseller balance info (.+) in the hashmap$")
	public void verifyBalanceOfReseller(String resellerId) throws IOException, SQLException{
		
		String resellerIdFromProp = readWebVariables.readPropertiesForWebVariables(resellerId);
		double amount = resultset.returnResellerAmount(resellerIdFromProp);
		
		log.info("Putting Reseller Balance to hashmap Reseller Balance is :"+amount+" and Reseller ID is: "+resellerIdFromProp+"_sender");
		
		resellerBalanceMap.put(resellerIdFromProp+"_sender", amount);
	}
	
	@And("^I verify the reseller (.+) balance after the transaction of amount (.+)$")
	public void verifyResellerBalanceAfterTransaction(String resellerId, String amount) throws IOException, SQLException{
		
		String resellerIdFromProp = readWebVariables.readPropertiesForWebVariables(resellerId);
		String amountFromProp = readWebVariables.readPropertiesForWebVariables(amount);
		
		Double resellerBalanceFromHashMap = resellerBalanceMap.get(resellerIdFromProp+"_sender");
		
		int finalbalanceOfReseller = (int) (resellerBalanceFromHashMap - Integer.parseInt(amountFromProp));
		
		double resellerAmountFromDB = resultset.returnResellerAmount(resellerIdFromProp);
		
		Assert.assertEquals(resellerAmountFromDB, finalbalanceOfReseller, Delta);
		
		log.info("ResellerBalnce From Db = "+resellerAmountFromDB);
		log.info("ResellerBalnce Calculated Manually = "+finalbalanceOfReseller);
	}
	
	@Given("^I clear the hashmap for resellerbalance verification$")
	public void emptyHashMap(){
		resellerBalanceMap.clear();
	}
	
	@Then("^User verify the balance of the reseller (.+) incase of approve requestreversal after the transaction of amount (.+)$")
	public void verifyBalanceAfterRequestReversalApprove(String resellerId,
			String amount) throws IOException {

		String ResellerIdFromProp = readWebVariables.readPropertiesForWebVariables(resellerId);
		String AmoutFromProp = readWebVariables.readPropertiesForWebVariables(amount);

		try {
			Double getCurrentBalance = (double) resultset.returnResellerAmount(ResellerIdFromProp);
			Double balanceAfterRequestReversalManualCalculated = afterBalanceOfSender + Double.parseDouble(AmoutFromProp);
			log.info("balanceAfterRequestReversalManualCalculated: " + balanceAfterRequestReversalManualCalculated);
			log.info("getCurrentBalance: " + getCurrentBalance);
			Assert.assertEquals(balanceAfterRequestReversalManualCalculated,getCurrentBalance);
		} catch (IOException e) {
			log.error(e);
		} catch (SQLException e) {
			log.error(e);
		}
	}

	@And("^I press the enter key on the webElement (.+)$")
	public void pressEnter(String xpath) throws IOException {
		WebElement pressEnter = webdriver.findElement(By.xpath(readXpaths.readPropertiesForXpaths(xpath)));
		pressEnter.sendKeys(Keys.ENTER);
		log.info("User press the Enter Key "+pressEnter);
	}

	@Then("^I get the ersReference of last Transaction from database$")
	public void getErsReferenceOfLastTransaction() {
		try {
			ersReference = resultset.returnLastERSreference();
			log.info("I get the ersReference of last Transaction from database: "+ ersReference);
		} catch (IOException e) {
			log.error(e);
		} catch (SQLException e) {
			log.error(e);
		}
	}

	@And("I enter new calculated ERS reference for requestReversal in the text field (.+) (.+)")
	public void calculateERSreferenceForReqRev(String xpath, String range) throws IOException, SQLException {
		String returnedERSforReqRev = resultset.getErsReference(readWebVariables.readPropertiesForWebVariables(range));
		log.info("ERS-Refernce: "+returnedERSforReqRev);
		log.info("XPATH: "+xpath);
		webdriver.findElement(By.xpath(readXpaths.readPropertiesForXpaths(xpath))).sendKeys(returnedERSforReqRev);
		log.info("returnedERSforReqRev: " + returnedERSforReqRev);
	}

	@And("^System verifies the total count of transactions (.+) from the database when user enters the userId as (.+) tranasctiontype as (.+) datedifference is of (.+) days and (.+) resultcode is (.+) and searchTagsFlags is (.+)$")
	public void verifyTheTransactionCount(String countFromCucumber,
			String userId, String transactionType, String datediff,
			String command, String resultcode, String digit)
			throws IOException, SQLException, ParseException {

		String userIdFromProp = readWebVariables.readPropertiesForWebVariables(userId);
		String transactionTypeFromProp = readWebVariables.readPropertiesForWebVariables(transactionType);
		String datediffFromProp = readWebVariables.readPropertiesForWebVariables(datediff);
		String commandFromProp = readWebVariables.readPropertiesForWebVariables(command);
		String resultcodeFromProp = readWebVariables.readPropertiesForWebVariables(resultcode);
		String digitFromProp = readWebVariables.readPropertiesForWebVariables(digit);
		String countFromCucumberFromProp = readXpaths.readPropertiesForXpaths(countFromCucumber);

		log.info("userIdFromProp= " + userIdFromProp);
		log.info("transactionTypeFromProp= " + transactionTypeFromProp);
		log.info("datediffFromProp= " + datediffFromProp);
		log.info("resultcodeFromProp= " + resultcodeFromProp);
		log.info("digitFromProp= " + digitFromProp);
		log.info("commandFromProp= " + commandFromProp);
		log.info("countFromCucumberFromProp= " + countFromCucumberFromProp);

		int countFromDatabase = resultset.returnCountOfRecordsFromSupportSystem
		(userIdFromProp,
		transactionTypeFromProp, 
		datediffFromProp,
		commandFromProp, 
		resultcodeFromProp, 
		digitFromProp);
		
		log.info("countFromDatabase: " + countFromDatabase);
		String countFromUI = webdriver.findElement(By.xpath(countFromCucumberFromProp)).getText();
		log.info("countFromUI: >>>>>>" + countFromUI);
		String[] array = countFromUI.split(" ");
		Assert.assertEquals(array[3], Integer.toString(countFromDatabase));
		log.info("countFromUI: " + array[3]);
		log.info("countFromDatabase: " + countFromDatabase);
		log.info("Count is Verified");
	}

	@And("^I verify the total count of reverse transactions (.+) from the database when user enters the userId as (.+) datedifference is of (.+) days and command is (.+) result code is (.+) and profile id is (.+)$")
	public void verifyTheResverseTransactionCount(String countFromCucumber,
			String userId, String datediff, String command, String resultcode,
			String profileID) throws IOException, SQLException, ParseException {

		String userIdFromProp = readWebVariables.readPropertiesForWebVariables(userId);
		String datediffFromProp = readWebVariables.readPropertiesForWebVariables(datediff);
		String commandFromProp = readWebVariables.readPropertiesForWebVariables(command);
		String resultcodeFromProp = readWebVariables.readPropertiesForWebVariables(resultcode);
		String profileIDFromProp = readWebVariables.readPropertiesForWebVariables(profileID);
		String countFromCucumberFromProp = readWebVariables.readPropertiesForWebVariables(countFromCucumber);

		log.info("userIdFromProp= " + userIdFromProp);
		log.info("datediffFromProp= " + datediffFromProp);
		log.info("profileIdFromProp= " + profileID);
		log.info("commandFromProp= " + commandFromProp);
		log.info("countFromCucumberFromProp= " + countFromCucumberFromProp);
		int countFromDatabase = resultset.returnCountOfReverseRecordsFromSupportSystem(userIdFromProp,
				datediffFromProp, commandFromProp, resultcodeFromProp,
				profileIDFromProp);
		
		String countFromUI = webdriver.findElement(By.xpath(countFromCucumberFromProp)).getText();
		log.info("countFromUI: >>>>>>" + countFromUI);
		log.info("countFromUI: >>>>>>");
		String[] array = countFromUI.split(" ");
		Assert.assertEquals(array[3], Integer.toString(countFromDatabase));
		log.info("countFromDatabase: " + countFromDatabase);
		log.info("Count is Verified");
	}

	@Given("^Log (.+)$")
	public void logMessage(String message) {
		log.info("=============>>>>Scenario: " + message + "<<<<<=============");
	}

	@And("^I refresh the page$")
	public void refreshWebPage() {
		webdriver.navigate().refresh();
	}
	
	@And("^User selects file (.+) from (.+)$")
	public void uploadFile(String filePath, String xpath) throws IOException{
		String workingDir = System.getProperty("user.dir");
		String filepath = readConfig.readPropertiesforConfig(filePath);
		String ChoosedfilePath = workingDir.concat(filepath);
		log.info("path--> "+ChoosedfilePath);
		WebElement xpathOfChooseFile = webdriver.findElement(By.xpath(readXpaths.readPropertiesForXpaths(xpath)));
		xpathOfChooseFile.sendKeys(ChoosedfilePath);
		log.info("buttonXPath: " + xpathOfChooseFile);	
	}
	
	@And("^I check change password for Reseller (.+) having resellerType (.+) and old password was (.+) and new password is (.+) and confirm password is (.+)$")
	public void isPasswordChangeRequiredOrNot(String resellerId, String resellerType, String oldPassword, String newPassword, String confirmPassword) throws IOException, SQLException, ParseException, InterruptedException{
		
		String getDefaultLastPasswordChangeTimeStamp = readWebVariables.readPropertiesForWebVariables("LAST_PASSWORD_CHANGE_DEFAULT_TIMESTAMP");
				
		String resellerIdfromprop = readWebVariables.readPropertiesForWebVariables(resellerId);
		String resellerTypefromprop = readWebVariables.readPropertiesForWebVariables(resellerType);
		
		String status = resultset.returnPasswordChange(resellerIdfromprop,resellerTypefromprop);
		log.info("Status of Password change: "+ status);
		
		if(getDefaultLastPasswordChangeTimeStamp.equals(status)){
			log.info("Password need to be changed, this is the first time for this reseller that he is going to login");
			
			inputText(oldPassword, "AgentPortalChangePasswordOldPwdfield");
			inputText(newPassword, "AgentPortalChangePasswordNewPwdfield");
			inputText(confirmPassword, "AgentPortalChangePasswordNewPwdfieldAgain");
			
			clickWebElement("AgentPortalPwdSubmitButton");
			SystemSleeps(3000);
			refreshWebPage();
			
		}else{
			log.info("Password change is not required.");
		}
	}
	
	@And("^I confirm whether to set the old password or password for Reseller (.+) having resellerType (.+) and old password was (.+) and new password is (.+) and confirm password is (.+)$")
	public void setNewPasswordOrOldPassword(String resellerId, String resellerType, String oldPassword, String newPassword, String confirmPassword) throws IOException, SQLException, ParseException, InterruptedException{
		
		String getDefaultLastPasswordChangeTimeStamp = readWebVariables.readPropertiesForWebVariables("LAST_PASSWORD_CHANGE_DEFAULT_TIMESTAMP");
				
		String resellerIdfromprop = readWebVariables.readPropertiesForWebVariables(resellerId);
		String resellerTypefromprop = readWebVariables.readPropertiesForWebVariables(resellerType);
		
		String status = resultset.returnPasswordChange(resellerIdfromprop,resellerTypefromprop);
		log.info("Status of Password change: "+ status);
		
		if(getDefaultLastPasswordChangeTimeStamp.equals(status)){
			log.info("Password need to be changed, this is the first time for this reseller that he is going to login");
			
			inputText(oldPassword, "AgentportalPassword");
						
		}else{
			inputText(newPassword, "AgentportalPassword");
			log.info("Password change is not required. User will login with the new password which he set previously");
		}
	}
	
	@After
	public void close_browser() {
		webdriver.quit();
	}
}
