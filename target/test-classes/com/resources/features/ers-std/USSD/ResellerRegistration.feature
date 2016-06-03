Feature: Reseller Registration

@ResellerRegistration-USSD-ci-mtn
Scenario: Reseller Register him self for the first time
Given Reseller register himself for the first time by hitting the shortcode USSD_SHORT_CODE and reseller menu was USSD_REGISTER_OPTION and reseller pin was RESELLER_PIN_2 and I get the reseller State EXTDEV_DEVICES_UNREGISTER_STATE from Database and then I see the message on ussdapp RESELLER_REGISTRATION_SUCCESS 


@ResellerRegistration-USSD-ci-mtn
Scenario: Already Registered Reseller try to register him self again
Given Reseller register himself for the first time by hitting the shortcode USSD_SHORT_CODE and reseller menu was USSD_REGISTER_OPTION and reseller pin was RESELLER_PIN_2 and I get the reseller State EXTDEV_DEVICES_REGISTER_STATE from Database and then I see the message on ussdapp ALREADY_ACTIVATED_RESELLER 
