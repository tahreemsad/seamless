Feature: Perform P2P

@P2P-Transfer-SMSapp @P2P-Transfer-SMSapp-gb-mtn
Scenario: Perform P2P transfer from SMS Channel
When I register a new subscriber using smsapp msg is SUBSCRIBER_REGISTER_PIN_0000 log notification message is SUCCESS_NOTIFICATION and component is SMSAPP_LOG and number of log lines are SMSAPP_LOG_LINES
Then I perform p2p using msg SUBSCRIBER_TRANSFER amunt is P2P_AMOUNT and receiver is SUBSCRIBER_1 and password is SUBSCRIBER_PASSWORD_P2P log notification message is SUCCESS_NOTIFICATION_ON_TRANSFER and component is SMSAPP_LOG and numebr of log lines are SMSAPP_LOG_LINES