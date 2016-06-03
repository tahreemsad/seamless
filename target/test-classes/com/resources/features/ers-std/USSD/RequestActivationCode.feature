Feature: Request Activation Code

@RequestActivationCode-USSD-ci-mtn
Scenario: Reseller request for the activation code
When Reseller SDEAL1_ID having reseller msisdn SDEAL1_MSISDN request for the activation code he recieves the activation code and then I verify the activation code by hitting the short code USSD_SHORT_CODE and in response I received the message RESELLER_ACTIVATION_CODE 
