package pageObjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.cognizant.Craft.ReusableLibrary;
import com.cognizant.Craft.ScriptHelper;
import com.cognizant.framework.Status;

import supportLibraries.Utility_Functions;

public class MRILeaseDetailsPage extends ReusableLibrary {
	public MRILeaseDetailsPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
		PageFactory.initElements(driver.getWebDriver(), this);
	}
	

	@FindBy(xpath="//div[@id = 'MRI_SUB1']//*[@id='MRI_1MRIX_16_TABCATG4']")
	WebElement LeaseAdministration;
	
	@FindBy(xpath="//label[@id='MRI_1L_LADMINOPT1_3']")
	WebElement TransferToAnotherSuiteRadioButton;
	
	@FindBy(xpath="//label[@id='MRI_1L_LADMINOPT1_2']")
	WebElement RenewThisLeaseRadioButton;
	
	@FindBy(xpath="//label[@id='MRI_1L_LADMINOPT1_4']")
	WebElement VacateThisSuiteRadioButton;
	
	@FindBy(xpath="//label[@id='MRI_1L_LADMINOPT1_5']")
	WebElement OccupyAdditionalSuiteRadioButton;
	
	@FindBy(xpath="//*[@title='Continue Administration of the Record']")
	WebElement ContinueButton;
	
	@FindBy(xpath="//div[@id = 'content']")
	WebElement ContentWindow;

	
	public void clickLeaseAdministration() {
		
		Utility_Functions.timeWait(2);
		Utility_Functions.xClickHiddenElement(driver, LeaseAdministration);
		System.out.println("LeaseAdministration clicked");

	}
	
	public void selectRenewThisLease() {
		Utility_Functions.timeWait(2);		
		driver.switchTo().frame(driver.findElement(By.id("IFRAME_MRI_1MRIX_16_T18")));
		Utility_Functions.timeWait(1);
		Utility_Functions.xClickHiddenElement(driver, RenewThisLeaseRadioButton);
		System.out.println("RenewThisLeaseRadioButton clicked");
	}
	
	public void selectTransferToAnotherSuite() {
		Utility_Functions.timeWait(2);		
		driver.switchTo().frame(driver.findElement(By.id("IFRAME_MRI_1MRIX_16_T18")));
		Utility_Functions.timeWait(1);
		Utility_Functions.xClickHiddenElement(driver, TransferToAnotherSuiteRadioButton);
		System.out.println("TransferToAnotherSuiteRadioButton clicked");
	}
	
	public void selectVacateThisSuite() {
		Utility_Functions.timeWait(2);		
		driver.switchTo().frame(driver.findElement(By.id("IFRAME_MRI_1MRIX_16_T18")));
		Utility_Functions.timeWait(1);
		Utility_Functions.xClickHiddenElement(driver, VacateThisSuiteRadioButton);
		System.out.println("VacateThisSuiteRadioButton clicked");
	}
	
	public void clickContinueButton() {
		Utility_Functions.xClickHiddenElement(driver, ContinueButton);
		System.out.println("ContinueButton clicked");	
	}
	
	public boolean isAlertPresent() 
	{ 
	    try 
	    { 
	        driver.switchTo().alert(); 
	        return true; 
	    }   // try 
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }   // catch 
	}   // isAlertPresent()
	
	public void leaseDetailsPageDisplayed() {

		if(isAlertPresent()) {
			driver.switchTo().alert().accept();
			report.updateTestLog("Save Button clicked","Save action failed", Status.FAIL);
		}
		else {
			
			report.updateTestLog("Save Button clicked","Save action successful", Status.PASS);
		}
		
		
	}
	

}
