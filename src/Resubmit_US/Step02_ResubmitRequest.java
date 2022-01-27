package Resubmit_US;

import org.testng.annotations.Test;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

import lib.Excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Step02_ResubmitRequest {

	private WebDriver driver;
	public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
	public String sheet = "Login";

	// Define the element
	@FindBy(xpath = ".//*[@id='content-main']/p[2]/a")
	private WebElement loginToContractor_Link;
	@FindBy(id = "btn_signin")
	private WebElement Signin_Button;
	@FindBy(xpath = ".//*[@id='desktop']")
	private WebElement Username_Box;
	@FindBy(xpath = ".//*[@id='body']/div[1]/div[2]/div/div/form/input[4]")
	private WebElement Password_Box;
	@FindBy(xpath = ".//*[@id='content-main']/form[1]/div/span/input")
	private WebElement Create_New_Request;

	// Skill Request Page
	@FindBy(id = "FLD_REQ_TYPE")
	private WebElement New_Request;
	@FindBy(id = "FLD_NORMAL_TYPE1")
	private WebElement Time_Materials;
	@FindBy(name = "btnContinue")
	private WebElement Continue;

	// Select Requestor
	@FindBy(id = "FLD_REQST_CO")
	private WebElement Requesting_Company;
	@FindBy(id = "FLD_REQST_ORG")
	private WebElement Requesting_Organization;

	// Job Role/Skill
	// @FindBy ( xpath= ".//*[@id='JRSS_SELECTION1']") private WebElement
	// Priced_JRSS;
	@FindBy(xpath = ".//*[@id='JRSS_SELECTION2']")
	private WebElement Priced_JRSS;
	@FindBy(id = "FLD_JOB_ROLE")
	private WebElement Select_JobRole;
	@FindBy(id = "FLD_SKILL_SET")
	private WebElement Select_SkillSet;

	// Project Creation
	@FindBy(id = "FLD_PROJ_NAME")
	private WebElement Project_Name;
	@FindBy(id = "FLD_CONTACT_NAME")
	private WebElement Customer_Name;
	@FindBy(id = "FLD_IS_GLOBAL_RESOURCE0")
	private WebElement GlobalResource_No;
	@FindBy(id = "FLD_CONTACT_NAME")
	private WebElement CustomerName_Refernce;
	@FindBy(xpath = ".//*[@id='FLD_IS_FED_CONTRACT']")
	private WebElement Govt_FederalContract;
	@FindBy(xpath = ".//*[@name='btnAddContingentMgr']")
	private WebElement Project_Task_Manager_AddButton;
	@FindBy(xpath = ".//*[@id='btnAdd']")
	private WebElement Project_Coordinator_AddButton;
	@FindBy(xpath = ".//*[@id='btnAddRes']")
	private WebElement Response_Reviwer_AddButton;
	@FindBy(xpath = ".//*[@id='btnAddView']")
	private WebElement Viewer_only_AddButton;
	@FindBy(xpath = ".//*[@id='FLD_IS_TIME_APPROVAL_TASK1']")
	private WebElement Will_Manager_perform_TimeApproval_Yes;
	@FindBy(xpath = ".//*[@id='FLD_IS_TIME_APPROVAL_TASK0']")
	private WebElement Will_Manager_perform_TimeApproval_No;
	@FindBy(xpath = ".//*[@name='btnAddTimeApprovalMgr']")
	private WebElement TimeApproverID_AddButton;

	@FindBy(xpath = ".//*[@name='FLD_EMP_WEB_ID']")
	private WebElement Email_Id_TextBox;
	@FindBy(xpath = ".//*[@name='BTN_GO']")
	private WebElement GoButton;
	@FindBy(xpath = ".//*[@id='content-main']/table[4]/tbody/tr[2]/td[1]/a")
	private WebElement Name;
	@FindBy(xpath = ".//input[@name='TEMP TIME APPROVER BUTTON']")
	private WebElement UseAsTimeApprover_Button;
	@FindBy(xpath = ".//input[@name='TEMP MANAGER FOR CR BUTTON']")
	private WebElement UseAsManager_Button;
	@FindBy(xpath = ".//input[@name='TEMP TECH COORD BUTTON']")
	private WebElement UseAsPC_Button;
	@FindBy(xpath = ".//input[@name='TEMP RES REVIEWER BUTTON']")
	private WebElement UseAsRR_Button;
	@FindBy(xpath = ".//input[@name='TEMP VIEW ONLY BUTTON']")
	private WebElement UseAsVO_Button;

	@FindBy(id = "FLD_CLIENT")
	private WebElement Client;
	@FindBy(id = "FLD_BRAND")
	private WebElement Brand;
	@FindBy(id = "FLD_SECTOR")
	private WebElement Sector;
	@FindBy(id = "FLD_INDUSTRY")
	private WebElement Industry;
	@FindBy(id = "fldRegulatedAcc1")
	private WebElement FDA;
	@FindBy(id = "fldRegulatedAcc2")
	private WebElement FFIEC;
	@FindBy(id = "fldRegulatedAcc4")
	private WebElement NREG;
	@FindBy(xpath = " .//*[@value='I'] ")
	private WebElement Accounting_Type;

	// Skill detail Location
	@FindBy(id = "FLD_WRK_LOC_STATE")
	private WebElement State_Region_Province;
	@FindBy(id = "FLD_WRK_LOC_CITY")
	private WebElement City;
	@FindBy(id = "FLD_WRK_LOC")
	private WebElement Work_Location;
	@FindBy(name = "Continue")
	private WebElement Continue_2;
	@FindBy(id = "FLD_ALT_WORK_LOC")
	private WebElement Alternate_Workloc;
	@FindBy(id = "FLD_FLOW_DOWN_TRMS")
	private WebElement FlowDown_Checkbox;
	@FindBy(xpath = "//*[@id='FLD_FLOW_DOWN_RESTRICTION1']")
	private WebElement Flowdown_NoRadio;
	@FindBy(xpath = "//*[@id='FLD_FLOW_DOWN_RESTRICTION']")
	private WebElement Flowdown_YesRadio;

	// Skill detail skill price
	@FindBy(id = "FLD_RQSTD_SKILL_LVL")
	private WebElement Skill_Level;
	@FindBy(id = "FLD_RQSTD_PRICE_LVL")
	private WebElement Price_Level;
	@FindBy(id = "FLD_QTY_SKILL_NEEDED")
	private WebElement Quantity;
	@FindBy(id = "FLD_ST")
	private WebElement ST_Rate;
	@FindBy(id = "FLD_OT")
	private WebElement OT_Rate;

	// supplier selection page
	@FindBy(xpath = "//input[@name='fldSelectSuppForReq' and @value='2']")
	private WebElement secondary_supp;
	@FindBy(xpath = "//input[@name='fldSelectSuppForReq' and @value='2']")
	private WebElement Primary_supp;
	@FindBy(xpath = "//a[contains(text(), 'Deselect all')]")
	private WebElement Deselect_All;
	@FindBy(xpath = "//*[contains(text(), 'WOI US TEST VENDER 1-A')]//preceding-sibling::input[@name='fldSuppliers']")
	private WebElement RadioButton_UStestVendor;
	@FindBy(xpath = "//*[contains(text(), 'MARVEL')]//preceding-sibling::input[@name='fldSuppliers']")
	private WebElement Marvel_sup;
	@FindBy(xpath = "//textarea[@id='FLD_SUPPWARN_SUPPSELJUST']")
	private WebElement SuppSelectionJustification;
	@FindBy(xpath = "//*[@id='FLD_SUPPLIER_LIST1']")
	private WebElement Contract_selection;

	// Skill Summary Page
	@FindBy(xpath = ".//*[@value='Continue to request summary'] ")
	private WebElement ContinueToRequestSummary;

	// Review Skill request
	@FindBy(name = "Submit request")
	private WebElement SubmitRequest;

	// Request Created
	@FindBy(xpath = ".//*[@id='content-main']/table[1]/tbody/tr/td[1]/h1 ")
	private WebElement RequestCreated;
	@FindBy(xpath = ".//*[@id='content-main']/table[1]/tbody/tr/td[1]/h1 ")
	private WebElement RequestCreated_Header;

	// New login code
	@FindBy(xpath = "//span[@id='credentialSignin']")
	private WebElement cred_login;
	// @FindBy ( xpath= "//input[@id='otp-input']") private WebElement otp_box;
	// @FindBy(id = "submit_btn") private WebElement otp_submit_btn;
	@FindBy(xpath = "//input[@name='username']")
	private WebElement login_username;
	@FindBy(xpath = "//input[@name='password']")
	private WebElement login_password;
	@FindBy(id = "login-button")
	private WebElement login_Button;
	@FindBy(id = "otp-input")
	private WebElement passcodeBox;
	@FindBy(id = "submit_btn")
	private WebElement SubmitPasscode;
	@FindBy(xpath = "//label[@id='totp_label']")
	private WebElement Authenticator_App_option;
	@FindBy(xpath = "//input[@id='newTotp-otp-input']")
	private WebElement EnterAccessCode_Box;
	@FindBy(xpath = "//button[contains(text(),'Next: Verify')]")
	private WebElement Verify_Button;
	@FindBy(xpath = ".//div[@id='totp_item']")
	private WebElement Totp_Link;
	@FindBy(xpath = "//input[@id='otp-input']")
	private WebElement OTP_TextBox;
	@FindBy(xpath = "//button[@id='submit_btn']")
	private WebElement OTP_Submit_Button;

	@FindBy(css = ".beeheard-overlay-close")
	private WebElement popup_close;
	
	//Serach request
	
	@FindBy ( xpath = ".//*[@id='left-nav']/div/a[8]" ) private WebElement LHS_Search_tab ;
	@FindBy ( id="FLD_REQ_NUM_SEARCH" ) private WebElement Request_Num_Fld ;
	@FindBy ( name="GO" ) private WebElement Search_GO_btn ;
	@FindBy ( xpath = ".//*[@id='content-main']/form/table[3]/tbody/tr[2]/td[1]/a" ) private WebElement Request_Num_link ;

	@FindBy ( xpath = ".//*[@id='content-main']/form/table[1]/tbody/tr[1]/td[3]/a" ) private WebElement Requested_Skill ;

	// Resubmit request
	@FindBy(xpath = "//input[@name='btnModifyReq']")
	private WebElement ModifyRequest_btn;
	@FindBy(xpath = "//input[@type='submit' and @value='Modify request details']")
	private WebElement ModifyRequestDetails_btn;

	@FindBy(xpath = "//tr[@class='true']//td[3]/a")
	private WebElement skilllinkToModifyRequest;

	@FindBy(xpath = "//a[contains(text(),'Check all ')]")
	private WebElement checkall_chkbox;
	
	@FindBy(id = "btnContinue")
	private WebElement continue1;
	
	
	@FindBy(xpath = "//input[@value='Resubmit request']")
	private WebElement Resubmit_btn;

	// Initialize the web elements
	public Step02_ResubmitRequest(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Function to login to the application
	public void login() throws IOException, InterruptedException {

		WebDriverWait wait00 = new WebDriverWait(driver, 180);
		wait00.until(ExpectedConditions.visibilityOf(cred_login));
		cred_login.click();

		// new login changes
		WebDriverWait wait01 = new WebDriverWait(driver, 180);
		wait01.until(ExpectedConditions.visibilityOf(login_Button));
		wait01.until(ExpectedConditions.elementToBeClickable(login_Button));
		login_username.clear();
		login_username.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 1, 0));
		Thread.sleep(1000);
		login_password.clear();
		login_password.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 1, 1));

		// Shutterbug.shootPage(driver,
		// ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") +
		// "\\src\\test\\resources\\Screens\\GB");
		login_Button.click();
		try {
			WebDriverWait wait02 = new WebDriverWait(driver, 50);
			wait02.until(ExpectedConditions.visibilityOf(Authenticator_App_option));

			Authenticator_App_option.click();
			System.out.println("Authenticator option is selected for authentication");
		} catch (NoSuchElementException | TimeoutException e) {
			System.out.println("Page to select Authenticator app option is not displayed");
			e.printStackTrace();
		}

		try {

			String otpKeyStr = "WEIBVLBVWMVAZZPZ";// csatestus1@c25a0161.toronto.ca.ibm.com
			Totp totp = new Totp(otpKeyStr);
			String twoFactorCode = totp.now();

			OTP_TextBox.sendKeys(twoFactorCode);
			System.out.println("value fetched from box= " + OTP_TextBox.getAttribute("value"));

			OTP_Submit_Button.click();
			System.out.println("clicked on OTP submit button");
			WebDriverWait wait03 = new WebDriverWait(driver, 100);
			wait03.until(ExpectedConditions.visibilityOf(Create_New_Request));
			System.out.println("Title of page= " + driver.getTitle());
		} catch (Exception e) {
			System.out.println("no OTP screen");
			e.printStackTrace();
			WebDriverWait wait04 = new WebDriverWait(driver, 100);
			wait04.until(ExpectedConditions.visibilityOf(Create_New_Request));
			System.out.println("Title of page= " + driver.getTitle());
		}

	}
	
	public void searchRequest()
	{
		WebDriverWait wait11 = new WebDriverWait(driver, 180);
		wait11.until(ExpectedConditions.visibilityOf(LHS_Search_tab));

		LHS_Search_tab.click();

		WebDriverWait wait1 = new WebDriverWait(driver, 160);
		wait1.until(ExpectedConditions.visibilityOf(Request_Num_Fld)); 

		Request_Num_Fld.sendKeys(Excel.getCellValue(xlsFilePath, "Request_creation", 1, 15));
		Search_GO_btn.click();

		WebDriverWait wait2 = new WebDriverWait(driver, 160);
		wait2.until(ExpectedConditions.visibilityOf(Request_Num_link)); 

		Request_Num_link.click();
	}

	public void resubmitReq() {
		WebDriverWait wait05 = new WebDriverWait(driver, 200);
		wait05.until(ExpectedConditions.visibilityOf(ModifyRequest_btn));
		ModifyRequest_btn.click();
	}

	public void modifyRequestDetails() {
		WebDriverWait wait06 = new WebDriverWait(driver, 100);
		wait06.until(ExpectedConditions.visibilityOf(ModifyRequestDetails_btn));
		ModifyRequestDetails_btn.click();
		
		WebDriverWait wait07 = new WebDriverWait(driver, 100);
		wait07.until(ExpectedConditions.visibilityOf(Project_Name));
		Project_Name.clear();
		Project_Name.sendKeys("@Anusha_resubmitReq");
		
		Continue.click();
		
		Resubmit_btn.click();
	}

	public void modifyRequestInfo() {
		WebDriverWait wait08 = new WebDriverWait(driver, 100);
		wait08.until(ExpectedConditions.visibilityOf(skilllinkToModifyRequest));
		skilllinkToModifyRequest.click();

		WebDriverWait wait09 = new WebDriverWait(driver, 100);
		wait09.until(ExpectedConditions.visibilityOf(checkall_chkbox));
		checkall_chkbox.click();

		/*WebDriverWait wait10 = new WebDriverWait(driver, 100);
		wait10.until(ExpectedConditions.visibilityOf(Continue));*/
		Continue.click();

		WebDriverWait wait11 = new WebDriverWait(driver, 160);
		wait11.until(ExpectedConditions.visibilityOf(Skill_Level));
		Select SLDropdown = new Select(Skill_Level);
		SLDropdown.selectByVisibleText("Band 7");

		/*WebDriverWait wait12 = new WebDriverWait(driver, 160);
		wait12.until(ExpectedConditions.visibilityOf(Price_Level));*/
		Select PLDropdown = new Select(Price_Level);
		PLDropdown.selectByVisibleText("Journeyman");

		/*WebDriverWait wait13 = new WebDriverWait(driver, 160);
		wait3.until(ExpectedConditions.visibilityOf(Deselect_All));*/
		Deselect_All.click();
		
		/*WebDriverWait wait13 = new WebDriverWait(driver, 160);
		wait3.until(ExpectedConditions.visibilityOf(Marvel_sup));*/
		Marvel_sup.click();
		
		/*WebDriverWait wait3 = new WebDriverWait(driver, 160);
		wait3.until(ExpectedConditions.visibilityOf(Flowdown_YesRadio));*/
		Flowdown_YesRadio.click();
		continue1.click();
		
		//Continue.click();
		WebDriverWait wait3 = new WebDriverWait(driver, 160);
		wait3.until(ExpectedConditions.visibilityOf(ContinueToRequestSummary));
		ContinueToRequestSummary.click();
		
		Resubmit_btn.click();
		

	}
}
