package pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import library.BasePageObject;

public class States extends BasePageObject{
	
	WebDriver driver;
	Map<String, Map<String, Map<String, List<String>>>> dataMap = new HashMap();
	String url = "https://app.missionhumane.org/";
	
	public States(WebDriver driver) 
	{
		super(driver);
	}
	
	protected void loadUrl()
	{
		loadUrl(url);
	}
	
	public List<String> getStates() 
	{
		  System.out.println("Entered getStates function");
		  List<WebElement> statesList = driver.findElements(By.xpath("//*[@class='location-column']"));
		  List<String> stateNames = new ArrayList();;
		  for(WebElement state : statesList) 
		  {
			  //dataMap.put(state.getText().toLowerCase(), null);
			  stateNames.add(state.getText().toLowerCase());
		  }
		  System.out.println("States updated----"+stateNames);
		  return stateNames;
	}
}
