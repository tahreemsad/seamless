Feature: New subscriber anonymousId generation through USSD

@AnonymousID-USSD
Scenario: New subscriber register himself for the first time
When A New subscriber is generated and registered having shortcode SUBSRCRIBER_SHORT_CODE with menu option SUBSCRIBER_REGISTER_OPTION_FOR_ANONYMOUSID with pin SUBSCRIBER_PIN_2 with new pin SUBSCRIBER_PIN_2 and in response i received the message SubscriberRegisteredSuccess
And I request to generate AnonymousID from MSISDN SUBSCRIBER_MSISDN and shortcode ANONYMOUSID_SHORT_CODE
Then User Performs R2S with valid data to the subcriber anonymousid ANONYMOUS_ID from reseller number SUBSCRIBER_2 having resellerID RESELLER-1-1-1_ID with an amount TOPUP_AMOUNT and pin is RESELLER_PIN_2 and You have topped up R2S_TOPUP_AMOUNT_CONFIRMATION FCFA in message is returned after execution having shortcode USSD_SHORT_CODE and option was USSD_MENU_TOPUP