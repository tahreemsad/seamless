Feature: Perform R2R from Request Transfer Service

@R2R-TransferService-Allowed
Scenario: Transfer from Operator to Distributor
When Reseller transfers to reseller having resellerId OPERATOR_ID resellerType RESELLERUSER userId POS_USER_ID password POS_USER_PASSWORD amount TRANSFER_AMOUNT and receiverId is DISTRIBUTOR_ID and in response SUCCESS_RESPONSE message is received

@R2R-TransferService-Allowed
Scenario: Transfer from Distributor to Distributor
When Reseller transfers to reseller having resellerId DISTRIBUTOR_ID resellerType RESELLERUSER userId POS_USER_ID password POS_USER_PASSWORD amount TRANSFER_AMOUNT and receiverId is DISTRIBUTOR_ID2 and in response SUCCESS_RESPONSE message is received

@R2R-TransferService-Allowed
Scenario: Transfer from Distributor to Sub-Distributor
When Reseller transfers to reseller having resellerId DISTRIBUTOR_ID resellerType RESELLERUSER userId POS_USER_ID password POS_USER_PASSWORD amount TRANSFER_AMOUNT and receiverId is SUB_DISTRIBUTOR_ID and in response SUCCESS_RESPONSE message is received

@R2R-TransferService-Allowed
Scenario: Transfer from Sub-Distributor to Sub-Distributor
When Reseller transfers to reseller having resellerId SUB_DISTRIBUTOR_ID resellerType RESELLERUSER userId POS_USER_ID password POS_USER_PASSWORD amount TRANSFER_AMOUNT and receiverId is SUB_DISTRIBUTOR_ID2 and in response SUCCESS_RESPONSE message is received

@R2R-TransferService-Allowed
Scenario: Transfer from Sub-Distributor to Reseller
When Reseller transfers to reseller having resellerId SUB_DISTRIBUTOR_ID resellerType RESELLERUSER userId POS_USER_ID password POS_USER_PASSWORD amount TRANSFER_AMOUNT and receiverId is RESELLER_ID and in response SUCCESS_RESPONSE message is received

@R2R-TransferService-Allowed
Scenario: Transfer from Reseller to Reseller
When Reseller transfers to reseller having resellerId RESELLER_ID resellerType RESELLERUSER userId POS_USER_ID password POS_USER_PASSWORD amount TRANSFER_AMOUNT and receiverId is RESELLER_ID2 and in response SUCCESS_RESPONSE message is received

@R2R-TransferService-NotAllowed
Scenario: Transfer from Reseller to Sub-Distributor
When Reseller transfers to reseller having resellerId RESELLER_ID resellerType RESELLERUSER userId POS_USER_ID password POS_USER_PASSWORD amount TRANSFER_AMOUNT and receiverId is SUB_DISTRIBUTOR_ID and in response INVALID_HIERARCHY message is received

@R2R-TransferService-NotAllowed
Scenario: Transfer from Sub-Distributor to Distributor
When Reseller transfers to reseller having resellerId SUB_DISTRIBUTOR_ID resellerType RESELLERUSER userId POS_USER_ID password POS_USER_PASSWORD amount TRANSFER_AMOUNT and receiverId is DISTRIBUTOR_ID and in response INVALID_HIERARCHY message is received

@R2R-TransferService-NotAllowed
Scenario: Transfer from Distributor to Operator
When Reseller transfers to reseller having resellerId DISTRIBUTOR_ID resellerType RESELLERUSER userId POS_USER_ID password POS_USER_PASSWORD amount TRANSFER_AMOUNT and receiverId is OPERATOR_ID and in response INVALID_HIERARCHY message is received

@R2R-TransferService-NotAllowed
Scenario: Transfer from Operator to Sub-Distributor
When Reseller transfers to reseller having resellerId OPERATOR_ID resellerType RESELLERUSER userId POS_USER_ID password POS_USER_PASSWORD amount TRANSFER_AMOUNT and receiverId is SUB_DISTRIBUTOR_ID and in response INVALID_HIERARCHY message is received

@R2R-TransferService-NotAllowed
Scenario: Transfer from Operator to Reseller
When Reseller transfers to reseller having resellerId OPERATOR_ID resellerType RESELLERUSER userId POS_USER_ID password POS_USER_PASSWORD amount TRANSFER_AMOUNT and receiverId is RESELLER_ID and in response INVALID_HIERARCHY message is received

@R2R-TransferService-NotAllowed
Scenario: Transfer from Invalid Reseller ID
When Reseller transfers to reseller having resellerId INVALID_DISTRIBUTOR_ID resellerType RESELLERUSER userId POS_USER_ID password POS_USER_PASSWORD amount TRANSFER_AMOUNT and receiverId is RESELLER_ID and in response INVALID_RESELLER message is received

@R2R-TransferService-NotAllowed
Scenario: Transfer from Reseller with Invalid password
When Reseller transfers to reseller having resellerId DISTRIBUTOR_ID resellerType RESELLERUSER userId POS_USER_ID password INVALID_POS_PASSWORD amount TRANSFER_AMOUNT and receiverId is RESELLER_ID and in response INVALID_PASSWORD message is received

@R2R-TransferService-NotAllowed
Scenario: Transfer from Reseller with Invalid UserId
When Reseller transfers to reseller having resellerId DISTRIBUTOR_ID resellerType RESELLERUSER userId INVALID_POS_USER_ID password POS_USER_PASSWORD amount TRANSFER_AMOUNT and receiverId is RESELLER_ID and in response INVALID_USERID message is received

@R2R-TransferService-NotAllowed
Scenario: Transfer from Reseller with Invalid Amount -- 0 balance
When Reseller transfers to reseller having resellerId DISTRIBUTOR_ID resellerType RESELLERUSER userId POS_USER_ID password POS_USER_PASSWORD amount TRANSFER_AMOUNT_ZERO and receiverId is RESELLER_ID and in response INVALID_AMOUNT message is received

@R2R-TransferService-NotAllowed1
Scenario: Transfer from Reseller with Invalid Amount -- Contracts Voilation balance
When Reseller transfers to reseller having resellerId DISTRIBUTOR_ID resellerType RESELLERUSER userId POS_USER_ID password POS_USER_PASSWORD amount INVALID_TRANSFER_AMOUNT and receiverId is RESELLER_ID and in response INVALID_AMOUNT message is received

@R2R-TransferService-NotAllowed
Scenario: Transfer from Reseller with Invalid principal ID
When Reseller transfers to reseller having resellerId DISTRIBUTOR_ID resellerType INVALID_RESELLERUSER userId POS_USER_ID password POS_USER_PASSWORD amount TRANSFER_AMOUNT and receiverId is RESELLER_ID and in response INVALID_PRINCIPAL_TYPE message is received