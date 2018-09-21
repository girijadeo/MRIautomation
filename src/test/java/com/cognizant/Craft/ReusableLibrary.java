package com.cognizant.Craft;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.cognizant.framework.CraftDataTable;
import com.cognizant.framework.FrameworkParameters;
import com.cognizant.framework.Settings;
import com.cognizant.framework.selenium.CraftDriver;
import com.cognizant.framework.selenium.SeleniumReport;
import com.cognizant.framework.selenium.WebDriverUtil;

/**
 * Abstract base class for reusable libraries created by the user
 * 
 * @author Cognizant
 */
public abstract class ReusableLibrary {

	Map<String, Object> perfectoCommand = new HashMap<>();

	/**
	 * The {@link CraftDataTable} object (passed from the test script)
	 */
	protected CraftDataTable dataTable;
	/**
	 * The {@link SeleniumReport} object (passed from the test script)
	 */
	protected SeleniumReport report;
	/**
	 * The {@link CraftDriver} object
	 */
	protected CraftDriver driver;

	protected WebDriverUtil driverUtil;

	/**
	 * The {@link ScriptHelper} object (required for calling one reusable
	 * library from another)
	 */
	protected ScriptHelper scriptHelper;

	/**
	 * The {@link Properties} object with settings loaded from the framework
	 * properties file
	 */
	protected Properties properties;
	/**
	 * The {@link FrameworkParameters} object
	 */
	protected FrameworkParameters frameworkParameters;

	/**
	 * Constructor to initialize the {@link ScriptHelper} object and in turn the
	 * objects wrapped by it
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object
	 */
	public ReusableLibrary(ScriptHelper scriptHelper) {
		this.scriptHelper = scriptHelper;
		this.dataTable = scriptHelper.getDataTable();
		this.report = scriptHelper.getReport();
		this.driver = scriptHelper.getcraftDriver();
		this.driverUtil = scriptHelper.getDriverUtil();

		properties = Settings.getInstance();
		frameworkParameters = FrameworkParameters.getInstance();
	}

	/**
	 * All reusuable Appium Functions with Perfecto
	 */

	protected void openApp(final String context, final String appName) {
		if (context.equals("NATIVE_APP")) {
			final Map<String, Object> perfectoCommand = new HashMap<>();
			perfectoCommand.put("name", appName);
			driver.getAppiumDriver().executeScript("mobile:application:open",
					perfectoCommand);
		}
	}

	protected void openAppWithIdentifier(final String context,
			final String identifer) {
		if (context.equals("NATIVE_APP")) {
			perfectoCommand.put("identifier", identifer);
			driver.getAppiumDriver().executeScript("mobile:application:open",
					perfectoCommand);
			perfectoCommand.clear();
		}
	}

	protected byte[] downloadReport(final String type) throws IOException {
		final String command = "mobile:report:download";
		final Map<String, String> params = new HashMap<>();
		params.put("type", type);
		final String report = (String) ((RemoteWebDriver) driver
				.getRemoteWebDriver()).executeScript(command, params);
		final byte[] reportBytes = OutputType.BYTES
				.convertFromBase64Png(report);
		return reportBytes;
	}

	protected byte[] downloadWTReport() {
		final String reportUrl = (String) driver.getAppiumDriver()
				.getCapabilities().getCapability("windTunnelReportUrl");
		String returnString = "<html><head><META http-equiv=\"refresh\" content=\"0;URL=";
		returnString = returnString + reportUrl + "\"></head><body /></html>";

		return returnString.getBytes();
	}

	protected void closeApp(final String context, final String appName) {
		if (context.equals("NATIVE_APP")) {
			perfectoCommand.put("name", appName);
			try {
				driver.getAppiumDriver().executeScript(
						"mobile:application:close", perfectoCommand);
			} catch (final WebDriverException e) {
			}
		}
	}

	protected void closeAppWithIdentifier(final String context,
			final String bundleId) {
		if (context.equals("NATIVE_APP")) {
			perfectoCommand.put("identifier", bundleId);
			try {
				driver.getAppiumDriver().executeScript(
						"mobile:application:close", perfectoCommand);
			} catch (final WebDriverException e) {
			}
		}
	}

	protected Boolean textCheckpoint(final String textToFind,
			final Integer timeout) {
		perfectoCommand.put("content", textToFind);
		perfectoCommand.put("timeout", timeout);
		final Object result = driver.getAppiumDriver().executeScript(
				"mobile:checkpoint:text", perfectoCommand);
		final Boolean resultBool = Boolean.valueOf(result.toString());
		perfectoCommand.clear();

		System.out
				.println("###  result ### " + textToFind + "   " + resultBool);

		return resultBool;
	}

	protected void visualScrollToClick(final String label,
			final Integer threshold) {
		perfectoCommand.put("label", label);
		perfectoCommand.put("threshold", threshold);
		perfectoCommand.put("scrolling", "scroll");
		driver.getAppiumDriver().executeScript("mobile:button-text:click",
				perfectoCommand);
		perfectoCommand.clear();
	}

	protected void visualClick(final String label, final Integer timeout,
			final Integer threshold) {
		perfectoCommand.put("label", label);
		perfectoCommand.put("threshold", threshold);
		perfectoCommand.put("timeout", timeout);
		driver.getAppiumDriver().executeScript("mobile:button-text:click",
				perfectoCommand);
		perfectoCommand.clear();
	}

	protected void visualClick(final String label, final Integer timeout,
			final Integer threshold, final String labelDirection,
			final String labelOffset) {
		perfectoCommand.put("label", label);
		perfectoCommand.put("threshold", threshold);
		perfectoCommand.put("timeout", timeout);
		perfectoCommand.put("label.direction", labelDirection);
		perfectoCommand.put("label.offset", labelOffset);
		driver.getAppiumDriver().executeScript("mobile:button-text:click",
				perfectoCommand);
		perfectoCommand.clear();
	}

	protected void putFileOnDevice(final String repositoryFile,
			final String handsetFile) {
		perfectoCommand.put("repositoryFile", repositoryFile);
		perfectoCommand.put("handsetFile", handsetFile);
		driver.getAppiumDriver().executeScript("mobile:media:put",
				perfectoCommand);
		perfectoCommand.clear();

	}

	protected void getFileOnDevice(final String handsetFile,
			final String repositoryFile) {
		perfectoCommand.put("repositoryFile", repositoryFile);
		perfectoCommand.put("handsetFile", handsetFile);
		driver.getAppiumDriver().executeScript("mobile:media:get",
				perfectoCommand);
		perfectoCommand.clear();

	}

	protected Boolean textCheckpoint(final String textToFind,
			final Integer timeout, final Integer threshold) {
		perfectoCommand.put("content", textToFind);
		perfectoCommand.put("timeout", timeout);
		perfectoCommand.put("threshold", threshold);
		final Object result = driver.getAppiumDriver().executeScript(
				"mobile:checkpoint:text", perfectoCommand);
		final Boolean resultBool = Boolean.valueOf(result.toString());
		perfectoCommand.clear();

		System.out
				.println("###  result ### " + textToFind + "   " + resultBool);

		return resultBool;
	}

	protected Boolean textCheckpoint(final String textToFind,
			final Integer timeout, final Integer threshold, final Integer index) {
		perfectoCommand.put("content", textToFind);
		perfectoCommand.put("timeout", timeout);
		perfectoCommand.put("threshold", threshold);
		perfectoCommand.put("index", index);
		final Object result = driver.getAppiumDriver().executeScript(
				"mobile:checkpoint:text", perfectoCommand);
		final Boolean resultBool = Boolean.valueOf(result.toString());
		perfectoCommand.clear();

		System.out
				.println("###  result ### " + textToFind + "   " + resultBool);

		return resultBool;
	}

	protected void deviceKeyPress(final String keyPress) {

		perfectoCommand.put("keySequence", keyPress);
		driver.getAppiumDriver().executeScript("mobile:presskey",
				perfectoCommand);
		perfectoCommand.clear();
	}

	protected void swipe(final String x1, final String y1, final String x2,
			final String y2) {
		final List<String> swipeCoordinates = new ArrayList<>();
		swipeCoordinates.add(x1 + ',' + y1);
		swipeCoordinates.add(x2 + ',' + y2);
		perfectoCommand.put("location", swipeCoordinates);
		driver.getAppiumDriver().executeScript("mobile:touch:drag",
				perfectoCommand);
		perfectoCommand.clear();
	}

	public void PauseScript(int How_Long_To_Pause) {

		// convert to seconds
		How_Long_To_Pause = How_Long_To_Pause * 1000;

		try {
			Thread.sleep(How_Long_To_Pause);
		} catch (final InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

	}

	/**
	 * All reusuable Selenium Functions with Perfecto
	 */

	protected void closeWebDriver() throws IOException {
		// make sure web driver is closed
		if (!hasQuit(driver.getRemoteWebDriver())) {

			// driver.close();
			Map<String, Object> params = new HashMap<>();

			((JavascriptExecutor) driver).executeScript(
					"mobile:execution:close", params);

			// downloadReport("html");
			downloadWTReport();
			driver.quit();
		}
	}

	protected static boolean hasQuit(RemoteWebDriver driver) {
		return ((RemoteWebDriver) driver).getSessionId() == null;
	}

	protected static void switchToContext(RemoteWebDriver driver, String context) {
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", context);
		executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
	}

	protected void pointOfInterest(String desc, String status) {
		perfectoCommand.clear();
		perfectoCommand.put("description", desc);
		perfectoCommand.put("status", status);
		driver.executeScript("mobile:status:event", perfectoCommand);
		perfectoCommand.clear();
	}

	protected void selectValue(String xpath, String visibleText) {
		Select select = new Select(driver.findElementByXPath(xpath));
		select.selectByVisibleText(visibleText);
	}

	protected void tapCoordinates(Integer xCoord, Integer yCoord) {
		perfectoCommand.clear();
		perfectoCommand.put("location", xCoord + "," + yCoord);
		driver.executeScript("mobile:touch:tap", perfectoCommand);
		perfectoCommand.clear();
	}

}