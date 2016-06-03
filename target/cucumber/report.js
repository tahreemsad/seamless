$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/com/resources/features/ers-std/USSD/AtlassianIntegration.feature");
formatter.feature({
  "line": 1,
  "name": "Request to integrate atlassian with zephyr",
  "description": "",
  "id": "request-to-integrate-atlassian-with-zephyr",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 4,
  "name": "User preforms to integrate with zephyr",
  "description": "",
  "id": "request-to-integrate-atlassian-with-zephyr;user-preforms-to-integrate-with-zephyr",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 3,
      "name": "@integrate"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "I Perform to integrate my automation with zephyr",
  "keyword": "When "
});
formatter.match({
  "location": "AtlassianIntegration.hitCurl()"
});
formatter.result({
  "duration": 840261000,
  "error_message": "java.lang.RuntimeException: Server returned HTTP response code: 403 for URL: http://192.168.99.100:32768/flex/services/rest/latest/execution/2\n\tat com.seamless.ers.qa.cucumber.ImplementationClasses.USSD.AtlassianIntegration.AuthGet(AtlassianIntegration.java:131)\n\tat com.seamless.ers.qa.cucumber.ImplementationClasses.USSD.AtlassianIntegration.hitCurl(AtlassianIntegration.java:35)\n\tat ✽.When I Perform to integrate my automation with zephyr(src/test/resources/com/resources/features/ers-std/USSD/AtlassianIntegration.feature:5)\n",
  "status": "failed"
});
formatter.after({
  "duration": 3030635000,
  "status": "passed"
});
});