Feature: Registration and Change Pin

@PinChange-USSD @gb-PinChange-USSD @gh-glo-PinChange-USSD @ci-mtn-PinChange-USSD
Scenario: Subscriber try to change his password and put the new password as the old one
When New subscriber is registered having shortcode SUBSRCRIBER_SHORT_CODE with menu option USSD_SUBSCRIBER_REGISTER_OPTION with pin SUBSCRIBER_PIN_1 with new pin SUBSCRIBER_PIN_2 and in response i received the message SubscriberRegisteredSuccess 

#@PinChange-USSD
#Scenario: Subscriber try to change his password and put the new password different as the old one
#When New subscriber is registered having shortcode SUBSRCRIBER_SHORT_CODE with menu option USSD_SUBSCRIBER_REGISTER_OPTION with pin SUBSCRIBER_PIN_1 with new pin SUBSCRIBER_PIN_2 and in response i received the message SubscriberRegisteredSuccess

#@PinChange-USSD
#Scenario: Subscriber try to change his password and put the change password and confirm password invalid
#When New subscriber is registered having shortcode SUBSRCRIBER_SHORT_CODE with menu option USSD_SUBSCRIBER_REGISTER_OPTION with pin SUBSCRIBER_PIN_1 with new pin SUBSCRIBER_PIN_2 and in response i received the message SubscriberRegisteredSuccess


##############################RESELLER####################################

#@PinChange-USSD
#Scenario: Reseller try to change his password and put the new password as the old one
#When I change the password by hitting the curl command with short code USSD_SHORT_CODE for the reseller with MSISDN RESELLER_MSISDN_TO_CHANGE_PIN and optionMenu is optionMenuToChangePin and currentpassword is SUBSCRIBER_PIN_1 and new password is SUBSCRIBER_PIN_1 and confirm password is SUBSCRIBER_PIN_2 then i recieved the response as ErrorMessageForSamePassword

#@PinChange-USSD
#Scenario: Reseller try to change his password and put the new password different as the old one
#When I change the password by hitting the curl command with short code USSD_SHORT_CODE for the reseller with MSISDN RESELLER_MSISDN_TO_CHANGE_PIN and optionMenu is optionMenuToChangePin and currentpassword is SUBSCRIBER_PIN_1 and new password is SUBSCRIBER_ACTIVATION_NEW_PIN and confirm password is SUBSCRIBER_ACTIVATION_NEW_PIN then i recieved the response as PIN_CHANGED_SUCCESSFULLY
#@PinChange-USSD
#Scenario: Reseller try to change his password and put the change password back to the default one
#When I change the password by hitting the curl command with short code USSD_SHORT_CODE for the reseller with MSISDN RESELLER_MSISDN_TO_CHANGE_PIN and optionMenu is optionMenuToChangePin and currentpassword is SUBSCRIBER_ACTIVATION_NEW_PIN and new password is SUBSCRIBER_PIN_1 and confirm password is SUBSCRIBER_PIN_1 then i recieved the response as PIN_CHANGED_SUCCESSFULLY

#@PinChange-USSD
#Scenario: Reseller try to change his password and put the change password and confirm password invalid
#When I change the password by hitting the curl command with short code USSD_SHORT_CODE for the reseller with MSISDN RESELLER_MSISDN_TO_CHANGE_PIN and optionMenu is optionMenuToChangePin and currentpassword is SUBSCRIBER_PIN_1 and new password is RESELLER_PIN and confirm password is RESELLER_INCORRECT_PIN then i recieved the response as ConfirmationPasswordDoesnotMatch