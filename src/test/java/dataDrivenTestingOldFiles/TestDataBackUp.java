package dataDrivenTestingOldFiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataDrivenTestingOldFiles.GetExcelData;

public class TestDataBackUp {
	
  WebDriver driver;
  Map<String, Map<String, List<String>>> dataMap = new HashMap();
  String Url = "https://app.missionhumane.org/";
	
  @BeforeTest
  public void loadPage() {
	  System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
	  driver = new ChromeDriver();
	  loadUrl(Url);
  }
  
  public void loadUrl(String url) {
	  driver.get(url);
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
  }
  
  public void getStates() {
	  System.out.println("Entered getStates function");
	  List<WebElement> statesList = driver.findElements(By.xpath("//*[@class='location-column']"));
	  for(WebElement state : statesList) {
		  dataMap.put(state.getText().toLowerCase(), null);
	  }
	  System.out.println("States updated----"+dataMap);
  }
  
  @Test(dataProvider="states")
  public void verifyStates(String data) {
	  getStates();
	  Assert.assertTrue(dataMap.keySet().contains(data));
  }
  
  @DataProvider(name="states")
  public Object[] passExcelStates() {
	  Object[] stateList = passExcelData(1);
	  return stateList;
  }

  public void getCitiesInState(String state) {
	  System.out.println("Entered getCitiesInState function");
	  Map<String, List<String>> citiesMap = new HashMap();
	  if(dataMap.keySet().contains(state)) {
		  loadUrl(Url+state.split(" ")[0].toLowerCase());
		  List<WebElement> citiesList = driver.findElements(By.xpath("//*[@class='location-column']"));
		  for(WebElement city : citiesList) {
			  String cityName = city.getText().toLowerCase();
			  citiesMap.put(cityName, null);
		  }
		  dataMap.put(state, citiesMap);
	  }
	  System.out.println("Cities for state: "+state+ " updated -- "+dataMap);
  }
  
  @Test(dataProvider="cities")
  public void verifyCities(String state, String city) {
	  getStates();
	  getCitiesInState(state);
	  if(dataMap.get(state)!=null) {
		  Assert.assertTrue(dataMap.get(state).keySet().contains(city));
	  } else {
		  Assert.assertTrue(false);
	  }
  }
  
  @DataProvider(name="cities")
  public Object[] passExcelCities() {
	  Object[][] stateCitiesList = passExcelData(2);
	  return stateCitiesList;
  }
  
  
  public void getResourcesInCities(String state, String city) {
	  System.out.println("Entered getResourcesInCities function");
	  List<String> resourcesNamesList = new ArrayList();
	  if(dataMap.keySet().contains(state) && dataMap.get(state).keySet().contains(city)) {
		  loadUrl(Url+state.split(" ")[0].toLowerCase()+"/"+city.split(" ")[0].toLowerCase());
		  List<WebElement> resourcesList = driver.findElements(By.xpath("//*[@class='sc-eCApnc iylGhi']"));
		  for(WebElement resource : resourcesList) {
			  String resourceName = resource.getText().toLowerCase();
			  resourcesNamesList.add(resourceName);
		  }
		  dataMap.get(state).put(city.toLowerCase(), resourcesNamesList);
	  }
	  System.out.println("Resources for state: "+state+" ---city: "+ city + " updated -- "+dataMap);
  }
  
  @Test(dataProvider="resources")
  public void verifyResources(String state, String city, String resource) {
	  System.out.println("state---"+state+"____city----"+city+"------res---"+resource);
	  if(dataMap.isEmpty())
	  {
		  getStates();
	  } 
	  if(dataMap.get(state)==null || dataMap.get(state).isEmpty()) {
		  getCitiesInState(state);
	  } 
	  if(dataMap.get(state)==null || dataMap.get(state).isEmpty() || dataMap.get(state).get(city)==null) {
		  getResourcesInCities(state, city);
	  }
	  //System.out.println("before assert------"+dataMap.get(state));
	  if(dataMap.get(state)!=null && dataMap.get(state).get(city)!=null) {
		  //System.out.println("in assert-----"+dataMap.get(state).get(city));
		  Assert.assertTrue(dataMap.get(state).get(city).contains(resource));
	  } else {
		  Assert.assertTrue(false);
	  }
  }
  
  @DataProvider(name="resources")
  public Object[] passExcelResources() {
	  Object[][] stateCitiesResourcesList = passExcelData(3);
	  return stateCitiesResourcesList;
  }
  
  /*public Object[] passExcelData(int column) {
	  ExcelDataConfig edf = new ExcelDataConfig("src/main/resources/MissionHumaneData.xlsx");
	  int rowCount = edf.getRowCount(0);
	  Object[] data = new Object[rowCount];
	  for(int i=0; i<rowCount; i++) {
		  data[i] = edf.getData(0, i, column);
	  }
	  return data;
  }
  
  public Object[][] passExcelData(int column1, int column2) {
	  ExcelDataConfig edf = new ExcelDataConfig("src/main/resources/MissionHumaneData.xlsx");
	  int rowCount = edf.getRowCount(0);
	  Object[][] data = new Object[rowCount][2];
	  for(int i=0; i<rowCount; i++) {
		  data[i][0] = edf.getData(0, i, column1);
		  data[i][1] = edf.getData(0, i, column2);
	  }
	  return data;
  }
  */
  
  
  public Object[][] passExcelData(int column) {
	  GetExcelData excelData = new GetExcelData();
	  Object[][] data = excelData.getData(column);
	  return data;
  }
  
  @AfterTest
  public void afterTest() {
	  driver.close();
	  driver.quit();
  }
  
}
