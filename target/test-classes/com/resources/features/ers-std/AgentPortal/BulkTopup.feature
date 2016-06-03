Feature: Bulk Topup

@BulkTopup-AgentPortal-gh-glo1 @BulkTopup-AgentPortal-ng_glo
Scenario: Reseller successfully topup to more than one subscriber present in file
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter OPERATOR_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I enter WEBADMIN_PASSWORD in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 5 seconds
And User clicks on the button AgentportalMainSubscribersButton
And User clicks on the button AgentportalbulkTopupButton
And System sleeps for 2000 seconds
And User clicks on the button AgentPortalBulkTopupFileSelection
And System sleeps for 2000 seconds
And User clicks on the button AgentPortalBrowseFile
And System sleeps for 2000 seconds
And User selects file BULK_TOPUP_FILE from AgentPortalBrowseFile
And System sleeps for 2000 seconds
And User clicks on the button AgenportalReceiptOKButton
And System sleeps for 2000 seconds

@BulkTopup-AgentPortal-gh-glo @BulkTopup-AgentPortal-ng_glo
Scenario: Reseller successfully topup to more than one subscriber by selection
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter OPERATOR_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I enter WEBADMIN_PASSWORD in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 5 seconds
And User clicks on the button AgentportalMainSubscribersButton
And User clicks on the button AgentportalbulkTopupButton
And User clicks on the button AgentPortalBulkTopupTypedropdown
And I input SUBSCRIBER_1 in the AgentPortalBulkTopupMSISDN1 field with IS_PREFIX_NOTREQUIRED
And I enter BulkAmount_SUBSCRIBER_1 in the AgentPortalBulkTopupAmount1 field
And User clicks on the button AgentPortalBulkTopupAddNewButton
And I input SUBSCRIBER_2 in the AgentPortalBulkTopupMSISDN2 field with IS_PREFIX_NOTREQUIRED
And I enter BulkAmount_SUBSCRIBER_2 in the AgentPortalBulkTopupAmount2 field
And User clicks on the button AgentPortalBulkTopupAddNewButton
And I input SUBSCRIBER_3 in the AgentPortalBulkTopupMSISDN3 field with IS_PREFIX_NOTREQUIRED
And I enter BulkAmount_SUBSCRIBER_3 in the AgentPortalBulkTopupAmount3 field
And User clicks on the button AgentPortalBulkTopupAddNewButton
And I input SUBSCRIBER_4 in the AgentPortalBulkTopupMSISDN4 field with IS_PREFIX_NOTREQUIRED
And I enter BulkAmount_SUBSCRIBER_4 in the AgentPortalBulkTopupAmount4 field
And User clicks on the button AgentPortalBulkTopupAddNewButton
And I input SUBSCRIBER_5 in the AgentPortalBulkTopupMSISDN5 field with IS_PREFIX_NOTREQUIRED
And I enter BulkAmount_SUBSCRIBER_5 in the AgentPortalBulkTopupAmount5 field
And System sleeps for 2000 seconds
And User clicks on the button AgentPortalBulkTopupSubmit
And System sleeps for 2000 seconds
And I press the enter key on the webElement AgenportalReceiptOKButton
And System sleeps for 2000 seconds

###################################################################################################################################################################
#These test cases are specifically for CI-MTN customer as the hierarchy is totally different in CI-MTN, so we are going to write seprate test cases for this customer

@BulkTopup-AgentPortal-ci-mtn
Scenario: Reseller successfully topup to more than one subscriber present in file
Given I browse to the Agentportal page
Given I clear the hashmap for resellerbalance verification
And I put the reseller balance info SDEAL1_ID in the hashmap 
And System sleeps for 2000 seconds
And I enter SDEAL1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I confirm whether to set the old password or password for Reseller SDEAL1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalLoginButton
And System sleeps for 3000 seconds
And User clicks on the button AgentportalMainSubscribersButton
And System sleeps for 2000 seconds
And User clicks on the button AgentportalbulkTopupButton
And System sleeps for 2000 seconds
And User clicks on the button AgentPortalBulkTopupFileSelection
And System sleeps for 2000 seconds
And User clicks on the button AgentPortalBrowseFile
And System sleeps for 2000 seconds
And User selects file BULK_TOPUP_FILE from AgentPortalBrowseFile
And System sleeps for 5000 seconds
And User clicks on the button AgentportalSelectFileBulkTopupOKButton
And System sleeps for 5000 seconds
And User clicks on the button AgentPortalBulkTopupSubmit
And System sleeps for 5000 seconds
Then I verify the popup text AgentportalVerfiyBulkTopupReceipt with the user text AGENTPORTAL_VALID_BULKTOPUP_AMOUNT_RECEIPT on the page
And System sleeps for 2000 seconds
And User clicks on the button AgenportalReceiptOKButton
And I verify the reseller SDEAL1_ID balance after the transaction of amount RESELLER_TOTAL_BULK_AMOUNT