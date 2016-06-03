import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		monochrome = true,
        format = { "pretty","html:target/cucumber" },
        tags   = {
				    	"@R2R-AgentPortal"
				    +  	"@R2S-AgentPortal,"
			      	+	"@NotAllowedTrans-AgentPortal,"
				  	+	"@ContractsVoilation-AgentPortal,"
				  	+	"@login-AgenPortal,"
					+	"@SearchTrans-AgentPortal,"
 					+	"@Subscriber-registration-USSD"
					+	"@ResellerRegistration-USSD" 
 					+	"@R2S-USSD,"
					+	"@R2R-USSD1"
   		   			+	"@LastTransaction-USSD1"
 					+	"@CheckResellerbalance-USSD"
					+	"@P2P-USSD"
					+   "@PinChange-USSD,"
				 	+	"@viewussdmenu"
					+   "@P2P-Transfer-SMSapp,"
					+   "@Subscriber-ChangePassword-SMSapp,"
				    +   "@Subscriber-Registration-SMSapp,"
				    +   "@R2R-Through-SMSapp,"
				    +   "@R2S-Through-SMSapp,"
					+   "@CheckResellerbalance-SMSapp,"
					+	"@Reseller-ChangePassword-SMSapp,"
					+   "@R2S-TopupService-Allowed,"
				    +   "@R2S-TopupService-NotAllowed,"
				    +   "@R2R-TransferService-Allowed,"
				    +   "@R2R-TransferService-NotAllowed,"
			        +	"@Correct-Login-WebAdmin,"
				    +   "@SearchTransaction-WebAdmin,"
//				    +	"@MM2ERS-Allowed,"
//					+	"@MM2ERS-NotAllowed,"
//				    +	"@CahsIn-Valid-USSD,"
//					+	"@CahsIn-InValid-USSD,"
				    +   "@AnonymousID-USSD,"
				    +    "@Subscriber-anonymousId-SMSapp"
				
				},
        features = {"."})
public class RunTest {

}