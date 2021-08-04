package tests;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import library.Browser;
import library.GetExcelData;
import pages.Resources;

public class TestResources {
	
	WebDriver driver;
	Resources resources;
	
	// Launch of the given browser.
	@BeforeTest
	public void browserlaunch()
	{
		driver = Browser.StartBrowser("Chrome");
	}
	
	@Test(dataProvider="resources")
	public void verifyResources(String state, String city, String resource) {
		resources = new Resources(driver);
		List<String> resourceNames = resources.getResourcesInCities(state, city);
		//System.out.println("before assert------"+dataMap.get(state));
		if(resourceNames!=null) {
			//System.out.println("in assert-----"+dataMap.get(state).get(city));
			Assert.assertTrue(resourceNames.contains(resource));
		} else {
			Assert.assertTrue(false);
		}
	}
	  
	@DataProvider(name="resources")
	public Object[] passExcelResources() {
		GetExcelData excelData = new GetExcelData();
		Object[][] stateCitiesResourcesList = excelData.getData(3);
		return stateCitiesResourcesList;
	}
	
	 @AfterTest
	  public void closeBrowser()
	  {
		  driver.close();
		  driver.quit();
	  }
}
