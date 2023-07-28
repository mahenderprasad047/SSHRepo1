package GenericUtilities;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonFunctions 
{

	//To wait for sometime - Hard wait
	public static void WaitForFewSeconds(int time)
	{
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	//To Highlight the a Object
	public static void HighlightWebElement(WebDriver driver, WebElement ele)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.background='yellow'", ele);
	}
	
}
