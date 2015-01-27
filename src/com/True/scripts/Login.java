package com.True.scripts;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.True.pagehelper.LoginHelper;
import com.True.util.DriverTestCase;
import com.True.util.ExecutionLog;

public class Login extends DriverTestCase
{	
	@Test
	public void testLogin() throws Exception
	{			
		//Initialize objects
		loginHelper = new LoginHelper(getWebDriver());		
		
			
		ExecutionLog.LogAddClass(this.getClass().getName() + " and Test method " +Thread.currentThread().getStackTrace()[1].getMethodName());
		try{
		
			
			//Open application
			getWebDriver().navigate().to(application_url);
			ExecutionLog.Log("Open application url");
			
			//verify login with face book
			loginHelper.verifyLoginWithFacebook();
			
			//click on login button
			loginHelper.clickOnLoginBtn();
			
			//verify email required error message
			loginHelper.verifyEmailErrormessage();
			
			//verify password required error message
			loginHelper.verifyPasswordErrormessage();
			
			//verify forget Your password 
			loginHelper.verifyForgetYourPassword();
			
			//click on Term of Service
			loginHelper.clickOnTermOfService();
			
			//verify Term of Service
			loginHelper.verifyTermOfService();
			
			//click on Privacy Policy
			loginHelper.clickOnPrivacyPolicies();
			
			//verify Privacy Policy
			loginHelper.verifyPrivacyPolicies();
			
			//enter User name 
			loginHelper.enterUserName("qa.qatests");
			
			//click on login button
			loginHelper.clickOnLoginBtn();
			
			//verify invalid email error message
			loginHelper.enterInvalidErrormsg();
			
			
			//enter User name 
			loginHelper.enterUserName("qa.qatests@outlook.com");
			
			//enter Password
			loginHelper.enterPassword("1234");
			
			//verify Invalid Password message
			loginHelper.verifyinvalidPassword();
			
			//click on login button
			loginHelper.clickOnLoginBtn();
			
			
		}
		 
		catch (Error e) {
			captureScreenshot("testLogin");	
			ExecutionLog.LogErrorMessage(e);			
			throw e;
		} 
		catch(Exception e) {
			captureScreenshot("testLogin");
			ExecutionLog.LogExceptionMessage(e);			
			throw e;
		 }		
	}
	
	@AfterMethod
	public void endMethods() throws Exception
	{		
		ExecutionLog.LogEndClass(this.getClass().getName());
	}

}
