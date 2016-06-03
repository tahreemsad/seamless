Feature: Perform R2S

#@ResellerRegistration-USSD
#Scenario: Register the Sub-Ditributor
#When User register him self by hitting the shortcode USSD_SHORT_CODE with msisdn SUBDIS_MSISDN_FOR_RESELLER_REGISTRATION with menuOption USSD_SUBSCRIBER_REGISTER_OPTION with activated pin RESELLER_ACTIVATION_PIN with new pin RESELLER_PIN_2 and in response i received the message ResellerActivation

#@ResellerRegistration-USSD
#Scenario: Register the Sub-Ditributor
#When User register him self by hitting the shortcode 103 with msisdn 2456030200 with menuOption 1 with activated pin 12345 with new pin 2015 and in response i received the message You have been activated successfully.

#@ResellerRegistration-USSD
#Scenario: Register the Reseller
#When User register him self by hitting the shortcode 103 with msisdn 2456030201 with menuOption 1 with activated pin 12345 with new pin 2015 and in response i received the message You have been activated successfully.

@R2S-USSD @gb-R2S-USSD @zm-R2S-USSD @gh-glo-R2S-USSD @ng-glo-R2S-USSD
Scenario: Perform R2S (Reseller2Subcriber) from Sub-Distributor to Subscriber
When User Performs R2S with valid data to the subscriber number SUBSCRIBER_1 from reseller number SUBDIST1-1_MSISDN having resellerID SUBDIST1-1_ID with an amount TOPUP_AMOUNT and pin is RESELLER_PIN_2 and R2S_TOPUP_AMOUNT_CONFIRMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_MENU_TOPUP

@R2S-USSD @gb-R2S-USSD @gh-glo-R2S-USSD @ng-glo-R2S-USSD
Scenario: Perform R2S (Reseller2Subcriber) from Reseller to Subscriber 
When User Performs R2S with valid data to the subscriber number SUBSCRIBER_4 from reseller number RESELLER-1-1-1_MSISDN having resellerID RESELLER-1-1-1_ID with an amount TOPUP_AMOUNT and pin is RESELLER_PIN_2 and R2S_TOPUP_AMOUNT_CONFIRMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_MENU_TOPUP

@R2S-USSD @gb-R2S-USSD @gh-glo-R2S-USSD @ng-glo-R2S-USSD
Scenario: Perform R2S (Reseller2Subcriber) from Sub-Ditributor to Subscriber with Invalid pin
When User Perform R2S with invalid data to the subcriber number SUBSCRIBER_4 from reseller number SUBDIST1-1_MSISDN having resellerID SUBDIST1-1_ID with an amount TOPUP_AMOUNT and pin is RESELLER_PIN_1 and USSD_INVALID_PIN in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_MENU_TOPUP

@R2S-USSD @gb-R2S-USSD @gh-glo-R2S-USSD @ng-glo-R2S-USSD
Scenario: Perform R2S (Reseller2Subcriber) from Sub-Ditributor to Subscriber(Reseller)
When User Performs R2S with valid data to the subscriber number SUBSCRIBER_1 from reseller number SUBDIST1-1_MSISDN having resellerID SUBDIST1-1_ID with an amount TOPUP_AMOUNT and pin is RESELLER_PIN_2 and R2S_TOPUP_AMOUNT_CONFIRMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_MENU_TOPUP

###################################################################################################################################################################
#These test cases are specifically for CI-MTN customer as the hierarchy is totally different in CI-MTN, so we are going to write seprate test cases for this customer

@ci-mtn-R2S-USSD
Scenario: Perform R2S (Reseller2Subcriber) from Strategic Dealer to Subscriber
When User Performs R2S with valid data to the subscriber number SUBSCRIBER_1 from reseller number SDEAL1_MSISDN having resellerID SDEAL1_ID with an amount TOPUP_AMOUNT and pin is RESELLER_PIN_2 and R2S_TOPUP_AMOUNT_CONFIRMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_MENU_TOPUP

@ci-mtn-R2S-USSD
Scenario: Perform R2S (Reseller2Subcriber) from Strategic Agent to Subscriber
When User Performs R2S with valid data to the subscriber number SUBSCRIBER_1 from reseller number SAGENT1-1_MSISDN having resellerID SAGENT1-1_ID with an amount TOPUP_AMOUNT and pin is RESELLER_PIN_2 and R2S_TOPUP_AMOUNT_CONFIRMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_MENU_TOPUP

@ci-mtn-R2S-USSD
Scenario: Perform R2S (Reseller2Subcriber) from Dealer to Subscriber
When User Performs R2S with valid data to the subscriber number SUBSCRIBER_1 from reseller number DEAL1-1_MSISDN having resellerID DEAL1-1_ID with an amount TOPUP_AMOUNT and pin is RESELLER_PIN_2 and R2S_TOPUP_AMOUNT_CONFIRMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_MENU_TOPUP

@ci-mtn-R2S-USSD
Scenario: Perform R2S (Reseller2Subcriber) from POS to Subscriber
When User Performs R2S with valid data to the subscriber number SUBSCRIBER_1 from reseller number POS-1-1-1-1_MSISDN having resellerID POS-1-1-1-1_ID with an amount TOPUP_AMOUNT and pin is RESELLER_PIN_2 and R2S_TOPUP_AMOUNT_CONFIRMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_MENU_TOPUP

@ci-mtn-R2S-USSD
Scenario: Perform R2S (Reseller2Subcriber) from Dealer Agent to Subscriber
When User Performs R2S with valid data to the subscriber number SUBSCRIBER_1 from reseller number DAGENT-1-1-1_MSISDN having resellerID DAGENT-1-1-1_ID with an amount TOPUP_AMOUNT and pin is RESELLER_PIN_2 and R2S_TOPUP_AMOUNT_CONFIRMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_MENU_TOPUP

@ci-mtn-R2S-USSD
Scenario: Perform R2S (Reseller2Subcriber) from Strategic Delegate to Subscriber
When User Performs R2S with valid data to the subscriber number SUBSCRIBER_1 from reseller number SDEL1-1_MSISDN having resellerID SDEL1-1_ID with an amount TOPUP_AMOUNT and pin is RESELLER_PIN_2 and R2S_TOPUP_AMOUNT_CONFIRMATION_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_MENU_TOPUP

### Negaive

@ci-mtn-R2S-USSD
Scenario: Perform R2S (Reseller2Subcriber) from Strategic Dealer to Subscriber with Invalid Amount
When User Performs R2S with valid data to the subscriber number SUBSCRIBER_1 from reseller number SDEAL1_MSISDN having resellerID SDEAL1_ID with an amount INVALID_TOPUP_AMOUNT and pin is RESELLER_PIN_2 and USSD_INVALID_AMOUNT in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_MENU_TOPUP