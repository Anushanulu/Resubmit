package Resubmit_US_main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Resubmit_US.Step01_CreateRequest;
import Resubmit_US.Step02_AddResponse;
import Resubmit_US.Step02_ResubmitRequest;
import Resubmit_US.Step03_Finalize;

import java.io.IOException;

import org.apache.log4j.Logger;

import lib.Excel;


public class ResubmitRequest
{
	// TestNG logger

	public static Logger log = Logger.getLogger("TnM");

	public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
	public String sheet="Login"; 
	public String url;
	public String id;
	public String paswd;


	public WebDriver driver;


	@BeforeTest
	public void setup()
	{

		System.setProperty("webdriver.gecko.driver", "C:\\Users\\SriSwathiAnushaNulu\\Documents\\Softwares\\geckodriver-v0.29.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();

		//System.setProperty("webdriver.chrome.driver","C:\\Users\\SanjayKushwaha\\Desktop\\Selenium\\chromedriver_win32\\chromedriver.exe");
		//driver = new ChromeDriver();

		//id = Excel.getCellValue(xlsFilePath, sheet, 1, 0);
		//paswd = Excel.getCellValue(xlsFilePath, sheet, 1, 1);
		url = Excel.getCellValue(xlsFilePath, sheet, 1, 2);

		//String url1 = "https://" +  id + ":" + paswd + "@" + url;

		driver.get(url);  
		driver.manage().window().maximize();
	}	

	// test to Login to the application as RIPC
	@Test(priority=0)
	public void RIPC_Login() throws IOException, InterruptedException 
	{

		Step02_ResubmitRequest login = new Step02_ResubmitRequest(driver);
		login.login();
	}
	@Test(priority=1)
	public void modifyRequestdetails() 
	{

		Step02_ResubmitRequest modifyreq_details = new Step02_ResubmitRequest(driver);
		modifyreq_details.searchRequest();
		modifyreq_details.resubmitReq();
		modifyreq_details.modifyRequestDetails();
	}
	@Test(priority=2)
	public void modifyRequestInfo() 
	{
		Step02_ResubmitRequest modifyreq_info = new Step02_ResubmitRequest(driver);
		modifyreq_info.searchRequest();
		modifyreq_info.resubmitReq();
		modifyreq_info.modifyRequestInfo();
	}
}


