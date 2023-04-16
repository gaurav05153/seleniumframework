package objectManager;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import dataProvider.ConfigFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Logging;

public class DriverManager 
{
	private static WebDriver driver;
	
	public static WebDriver getDriver()
	{
		if(driver==null)
			createDriver();
		return driver;
	}
	
	public static void createDriver()
	{
		switch(ConfigFileReader.getBrowser().toUpperCase())
		{
		case "FIREFOX":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			Logging.info("Firefox driver reated");
			break;
		case "CHROME":
			ChromeOptions options = new ChromeOptions();
			options.setAcceptInsecureCerts(true);
			options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
			options.addArguments("start-maximized");
			options.addArguments("--disabled-popup-blocking");
			options.addArguments("--remote-allow-origins=*");
			if(ConfigFileReader.getMode().equalsIgnoreCase("YES"))
			{
				options.addArguments("--headless");
				options.addArguments("--disable-gpu");
			}
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			Logging.info("Chrome driver created");
			break;
		case "EDGE":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			Logging.info("Edge driver created");
			break;
		default:
			System.out.println("No matching browser found");
			Logging.info("No matching driver found. Closing the script run.");
			System.exit(0);
			break;
		}
		driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigFileReader.getImplicitWait()), TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Long.parseLong(ConfigFileReader.getPageLoadTimeout()), TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Logging.info("Browser window maximize");
	}

}
