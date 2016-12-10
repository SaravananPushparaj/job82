package Scenario_Component;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Generic_Component.Base_Class;
import PageObject_Component.PageObject_Search;

public class Scenario_Search extends Base_Class {
	
	static Logger log= Logger.getLogger(Scenario_Search.class);
	SoftAssert sAssert= new SoftAssert();
	
	@Test(dataProvider="dp_InvalidSearch",dataProviderClass=DataProvider_Component.DataProvider_Search.class, groups={"smoke"})
	public void testInvalidSearch(Map Search) throws IOException, InterruptedException
	{
		
		String TC_ID = Search.get("TC_ID").toString();
		String Order = Search.get("Order").toString();
		String Search_item = Search.get("Search_item").toString();
		String Exp_Result = Search.get("Exp_Result").toString();
		
	    //startTest = extentReports.startTest(TC_ID);
		log.info("Executing the TC_ID  " +TC_ID + "and"+ "Order is " +Order);
		
		Start_Server();
		InitializeApp();
		//startTest.log(LogStatus.PASS, "Succesfully launched the app");
		//startTest.log(LogStatus.PASS, "Succesfully launched the app" );
				
		PageObject_Search BS_Pob= new PageObject_Search(driver);
		
		Explicit_Wait(BS_Pob.Search_btn, 25);
		BS_Pob.Click_Searchbtn();
		
		Explicit_Wait(BS_Pob.Search_txt, 25);
		BS_Pob.Enter_Searchtxt(Search_item);
		
		Explicit_Wait(BS_Pob.Invalid_msg, 25);
		String Actual_Result = BS_Pob.getInvalidmsg();
		
		if(Actual_Result.equals(Exp_Result))
		{
			log.info("Passed as Actual Result is  " +Actual_Result+" and "+ "Expected Result " +Exp_Result);
			Capture_Screenshot(TC_ID, Order);
			//startTest.log(LogStatus.PASS, "Passed as Actual Result is  " +Actual_Result+" and "+ "Expected Result " +Exp_Result);
			
		}
		else
		{
			log.info("Failed as Actual Result is  " +Actual_Result+" and Expected Result " +Exp_Result);
			Capture_Screenshot(TC_ID, Order);
			//startTest.log(LogStatus.FAIL, "Failed as Actual Result is  " +Actual_Result+" and "+ "Expected Result " +Exp_Result);
			sAssert.fail("Failed as Actual Result is  " +Actual_Result+" and Expected Result " +Exp_Result);
		}
		
		
		//Stop_Server();
		sAssert.assertAll();
		
		
	}
	
	
	@Test(dataProvider="dp_ValidSearch",dataProviderClass=DataProvider_Component.DataProvider_Search.class,groups={"regression"})
	public void testValidSearch(Map Search) throws IOException, InterruptedException
	{
		
		String TC_ID = Search.get("TC_ID").toString();
		String Order = Search.get("Order").toString();
		String Search_item = Search.get("Search_item").toString();
		String Exp_Result = Search.get("Exp_Result").toString().replace(".0", "");
		
		log.info("Executing the TC_ID  " +TC_ID + "and"+ "Order is " +Order);
	     Start_Server();
		InitializeApp();
		//startTest = extentReports.startTest(TC_ID);
		
		PageObject_Search BS_Pob= new PageObject_Search(driver);
		
		Explicit_Wait(BS_Pob.Search_btn, 25);
		BS_Pob.Click_Searchbtn();
		
		Explicit_Wait(BS_Pob.Search_txt, 25);
		BS_Pob.Enter_Searchtxt(Search_item);
		
		Explicit_Wait(BS_Pob.Valid_msg, 25);
		String Output = BS_Pob.getValidmsg();
		
		String Actual_Result = Output.replace(" products", "");
		
		if(Actual_Result.equals(Exp_Result))
		{
			log.info("Passed as Actual Result is  " +Actual_Result+" and "+ "Expected Result " +Exp_Result);
			//startTest.log(LogStatus.PASS, "Passed as Actual Result is  " +Actual_Result+" and "+ "Expected Result " +Exp_Result);
		}
		else
		{
			log.info("Failed as Actual Result is  " +Actual_Result+" and Expected Result " +Exp_Result);
			//startTest.log(LogStatus.FAIL, "Failed as Actual Result is  " +Actual_Result+" and "+ "Expected Result " +Exp_Result);
			sAssert.fail("Failed as Actual Result is  " +Actual_Result+" and Expected Result " +Exp_Result);
		}
		
		
		Stop_Server();
		sAssert.assertAll();
		
		
	}

}
