package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import library.BasePageObject;

public class Cities extends BasePageObject 
{
	String url = "https://app.missionhumane.org/";
	States states;
	List<String> stateNames;
	
	public Cities(WebDriver driver) 
	{
		super(driver);
		states = new States(driver);
	}

	public List<String> getCitiesInState(String state) {
		  System.out.println("Entered getCitiesInState function");
		  stateNames = states.getStates();
		  List<String> cityNames = new ArrayList<String>();
		  //Map<String, Map<String, List<String>>> citiesMap = new HashMap();
		  if(stateNames.contains(state)) {
			  loadUrl(url+state.split(" ")[0].toLowerCase());
			  List<WebElement> citiesList = driver.findElements(By.xpath("//*[@class='location-column']"));
			  for(WebElement city : citiesList) {
				  String cityName = city.getText().toLowerCase();
				  cityNames.add(cityName);
			  }
			  //dataMap.put(state, citiesMap);
			  System.out.println("Cities for state: "+state+ " updated -- "+cityNames);
		  }
		  return cityNames;
	}
}
