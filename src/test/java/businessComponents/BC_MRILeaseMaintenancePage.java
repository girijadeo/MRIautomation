package businessComponents;

import com.cognizant.Craft.ReusableLibrary;
import com.cognizant.Craft.ScriptHelper;

import pageObjects.MRILeaseMaintenancePage;


public class BC_MRILeaseMaintenancePage extends ReusableLibrary{

	public BC_MRILeaseMaintenancePage(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}
	
	MRILeaseMaintenancePage LM = new MRILeaseMaintenancePage(scriptHelper);
	
	public void bc_searchAndSelectOccupantName() throws InterruptedException {
		LM.searchAndSelectOccupantName();
		
		
	}

	
}