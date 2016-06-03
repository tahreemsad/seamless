Feature: Perform Voucher over Terminal

@SELL_VOUCHER_VOT @SELL_VOUCHER_VOT_ZM_MTN
Scenario: User sell one voucher from USSD
Given Reseller with DIST1_ID and DIST1_MSISDN from USSDapp using USSD_SHORT_CODE and VOUCHER_PURCHASE_MENU_OPTION and sub menu is VOUCHER_PURCHASE_SUB_MENU_OPTION and sell a voucher of VOUCHER_DENOMINATION and RESELLER_PIN and CONFIRMATION and VOUCHER_DENOMINATION_PRICE and in response SUCCESS_MESSAGE message is received also message is send to reseller from KANNEL_LOG logs lines SMSBOX_LOG_LINES where class type is CLASS_TYPE and success message is SENDER_RESELLER_SMS


@SELL_VOUCHER_VOT_BLOCK_PRODUCT
Scenario: User sell one voucher from USSD but product is blocked
Given I browse to the WebAdmin page
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 5000 seconds
And User clicks on the button WebAdminOperatorsMainMenu
And User clicks on the button WebAdminOperatorsSubMenuOperatorName
And System sleeps for 2000 seconds
And User clicks on the button WebAdminOperatorsSubSubMenuProducts
And System sleeps for 2000 seconds
And User clicks on the button WebAdminOperatorsSelectProduct
And System sleeps for 2000 seconds
And User clicks on the button WebAdminOperatorsProductBlock
And System sleeps for 2000 seconds
And User accept the alert box
And System sleeps for 2000 seconds
Given Reseller with DIST1_ID and DIST1_MSISDN from USSDapp using USSD_SHORT_CODE and VOUCHER_PURCHASE_MENU_OPTION and sub menu is VOUCHER_PURCHASE_SUB_MENU_OPTION and sell a voucher of VOUCHER_DENOMINATION and RESELLER_PIN and CONFIRMATION and VOUCHER_DENOMINATION_PRICE and in response BLOCKED_PRODUCT_MESSAGE message is received also message is send to reseller from KANNEL_LOG logs lines SMSBOX_LOG_LINES where class type is CLASS_TYPE and success message is SMS_SUCCESS_MESSAGE
And User clicks on the button WebAdminOperatorsProductUnblock
And User accept the alert box
And Product with name 100_FCFA and status 0 is updated in the Database
When I restart the component core

@VoT_Revoke_Product
Scenario: Revoke product from webadmin
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
And User clicks on the button WebAdminStockVoucherBatchesRevokeBtn
And User accept the alert box

@SELL_VOUCHER_VOT_REVOKE_PRODUCT
Scenario: User sell one voucher from USSD but product is blocked
Given Reseller with DIST1_ID and DIST1_MSISDN from USSDapp using USSD_SHORT_CODE and VOUCHER_PURCHASE_MENU_OPTION and sub menu is VOUCHER_PURCHASE_SUB_MENU_OPTION and sell a voucher of VOUCHER_DENOMINATION and RESELLER_PIN and CONFIRMATION and VOUCHER_DENOMINATION_PRICE and in response BLOCKED_PRODUCT_MESSAGE message is received also message is send to reseller from KANNEL_LOG logs lines SMSBOX_LOG_LINES where class type is CLASS_TYPE and success message is SMS_SUCCESS_MESSAGE

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

@VoT-UNRevokeProduct
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