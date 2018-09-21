package pageObjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.cognizant.Craft.ReusableLibrary;
import com.cognizant.Craft.ScriptHelper;

import supportLibraries.Utility_Functions;

public class MRITransferToAnotherSuitePopUp extends ReusableLibrary {
	public MRITransferToAnotherSuitePopUp(ScriptHelper scriptHelper) {
		super(scriptHelper);
		PageFactory.initElements(driver.getWebDriver(), this);
	}


	@FindBy(xpath="//input[@name='MRI_1LA_ALTSTOP']")
	WebElement PreviousLeaseAlternateStopBillingDate;
	
	@FindBy(xpath="//input[@name='MRI_1LA_NEWSUIT']")
	WebElement SuiteID;
	
	@FindBy(xpath="//button[@class='accessKeyBlocker' and @accesskey='6']")
	WebElement SuiteIDListButton;
	
	@FindBy(xpath="//input[@name='MRI_1LA_NEWSTART']")
	WebElement NewLeaseStartDate;
	
	@FindBy(xpath="//input[@name='MRI_1LA_NEWEXPIRE']")
	WebElement NewLeaseExpirationDate;
	
	@FindBy(xpath="//select[@name='MRI_1LA_NEWGENCODE']")
	WebElement TransferReason;
	
	@FindBy(xpath = "//*[@xmlns='http://www.w3.org/1999/xhtml' and @class='Grid Default']")
	WebElement RenewThisLeaseTable;
	
	@FindBy(xpath = "//input[@id='MRI_1CHANGE']")
	WebElement SaveButton;
	

	
	public void transferToAnotherSuite() {

		Utility_Functions.xSwitchtoFrame(driver, RenewThisLeaseTable);
		System.out.println("Switched to frame of the table");
		driver.switchTo().frame(driver.findElement(By.id("modalPage_0_iframe")));
		Utility_Functions.xSendKeys(driver, PreviousLeaseAlternateStopBillingDate, dataTable.getData("General_Data", "Param3"));
		System.out.println("PreviousLeaseAlternateStopBillingDate Entered");
		
		Utility_Functions.xSendKeys(driver, SuiteID, dataTable.getData("General_Data", "Param4"));
		System.out.println("SuiteID Entered");
		Utility_Functions.timeWait(2);
		Utility_Functions.xSendKeys(driver, NewLeaseStartDate, dataTable.getData("General_Data", "Param5"));
		System.out.println("NewLeaseStartDate Entered");
		Utility_Functions.xSendKeys(driver, NewLeaseExpirationDate, dataTable.getData("General_Data", "Param3"));
		System.out.println("NewLeaseExpirationDate Entered");
		Utility_Functions.xSelectDropdownByIndex(TransferReason,2);
		System.out.println("TransferReason selected");
		Utility_Functions.timeWait(2);
		Utility_Functions.xClickHiddenElement(driver, SaveButton);
		System.out.println("Save button clicked");
		Utility_Functions.timeWait(2);
		
//		driver.switchTo().alert().accept();
		Utility_Functions.timeWait(2);
		
			
	}
	


}
