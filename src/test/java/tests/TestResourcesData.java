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
import pages.ResourcesData;

public class TestResourcesData {

	WebDriver driver;
	ResourcesData resourcesData;

	// Launch of the given browser.
	@BeforeTest
	public void browserlaunch() {
		driver = Browser.StartBrowser("Chrome");
	}

	@Test(dataProvider = "resourcesData")
	public void verifyResourcesData(String state, String city, String resource, String resourceData) {
		System.out.println("Sheet input -- state : " + state + "____city : " + city + "____res : " + resource
				+ "____res data : " + resourceData);
		resourcesData = new ResourcesData(driver);
		List<String> resourceDataDetails = resourcesData.getResourcesData(state, city, resource);
		System.out.println(" assert------" + resourceDataDetails + "_______" + resourceData.replaceAll("\\s", ""));
		System.out.println("before assert------" + resourceDataDetails.contains(resourceData.replaceAll("\\s", "")));
		boolean check = false;
		if (resourceDataDetails != null) {
			for (String resourceDataDetail : resourceDataDetails) {
				if (resourceDataDetail.contains(resourceData.replaceAll("\\s", ""))) {
					check = true;
					break;
				}
			}
		}
		Assert.assertTrue(check);
	}

	@DataProvider(name = "resourcesData")
	public Object[] passExcelResourcesData() {
		GetExcelData excelData = new GetExcelData();
		Object[][] stateCitiesResourcesDataList = excelData.getData(4);
		return stateCitiesResourcesDataList;
	}

	@AfterTest
	public void closeBrowser() {
		driver.close();
		driver.quit();
	}

}
