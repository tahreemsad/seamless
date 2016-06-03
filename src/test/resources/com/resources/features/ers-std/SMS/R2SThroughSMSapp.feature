Feature: Perform R2S through SMS app

@R2S-Through-SMSapp @R2S-Through-SMSapp-gb-mtn
Scenario: Perform R2S
When User Perform R2S with valid data through SMS channel to the subscriber number SUBSCRIBER_1 having resellerID SUBDIST1_2_ID from reseller number SUBDIST1_2_MSISDN with an amount TOPUP_AMOUNT and pin is RESELLER_PIN and in logs notification message SUCCESS_NOTIFICATION_ON_TOPUP is returned after execution where componenet is SMSAPP_LOG and log lines are SMSAPP_LOG_LINES and curl message was R2S_TRANSFER
