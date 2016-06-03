Feature: Check Reseller Balance through SMS 

@CheckResellerbalance-SMSapp
Scenario: Check Reseller Balance
Then I check the balance through smsapp from msisdn RESELLER-1-1-1_MSISDN having reseller id RESELLER-1-1-1_ID having curlmsg CURL_MESSAGE having pin RESELLER_PIN and in response i get my balance from the SMSAPP_LOG logs in log lines SMSAPP_LOG_ONE_LINE