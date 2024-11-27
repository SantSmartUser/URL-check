package util;

import org.testng.ITestListener;
import org.testng.ITestResult;
import util.Base;

import java.io.IOException;

import org.testng.ITestContext;

public class ScreenshotListener extends Base implements ITestListener {

	public void onTestFailure(ITestResult result) {

		/*
		 * try { failed(result.getMethod().getMethodName()); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */

	}

}
