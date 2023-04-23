package utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import dataProvider.ConfigFileReader;

public class Utility 
{
	WebDriver driver;
	
	//Waiting until element is visibility
	public static WebElement waitForVisibilityOfElement(WebDriver driver, WebElement ele)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5))
	           .ignoring(NoSuchElementException.class,StaleElementReferenceException.class);    
	     wait.until(ExpectedConditions.visibilityOf(ele));
	     return ele;   
	}
	//Waiting until element is clickable
	public static WebElement waitForElementToBoClickable(WebDriver driver,WebElement ele)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5))
	        .ignoring(NoSuchElementException.class,StaleElementReferenceException.class); 
	  wait.until(ExpectedConditions.elementToBeClickable(ele));
	  return ele;
	}

	//Highlighting element
	public static void highlightElement(WebDriver driver,WebElement ele) throws InterruptedException
	{
	     JavascriptExecutor js = (JavascriptExecutor)driver;
	     for (int i = 0; i <3; i++)
	     {
	           js.executeScript("arguments[0].style.border='4px solid red'", ele);
	           Thread.sleep(100);
	           js.executeScript("arguments[0].style.border=''",ele);                
	       }
	}
	//Waiting until element is visible and clickable
	public static void waitForElementVisibleAndClickable(WebDriver driver, WebElement element, int seconds)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	  wait.until(ExpectedConditions.visibilityOf(element));
	  wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	//Waiting until element is visible
	public static void waitForElementVisible(WebDriver driver, WebElement element, int seconds){
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	  wait.until(ExpectedConditions.visibilityOf(element));
	}
	//Waiting until element is disappeared
	public static void waitForElementToDisappear(WebDriver driver, WebElement ele)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5))
	        .ignoring(NoSuchElementException.class,StaleElementReferenceException.class);
	  wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	//Capturing screenshot of visible screen
	public static String captureScreenshot(WebDriver driver) throws IOException
	{
		File scFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File("Result/"+System.currentTimeMillis()+".png");		
		FileUtils.copyFile(scFile, dest);
		return dest.getAbsolutePath();
	}

}
