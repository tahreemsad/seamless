Feature:R2R hierarchy

@R2R-AgentPortal1
Scenario:R2R transaction allowed from operator to distributor
Given Log R2R transaction allowed from operator to distributor
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
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
Then I verify the popup text AgentportalCreditTransferReceipt with the user text AGENTPORTAL_VALID_AMOUNT_RECEIPT on the page
Then I verify the balance of the sender reseller OPERATOR_ID after the transaction of amount R2R_AMOUNT

@R2R-AgentPortal
Scenario:R2R transaction allowed from distributor to distributor
Given Log R2R transaction allowed from distributor to distributor
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
And I enter DIST2_ID in the AgentportalCreditTransferAgentIdfield field
And I input DIST2_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
Then I verify the popup text AgentportalCreditTransferReceipt with the user text AGENTPORTAL_VALID_AMOUNT_RECEIPT on the page 
Then I verify the balance of the sender reseller DIST1_ID after the transaction of amount R2R_AMOUNT

@R2R-AgentPortal
Scenario:R2R transaction allowed from distributor to sub-distributor
Given Log R2R transaction allowed from distributor to sub-distributor
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
And I enter SUBDIST1-1_ID in the AgentportalCreditTransferAgentIdfield field
And I input SUBDIST1-1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
Then I verify the popup text AgentportalCreditTransferReceipt with the user text AGENTPORTAL_VALID_AMOUNT_RECEIPT on the page
Then I verify the balance of the sender reseller DIST1_ID after the transaction of amount R2R_AMOUNT

@R2R-AgentPortal
Scenario:R2R transaction allowed from sub-distributor to sub-distributor
Given Log R2R transaction allowed from sub-distributor to sub-distributor
Given I verify the balance of the sender reseller SUBDIST1-2_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter SUBDIST1-2_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I enter WEBADMIN_PASSWORD in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 5 seconds
And User clicks on the button AgentportalMainAgentsButton
And User clicks on the button AgentportalCreditTransferButton
And I enter SUBDIST1-1_ID in the AgentportalCreditTransferAgentIdfield field
And I input SUBDIST1-1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
Then I verify the popup text AgentportalCreditTransferReceipt with the user text AGENTPORTAL_VALID_AMOUNT_RECEIPT on the page
Then I verify the balance of the sender reseller SUBDIST1-2_ID after the transaction of amount R2R_AMOUNT

@R2R-AgentPortal
Scenario:R2R transaction allowed from sub-distributor to Reseller
Given Log R2R transaction allowed from sub-distributor to Reseller
Given I verify the balance of the sender reseller SUBDIST1-2_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter SUBDIST1-2_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I enter WEBADMIN_PASSWORD in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 5 seconds
And User clicks on the button AgentportalMainAgentsButton
And User clicks on the button AgentportalCreditTransferButton
And I enter RESELLER-1-1-1_ID in the AgentportalCreditTransferAgentIdfield field
And I input RESELLER-1-1-1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
Then I verify the popup text AgentportalCreditTransferReceipt with the user text AGENTPORTAL_VALID_AMOUNT_RECEIPT on the page
Then I verify the balance of the sender reseller SUBDIST1-2_ID after the transaction of amount R2R_AMOUNT

@R2R-AgentPortal
Scenario:R2R transaction allowed from Reseller to Reseller
Given Log R2R transaction allowed from Reseller to Reseller
Given I verify the balance of the sender reseller RESELLER-1-1-1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter RESELLER-1-1-1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I enter WEBADMIN_PASSWORD in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 5 seconds
And User clicks on the button AgentportalMainAgentsButton
And User clicks on the button AgentportalCreditTransferButton
And I enter RESELLER-1-1-2_ID in the AgentportalCreditTransferAgentIdfield field
And I input RESELLER-1-1-2_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
Then I verify the popup text AgentportalCreditTransferReceipt with the user text AGENTPORTAL_VALID_AMOUNT_RECEIPT on the page
Then I verify the balance of the sender reseller RESELLER-1-1-1_ID after the transaction of amount R2R_AMOUNT


###################################################################################################################################################################
#These test cases are specifically for CI-MTN customer as the hierarchy is totally different in CI-MTN, so we are going to write seprate test cases for this customer

@R2R-AgentPortal-ci-mtn
Scenario: R2R transaction allowed from operator to distributor
And I clear hashmap
Given Log R2R transaction allowed from operator to distributor
Given I verify the balance of the sender reseller OPERATOR_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter OPERATOR_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I confirm whether to set the old password or password for Reseller OPERATOR_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalLoginButton
And System sleeps for 5000 seconds
And I check change password for Reseller OPERATOR_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalMainAgentsButton
And User clicks on the button AgentportalCreditTransferButton
And I enter SDEAL1_ID in the AgentportalCreditTransferAgentIdfield field
And I input SDEAL1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
Then I verify the popup text AgentportalCreditTransferReceipt with the user text AGENTPORTAL_VALID_AMOUNT_RECEIPT on the page
Then I verify the balance of the sender reseller OPERATOR_ID after the transaction of amount R2R_AMOUNT

@R2R-AgentPortal-ci-mtn
Scenario: Transfer from Strategic Dealer to Dealer using String base
And I clear hashmap
Given Log Transfer from Strategic Dealer to Dealer using String base
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
And User clicks on the button AgentportalCreditTransferButton
And I input DEAL1-1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And I enter DEAL1-1_ID in the AgentportalCreditTransferAgentIdfield field
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
Then I verify the popup text AgentportalCreditTransferReceipt with the user text AGENTPORTAL_VALID_AMOUNT_RECEIPT on the page
Then I verify the balance of the sender reseller SDEAL1_ID after the transaction of amount R2R_AMOUNT

@R2R-AgentPortal-ci-mtn
Scenario: Transfer from Strategic Dealer to POS using String base
And I clear hashmap
Given Log Transfer from Strategic Dealer to POS using String base
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
And User clicks on the button AgentportalCreditTransferButton
And I input POS-1-1-1-1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And I enter POS-1-1-1-1_ID in the AgentportalCreditTransferAgentIdfield field
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
Then I verify the popup text AgentportalCreditTransferReceipt with the user text AGENTPORTAL_VALID_AMOUNT_RECEIPT on the page
Then I verify the balance of the sender reseller SDEAL1_ID after the transaction of amount R2R_AMOUNT

@R2R-AgentPortal-ci-mtn
Scenario: Transfer from Strategic Agent to POS using String base
And I clear hashmap
Given Log Transfer from Strategic Agent to POS using String base
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
And I input POS-1-1-1-1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And I enter POS-1-1-1-1_ID in the AgentportalCreditTransferAgentIdfield field
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
Then I verify the popup text AgentportalCreditTransferReceipt with the user text AGENTPORTAL_VALID_AMOUNT_RECEIPT on the page
Then I verify the balance of the sender reseller SAGENT1-1_ID after the transaction of amount R2R_AMOUNT

@R2R-AgentPortal-ci-mtn
Scenario: Transfer from Dealer Agent to POS using String base
And I clear hashmap
Given Log Transfer from Dealer Agent to POS using String base
Given I verify the balance of the sender reseller DAGENT-1-1-1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter DAGENT-1-1-1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I confirm whether to set the old password or password for Reseller DAGENT-1-1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalLoginButton
And System sleeps for 5000 seconds
And I check change password for Reseller DAGENT-1-1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalMainAgentsButton
And User clicks on the button AgentportalCreditTransferButton
And I input POS-1-1-1-1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And I enter POS-1-1-1-1_ID in the AgentportalCreditTransferAgentIdfield field
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
Then I verify the popup text AgentportalCreditTransferReceipt with the user text AGENTPORTAL_VALID_AMOUNT_RECEIPT on the page
Then I verify the balance of the sender reseller DAGENT-1-1-1_ID after the transaction of amount R2R_AMOUNT

@R2R-AgentPortal-ci-mtn
Scenario: Transfer from Strategic Dealer to Strategic Agent using String base
And I clear hashmap
Given Log Transfer from Strategic Dealer to Strategic Agent using String base
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
And User clicks on the button AgentportalCreditTransferButton
And I input SAGENT1-1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And I enter SAGENT1-1_ID in the AgentportalCreditTransferAgentIdfield field
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
Then I verify the popup text AgentportalCreditTransferReceipt with the user text AGENTPORTAL_VALID_AMOUNT_RECEIPT on the page
Then I verify the balance of the sender reseller SDEAL1_ID after the transaction of amount R2R_AMOUNT

@R2R-AgentPortal-ci-mtn
Scenario: Transfer from Dealer to Dealer Agent using String base
And I clear hashmap
Given Log Transfer from Dealer to Dealer Agent using String base
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
And I input DAGENT-1-1-1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And I enter DAGENT-1-1-1_ID in the AgentportalCreditTransferAgentIdfield field
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
Then I verify the popup text AgentportalCreditTransferReceipt with the user text AGENTPORTAL_VALID_AMOUNT_RECEIPT on the page
Then I verify the balance of the sender reseller DEAL1-1_ID after the transaction of amount R2R_AMOUNT

@R2R-AgentPortal-ci-mtn
Scenario: Transfer from Dealer to POS using String base
And I clear hashmap
Given Log Transfer from Dealer to POS using String base
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
And I input POS-1-1-1-1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And I enter POS-1-1-1-1_ID in the AgentportalCreditTransferAgentIdfield field
And I enter R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
Then I verify the popup text AgentportalCreditTransferReceipt with the user text AGENTPORTAL_VALID_AMOUNT_RECEIPT on the page
Then I verify the balance of the sender reseller DEAL1-1_ID after the transaction of amount R2R_AMOUNT