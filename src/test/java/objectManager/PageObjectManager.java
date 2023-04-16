package objectManager;

import org.openqa.selenium.WebDriver;

import pageObjects.RelevelPageObjects;

public class PageObjectManager 
{	
	private WebDriver driver;
	private RelevelPageObjects relevelPageObjects;
	
	public PageObjectManager(WebDriver driver)
	{
		this.driver=driver;
	}

	public RelevelPageObjects getRelevelPageObjects()
	{
		return (relevelPageObjects==null) ? relevelPageObjects = new RelevelPageObjects(driver): relevelPageObjects;
	}
}
