package businessComponents;

import com.cognizant.Craft.ReusableLibrary;
import com.cognizant.Craft.ScriptHelper;

import pageObjects.MRITransferToAnotherSuitePopUp;



public class BC_MRITransferToAnotherSuitePopUp extends ReusableLibrary{

	public BC_MRITransferToAnotherSuitePopUp(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}
	
	MRITransferToAnotherSuitePopUp TL = new MRITransferToAnotherSuitePopUp(scriptHelper);
	
	public void bc_transferToAnotherSuite() throws InterruptedException {
		TL.transferToAnotherSuite();
	}

	
}