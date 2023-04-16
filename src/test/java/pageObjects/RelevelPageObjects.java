package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RelevelPageObjects 
{	
	public RelevelPageObjects(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@rel-name='HeaderLoginCTAClicked']")
	public WebElement lnkLogin;
	
	@FindBy(xpath="//*[@rel-name='SignupLoginPhoneInputField']")
	public WebElement txtMobileNo;
	
	@FindBy(xpath="//*[@rel-name='SignupLoginPhoneLoginCTA']")
	public WebElement btnLogin;
	
	@FindBy(xpath="//*[@rel-name='SignupLoginEditPhoneCTA']")
	public WebElement lnkEditMobileNumber;
	
	@FindBy(xpath="//*[@rel-name='SignupLoginOTPContinueCTA']")
	public WebElement btnContinue;
	
	@FindBy(xpath="//*[@rel-name='SignupLoginOTPInputField']")
	public WebElement txtOtp;
	
	@FindBy(id="Tests")
	public WebElement icnTests;
	
	@FindBy(xpath="//*[@name='product-search']")
	public WebElement txtProductSearch;
	
	@FindBy(xpath="//*[@name='HeaderCoursesCTAClicked']")
	public WebElement icnCourses;
	
	@FindBy(partialLinkText="Courses")
	public WebElement lnkCourses;
	
	@FindBy(xpath="//*[contains(text(),'Software Testing')]//following::button[1]")
	public WebElement btnViewDetails;

	@FindBy(xpath="//*[@rel-name='MainLandingPageHeaderGetHiredCTA']")
	public WebElement btnStartLearning;
	
	@FindBy(id="frontend-developmentname")
	public WebElement lblFrontendtest;
	
	@FindBy(xpath="//div[@id='bookYourSlotCTA']//*[text()='Book your free slot']")
	public WebElement btnBookFreeSlotTestTab;

	@FindBy(css="div#__next div.flex>h1")
	public WebElement titleOfRelevelLandingPage;

	@FindBy(xpath= "//input[@rel-name='SignupLoginPhoneInputField']/following-sibling::div")
	public WebElement messagePlaceholderForInvalidMobileNumber;

	@FindBy(xpath= "//div[@id='__next']//span[contains(text(),'sent an OTP to your mobile number')]")
	public WebElement messagePlaceholderForOtpSent;
}
