Feature: Search Transactions

@SearchTrans-AgentPortal
Scenario: Search single Transaction from Agentportal
Given Log Search single Transaction from Agentportal 
Given Log R2R transaction allowed from distributor to sub-distributor
Given I verify the balance of the reseller DIST1 before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
#And I select English from dropdown AgentportalLoginpagedropdown
And I enter DIST1 in the AgentportalResellerId field
And I enter webadmin in the AgentportalUserId field
And I enter 20142014 in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 5 seconds
And User clicks on the button AgentportalMainAgentsButton
And User clicks on the button AgentportalCreditTransferButton
And I enter SUBDIST2-1 in the AgentportalCreditTransferAgentIdfield field
And I enter 2456020100 in the AgentportalCreditTransferAgentMSISDNfield field
And I enter 1000 in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
Then I verify the popup text AgentportalCreditTransferReceipt with the user text Credit Transfer Receipt on the page
Then I verify the balance of the reseller DIST1 after the transaction of amount 1000
And User clicks on the button AgenportalReceiptOKButton
Then I get the ersReference of last Transaction from database
And User clicks on the button AgentportalMainTransactionButton
And User clicks on the button AgentportalSingleTransactionSearch
And User enter ersReference in the textfield AgenportalTransactionReferenceField
And User clicks on the button AgenportalTransactionReferenceSubmitButton
And User compare the value DIST1 with the text field AgenPortalSingleTransactionDetailSender value
And User compare the value 0 (SUCCESS) with the text field AgenPortalSingleTransactionDetailResultCode value
And User compare the value Success with the text field AgenPortalSingleTransactionDetailResultDescription value
And User compare the value Completed with the text field AgenPortalSingleTransactionDetailChaninState value
And User compare the value WEB with the text field AgenPortalSingleTransactionDetailChannel value
And I compare the value of ERSreference with the input field AgenPortalSingleTransactionDetailReference

@SearchTrans-AgentPortal
Scenario: Request Reversal from Agentportal (approved)
Given Log Request Reversal from Agentportal (approved)
Given I verify the balance of the sender reseller DIST1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter DIST1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I enter WEBADMIN_PASSWORD in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 5 seconds
And User clicks on the button AgentportalMainAgentsButton
And User clicks on the button AgentportalCreditTransferButton
And I enter SUBDIST2-1_ID in the AgentportalCreditTransferAgentIdfield field
And I enter SUBDSIT2-1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
Then I verify the popup text AgentportalCreditTransferReceipt with the user text AGENTPORTAL_VALID_AMOUNT_RECEIPT on the page
Then I verify the balance of the sender reseller DIST1_ID after the transaction of amount R2R_AMOUNT
And User clicks on the button AgenportalReceiptOKButton
Then I get the ersReference of last Transaction from database
And User clicks on the button AgentportalTransactionLink
And User clicks on the button AgenPortalSearchTransactionSubmitButton
And System sleeps for 5 seconds
And I select REQUEST_REVERSAL_OPTION from dropdown AgenPortalSearchTransactionRequestReversalDropdown
And User clicks on the button AgenPortalSearchTransactionRequestReversalGoLink
And System sleeps for 5 seconds
And I enter AGENTPORTAL_COMMENT_BOX_VALUE_REQUEST_REVERSAL in the AgenPortalSearchTransactionRequestReversalCommentField field
And User clicks on the button AgenPortalSearchTransactionRequestReversalCommentPopup
And System sleeps for 6 seconds
Then I verify the popup text AgenPortalResponsePopup with the user text AGENTPORTAL_VALID_REQUEST_REVERSAL on the page
Given I browse to the WebAdmin page
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 2000 seconds
And User clicks on the button WebAdminMainMenuSupport
And System sleeps for 2000 seconds
And User clicks on the button WebAdminMainMenuSupportSearchTransactions
And System sleeps for 1000 seconds
Then I switch to frame
And System sleeps for 5000 seconds
And User clicks on the button WebAdminSubMenuByReference
And I enter new calculated ERS reference for requestReversal in the text field WebAdminSubMenuByReferenceField SQL_LIMIT_RANGE_1
And User clicks on the button WebAdminSubMenuByReferenceSearchButton
And System sleeps for 3000 seconds
And User clicks on the button WebAdminApproveTransactionButton
And System sleeps for 5000 seconds
And I enter ROOT_USER_PASSWORD in the WebAdminReqRevPassword field
And User clicks on the button WebAdminReqRevSubmitButton
And System sleeps for 5000 seconds
Then I verify the popup text WebAdminTransSucceededPopup with the user text REQUEST_REVERSAL_APPROVED_TEXT on the page
Then User verify the balance of the reseller DIST1_ID incase of approve requestreversal after the transaction of amount R2R_AMOUNT

@SearchTrans-AgentPortal
Scenario: Request Reversal from Agentportal (deny)
Given Log Request Reversal from Agentportal (deny)
Given I verify the balance of the sender reseller DIST1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter DIST1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I enter WEBADMIN_PASSWORD in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 5 seconds
And User clicks on the button AgentportalMainAgentsButton
And User clicks on the button AgentportalCreditTransferButton
And I enter SUBDIST2-1_ID in the AgentportalCreditTransferAgentIdfield field
And I enter SUBDSIT2-1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
Then I verify the popup text AgentportalCreditTransferReceipt with the user text AGENTPORTAL_VALID_AMOUNT_RECEIPT on the page
Then I verify the balance of the sender reseller DIST1_ID after the transaction of amount R2R_AMOUNT
And User clicks on the button AgenportalReceiptOKButton
Then I get the ersReference of last Transaction from database
And User clicks on the button AgentportalTransactionLink
And User clicks on the button AgenPortalSearchTransactionSubmitButton
And System sleeps for 5 seconds
And I select REQUEST_REVERSAL_OPTION from dropdown AgenPortalSearchTransactionRequestReversalDropdown
And User clicks on the button AgenPortalSearchTransactionRequestReversalGoLink
And System sleeps for 5 seconds
And I enter AGENTPORTAL_COMMENT_BOX_VALUE_REQUEST_REVERSAL in the AgenPortalSearchTransactionRequestReversalCommentField field
And User clicks on the button AgenPortalSearchTransactionRequestReversalCommentPopup
And System sleeps for 6000 seconds
Then I verify the popup text AgenPortalResponsePopup with the user text AGENTPORTAL_VALID_REQUEST_REVERSAL on the page
Given I browse to the WebAdmin page
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 2000 seconds
And User clicks on the button WebAdminMainMenuSupport
And System sleeps for 2000 seconds
And User clicks on the button WebAdminMainMenuSupportSearchTransactions
And System sleeps for 5000 seconds
Then I switch to frame
And System sleeps for 1000 seconds
And User clicks on the button WebAdminSubMenuByReference
And I enter new calculated ERS reference for requestReversal in the text field WebAdminSubMenuByReferenceField SQL_LIMIT_RANGE_1
And User clicks on the button WebAdminSubMenuByReferenceSearchButton
And System sleeps for 3000 seconds
And User clicks on the button WebAdminDenyTransactionButton
And System sleeps for 5000 seconds
And I enter ROOT_USER_PASSWORD in the WebAdminReqRevPassword field
And User clicks on the button WebAdminReqRevSubmitButton
And System sleeps for 5000 seconds
Then I verify the popup text WebAdminTransSucceededPopup with the user text REQUEST_REVERSAL_DENY_TEXT on the page
Then User verify the balance of the reseller DIST1_ID incase of approve requestreversal after the transaction of amount ZERO_AMOUNT