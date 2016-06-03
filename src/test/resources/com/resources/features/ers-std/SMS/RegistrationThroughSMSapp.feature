Feature: Registration through SMSapp 

@Subscriber-Registration-SMSapp @Subscriber-Registration-SMSapp-gb-mtn
Scenario: Subscriber registration for first time from SMS Channel
When I register a new subscriber using smsapp msg is SUBSCRIBER_REGISTER_PIN_0000 log notification message is SUCCESS_NOTIFICATION and component is SMSAPP_LOG and number of log lines are SMSAPP_LOG_LINES