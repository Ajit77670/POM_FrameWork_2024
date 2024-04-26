package com.qa.opencart.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    private int count=0;
    private static int maxtry=3;

    public boolean retry(ITestResult iTestresult) {

        if (!iTestresult.isSuccess()) {

            if (count < maxtry) {
                count++;
                iTestresult.setStatus(ITestResult.FAILURE); // Marked test as failed
                return true; // tell testNG to re-run the test.
            } else {
                iTestresult.setStatus(ITestResult.FAILURE);  // if maxCount reached,test marked as failed.
            }
        }
        else {
                iTestresult.setStatus(ITestResult.SUCCESS);  //if the tetPasses. TestNG marked it passes
            }
            return false;
        }

    }


