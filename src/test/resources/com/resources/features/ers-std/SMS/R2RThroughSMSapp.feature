Feature: Perform R2R through SMS app

@R2R-Through-SMSapp @R2R-Through-SMSapp-gb-mtn
Scenario: Perform R2R from SMSapp
When User Perform R2R with valid data through SMS channel to the reciever reseller number SUBDIST1_2_MSISDN having reciever resellerID SUBDIST1_2_ID from reseller number DIST1_MSISDN having reseller id DIST1_ID with an amount TRANSFER_AMOUNT and pin is RESELLER_PIN and in logs notification message SUCCESS_NOTIFICATION_ON_TRANSFER is returned after execution where componenet is SMSAPP_LOG and log lines are SMSAPP_LOG_LINES and curl message was R2R_TRANSFER