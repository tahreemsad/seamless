Feature: Check last Transaction through USSD

@LastTransaction-USSD @gb-LastTransaction-USSD @zm-LastTransaction-USSD @gh-glo-LastTransaction-USSD
Scenario: Check Last Transaction Status for passed transaction
When I Perform R2R with valid data to the reseller number DIST2_MSISDN having reseller id DIST2_ID from reseller number OPERATOR_MSISDN with reseller id OPERATOR_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was R2R_OPTION
And I check the last transaction for passed status for the reseller OPERATOR_MSISDN having shortCode USSD_SHORT_CODE and menuOption USSD_TRANSACTION_STATUS_MENUOPTION then I see the USSD response which was USSD_ERS_TRANSACTION ersreference USSD_VALID_TRANSACTION

@LastTransaction-USSD @gb-LastTransaction-USSD @zm-LastTransaction-USSD @gh-glo-LastTransaction-USSD
Scenario: Check Last Transaction Status for failed transaction
When I Perform R2R with valid data to the reseller number RESELLER-1-2-2_MSISDN having reseller id RESELLER-1-2-2_ID from reseller number RESELLER-1-1-1_MSISDN with reseller id RESELLER-1-1-1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_1 and USSD_INVALID_PIN in message is returned after execution having shortcode USSD_SHORT_CODE and option was R2R_OPTION
And I check the last transaction for failed status for the reseller RESELLER-1-1-1_MSISDN having shortCode USSD_SHORT_CODE and menuOption USSD_TRANSACTION_STATUS_MENUOPTION then I see the USSD response which was USSD_ERS_TRANSACTION ersreference USSD_INVALID_EREREFRENCE

###################################################################################################################################################################
#These test cases are specifically for CI-MTN customer as the hierarchy is totally different in CI-MTN, so we are going to write seprate test cases for this customer

@ci-mtn-LastTransaction-USSD
Scenario: Check Last Transaction Status for passed transaction
When I Perform R2R with valid data to the reseller number DEAL1-1_MSISDN having reseller id DEAL1-1_ID from reseller number SDEAL1_MSISDN with reseller id SDEAL1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION
And I check the last transaction for passed status for the reseller SDEAL1_MSISDN having shortCode USSD_SHORT_CODE and menuOption USSD_MENU_RESELLER_SELF_CARE then I see the USSD response which was USSD_ERS_TRANSACTION ersreference USSD_VALID_TRANSACTION

@ci-mtn-LastTransaction-USSD
Scenario: Check Last Transaction Status for failed transaction
When I Perform R2R with valid data to the reseller number SDEAL1_MSISDN having reseller id SDEAL1_ID from reseller number OPERATOR_MSISDN with reseller id OPERATOR_MSISDN with an amount R2R_AMOUNT and pin is INVALID_RESELLER_PIN and USSD_INVALID_PIN in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION
And I check the last transaction for failed status for the reseller OPERATOR_MSISDN having shortCode USSD_SHORT_CODE and menuOption USSD_MENU_RESELLER_SELF_CARE then I see the USSD response which was USSD_ERS_TRANSACTION ersreference USSD_INVALID_EREREFRENCE