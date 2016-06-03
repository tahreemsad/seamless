Feature: Request for CMBK through USSD

@CMBK-USSD
Scenario: Subscriber request for a CMBK
And I send CMBK request using shortcode CMBK_SHORT_CODE to the subscriber SUBSCRIBER_1 and the USSD response should be CMBKRequestSent with log component SMSAPP_LOG and loglines are SMSAPP_LOG_ONE_LINE

################# NEGATIVE TESTING ##############################

@CMBK-USSD
Scenario: Subscriber request for a CMBK to an invalid msisdn
And I send CMBK request using shortcode CMBK_SHORT_CODE to the subscriber SUBSCRIBER_INVALID and the USSD response should be CMBKRequestWrongMSISDN with log component SMSAPP_LOG and loglines are SMSAPP_LOG_ONE_LINE