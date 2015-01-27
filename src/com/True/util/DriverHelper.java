package com.True.util;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.FindElements;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.thoughtworks.selenium.Selenium;

public abstract class DriverHelper {
	// Define objects
	protected WebDriver driver;
	private Selenium selenium;

	
    
	// Declare objects
	public DriverHelper(WebDriver webdriver) {
		driver = webdriver;
		// selenium = new WebDriverBackedSelenium(driver, "");
	}

	// Return web driver object
	public WebDriver getWebDriver() {
		return driver;
	}

	// Return selenium object
	public Selenium getSelenium() {
		return selenium;
	}

	// Print message on screen
	public void Log(String logMsg) {
		System.out.println(logMsg);
	}

	// Handle locator type
	public By ByLocator(String locator) {
		By result = null;

		if (locator.startsWith("//")) {
			result = By.xpath(locator);
		} else if (locator.startsWith("css=")) {
			result = By.cssSelector(locator.replace("css=", ""));
		} else if (locator.startsWith("#")) {
			result = By.name(locator.replace("#", ""));
		} else if (locator.startsWith("link=")) {
			result = By.linkText(locator.replace("link=", ""));
		} else {
			result = By.id(locator.replace("id=", ""));
		}
		return result;
	}

	// Assert element present
	public Boolean isElementPresent(String locator) {
		Boolean result = false;
		try {
			getWebDriver().findElement(ByLocator(locator));
			result = true;
		} catch (Exception ex) {
		}
		return result;
	}

	// Wait for element present
	public void WaitForElementPresent(String locator, int timeout) {
		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				break;
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Wait for element not present
	public void WaitForElementNotPresent(String locator, int timeout) {
		for (int i = 0; i < timeout; i++) {
			if (!isElementPresent(locator)) {
				break;
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Wait for element enabled
	public void WaitForElementEnabled(String locator, int timeout) {
		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				if (getWebDriver().findElement(ByLocator(locator)).isEnabled()) {
					break;
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Wait for element not enabled
	public void WaitForElementNotEnabled(String locator, int timeout) {
		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				if (!getWebDriver().findElement(ByLocator(locator)).isEnabled()) {
					break;
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Wait for element visible
	public void WaitForElementVisible(String locator, int timeout) {
		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				if (getWebDriver().findElement(ByLocator(locator))
						.isDisplayed()) {
					break;
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Wait for element not visible
	public void WaitForElementNotVisible(String locator, int timeout) {
		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				if (!getWebDriver().findElement(ByLocator(locator))
						.isDisplayed()) {
					break;
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Wait for text present
	public void WaitForTextPresent(String locator, String text, int timeout) {
		WaitForElementPresent(locator, timeout);
		for (int i = 0; i < timeout; i++) {
			if (isTextPresent(locator, text)) {
				break;
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Handle mouse over action
	public void mouseOver(String locator) {
		this.WaitForElementPresent(locator, 50);
		WebElement el = getWebDriver().findElement(ByLocator(locator));

		// build and perform the mouseOver with Advanced User Interactions API
		Actions builder = new Actions(getWebDriver());
		builder.moveToElement(el).build().perform();
	}

	// Handle mouse double click action
	public void mouseDoubleClick(String locator) {
		this.WaitForElementPresent(locator, 50);
		WebElement el = getWebDriver().findElement(ByLocator(locator));

		// build and perform the mouse click with Advanced User Interactions API
		Actions builder = new Actions(getWebDriver());
		builder.doubleClick(el).perform();
	}

	// Handle drag and drop action
	public void dragAndDrop(String draggable, String to) {
		this.WaitForElementPresent(draggable, 50);
		this.WaitForElementPresent(to, 50);
		WebElement elDraggable = getWebDriver().findElement(
				ByLocator(draggable));
		WebElement todrag = getWebDriver().findElement(ByLocator(to));

		// build and perform drag and drop with Advanced User Interactions API
		Actions builder = new Actions(getWebDriver());
		builder.dragAndDrop(elDraggable, todrag).perform();
	}

	// Handle click action
	public void clickOn(String locator) {
		this.WaitForElementPresent(locator, 30);
		Assert.assertTrue(isElementPresent(locator), "Element Locator :"
				+ locator + " Not found");
		WebElement el = getWebDriver().findElement(ByLocator(locator));
		el.click();
	}

	// Handle send keys action
	public void sendKeys(String locator, String text) {
		this.WaitForElementPresent(locator, 30);
		Assert.assertTrue(isElementPresent(locator), "Element Locator :" + locator + " Not found");
		WebElement el = getWebDriver().findElement(ByLocator(locator));
		el.clear();
		el.sendKeys(text);
	}

	// Handle send keys action
	public void typeText(String locator, String text) {
		this.WaitForElementPresent(locator, 30);
		Assert.assertTrue(isElementPresent(locator), "Element Locator :" + locator + " Not found");
		WebElement el = getWebDriver().findElement(ByLocator(locator));
		el.sendKeys(text);
	}

	// Select value from drop down
	public void selectDropDown(String locator, String targetValue) {
		Assert.assertTrue(isElementPresent(locator), "Element Locator :"
				+ locator + " Not found");
		this.WaitForElementPresent(locator, 20);
		new Select(getWebDriver().findElement(ByLocator(locator)))
		.selectByVisibleText(targetValue);

	}

	// Assert text present
	public boolean isTextPresent(String locator, String str) {
		Assert.assertTrue(isElementPresent(locator), "Element Locator :"
				+ locator + " Not found");
		String message = getWebDriver().findElement(ByLocator(locator))
				.getText();
		if (message.contains(str)) {
			return true;
		} else {
			return false;
		}
	}

	// Store text from a locatorl
	public String getText(String locator) {
		WaitForElementPresent(locator, 20);
		Assert.assertTrue(isElementPresent(locator), "Element Locator :"
				+ locator + " Not found");
		String text = getWebDriver().findElement(ByLocator(locator)).getText();
		return text;
	}

	// Assert check box selected
	public boolean isChecked(String locator) {
		boolean checkStatus = false;
		WaitForElementPresent(locator, 20);
		Assert.assertTrue(isElementPresent(locator), "Element Locator :"
				+ locator + " Not found");
		WebElement el = getWebDriver().findElement(ByLocator(locator));
		checkStatus = el.isSelected();
		return checkStatus;
	}

	// Get attribute value
	public String getAttribute(String locator, String attribute) {
		WaitForElementPresent(locator, 20);
		Assert.assertTrue(isElementPresent(locator), "Element Locator :"
				+ locator + " Not found");
		String text = getWebDriver().findElement(ByLocator(locator))
				.getAttribute(attribute);
		return text;
	}

	// Get integer value
	public Integer getInt(String strString) {
		Pattern intsOnly = Pattern.compile("\\d+");
		String input = strString;
		Matcher makeMatch = intsOnly.matcher(input);
		makeMatch.find();
		String digitStr = makeMatch.group();
		Integer digit = Integer.parseInt(digitStr);
		return digit;
	}

	// Execute java script
	public void javaScriptExecute(String javascrpt) {
		JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
		js.executeScript(javascrpt);
	}

	public void acceptAlert() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Alert alert = getWebDriver().switchTo().alert();
		alert.accept();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Verify text present
	public void verifyTextPresent(String text)
	{
        switchwindow();
		waitForWorkAroundTime(5000);
		
		boolean result = driver.findElement(ByLocator("//body")).getText().contains(text);
		Assert.assertTrue(result);
		//return result;
	}
	
	public void switchwindow()
	{

        String winHandleBefore = driver.getWindowHandle();

        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
	}

	public void selectWindowWithTitle()
	{

		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("window.document.getElementById('button-delete').click()");
	}

	public String getCurrentURL()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//String a = js.executeScript("window.location.href");
		String URL = (String) js.executeScript("window.document.URL.substr()");
		return URL;
	}

	public Integer getXpathCount(String locator)
	{
		WaitForElementPresent(locator, 30);
		Assert.assertTrue(isElementPresent(locator), "Element Locator :"
				+ locator + " Not found");
		int a = driver.findElements(By.xpath(locator)).size();
		return a;
	}

	public void refreshCurrentPage()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("location.reload()");
	}

	public void waitForWorkAroundTime(int timeout)
	{
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void selectFrame(String locator)
	{
		waitForWorkAroundTime(5000);
		getWebDriver().switchTo().frame(getWebDriver().findElement(ByLocator(locator)));
	}
	
	public void clickOnElements(String locator, int index)
	{
		List<WebElement> el = getWebDriver().findElements(ByLocator(locator));
		System.out.println("Total element found with Locator: "+ locator);
		el.get(index).click();
	}
	
	public void typeOnElements(String locator, int index, String text)
	{
		List<WebElement> el = getWebDriver().findElements(ByLocator(locator));
		System.out.println("Total element found with Locator: "+ locator);
		el.get(index).sendKeys(text);
	}



}
