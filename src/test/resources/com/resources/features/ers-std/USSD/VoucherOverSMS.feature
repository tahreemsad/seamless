Feature: Purchase Voucher Over SMS

@VoS-USSD
Scenario:Purchase Single VoS  from Distributor to Subscriber
When User sell vouchers with reseller msisdn DIST1_MSISDN using shortcode USSD_SHORT_CODE by choosing menu option USSD_MENU_VoS and sub menu option USSD_SUB_MENU_VOS and selecting denomination option DENOMINATION_OPTION_1 for subscriber msisdn SUBSCRIBER_4 entering no of vouchers SINGLE_VOUCHER with reseller pin RESELLER_PIN_2 get ussd notification MESSAGE after confirmation by CONFIRM for reseller id DIST1_ID and denomination is DENOMINATION sender reseller recieve sms SENDER_RESELLER_SMS and subscriber recieve sms RECIVER_SUBSCRIBER_SMS and sms from kannel KANNEL_LOG and line from log SMSBOX_LOG_LINES

@VoS-USSD
Scenario:Purchase multiple VoS  from Distributor to Subscriber
When User sell vouchers with reseller msisdn DIST1_MSISDN using shortcode USSD_SHORT_CODE by choosing menu option USSD_MENU_VoS and sub menu option USSD_SUB_MENU_VOS and selecting denomination option DENOMINATION_OPTION_1 for subscriber msisdn SUBSCRIBER_4 entering no of vouchers SINGLE_VOUCHER with reseller pin RESELLER_PIN_2 get ussd notification MESSAGE after confirmation by CONFIRM for reseller id DIST1_ID and denomination is DENOMINATION sender reseller recieve sms SENDER_RESELLER_SMS and subscriber recieve sms RECIVER_SUBSCRIBER_SMS and sms from kannel KANNEL_LOG and line from log SMSBOX_LOG_LINES

@VoS-USSD-Blocked
Scenario:Purchase Blocked Vouchers
Given I browse to the WebAdmin page
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 5000 seconds
#Then I should see InfoPageText on the page
And User clicks on the button WebAdminOperatorsMainMenu
And User clicks on the button WebAdminOperatorsSubMenuOperatorName
And User clicks on the button WebAdminOperatorsSubSubMenuProducts
And User clicks on the button WebAdminOperatorsSelectProduct
And User clicks on the button WebAdminOperatorsProductBlock
And User accept the alert box
When User sell vouchers with reseller msisdn DIST1_MSISDN using shortcode USSD_SHORT_CODE by choosing menu option USSD_MENU_VoS and sub menu option USSD_SUB_MENU_VOS and selecting denomination option DENOMINATION_OPTION_1 for subscriber msisdn SUBSCRIBER_4 entering no of vouchers SINGLE_VOUCHER with reseller pin RESELLER_PIN_2 get ussd notification MESSAGE after confirmation by CONFIRM for reseller id DIST1_ID and denomination is DENOMINATION sender reseller recieve sms SENDER_RESELLER_SMS and subscriber recieve sms RECIVER_SUBSCRIBER_SMS and sms from kannel KANNEL_LOG and line from log SMSBOX_LOG_LINES
And User accept the alert box
And Product with name 100_USD and status 0 is updated in the Database
When I restart the component core

@VoS-USSD-RevokeProduct
Scenario: Revoke product from webadmin
Given I browse to the WebAdmin page
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 2000 seconds
#Then I should see InfoPageText on the page
And User clicks on the button WebAdminStockMainMenu
And User clicks on the button WebAdminStockVoucherBatches
And User clicks on the button WebAdminStockVoucherBatchesSearch
And User clicks on the button WebAdminStcokVoucherBatchetoRevoke
And System sleeps for 2000 seconds
And I enter RevokeTxt in the WebAdminStockVocherBatchesRevokeText field
And System sleeps for 2000 seconds
And User clicks on the button WebAdminStockVoucherBatchesRevokeBtn
And User accept the alert box

@VoS-USSD-RevokeProduct_SELL
Scenario: Purchase Out of Stock Vouchers
When User sell vouchers with reseller msisdn DIST1_MSISDN using shortcode USSD_SHORT_CODE by choosing menu option USSD_MENU_VoS and selecting denomination option DENOMINATION_OPTION_1 for subscriber msisdn SUBSCRIBER_4 entering no of vouchers NO_OF_VOCHERS with reseller pin RESELLER_PIN_2 get ussd notification OUTOFSTOCK_PRODUCT_MESSAGE after confirmation by CONFIRM for reseller id DISTRIBUTOR_ID and denomination is DENOMINATION sender reseller recieve sms SENDER_RESELLER_SMS and subscriber recieve sms RECIVER_SUBSCRIBER_SMS and sms from kannel KANNEL_LOG and line from log SMSBOX_LOG_LINES

@VoS-USSD-UNRevokeProduct
Scenario: Unrevoke voucher batch from webadmin
Given I browse to the WebAdmin page
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 2000 seconds
And User clicks on the button WebAdminStockMainMenu
And User clicks on the button WebAdminStockVoucherBatches
And User clicks on the button WebAdminStockVoucherBatchesSearch
And User clicks on the button WebAdminStcokVoucherBatchetoRevoke
And System sleeps for 2000 seconds
And I enter RevokeTxt in the WebAdminStockVocherBatchesRevokeText field
And System sleeps for 2000 seconds
And User clicks on the button WebAdminStockVoucherBatchesUnRevokeBtn
And User accept the alert box
