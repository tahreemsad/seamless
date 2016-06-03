Feature: Sell Voucher

@SellVoucher-AgentPortal-gh-glo
Scenario: Reseller successfully sells a voucher from AgentPortal
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter OPERATOR_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I enter WEBADMIN_PASSWORD in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 5 seconds
And User clicks on the button AgentportalMainSubscribersButton
And User clicks on the button AgentportalSellVoucherButton
And System sleeps for 2000 seconds
And I select AIRTIME_VOUCHER from dropdown AgenPortalSelectVoucherTypeDropdown
And System sleeps for 2000 seconds
And I select 100_VOUCHER from dropdown AgentPortalVoucherSelection
And System sleeps for 2000 seconds
And I input SUBSCRIBER_1 in the AgentPortalSubscriberMSISDN field with IS_PREFIX_NOTREQUIRED
And System sleeps for 2000 seconds
And I select CASH_PAYMENT-METHOD from dropdown AgenPortalSelectPaymentMethodDropdown
And I enter VOUCHER_COMMENT in the AgentPortalVoucherComment field
And System sleeps for 2000 seconds
And User clicks on the button AgentPortalVoucherSubmitButton
And System sleeps for 2000 seconds
And I press the enter key on the webElement AgenportalReceiptOKButton