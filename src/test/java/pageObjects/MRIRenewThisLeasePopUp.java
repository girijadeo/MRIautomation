package pageObjects;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.DateUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.cognizant.Craft.ReusableLibrary;
import com.cognizant.Craft.ScriptHelper;

import supportLibraries.Utility_Functions;

public class MRIRenewThisLeasePopUp extends ReusableLibrary {
	public MRIRenewThisLeasePopUp(ScriptHelper scriptHelper) {
		super(scriptHelper);
		PageFactory.initElements(driver.getWebDriver(), this);
	}
	
	@FindBy(xpath="//input[@name='MRI_1LA_VACVACATE']")
	WebElement PreviousLeaseVacateDate;
	

	@FindBy(xpath="//input[@name='MRI_1LA_ALTSTOP']")
	WebElement AlternateStopBillingDate;
	
	@FindBy(xpath="//input[@name='MRI_1LA_RENWSTART']")
	WebElement NewLeaseStartDate;
	
	@FindBy(xpath="//input[@name='MRI_1LA_RENWEXPIRE']")
	WebElement NewLeaseExpirationDate;
	
	@FindBy(xpath="//select[@name='MRI_1LA_GENCODE']")
	WebElement RenewalReason;
	
	@FindBy(xpath = "//*[@xmlns='http://www.w3.org/1999/xhtml' and @class='Grid Default']")
	WebElement RenewThisLeaseTable;
	
	@FindBy(xpath = "//input[@id='MRI_1CHANGE']")
	WebElement SaveButton;

	public String addDaysToDate(String date_to_change, int numberOfDaysToAdd) throws ParseException {
		Date date_to_change_DateFormat =new SimpleDateFormat("MM/dd/yyyy").parse(date_to_change);
		
		Calendar c = Calendar.getInstance();  
		c.setTime(date_to_change_DateFormat);
		c.add(Calendar.DATE, 2);
		date_to_change_DateFormat = c.getTime();

		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String changedDate = df.format(date_to_change_DateFormat);
		
		return changedDate;
	}

	
	public void renewThisLease()throws Exception  {
		
		Utility_Functions.xSwitchtoFrame(driver, RenewThisLeaseTable);
		System.out.println("Switched to frame of the table");
		driver.switchTo().frame(driver.findElement(By.id("modalPage_0_iframe")));
		
		String previousLeaseVacateDate = PreviousLeaseVacateDate.getAttribute("value");
		String newLeaseStartDate = NewLeaseStartDate.getAttribute("value");
		
		String strAlternateStopBillingDate = addDaysToDate(previousLeaseVacateDate,2);
		String strNewLeaseExpirationDate = addDaysToDate(newLeaseStartDate,2);
		
		Utility_Functions.xSendKeys(driver, AlternateStopBillingDate, strAlternateStopBillingDate);
		System.out.println("AlternateStopBillingDate Entered");
		Utility_Functions.xSendKeys(driver, NewLeaseExpirationDate, strNewLeaseExpirationDate);
		System.out.println("NewLeaseExpirationDate Entered");
		
		Utility_Functions.xSelectDropdownByIndex(RenewalReason,2);
		System.out.println("RenewalReason selected");
		Utility_Functions.timeWait(2);
		Utility_Functions.xClickHiddenElement(driver, SaveButton);
		System.out.println("Save button clicked");
		Utility_Functions.timeWait(2);
			
	}
	


}
