Feature: Bulk Credit Transfer

@BulkTransfer-AgentPortal-ng_glo
Scenario: Treasury reseller successfully transfer to more than one dealers present in file
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter TREASURY_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I enter WEBADMIN_PASSWORD in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 5 seconds
And User clicks on the button AgentportalMainAgentsButton
And User clicks on the button AgentportalbulkCreditTransferButton
And System sleeps for 2000 seconds
And User clicks on the button AgentPortalBulkTopupFileSelection
And System sleeps for 2000 seconds
And User clicks on the button AgentPortalBrowseFile
And User selects file BULK_TRANSFER_FILE from AgentPortalBrowseFile
And System sleeps for 1000 seconds
And User clicks on the button AgentPortalBrowseFileOKButton
And System sleeps for 2000 seconds
And User clicks on the button AgentPortalBulkCreditTransferSubmitButton
And System sleeps for 3000 seconds
Then I should see BulkTransferCompletedSuccessfullyText on the page AgentPortalBulkTransferPopupReceiptMsg1
Then I should see BulkTransferCompletedSuccessfullyText on the page AgentPortalBulkTransferPopupReceiptMsg2
And System sleeps for 3000 seconds
And User clicks on the button AgenportalBulkCreditTransferReceiptOKButton
And System sleeps for 3000 seconds

@BulkTransfer-AgentPortal-ng_glo
Scenario: Treasury reseller successfully transfer to more than one dealers by selection
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter TREASURY_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I enter WEBADMIN_PASSWORD in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 5 seconds
And User clicks on the button AgentportalMainAgentsButton
And User clicks on the button AgentportalbulkCreditTransferButton
And System sleeps for 2000 seconds
#And User clicks on the button AgentPortalBulkTransferTypedropdown
And I input RESELLER_1 in the AgentPortalBulkTransferMSISDN1 field with IS_PREFIX_NOTREQUIRED
And I enter BulkTransferAmount_RESELLER_1 in the AgentPortalBulkTransferAmount1 field
And User clicks on the button AgentPortalBulkTransferAddNewButton
And I input RESELLER_2 in the AgentPortalBulkTransferMSISDN2 field with IS_PREFIX_NOTREQUIRED
And I enter BulkTransferAmount_RESELLER_2 in the AgentPortalBulkTransferAmount2 field
And User clicks on the button AgentPortalBulkTransferAddNewButton 
And I input RESELLER_3 in the AgentPortalBulkTransferMSISDN3 field with IS_PREFIX_NOTREQUIRED
And I enter BulkTransferAmount_RESELLER_3 in the AgentPortalBulkTransferAmount3 field
And System sleeps for 2000 seconds
And User clicks on the button AgentPortalBulkCreditTransferSubmitButton
And System sleeps for 2000 seconds
Then I should see BulkTransferCompletedSuccessfullyText on the page AgentPortalBulkTransferPopupReceiptMsg1
Then I should see BulkTransferCompletedSuccessfullyText on the page AgentPortalBulkTransferPopupReceiptMsg2
Then I should see BulkTransferCompletedSuccessfullyText on the page AgentPortalBulkTransferPopupReceiptMsg3
And System sleeps for 3000 seconds
And I press the enter key on the webElement AgenportalBulkCreditTransferReceiptOKButton
And System sleeps for 3000 seconds