package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import library.BasePageObject;

public class StatesWithPageFactory extends BasePageObject {
	
	String url = "https://app.missionhumane.org/";
	
	@FindBy(xpath = "//*[@class='location-column']")
	private WebElement states;
	
	public StatesWithPageFactory(WebDriver driver) 
	{
		super(driver);
		loadUrl(url);
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * protected void loadUrl() 
	 * { 
	 * loadUrl(url); 
	 * }
	 */
	
	public List<String> getStates() 
	{
		  System.out.println("Entered getStates function");
		  List<WebElement> statesList = (List<WebElement>) states;
		  List<String> stateNames = new ArrayList<String>();;
		  for(WebElement state : statesList) 
		  {
			  //dataMap.put(state.getText().toLowerCase(), null);
			  stateNames.add(state.getText().toLowerCase());
		  }
		  System.out.println("States updated----"+stateNames);
		  return stateNames;
	}
}
