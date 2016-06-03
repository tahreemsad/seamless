Feature: Perform R2S using Request Topup Service

@R2S-TopupService-Allowed
Scenario: Distributor to subscriber number with SUCCESS in response
When Reseller topups to subscriber having resellerId DISTRIBUTOR_ID resellerType RESELLERUSER userId POS_USER_ID password POS_USER_PASSWORD amount TOPUP_AMOUNT and receiverMSISDN is SUBSCRIBER_RECEIVER_MSSIDN and type is SUBSCRIBERMSISDN and in response SUCCESS_RESPONSE message is received 

@R2S-TopupService-Allowed
Scenario: Sub-Distributor to subscriber number with SUCCESS in response
When Reseller topups to subscriber having resellerId SUB_DISTRIBUTOR_ID resellerType RESELLERUSER userId POS_USER_ID password POS_USER_PASSWORD amount TOPUP_AMOUNT and receiverMSISDN is SUBSCRIBER_RECEIVER_MSSIDN and type is SUBSCRIBERMSISDN and in response SUCCESS_RESPONSE message is received 

@R2S-TopupService-Allowed
Scenario: Reseller to subscriber number with SUCCESS in response 
When Reseller topups to subscriber having resellerId RESELLER_ID resellerType RESELLERUSER userId POS_USER_ID password POS_USER_PASSWORD amount TOPUP_AMOUNT and receiverMSISDN is SUBSCRIBER_RECEIVER_MSSIDN and type is SUBSCRIBERMSISDN and in response SUCCESS_RESPONSE message is received

@R2S-TopupService-NotAllowed
Scenario: Operator to subscriber number with Invalid in response
When Reseller topups to subscriber having resellerId OPERATOR_ID resellerType RESELLERUSER userId POS_USER_ID password POS_USER_PASSWORD amount TOPUP_AMOUNT and receiverMSISDN is SUBSCRIBER_RECEIVER_MSSIDN and type is SUBSCRIBERMSISDN and in response INVALID_AMOUNT message is received

@R2S-TopupService-NotAllowed
Scenario: Topup to subscriber number with Invalid Reseller type in response
When Reseller topups to subscriber having resellerId DISTRIBUTOR_ID resellerType INVALID_RESELLERUSER userId POS_USER_ID password POS_USER_PASSWORD amount TOPUP_AMOUNT and receiverMSISDN is SUBSCRIBER_RECEIVER_MSSIDN and type is SUBSCRIBERMSISDN and in response INVALID_PRINCIPAL_TYPE message is received

@R2S-TopupService-NotAllowed
Scenario: Topup to subscriber number with INITIATOR_PRINCIPAL_NOT_FOUND in response
When Reseller topups to subscriber having resellerId INVALID_DISTRIBUTOR_ID resellerType RESELLERUSER userId POS_USER_ID password POS_USER_PASSWORD amount TOPUP_AMOUNT and receiverMSISDN is SUBSCRIBER_RECEIVER_MSSIDN and type is SUBSCRIBERMSISDN and in response INVALID_RESELLER message is received

@R2S-TopupService-NotAllowed
Scenario: Topup to subscriber number with AUTHENTICATION_FAILED in response 
When Reseller topups to subscriber having resellerId DISTRIBUTOR_ID resellerType RESELLERUSER userId INVALID_POS_USER_ID password POS_USER_PASSWORD amount TOPUP_AMOUNT and receiverMSISDN is SUBSCRIBER_RECEIVER_MSSIDN and type is SUBSCRIBERMSISDN and in response INVALID_USERID message is received

@R2S-TopupService-NotAllowed
Scenario: Topup to subscriber number with INITIATOR_PRINCIPAL_NOT_FOUND in response
When Reseller topups to subscriber having resellerId DISTRIBUTOR_ID resellerType RESELLERUSER userId POS_USER_ID password INVALID_POS_PASSWORD amount TOPUP_AMOUNT and receiverMSISDN is SUBSCRIBER_RECEIVER_MSSIDN and type is SUBSCRIBERMSISDN and in response INVALID_PASSWORD message is received

@R2S-TopupService-NotAllowed
Scenario: Topup to subscriber number with REJECTED_AMOUNT with 0 Balance
When Reseller topups to subscriber having resellerId DISTRIBUTOR_ID resellerType RESELLERUSER userId POS_USER_ID password POS_USER_PASSWORD amount TRANSFER_AMOUNT_ZERO and receiverMSISDN is SUBSCRIBER_RECEIVER_MSSIDN and type is SUBSCRIBERMSISDN and in response INVALID_AMOUNT message is received

@R2S-TopupService-NotAllowed
Scenario: Topup to subscriber number with REJECTED_AMOUNT with Contracts Voilation
When Reseller topups to subscriber having resellerId DISTRIBUTOR_ID resellerType RESELLERUSER userId POS_USER_ID password POS_USER_PASSWORD amount INVALID_TOPUP_AMOUNT and receiverMSISDN is SUBSCRIBER_RECEIVER_MSSIDN and type is SUBSCRIBERMSISDN and in response INVALID_AMOUNT message is received