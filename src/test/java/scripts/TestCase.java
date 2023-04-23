package scripts;

import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import context.TestContext;
import dataProvider.ConfigFileReader;
import dataProvider.ReadWriteExcel;
import extentReport.ExtentReport;
import objectManager.DriverManager;
import pageObjects.RelevelPageObjects;
import utils.Action;
import utils.Logging;
import utils.Utility;

public class TestCase {
    WebDriver driver;
    TestContext testContext;
    RelevelPageObjects relevelPageObjects;
    Action action;
    Utility utility;
    ExtentReport extentReport;
    ReadWriteExcel excel;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() throws IOException {
        driver = DriverManager.getDriver();
        driver.get(ConfigFileReader.getUrl());
        testContext = new TestContext();
        relevelPageObjects = testContext.getPageObjectManager().getRelevelPageObjects();
        action = testContext.getActionObject();
        utility = new Utility();
        extentReport = testContext.getExtentReport();
        excel = new ReadWriteExcel();
        PropertyConfigurator.configure("log4j.properties");
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        extentReport.flush();
        driver.close();  //close the driver
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {

    }

    @AfterMethod(alwaysRun = true)
    public void closeTestMethod(ITestResult result) throws IOException {
        Logging.info("Ending the Test Case Execution");

        if (ITestResult.FAILURE == result.getStatus()) {
            System.out.println("Failed Status Check");
            extentReport.addScreenshot(driver);
        }
    }


    @Test(description = "TC001-This test verifies user see a validation message when invalid mobile number is entered for login", priority = 1, enabled = false)
    public void tc_1() throws Exception {
        try {
            HSSFSheet sheet = excel.getSheet("TC001");
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                extentReport.createTest("TC001-This test verifies user see a validation message when invalid mobile number entered for login");
                action.clickButton(relevelPageObjects.lnkLogin, "Login link");
                extentReport.info("Login link clicked");
                action.sendKeys(relevelPageObjects.txtMobileNo, sheet.getRow(i).getCell(0).getStringCellValue(), "Mobile Number textbox");
                extentReport.info("Mobile number entered");
                action.pressTab(relevelPageObjects.txtMobileNo);
                extentReport.info("Tab pressed");
                utility.waitForVisibilityOfElement(driver, relevelPageObjects.messagePlaceholderForInvalidMobileNumber);
                //The method findElements returns a list of matching elements.
                // Then, we have to use the method size to get the number of items in the list. If the size is 0, it means that this element is absent from the page.
                if (driver.findElements(By.xpath("//*[text()='Please enter valid mobile number']")).size() > 0) {
                    extentReport.pass("Please enter valid mobile number validation message is displayed");
                    extentReport.addScreenshot(driver);
                    Logging.info("Please enter valid mobile number validation message is displayed ");
                    Logging.endTestCase();
                } else {
                    extentReport.fail("Validation message for invalid mobile number is not displayed");
                    extentReport.addScreenshot(driver);
                    Logging.info("Validation message for invalid mobile number is not displayed");
                    Logging.endTestCase();
                }
                driver.get(ConfigFileReader.getUrl());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            extentReport.fail(e.getMessage());
            Logging.info(e.getMessage());
            Logging.endTestCase();
        }
    }

    @Test(description = "TC002-This test verifies user see a validation message when invalid mobile number is entered for login (Different Way)", priority = 2, enabled = false)
    public void tc_2() throws Exception {
        String expectedMessage = "Please enter valid mobile number";
        try {
            HSSFSheet sheet = excel.getSheet("TC001");
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                extentReport.createTest("TC002-This test verifies user see a validation message when invalid mobile number entered for login");
                action.clickButton(relevelPageObjects.lnkLogin, "Login link");
                extentReport.info("Login link clicked");
                action.sendKeys(relevelPageObjects.txtMobileNo, sheet.getRow(i).getCell(0).getStringCellValue(), "Mobile Number textbox");
                extentReport.info("Mobile number entered");
                action.pressTab(relevelPageObjects.txtMobileNo);
                extentReport.info("Tab pressed");
                String actualMessage = relevelPageObjects.messagePlaceholderForInvalidMobileNumber.getText();
                Assert.assertEquals(actualMessage, expectedMessage, "Application is displaying incorrect message when invalid mobile number is passed while login");
                extentReport.pass("Application is displaying correct message when invalid mobile number is entered while login");
                Logging.info("Application is displaying correct message when invalid mobile number is entered while login");
                Logging.endTestCase();

//				if(driver.findElements(By.xpath("//*[text()='Please Enter valid mobile number']")).size()>0)
//				{
//					extentReport.pass("Please enter valid mobile number validation message is displayed");
//					extentReport.addScreenshot(driver);
//					Logging.info("Please enter valid mobile number validation message is displayed ");
//					Logging.endTestCase();
//				}
//				else
//				{
//					extentReport.fail("Validation message for invalid mobile number is not displayed");
//					extentReport.addScreenshot(driver);
//					Logging.info("Validation message for invalid mobile number is not displayed");
//					Logging.endTestCase();
//				}
                driver.get(ConfigFileReader.getUrl());
            }
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
            extentReport.fail(e.getMessage());
            Logging.info(e.getMessage());
            Logging.endTestCase();
        } catch (Exception e)     // In case, you want to keep just one Catch block, You can use catch (Throwable t) as Exception and Error extend Throwable
        {
            System.out.println(e.getMessage());
            extentReport.fail(e.getMessage());
            Logging.info(e.getMessage());
            Logging.endTestCase();
        }
    }

    @Test(description = "TC003-This test verifies that user can click login button when valid mobile number is provided", priority = 3, enabled = false)
    public void tc_3() throws Exception {
        Logging.startTestCase("TC003");
        try {
            HSSFSheet sheet = excel.getSheet("TC002");
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                extentReport.createTest("TC003-This test verify user can click login button when valid mobile number is provided");
                action.clickButton(relevelPageObjects.lnkLogin, "Login link");
                extentReport.info("Login link clicked");
                action.sendKeys(relevelPageObjects.txtMobileNo, sheet.getRow(i).getCell(0).getStringCellValue(), "Mobile Number textbox");
                extentReport.info("Mobile number entered");
                action.clickButton(relevelPageObjects.btnLogin, "Login button");
                extentReport.info("Login button clicked");
                Thread.sleep(3000);
                if (driver.findElements(By.xpath("//*[contains(text(),'sent an OTP to your mobile number')]")).size() > 0) {
                    extentReport.pass("We've sent an OTP to your mobile number success message is displayed");
                    extentReport.addScreenshot(driver);
                    Logging.info("We've sent an OTP to your mobile number success message is displayed");
                    Logging.endTestCase();
                } else {
                    extentReport.fail("Success message is not displayed");
                    extentReport.addScreenshot(driver);
                    Logging.info("Success message is not displayed");
                    Logging.endTestCase();
                }
                driver.get(ConfigFileReader.getUrl());
            }
        } catch (Exception e) {
            extentReport.fail(e.getMessage());
            Logging.info(e.getMessage());
            Logging.endTestCase();
        }
    }

    @Test(description = "TC004-This test verifies that user can click login button when valid mobile number is provided (Different Way) ", priority = 4, enabled = false)
    public void tc_4() throws Exception {
        Logging.startTestCase("TC004");
        try {
            HSSFSheet sheet = excel.getSheet("TC002");
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                extentReport.createTest("TC004-This test verify user can click login button when valid mobile number is provided");
                action.clickButton(relevelPageObjects.lnkLogin, "Login link");
                extentReport.info("Login link clicked");
                action.sendKeys(relevelPageObjects.txtMobileNo, sheet.getRow(i).getCell(0).getStringCellValue(), "Mobile Number textbox");
                extentReport.info("Mobile number entered");
                action.clickButton(relevelPageObjects.btnLogin, "Login button");
                extentReport.info("Login button clicked");
                utility.waitForVisibilityOfElement(driver, relevelPageObjects.messagePlaceholderForOtpSent);
                if (relevelPageObjects.messagePlaceholderForOtpSent.isDisplayed()) {
                    extentReport.pass("User can click on Login button and received the message: We've sent an OTP to your mobile number success message is displayed");
                    Logging.info("User can click on Login button and received the message: We've sent an OTP to your mobile number success message is displayed");
                    Logging.endTestCase();
                } else {
                    extentReport.fail("Success message is not displayed");
                    extentReport.addScreenshot(driver);
                    Logging.info("Success message is not displayed");
                    Logging.endTestCase();
                }
                driver.get(ConfigFileReader.getUrl());
            }
        } catch (Exception e) {
            extentReport.fail(e.getMessage());
            Logging.info(e.getMessage());
            Logging.endTestCase();
        }
    }

    @Test(description = "TC005-This test verify user can edit and login with new mobile number", priority = 5, enabled = true)
    public void tc_5() throws IOException {
	  Logging.startTestCase("TC005");
        try {
            HSSFSheet sheet = excel.getSheet("TC003");
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                extentReport.createTest("TC005-This test verify user can edit and login with new mobile number");
                action.clickLink(relevelPageObjects.lnkLogin, "Login link");
                extentReport.info("Login link clicked");
                action.sendKeys(relevelPageObjects.txtMobileNo, sheet.getRow(i).getCell(0).getStringCellValue(), "Mobile Number textbox");
                extentReport.info("Mobile number entered");
                action.clickButton(relevelPageObjects.btnLogin, "Login button");
                extentReport.info("Login button clicked");
                action.clickLink(relevelPageObjects.lnkEditMobileNumber, "Edit");
                extentReport.info("Edit mobile number link clicked");
                action.sendKeys(relevelPageObjects.txtMobileNo, sheet.getRow(i).getCell(1).getStringCellValue(), "Mobile Number textbox");
                extentReport.info("New mobile number entered");
                action.clickButton(relevelPageObjects.btnLogin, "Login button");
                extentReport.info("Login button clicked");
                if (driver.findElements(By.xpath("//*[contains(text(),'sent an OTP to your mobile number')]")).size() > 0) {
                    extentReport.pass("Sent an OTP to your mobile number message is displayed");
                    extentReport.addScreenshot(driver);
                    Logging.info("Sent an OTP to your mobile number message is displayed");
                    Logging.endTestCase();
                } else {
                    extentReport.fail("OTP is not sent");
                    extentReport.addScreenshot(driver);
                    Logging.info("OTP is not sent");
                    Logging.endTestCase();
                }
                driver.get(ConfigFileReader.getUrl());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            extentReport.fail(e.getMessage());
            Logging.info(e.getMessage());
            Logging.endTestCase();
        }
    }

    @Test(description = "TC006-This test verify user can edit and login with new mobile number(Different Way) ", priority = 6, enabled = true)
    public void tc_6() throws IOException {
        Logging.startTestCase("TC006");
        try {
            HSSFSheet sheet = excel.getSheet("TC003");
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                extentReport.createTest("TC006-This test verify user can edit and login with new mobile number (Different Way)");
                action.clickLink(relevelPageObjects.lnkLogin, "Login link");
                extentReport.info("Login link clicked");
                action.sendKeys(relevelPageObjects.txtMobileNo, sheet.getRow(i).getCell(0).getStringCellValue(), "Mobile Number textbox");
                extentReport.info("Mobile number entered");
                action.clickButton(relevelPageObjects.btnLogin, "Login button");
                extentReport.info("Login button clicked");
                action.clickLink(relevelPageObjects.lnkEditMobileNumber, "Edit");
                extentReport.info("Edit mobile number link clicked");
                action.sendKeys(relevelPageObjects.txtMobileNo, sheet.getRow(i).getCell(1).getStringCellValue(), "Mobile Number textbox");
                extentReport.info("New mobile number entered");
                action.clickButton(relevelPageObjects.btnLogin, "Login button");
                extentReport.info("Login button clicked");
                utility.waitForVisibilityOfElement(driver, relevelPageObjects.messagePlaceholderForOtpSent);
                String expectedMessage = "We've sent an OTP to your mobile number";
                String actualMessage = relevelPageObjects.messagePlaceholderForOtpSent.getText();
                Assert.assertEquals(actualMessage, expectedMessage);  //checking the verbiage of message
                if (relevelPageObjects.messagePlaceholderForOtpSent.isDisplayed()) {  //checking that message is displayed on UI
                    extentReport.pass("Sent an OTP to your mobile number message is displayed");
                    extentReport.addScreenshot(driver);
                    Logging.info("Sent an OTP to your mobile number message is displayed");
                    Logging.endTestCase();
                } else {
                    extentReport.fail("OTP is not sent");
                    extentReport.addScreenshot(driver);
                    Logging.info("OTP is not sent");
                    Logging.endTestCase();
                }
                driver.get(ConfigFileReader.getUrl());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            extentReport.fail(e.getMessage());
            Logging.info(e.getMessage());
            Logging.endTestCase();
        }
    }

    @Test(description = "TC007-This test verify validation message is shown when user enter invalid OTP for login", priority = 7, enabled = false)
    public void tc_7() throws Exception {
Logging.startTestCase("TC007");
        try {
            HSSFSheet sheet = excel.getSheet("TC004");
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                extentReport.createTest("TC007-This test verify validation message is shown when user enter invalid OTP for login");
                action.clickLink(relevelPageObjects.lnkLogin, "Login link");
                extentReport.info("Login link clicked");
                action.sendKeys(relevelPageObjects.txtMobileNo, sheet.getRow(i).getCell(0).getStringCellValue(), "Mobile Number textbox");
                extentReport.info("Mobile number entered");
                action.clickButton(relevelPageObjects.btnLogin, "Login button");
                action.sendKeys(relevelPageObjects.txtOtp, sheet.getRow(i).getCell(1).getStringCellValue(), "OTP textbox");
                extentReport.info("OTP entered");
                action.clickButton(relevelPageObjects.btnContinue, "Continue");
                extentReport.info("Continue button clicked");
                Thread.sleep(3000);
                if (driver.findElements(By.xpath("//div[text()='This OTP is incorrect']")).size() > 0) {
                    extentReport.pass("This OTP is incorrect message is displayed");
                    extentReport.addScreenshot(driver);
                    Logging.info("This OTP is incorrect message is displayed");
                    Logging.endTestCase();
                } else {
                    extentReport.fail("Invalid OTP message is not displayed");
                    extentReport.addScreenshot(driver);
                    Logging.info("Invalid OTP message is not displayed");
                    Logging.endTestCase();
                }
                driver.get(ConfigFileReader.getUrl());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            extentReport.fail(e.getMessage());
            Logging.info(e.getMessage());
            Logging.endTestCase();
        }
    }

    @Test(description = "TC009-This test verify user can view a tests from Test tab", priority = 9, enabled = false)
    public void tc_9() throws Exception {
	  Logging.startTestCase("TC009");
        try {
            extentReport.createTest("TC009-This test verify user can view a test from Test tab");
            action.clickLink(relevelPageObjects.icnTests, "Test");
            extentReport.info("Test link clicked");
            action.clickLink(relevelPageObjects.lblFrontendtest, "Frontend Development Test");
            extentReport.info("Frontend Development Test clicked");
            action.clickButton(relevelPageObjects.btnBookFreeSlotTestTab, "Book your free slot");
            if (driver.findElements(By.xpath("//*[text()='Sign up or login']")).size() > 0) {
                extentReport.pass("Sign up or login screen displayed");
                extentReport.addScreenshot(driver);
                Logging.info("Sign up or login screen displayed");
                Logging.endTestCase();
            } else {
                extentReport.fail("Sign up or login screen not displayed");
                extentReport.addScreenshot(driver);
                Logging.info("Sign up or login screen not displayed");
                Logging.endTestCase();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            extentReport.fail(e.getMessage());
            Logging.info(e.getMessage());
            Logging.endTestCase();
        }
    }

    @Test(description = "TC011-This test verify user can view a course from Courses tab", priority = 11, enabled = false)
    public void tc_11() throws Exception {
        try {
            extentReport.createTest("TC011-This test verify user can view a tests from Courses tab");
            action.clickLink(relevelPageObjects.lnkCourses, "Courses");
            extentReport.info("Courses link clicked");
            action.clickButton(relevelPageObjects.btnViewDetails, "View Details");
            extentReport.info("Software testing course View details button clicked");
            if (driver.findElements(By.xpath("//*[text()='Apply now']")).size() > 0) {
                extentReport.pass("Apply now button is displayed");
                extentReport.addScreenshot(driver);
                Logging.info("Apply now button is displayed");
                Logging.endTestCase();
            } else {
                extentReport.fail("Apply now button is not displayed");
                extentReport.addScreenshot(driver);
                Logging.info("Apply now button is not displayed");
                Logging.endTestCase();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            extentReport.fail(e.getMessage());
            Logging.info(e.getMessage());
            Logging.endTestCase();
        }
    }

    @Test(description = "TC013-This test is to verify the Relevel Landing page Main Heading Negative Test", priority = 13, enabled = false)
    public void tc_13() throws Exception {
        try {
            String expectedMessage = "Kickstart your dream career with Relevel";
            extentReport.createTest("TC013-This test is to verify the Relevel Landing page Main Heading");
            String actualMessage = action.getText(relevelPageObjects.titleOfRelevelLandingPage);
            extentReport.info("Getting the actual heading of Relevel Landing Page");
            Assert.assertEquals(actualMessage, expectedMessage, "Expected Relevel heading is not appeared on Relevel Landing Page");
            extentReport.info("Expected Relevel heading is appeared on Relevel Landing Page");

        } catch (Throwable e) {
            System.out.println(e.getMessage());
            extentReport.fail(e.getMessage());
            Logging.error(e.getMessage());
            Logging.endTestCase();
        }
    }

    @Test(description = "TC014-This test is to verify the Relevel Landing page Main Heading Positive Test", priority = 14, enabled = false)
    public void tc_14() throws Exception {
        try {
            String expectedMessage = "Get your dream job in 10 days";
            extentReport.createTest("TC014-This test is to verify the Relevel Landing page Main Heading");
            String actualMessage = action.getText(relevelPageObjects.titleOfRelevelLandingPage);
            extentReport.info("Getting the actual heading of Relevel Landing Page");
            Assert.assertEquals(actualMessage, expectedMessage, "Expected Relevel heading is not appeared on Relevel Landing Page");
            extentReport.info("Expected Relevel heading is appeared on Relevel Landing Page");

        } catch (Throwable e) {
            System.out.println(e.getMessage());
            extentReport.fail(e.getMessage());
            Logging.error(e.getMessage());
            Logging.endTestCase();
        }
    }


}
