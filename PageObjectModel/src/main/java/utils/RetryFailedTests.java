package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTests implements IRetryAnalyzer{
	
	int maxRetry=2;
	int count=1;
	
	public boolean retry(ITestResult result) {
		if(count<maxRetry) {
			count++;
		return true;
	}
		return false;
	}

}
