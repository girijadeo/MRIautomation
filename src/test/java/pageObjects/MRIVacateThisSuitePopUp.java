package pageObjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.cognizant.Craft.ReusableLibrary;
import com.cognizant.Craft.ScriptHelper;
import com.cognizant.framework.Status;

import supportLibraries.Utility_Functions;

public class MRIVacateThisSuitePopUp extends ReusableLibrary {
	public MRIVacateThisSuitePopUp(ScriptHelper scriptHelper) {
		super(scriptHelper);
		PageFactory.initElements(driver.getWebDriver(), this);
	}


	@FindBy(xpath="//input[@name='MRI_1LA_ALTSTOP']")
	WebElement AlternateStopBillingDate;
	
	@FindBy(xpath="//input[@name='MRI_1LA_RENWEXPIRE']")
	WebElement NewLeaseExpirationDate;
	
	@FindBy(xpath="//select[@name='MRI_1LA_GENCODE']")
	WebElement RenewalReason;
	
	@FindBy(xpath = "//*[@xmlns='http://www.w3.org/1999/xhtml' and @class='Grid Default']")
	WebElement VacateThisSuiteTable;
	
	@FindBy(xpath = "//input[@id='MRI_1MRIX_3']")
	WebElement SaveButton;
	
	@FindBy(xpath="//div[@id = 'MRI_SUB1']//*[@id='MRI_1MRIX_16_TABCATG4']")
	WebElement LeaseAdministration;
	
//	@FindBy(xpath="//div[@id = 'content']")
//	WebElement ContentWindow;
	

	
	public void vacateThisSuite() {

		Utility_Functions.xSwitchtoFrame(driver, VacateThisSuiteTable);
		System.out.println("Switched to frame of the table");
		driver.switchTo().frame(driver.findElement(By.id("modalPage_0_iframe")));
		Utility_Functions.xSendKeys(driver, AlternateStopBillingDate, dataTable.getData("General_Data", "Param3"));
		System.out.println("AlternateStopBillingDate Entered");
		Utility_Functions.timeWait(2);
		Utility_Functions.xClickHiddenElement(driver, SaveButton);
		System.out.println("Save button clicked");
		Utility_Functions.timeWait(2);
		

//		driver.switchTo().defaultContent();
//		
//		if(ContentWindow.isDisplayed()) {
//			report.updateTestLog("Save Button clicked","Save action successful", Status.PASS);
//		}
//		else {
//			report.updateTestLog("Save Button clicked","Save action failed", Status.FAIL);
//		}
//			
	}
	


}
