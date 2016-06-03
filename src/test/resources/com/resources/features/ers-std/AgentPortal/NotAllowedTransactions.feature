Feature:  Not allowed to transfer/topup from child to parent, self transfer 

@NotAllowedTrans-AgentPortal
Scenario: R2R transaction not allowed from reseller to sub-distributor
Given Log R2R transaction not allowed from reseller to sub-distributor
Given I verify the balance of the sender reseller RESELLER-1-1-1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter RESELLER-1-1-1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I enter WEBADMIN_PASSWORD in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 5 seconds
And User clicks on the button AgentportalMainAgentsButton
And System sleeps for 5 seconds
And User clicks on the button AgentportalCreditTransferButton
And System sleeps for 10 seconds
And I enter SUBDIST1-1_ID in the AgentportalCreditTransferAgentIdfield field
And System sleeps for 10 seconds
And I input SUBDIST1-1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And System sleeps for 10 seconds
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And System sleeps for 5 seconds
And User clicks on the button AgentportalCreditTransferSubmit
And System sleeps for 5 seconds
Then I verify the popup text AgenPortalResponsePopup with the user text AGENTPORTAL_INVALID_HIERARCHY on the page
Then I verify the balance of the sender reseller RESELLER-1-1-1_ID after the transaction of amount ZERO_AMOUNT

@NotAllowedTrans-AgentPortal
Scenario: R2R transaction not allowed from sub-distributor to distributor
Given Log R2R transaction not allowed from sub-distributor to distributor
Given I verify the balance of the sender reseller SUBDIST1-1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter SUBDIST1-1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I enter WEBADMIN_PASSWORD in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 5 seconds
And User clicks on the button AgentportalMainAgentsButton
And User clicks on the button AgentportalCreditTransferButton
And I enter DIST1_ID in the AgentportalCreditTransferAgentIdfield field
And I input DIST1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
Then I verify the popup text AgenPortalResponsePopup with the user text AGENTPORTAL_INVALID_HIERARCHY on the page
Then I verify the balance of the sender reseller SUBDIST1-1_ID after the transaction of amount ZERO_AMOUNT

@NotAllowedTrans-AgentPortal
Scenario: R2R transaction not allowed from distributor to operator
Given Log R2R transaction not allowed from distributor to operator
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
And I enter OPERATOR_ID in the AgentportalCreditTransferAgentIdfield field
And I input OPERATOR_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
Then I verify the popup text AgenPortalResponsePopup with the user text AGENTPORTAL_INVALID_HIERARCHY on the page
Then I verify the balance of the sender reseller DIST1_ID after the transaction of amount ZERO_AMOUNT

@NotAllowedTrans-AgentPortal
Scenario: Reseller webuser cannot perform R2S transaction
Given Log Reseller webuser cannot perform R2S transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter OPERATOR_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I enter WEBADMIN_PASSWORD in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 5 seconds
Then I should not see AGENTPORTAL_SUBSCRIBER_MENU on the page

@NotAllowedTrans-AgentPortal
Scenario: Reseller webuser cannot perform R2R transaction
Given Log Reseller webuser cannot perform R2R transaction
Given I verify the balance of the sender reseller OPERATOR before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter DIST1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I enter WEBADMIN_PASSWORD in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 5 seconds
Then I should not see AGENTPORTAL_CREDIT_TRANSFER_MENU on the page

@NotAllowedTrans-AgentPortal
Scenario: Decimal amount is not allowed in R2R transfer in Agent Portal
Given Log Decimal amount is not allowed in R2R transfer in Agent Portal
Given I verify the balance of the sender reseller OPERATOR_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter OPERATOR_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I enter WEBADMIN_PASSWORD in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 5 seconds
And User clicks on the button AgentportalMainAgentsButton
And User clicks on the button AgentportalCreditTransferButton
And I enter DIST1_ID in the AgentportalCreditTransferAgentIdfield field
And I input DIST1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And I enter AMOUNT_DECIMAL in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
Then I verify the popup text AgentportalInvalidCreditTransferReceipt with the user text AGENTPORTAL_INVALID_AMOUNT on the page
Then I verify the balance of the sender reseller OPERATOR_ID after the transaction of amount ZERO_AMOUNT

@NotAllowedTrans-AgentPortal
Scenario: Decimal amount is not allowed in R2S topup in Agent Portal
Given Log Decimal amount is not allowed in R2S topup in Agent Portal
Given I verify the balance of the sender reseller SUBDIST1-1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter SUBDIST1-1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I enter WEBADMIN_PASSWORD in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 5 seconds
And User clicks on the button AgentportalMainSubscribersButton
And I input SUBSCRIBER_1 in the AgentportalTopupSubscriberIdfield field with IS_PREFIX_NOTREQUIRED
And I enter AMOUNT_DECIMAL in the AgentportalTopupSubscriberAmountfield field
And User clicks on the button AgentportalTopupSubmitButton
Then I verify the popup text AgentportalInvalidCreditTransferReceipt with the user text AGENTPORTAL_INVALID_AMOUNT on the page
Then I verify the balance of the sender reseller SUBDIST1-1_ID after the transaction of amount ZERO_AMOUNT

@NotAllowedTrans-AgentPortal-1
Scenario: + is not allowed in the Subscriber MSISDN on AgentPortal
Given Log + is not allowed in the Subscriber MSISDN on AgentPortal
Given I verify the balance of the sender reseller SUBDIST1-1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter SUBDIST1-1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I enter WEBADMIN_PASSWORD in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 5 seconds
And User clicks on the button AgentportalMainSubscribersButton
And I input SUBSCRIBER_1 in the AgentportalTopupSubscriberIdfield field with IS_PREFIX_REQUIRED
And I enter R2S_AMOUNT in the AgentportalTopupSubscriberAmountfield field
And System sleeps for 5000 seconds
And User clicks on the button AgentportalTopupSubmitButton
Then I verify the popup text AgentportalInvalidCreditTransferReceipt with the user text AGENTPORTAL_INVALID_PHONE on the page
Then I verify the balance of the sender reseller SUBDIST1-1_ID after the transaction of amount ZERO_AMOUNT

@NotAllowedTrans-AgentPortal-1
Scenario: + is not allowed in the Reseller MSISDN on AgentPortal
Given Log + is not allowed in the Reseller MSISDN on AgentPortal
Given I verify the balance of the sender reseller OPERATOR_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter OPERATOR_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I enter WEBADMIN_PASSWORD in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 5 seconds
And User clicks on the button AgentportalMainAgentsButton
And User clicks on the button AgentportalCreditTransferButton
And I enter DIST1_ID in the AgentportalCreditTransferAgentIdfield field
And I input DIST1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_REQUIRED
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And System sleeps for 5000 seconds
And User clicks on the button AgentportalCreditTransferSubmit
Then I verify the popup text AgentportalInvalidCreditTransferReceipt with the user text AGENTPORTAL_INVALID_PHONE on the page
Then I verify the balance of the sender reseller OPERATOR_ID after the transaction of amount ZERO_AMOUNT

###################################################################################################################################################################
#These test cases are specifically for CI-MTN customer as the hierarchy is totally different in CI-MTN, so we are going to write seprate test cases for this customer

@NotAllowedTrans-AgentPortal-ci-mtn
Scenario: Transfer from Strategic Dealer to Operator using String base
Given Log Transfer from Strategic Dealer to Operator using String base
Given I verify the balance of the sender reseller SDEAL1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter SDEAL1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I confirm whether to set the old password or password for Reseller SDEAL1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalLoginButton
And System sleeps for 5000 seconds
And I check change password for Reseller SDEAL1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalMainAgentsButton
And System sleeps for 5 seconds
And User clicks on the button AgentportalCreditTransferButton
And System sleeps for 10 seconds
And I enter OPERATOR_ID in the AgentportalCreditTransferAgentIdfield field
And System sleeps for 10 seconds
And I input OPERATOR_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And System sleeps for 10 seconds
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And System sleeps for 5000 seconds
And User clicks on the button AgentportalCreditTransferSubmit
And System sleeps for 2000 seconds
Then I verify the popup text AgenPortalResponsePopup with the user text AGENTPORTAL_INVALID_HIERARCHY on the page
Then I verify the balance of the sender reseller SDEAL1_ID after the transaction of amount ZERO_AMOUNT

@NotAllowedTrans-AgentPortal-ci-mtn
Scenario: Transfer from  Dealer to Strategic Dealer using String base
Given Log Transfer from  Dealer to Strategic Dealer using String base
Given I verify the balance of the sender reseller DEAL1-1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter DEAL1-1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I confirm whether to set the old password or password for Reseller DEAL1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalLoginButton
And System sleeps for 5000 seconds
And I check change password for Reseller DEAL1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalMainAgentsButton
And User clicks on the button AgentportalCreditTransferButton
And I enter SDEAL1_ID in the AgentportalCreditTransferAgentIdfield field
And I input SDEAL1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
And System sleeps for 2000 seconds
Then I verify the popup text AgenPortalResponsePopup with the user text AGENTPORTAL_INVALID_HIERARCHY on the page
Then I verify the balance of the sender reseller DEAL1-1_ID after the transaction of amount ZERO_AMOUNT

@NotAllowedTrans-AgentPortal-ci-mtn
Scenario: Transfer from POS to Strategic Dealer using String base
Given Log Transfer from POS to Strategic Dealer using String base
Given I verify the balance of the sender reseller POS-1-1-1-1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter POS-1-1-1-1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I confirm whether to set the old password or password for Reseller POS-1-1-1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalLoginButton
And System sleeps for 5000 seconds
And I check change password for Reseller POS-1-1-1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalMainAgentsButton
And User clicks on the button AgentportalCreditTransferButton
And I enter DEAL1-1_ID in the AgentportalCreditTransferAgentIdfield field
And I input DEAL1-1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
And System sleeps for 2000 seconds
Then I verify the popup text AgenPortalResponsePopup with the user text AGENTPORTAL_INVALID_AMOUNT_MESSAGE on the page
Then I verify the balance of the sender reseller POS-1-1-1-1_ID after the transaction of amount ZERO_AMOUNT

@NotAllowedTrans-AgentPortal-ci-mtn
Scenario: Transfer from POS to Delegate Agent using String base
Given Log Transfer from POS to Delegate Agent using String base
Given I verify the balance of the sender reseller POS-1-1-1-1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter POS-1-1-1-1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I confirm whether to set the old password or password for Reseller POS-1-1-1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalLoginButton
And System sleeps for 5000 seconds
And I check change password for Reseller POS-1-1-1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalMainAgentsButton
And User clicks on the button AgentportalCreditTransferButton
And I enter DAGENT-1-1-1_ID in the AgentportalCreditTransferAgentIdfield field
And I input DAGENT-1-1-1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
And System sleeps for 2000 seconds
Then I verify the popup text AgenPortalResponsePopup with the user text AGENTPORTAL_INVALID_AMOUNT_MESSAGE on the page
Then I verify the balance of the sender reseller POS-1-1-1-1_ID after the transaction of amount ZERO_AMOUNT

@NotAllowedTrans-AgentPortal-ci-mtn
Scenario: Transfer from POS to Strategic Agent using String base
Given Log Transfer from POS to Strategic Agent using String base
Given I verify the balance of the sender reseller POS-1-1-1-1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter POS-1-1-1-1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I confirm whether to set the old password or password for Reseller POS-1-1-1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalLoginButton
And System sleeps for 5000 seconds
And I check change password for Reseller POS-1-1-1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalMainAgentsButton
And User clicks on the button AgentportalCreditTransferButton
And I enter SAGENT1-1_ID in the AgentportalCreditTransferAgentIdfield field
And I input SAGENT1-1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
And System sleeps for 2000 seconds
Then I verify the popup text AgenPortalResponsePopup with the user text AGENTPORTAL_INVALID_AMOUNT_MESSAGE on the page
Then I verify the balance of the sender reseller POS-1-1-1-1_ID after the transaction of amount ZERO_AMOUNT

@NotAllowedTrans-AgentPortal-ci-mtn
Scenario: Transfer from Strategic Agent to Strategic Dealer using String base
Given Log Transfer from Strategic Agent to Strategic Dealer using String base
Given I verify the balance of the sender reseller SAGENT1-1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter SAGENT1-1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I confirm whether to set the old password or password for Reseller SAGENT1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalLoginButton
And System sleeps for 5000 seconds
And I check change password for Reseller SAGENT1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalMainAgentsButton
And User clicks on the button AgentportalCreditTransferButton
And I enter SDEAL1_ID in the AgentportalCreditTransferAgentIdfield field
And I input SDEAL1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
And System sleeps for 2000 seconds
Then I verify the popup text AgenPortalResponsePopup with the user text AGENTPORTAL_INVALID_HIERARCHY on the page
Then I verify the balance of the sender reseller SAGENT1-1_ID after the transaction of amount ZERO_AMOUNT

@NotAllowedTrans-AgentPortal-ci-mtn
Scenario: Transfer from Strategic Delegate to Strategic Dealer
Given Log Transfer from Strategic Delegate to Strategic Dealer
Given I verify the balance of the sender reseller SDEL1-1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter SDEL1-1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I confirm whether to set the old password or password for Reseller SDEL1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalLoginButton
And System sleeps for 5000 seconds
And I check change password for Reseller SDEL1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalMainAgentsButton
And User clicks on the button AgentportalCreditTransferButton
And I enter SDEAL1_ID in the AgentportalCreditTransferAgentIdfield field
And I input SDEAL1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
And System sleeps for 2000 seconds
Then I verify the popup text AgenPortalResponsePopup with the user text AGENTPORTAL_INVALID_HIERARCHY on the page
Then I verify the balance of the sender reseller SDEL1-1_ID after the transaction of amount ZERO_AMOUNT

@NotAllowedTrans-AgentPortal-ci-mtn
Scenario: Transfer from Dealer Delegate to Dealer
Given Log Transfer from Dealer Delegate to Dealer
Given I verify the balance of the sender reseller DDEL-1-1-1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter DDEL-1-1-1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I confirm whether to set the old password or password for Reseller DDEL-1-1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalLoginButton
And System sleeps for 5000 seconds
And I check change password for Reseller DDEL-1-1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalMainAgentsButton
And User clicks on the button AgentportalCreditTransferButton
And I enter DEAL1-1_ID in the AgentportalCreditTransferAgentIdfield field
And I input DEAL1-1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
And System sleeps for 2000 seconds
Then I verify the popup text AgenPortalResponsePopup with the user text AGENTPORTAL_INVALID_HIERARCHY on the page
Then I verify the balance of the sender reseller DDEL-1-1-1_ID after the transaction of amount ZERO_AMOUNT

@NotAllowedTrans-AgentPortal-ci-mtn
Scenario: Transfer from POS to DEALER using String base
Given Log Transfer from POS to DEALER using String base
Given I verify the balance of the sender reseller POS-1-1-1-1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter POS-1-1-1-1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I confirm whether to set the old password or password for Reseller POS-1-1-1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalLoginButton
And System sleeps for 5000 seconds
And I check change password for Reseller POS-1-1-1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalMainAgentsButton
And User clicks on the button AgentportalCreditTransferButton
And I enter DEAL1-1_ID in the AgentportalCreditTransferAgentIdfield field
And I input DEAL1-1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
And System sleeps for 2000 seconds
Then I verify the popup text AgenPortalResponsePopup with the user text AGENTPORTAL_INVALID_AMOUNT_MESSAGE on the page
Then I verify the balance of the sender reseller POS-1-1-1-1_ID after the transaction of amount ZERO_AMOUNT
