package dataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader 
{
	private static Properties properties;
	private static final String propertyFilePath="config.properties";
	
	static
	{
		BufferedReader reader;
		try
		{
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try
			{
				properties.load(reader);
				reader.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Config.properties file not found at "+propertyFilePath);
		}
	}
	
	public static String getUrl()
	{
		String url = properties.getProperty("url");
		if(url !=null)
			return url;
		else
			throw new RuntimeException("URL not specified in config.properties file");
	}
	
	public static String getMode()
	{
		String mode = properties.getProperty("headless");
		if(mode !=null)
			return mode;
		else
			throw new RuntimeException("Headless mode not specified in config.properties file");
	}
	
	public static String getBrowser()
	{
		String browser = properties.getProperty("browser");
		if(browser !=null)
			return browser;
		else
			throw new RuntimeException("Browser not specified in config.properties file");
	}
	
	public static String getImplicitWait()
	{
		String waitTime = properties.getProperty("implicitWait");
		if(waitTime !=null)
			return waitTime;
		else
			throw new RuntimeException("implicitWait not specified in config.properties file");
	}
	
	public static String getPageLoadTimeout()
	{
		String loadTime = properties.getProperty("pageLoadTimeout");
		if(loadTime !=null)
			return loadTime;
		else
			throw new RuntimeException("pageLoadTimeout not specified in config.properties file");
	}
	
	public static String getTestDataFile()
	{
		String testDataPath = properties.getProperty("testDataFilePath");
		if(testDataPath !=null)
			return testDataPath;
		else
			throw new RuntimeException("Test data file path not specified in config.properties file");
	}

	public static String getUserName()
	{
		String testDataPath = properties.getProperty("userName");
		if(testDataPath !=null)
			return testDataPath;
		else
			throw new RuntimeException("User name not specified in config.properties file");
	}

	public static String getPassword()
	{
		String testDataPath = properties.getProperty("password");
		if(testDataPath !=null)
			return testDataPath;
		else
			throw new RuntimeException("User password not specified in config.properties file");
	}

}
