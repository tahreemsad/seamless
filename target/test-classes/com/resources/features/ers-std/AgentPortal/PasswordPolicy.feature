Feature:User checks the password policies

Scenario:Change to a too short password
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I select English from dropdown AgentportalLoginpagedropdown
And I enter dealer-1 in the AgentportalResellerId field
And I enter webadmin in the AgentportalUserId field
And I enter 20132013 in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 5 seconds
And User clicks on the button AgentPortalChangePasswordButton
And I enter 20132012 in the AgentPortalChangePasswordOldPwdfield field
And I enter 2013 in the AgentPortalChangePasswordNewPwdfield field
And I enter 2013 in the AgentPortalChangePasswordNewPwdfieldAgain field
And User clicks on the button AgentPortalPwdSubmitButton
Then I verify the error text AgentPortalShortPwdPopUp on the page


Scenario:Change to a too long password
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I select English from dropdown AgentportalLoginpagedropdown
And I enter dealer-1 in the AgentportalResellerId field
And I enter webadmin in the AgentportalUserId field
And I enter 20132013 in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 5 seconds
And User clicks on the button AgentPortalChangePasswordButton
And I enter 20132012 in the AgentPortalChangePasswordOldPwdfield field
And I enter 2013201220132012 in the AgentPortalChangePasswordNewPwdfield field
And I enter 2013201220132012 in the AgentPortalChangePasswordNewPwdfieldAgain field
And User clicks on the button AgentPortalPwdSubmitButton
Then I verify the error text AgentPortalShortPwdPopUp on the page


Scenario:Change to password with special characters
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I select English from dropdown AgentportalLoginpagedropdown
And I enter dealer-1 in the AgentportalResellerId field
And I enter webadmin in the AgentportalUserId field
And I enter 20132013 in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 5 seconds
And User clicks on the button AgentPortalChangePasswordButton
And I enter 20132012 in the AgentPortalChangePasswordOldPwdfield field
And I enter 2013!@#$ in the AgentPortalChangePasswordNewPwdfield field
And I enter 2013!@#$ in the AgentPortalChangePasswordNewPwdfieldAgain field
And User clicks on the button AgentPortalPwdSubmitButton
Then I verify the error text AgentPortalCorrectPwdPopUp on the page


Scenario:Password should be automatically blocked upon 3 wrong attempts
Given I browse to the Agentportal page
And System sleeps for 2000 seconds
And I select English from dropdown AgentportalLoginpagedropdown
And I enter subdealer-1-2 in the AgentportalResellerId field
And I enter webadmin in the AgentportalUserId field
And I enter 20132018 in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 1000 seconds
And User clicks on the button AgentPortalLoginFailedPopupButton
And User clicks on the button AgentportalLoginButton
And System sleeps for 1000 seconds
And User clicks on the button AgentPortalLoginFailedPopupButton
And User clicks on the button AgentportalLoginButton
And System sleeps for 1000 seconds
And User clicks on the button AgentPortalLoginFailedPopupButton
And I enter subdealer-1-2 in the AgentportalResellerId field after reset
And I enter webadmin in the AgentportalUserId field after reset
And I enter 20132013 in the AgentportalPassword field after reset
And User clicks on the button AgentportalLoginButton
Then I verify the error text AgentPortalLoginFailedPopupText on the page

Scenario:Password IncorrectAttempts flag should be reset if user only use wrong password twice
Given I browse to the Agentportal page
And System sleeps for 5 seconds
And I select English from dropdown AgentportalLoginpagedropdown
And I enter subdealer-1-3 in the AgentportalResellerId field
And I enter webadmin in the AgentportalUserId field
And I enter 20132017 in the AgentportalPassword field
And User clicks on the button AgentportalLoginButton
And System sleeps for 1000 seconds
And User clicks on the button AgentPortalLoginFailedPopupButton
And User clicks on the button AgentportalLoginButton
And System sleeps for 1000 seconds
And User clicks on the button AgentPortalLoginFailedPopupButton
And I enter subdealer-1-3 in the AgentportalResellerId field after reset
And I enter webadmin in the AgentportalUserId field after reset
And I enter 20132013 in the AgentportalPassword field after reset
And User clicks on the button AgentportalLoginButton
Then I verify the error text AgentPortalLoginFailedPopupText on the page