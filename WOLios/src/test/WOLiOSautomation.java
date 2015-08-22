package test;

import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utilities.ExcelUtils;



public class WOLiOSautomation {
	//private static final Logger LOGGER=Logger.getLogger(WOLiOSautomation.class);
	private IOSDriver<WebElement> driver= null;

	FileInputStream fstream = null;
	File myFile = new File("//Users//deepti.pandey//Documents//Old-data//Selenium//TestData//WOLTestcases_12thAug.xlsx");
	XSSFWorkbook myWorkBook;
	XSSFSheet mySheet;
	int executioncount=0;
	int rowcount=0;
	Map<Integer, Object[]> testresultdata;   
	String dateformat =getDateTime(); 
	String resultsheet = "TestResult"+dateformat;


	private  final static String getDateTime()
	{
		DateFormat df = new SimpleDateFormat("yyyyMMdd_hhmmss");
		df.setTimeZone(TimeZone.getTimeZone("IST"));
		return df.format(new Date());
	}


	@BeforeMethod
	public void beforeMethod() throws Exception { //setup		 

		File appDir = new File("/Users/deepti.pandey/Documents/Old-data/Selenium/Apps/test/");
		final File app = new File (appDir,"WindstreamTest.app");
		final DesiredCapabilities capabilities = new DesiredCapabilities(); 
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "iOS");
		capabilities.setCapability("platformName", "iOS"); 
		capabilities.setCapability("platformVersion", "8.3"); 
		capabilities.setCapability("deviceName", "iPhone 6");
		capabilities.setCapability("nativeWebTap", "true");
		capabilities.setCapability("autoAcceptAlerts", "true");
		capabilities.setCapability("autoWebview", "false");
		capabilities.setCapability("app", app.getAbsolutePath()); 
		driver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		ExcelUtils.initializeExcelFile();

	} // BeforeMethod closed

	@AfterMethod
	public void afterMethod() {

	}	//AfterMethod closed

	@Test
	public void test() throws InterruptedException
	{
	
		WebElement usernameTextbox = driver.findElement(By.className("UIATextField"));
		WebElement passwordTextbox = driver.findElement(By.className("UIASecureTextField"));
		WebElement signINButton = driver.findElement(By.className("UIAButton"));
		WebElement kmlbutton = driver.findElement(By.className("UIATextField"));
		WebElement homeStaticText = driver.findElement(By.className("UIAStaticText"));
		List<WebElement> completeList = new ArrayList<WebElement>();
		//completeList.addAll(mLink);

		for (utilities.UserDetails user: ExcelUtils.USER_DETAILS )
		{
		try{	

			System.out.println("\nLogin with");
			driver.context("NATIVE_APP");

			{ 				
				driver.findElement(By.className("UIATextField")).click();

				driver.findElement(By.className("UIATextField")).sendKeys(user.getUserName());

				driver.findElement(By.className("UIASecureTextField")).click();

				driver.findElement(By.className("UIASecureTextField")).sendKeys(user.getPassword());
				Assert.assertEquals(signINButton.getAttribute("label"), "SIGN IN");
				if( usernameTextbox.getText().isEmpty()||passwordTextbox.getText().isEmpty())
				{
					System.out.println("error");
					throw new RuntimeException("blank credentials..Sign In Fail");
				}

				else 
				{
					driver.findElement(By.className("UIAButton")).click();
					Thread.sleep(8000);
				}

			}

			//Assert.assertEquals(homeStaticText.getAttribute("label"), "Windstream Online Mobile", "Login Successful");
			System.out.println("Login Successful");

			List<WebElement> mLink = driver.findElements(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[2]/UIAWebView[1]/UIALink"));
			System.out.println("Available links are : ");

			for (int i=0;i< mLink.size();i++)
			{				 

				mLink.get(i).click(); 

			}  



			// assertTrue(linkcell.isDisplayed());
		} catch (Exception e) { 
			System.out.println("error occured in wol simulation");   	 
			e.printStackTrace();
		} 

		}

	}// extended for loop closes		          
} //class ends
