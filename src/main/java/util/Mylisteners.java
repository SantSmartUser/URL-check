package util;

import java.awt.Desktop;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.EmailException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import util.ExtentReportGenerator;

public class Mylisteners extends Base implements ITestListener {
	ExtentReports report = ExtentReportGenerator.getExtentReport();

	ExtentTest etest;
	int i = 0;

	public void onTestStart(ITestResult result) {

		String testName = result.getName();

		// ExtentReportGenerator.getExtentReport()
		System.out.println("Inside method :-" + testName);

		etest = report.createTest(testName);

		etest.log(Status.INFO, testName + " execution started");

	}

	public void onTestSuccess(ITestResult result) {

		String testName = result.getName();

		etest.log(Status.PASS, testName + " Successfuly Executed");

		etest.assignAuthor("Nisha Soni");

	}

	public void onTestFailure(ITestResult result) {

		String testName = result.getName();

		etest.log(Status.FAIL, testName + " got failed");

		etest.assignAuthor("Nisha Soni");

		WebDriver driver = null;

		try {

			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());

		} catch (IllegalArgumentException e) {

			e.printStackTrace();

		} catch (IllegalAccessException e) {

			e.printStackTrace();

		} catch (NoSuchFieldException e) {

			e.printStackTrace();

		} catch (SecurityException e) {

			e.printStackTrace();

		}

		try {
			
			
			byte[] file1 = FileUtils.readFileToByteArray(new File(takeScreenshot(testName, driver)));
			String screenshot = Base64.encodeBase64String(file1);
			etest.addScreenCaptureFromBase64String(screenshot);	
			//etest.addScreenCaptureFromPath(takeScreenshot(testName, driver), testN;ame);
		
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		System.out.println("Inside fail:-" + testName);

		etest.log(Status.INFO, result.getThrowable());
		//etest.log(Status.PASS, etest.addScreenCaptureFromBase64String(testName));
		
		i++;

	}

	public void onTestSkipped(ITestResult result) {

		String testName = result.getName();

		etest.log(Status.SKIP, testName + " got skipped");

		etest.log(Status.INFO, result.getThrowable());

		etest.assignAuthor("Nisha Soni");

	}

	public void onFinish(ITestContext context) {

		// String testName=result.getName();

		report.flush();
		try {
			SendEmail.sendEmail();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * SimpleDateFormat formatter = new SimpleDateFormat("dd_MMM_yyyy_HH_mm"); Date
		 * date1 = new Date(); System.out.println(formatter.format(date1)); String
		 * filename = "UrlStatus_" + formatter.format(date1);
		 */

		File eReportsFile = new File(
				"C:\\Users\\Amjad.Sayyed\\Downloads\\URL_Check\\ExtentReports\\" + ExtentReportGenerator.filename1 + ".html");

		try {

			Desktop.getDesktop().browse(eReportsFile.toURI());

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {

	}

}
