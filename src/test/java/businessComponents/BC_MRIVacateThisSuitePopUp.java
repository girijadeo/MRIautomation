package businessComponents;

import com.cognizant.Craft.ReusableLibrary;
import com.cognizant.Craft.ScriptHelper;


import pageObjects.MRIVacateThisSuitePopUp;


public class BC_MRIVacateThisSuitePopUp extends ReusableLibrary{

	public BC_MRIVacateThisSuitePopUp(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}
	
	MRIVacateThisSuitePopUp VS = new MRIVacateThisSuitePopUp(scriptHelper);
	
	public void bc_vacateThisSuite() throws InterruptedException {
		VS.vacateThisSuite();
	}

	
}