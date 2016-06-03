# ers-std-cucumber-automation


## Overview

Test Automation source code for Standard ERS Components

## Configuration

N/A

## Usage

##### Run with Command Line Arguments:

mvn clean install -DtargetEnvironment=<TARGET IP> for e.g. mvn clean install -DtargetEnvironment=10.10.10.10


##### Run Without Command Line Arguments

Open the project in Eclipse. Go to POM.xml -> Overview Tab -> Properties

Double Click on "targetEnvironment" property and change the value to your desired IP address.

Run "mvn clean package" from your terminal

------------------------------------------------------------------

You can also run a specific scenario by using the followig command

mvn test -DtargetEnvironment="10.10.4.67" -Dcustomer="ci_mtn" -Dcucumber.options="--tags @R2R-TransferService-NotAllowed"

Where @R2R-TransferService-NotAllowed" is a tag defined for each Scenario in .feature file

How to run multiple test cases in single command?

mvn test -DtargetEnvironment="10.10.4.67" -Dcustomer="ci_mtn" -Dcucumber.options="--tags @VoS-USSD-RevokeProduct,@VoS-USSD-RevokeProduct_SELL,@VoS-USSD-UNRevokeProduct"

Make sure there is no space between tag and comma

