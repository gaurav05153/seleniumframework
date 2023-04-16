package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

import objectManager.DriverManager;

public class Action 
{
	
	WebDriver driver;
	JavascriptExecutor exec;
	
	public Action()
	{
		this.driver=DriverManager.getDriver();
		exec = (JavascriptExecutor)driver;
	}
	//Clicking button
	public void clickButton(WebElement ele,String eleName) throws Exception
	{
			Utility.waitForElementVisibleAndClickable(driver,ele,30);
			Utility.highlightElement(driver, ele);
			ele.click();
			Logging.info("Button clicked - "+eleName);
	}
	//Entering text in text box
	public void sendKeys(WebElement ele, String testData,String eleName) throws InterruptedException
	{
			Utility.waitForVisibilityOfElement(driver,ele);
			Utility.highlightElement(driver, ele);
			ele.clear();
			ele.sendKeys(testData);
			Logging.info("Text entered - "+eleName);
	}
	//Clicking image
	public void clickImage(WebElement ele,String eleName) throws InterruptedException
	{
	     Utility.waitForVisibilityOfElement(driver,ele);
	     Utility.highlightElement(driver, ele);
	     ele.click();
	     Logging.info("Image clicked - "+eleName);
	}
	//Clicking hyperlink
	public void clickLink(WebElement ele,String eleName) throws InterruptedException
	{
	     Utility.waitForVisibilityOfElement(driver,ele);
	     Utility.highlightElement(driver, ele);
	     ele.click();
	     Logging.info("Link clicked - "+eleName);
	}
	//Button click using javaScript
	public void jsClickButton(WebElement ele,String eleName)
	{
		exec.executeScript("arguments[0].click", ele);	
		Logging.info("Button clicked - "+eleName);
	}
	//Tab press
	public void pressTab(WebElement ele)
	{
		ele.sendKeys(Keys.TAB);
		Logging.info("Tab pressed");
	}

    //Getting the text
    public String getText(WebElement ele) throws InterruptedException
    {
        Utility.waitForVisibilityOfElement(driver,ele);
        String actualText = ele.getText();
        Logging.info("Actual Text  - "+actualText);
        return actualText;
    }
}
