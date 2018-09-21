package businessComponents;

import com.cognizant.Craft.ReusableLibrary;
import com.cognizant.Craft.ScriptHelper;

import pageObjects.MRIHomePage;


public class BC_MRIHomePage extends ReusableLibrary{

	public BC_MRIHomePage(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}
	
	MRIHomePage home = new MRIHomePage(scriptHelper);
	
	public void invokeApplication() throws InterruptedException{
		home.invokeApplication();
	}
	
	public void bc_clickMainMenu() throws InterruptedException {
		home.clickMainMenu();
		
		
	}
	public void bc_clickCommercialManagement() throws InterruptedException {
		home.clickCommercialManagement();
	}
	
}