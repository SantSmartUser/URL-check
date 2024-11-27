package util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements  IRetryAnalyzer{

	int retryLimit = 3;
	int counter =0;
	@Override
	public boolean retry(ITestResult result) {
		if (counter < retryLimit )
		{
			counter++;
			return true;
		}
		
		return false;
	}

}
