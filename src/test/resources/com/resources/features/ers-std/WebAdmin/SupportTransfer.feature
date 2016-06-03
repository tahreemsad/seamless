Feature:Support transfer from WebAdmin

@SupportTransfer-webadmin
Scenario: Support transfer by operator to distributor over web admin
Given I browse to the WebAdmin page
And I clear hashmap
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 2000 seconds
And User clicks on the button WebAdminMainMenuSupport
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSupportTransfer
And System sleeps for 5000 seconds
Given I verify the balance of the sender reseller OPERATOR_ID before the transaction
Given I verify the balance of the receiver reseller DIST1_ID before the transaction
And I enter OPERATOR_ID in the WebAdminMainMenuSupportTransferSenderIdNameField field
And I enter DIST1_ID in the WebAdminMainMenuSupportTransferRecieverIdNameField field
And I select ResellerAccountType from dropdown WebAdminMainMenuSupportTransferDropdown
And System sleeps for 3000 seconds
And I enter R2R_AMOUNT in the WebAdminMainMenuSupportTransferAmountNameField field
And System sleeps for 3000 seconds
And User clicks on the button WebAdminMainMenuSupportTransferContinueButton
And System sleeps for 6000 seconds
And User clicks on the button WebAdminMainMenuSupportTransferTransferButton
Then I should see TransferCompletedSuccessfullyText on the page WebAdminMainMenuSupportTransferTransferConfirmationPage
Then I verify the balance of the sender reseller OPERATOR_ID after the transaction of amount R2R_AMOUNT
Then I verify the balance of the receiver reseller DIST1_ID after the transaction of amount R2R_AMOUNT
And I clear hashmap

@SupportTransfer-webadmin
Scenario: Support transfer by distributor to sub-distributor over web admin
Given I browse to the WebAdmin page
And I clear hashmap
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 2000 seconds
And User clicks on the button WebAdminMainMenuSupport
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSupportTransfer
And System sleeps for 5000 seconds
Given I verify the balance of the sender reseller DIST1_ID before the transaction
Given I verify the balance of the receiver reseller SUBDIST1-1_ID before the transaction
And I enter DIST1_ID in the WebAdminMainMenuSupportTransferSenderIdNameField field
And I enter SUBDIST1-1_ID in the WebAdminMainMenuSupportTransferRecieverIdNameField field
And I select ResellerAccountType from dropdown WebAdminMainMenuSupportTransferDropdown
And System sleeps for 3000 seconds
And I enter R2R_AMOUNT in the WebAdminMainMenuSupportTransferAmountNameField field
And System sleeps for 3000 seconds
And User clicks on the button WebAdminMainMenuSupportTransferContinueButton
And System sleeps for 6000 seconds
And User clicks on the button WebAdminMainMenuSupportTransferTransferButton
Then I should see TransferCompletedSuccessfullyText on the page WebAdminMainMenuSupportTransferTransferConfirmationPage
Then I verify the balance of the sender reseller DIST1_ID after the transaction of amount R2R_AMOUNT
Then I verify the balance of the receiver reseller SUBDIST1-1_ID after the transaction of amount R2R_AMOUNT
And I clear hashmap

@SupportTransfer-webadmin
Scenario: Support transfer by sub-distributor to reseller over web admin
Given I browse to the WebAdmin page
And I clear hashmap
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 2000 seconds
And User clicks on the button WebAdminMainMenuSupport
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSupportTransfer
And System sleeps for 5000 seconds
Given I verify the balance of the sender reseller SUBDIST1-1_ID before the transaction
Given I verify the balance of the receiver reseller RESELLER-1-1-1_ID before the transaction
And I enter SUBDIST1-1_ID in the WebAdminMainMenuSupportTransferSenderIdNameField field
And I enter RESELLER-1-1-1_ID in the WebAdminMainMenuSupportTransferRecieverIdNameField field
And I select ResellerAccountType from dropdown WebAdminMainMenuSupportTransferDropdown
And System sleeps for 3000 seconds
And I enter R2R_AMOUNT in the WebAdminMainMenuSupportTransferAmountNameField field
And System sleeps for 3000 seconds
And User clicks on the button WebAdminMainMenuSupportTransferContinueButton
And System sleeps for 6000 seconds
And User clicks on the button WebAdminMainMenuSupportTransferTransferButton
Then I should see TransferCompletedSuccessfullyText on the page WebAdminMainMenuSupportTransferTransferConfirmationPage
Then I verify the balance of the sender reseller SUBDIST1-1_ID after the transaction of amount R2R_AMOUNT
Then I verify the balance of the receiver reseller RESELLER-1-1-1_ID after the transaction of amount R2R_AMOUNT
And I clear hashmap

@SupportTransfer-webadmin
Scenario: Support transfer by distributor to operator over web admin
Given I browse to the WebAdmin page
And I clear hashmap
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 2000 seconds
And User clicks on the button WebAdminMainMenuSupport
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSupportTransfer
And System sleeps for 5000 seconds
Given I verify the balance of the sender reseller DIST1_ID before the transaction
Given I verify the balance of the receiver reseller OPERATOR_ID before the transaction
And I enter DIST1_ID in the WebAdminMainMenuSupportTransferSenderIdNameField field
And I enter OPERATOR_ID in the WebAdminMainMenuSupportTransferRecieverIdNameField field
And I select ResellerAccountType from dropdown WebAdminMainMenuSupportTransferDropdown
And System sleeps for 3000 seconds
And I enter R2R_AMOUNT in the WebAdminMainMenuSupportTransferAmountNameField field
And System sleeps for 3000 seconds
And User clicks on the button WebAdminMainMenuSupportTransferContinueButton
And System sleeps for 6000 seconds
And User clicks on the button WebAdminMainMenuSupportTransferTransferButton
Then I should see TransferCompletedSuccessfullyText on the page WebAdminMainMenuSupportTransferTransferConfirmationPage
Then I verify the balance of the sender reseller DIST1_ID after the transaction of amount R2R_AMOUNT
Then I verify the balance of the receiver reseller OPERATOR_ID after the transaction of amount R2R_AMOUNT
And I clear hashmap

@SupportTransfer-webadmin
Scenario: Support transfer by reseller to sub-distributor over web admin
Given I browse to the WebAdmin page
And I clear hashmap
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 2000 seconds
And User clicks on the button WebAdminMainMenuSupport
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSupportTransfer
And System sleeps for 5000 seconds
Given I verify the balance of the sender reseller RESELLER-1-1-1_ID before the transaction
Given I verify the balance of the receiver reseller SUBDIST1-1_ID before the transaction
And I enter RESELLER-1-1-1_ID in the WebAdminMainMenuSupportTransferSenderIdNameField field
And I enter SUBDIST1-1_ID in the WebAdminMainMenuSupportTransferRecieverIdNameField field
And I select ResellerAccountType from dropdown WebAdminMainMenuSupportTransferDropdown
And System sleeps for 3000 seconds
And I enter R2R_AMOUNT in the WebAdminMainMenuSupportTransferAmountNameField field
And System sleeps for 3000 seconds
And User clicks on the button WebAdminMainMenuSupportTransferContinueButton
And System sleeps for 6000 seconds
And User clicks on the button WebAdminMainMenuSupportTransferTransferButton
Then I should see TransferCompletedSuccessfullyText on the page WebAdminMainMenuSupportTransferTransferConfirmationPage
Then I verify the balance of the sender reseller RESELLER-1-1-1_ID after the transaction of amount R2R_AMOUNT
Then I verify the balance of the receiver reseller SUBDIST1-1_ID after the transaction of amount R2R_AMOUNT
And I clear hashmap

@SupportTransfer-webadmin
Scenario: Support transfer by sub-distributor to distributor over web admin
Given I browse to the WebAdmin page
And I clear hashmap
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 2000 seconds
And User clicks on the button WebAdminMainMenuSupport
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSupportTransfer
And System sleeps for 5000 seconds
Given I verify the balance of the sender reseller SUBDIST1-1_ID before the transaction
Given I verify the balance of the receiver reseller DIST1_ID before the transaction
And I enter SUBDIST1-1_ID in the WebAdminMainMenuSupportTransferSenderIdNameField field
And I enter DIST1_ID in the WebAdminMainMenuSupportTransferRecieverIdNameField field
And I select ResellerAccountType from dropdown WebAdminMainMenuSupportTransferDropdown
And System sleeps for 3000 seconds
And I enter R2R_AMOUNT in the WebAdminMainMenuSupportTransferAmountNameField field
And System sleeps for 3000 seconds
And User clicks on the button WebAdminMainMenuSupportTransferContinueButton
And System sleeps for 6000 seconds
And User clicks on the button WebAdminMainMenuSupportTransferTransferButton
Then I should see TransferCompletedSuccessfullyText on the page WebAdminMainMenuSupportTransferTransferConfirmationPage
Then I verify the balance of the sender reseller SUBDIST1-1_ID after the transaction of amount R2R_AMOUNT
Then I verify the balance of the receiver reseller DIST1_ID after the transaction of amount R2R_AMOUNT
And I clear hashmap

###################################################################################################################################################################
#These test cases are specifically for CI-MTN customer as the hierarchy is totally different in CI-MTN, so we are going to write seprate test cases for this customer
@SupportTransfer-webadmin-ci-mtn
Scenario: Support transfer by operator to Strategic Dealer over web admin
Given I browse to the WebAdmin page
And I clear hashmap
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 2000 seconds
And User clicks on the button WebAdminMainMenuSupport
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSupportTransfer
And System sleeps for 5000 seconds
Given I verify the balance of the sender reseller OPERATOR_ID before the transaction
Given I verify the balance of the receiver reseller SDEAL1_ID before the transaction
And I enter OPERATOR_ID in the WebAdminMainMenuSupportTransferSenderIdNameField field
And I enter SDEAL1_ID in the WebAdminMainMenuSupportTransferRecieverIdNameField field
And I select ResellerAccountType from dropdown WebAdminMainMenuSupportTransferDropdown
And System sleeps for 3000 seconds
And I enter R2R_AMOUNT in the WebAdminMainMenuSupportTransferAmountNameField field
And System sleeps for 3000 seconds
And User clicks on the button WebAdminMainMenuSupportTransferContinueButton
And System sleeps for 6000 seconds
And User clicks on the button WebAdminMainMenuSupportTransferTransferButton
Then I should see TransferCompletedSuccessfullyText on the page WebAdminMainMenuSupportTransferTransferConfirmationPage
Then I verify the balance of the sender reseller OPERATOR_ID after the transaction of amount R2R_AMOUNT
Then I verify the balance of the receiver reseller SDEAL1_ID after the transaction of amount R2R_AMOUNT
And I clear hashmap