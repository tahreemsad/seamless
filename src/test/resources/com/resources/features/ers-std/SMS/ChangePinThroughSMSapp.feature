Feature: Change password through SMS app

@Subscriber-ChangePassword-SMSapp @Subscriber-ChangePassword-SMSapp-gb-mtn
Scenario: Subscriber Change password from SMS Channel 
When I register a new subscriber using smsapp msg is SUBSCRIBER_REGISTER_PIN_0000 log notification message is SUCCESS_NOTIFICATION and component is SMSAPP_LOG and number of log lines are SMSAPP_LOG_LINES
Then I perform the change pin using msg SUBSCRIBER_CHANGE_PIN and old password is SUBSCRIBER_OLD_PIN and new password is SUBSCRIBER_NEW_PIN and confirm password is SUBSCRIBER_CONFIRM_PIN and notification message is SUCCESS_NOTIFICATION_ON_SMS_CHANGE and component is SMSAPP_LOG and log lines are SMSAPP_LOG_LINES

@Reseller-ChangePassword-SMSapp @Reseller-ChangePassword-SMSapp-gb-mtn
Scenario: Reseller Change password from SMS Channel
Given User performs the change pin using msg RESELLER_CHANGE_PIN having number DIST1_MSISDN and old password is RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD and notification message is SUCCESS_NOTIFICATION_ON_SMS_CHANGE and component is SMSAPP_LOG and log lines are SMSAPP_LOG_LINES

@Reseller-ChangePassword-revertback-SMSapp @Reseller-ChangePassword-revertback-SMSapp-gb-mtn
Scenario: Reseller Change password from SMS Channel and reset it to the old one
Given User performs the change pin using msg RESELLER_CHANGE_PIN having number DIST1_MSISDN and old password is RESELLER_NEW_PASSWORD and new password is RESELLER_OLD_PASSWORD and confirm password is RESELLER_OLD_PASSWORD and notification message is SUCCESS_NOTIFICATION_ON_SMS_CHANGE and component is SMSAPP_LOG and log lines are SMSAPP_LOG_LINES