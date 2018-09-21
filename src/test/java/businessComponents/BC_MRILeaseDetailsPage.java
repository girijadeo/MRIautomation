package businessComponents;

import com.cognizant.Craft.ReusableLibrary;
import com.cognizant.Craft.ScriptHelper;

import pageObjects.MRILeaseDetailsPage;


public class BC_MRILeaseDetailsPage extends ReusableLibrary{

	public BC_MRILeaseDetailsPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}
	
	MRILeaseDetailsPage LD = new MRILeaseDetailsPage(scriptHelper);
	
	public void bc_clickLeaseAdministration() throws InterruptedException {
		LD.clickLeaseAdministration();
		
	}
	
	public void bc_selectRenewThisLeaseAndContinue() throws InterruptedException{
		LD.selectRenewThisLease();
		LD.clickContinueButton();
	}

	public void bc_selectTransferToAnotherSuiteAndContinue() throws InterruptedException{
		LD.selectTransferToAnotherSuite();
		LD.clickContinueButton();
	}
	
	public void bc_selectVacateThisSuiteAndContinue() throws InterruptedException{
		LD.selectVacateThisSuite();
		LD.clickContinueButton();
	}
	
	public void bc_leaseDetailsPageDisplayed() throws InterruptedException{
		LD.leaseDetailsPageDisplayed();
	}
	
}