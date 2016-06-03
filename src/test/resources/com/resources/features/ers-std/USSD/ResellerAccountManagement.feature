Feature: Perform Reseller Account Management via USSD channel

#Create Reseller
@ng-glo-CreateReseller-USSD
Scenario: Perform Create Reseller from Distributor to Sub-Distributor
When User performs Create Reseller from reseller id DIST1_ID having DIST1_MSISDN to child SUB_DIST with valid data with RESELLER_TYPE_1 and first name FNAME_1 and last name LNAME_1 and RESPONSE_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and pin RESELLER_PIN_2  

#Create Reseller
@ng-glo-CreateReseller-USSD
Scenario: Perform Create Reseller from Sub-Distributor to Retailer
When User performs Create Reseller from reseller id SUBDIST1-1_ID having SUBDIST1-1_MSISDN to child ANY_RETAILER with valid data with RESELLER_TYPE_2 and first name FNAME_2 and last name LNAME_2 and RESPONSE_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and pin RESELLER_PIN_2  

#Create Reseller
@ng-glo-CreateReseller-USSD
Scenario: Perform Create Reseller from Distributor to Direct-Reseller
When User performs Create Reseller from reseller id DIST1_ID having DIST1_MSISDN to child DIRECT_RESELLER with valid data with RESELLER_TYPE_3 and first name FNAME_3 and last name LNAME_3 and RESPONSE_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and pin RESELLER_PIN_2  

#Create Reseller
@ng-glo-CreateReseller-USSD
Scenario: Perform Create Reseller from Distributor to Staff-Distributor
When User performs Create Reseller from reseller id DIST1_ID having DIST1_MSISDN to child STAFF_DIST with valid data with RESELLER_TYPE_4 and first name FNAME_4 and last name LNAME_4 and RESPONSE_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and pin RESELLER_PIN_2  

#Create Reseller
@ng-glo-CreateReseller-USSD
Scenario: Perform Create Reseller from Sub-Distributor to Staff-Sub-Distributor
When User performs Create Reseller from reseller id SUBDIST1-1_ID having SUBDIST1-1_MSISDN to child STAFF_SUBDIST with valid data with RESELLER_TYPE_5 and first name FNAME_5 and last name LNAME_5 and RESPONSE_MESSAGE in message is returned after execution having shortcode USSD_SHORT_CODE and pin RESELLER_PIN_2  

