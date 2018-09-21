package testscripts.MRI;

import org.openqa.selenium.Platform;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.cognizant.Craft.CRAFTTestCase;
import com.cognizant.framework.IterationOptions;
import com.cognizant.framework.selenium.Browser;
import com.cognizant.framework.selenium.ExecutionMode;
import com.cognizant.framework.selenium.SeleniumTestParameters;
import supportLibraries.TFSDefectManagement;


import com.cognizant.Craft.*;


public class TC_LaunchGoogle_Pass01 extends CRAFTTestCase
{
	@Test(dataProvider = "RegressionTestScripts")
	public void runTC_LaunchGoogle_Pass01(String testInstance, ExecutionMode executionMode, Platform platform
			/*MobileToolName mobileToolName,
			MobileExecutionPlatform executionPlatform, String deviceName*/)
	{
		SeleniumTestParameters testParameters = new SeleniumTestParameters(currentScenario, currentTestcase);
		testParameters.setCurrentTestDescription("Test for validating the launching of MRI website");
		testParameters.setIterationMode(IterationOptions.RUN_ONE_ITERATION_ONLY);
		testParameters.setBrowser(Browser.INTERNET_EXPLORER);
		testParameters.setExecutionMode(executionMode);
		testParameters.setPlatform(platform);
		DriverScript driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
		tearDownTestRunner(testParameters, driverScript);
//		TFSDefectManagement bug = new TFSDefectManagement();
//		bug.createBugInTFS("create",currentTestcase,currentScenario,"MRI","","2","3 - Medium","Maintenance/Support","");
	}
	
	@DataProvider(name = "RegressionTestScripts", parallel = false)
	public Object[][] dataTC2() {
		return new Object[][] { { "Instance1", ExecutionMode.LOCAL, Platform.WINDOWS /*ExecutionMode.LOCAL,
				MobileToolName.APPIUM, MobileExecutionPlatform.IOS,
				"4d005cb2c4938197"*/ },
			};
	}
	
	
}


















