Feature: R2S Allowed Transactions

@R2S-AgentPortal
Scenario: R2S transaction allowed from sub-ditributor to Subscriber
Given Log R2S transaction allowed from sub-ditributor to reseller
Given I verify the balance of the sender reseller SUBDIST1-1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter SUBDIST1-1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I enter WEBADMIN_PASSWORD in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 5000 seconds
And User clicks on the button AgentportalMainSubscribersButton
And I input SUBSCRIBER_1 in the AgentportalTopupSubscriberIdfield field with IS_PREFIX_NOTREQUIRED
And I enter R2S_AMOUNT in the AgentportalTopupSubscriberAmountfield field
And User clicks on the button AgentportalTopupSubmitButton
Then I verify the popup text AgentportalCreditTransferReceipt with the user text AGENTPORTAL_VALID_TOPUP_AMOUNT_RECEIPT on the page
Then I verify the balance of the sender reseller SUBDIST1-1_ID after the transaction of amount R2S_AMOUNT

@R2S-AgentPortal
Scenario: R2S transaction allowed from reseller to Subscriber
Given Log R2S transaction allowed from reseller to subscriber
Given I verify the balance of the sender reseller RESELLER-1-1-1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter RESELLER-1-1-1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I enter WEBADMIN_PASSWORD in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 5 seconds
And User clicks on the button AgentportalMainSubscribersButton
And I input SUBSCRIBER_1 in the AgentportalTopupSubscriberIdfield field with IS_PREFIX_NOTREQUIRED
And I enter R2S_AMOUNT in the AgentportalTopupSubscriberAmountfield field
And System sleeps for 5000 seconds
And User clicks on the button AgentportalTopupSubmitButton
Then I verify the popup text AgentportalCreditTransferReceipt with the user text AGENTPORTAL_VALID_TOPUP_AMOUNT_RECEIPT on the page
Then I verify the balance of the sender reseller RESELLER-1-1-1_ID after the transaction of amount R2S_AMOUNT

###################################################################################################################################################################
#These test cases are specifically for CI-MTN customer as the hierarchy is totally different in CI-MTN, so we are going to write seprate test cases for this customer

@R2S-AgentPortal-ci-mtn
Scenario: Perform R2S (Reseller2Subcriber) from Strategic Dealer to Subscriber
Given Log Perform R2S (Reseller2Subcriber) from Strategic Dealer to Subscriber
Given I verify the balance of the sender reseller SDEAL1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter SDEAL1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I confirm whether to set the old password or password for Reseller SDEAL1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalLoginButton
And System sleeps for 5000 seconds
And I check change password for Reseller SDEAL1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalMainSubscribersButton
And I input SUBSCRIBER_1 in the AgentportalTopupSubscriberIdfield field with IS_PREFIX_NOTREQUIRED
And I enter R2S_AMOUNT in the AgentportalTopupSubscriberAmountfield field
And System sleeps for 2000 seconds
And User clicks on the button AgentportalTopupSubmitButton
And System sleeps for 2000 seconds
Then I verify the popup text AgentportalCreditTransferReceipt with the user text AGENTPORTAL_VALID_TOPUP_AMOUNT_RECEIPT on the page
Then I verify the balance of the sender reseller SDEAL1_ID after the transaction of amount R2S_AMOUNT

@R2S-AgentPortal-ci-mtn
Scenario: Perform R2S (Reseller2Subcriber) from Strategic Agent to Subscriber
Given Log Perform R2S (Reseller2Subcriber) from Strategic Agent to Subscriber
Given I verify the balance of the sender reseller SAGENT1-1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter SAGENT1-1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I confirm whether to set the old password or password for Reseller SAGENT1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalLoginButton
And System sleeps for 5000 seconds
And I check change password for Reseller SAGENT1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalMainSubscribersButton
And I input SUBSCRIBER_1 in the AgentportalTopupSubscriberIdfield field with IS_PREFIX_NOTREQUIRED
And I enter R2S_AMOUNT in the AgentportalTopupSubscriberAmountfield field
And System sleeps for 5000 seconds
And User clicks on the button AgentportalTopupSubmitButton
And System sleeps for 2000 seconds
Then I verify the popup text AgentportalCreditTransferReceipt with the user text AGENTPORTAL_VALID_TOPUP_AMOUNT_RECEIPT on the page
Then I verify the balance of the sender reseller SAGENT1-1_ID after the transaction of amount R2S_AMOUNT

@R2S-AgentPortal-ci-mtn
Scenario: Perform R2S (Reseller2Subcriber) from Dealer to Subscriber
Given Log Perform R2S (Reseller2Subcriber) from Dealer to Subscriber
Given I verify the balance of the sender reseller DEAL1-1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter DEAL1-1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I confirm whether to set the old password or password for Reseller DEAL1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalLoginButton
And System sleeps for 5000 seconds
And I check change password for Reseller DEAL1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalMainSubscribersButton
And I input SUBSCRIBER_1 in the AgentportalTopupSubscriberIdfield field with IS_PREFIX_NOTREQUIRED
And I enter R2S_AMOUNT in the AgentportalTopupSubscriberAmountfield field
And System sleeps for 5000 seconds
And User clicks on the button AgentportalTopupSubmitButton
And System sleeps for 2000 seconds
Then I verify the popup text AgentportalCreditTransferReceipt with the user text AGENTPORTAL_VALID_TOPUP_AMOUNT_RECEIPT on the page
Then I verify the balance of the sender reseller DEAL1-1_ID after the transaction of amount R2S_AMOUNT

@R2S-AgentPortal-ci-mtn
Scenario: Perform R2S (Reseller2Subcriber) from POS to Subscriber
Given Log Perform R2S (Reseller2Subcriber) from POS to Subscriber
Given I verify the balance of the sender reseller POS-1-1-1-1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter POS-1-1-1-1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I confirm whether to set the old password or password for Reseller POS-1-1-1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalLoginButton
And System sleeps for 5000 seconds
And I check change password for Reseller POS-1-1-1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalMainSubscribersButton
And I input SUBSCRIBER_1 in the AgentportalTopupSubscriberIdfield field with IS_PREFIX_NOTREQUIRED
And I enter R2S_AMOUNT in the AgentportalTopupSubscriberAmountfield field
And System sleeps for 5000 seconds
And User clicks on the button AgentportalTopupSubmitButton
And System sleeps for 2000 seconds
Then I verify the popup text AgentportalCreditTransferReceipt with the user text AGENTPORTAL_VALID_TOPUP_AMOUNT_RECEIPT on the page
Then I verify the balance of the sender reseller POS-1-1-1-1_ID after the transaction of amount R2S_AMOUNT

@R2S-AgentPortal-ci-mtn
Scenario: Perform R2S (Reseller2Subcriber) from Dealer Agent to Subscriber
Given Log Perform R2S (Reseller2Subcriber) from Dealer Agent to Subscriber
Given I verify the balance of the sender reseller DAGENT-1-1-1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter DAGENT-1-1-1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I confirm whether to set the old password or password for Reseller DAGENT-1-1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalLoginButton
And System sleeps for 5000 seconds
And I check change password for Reseller DAGENT-1-1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalMainSubscribersButton
And I input SUBSCRIBER_1 in the AgentportalTopupSubscriberIdfield field with IS_PREFIX_NOTREQUIRED
And I enter R2S_AMOUNT in the AgentportalTopupSubscriberAmountfield field
And System sleeps for 5000 seconds
And User clicks on the button AgentportalTopupSubmitButton
And System sleeps for 2000 seconds
Then I verify the popup text AgentportalCreditTransferReceipt with the user text AGENTPORTAL_VALID_TOPUP_AMOUNT_RECEIPT on the page
Then I verify the balance of the sender reseller DAGENT-1-1-1_ID after the transaction of amount R2S_AMOUNT

@R2S-AgentPortal-ci-mtn
Scenario: Perform R2S (Reseller2Subcriber) from Strategic Delegate to Subscriber
Given Log Perform R2S (Reseller2Subcriber) from Strategic Delegate to Subscriber
Given I verify the balance of the sender reseller SDEL1-1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter SDEL1-1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I confirm whether to set the old password or password for Reseller SDEL1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalLoginButton
And System sleeps for 5000 seconds
And I check change password for Reseller SDEL1-1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalMainSubscribersButton
And I input SUBSCRIBER_1 in the AgentportalTopupSubscriberIdfield field with IS_PREFIX_NOTREQUIRED
And I enter R2S_AMOUNT in the AgentportalTopupSubscriberAmountfield field
And System sleeps for 5000 seconds
And User clicks on the button AgentportalTopupSubmitButton
And System sleeps for 2000 seconds
Then I verify the popup text AgentportalCreditTransferReceipt with the user text AGENTPORTAL_VALID_TOPUP_AMOUNT_RECEIPT on the page
Then I verify the balance of the sender reseller SDEL1-1_ID after the transaction of amount R2S_AMOUNT

@R2S-AgentPortal-ci-mtn
Scenario: Perform R2S (Reseller2Subcriber) from Strategic Dealer to Subscriber in National Format
Given Log Perform R2S (Reseller2Subcriber) from Strategic Dealer to Subscriber
Given I verify the balance of the sender reseller SDEAL1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter SDEAL1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I confirm whether to set the old password or password for Reseller SDEAL1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalLoginButton
And System sleeps for 5000 seconds
And I check change password for Reseller SDEAL1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalMainSubscribersButton
And I input SUBSCRIBER_NATIONAL_1 in the AgentportalTopupSubscriberIdfield field with IS_PREFIX_NATIONAL
And I enter R2S_AMOUNT in the AgentportalTopupSubscriberAmountfield field
And System sleeps for 5000 seconds
And User clicks on the button AgentportalTopupSubmitButton
And System sleeps for 2000 seconds
Then I verify the popup text AgentportalCreditTransferReceipt with the user text AGENTPORTAL_VALID_TOPUP_AMOUNT_RECEIPT on the page
Then I verify the balance of the sender reseller SDEAL1_ID after the transaction of amount R2S_AMOUNT