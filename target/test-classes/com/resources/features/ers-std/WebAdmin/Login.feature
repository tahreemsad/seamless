Feature:Login from WebAdmin

@Correct-Login-WebAdmin @Correct-Login-WebAdmin-ci-mtn
Scenario:Web Admin is running and Root can login with correct password
Given I browse to the WebAdmin page
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 5000 seconds
Then I should see InfoPageText on the page WebAdminWelcomeInfoText

@InCorrect-Login-WebAdmin @InCorrect-Login-WebAdmin-ci-mtn
Scenario:Root cannot loginwith incorrect password
Given I browse to the WebAdmin page
And System sleeps for 5000 seconds
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter INVALID_ROOT_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 5000 seconds
Then I should see LoginFailed on the page WebAdminLoginErrorPopUp

@Correct-Login-WebAdmin @Correct-Login-WebAdmin-ci-mtn
Scenario:Webadminuser root can create other users
Given I browse to the WebAdmin page
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSystem
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSystemUsers
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSystemUsersUsers
And User clicks on the button WebAdminMainMenuSystemUsersUsersNewUserButton
And I create a new admin user and input it in the textfield with the xpath WebAdminMainMenuSystemUsersUsersUserIDfield 
And I enter ADMIN_USER_PASSWORD in the WebAdminMainMenuSystemUsersUsersPasswordfield field
And I select WEB_ADMIN_ROLE from dropdown WebAdminMainMenuSystemUsersUsersRoledropdown
And System sleeps for 5000 seconds
And User clicks on the button WebAdminMainMenuSystemUsersUsersCreateUserButton
And System sleeps for 5000 seconds
Then I should see WEB_ADMIN_ROLE on the page WebAdminUserRole