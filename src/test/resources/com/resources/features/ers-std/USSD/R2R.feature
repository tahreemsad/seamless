Feature: Perform R2R

@R2R-USSD @gb-R2R-USSD @zm-R2R-USSD @gh-glo-R2R-USSD 
Scenario: Transfer from Operator to Distributor using String base
When I Perform R2R with valid data to the reseller number DIST2_MSISDN having reseller id DIST2_ID from reseller number OPERATOR_MSISDN with reseller id OPERATOR_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was R2R_OPTION

@R2R-USSD @gb-R2R-USSD @gh-glo-R2R-USSD
Scenario: Transfer from Distributor to Distributor using String base
When I Perform R2R with valid data to the reseller number DIST2_MSISDN having reseller id DIST2_ID from reseller number DIST1_MSISDN with reseller id DIST1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was R2R_OPTION

@R2R-USSD @gb-R2R-USSD @zm-R2R-USSD @gh-glo-R2R-USSD @ng-glo-R2R-USSD
Scenario: Transfer from Distributor to Sub-distributor using String base
When I Perform R2R with valid data to the reseller number SUBDIST1-1_MSISDN having reseller id SUBDIST1-1_ID from reseller number DIST1_MSISDN with reseller id DIST1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was R2R_OPTION

@R2R-USSD @gb-R2R-USSD @gh-glo-R2R-USSD
Scenario: Transfer from subdistributor to subdistributor using String base
When I Perform R2R with valid data to the reseller number SUBDIST1-2_MSISDN having reseller id SUBDIST1-2_ID from reseller number SUBDIST1-1_MSISDN with reseller id SUBDIST1-1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was R2R_OPTION

@R2R-USSD @gb-R2R-USSD @zm-R2R-USSD @gh-glo-R2R-USSD @ng-glo-R2R-USSD
Scenario: Transfer from subdistributor to Reseller using String base
When I Perform R2R with valid data to the reseller number RESELLER-1-1-1_MSISDN having reseller id RESELLER-1-1-1_ID from reseller number SUBDIST1-1_MSISDN with reseller id SUBDIST1-1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was R2R_OPTION

#Negative Test Cases

@R2R-USSD @gb-R2R-USSD @zm-R2R-USSD @gh-glo-R2R-USSD
Scenario: Transfer from sub-distributor to non-register Reseller using String base
When I Perform R2R with valid data to the reseller number RESELLER-3-1-1_MSISDN having reseller id RESELLER-3-1-1_ID from reseller number SUBDIST1-1_MSISDN with reseller id SUBDIST1-1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was R2R_OPTION

@R2R-USSD @gb-R2R-USSD 
Scenario: Transfer from Reseller to Reseller using String base
When I Perform R2R with valid data to the reseller number RESELLER-1-2-2_MSISDN having reseller id RESELLER-1-2-2_ID from reseller number RESELLER-1-1-1_MSISDN with reseller id RESELLER-1-1-1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was R2R_OPTION

@R2R-USSD @gb-R2R-USSD @zm-R2R-USSD @gh-glo-R2R-USSD @ng-glo-R2R-USSD
Scenario: Transfer with wrong pin
When I Perform R2R with valid data to the reseller number RESELLER-1-2-2_MSISDN having reseller id RESELLER-1-2-2_ID from reseller number RESELLER-1-1-1_MSISDN with reseller id RESELLER-1-1-1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_1 and USSD_INVALID_PIN in message is returned after execution having shortcode USSD_SHORT_CODE and option was R2R_OPTION

@R2R-USSD @gb-R2R-USSD @zm-R2R-USSD @gh-glo-R2R-USSD
Scenario: Transfer when amount outside contract
When I Perform R2R with invalid data to the reseller number RESELLER-1-2-2_MSISDN having reseller id RESELLER-1-2-2_ID from reseller number RESELLER-1-1-1_MSISDN with reseller id RESELLER-1-1-1_ID with an amount R2R_AMOUNT2 and pin is RESELLER_PIN_2 and USSD_INVALID_AMOUNT in message is returned after execution having shortcode USSD_SHORT_CODE and option was R2R_OPTION

@R2R-USSD @gb-R2R-USSD @gh-glo-R2R-USSD
Scenario: Transfer from non registered reseller to registered reseller
When I Perform R2R with invalid data to the reseller number RESELLER-1-2-2_MSISDN having reseller id RESELLER-1-2-2_ID from reseller number RESELLER-3-1-1_MSISDN with reseller id RESELLER-3-1-1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_3 and UnRegisterUserCreditTrasnferViolation in message is returned after execution having shortcode USSD_SHORT_CODE and option was R2R_OPTION

@R2R-USSD @gb-R2R-USSD @zm-R2R-USSD @gh-glo-R2R-USSD
Scenario: Transfer is NOT allowed from distributor to operator
When I Perform R2R with invalid data to the reseller number OPERATOR_MSISDN having reseller id OPERATOR_ID from reseller number DIST2_MSISDN with reseller id DIST2_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and InvalidHierarchy in message is returned after execution having shortcode USSD_SHORT_CODE and option was R2R_OPTION

@R2R-USSD @gb-R2R-USSD @zm-R2R-USSD @gh-glo-R2R-USSD
Scenario: Perform R2R Self Transfer
When I Perform R2R with invalid data to the reseller number DIST1_MSISDN having reseller id DIST1_ID from reseller number DIST1_MSISDN with reseller id DIST1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and InvalidR2RTransfer in message is returned after execution having shortcode USSD_SHORT_CODE and option was R2R_OPTION

###################################################################################################################################################################
#These test cases are specifically for CI-MTN customer as the hierarchy is totally different in CI-MTN, so we are going to write seprate test cases for this customer

@ci-mtn-R2R-USSD
Scenario: Transfer from Operator to Strategic Dealer using String base
When I Perform R2R with valid data to the reseller number SDEAL1_MSISDN having reseller id SDEAL1_ID from reseller number OPERATOR_MSISDN with reseller id OPERATOR_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

@ci-mtn-R2R-USSD
Scenario: Transfer from Strategic Dealer to Dealer using String base
When I Perform R2R with valid data to the reseller number DEAL1-1_MSISDN having reseller id DEAL1-1_ID from reseller number SDEAL1_MSISDN with reseller id SDEAL1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

@ci-mtn-R2R-USSD
Scenario: Transfer from Strategic Dealer to POS using String base
When I Perform R2R with valid data to the reseller number POS-1-1-1-1_MSISDN having reseller id POS-1-1-1-1_ID from reseller number SDEAL1_MSISDN with reseller id SDEAL1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

@ci-mtn-R2R-USSD
Scenario: Transfer from Strategic Dealer to Strategic Agent using String base
When I Perform R2R with valid data to the reseller number SAGENT1-1_MSISDN having reseller id SAGENT1-1_ID from reseller number SDEAL1_MSISDN with reseller id SDEAL1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

@ci-mtn-R2R-USSD
Scenario: Transfer from Strategic Agent to POS using String base
When I Perform R2R with valid data to the reseller number POS-1-1-1-1_MSISDN having reseller id POS-1-1-1-1_ID from reseller number SAGENT1-1_MSISDN with reseller id SAGENT1-1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

@ci-mtn-R2R-USSD
Scenario: Transfer from Dealer to Dealer Agent using String base
When I Perform R2R with valid data to the reseller number DAGENT-1-1-1_MSISDN having reseller id DAGENT-1-1-1_ID from reseller number DEAL1-1_MSISDN with reseller id DEAL1-1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

@ci-mtn-R2R-USSD
Scenario: Transfer from Dealer Agent to POS using String base
When I Perform R2R with valid data to the reseller number POS-1-1-1-1_MSISDN having reseller id POS-1-1-1-1_ID from reseller number DAGENT-1-1-1_MSISDN with reseller id DAGENT-1-1-1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

@ci-mtn-R2R-USSD
Scenario: Transfer from Dealer to POS using String base
When I Perform R2R with valid data to the reseller number POS-1-1-1-1_MSISDN having reseller id POS-1-1-1-1_ID from reseller number DEAL1-1_MSISDN with reseller id DEAL1-1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and TRANSFER_CONFORMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

#Negative Test Cases

@ci-mtn-R2R-USSD
Scenario: Transfer from Strategic Dealer to Strategic Dealer
When I Perform R2R with invalid data to the reseller number SDEAL2_MSISDN having reseller id SDEAL2_ID from reseller number SDEAL1_MSISDN with reseller id SDEAL1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and InvalidHierarchy in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

@ci-mtn-R2R-USSD
Scenario: Transfer from Dealer to Dealer
When I Perform R2R with invalid data to the reseller number DEAL2-1_MSISDN having reseller id DEAL2-1_ID from reseller number DEAL1-1_MSISDN with reseller id DEAL1-1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and InvalidHierarchy in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

@ci-mtn-R2R-USSD
Scenario: Transfer from Strategic Agent to Strategic Agent
When I Perform R2R with invalid data to the reseller number SAGENT1-2_MSISDN having reseller id SAGENT1-2_ID from reseller number SAGENT1-1_MSISDN with reseller id SAGENT1-1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and InvalidHierarchy in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

@ci-mtn-R2R-USSD
Scenario: Transfer from POS to POS
When I Perform R2R with invalid data to the reseller number POS-1-1-1-2_MSISDN having reseller id POS-1-1-1-2_ID from reseller number POS-1-1-1-1_MSISDN with reseller id POS-1-1-1-1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and USSD_INVALID_AMOUNT in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

@ci-mtn-R2R-USSD
Scenario: Transfer from Dealer to Strategic Agent
When I Perform R2R with valid data to the reseller number SAGENT1-2_MSISDN having reseller id SAGENT1-2_ID from reseller number DEAL1-1_MSISDN with reseller id DEAL1-1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and InvalidHierarchy in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

@ci-mtn-R2R-USSD
Scenario: Transfer from Strategic Agent to Strategic Delegate
When I Perform R2R with valid data to the reseller number SDEL1-1_MSISDN having reseller id SDEL1-1_ID from reseller number SDEAL1_MSISDN with reseller id SDEAL1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and InvalidHierarchy in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

@ci-mtn-R2R-USSD
Scenario: Transfer from Dealer Agent to Dealer Delegate
When I Perform R2R with valid data to the reseller number DDEL-1-1-1_MSISDN having reseller id DDEL-1-1-1_ID from reseller number DAGENT-1-1-1_MSISDN with reseller id DAGENT-1-1-1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and InvalidHierarchy in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

@ci-mtn-R2R-USSD
Scenario: Transfer from Dealer to Strategic Agent with wrong pin
When I Perform R2R with valid data to the reseller number SAGENT1-2_MSISDN having reseller id SAGENT1-2_ID from reseller number DEAL1-1_MSISDN with reseller id DEAL1-1_ID with an amount R2R_AMOUNT and pin is INVALID_RESELLER_PIN and USSD_INVALID_PIN in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

@ci-mtn-R2R-USSD
Scenario: Transfer from Strategic Dealer to Dealer with invalid margin amount
When I Perform R2R with valid data to the reseller number DEAL1-1_MSISDN having reseller id DEAL1-1_ID from reseller number SDEAL1_MSISDN with reseller id SDEAL1_ID with an amount INVALID_R2R_AMOUNT and pin is RESELLER_PIN_2 and USSD_INVALID_AMOUNT in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

@ci-mtn-R2R-USSD
Scenario: Self Transfer from USSDApp
When I Perform R2R with valid data to the reseller number SDEAL1_MSISDN having reseller id SDEAL1_ID from reseller number SDEAL1_MSISDN with reseller id SDEAL1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and InvalidHierarchy in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

@ci-mtn-R2R-USSD
Scenario: Transfer from Strategic Dealer to Strategic Dealer (which is not a sub resller)
When I Perform R2R with valid data to the reseller number SDEAL2-1_MSISDN having reseller id SDEAL2-1_ID from reseller number SDEAL1_MSISDN with reseller id SDEAL1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and InvalidHierarchy in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION


#Bottom To Top Hierarchy

@ci-mtn-R2R-USSD
Scenario: Transfer from Strategic Dealer to Operator using String base
When I Perform R2R with valid data to the reseller number OPERATOR_MSISDN having reseller id OPERATOR_ID from reseller number SDEAL1_MSISDN with reseller id SDEAL1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and InvalidHierarchy in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

@ci-mtn-R2R-USSD
Scenario: Transfer from Dealer to Strategic Dealer using String base
When I Perform R2R with valid data to the reseller number SDEAL1_MSISDN having reseller id SDEAL1_ID from reseller number DEAL1-1_MSISDN with reseller id DEAL1-1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and InvalidHierarchy in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

@ci-mtn-R2R-USSD
Scenario: Transfer from POS to Strategic Dealer using String base
When I Perform R2R with valid data to the reseller number SDEAL1_MSISDN having reseller id SDEAL1_ID from reseller number POS-1-1-1-1_MSISDN with reseller id POS-1-1-1-1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and USSD_INVALID_AMOUNT in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

@ci-mtn-R2R-USSD
Scenario: Transfer from POS to Delegate Agent using String base
When I Perform R2R with valid data to the reseller number DAGENT-1-1-1_MSISDN having reseller id DAGENT-1-1-1_ID from reseller number POS-1-1-1-1_MSISDN with reseller id POS-1-1-1-1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and USSD_INVALID_AMOUNT in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

@ci-mtn-R2R-USSD
Scenario: Transfer from POS to Strategic Agent using String base
When I Perform R2R with valid data to the reseller number SAGENT1-1_MSISDN having reseller id SAGENT1-1_ID from reseller number POS-1-1-1-1_MSISDN with reseller id POS-1-1-1-1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and USSD_INVALID_AMOUNT in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

@ci-mtn-R2R-USSD
Scenario: Transfer from Strategic Agent to Strategic Dealer using String base
When I Perform R2R with valid data to the reseller number SDEAL1_MSISDN having reseller id SDEAL1_ID from reseller number SAGENT1-1_MSISDN with reseller id SAGENT1-1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and InvalidHierarchy in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

@ci-mtn-R2R-USSD
Scenario: Transfer from Strategic Delegate to Strategic Dealer using String base
When I Perform R2R with valid data to the reseller number SDEAL1_MSISDN having reseller id SDEAL1_ID from reseller number SDEL1-1_MSISDN with reseller id SDEL1-1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and InvalidHierarchy in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

@ci-mtn-R2R-USSD
Scenario: Transfer from Dealer Delegate to Dealer using String base
When I Perform R2R with valid data to the reseller number DEAL1-1_MSISDN having reseller id DEAL1-1_ID from reseller number DDEL-1-1-1_MSISDN with reseller id DDEL-1-1-1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and InvalidHierarchy in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION

@ci-mtn-R2R-USSD
Scenario: Transfer from POS to DEALER using String base
When I Perform R2R with valid data to the reseller number DEAL1-1_MSISDN having reseller id DEAL1-1_ID from reseller number POS-1-1-1-1_MSISDN with reseller id POS-1-1-1-1_ID with an amount R2R_AMOUNT and pin is RESELLER_PIN_2 and USSD_INVALID_AMOUNT in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_TRANSFER_OPTION