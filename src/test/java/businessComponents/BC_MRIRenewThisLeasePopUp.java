package businessComponents;

import com.cognizant.Craft.ReusableLibrary;
import com.cognizant.Craft.ScriptHelper;

import pageObjects.MRIRenewThisLeasePopUp;


public class BC_MRIRenewThisLeasePopUp extends ReusableLibrary{

	public BC_MRIRenewThisLeasePopUp(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}
	
	MRIRenewThisLeasePopUp RL = new MRIRenewThisLeasePopUp(scriptHelper);
	
	public void bc_renewThisLease() throws Exception {
		RL.renewThisLease();
	}

	
}