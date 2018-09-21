package com.cognizant.Craft;


import com.cognizant.framework.FrameworkParameters;
import com.cognizant.framework.Settings;
import com.cognizant.framework.selenium.*;

import supportLibraries.TFSDefectManagement;

import java.util.Properties;

import org.openqa.selenium.Platform;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

/**
 * Abstract base class for all the test cases to be automated
 * 
 * @author Cognizant
 */
public abstract class CRAFTTestCase {
	/**
	 * The current scenario
	 */
	protected String currentScenario;
	/**
	 * The current test case
	 */
	protected String currentTestcase;

	private ResultSummaryManager resultSummaryManager = ResultSummaryManager
			.getInstance();
	private Properties properties;
	public supportLibraries.TFSDefectManagement TFSDefectObj;


	/**
	 * Function to do the required framework setup activities before executing
	 * the overall test suite
	 * 
	 * @param testContext
	 *            The TestNG {@link ITestContext} of the current test suite
	 */
	@BeforeSuite
	public void setUpTestSuite(ITestContext testContext) {
		resultSummaryManager.setRelativePath();
		resultSummaryManager.initializeTestBatch(testContext.getSuite()
				.getName());

		int nThreads;
		if ("false".equalsIgnoreCase(testContext.getSuite().getParallel())) {
			nThreads = 1;
		} else {
			nThreads = testContext.getCurrentXmlTest().getThreadCount();
		}

		// Note: Separate threads may be spawned through usage of DataProvider
		// testContext.getSuite().getXmlSuite().getDataProviderThreadCount();
		// This will be at test case level (multiple instances on same test case
		// in parallel)
		// This level of threading will not be reflected in the summary report

		resultSummaryManager.initializeSummaryReport(nThreads);
		resultSummaryManager.setupErrorLog();
	}

	/**
	 * Function to do the required framework setup activities before executing
	 * each test case
	 */
	@BeforeMethod
	public void setUpTestRunner() {
		FrameworkParameters frameworkParameters = FrameworkParameters
				.getInstance();
		if (frameworkParameters.getStopExecution()) {
			tearDownTestSuite();

			// Throwing TestNG SkipException within a configuration method
			// causes all subsequent test methods to be skipped/aborted
			throw new SkipException(
					"Test execution terminated by user! All subsequent tests aborted...");
		} else {
			currentScenario = this.getClass().getPackage().getName()
					.substring(12);
			currentTestcase = this.getClass().getSimpleName();
		}
	}

	/**
	 * Function to do the required framework teardown activities after executing
	 * each test case
	 * 
	 * @param testParameters
	 *            The {@link SeleniumTestParameters} object passed from the test
	 *            case
	 * @param driverScript
	 *            The {@link DriverScript} object passed from the test case
	 */
	protected synchronized void tearDownTestRunner(
		SeleniumTestParameters testParameters, DriverScript driverScript) {
		String testReportName = driverScript.getReportName();
		String executionTime = driverScript.getExecutionTime();
		String testStatus = driverScript.getTestStatus();

		resultSummaryManager.updateResultSummary(testParameters,
				testReportName, executionTime, testStatus);

		if ("Failed".equalsIgnoreCase(testStatus)) {
			
			//Added by Girija Deo on 02/01/2018 for the defect management service:
			
			TFSDefectManagement bug = new TFSDefectManagement();
			
			properties = Settings.getInstance(); 
			
			Boolean existingBugTrueOrFalse = bug.searchBugInTFS(properties.getProperty("URL"),currentTestcase, properties.getProperty("ProjectNameInTFS"));
			if(existingBugTrueOrFalse==false) {
			
				System.out.println("Existing defect not found for the test case - "+currentTestcase+ " - hence creating a new one");
				bug.createBugInTFS(properties.getProperty("URL"),currentTestcase,currentScenario + driverScript.getFailureDescription(), 
						
//						testParameters.getCurrentTestcase(), 
//						testParameters.getCurrentTestDescription(),
						properties.getProperty("ProjectNameInTFS"),"",properties.getProperty("priority"),
						properties.getProperty("severity"),properties.getProperty("worktype"),properties.getProperty("history"));
//				bug.createBugInTFS("http://10.71.77.70:8000", currentTestcase, currentScenario, "MRI", "", "2", "3 - Medium",
//					"Maintenance/Support", "");
			}
			else {
				System.out.println("Existing defect found for the test case - "+currentTestcase+ " - hence no new defect created");				
			}
			Assert.fail(driverScript.getFailureDescription());
			
		}
	}

	/**
	 * Function to do the required framework teardown activities after executing
	 * the overall test suite
	 */
	@AfterSuite
	public void tearDownTestSuite() {
		resultSummaryManager.wrapUp(true);
		// resultSummaryManager.launchResultSummary();
	}

	@DataProvider(name = "GlobalTestConfigurations", parallel = true)
	public Object[][] dataGlobal() {
		return new Object[][] { { "Instance4", ExecutionMode.LOCAL,
				MobileToolName.APPIUM, MobileExecutionPlatform.ANDROID, "4.4",
				"N/A", Browser.FIREFOX, Platform.WINDOWS } };
	}
}