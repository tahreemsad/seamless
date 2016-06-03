Feature: Login to agentportal

@login-AgenPortal
Scenario: Can log in with proper credentials
Given Log Can log in with proper credentials
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter DIST1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I enter WEBADMIN_PASSWORD in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 2000 seconds
And User clicks on the button AgentportalMainAgentsButton
Then I should see WELCOME_MESSAGE_AGENT_PORTAL on the page AgentportalWelcomeText

@login-AgenPortal
Scenario: Cannot log in if password is incorrect
Given Log Cannot log in if password is incorrect
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter DIST1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And System sleeps for 2000 seconds
And I enter INCORRECT_AGENTPORTAL_PASSWORD in the AgentportalPassword field
And System sleeps for 2000 seconds
And User clicks on the button AgentportalLoginButton
And System sleeps for 2000 seconds
Then I verify the popup text AgentPortalLoginFailedPopupText with the user text AGENTPORTAL_LOGIN_FAILED_MESSAGE on the page

@login-AgenPortal
Scenario: Cannot log in if password is Short
Given Log Cannot log in if password is Short
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter DIST1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I enter AGENTPORTAL_SHORT_LOGIN_PASSWORD in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
Then I verify the popup text AgentPortalLoginFailedPopupText with the user text AGENTPORTAL_LOGIN_FAILED_MESSAGE on the page

@login-AgenPortal
Scenario: Cannot log with empty password
Given Log Cannot log with empty password
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter DIST1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And User clicks on the button AgentportalLoginButton
Then I verify the popup text AgentPortalLoginFailedPopupText with the user text AGENTPORTAL_LOGIN_WITH_NO_PASSWORD_MESSAGE on the page

###################################################################################################################################################################
#These test cases are specifically for CI-MTN customer as the hierarchy is totally different in CI-MTN, so we are going to write seprate test cases for this customer

@login-AgenPortal-ci-mtn
Scenario: Can log in with proper credentials
Given Log Can log in with proper credentials
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter SDEAL1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I confirm whether to set the old password or password for Reseller SDEAL1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalLoginButton
And System sleeps for 5000 seconds
And I check change password for Reseller SDEAL1_ID having resellerType USERTYPE and old password was RESELLER_OLD_PASSWORD and new password is RESELLER_NEW_PASSWORD and confirm password is RESELLER_CONFIRM_PASSWORD
And User clicks on the button AgentportalMainAgentsButton
Then I should see WELCOME_MESSAGE_AGENT_PORTAL on the page AgentportalWelcomeText

@login-AgenPortal-ci-mtn
Scenario: Cannot log in if password is incorrect
Given Log Cannot log in if password is incorrect
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter SDEAL1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And System sleeps for 2000 seconds
And I enter INCORRECT_AGENTPORTAL_PASSWORD in the AgentportalPassword field
And System sleeps for 2000 seconds
And User clicks on the button AgentportalLoginButton
And System sleeps for 2000 seconds
Then I verify the popup text AgentPortalLoginFailedPopupText with the user text AGENTPORTAL_LOGIN_FAILED_MESSAGE on the page

@login-AgenPortal-ci-mtn
Scenario: Cannot log in if password is Short
Given Log Cannot log in if password is Short
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter SDEAL1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And I enter AGENTPORTAL_SHORT_LOGIN_PASSWORD in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
Then I verify the popup text AgentPortalLoginFailedPopupText with the user text AGENTPORTAL_LOGIN_FAILED_MESSAGE on the page

@login-AgenPortal-ci-mtn
Scenario: Cannot log with empty password
Given Log Cannot log with empty password
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I enter SDEAL1_ID in the AgentportalResellerId field
And I enter WEBADMIN_USER in the AgentportalUserId field
And User clicks on the button AgentportalLoginButton
Then I verify the popup text AgentPortalLoginFailedPopupText with the user text AGENTPORTAL_LOGIN_WITH_NO_PASSWORD_MESSAGE on the page