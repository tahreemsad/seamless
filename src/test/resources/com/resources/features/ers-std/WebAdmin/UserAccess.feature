Feature:User Access

Scenario:User root has the right access
Given I browse to the WebAdmin page
And I enter root in the WebAdminLoginUserNameField field
And I enter root in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 1000 seconds
Then I should see Info on the page
Then I should see Support on the page
Then I should see Reports on the page
Then I should see Invoices on the page
Then I should see Operators on the page
Then I should see Stock on the page
Then I should see Accounts on the page
Then I should see Admin on the page
Then I should see System on the page
Then I should see My account on the page
And System sleeps for 2000 seconds


Scenario:Webadminuser marketing has the right access
Given I browse to the WebAdmin page
And I enter root in the WebAdminLoginUserNameField field
And I enter root in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSystem
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSystemUsers
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSystemUsersUsers
And User clicks on the button WebAdminMainMenuSystemUsersUsersNewUserButton
And I enter marketingusr in the WebAdminMainMenuSystemUsersUsersUserIDfield field
And I enter market1234 in the WebAdminMainMenuSystemUsersUsersPasswordfield field
And I select Marketing from dropdown WebAdminMainMenuSystemUsersUsersRoledropdown
And System sleeps for 1500 seconds
And User clicks on the button WebAdminMainMenuSystemUsersUsersCreateUserButton
And User clicks on the button WebAdminLogoutLink
And System sleeps for 1000 seconds
And I enter marketingusr in the WebAdminLoginUserNameField field
And I enter market1234 in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 1000 seconds
Then I should not see Info on the page
Then I should not see Support on the page
Then I should see Reports on the page
Then I should not see Invoices on the page
Then I should not see Operators on the page
Then I should not see Stock on the page
Then I should not see Accounts on the page
Then I should not see Admin on the page
Then I should not see System on the page
Then I should see My account on the page
And System sleeps for 2000 seconds


Scenario:Webadminuser sales has the right access
Given I browse to the WebAdmin page
And I enter root in the WebAdminLoginUserNameField field
And I enter root in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSystem
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSystemUsers
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSystemUsersUsers
And User clicks on the button WebAdminMainMenuSystemUsersUsersNewUserButton
And I enter salesuser in the WebAdminMainMenuSystemUsersUsersUserIDfield field
And I enter sale1234 in the WebAdminMainMenuSystemUsersUsersPasswordfield field
And I select Sales from dropdown WebAdminMainMenuSystemUsersUsersRoledropdown
And System sleeps for 1500 seconds
And User clicks on the button WebAdminMainMenuSystemUsersUsersCreateUserButton
And User clicks on the button WebAdminLogoutLink
And System sleeps for 1000 seconds
And I enter salesuser in the WebAdminLoginUserNameField field
And I enter sale1234 in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 1000 seconds
Then I should not see Info on the page
Then I should not see Support on the page
Then I should see Reports on the page
Then I should not see Invoices on the page
Then I should not see Operators on the page
Then I should not see Stock on the page
Then I should not see Accounts on the page
Then I should not see Admin on the page
Then I should not see System on the page
Then I should see My account on the page
And System sleeps for 2000 seconds


Scenario:Webadminuser finance has the right access
Given I browse to the WebAdmin page
And I enter root in the WebAdminLoginUserNameField field
And I enter root in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSystem
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSystemUsers
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSystemUsersUsers
And User clicks on the button WebAdminMainMenuSystemUsersUsersNewUserButton
And I enter finance in the WebAdminMainMenuSystemUsersUsersUserIDfield field
And I enter finance1234 in the WebAdminMainMenuSystemUsersUsersPasswordfield field
And I select Finance from dropdown WebAdminMainMenuSystemUsersUsersRoledropdown
And System sleeps for 1500 seconds
And User clicks on the button WebAdminMainMenuSystemUsersUsersCreateUserButton
And User clicks on the button WebAdminLogoutLink
And System sleeps for 1000 seconds
And I enter finance in the WebAdminLoginUserNameField field
And I enter finance1234 in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 1000 seconds
Then I should not see Info on the page
Then I should see Support on the page
Then I should see Reports on the page
Then I should not see Invoices on the page
Then I should not see Operators on the page
Then I should not see Stock on the page
Then I should not see Accounts on the page
Then I should not see Admin on the page
Then I should not see System on the page
Then I should see My account on the page
And System sleeps for 2000 seconds


Scenario:Webadminuser front_office has the right access
Given I browse to the WebAdmin page
And I enter root in the WebAdminLoginUserNameField field
And I enter root in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSystem
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSystemUsers
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSystemUsersUsers
And User clicks on the button WebAdminMainMenuSystemUsersUsersNewUserButton
And I enter frontoffice in the WebAdminMainMenuSystemUsersUsersUserIDfield field
And I enter fofc1234 in the WebAdminMainMenuSystemUsersUsersPasswordfield field
And I select Front Office from dropdown WebAdminMainMenuSystemUsersUsersRoledropdown
And System sleeps for 1500 seconds
And User clicks on the button WebAdminMainMenuSystemUsersUsersCreateUserButton
And User clicks on the button WebAdminLogoutLink
And System sleeps for 1000 seconds
And I enter frontoffice in the WebAdminLoginUserNameField field
And I enter fofc1234 in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 1000 seconds
Then I should not see Info on the page
Then I should see Support on the page
Then I should see Reports on the page
Then I should not see Invoices on the page
Then I should not see Operators on the page
Then I should not see Stock on the page
Then I should not see Accounts on the page
Then I should not see Admin on the page
Then I should not see System on the page
Then I should see My account on the page
And System sleeps for 2000 seconds


Scenario:Webadminuser back_office has the right access
Given I browse to the WebAdmin page
And I enter root in the WebAdminLoginUserNameField field
And I enter root in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSystem
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSystemUsers
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSystemUsersUsers
And User clicks on the button WebAdminMainMenuSystemUsersUsersNewUserButton
And I enter backoffice in the WebAdminMainMenuSystemUsersUsersUserIDfield field
And I enter bofc1234 in the WebAdminMainMenuSystemUsersUsersPasswordfield field
And I select Back Office from dropdown WebAdminMainMenuSystemUsersUsersRoledropdown
And System sleeps for 1500 seconds
And User clicks on the button WebAdminMainMenuSystemUsersUsersCreateUserButton
And User clicks on the button WebAdminLogoutLink
And System sleeps for 1000 seconds
And I enter backoffice in the WebAdminLoginUserNameField field
And I enter bofc1234 in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 1000 seconds
Then I should not see Info on the page
Then I should see Support on the page
Then I should see Reports on the page
Then I should not see Invoices on the page
Then I should not see Operators on the page
Then I should not see Stock on the page
Then I should not see Accounts on the page
Then I should not see Admin on the page
Then I should not see System on the page
Then I should see My account on the page
And System sleeps for 2000 seconds


Scenario:Prosys user cannot log in to webadmin
Given I browse to the WebAdmin page
And I enter root in the WebAdminLoginUserNameField field
And I enter root in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSystem
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSystemUsers
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSystemUsersUsers
And User clicks on the button WebAdminMainMenuSystemUsersUsersNewUserButton
And I enter prosys in the WebAdminMainMenuSystemUsersUsersUserIDfield field
And I enter psys1234 in the WebAdminMainMenuSystemUsersUsersPasswordfield field
And I select _internal_ws from dropdown WebAdminMainMenuSystemUsersUsersRoledropdown
And System sleeps for 1500 seconds
And User clicks on the button WebAdminMainMenuSystemUsersUsersCreateUserButton
And User clicks on the button WebAdminLogoutLink
And System sleeps for 1000 seconds
And I enter prosys in the WebAdminLoginUserNameField field
And I enter psys1234 in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 1000 seconds
Then I verify the error text WebAdminRoleLoginError on the page

