Feature: System Import Batch R2R

@BatchR2R-WebAdmin-ng_glo
Scenario: Root User creates a BatchR2R import definition and Import a batch file
Given I browse to the WebAdmin page
And I enter ROOT_USER_USERMAME in the WebAdminLoginUserNameField field
And I enter ROOT_USER_PASSWORD in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 5000 seconds
And I should see InfoPageText on the page WebAdminWelcomeInfoText
And User clicks on the button WebAdminMainMenuAdmin
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSystem
And System sleeps for 3000 seconds
And User clicks on the button WebAdminMainMenuSystemImport
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSystemImportImport
#And User clicks on the button WebAdminMainMenuSystemImportDefinitions
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSystemImportImportSubmit
#And I select billing_defination from dropdown WebAdminMainMenuSystemImportDefinitionsdropdown
And System sleeps for 1000 seconds
And I select ImportDefinition from dropdown WebAdminMainMenuSystemImportImportSubmitDropdown
#And User clicks on the button WebAdminMainMenuSystemImportDefinitionsAdd
And System sleeps for 1000 seconds
#And I enter BatchR2R in the WebAdminMainMenuSystemImportDefinitionsAddBatchR2RName field
#And I enter BatchR2RDes in the WebAdminMainMenuSystemImportDefinitionsAddBatchR2RDes field
#And I enter BatchR2RFileLevel in the WebAdminMainMenuSystemImportDefinitionsAddBatchR2RFileLevel field
#And I select Formatter from dropdown WebAdminMainMenuSystemImportDefinitionsFormatterdropdown
And User clicks on the button WebAdminMainMenuSystemImportImportBrowseFile
And User selects file BatchR2R_Import_FILE from WebAdminMainMenuSystemImportImportBrowseFile
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuSystemImportImportBrowseFileSubmitButton
And System sleeps for 5000 seconds
And User clicks on the button WebAdminMainMenuSystemImportImportBrowseFileRefreshButton
And System sleeps for 5000 seconds