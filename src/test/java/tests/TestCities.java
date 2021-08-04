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
import pages.Cities;

public class TestCities {
	
	WebDriver driver;
	Cities cities;
	
	// Launch of the given browser.
	@BeforeTest
	public void browserlaunch()
	{
		driver = Browser.StartBrowser("Chrome");
	}
	
	 @Test(dataProvider="cities")
	  public void verifyCities(String state, String city) {
		  cities = new Cities(driver);
		  List<String> cityNames = cities.getCitiesInState(state);
		  if(cityNames!=null) {
			  Assert.assertTrue(cityNames.contains(city));
		  } else {
			  Assert.assertTrue(false);
		  }
	  }
	  
	  @DataProvider(name="cities")
	  public Object[] passExcelCities() {
		  GetExcelData excelData = new GetExcelData();
		  Object[][] stateCitiesList = excelData.getData(2);
		  return stateCitiesList;
	  }
	  
	  @AfterTest
	  public void closeBrowser()
	  {
		  driver.close();
		  driver.quit();
	  }
}
