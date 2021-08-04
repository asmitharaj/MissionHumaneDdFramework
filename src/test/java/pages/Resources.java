package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import library.BasePageObject;

public class Resources extends BasePageObject {
	
	String url = "https://app.missionhumane.org/";
	States states;
	Cities cities;
	
	public Resources(WebDriver driver) 
	{
		super(driver);
		states = new States(driver);
	}
	
	public List<String> getResourcesInCities(String state, String city) 
	{
		System.out.println("Entered getResourcesInCities function - state : " + state + " ; city : "+ city);
		cities = new Cities(driver);
		//Map<String, List<String>> resourcesMap = new HashMap();
		List<String> cityNames = cities.getCitiesInState(state);
		List<String> resourceNames = new ArrayList<String>();
		if(cityNames.contains(city)) {			  
			loadUrl(url+state.split(" ")[0].toLowerCase()+"/"+city.split(" ")[0].toLowerCase());
			List<WebElement> resourcesList = driver.findElements(By.xpath("//*[@class='sc-eCApnc iylGhi']"));
			for(WebElement resource : resourcesList) 
			{
				String resourceName = resource.getText().toLowerCase();
				resourceNames.add(resourceName);			  
			}
			//dataMap.get(state).put(city.toLowerCase(), resourcesMap);
			//resourcesMap.put(city.toLowerCase(), null);
			System.out.println("Resources for state: "+state+" ---city: "+ city + " updated -- "+resourceNames);
		}
		return resourceNames;
	}

}
