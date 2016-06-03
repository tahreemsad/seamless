Feature: Request Reversal over USSD

@RR-USSD
Scenario: Request reversal over USSD
When I Perform R2R with valid data to the reseller number DIST2_MSISDN having reseller id DIST2_ID from reseller number DIST1_MSISDN with reseller id DIST1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was R2R_OPTION
When I Request Reversal from reseller DIST1_MSISDN having resellerID DIST1_ID with shortcode RR_SHORTCODE and menuoption RR_MENUOPTION and pin is RESELLER_PIN_2 and message is RR_TRANSFER_CONFORMATION_MESSAGE for amount R2R_AMOUNT component is SMSAPP_LOG and number of log lines are NO_OF_LOG_LINES and notification message is REVERSAL_LOG_MESSAGE

@APPROVE-RR-USSD
Scenario: Approve request reversal over USSD
When I Perform R2R with valid data to the reseller number DIST2_MSISDN having reseller id DIST2_ID from reseller number DIST1_MSISDN with reseller id DIST1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was R2R_OPTION
When I Request Reversal from reseller DIST1_MSISDN having resellerID DIST1_ID with shortcode RR_SHORTCODE and menuoption RR_MENUOPTION and pin is RESELLER_PIN_2 and message is RR_TRANSFER_CONFORMATION_MESSAGE for amount R2R_AMOUNT component is SMSAPP_LOG and number of log lines are NO_OF_LOG_LINES and notification message is REVERSAL_LOG_MESSAGE
When I request to approve from reseller DIST2_MSISDN with reseller id DIST2_ID to reseller id DIST1_ID with shortcode RR_SHORTCODE and menuoption APPROVE_MENUOPTION and pin is RESELLER_PIN_2 and message is RR_TRANSFER_APPROVAL_MESSAGE for amount R2R_AMOUNT component is SMSAPP_LOG and number of log lines are NO_OF_LOG_LINES and notification message is DENIAL_LOG_MESSAGE

@DENY-RR-USSD
Scenario: Deny request reversal over USSD
When I Perform R2R with valid data to the reseller number DIST2_MSISDN having reseller id DIST2_ID from reseller number DIST1_MSISDN with reseller id DIST1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was R2R_OPTION
When I Request Reversal from reseller DIST1_MSISDN having resellerID DIST1_ID with shortcode RR_SHORTCODE and menuoption RR_MENUOPTION and pin is RESELLER_PIN_2 and message is RR_TRANSFER_CONFORMATION_MESSAGE for amount R2R_AMOUNT component is SMSAPP_LOG and number of log lines are NO_OF_LOG_LINES and notification message is REVERSAL_LOG_MESSAGE
When I request to deny from reseller DIST2_MSISDN with reseller id DIST2_ID to reseller id DIST1_ID with shortcode RR_SHORTCODE and menuoption DENY_MENUOPTION and pin is RESELLER_PIN_2 and message is RR_TRANSFER_DENIAL_MESSAGE for amount R2R_AMOUNT component is SMSAPP_LOG and number of log lines are NO_OF_LOG_LINES and notification message is DENIAL_LOG_MESSAGE

@RESEND-RR-USSD
Scenario: Resend request reversal from webadmin
When I Perform R2R with valid data to the reseller number DIST2_MSISDN having reseller id DIST2_ID from reseller number DIST1_MSISDN with reseller id DIST1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was R2R_OPTION
When I Request Reversal from reseller DIST1_MSISDN having resellerID DIST1_ID with shortcode RR_SHORTCODE and menuoption RR_MENUOPTION and pin is RESELLER_PIN_2 and message is RR_TRANSFER_CONFORMATION_MESSAGE for amount R2R_AMOUNT component is SMSAPP_LOG and number of log lines are NO_OF_LOG_LINES and notification message is REVERSAL_LOG_MESSAGE
Given I browse to the WebAdmin page
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSupport
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSupportSearchTransactions
And System sleeps for 20000 seconds
Then I switch to frame
And User clicks on the button WebAdminSubMenuByReference
And I enter new calculated ERS reference for requestReversal in the text field WebAdminSubMenuByReferenceField SQL_LIMIT_RANGE_2
And System sleeps for 1000 seconds
And User clicks on the button WebAdminSubMenuByReferenceSearchButton
And System sleeps for 5000 seconds
And User clicks on the button WebAdminSubMenuByReferenceResendButton

#################NEGATIVE TESTING#############################

@RR-USSD
Scenario: Request reversal already reversed
When I Perform R2R with valid data to the reseller number DIST2_MSISDN having reseller id DIST2_ID from reseller number DIST1_MSISDN with reseller id DIST1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was R2R_OPTION
When I Request Reversal from reseller DIST1_MSISDN having resellerID DIST1_ID with shortcode RR_SHORTCODE and menuoption RR_MENUOPTION and pin is RESELLER_PIN_2 and message is RR_TRANSFER_CONFORMATION_MESSAGE for amount R2R_AMOUNT component is SMSAPP_LOG and number of log lines are NO_OF_LOG_LINES and notification message is REVERSAL_LOG_MESSAGE
When I request to approve from reseller DIST2_MSISDN with reseller id DIST2_ID to reseller id DIST1_ID with shortcode RR_SHORTCODE and menuoption APPROVE_MENUOPTION and pin is RESELLER_PIN_2 and message is RR_TRANSFER_APPROVAL_MESSAGE for amount R2R_AMOUNT component is SMSAPP_LOG and number of log lines are NO_OF_LOG_LINES and notification message is DENIAL_LOG_MESSAGE
When I Request Reversal from reseller DIST1_MSISDN having resellerID DIST1_ID with shortcode RR_SHORTCODE and menuoption RR_MENUOPTION and pin is RESELLER_PIN_2 and message is RR_TRANSFER_CONFORMATION_MESSAGE for amount R2R_AMOUNT component is SMSAPP_LOG and number of log lines are NO_OF_LOG_LINES and notification message is REVERSAL_LOG_MESSAGE

@RR-USSD
Scenario: Request reversal from other reseller
When I Perform R2R with valid data to the reseller number DIST2_MSISDN having reseller id DIST2_ID from reseller number DIST1_MSISDN with reseller id DIST1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was R2R_OPTION
When I Request Reversal from reseller DIST2_MSISDN having resellerID DIST2_ID with shortcode RR_SHORTCODE and menuoption RR_MENUOPTION and pin is RESELLER_PIN_2 and message is RR_OWN_REVERSAL_MESSAGE for amount R2R_AMOUNT component is SMSAPP_LOG and number of log lines are NO_OF_LOG_LINES and notification message is REVERSAL_LOG_MESSAGE

@RR-USSD
Scenario: Request reversal for denied transaction 
When I Perform R2R with valid data to the reseller number DIST2_MSISDN having reseller id DIST2_ID from reseller number DIST1_MSISDN with reseller id DIST1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was R2R_OPTION
When I Request Reversal from reseller DIST1_MSISDN having resellerID DIST1_ID with shortcode RR_SHORTCODE and menuoption RR_MENUOPTION and pin is RESELLER_PIN_2 and message is RR_TRANSFER_CONFORMATION_MESSAGE for amount R2R_AMOUNT component is SMSAPP_LOG and number of log lines are NO_OF_LOG_LINES and notification message is REVERSAL_LOG_MESSAGE
When I request to deny from reseller DIST2_MSISDN with reseller id DIST2_ID to reseller id DIST1_ID with shortcode RR_SHORTCODE and menuoption DENY_MENUOPTION and pin is RESELLER_PIN_2 and message is RR_TRANSFER_DENIAL_MESSAGE for amount R2R_AMOUNT component is SMSAPP_LOG and number of log lines are NO_OF_LOG_LINES and notification message is DENIAL_LOG_MESSAGE
When I Request Reversal from reseller DIST1_MSISDN having resellerID DIST1_ID with shortcode RR_SHORTCODE and menuoption RR_MENUOPTION and pin is RESELLER_PIN_2 and message is RR_TRANSFER_CONFORMATION_MESSAGE for amount R2R_AMOUNT component is SMSAPP_LOG and number of log lines are NO_OF_LOG_LINES and notification message is REVERSAL_LOG_MESSAGE

@APPROVE-RR-USSD
Scenario: Approve by other reseller over USSD
When I Perform R2R with valid data to the reseller number DIST2_MSISDN having reseller id DIST2_ID from reseller number DIST1_MSISDN with reseller id DIST1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was R2R_OPTION
When I Request Reversal from reseller DIST1_MSISDN having resellerID DIST1_ID with shortcode RR_SHORTCODE and menuoption RR_MENUOPTION and pin is RESELLER_PIN_2 and message is RR_TRANSFER_CONFORMATION_MESSAGE for amount R2R_AMOUNT component is SMSAPP_LOG and number of log lines are NO_OF_LOG_LINES and notification message is REVERSAL_LOG_MESSAGE
When I request to approve from reseller DIST1_MSISDN with reseller id DIST1_ID to reseller id DISTRIBUTOR_ID1 with shortcode RR_SHORTCODE and menuoption APPROVE_MENUOPTION and pin is RESELLER_PIN_2 and message is RR_OWN_REVERSAL_MESSAGE for amount R2R_AMOUNT component is SMSAPP_LOG and number of log lines are NO_OF_LOG_LINES and notification message is DENIAL_LOG_MESSAGE

@DENY-RR-USSD
Scenario: Deny by other reseller over USSD
When I Perform R2R with valid data to the reseller number DIST2_MSISDN having reseller id DIST2_ID from reseller number DIST1_MSISDN with reseller id DIST1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was R2R_OPTION
When I Request Reversal from reseller DIST1_MSISDN having resellerID DIST1_ID with shortcode RR_SHORTCODE and menuoption RR_MENUOPTION and pin is RESELLER_PIN_2 and message is RR_TRANSFER_CONFORMATION_MESSAGE for amount R2R_AMOUNT component is SMSAPP_LOG and number of log lines are NO_OF_LOG_LINES and notification message is REVERSAL_LOG_MESSAGE
When I request to deny from reseller DIST1_MSISDN with reseller id DIST1_ID to reseller id DIST1_ID with shortcode RR_SHORTCODE and menuoption DENY_MENUOPTION and pin is RESELLER_PIN_2 and message is RR_OWN_REVERSAL_MESSAGE for amount R2R_AMOUNT component is SMSAPP_LOG and number of log lines are NO_OF_LOG_LINES and notification message is DENIAL_LOG_MESSAGE
