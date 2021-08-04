package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import library.BasePageObject;

public class ResourcesData extends BasePageObject {
	
	String url = "https://app.missionhumane.org/";
	States states;
	Cities cities;
	Resources resources;
	
	public ResourcesData(WebDriver driver) 
	{
		super(driver);
		states = new States(driver);
	}
	
	  
	public List<String> getResourcesData(String state, String city, String resource) {
		System.out.println("Entered getResourcesData function");
		
		cities = new Cities(driver);
		resources = new Resources(driver);
		List<String> resourceNames = resources.getResourcesInCities(state, city);
		List<String> resourcesData = new ArrayList<String>();
		if(resourceNames.contains(resource)) {
			loadUrl(url+state.split(" ")[0].toLowerCase()+"/"+city.split(" ")[0].toLowerCase()+"/"+resource.split(" ")[0].toLowerCase());
			List<WebElement> resourcesDataList = driver.findElements(By.xpath("//*[@class='MuiTypography-root card-desc MuiTypography-body2']"));
			for(WebElement resourceElement : resourcesDataList) {
				String resourceName = resourceElement.getText().toLowerCase();
				resourcesData.add(resourceName);
			}
			//dataMap.get(state).get(city).put(resource, resourcesDataList);
			System.out.println("Resources Data for state: "+state+" ---city: "+ city + "---resource : " +resource +" updated -- "+resourcesData);
		}
		return resourcesData;
	}

}
