Feature: Contracts Voilation

@ContractsVoilation-AgentPortal
Scenario: Contracts voilation for R2R, user try to input value > contract value
Given Log Contracts voilation for R2R
Given I verify the balance of the sender reseller OPERATOR_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter OPERATOR_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I enter WEBADMIN_PASSWORD in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 50 seconds
And User clicks on the button AgentportalMainAgentsButton
And User clicks on the button AgentportalCreditTransferButton
And I enter DIST1_ID in the AgentportalCreditTransferAgentIdfield field
And I input DIST1_MSISDN in the AgentportalCreditTransferAgentMSISDNfield field with IS_PREFIX_NOTREQUIRED
And I enter INVALID_R2R_AMOUNT in the AgentportalCreditTransferAmonutfield field
And User clicks on the button AgentportalCreditTransferSubmit
Then I verify the popup text AgentportalInvalidCreditTransferReceipt with the user text AGENTPORTAL_INVALID_AMOUNT_MESSAGE on the page
Then I verify the balance of the sender reseller OPERATOR_ID after the transaction of amount ZERO_AMOUNT

@ContractsVoilation-AgentPortal
Scenario: Contracts voilation for R2S, user try to input 0
Given Log Contracts voilation for R2S
Given I verify the balance of the sender reseller SUBDIST1-1_ID before the transaction
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
#And I select English from dropdown AgentportalLoginpagedropdown
And I enter SUBDIST1-1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I enter WEBADMIN_PASSWORD in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 5 seconds
And User clicks on the button AgentportalMainSubscribersButton
And I input SUBSCRIBER_1 in the AgentportalTopupSubscriberIdfield field with IS_PREFIX_NOTREQUIRED
And I enter ZERO_AMOUNT in the AgentportalTopupSubscriberAmountfield field
And User clicks on the button AgentportalTopupSubmitButton
Then I verify the popup text AgentportalInvalidCreditTransferReceipt with the user text AGENTPORTAL_INVALID_AMOUNT_With_ZERO_BALANCE_MESSAGE on the page
Then I verify the balance of the sender reseller SUBDIST1-1_ID after the transaction of amount ZERO_AMOUNT
