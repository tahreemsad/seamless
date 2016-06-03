Feature:Create campaign with percentage bonus


Scenario: Root User creates a campaign with percentage bounus
Given I browse to the WebAdmin page
And I enter root in the WebAdminLoginUserNameField field
And I enter root in the WebAdminLoginPasswordField field
And User clicks on the button WebAdminLoginButton
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuAdmin
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuAdminBonusManagement
And System sleeps for 10000 seconds
Then I switch to frame
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuAdminBonusManagementAddNewCampaign
And System sleeps for 5000 seconds
And I enter TestAutomation1 in the WebAdminMainMenuAdminBonusManagementCampNamefield field
And I enter TestAutomation1 in the WebAdminMainMenuAdminBonusManagementCampDescriptionfield field
And User clicks on the button Date_picker_start_textbox
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuAdminBonusManagementCampStartDateDropDown
And System sleeps for 1000 seconds
And User clicks on the button Date_picker_end_textbox
And System sleeps for 1000 seconds
And User clicks on the button WebAdminMainMenuAdminBonusManagementCampEndDateDropDown
And System sleeps for 1000 seconds
And I select Reseller from dropdown WebAdminMainMenuAdminBonusManagementCampSenderLevelDropDown
And I select End Subscriber from dropdown WebAdminMainMenuAdminBonusManagementCampReceiverLevelDropDown
And I select USSD from dropdown WebAdminMainMenuAdminBonusManagementCampChannelDropDown
And I enter 100 in the WebAdminMainMenuAdminBonusManagementCampTargetfield field
And I select Amount Sum from dropdown WebAdminMainMenuAdminBonusManagementCampTargetTypeDropDown
And I enter 5 in the WebAdminMainMenuAdminBonusManagementCampPromotionAmountfield field
And I select Percentage from dropdown WebAdminMainMenuAdminBonusManagementCampPromotionTypeDropDown
And User clicks on the button WebAdminMainMenuAdminBonusManagementCampSubmitButton
And System sleeps for 5000 seconds