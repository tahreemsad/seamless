Feature: Friends And Family

@FAF-USSD
Scenario: Subscriber requests to add a subscriber number to FAF list
When I hit shortcode FAF_SHORTCODE from subscriber SUBSCRIBER_6 and request ADD_MENU_OPTION to add FAF number to my list and ussd response is NUMBER_ADDED and component is VASMANAGER_LOG and number of log lines are SMSAPP_LOG_ONE_LINE and message in logs ADD_LOG_MESSAGE


@FAF-USSD
Scenario: Subscriber requests to view his FAF list
Then I request from subscriber SUBSCRIBER_6 shortcode FAF_SHORTCODE and request VIEW_FAF_MENU_OPTION ussd response is FAF_LIST

@FAF-USSD
Scenario: Subscriber requests to remove a subscriber number to FAF list
When I request shortcode FAF_SHORTCODE from subscriber SUBSCRIBER_6 and request REMOVE_MENU_OPTION to add FAF number to my list and ussd response is NUMBER_REMOVED and component is VASMANAGER_LOG and number of log lines are SMSAPP_LOG_ONE_LINE and message in logs REMOVAL_LOG_MESSAGE
