Feature:Search reseller

Scenario:User login to webadmin and Search reseller
Given I browse to the WebAdmin page
And System sleeps for 5000 seconds
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 5000 seconds
And User clicks on the button WebAdminMainMenuSupport
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSupportResellers
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSupportResellersResellerSearchButton
And System sleeps for 5000 seconds
Then I should see DIST1_ID on the page WebAdminSDEAL1XpathSearchResult

###################################################################################################################################################################
#These test cases are specifically for CI-MTN customer as the hierarchy is totally different in CI-MTN, so we are going to write seprate test cases for this customer

@ci-mtn-searchReseller
Scenario:User login to webadmin and Search reseller
Given I browse to the WebAdmin page
And System sleeps for 5000 seconds
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 5000 seconds
And User clicks on the button WebAdminMainMenuSupport
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSupportResellers
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSupportResellersResellerSearchButton
And System sleeps for 5000 seconds
Then I should see SDEAL1_ID on the page WebAdminSDEAL1XpathSearchResult