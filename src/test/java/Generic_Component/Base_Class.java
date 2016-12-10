package Generic_Component;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Base_Class {
	
	public static Process process;
	public static  AppiumDriver driver;
	public static ExtentReports extentReports;
	public ExtentTest startTest;
	

	
	//@BeforeMethod(groups={"smoke"})
	public static void Start_Server() throws IOException, InterruptedException
	{
		String Start_server="D:\\Appium\\node.exe D:\\Appium\\node_modules\\appium\\bin\\appium.js";
		process = Runtime.getRuntime().exec(Start_server);
		
		
		if(process!=null)
		{
			
			System.out.println("Appium server is Running");
		}
		else
		{
			System.out.println("Not started the Server");
		}
		
		Thread.sleep(12000);
		
		
	}
	
	//@BeforeSuite(groups={"smoke"})
public void intitalSetup(){
		
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh-mm-ss");
		String reportdate = simpleDateFormat.format(date);
		
		extentReports = new ExtentReports("D:\\September_BB_Proj\\Report\\" + "Bigbasketreport_" + reportdate + ".html"    ,false);
		
		
	}
	
	
	public static void InitializeApp() throws InterruptedException, IOException
	{
		DesiredCapabilities capabilities= new DesiredCapabilities();
		
		//device details
		capabilities.setCapability("deviceName", "GT-I9300I");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4.4");
		
		//app details
		capabilities.setCapability("appPackage",Utility_Class.Reading_properties("Package_name") );
		capabilities.setCapability("appActivity",Utility_Class.Reading_properties("Activity_name") );
		
		//Appium Server details
		 driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
		Thread.sleep(4000);
	}
	
	
	public void Explicit_Wait(WebElement ele, long T1)
	{
		WebDriverWait wait = new WebDriverWait(driver, T1);
		wait.until(ExpectedConditions.visibilityOf(ele)).isDisplayed();
		
	}
	
	
	public void Capture_Screenshot(String TC_ID, String Order) throws IOException
	{
		Date date= new Date();
		SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		File file= new File(df.format(date)+".png");
		
		
		TakesScreenshot screenshot=(TakesScreenshot) driver;
		File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File("D:\\September_BB_Proj\\Screenshot\\"+TC_ID+"-"+Order+"-"+file));
		
	}
	
	
	//@AfterMethod(groups={"smoke"})
	public void Stop_Server() throws InterruptedException
	{
		if(process!=null)
		{
			process.destroy();
			Thread.sleep(8000);
			System.out.println("Stopped the Appium Server");
			
			
		}
		
//		extentReports.endTest(startTest);
//		extentReports.flush();
	}

}
