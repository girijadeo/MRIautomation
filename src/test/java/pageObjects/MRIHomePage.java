package pageObjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.cognizant.Craft.ReusableLibrary;
import com.cognizant.Craft.ScriptHelper;

import supportLibraries.Utility_Functions;

public class MRIHomePage extends ReusableLibrary {
	public MRIHomePage(ScriptHelper scriptHelper) {
		super(scriptHelper);
		PageFactory.initElements(driver.getWebDriver(), this);
	}
	
	@FindBy(xpath="//*[@id='top']")
	WebElement MainMenu;
	
	@FindBy(xpath="//*[@id='CM_HOME4_PARENT']")
	WebElement CommercialManagement;

	
	public void clickMainMenu() {

		Utility_Functions.impWait(driver, 2);
		Utility_Functions.xHoverElementclicks(MainMenu, driver);
//		Utility_Functions.xClick(driver, MainMenu, false);
		System.out.println("MainMenu clicked");
	}
	
	public void clickCommercialManagement() {
		Utility_Functions.impWait(driver, 5);
//		Utility_Functions.xHoverElementclicks(CommercialManagement, driver);
//		Utility_Functions.xHoverElementclicks(CommercialManagement, driver);
		
		Utility_Functions.xWaitForElementClickable(driver, CommercialManagement, 5);
		Utility_Functions.xClick(driver, CommercialManagement, true);
		System.out.println("CommercialManagement clicked");
	}
	
	
	public void invokeApplication() {
//		driver.get(dataTable.getData("General_Data", "URL"));
//		driver.get("https://www.google.com");
		System.out.println("OK");
//		Assert.assertTrue(Utility_Functions.xWaitForElementVisible(driver, MainMenu, 10), "Application not invoked, Main menu option not displayed");
		System.out.println("Application invoked");
        
	}

}
