Feature:Search Transaction         
          
@SearchTransaction-WebAdmin     
Scenario: Search for the records using the filters (userid, Type=Transfer, period=1 hour, status=Success)
#And I preform r2r before search transaction from reseller R2R_RECEIVER_RESELLER_MSISDN with id OPERATOR_ID from source OPERATOR_MSISDN with a amount R2R_AMOUNT having pin RESELLER_NEW_PIN and return message is TRANSFER_CONFORMATION_MESSAGE having shortcode USSD_SHORT_CODE and menu option is R2R_OPTION
Given I browse to the WebAdmin page
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 2000 seconds
And User clicks on the button WebAdminMainMenuSupport
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSupportSearchTransactions
And System sleeps for 5000 seconds
Then I switch to frame
And System sleeps for 1000 seconds
And I enter OPERATOR_ID in the WebAdminMainMenuSupportSearchTransactionsResellerIDfield field
And I deselect SearchTransactionTypeCreditTransfer from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I deselect SearchTransactionTypeTopup from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I select SearchTransactionTypeCreditTransfer from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I select SearchTransactionStatusSuccess from dropdown WebAdminSearchTransactionStatusValueBox 
And I select SearchTransactionPeriodPerHour from dropdown WebAdminSearchTransactionPeriodDropDown
And User clicks on the button WebAdminMainMenuSupportSearchTransactionsSearchButton
And System sleeps for 6000 seconds
And System verifies the total count of transactions WebAdminSearchTransactionTotalTransactions from the database when user enters the userId as OPERATOR_ID tranasctiontype as TRANSACTION_TYPE datedifference is of DATE_DIFF days and COMMAND_SUCCESS resultcode is NOT_IN_RESULT_CODE_FOR_FOR_SUCCESSFULL_TRANSACTIONS and searchTagsFlags is SUCCESS_CODE

@SearchTransaction-WebAdmin
Scenario: Search for the records using the filters (userid, Type=Transfer, period=1 hour, status=Failed)
#And I Perform R2R with invalid data before check last transaction to the reseller number OPERATOR_MSISDN having resellerID OPERATOR_ID from reseller number OPERATOR_MSISDN with an amount R2R_AMOUNT and pin is R2R_INVALID_PIN and USSD_INVALID_PIN in message is returned after execution having shortcode USSD_SHORT_CODE and option was R2R_OPTION
Given I browse to the WebAdmin page
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 2000 seconds
And User clicks on the button WebAdminMainMenuSupport
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSupportSearchTransactions
And System sleeps for 5000 seconds
Then I switch to frame
And System sleeps for 1000 seconds
And I enter OPERATOR_ID in the WebAdminMainMenuSupportSearchTransactionsResellerIDfield field
And I deselect SearchTransactionTypeCreditTransfer from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I deselect SearchTransactionTypeTopup from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I select SearchTransactionTypeCreditTransfer from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I select SearchTransactionStatusFailed from dropdown WebAdminSearchTransactionStatusValueBox 
And I select SearchTransactionPeriodPerHour from dropdown WebAdminSearchTransactionPeriodDropDown
And User clicks on the button WebAdminMainMenuSupportSearchTransactionsSearchButton
And System sleeps for 6000 seconds
And System verifies the total count of transactions WebAdminSearchTransactionTotalTransactions from the database when user enters the userId as OPERATOR_ID tranasctiontype as TRANSACTION_TYPE datedifference is of DATE_DIFF days and COMMAND_FAIL resultcode is NOT_IN_RESULT_CODE_FOR_FOR_FAILED_TRANSACTIONS and searchTagsFlags is SUCCESS_CODE

@SearchTransaction-WebAdmin
Scenario: Search for the records using the filters (userid, Type=Topup, period=1 hour, status=Success)
Given I browse to the WebAdmin page
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 2000 seconds
And User clicks on the button WebAdminMainMenuSupport
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSupportSearchTransactions
And System sleeps for 5000 seconds
Then I switch to frame
And System sleeps for 1000 seconds
And I enter DIST1_ID in the WebAdminMainMenuSupportSearchTransactionsResellerIDfield field
And I deselect SearchTransactionTypeCreditTransfer from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I deselect SearchTransactionTypeTopup from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I select SearchTransactionTypeTopup from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I select SearchTransactionStatusSuccess from dropdown WebAdminSearchTransactionStatusValueBox 
And I select SearchTransactionPeriodPerHour from dropdown WebAdminSearchTransactionPeriodDropDown
And User clicks on the button WebAdminMainMenuSupportSearchTransactionsSearchButton
And System sleeps for 6000 seconds
And System verifies the total count of transactions WebAdminSearchTransactionTotalTransactions from the database when user enters the userId as DIST1_ID tranasctiontype as TRANSACTION_TYPE_TOPUP datedifference is of DATE_DIFF days and COMMAND_SUCCESS resultcode is NOT_IN_RESULT_CODE_FOR_FOR_SUCCESSFULL_TRANSACTIONS and searchTagsFlags is TOPUP_CODE

@SearchTransaction-WebAdmin
Scenario:Search for the records using the filters (userid, Type=Topup, period=1 hour, status=Failed)
Given I browse to the WebAdmin page
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 2000 seconds
And User clicks on the button WebAdminMainMenuSupport
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSupportSearchTransactions
And System sleeps for 5000 seconds
Then I switch to frame
And System sleeps for 1000 seconds
And I enter DIST1_ID in the WebAdminMainMenuSupportSearchTransactionsResellerIDfield field
And I deselect SearchTransactionTypeCreditTransfer from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I deselect SearchTransactionTypeTopup from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I select SearchTransactionTypeTopup from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I select SearchTransactionStatusFailed from dropdown WebAdminSearchTransactionStatusValueBox 
And I select SearchTransactionPeriodPerHour from dropdown WebAdminSearchTransactionPeriodDropDown
And User clicks on the button WebAdminMainMenuSupportSearchTransactionsSearchButton
And System sleeps for 6000 seconds
And System verifies the total count of transactions WebAdminSearchTransactionTotalTransactions from the database when user enters the userId as DIST1_ID tranasctiontype as TRANSACTION_TYPE_TOPUP datedifference is of DATE_DIFF days and COMMAND_FAIL resultcode is NOT_IN_RESULT_CODE_FOR_FOR_FAILED_TRANSACTIONS and searchTagsFlags is TOPUP_CODE

@SearchTransaction-WebAdmin
Scenario:Search for the records using the filters (userid, Type=CreditTypeReversal, period=1 hour, status=Approved/Success)
Given I browse to the WebAdmin page
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 2000 seconds
And User clicks on the button WebAdminMainMenuSupport
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSupportSearchTransactions
And System sleeps for 5000 seconds
Then I switch to frame
And System sleeps for 1000 seconds
And I enter OPERATOR_ID in the WebAdminMainMenuSupportSearchTransactionsResellerIDfield field
And I deselect SearchTransactionTypeCreditTransfer from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I deselect SearchTransactionTypeTopup from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I select SearchTransactionTypeRequestReversal from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I select SearchTransactionStatusSuccess from dropdown WebAdminSearchTransactionStatusValueBox 
And User clicks on the button WebAdminMainMenuSupportSearchTransactionsSearchButton
And System sleeps for 6000 seconds
And I verify the total count of reverse transactions WebAdminSearchTransactionTotalTransactions from the database when user enters the userId as OPERATOR_ID datedifference is of DATE_DIFF days and command is COMMAND_SUCCESS result code is NOT_IN_RESULT_CODE_FOR_FOR_SUCCESSFULL_TRANSACTIONS and profile id is REVERSE_CREDIT_TRANSFER_PROFILE_ID 

@SearchTransaction-WebAdmin
Scenario:Search for the records using the filters (userid, Type=CreditTypeReversal, period=1 hour, status=Denied/Failed)
Given I browse to the WebAdmin page
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 2000 seconds
And User clicks on the button WebAdminMainMenuSupport
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSupportSearchTransactions
And System sleeps for 5000 seconds
Then I switch to frame
And System sleeps for 1000 seconds
And I enter OPERATOR_ID in the WebAdminMainMenuSupportSearchTransactionsResellerIDfield field
And I deselect SearchTransactionTypeCreditTransfer from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I deselect SearchTransactionTypeTopup from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And User clicks on the button WebAdminSearchTransactionTypeValueCreditTransferValue
And I select SearchTransactionTypeRequestReversal from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I select SearchTransactionStatusFailed from dropdown WebAdminSearchTransactionStatusValueBox 
And I select SearchTransactionPeriodPerHour from dropdown WebAdminSearchTransactionPeriodDropDown
And User clicks on the button WebAdminMainMenuSupportSearchTransactionsSearchButton
And System sleeps for 6000 seconds
And I verify the total count of reverse transactions WebAdminSearchTransactionTotalTransactions from the database when user enters the userId as OPERATOR_ID datedifference is of DATE_DIFF days and command is COMMAND_FAIL result code is NOT_IN_RESULT_CODE_FOR_FOR_FAILED_TRANSACTIONS and profile id is REVERSE_CREDIT_TRANSFER_PROFILE_ID

###################################################################################################################################################################
#These test cases are specifically for CI-MTN customer as the hierarchy is totally different in CI-MTN, so we are going to write seprate test cases for this customer

@SearchTransaction-WebAdmin-ci-mtn     
Scenario: Search for the records using the filters (userid, Type=Transfer, period=1 day, status=Success)
When I Perform R2R with valid data to the reseller number SDEAL1_MSISDN having reseller id SDEAL1_ID from reseller number OPERATOR_MSISDN with reseller id OPERATOR_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION
Given I browse to the WebAdmin page
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 2000 seconds
And User clicks on the button WebAdminMainMenuSupport
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSupportSearchTransactions
And System sleeps for 5000 seconds
Then I switch to frame
And System sleeps for 1000 seconds
And I enter OPERATOR_ID in the WebAdminMainMenuSupportSearchTransactionsResellerIDfield field
And I deselect SearchTransactionTypeCreditTransfer from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I deselect SearchTransactionTypeTopup from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I select SearchTransactionTypeCreditTransfer from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox 
And I select SearchTransactionStatusSuccess from dropdown WebAdminSearchTransactionStatusValueBox 
And I select SearchTransactionPeriodPerDay from dropdown WebAdminSearchTransactionPeriodDropDown
And User clicks on the button WebAdminMainMenuSupportSearchTransactionsSearchButton
And System sleeps for 6000 seconds
And System verifies the total count of transactions WebAdminSearchTransactionTotalTransactions from the database when user enters the userId as OPERATOR_ID tranasctiontype as TRANSACTION_TYPE datedifference is of DATE_DIFF days and COMMAND_SUCCESS resultcode is NOT_IN_RESULT_CODE_FOR_FOR_SUCCESSFULL_TRANSACTIONS and searchTagsFlags is SUCCESS_CODE

@SearchTransaction-WebAdmin-ci-mtn
Scenario: Search for the records using the filters (userid, Type=Transfer, period=1 hour, status=Failed)
When I Perform R2R with valid data to the reseller number DEAL1-1_MSISDN having reseller id DEAL1-1_ID from reseller number SDEAL1_MSISDN with reseller id SDEAL1_ID with an amount INVALID_R2R_AMOUNT and pin is RESELLER_PIN_2 and USSD_INVALID_AMOUNT in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION
Given I browse to the WebAdmin page
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 2000 seconds
And User clicks on the button WebAdminMainMenuSupport
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSupportSearchTransactions
And System sleeps for 5000 seconds
Then I switch to frame
And System sleeps for 1000 seconds
And I enter SDEAL1_ID in the WebAdminMainMenuSupportSearchTransactionsResellerIDfield field
And I deselect SearchTransactionTypeCreditTransfer from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I deselect SearchTransactionTypeTopup from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I select SearchTransactionTypeCreditTransfer from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I select SearchTransactionStatusFailed from dropdown WebAdminSearchTransactionStatusValueBox 
And I select SearchTransactionPeriodPerDay from dropdown WebAdminSearchTransactionPeriodDropDown
And User clicks on the button WebAdminMainMenuSupportSearchTransactionsSearchButton
And System sleeps for 6000 seconds
And System verifies the total count of transactions WebAdminSearchTransactionTotalTransactions from the database when user enters the userId as SDEAL1_ID tranasctiontype as TRANSACTION_TYPE datedifference is of DATE_DIFF days and COMMAND_FAIL resultcode is NOT_IN_RESULT_CODE_FOR_FOR_FAILED_TRANSACTIONS and searchTagsFlags is SUCCESS_CODE

@SearchTransaction-WebAdmin-ci-mtn
Scenario: Search for the records using the filters (userid, Type=Topup, period=1 day, status=Success)
When User Performs R2S with valid data to the subscriber number SUBSCRIBER_1 from reseller number SDEAL1_MSISDN having resellerID SDEAL1_ID with an amount TOPUP_AMOUNT and pin is RESELLER_PIN_2 and R2S_TOPUP_AMOUNT_CONFIRMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_MENU_TOPUP
Given I browse to the WebAdmin page
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 2000 seconds
And User clicks on the button WebAdminMainMenuSupport
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSupportSearchTransactions
And System sleeps for 5000 seconds
Then I switch to frame
And System sleeps for 1000 seconds
And I enter SDEAL1_ID in the WebAdminMainMenuSupportSearchTransactionsResellerIDfield field
And I deselect SearchTransactionTypeCreditTransfer from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I deselect SearchTransactionTypeTopup from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I select SearchTransactionTypeTopup from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I select SearchTransactionStatusSuccess from dropdown WebAdminSearchTransactionStatusValueBox 
And I select SearchTransactionPeriodPerDay from dropdown WebAdminSearchTransactionPeriodDropDown
And User clicks on the button WebAdminMainMenuSupportSearchTransactionsSearchButton
And System sleeps for 6000 seconds
And System verifies the total count of transactions WebAdminSearchTransactionTotalTransactions from the database when user enters the userId as SDEAL1_ID tranasctiontype as TRANSACTION_TYPE_TOPUP datedifference is of DATE_DIFF days and COMMAND_SUCCESS resultcode is NOT_IN_RESULT_CODE_FOR_FOR_SUCCESSFULL_TRANSACTIONS and searchTagsFlags is TOPUP_CODE

@SearchTransaction-WebAdmin-ci-mtn
Scenario: Search for the records using the filters (userid, Type=Topup, period=1 hour, status=Failed)
When User Performs R2S with valid data to the subscriber number SUBSCRIBER_1 from reseller number SDEAL1_MSISDN having resellerID SDEAL1_ID with an amount INVALID_TOPUP_AMOUNT and pin is RESELLER_PIN_2 and USSD_INVALID_AMOUNT in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_MENU_TOPUP
Given I browse to the WebAdmin page
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 2000 seconds
And User clicks on the button WebAdminMainMenuSupport
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSupportSearchTransactions
And System sleeps for 5000 seconds
Then I switch to frame
And System sleeps for 1000 seconds
And I enter SDEAL1_ID in the WebAdminMainMenuSupportSearchTransactionsResellerIDfield field
And I deselect SearchTransactionTypeCreditTransfer from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I deselect SearchTransactionTypeTopup from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I select SearchTransactionTypeTopup from dropdown WebAdminSearchTransactionTypeValueCreditTransferBox
And I select SearchTransactionStatusFailed from dropdown WebAdminSearchTransactionStatusValueBox 
And I select SearchTransactionPeriodPerDay from dropdown WebAdminSearchTransactionPeriodDropDown
And User clicks on the button WebAdminMainMenuSupportSearchTransactionsSearchButton
And System sleeps for 6000 seconds
And System verifies the total count of transactions WebAdminSearchTransactionTotalTransactions from the database when user enters the userId as SDEAL1_ID tranasctiontype as TRANSACTION_TYPE_TOPUP datedifference is of DATE_DIFF days and COMMAND_FAIL resultcode is NOT_IN_RESULT_CODE_FOR_FOR_FAILED_TRANSACTIONS and searchTagsFlags is TOPUP_CODE
