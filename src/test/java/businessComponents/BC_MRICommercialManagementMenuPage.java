package businessComponents;

import com.cognizant.Craft.ReusableLibrary;
import com.cognizant.Craft.ScriptHelper;

import pageObjects.MRICommercialManagementMenuPage;


public class BC_MRICommercialManagementMenuPage extends ReusableLibrary{

	public BC_MRICommercialManagementMenuPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}
	
	MRICommercialManagementMenuPage CM = new MRICommercialManagementMenuPage(scriptHelper);
	
	public void bc_clickLeaseMaintenance() throws InterruptedException {
		CM.clickLeaseMaintenance();
		
		
	}
	public void bc_clickLeaseSetup() throws InterruptedException {
		CM.clickLeaseSetup();
	}
	
}