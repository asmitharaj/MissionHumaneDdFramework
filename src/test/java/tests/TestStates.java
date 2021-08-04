package tests;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import library.Browser;
import pages.States;
import library.GetExcelData;

public class TestStates {
	
	WebDriver driver;
	States states;
	
	// Launch of the given browser.
	@BeforeTest
	public void browserlaunch()
	{
		driver = Browser.StartBrowser("Chrome");
	    states = new States(driver);
	}
		
	@Test(dataProvider="states")
	public void verifyStates(String data) 
	{
		List<String> stateNames = states.getStates();
		Assert.assertTrue(stateNames.contains(data));
	}
	  
	@DataProvider(name="states")
	public Object[] passExcelStates() 
	{
		GetExcelData excelData = new GetExcelData();
		Object[] stateList = excelData.getData(1);
		return stateList;
	}
	
	@AfterTest
	public void closeBrowser()
	{
		driver.close();
		driver.quit();
	}
}
