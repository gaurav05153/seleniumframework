package utils;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import context.TestContext;
import dataProvider.ConfigFileReader;
import extentReport.ExtentReport;
import objectManager.DriverManager;

public class Listener implements ITestListener {
    public static ExtentReport extentReport = new ExtentReport();

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test class execution started");
    }

    public void onFinish(ITestContext context) {
        System.out.println("Test Case Execution is finished");

    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("test case is started :" + iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("test case is passed:" + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println("Test case got failed" + result.getName());

        try {
            extentReport.fail("Test Case got failed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("test case got skipped:" + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("test case is failed , but some part of test is passed:" + result.getName());
    }


}
