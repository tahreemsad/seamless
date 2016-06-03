Feature: Check Reseller Balance through USSD

@CheckResellerbalance-USSD @gb-CheckResellerbalance-USSD @zm-CheckResellerbalance-USSD @gh-glo-CheckResellerbalance-USSD
Scenario: Check Balance
Then I perform the transaction from MSISDN RESELLER-1-1-1_MSISDN having resellerID RESELLER-1-1-1_ID shortcode USSD_SHORT_CODE and menuOption USSD_BALANCE_CHECK_OPTION


###################################################################################################################################################################
#These test cases are specifically for CI-MTN customer as the hierarchy is totally different in CI-MTN, so we are going to write seprate test cases for this customer

@CheckResellerbalance-USSD-ci-mtn
Scenario: Check Balance
Then I perform the transaction from MSISDN SDEAL1_MSISDN having resellerID SDEAL1_ID shortcode USSD_SHORT_CODE and menuOption USSD_MENU_RESELLER_SELF_CARE