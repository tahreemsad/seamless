package com.seamless.ers.qa.cucumber.ImplementationClasses.Common;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;

import cucumber.api.java.en.When;

public class RestartComponent {
	
	CucumberCommon common = new CucumberCommon();
	private static Logger log = LogManager.getLogger( RestartComponent.class );
	
	@When("^I restart the component (.+)$")
	public void restaringComponent(String componentName) throws JSchException, IOException{
		common.createSSHCoonection();
        ((ChannelExec) common.channel).setCommand(componentName+" restart");
	    log.info("Restarting "+componentName);    
        common.createChannel();
        log.info(common.Result);
        log.info(componentName+" Restarted");   
	}
}
