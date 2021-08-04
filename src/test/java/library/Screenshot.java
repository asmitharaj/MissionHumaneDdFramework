package library;

import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
 
  public static void captureScreenShot(WebDriver driver, String ScreenShotName)
  {
	  try 
	  {
		  File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  } 
	  catch (Exception e)
      {
		  System.out.println(e.getMessage());
          e.printStackTrace();
      }
   }
}