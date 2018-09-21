package pageObjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.cognizant.Craft.ReusableLibrary;
import com.cognizant.Craft.ScriptHelper;

import supportLibraries.Utility_Functions;

public class MRICommercialManagementMenuPage extends ReusableLibrary {
	public MRICommercialManagementMenuPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
		PageFactory.initElements(driver.getWebDriver(), this);
	}
	
	@FindBy(xpath="//*[text()='Lease Maintenance']")
	WebElement LeaseMaintenance;
	
	@FindBy(xpath="//*[text()='Lease Setup']")
	WebElement LeaseSetup;

	
	public void clickLeaseMaintenance() {

		Utility_Functions.impWait(driver, 2);
		Utility_Functions.xWaitForElementClickable(driver, LeaseMaintenance, 5);
		Utility_Functions.xHoverElementclicks(LeaseMaintenance, driver);
		System.out.println("LeaseMaintenance clicked");
	}
	
	
	public void clickLeaseSetup() {
		Utility_Functions.impWait(driver, 5);
		Utility_Functions.xHoverElementclicks(LeaseSetup, driver);
		System.out.println("LeaseSetup clicked");
		Utility_Functions.timeWait(3);
	}



}
