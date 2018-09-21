package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.cognizant.Craft.ReusableLibrary;
import com.cognizant.Craft.ScriptHelper;

import supportLibraries.Utility_Functions;

public class MRILeaseMaintenancePage extends ReusableLibrary {
	public MRILeaseMaintenancePage(ScriptHelper scriptHelper) {
		super(scriptHelper);
		PageFactory.initElements(driver.getWebDriver(), this);
	}
	

	
	@FindBy(xpath="//*[@id='MRI_1BLDG']")
	WebElement BldgID;
	
	@FindBy(xpath="//*[@id='cmdLookup']")
	WebElement BldgIDLookup;

	@FindBy(xpath="//span[@id='MRI_1MRIX_7']")
	WebElement LeaseMaintenancePageHeader;
	
	@FindBy(xpath="//div[@id='MRI_SUB1']//*[@id='MRI_1OCCSRCH']")
	WebElement OccupantNameTextBox;
	
	@FindBy(xpath="//input[@id='MRI_1MRIX_3']")
	WebElement SearchButton;
	
	@FindBy(xpath="//div[@id='content']")
	WebElement ContentWindow;
	
	@FindBy(xpath="//table[@class='Grid Default']")
	WebElement LeaseSearchResultsTable;
	
//	String OccupantName = dataTable.getData("General_Data", "Param1");

//	@FindBy(linkText = "")
//	WebElement OccupantNameLink;
	
	public void searchAndSelectOccupantName() {
		

		Utility_Functions.xSwitchtoFrame(driver, ContentWindow);
		Utility_Functions.xWaitForElementVisible(driver, OccupantNameTextBox, 7);
		
		Utility_Functions.xSendKeys(driver, OccupantNameTextBox, dataTable.getData("General_Data", "Param1"));
		System.out.println("Occupant Name entered");
		Utility_Functions.xClickButton(driver, SearchButton, true);
		System.out.println("Search button clicked");
//		WebElement OccupantNameLink = new WebElement;
		
		WebElement OccupantNameLink = driver.findElement(By.linkText(dataTable.getData("General_Data", "Param2")));
		Utility_Functions.xClick(driver, OccupantNameLink, true);
		System.out.println("Occupant Name Link clicked");

	}


}
