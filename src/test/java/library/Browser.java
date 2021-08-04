package library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Browser {

	static WebDriver driver;

    public static WebDriver StartBrowser(String browsername)
    {
       // If the browser is Firefox
       if(browsername.equalsIgnoreCase("Firefox"))
       {
    	   // Set the path for geckodriver.exe
    	   System.setProperty("webdriver.firefox.marionette","/Applications/geckodriver");
    	   driver = new FirefoxDriver();
       }
       // If the browser is Chrome
	   else if(browsername.equalsIgnoreCase("Chrome"))
	   {
		   // Set the path for chromedriver.exe
		   System.setProperty("webdriver.chrome.driver","/Applications/chromedriver");
	       driver = new ChromeDriver();
	   }
	   // If the browser is IE
	   else if(browsername.equalsIgnoreCase("IE"))
	   {
		   // Set the path for IEdriver.exe
		   System.setProperty("webdriver.ie.driver","/Applications/IEDriverServer");
	       driver = new InternetExplorerDriver();
	   }
       //driver.manage().window().maximize();
       //driver.get(url);
       return driver;
     }
}