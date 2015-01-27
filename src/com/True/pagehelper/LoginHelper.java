package com.True.pagehelper;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.True.locators.LocatorReader;
import com.True.util.DriverHelper;
import com.gargoylesoftware.htmlunit.javascript.host.Navigator;

public class LoginHelper extends DriverHelper 
{
	private LocatorReader loginLocator;	

	
	public LoginHelper(WebDriver driver) {
		super(driver);
		loginLocator = new LocatorReader("Login.xml");
	}


	public void enterUserName(String user)
	{
		String locator = loginLocator.getLocator("userName");
		sendKeys(locator,user);
	}


	public void enterPassword(String pass) 
	{
		String locator = loginLocator.getLocator("password");
		sendKeys(locator,pass);
		
	}


	public void clickOnLoginBtn() 
	{
		String locator = loginLocator.getLocator("loginBtn");
		clickOn(locator);
		waitForWorkAroundTime(1500);
	}


	public void verifyEmailErrormessage()
	{
		String locator = loginLocator.getLocator("emailError");
		Assert.assertTrue(isElementPresent(locator));
	}


	public void verifyPasswordErrormessage() 
	{
		String locator = loginLocator.getLocator("passwordErrorMsg");
		Assert.assertTrue(isElementPresent(locator));
	}

	public void verifyForgetYourPassword()
	{
		String locator = loginLocator.getLocator("forgetYourPassword");
		Assert.assertTrue(isElementPresent(locator));
	}


	public void clickOnTermOfService()
	{
		String locator = loginLocator.getLocator("termOfService");
		clickOn(locator);
		waitForWorkAroundTime(4000);
	}


	public void verifyTermOfService() 
	{
		String locator = loginLocator.getLocator("verifyTermofService");
		String text = getText(locator);
		Assert.assertTrue(text.contains("Term Of Services"));
		getWebDriver().navigate().back();
		waitForWorkAroundTime(4000);
	}


	public void clickOnPrivacyPolicies() 
	{
		String locator = loginLocator.getLocator("privacyPolicy");
		clickOn(locator);	
		waitForWorkAroundTime(4000);
	}


	public void verifyPrivacyPolicies() 
	{
		String locator = loginLocator.getLocator("verifyPrivacyPolicy");
		String text = getText(locator);
		Assert.assertTrue(text.contains("Privacy Policy"));
		getWebDriver().navigate().back();
		waitForWorkAroundTime(7000);
		refreshCurrentPage();
	}


	public void verifyLoginWithFacebook() 
	{
		String locator = loginLocator.getLocator("verifyPrivacyPolicy");
		Assert.assertTrue(isElementPresent(locator));
	}


	public void enterInvalidErrormsg()
	{
		String locator = loginLocator.getLocator("emailError");
		Assert.assertTrue(isElementPresent(locator));
	}


	public void verifyinvalidPassword()
	{
		String locator = loginLocator.getLocator("invalidPasswordMsg");
		Assert.assertTrue(isElementPresent(locator));	
	}

	
}
