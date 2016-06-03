Feature: View Ussd Menu

@viewussdmenu @gb-viewussdmenu @zm-viewussdmenu @gh-glo-viewussdmenu1 @ng-glo-viewussdmenu
Scenario: user performs the ussd from his cell phone by dialing *103#
Given User performs the USSD from my cell number SUBDIST1-1_MSISDN by entering the shortcode USSD_SHORT_CODE and menuOption USSD_NO_MENU to verify the menu SHOW_USSD_MENU

@viewussdmenu @gb-viewussdmenu @zm-viewussdmenu @gh-glo-viewussdmenu
Scenario: user performs the ussd  to verify reports submenu from his cell phone by dialing *103*7#
Given User performs the USSD from my cell number SUBDIST1-1_MSISDN by entering the shortcode USSD_SHORT_CODE and menuOption USSD_MENU_REPORTS to verify the menu SHOW_USSD_MENU_REPORTS

@viewussdmenu @gb-viewussdmenu @zm-viewussdmenu @gh-glo-viewussdmenu
Scenario: user performs the ussd  to verify vouchers submenu from his cell phone by dialing *103*10#
Given User performs the USSD from my cell number SUBDIST1-1_MSISDN by entering the shortcode USSD_SHORT_CODE and menuOption USSD_MENU_VOUCHERS to verify the menu SHOW_USSD_MENU_VOUCHERS

###################################################################################################################################################################

#These test cases are specifically for CI-MTN customer as the hierarchy is totally different in CI-MTN, so we are going to write seprate test cases for this customer
@ci-mtn-viewussdmenu
Scenario: user performs the ussd from his cell phone by dialing *103#
Given User performs the USSD from my cell number SDEAL1_MSISDN by entering the shortcode USSD_SHORT_CODE and menuOption USSD_NO_MENU to verify the menu SHOW_USSD_MENU

@ci-mtn-viewussdmenu
Scenario: user performs the ussd  to verify reports submenu from his cell phone by dialing *103*6*3#
Given User performs the USSD from my cell number SDEAL1_MSISDN by entering the shortcode USSD_SHORT_CODE and menuOption USSD_MENU_RESELLER_SELF_CARE to verify the menu SHOW_USSD_MENU_REPORTS

@ci-mtn-viewussdmenu
Scenario: user performs the ussd  to verify Register submenu from his cell phone by dialing *103*1#
Given User performs the USSD from my cell number SDEAL1_MSISDN by entering the shortcode USSD_SHORT_CODE and menuOption USSD_REGISTER_OPTION to verify the menu SHOW_USSD_REGISTER_MENU

@ci-mtn-viewussdmenu
Scenario: user performs the ussd  to verify Register submenu from his cell phone by dialing *103*6#
Given User performs the USSD from my cell number SDEAL1_MSISDN by entering the shortcode USSD_SHORT_CODE and menuOption USSD_MENU_RESELLER_SELF_CARE to verify the menu SHOW_USSD_RESELLER_SELFCARE_MENU