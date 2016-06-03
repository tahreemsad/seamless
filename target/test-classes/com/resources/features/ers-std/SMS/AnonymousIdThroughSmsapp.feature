Feature: subscriber anonymousId generation through SMSapp 

@Subscriber-anonymousId-SMSapp
Scenario: Subscriber anonymousId registration for first time from SMS Channel
When I register a user for anonymous id using smsapp msg is SUBSCRIBER_REGISTER_PIN_2015 log notification message is SUCCESS_NOTIFICATION and component is SMSAPP_LOG and number of log lines are SMSAPP_LOG_LINES
Then I generate anonymousId using smsapp for subscriber and shortcode is SHORTCODE log notification message is SMSAPP_NOTIFICATION and RESELLER_PIN and component is SMSAPP_LOG and number of log lines are SMSAPP_LOG_LINES
When I Perform R2S through SMS channel to the anonymousId ANONYMOUS_ID having resellerID SUBDIST1_2_ID from reseller number SUBDIST1_2_MSISDN with an amount TOPUP_AMOUNT and pin is RESELLER_PIN and in logs notification message SUCCESS_NOTIFICATION_ON_TOPUP is returned after execution where componenet is SMSAPP_LOG and log lines are SMSAPP_LOG_LINES and curl message was R2S_TRANSFER