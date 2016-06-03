Feature: Subscriber registration through USSD

@Subscriber-registration-USSD @gb-Subscriber-registration-USSD
Scenario: Subscriber who is already registered to the system try to register again
When User register him self by hitting the shortcode SUBSRCRIBER_SHORT_CODE with msisdn SUBSCRIBER_5 with menuOption USSD_SUBSCRIBER_REGISTER_OPTION with activated pin SUBSCRIBER_PIN_1 with new pin SUBSCRIBER_PIN_2 and in response i received the message SubscriberAlreadyActivated

@Subscriber-registration-USSD @gb-Subscriber-registration-USSD
Scenario: Subscriber register him self for the first time
When New subscriber is registered having shortcode SUBSRCRIBER_SHORT_CODE with menu option USSD_SUBSCRIBER_REGISTER_OPTION with pin SUBSCRIBER_PIN_1 with new pin SUBSCRIBER_PIN_2 and in response i received the message SubscriberRegisteredSuccess

###################################################################################################################################################################
#These test cases are specifically for CI-MTN customer as the hierarchy is totally different in CI-MTN, so we are going to write seprate test cases for this customer

@ci-mtn-Subscriber-registration-USSD
Scenario: Subscriber who is already registered to the system try to register again
When User register him self by hitting the shortcode SUBSRCRIBER_SHORT_CODE with msisdn SUBSCRIBER_5 with menuOption USSD_SUBSCRIBER_REGISTER_OPTION with activated pin SUBSCRIBER_PIN_1 with new pin SUBSCRIBER_PIN_2 and in response i received the message SubscriberAlreadyActivated

@ci-mtn-Subscriber-registration-USSD
Scenario: Subscriber register him self for the first time
When New subscriber is registered having shortcode SUBSRCRIBER_SHORT_CODE with menu option USSD_SUBSCRIBER_REGISTER_OPTION with pin SUBSCRIBER_PIN_1 with new pin SUBSCRIBER_PIN_2 and in response i received the message SubscriberRegisteredSuccess
