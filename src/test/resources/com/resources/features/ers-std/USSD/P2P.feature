Feature: Perform P2P

@P2P-USSD @gb-P2P-USSD
Scenario: Subscriber performs the topup to the other subscriber with valid Pin
When User Performs R2S with valid data to the subscriber number SUBSCRIBER_1 from reseller number SUBDIST1-1_MSISDN having resellerID SUBDIST1-1_ID with an amount TOPUP_AMOUNT and pin is RESELLER_PIN_2 and R2S_TOPUP_AMOUNT_CONFIRMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_MENU_TOPUP
When I Perform P2P to the subscriber number SUBSCRIBER_1 from subscriber number SUBSCRIBER_3 with an amount P2P_AMOUNT and pin is SUBSCRIBER_PIN_1 and USSD_VALID_TRANSFER in message is returned after execution having shortcode SUBSRCRIBER_SHORT_CODE and option was USSD_MENU_TOPUP

@P2P-USSD @gb-P2P-USSD
Scenario: Subscriber performs the topup to the other subscriber with Invalid Pin
When User Performs R2S with valid data to the subscriber number SUBSCRIBER_1 from reseller number SUBDIST1-1_MSISDN having resellerID SUBDIST1-1_ID with an amount TOPUP_AMOUNT and pin is RESELLER_PIN_2 and R2S_TOPUP_AMOUNT_CONFIRMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_MENU_TOPUP
When I Perform P2P to the subscriber number SUBSCRIBER_4 from subscriber number SUBSCRIBER_1 with an amount P2P_AMOUNT and pin is SUBSCRIBER_PIN_3 and USSD_INVALID_PIN in message is returned after execution having shortcode SUBSRCRIBER_SHORT_CODE and option was USSD_MENU_TOPUP

@P2P-USSD @gb-P2P-USSD
Scenario: Subscriber performs the topup his own number
When User Performs R2S with valid data to the subscriber number SUBSCRIBER_1 from reseller number SUBDIST1-1_MSISDN having resellerID SUBDIST1-1_ID with an amount TOPUP_AMOUNT and pin is RESELLER_PIN_2 and R2S_TOPUP_AMOUNT_CONFIRMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_MENU_TOPUP
When I Perform P2P to the subscriber number SUBSCRIBER_3 from subscriber number SUBSCRIBER_3 with an amount P2P_AMOUNT and pin is SUBSCRIBER_PIN_1 and USSD_INVALID_TOPUP in message is returned after execution having shortcode SUBSRCRIBER_SHORT_CODE and option was USSD_MENU_TOPUP

@P2P-USSD @gb-P2P-USSD
Scenario: Subscriber performs the topup outside the margins
When User Performs R2S with valid data to the subscriber number SUBSCRIBER_1 from reseller number SUBDIST1-1_MSISDN having resellerID SUBDIST1-1_ID with an amount TOPUP_AMOUNT and pin is RESELLER_PIN_2 and R2S_TOPUP_AMOUNT_CONFIRMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_MENU_TOPUP
When I Perform P2P to the subscriber number SUBSCRIBER_1 from subscriber number SUBSCRIBER_3 with an amount P2P_AMOUNT_2 and pin is SUBSCRIBER_PIN_1 and USSD_INVALID_AMOUNT in message is returned after execution having shortcode SUBSRCRIBER_SHORT_CODE and option was USSD_MENU_TOPUP

###################################################################################################################################################################
#These test cases are specifically for CI-MTN customer as the hierarchy is totally different in CI-MTN, so we are going to write seprate test cases for this customer

@ci-mtn-P2P-USSD
Scenario: Subscriber performs the topup to the other subscriber with valid Pin
When User Performs R2S with valid data to the subscriber number SUBSCRIBER_1 from reseller number SDEAL1_MSISDN having resellerID SDEAL1_ID with an amount TOPUP_AMOUNT and pin is RESELLER_PIN_2 and R2S_TOPUP_AMOUNT_CONFIRMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_MENU_TOPUP
When I Perform P2P to the subscriber number SUBSCRIBER_3 from subscriber number SUBSCRIBER_1 with an amount P2P_AMOUNT and pin is SUBSCRIBER_PIN_1 and USSD_VALID_TRANSFER in message is returned after execution having shortcode SUBSRCRIBER_SHORT_CODE and option was USSD_MENU_TOPUP

@ci-mtn-P2P-USSD
Scenario: Subscriber performs the topup to the other subscriber with Invalid Pin
When User Performs R2S with valid data to the subscriber number SUBSCRIBER_1 from reseller number SDEAL1_MSISDN having resellerID SDEAL1_ID with an amount TOPUP_AMOUNT and pin is RESELLER_PIN_2 and R2S_TOPUP_AMOUNT_CONFIRMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_MENU_TOPUP
When I Perform P2P to the subscriber number SUBSCRIBER_4 from subscriber number SUBSCRIBER_1 with an amount P2P_AMOUNT and pin is SUBSCRIBER_PIN_3 and USSD_INVALID_PIN in message is returned after execution having shortcode SUBSRCRIBER_SHORT_CODE and option was USSD_MENU_TOPUP

@ci-mtn-P2P-USSD
Scenario: Subscriber performs the topup his own number
When User Performs R2S with valid data to the subscriber number SUBSCRIBER_1 from reseller number SDEAL1_MSISDN having resellerID SDEAL1_ID with an amount TOPUP_AMOUNT and pin is RESELLER_PIN_2 and R2S_TOPUP_AMOUNT_CONFIRMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_MENU_TOPUP
When I Perform P2P to the subscriber number SUBSCRIBER_3 from subscriber number SUBSCRIBER_3 with an amount P2P_AMOUNT and pin is SUBSCRIBER_PIN_1 and USSD_INVALID_TOPUP in message is returned after execution having shortcode SUBSRCRIBER_SHORT_CODE and option was USSD_MENU_TOPUP

@ci-mtn-P2P-USSD
Scenario: Subscriber performs the topup outside the margins
When User Performs R2S with valid data to the subscriber number SUBSCRIBER_1 from reseller number SDEAL1_MSISDN having resellerID SDEAL1_ID with an amount TOPUP_AMOUNT and pin is RESELLER_PIN_2 and R2S_TOPUP_AMOUNT_CONFIRMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_MENU_TOPUP
When I Perform P2P to the subscriber number SUBSCRIBER_3 from subscriber number SUBSCRIBER_1 with an amount INVALID_P2P_AMOUNT and pin is SUBSCRIBER_PIN_1 and USSD_INVALID_AMOUNT in message is returned after execution having shortcode SUBSRCRIBER_SHORT_CODE and option was USSD_MENU_TOPUP
