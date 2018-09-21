package pageObjects;

import java.util.ArrayList;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.cognizant.Craft.ReusableLibrary;
import com.cognizant.Craft.ScriptHelper;
import com.cognizant.framework.Status;

import supportLibraries.Utility_Functions;

/**
 * Page Object Class for Login Page
 * 
 * @author Vishnuvardhan
 *
 */

public class LoginPage extends ReusableLibrary {

	/*
	 * Constructor to initialize the business component library
	 * 
	 * @param scriptHelper The {@link ScriptHelper} object passed from the
	 * {@link DriverScript}
	 */
	public LoginPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
		PageFactory.initElements(driver.getWebDriver(), this);
	}

	@FindBy(id = "username")
	WebElement txt_userName;

	@FindBy(id = "password")
	WebElement txt_password;

	@FindBy(id = "Login")
	WebElement btn_LogIn;

	@FindBy(xpath = "//div[@class='bBottom']//span[text()='Home']")
	WebElement menu_Home;

	@FindBy(xpath = "//button[contains(@class,'forceHeaderButton') and contains(@class,'oneUserProfileCardTrigger')]")
	WebElement logOutButton;

	@FindBy(xpath = "//div[contains(@class,'profile')]//a[contains(text(),'Log') and contains(text(),'Out')]")
	WebElement logOut;

	@FindBy(xpath = "//a[@class='continue'][text()='Continue']")
	WebElement continueLink;

	@FindBy(xpath = "//*[@id='userNavLabel']")
	WebElement userName;

	@FindBy(xpath = "//*[@id='userNav-menuItems']/a[text()='Switch to Lightning Experience']")
	WebElement switchLightningExperience;

	@FindBy(xpath = "//h2[@id='header']")
	WebElement changePasswordHeader;

	@FindBy(xpath = "//h2[@id='header'][text()='Change Your Password']")
	WebElement changeExpiredPasswordHeader;

	@FindBy(xpath = "//input[@id='currentpassword']")
	WebElement currentPassword;

	@FindBy(xpath = "//input[@id='newpassword']")
	WebElement newPassword;

	@FindBy(xpath = "//input[@id='confirmpassword']")
	WebElement confirmNewPassword;

	@FindBy(xpath = "//input[@id='answer']")
	WebElement securityAnswer;

	@FindBy(xpath = "//*[@id='password-button']")
	WebElement changePassword;

	@FindBy(xpath = "//div[@id='error']")
	WebElement errorMessage;

	@FindBy(xpath = "//a[@class='continue'][text()='Continue']")
	WebElement continueButtonMaiteinance;

	@FindBy(xpath = "//span[contains(text(),'Sandbox')]")
	WebElement sandbox;
	
	/**
	 * Validating the browser launch functionality
	 * 
	 * @author Vishnuvardhan
	 *
	 */

	// public String environment = properties.getProperty("RunEnvironment");
	public static String environment = System.getProperty("RunEnvironment");

	public String initializeEnvironment() {
		try {
			if (environment.equals(null)) {

			} /*
				 * else { System.out.
				 * println("Environment is set as per the parameters passed from Jenkins:::"
				 * + environment );
				 * report.updateTestLog("Intialize Environment",
				 * "Environment is set as per the parameters passed from Jenkins:::"
				 * , Status.PASS); }
				 */
		} catch (Exception e) {
			environment = properties.getProperty("RunEnvironment");
			System.out.println(
					"Environment is set as per the RunEnvironment value in Global Settings file:::" + environment);
		}
		return environment;
	}

	public void invokeApplication() {
		String environment = initializeEnvironment();
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("Application" + environment + "Url"),
				Status.PASS);
/*		if (environment.equals("UAT")) {
			driver.get(properties.getProperty("ApplicationUATUrl"));
		} else if (environment.equals("UAT2")) {
			driver.get(properties.getProperty("ApplicationUAT2Url"));
		} else if (environment.equals("FTE")) {
			driver.get(properties.getProperty("ApplicationFTEUrl"));
		} else if (environment.equals("FTE2")) {
			driver.get(properties.getProperty("ApplicationFTE2Url"));
		}*/
		String sApplicationUrl = properties.getProperty("ApplicationUrl");
		driver.get(sApplicationUrl);
		Utility_Functions.xWaitForElementPresent(driver, txt_userName, 10);
	}

	/**
	 * Validating the Login functionality
	 * 
	 * @author Vishnuvardhan
	 *
	 */
	public void login() {
		invokeApplication();
		try {
			if ((environment.equals("UAT")) || (environment.equals("UAT2")) || (environment.equals("FTE"))
					|| (environment.equals("FTE2"))) {
				if (dataTable.getData("General_Data", "TC_ID").contains("Admin")) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"SystemAdminUsername"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("OBAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"OBAMERCSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("OBAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"OBAMERManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("OBAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"OBAMERBroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("OBEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"OBEMEACSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("OBEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"OBEMEAManager"));				
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("OBEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("OccupierClientCare")) && (!dataTable.getData("General_Data", "TC_ID").contains("OBEMEABroker")) && (!dataTable.getData("General_Data", "TC_ID").contains("OBEMEAManager")) && (!dataTable.getData("General_Data", "TC_ID").contains("OBEMEACSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"OBEMEAOccupierClientCare"));				
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("OBEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"OBEMEABroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("OBAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"OBAPACCSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("OBAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"OBAPACManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("OBAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"OBAPACBroker"));

				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ABAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"ABAMERCSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ABAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"ABAMERManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ABAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"ABAMERBroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ABEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"ABEMEACSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ABEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"ABEMEAManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ABEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"ABEMEABroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ABAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"ABAPACCSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ABAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"ABAPACManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ABAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"ABAPACBroker"));

				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMAMERCSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMAMERManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMAMERBroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS")) && (!dataTable.getData("General_Data", "TC_ID").contains("BCP")) 
						&& (!dataTable.getData("General_Data", "TC_ID").contains("Dev")) && (!dataTable.getData("General_Data", "TC_ID").contains("IP") && (!dataTable.getData("General_Data", "TC_ID").contains("CA")
						&& (!dataTable.getData("General_Data", "TC_ID").contains("SuperUser"))))){
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMEMEACSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager")) && (!dataTable.getData("General_Data", "TC_ID").contains("BCP")) 
						&& (!dataTable.getData("General_Data", "TC_ID").contains("Dev"))  && (!dataTable.getData("General_Data", "TC_ID").contains("IP") && (!dataTable.getData("General_Data", "TC_ID").contains("CA")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMEMEAManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")) && (!dataTable.getData("General_Data", "TC_ID").contains("BCP")) 
						&& (!dataTable.getData("General_Data", "TC_ID").contains("Dev"))  && (!dataTable.getData("General_Data", "TC_ID").contains("IP")&& (!dataTable.getData("General_Data", "TC_ID").contains("CA")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMEMEABroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMAPACCSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMAPACManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker") && (!dataTable.getData("General_Data", "TC_ID").contains("NZL") 
						&& (!dataTable.getData("General_Data", "TC_ID").contains("MetroNZL") && (!dataTable.getData("General_Data", "TC_ID").contains("Metro")&& (!dataTable.getData("General_Data", "TC_ID").contains("AUC") 
						&& (!dataTable.getData("General_Data", "TC_ID").contains("AUS") && (!dataTable.getData("General_Data", "TC_ID").contains("NSW") && (!dataTable.getData("General_Data", "TC_ID").contains("ChristChurch")
						&& (!dataTable.getData("General_Data", "TC_ID").contains("MVP") && (!dataTable.getData("General_Data", "TC_ID").contains("India")&& (!dataTable.getData("General_Data", "TC_ID").contains("Japan"))))))))))))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMAPACBroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")&& (dataTable.getData("General_Data", "TC_ID").contains("AUC")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMAPACBrokerAUC"));			
				}else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")&& (dataTable.getData("General_Data", "TC_ID").contains("Japan")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMAPACBrokerJapan"));				
				}else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")&& (dataTable.getData("General_Data", "TC_ID").contains("India")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMAPACBrokerIndia"));				
				}else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")&& (dataTable.getData("General_Data", "TC_ID").contains("AUS")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMAPACBrokerAUS"));				
				}else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")&& (dataTable.getData("General_Data", "TC_ID").contains("ChristChurch")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMAPACBrokerChristChurch"));				
				}else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")&& (dataTable.getData("General_Data", "TC_ID").contains("NSW")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMAPACBrokerNSW"));				
				}else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")&& (dataTable.getData("General_Data", "TC_ID").contains("NZL")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMAPACBrokerNZL"));				
				}else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")&& (dataTable.getData("General_Data", "TC_ID").contains("MetroNZL")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMAPACBrokerMetroNZL"));				
				}else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")&& (dataTable.getData("General_Data", "TC_ID").contains("Metro")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMAPACBrokerMetro"));				
				}else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")&& (dataTable.getData("General_Data", "TC_ID").contains("MVP")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMAPACBrokerMVP"));

				
				}else if ((dataTable.getData("General_Data", "TC_ID").contains("APAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Project")&& (dataTable.getData("General_Data", "TC_ID").contains("Manager")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"APACProjectManager"));

				
				}else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS")) && (dataTable.getData("General_Data", "TC_ID").contains("IP"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMEMEACSSIP"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager")) && (dataTable.getData("General_Data", "TC_ID").contains("IP"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMEMEAManagerIP"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")) && (dataTable.getData("General_Data", "TC_ID").contains("IP"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMEMEABrokerIP"));
				}else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")) && (dataTable.getData("General_Data", "TC_ID").contains("BCP"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMEMEABrokerBCP"));
				}else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")) && (dataTable.getData("General_Data", "TC_ID").contains("BCP"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMEMEAManagerBCP"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")) && (dataTable.getData("General_Data", "TC_ID").contains("CA"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMEMEABrokerCA"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager")) && (dataTable.getData("General_Data", "TC_ID").contains("CA"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMEMEAManagerCA"));
				}else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")) && (dataTable.getData("General_Data", "TC_ID").contains("Dev"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMEMEABrokerDev"));
				}else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager")) && (dataTable.getData("General_Data", "TC_ID").contains("Dev"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMEMEAManagerDev"));
				}else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS")) && (dataTable.getData("General_Data", "TC_ID").contains("Dev"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMEMEACSSDev"));
				}else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS")) && (dataTable.getData("General_Data", "TC_ID").contains("SuperUser"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMEMEACSSSuperUser"));
					
				}else if ((dataTable.getData("General_Data", "TC_ID").contains("VASAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"VASAMERManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("VASAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"VASAMERBroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("VASAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"VASAMERCSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("VASEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"VASEMEAManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("VASEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")&& (!dataTable.getData("General_Data", "TC_ID").contains("SuperUser")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"VASEMEABroker"));				
				}else if ((dataTable.getData("General_Data", "TC_ID").contains("VASEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")&& (dataTable.getData("General_Data", "TC_ID").contains("SuperUser")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"VASEMEABrokerSuperUser"));				
				}  else if ((dataTable.getData("General_Data", "TC_ID").contains("VASEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"VASEMEACSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("VASAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"VASAPACManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("VASAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"VASAPACBroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("VASAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"VASAPACCSS"));
					
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ASAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"ASAMERManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ASAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"ASAMERBroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ASAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"ASAMERCSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ASEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"ASEMEAManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ASEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")&& (!dataTable.getData("General_Data", "TC_ID").contains("SuperUser")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"ASEMEABroker"));
				}else if ((dataTable.getData("General_Data", "TC_ID").contains("ASEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker") && (dataTable.getData("General_Data", "TC_ID").contains("SuperUser")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"ASEMEABrokerSuperUser"));		
				}  else if ((dataTable.getData("General_Data", "TC_ID").contains("ASEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"ASEMEACSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ASAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"ASAPACManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ASAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"ASAPACBroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ASAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"ASAPACCSS"));

				} else if ((dataTable.getData("General_Data", "TC_ID").contains("GWSAMER"))	&& (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"GWSAMERManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("GWSAMER"))	&& (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"GWSAMERBroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("GWSAMER"))	&& (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"GWSAMERCSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("GWSEMEA"))	&& (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"GWSEMEAManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("GWSEMEA"))	&& (dataTable.getData("General_Data", "TC_ID").contains("Broker")&& (!dataTable.getData("General_Data", "TC_ID").contains("SuperUser")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"GWSEMEABroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("GWSEMEA"))	&& (dataTable.getData("General_Data", "TC_ID").contains("Broker")&& (dataTable.getData("General_Data", "TC_ID").contains("SuperUser")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"GWSEMEABrokerSuperUser"));				
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("GWSEMEA"))	&& (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"GWSEMEACSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("GWSAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"GWSAPACManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("GWSAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"GWSAPACBroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("GWSAPAC"))	&& (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"GWSAPACCSS"));

				} else if ((dataTable.getData("General_Data", "TC_ID").contains("DAAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("Data"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"DAAMERData"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("DAEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Data"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"DAEMEAData"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("DAAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Data"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"DAAPACData"));
					
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("AUCAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"AUCAPACBroker"));
					
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")) && (dataTable.getData("General_Data", "TC_ID").contains("BCP"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMEMEABrokerBCP")); 
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager")) && (dataTable.getData("General_Data", "TC_ID").contains("BCP"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMEMEAManagerBCP")); 
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS")) && (dataTable.getData("General_Data", "TC_ID").contains("BCP"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMEMEACSSBCP")); 
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")) && (dataTable.getData("General_Data", "TC_ID").contains("Dev"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMEMEABrokerDev")); 
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager")) && (dataTable.getData("General_Data", "TC_ID").contains("Dev"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMEMEAManagerDev")); 
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS")) && (dataTable.getData("General_Data", "TC_ID").contains("Dev"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment+"CMEMEACSSDev")); 
					
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("OBAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName,properties.getProperty(environment + "OBAMERCSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("OBAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "OBAMERManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("OBAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "OBAMERBroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("OBEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "OBEMEACSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("OBEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "OBEMEAManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("OBEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("OccupierClientCare")) 
						&& (!dataTable.getData("General_Data", "TC_ID").contains("OBEMEABroker")) && (!dataTable.getData("General_Data", "TC_ID").contains("OBEMEAManager"))
						&& (!dataTable.getData("General_Data", "TC_ID").contains("OBEMEACSS"))) { 
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "OBEMEAOccupierClientCare"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("OBEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "OBEMEABroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("OBAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "OBAPACCSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("OBAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "OBAPACManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("OBAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "OBAPACBroker"));
					
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ABAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "ABAMERCSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ABAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "ABAMERManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ABAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "ABAMERBroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ABEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "ABEMEACSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ABEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "ABEMEAManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ABEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "ABEMEABroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ABAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "ABAPACCSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ABAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "ABAPACManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ABAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "ABAPACBroker"));

				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMAMERCSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMAMERManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMAMERBroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))
						&& (!dataTable.getData("General_Data", "TC_ID").contains("BCP")) && (!dataTable.getData("General_Data", "TC_ID").contains("Dev"))
						&& (!dataTable.getData("General_Data", "TC_ID").contains("IP") && (!dataTable.getData("General_Data", "TC_ID").contains("CA") && (!dataTable.getData("General_Data", "TC_ID").contains("SuperUser"))))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMEMEACSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))
						&& (!dataTable.getData("General_Data", "TC_ID").contains("BCP")) && (!dataTable.getData("General_Data", "TC_ID").contains("Dev"))
						&& (!dataTable.getData("General_Data", "TC_ID").contains("IP") && (!dataTable.getData("General_Data", "TC_ID").contains("CA")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMEMEAManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))
						&& (!dataTable.getData("General_Data", "TC_ID").contains("BCP")) && (!dataTable.getData("General_Data", "TC_ID").contains("Dev"))
						&& (!dataTable.getData("General_Data", "TC_ID").contains("IP") && (!dataTable.getData("General_Data", "TC_ID").contains("CA")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMEMEABroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMAPACCSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAPAC"))
						&& (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMAPACManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker") && (!dataTable.getData("General_Data", "TC_ID").contains("NZL") 
						&& (!dataTable.getData("General_Data", "TC_ID").contains("MetroNZL")&& (!dataTable.getData("General_Data", "TC_ID").contains("Metro") && (!dataTable.getData("General_Data", "TC_ID").contains("AUC")
						&& (!dataTable.getData("General_Data", "TC_ID").contains("AUS") && (!dataTable	.getData("General_Data", "TC_ID").contains("NSW") && (!dataTable.getData("General_Data", "TC_ID").contains("ChristChurch")
						&& (!dataTable.getData("General_Data", "TC_ID").contains("MVP"))))))))))) {
					Utility_Functions.xSendKeys(driver, txt_userName,properties.getProperty(environment + "CMAPACBroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker") && (dataTable.getData("General_Data", "TC_ID").contains("AUC")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMAPACBrokerAUC"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker") && (dataTable.getData("General_Data", "TC_ID").contains("AUS")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMAPACBrokerAUS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker") && (dataTable.getData("General_Data", "TC_ID").contains("ChristChurch")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMAPACBrokerChristChurch"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker") && (dataTable.getData("General_Data", "TC_ID").contains("NSW")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMAPACBrokerNSW"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker") && (dataTable.getData("General_Data", "TC_ID").contains("NZL")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMAPACBrokerNZL"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker") && (dataTable.getData("General_Data", "TC_ID").contains("MetroNZL")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMAPACBrokerMetroNZL"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker") && (dataTable.getData("General_Data", "TC_ID").contains("Metro")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMAPACBrokerMetro"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker") && (dataTable.getData("General_Data", "TC_ID").contains("MVP")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMAPACBrokerMVP"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS")) && (dataTable.getData("General_Data", "TC_ID").contains("IP"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMEMEACSSIP"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager")) && (dataTable.getData("General_Data", "TC_ID").contains("IP"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMEMEAManagerIP"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")) && (dataTable.getData("General_Data", "TC_ID").contains("IP"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMEMEABrokerIP"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")) && (dataTable.getData("General_Data", "TC_ID").contains("BCP"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMEMEABrokerBCP"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")) && (dataTable.getData("General_Data", "TC_ID").contains("BCP"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMEMEAManagerBCP"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")) && (dataTable.getData("General_Data", "TC_ID").contains("CA"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMEMEABrokerCA"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager")) && (dataTable.getData("General_Data", "TC_ID").contains("CA"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMEMEAManagerCA"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")) && (dataTable.getData("General_Data", "TC_ID").contains("Dev"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMEMEABrokerDev"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")) && (dataTable.getData("General_Data", "TC_ID").contains("Dev"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMEMEAManagerDev"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")) && (dataTable.getData("General_Data", "TC_ID").contains("Dev"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMEMEACSSDev"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS")) && (dataTable.getData("General_Data", "TC_ID").contains("SuperUser"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMEMEACSSSuperUser"));
					
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("VASAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "VASAMERManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("VASAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "VASAMERBroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("VASAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "VASAMERCSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("VASEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "VASEMEAManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("VASEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker") && (!dataTable.getData("General_Data", "TC_ID").contains("SuperUser")))) {	
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "VASEMEABroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("VASEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker") && (dataTable.getData("General_Data", "TC_ID").contains("SuperUser")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "VASEMEABrokerSuperUser"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("VASEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "VASEMEACSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("VASAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "VASAPACManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("VASAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "VASAPACBroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("VASAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "VASAPACCSS"));

				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ASAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "ASAMERManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ASAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "ASAMERBroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ASAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "ASAMERCSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ASEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "ASEMEAManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ASEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")
								&& (!dataTable.getData("General_Data", "TC_ID").contains("SuperUser")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "ASEMEABroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ASEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker") && (dataTable.getData("General_Data", "TC_ID").contains("SuperUser")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "ASEMEABrokerSuperUser"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ASEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "ASEMEACSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ASAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "ASAPACManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ASAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "ASAPACBroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("ASAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "ASAPACCSS"));

				} else if ((dataTable.getData("General_Data", "TC_ID").contains("GWSAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "GWSAMERManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("GWSAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "GWSAMERBroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("GWSAMER"))	&& (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "GWSAMERCSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("GWSEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "GWSEMEAManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("GWSEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker") && (!dataTable.getData("General_Data", "TC_ID").contains("SuperUser")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "GWSEMEABroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("GWSEMEA"))	&& (dataTable.getData("General_Data", "TC_ID").contains("Broker") && (dataTable.getData("General_Data", "TC_ID").contains("SuperUser")))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "GWSEMEABrokerSuperUser"));

				} else if ((dataTable.getData("General_Data", "TC_ID").contains("GWSEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "GWSEMEACSS"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("GWSAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "GWSAPACManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("GWSAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "GWSAPACBroker"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("GWSAPAC"))	&& (dataTable.getData("General_Data", "TC_ID").contains("CSS"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "GWSAPACCSS"));

				} else if ((dataTable.getData("General_Data", "TC_ID").contains("DAAMER")) && (dataTable.getData("General_Data", "TC_ID").contains("Data"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "DAAMERData"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("DAEMEA"))  && (dataTable.getData("General_Data", "TC_ID").contains("Data"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "DAEMEAData"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("DAAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Data"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "DAAPACData"));

				} else if ((dataTable.getData("General_Data", "TC_ID").contains("AUCAPAC")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "AUCAPACBroker"));

				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")) && (dataTable.getData("General_Data", "TC_ID").contains("BCP"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMEMEABrokerBCP"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager")) && (dataTable.getData("General_Data", "TC_ID").contains("BCP"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMEMEAManagerBCP"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS")) && (dataTable.getData("General_Data", "TC_ID").contains("BCP"))) {					
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMEMEACSSBCP"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Broker")) && (dataTable.getData("General_Data", "TC_ID").contains("Dev"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMEMEABrokerDev"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager")) && (dataTable.getData("General_Data", "TC_ID").contains("Dev"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMEMEAManagerDev"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("CMEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("CSS")) && (dataTable.getData("General_Data", "TC_ID").contains("Dev"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "CMEMEACSSDev"));
				
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("FRANEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "FRANEMEAManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("FDIGEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "FDIGEMEAManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("FDIREMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Manager"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "FDIREMEAManager"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("FDIGEMEA")) && (dataTable.getData("General_Data", "TC_ID").contains("Data"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "FDIGEMEAData"));
				
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("DAASIA")) && (dataTable.getData("General_Data", "TC_ID").contains("Data"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "DAASIAData"));
				} else if ((dataTable.getData("General_Data", "TC_ID").contains("DAPACI")) && (dataTable.getData("General_Data", "TC_ID").contains("Data"))) {
					Utility_Functions.xSendKeys(driver, txt_userName, properties.getProperty(environment + "DAPACIData"));
				}
			}
			Utility_Functions.timeWait(1);
			if (environment.equals("UAT")) {
				if (dataTable.getData("General_Data", "TC_ID").contains("Admin")) {
					Utility_Functions.xSendKeys(driver, txt_password, properties.getProperty("UATAdminPassword"));
				} else {
					Utility_Functions.xSendKeys(driver, txt_password, properties.getProperty("UATPassword"));
				}
			} else if (environment.equals("FTE")) {
				if (dataTable.getData("General_Data", "TC_ID").contains("Admin")) {
					Utility_Functions.xSendKeys(driver, txt_password, properties.getProperty("FTEAdminPassword"));
				} else {
					Utility_Functions.xSendKeys(driver, txt_password, properties.getProperty("FTEPassword"));
				}
			} else if (environment.equals("FTE2")) {
				if (dataTable.getData("General_Data", "TC_ID").contains("Admin")) {
					Utility_Functions.xSendKeys(driver, txt_password, properties.getProperty("FTE2AdminPassword"));
				} else {
					Utility_Functions.xSendKeys(driver, txt_password, properties.getProperty("FTE2Password"));
				}
			}
			if (environment.equals("UAT2")) {
				if (dataTable.getData("General_Data", "TC_ID").contains("Admin")) {
					Utility_Functions.xSendKeys(driver, txt_password, properties.getProperty("UAT2AdminPassword"));
				} else {
					Utility_Functions.xSendKeys(driver, txt_password, properties.getProperty("UAT2Password"));
				}
			}
			// Utility_Functions.timeWait(1);
			report.updateTestLog("Login", "Click the sign-in button", Status.PASS);
			Utility_Functions.xClick(driver, btn_LogIn, true);
			Utility_Functions.timeWait(1);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			if (continueLink.isDisplayed()) {
				Utility_Functions.xClick(driver, continueLink, true);
				report.updateTestLog("Scheduled Maintenance Window",
						"Clicking on continue link from the scheduled maintenance window", Status.PASS);
			} else {
				report.updateTestLog("Scheduled Maintenance Window",
						"Failed to click on continue link from the scheduled maintenance window", Status.FAIL);
			}
		}
	}


	/**
	 * Validating the Login functionality
	 * 
	 * @author Vishnuvardhan
	 *
	 */

	public void verifyLoginSuccessful() {
		try {
			try {
				try {
					Utility_Functions.xWaitForElementPresent(driver, menu_Home, 3);
					Utility_Functions.xWaitForElementPresent(driver, sandbox, 3);
					Utility_Functions.xClick(driver, sandbox, true);
					report.updateTestLog("Verify Login", "Login is successful", Status.PASS);
				} catch (Exception e) {
					if (continueButtonMaiteinance.isDisplayed()) {
						Utility_Functions.xClick(driver, continueButtonMaiteinance, true);
						report.updateTestLog("Verify Login", "Scheduled Maintenance Notification popped up",
								Status.PASS);
					}
				}
			} catch (Exception e) {
				Utility_Functions.xWaitForElementPresent(driver, userName, 3);
				Utility_Functions.xClick(driver, userName, true);
				Utility_Functions.timeWait(2);
				Utility_Functions.xWaitForElementPresent(driver, switchLightningExperience, 3);
				Utility_Functions.xClick(driver, switchLightningExperience, true);
				report.updateTestLog("Verify Login", "Switched to Lightning Experience Page", Status.PASS);
				Utility_Functions.xWaitForElementPresent(driver, menu_Home, 3);
				report.updateTestLog("Verify Login", "Login is successful", Status.PASS);
			}
		} catch (Exception e) {
			try {
				if (errorMessage.isDisplayed()) {
					report.updateTestLog("Verify Login",
							"UserName and Password entered are invalid please check the credentials:::",
							Status.WARNING);
					driver.close();
				}
			} catch (Exception e1) {
				System.out.println("Login wasn't successful:::");
				driver.close();
			}
			/*
			 * frameworkParameters.setStopExecution(true); throw new
			 * FrameworkException("Verify Login", "Login failed");
			 */
		}
	}

	/**
	 * Validating the logout functionality
	 * 
	 * @author Vishnuvardhan
	 *
	 */

	public void logout() {
		try {
			Utility_Functions.xWaitForElementPresent(driver, logOutButton, 3);
			Utility_Functions.xClick(driver, logOutButton, true);
			Utility_Functions.xWaitForElementPresent(driver, logOut, 3);
			Utility_Functions.xClick(driver, logOut, true);
			report.updateTestLog("Verify Logout", "User has been logged out successfully:::", Status.PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Function for Change Passwords
	 * 
	 * @author Vishnuvardhan
	 *
	 */

	public void changePassword() {
		userNames();
		String sCurrentPassword = dataTable.getData("General_Data", "Password");
		if ((environment.equals("UAT")) || (environment.equals("UAT2")) || (environment.equals("FTE"))
				|| (environment.equals("FTE2"))) {
			for (int i = 0; i < userNamesList.size(); i++) {
				Utility_Functions.timeWait(1);
				invokeApplication();
				String userName = userNamesList.get(i);
				Utility_Functions.xSendKeys(driver, txt_userName, userName);
				Utility_Functions.xWaitForElementPresent(driver, txt_password, 3);
				Utility_Functions.xSendKeys(driver, txt_password, sCurrentPassword);
				Utility_Functions.xWaitForElementPresent(driver, btn_LogIn, 3);
				Utility_Functions.xClick(driver, btn_LogIn, true);
				try {
					Utility_Functions.xWaitForElementPresent(driver, changePasswordHeader, 3);
				} catch (Exception e) {
					if (continueLink.isDisplayed()) {
						Utility_Functions.xClick(driver, continueLink, true);
						report.updateTestLog("Scheduled Maintenance Window", "Clicking on continue link from the scheduled maintenance window", Status.PASS);
					}
				}
				Utility_Functions.xWaitForElementPresent(driver, currentPassword, 3);
				Utility_Functions.xSendKeys(driver, currentPassword, sCurrentPassword);
				Utility_Functions.xWaitForElementPresent(driver, newPassword, 3);
				Utility_Functions.xSendKeys(driver, newPassword, dataTable.getData("General_Data", "NewPassword"));
				Utility_Functions.xWaitForElementPresent(driver, confirmNewPassword, 3);
				Utility_Functions.xSendKeys(driver, confirmNewPassword,
						dataTable.getData("General_Data", "NewPassword"));
				/*
				 * Utility_Functions.xScrollWindowOnce(driver);
				 * Utility_Functions.timeWait(1);
				 * Utility_Functions.xScrollWindowTop(driver);
				 * Utility_Functions.timeWait(1);
				 */
				Utility_Functions.xWaitForElementPresent(driver, securityAnswer, 3);
				Utility_Functions.xSendKeys(driver, securityAnswer,
						dataTable.getData("General_Data", "SecurityAnswer"));
				Utility_Functions.xWaitForElementPresent(driver, changePassword, 3);
				Utility_Functions.xClick(driver, changePassword, true);
				report.updateTestLog("Verify Change Password",
						"Password has been changed successfully:::" + dataTable.getData("General_Data", "NewPassword"),
						Status.PASS);
				Utility_Functions.timeWait(4);
				logout();
				System.out.println("Password has been changed successfully:::" + userName);
				Utility_Functions.timeWait(3);
				// driver.quit();
			}
		}
	}

	/**
	 * Function for Change Passwords
	 * 
	 * @author Vishnuvardhan
	 *
	 */

	public void changeExpiredPassword() {
		userNames();
		String sCurrentPassword = dataTable.getData("General_Data", "Password");
		if ((environment.equals("UAT")) || (environment.equals("UAT2")) || (environment.equals("FTE"))
				|| (environment.equals("FTE2"))) {
			for (int i = 0; i < userNamesList.size(); i++) {
				Utility_Functions.timeWait(1);
				invokeApplication();
				Utility_Functions.timeWait(3);
				String userName = userNamesList.get(i);
				Utility_Functions.xWaitForElementPresent(driver, txt_userName, 3);
				Utility_Functions.xSendKeys(driver, txt_userName, userName);
				Utility_Functions.xWaitForElementPresent(driver, txt_password, 3);
				Utility_Functions.xSendKeys(driver, txt_password, sCurrentPassword);
				Utility_Functions.xWaitForElementPresent(driver, btn_LogIn, 3);
				Utility_Functions.xClick(driver, btn_LogIn, true);
				try {
					Utility_Functions.xWaitForElementPresent(driver, changeExpiredPasswordHeader, 3);
					Utility_Functions.xWaitForElementPresent(driver, newPassword, 3);
					Utility_Functions.xSendKeys(driver, newPassword, dataTable.getData("General_Data", "NewPassword"));
					Utility_Functions.xWaitForElementPresent(driver, confirmNewPassword, 3);
					Utility_Functions.xSendKeys(driver, confirmNewPassword,
							dataTable.getData("General_Data", "NewPassword"));
					Utility_Functions.xWaitForElementPresent(driver, changePassword, 3);
					Utility_Functions.xClick(driver, changePassword, true);
					report.updateTestLog(
							"Verify Change Password", "Expired Password has been changed successfully:::"
									+ userNamesList.get(i) + dataTable.getData("General_Data", "NewPassword"),
							Status.PASS);
					Utility_Functions.timeWait(4);
					logout();
					Utility_Functions.timeWait(3);
				} catch (Exception e) {
					Utility_Functions.timeWait(4);
					logout();
					report.updateTestLog("Verify Change Password",
							"Password didn't get expired:::" + userNamesList.get(i), Status.PASS);
					Utility_Functions.timeWait(3);
				}
			}
		}
	}

	/**
	 * User Names for different environments
	 * 
	 * @author Vishnuvardhan
	 *
	 */
	public static ArrayList<String> userNamesList = new ArrayList<String>();

	public void userNames() {
		String environment = initializeEnvironment();
		environment = environment.toLowerCase();

		userNamesList.add("testuser1@cbre.com.crm." + environment);
		userNamesList.add("testuser10@cbre.com.crm." + environment);
		userNamesList.add("testuser11@cbre.com.crm." + environment);
		userNamesList.add("testuser12@cbre.com.crm." + environment);
		userNamesList.add("testuser13@cbre.com.crm." + environment);
		userNamesList.add("testuser15@cbre.com.crm." + environment);
		userNamesList.add("testuser16@cbre.com.crm." + environment);
		userNamesList.add("testuser17@cbre.com.crm." + environment);
		userNamesList.add("testuser18@cbre.com.crm." + environment);
		userNamesList.add("testuser19@cbre.com.crm." + environment);
		userNamesList.add("testuser2@cbre.com.crm." + environment);
		userNamesList.add("testuser20@cbre.com.crm." + environment);
		userNamesList.add("testuser21@cbre.com.crm." + environment);
		userNamesList.add("testuser22@cbre.com.crm." + environment);
		userNamesList.add("testuser23@cbre.com.crm." + environment);
		userNamesList.add("testuser24@cbre.com.crm." + environment);
		userNamesList.add("testuser25@cbre.com.crm." + environment);
		userNamesList.add("testuser26@cbre.com.crm." + environment);
		userNamesList.add("testuser27@cbre.com.crm." + environment);
		userNamesList.add("testuser28@cbre.com.crm." + environment);
		userNamesList.add("testuser29@cbre.com.crm." + environment);
		userNamesList.add("testuser3@cbre.com.crm." + environment);
		userNamesList.add("testuser30@cbre.com.crm." + environment);
		userNamesList.add("testuser31@cbre.com.crm." + environment);
		userNamesList.add("testuser32@cbre.com.crm." + environment);
		userNamesList.add("testuser36@cbre.com.crm." + environment);
		userNamesList.add("testuser37@cbre.com.crm." + environment);
		userNamesList.add("testuser38@cbre.com.crm." + environment);
		userNamesList.add("testuser39@cbre.com.crm." + environment);
		userNamesList.add("testuser4@cbre.com.crm." + environment);
		userNamesList.add("testuser40@cbre.com.crm." + environment);
		userNamesList.add("testuser41@cbre.com.crm." + environment);
		userNamesList.add("testuser45@cbre.com.crm." + environment);
		userNamesList.add("testuser46@cbre.com.crm." + environment);
		userNamesList.add("testuser47@cbre.com.crm." + environment);
		userNamesList.add("testuser48@cbre.com.crm." + environment);
		userNamesList.add("testuser49@cbre.com.crm." + environment);
		userNamesList.add("testuser5@cbre.com.crm." + environment);
		userNamesList.add("testuser50@cbre.com.crm." + environment);
		userNamesList.add("testuser51@cbre.com.crm." + environment);
		userNamesList.add("testuser6@cbre.com.crm." + environment);
		userNamesList.add("testuser62@cbre.com.crm." + environment);
		userNamesList.add("testuser65@cbre.com.crm." + environment);
		userNamesList.add("testuser66@cbre.com.crm." + environment);
		userNamesList.add("testuser67@cbre.com.crm." + environment);
		userNamesList.add("testuser68@cbre.com.crm." + environment);
		userNamesList.add("testuser69@cbre.com.crm." + environment);
		userNamesList.add("testuser7@cbre.com.crm." + environment);
		userNamesList.add("testuser70@cbre.com.crm." + environment);
		userNamesList.add("testuser71@cbre.com.crm." + environment);
		userNamesList.add("testuser72@cbre.com.crm." + environment);
		userNamesList.add("testuser73@cbre.com.crm." + environment);
		userNamesList.add("testuser74@cbre.com.crm." + environment);
		userNamesList.add("testuser75@cbre.com.crm." + environment);
		userNamesList.add("testuser76@cbre.com.crm." + environment);
		userNamesList.add("testuser78@cbre.com.crm." + environment);
		userNamesList.add("testuser79@cbre.com.crm." + environment);
		userNamesList.add("testuser8@cbre.com.crm." + environment);
		userNamesList.add("testuser80@cbre.com.crm." + environment);
		userNamesList.add("testuser81@cbre.com.crm." + environment);
		userNamesList.add("testuser82@cbre.com.crm." + environment);
		userNamesList.add("testuser83@cbre.com.crm." + environment);
		userNamesList.add("testuser84@cbre.com.crm." + environment);
		userNamesList.add("testuser85@cbre.com.crm." + environment);
		userNamesList.add("testuser86@cbre.com.crm." + environment);
		userNamesList.add("testuser87@cbre.com.crm." + environment);
		userNamesList.add("testuser88@cbre.com.crm." + environment);
		userNamesList.add("testuser9@cbre.com.crm." + environment);
		userNamesList.add("testuser90@cbre.com.crm." + environment);
		userNamesList.add("testuser91@cbre.com.crm." + environment);
		userNamesList.add("testuser92@cbre.com.crm." + environment);
		userNamesList.add("testuser93@cbre.com.crm." + environment);
		userNamesList.add("testuser94@cbre.com.crm." + environment);
		userNamesList.add("testuser95@cbre.com.crm." + environment);
		userNamesList.add("testuser96@cbre.com.crm." + environment);
		userNamesList.add("testuser97@cbre.com.crm." + environment);
		userNamesList.add("testuser98@cbre.com.crm." + environment);
		userNamesList.add("testuser99@cbre.com.crm." + environment);
		userNamesList.add("testuser100@cbre.com.crm." + environment);
		
		System.out.println("User Names List are::::" + userNamesList);
	}

	/**
	 * Function for verifying the User ID's are working or not
	 * 
	 * @author Vishnuvardhan
	 *
	 */
	static ArrayList<String> userIDsNotWorking = new ArrayList<String>();
	static ArrayList<String> userIDsWorking = new ArrayList<String>();

	public void verifyUserIDs() {
		userNames();
		int count = 0;
		try {
			if ((environment.equals("UAT")) || (environment.equals("UAT2")) || (environment.equals("FTE"))
					|| (environment.equals("FTE2"))) {
				for (int i = 0; i < userNamesList.size(); i++) {
					Utility_Functions.timeWait(1);
					invokeApplication();
					String sUserName = userNamesList.get(i);
					Utility_Functions.xWaitForElementPresent(driver, txt_userName, 3);
					Utility_Functions.xSendKeys(driver, txt_userName, sUserName);
					Utility_Functions.xWaitForElementPresent(driver, txt_password, 3);
					Utility_Functions.xSendKeys(driver, txt_password, properties.getProperty(environment + "Password"));
					Utility_Functions.xWaitForElementPresent(driver, btn_LogIn, 3);
					Utility_Functions.xClick(driver, btn_LogIn, true);
					try {
						try {
							Utility_Functions.xWaitForElementPresent(driver, menu_Home, 3);
							report.updateTestLog("Verify Login", "Login is successful", Status.PASS);
						} catch (Exception e) {
							Utility_Functions.xWaitForElementPresent(driver, userName, 3);
							Utility_Functions.xClick(driver, userName, true);
							Utility_Functions.timeWait(2);
							Utility_Functions.xWaitForElementPresent(driver, switchLightningExperience, 3);
							Utility_Functions.xClick(driver, switchLightningExperience, true);
							report.updateTestLog("Verify Login", "Switched to Lightning Experience Page", Status.PASS);
							Utility_Functions.xWaitForElementPresent(driver, menu_Home, 3);
							report.updateTestLog("Verify Login", "Login is successful", Status.PASS);
						}
						count++;
					} catch (Exception e) {
						report.updateTestLog("Verify Login",
								sUserName + "  :::isn't working unable to login" + "---Count is:::" + count,
								Status.FAIL);
						userIDsNotWorking.add(sUserName);
					}
					report.updateTestLog("Verify Login",
							sUserName + "  :::has been logged in successfully" + "---Count is:::" + count, Status.PASS);
					userIDsWorking.add(sUserName);
					Utility_Functions.timeWait(4);
					logout();
					// Utility_Functions.timeWait(3);
				}
				if (userNamesList.size() == count) {
					report.updateTestLog("Verify Login", "All the UserID's are working successfully", Status.PASS);
				} else {
					report.updateTestLog("Verify Login", "Not all the UserID's are working successfully", Status.FAIL);
				}
			}
			System.out.println("User ID's which aren't working are::" + userIDsNotWorking);
			report.updateTestLog("Verify Login", "User IDs which aren't working are::::" + userIDsNotWorking,
					Status.WARNING);
			report.updateTestLog("Verify Login", "User IDs which are working successfully::::" + userIDsWorking,
					Status.PASS);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
