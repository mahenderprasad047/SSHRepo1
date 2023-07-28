package Base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import GenericUtilities.BrowserUtilities;
import GenericUtilities.ConfigReaderUtility;

public class BaseClass 
{
	public WebDriver driver;
	
	public WebDriver getDriver()
	{
		return driver;
	}
	
	@BeforeClass
	public void LaunchApplication() throws InterruptedException 
	{
		//System.out.println("LOG:INFO - Launching Application");
		String sBrowser = ConfigReaderUtility.GetConfigProperty("Browser");
		String sURL = ConfigReaderUtility.GetConfigProperty("ApplicationURL");
		
		driver = BrowserUtilities.startBrowser(sBrowser, sURL);
		System.out.println("LOG:INFO - Browser is launched");
	}
	
	@AfterClass
	public void CloseApplication()
	{
		driver.quit();
		System.out.println("LOG:INFO - Closing Browser");
	}
	
	//@BeforeSuite
	public void StoreLogsToTextFile()
	{
		String path = System.getProperty("user.dir") + "/Reports/LastExecutionLogs.txt";
		File file = new File(path);
		PrintStream stream = null;
		try {
			stream = new PrintStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("From now on "+file.getAbsolutePath()+" will be your console");
		System.setOut(stream);
	}
}
