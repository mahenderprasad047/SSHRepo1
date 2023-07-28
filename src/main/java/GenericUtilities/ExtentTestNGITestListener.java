package GenericUtilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentTestNGITestListener implements ITestListener
{
	
	public static ExtentReports ext = getInstance();
	ExtentTest child;
	ThreadLocal<ExtentTest> parent = new ThreadLocal<ExtentTest>();
	static ExtentReports ext1;
	
	public synchronized void onStart(ITestContext result)
	{
		System.out.println("LOG-INFO : Inside onStart function");
	}
	
	public synchronized void onFinish(ITestContext result)
	{
		System.out.println("LOG-INFO : Inside onFinish function");
		ext.flush();
	}
	
	public synchronized void onTestStart(ITestResult result)
	{
		System.out.println("LOG-INFO : Inside onTestStart function.TestCase Name -" + result.getMethod().getMethodName());
		child = ext.createTest(result.getMethod().getMethodName());
		parent.set(child);
	}
	
	public synchronized void onTestSuccess(ITestResult result)
	{
		System.out.println("LOG-INFO : Inside onTestSuccess function.TestCase Name -" + result.getMethod().getMethodName());
		parent.get().pass("TestCase Name -" + result.getMethod().getMethodName());
	}
	
	public synchronized void onTestFailure(ITestResult result)
	{
		System.out.println("LOG-INFO : Inside onTestFailure function.TestCase Name -" + result.getMethod().getMethodName());
		parent.get().fail("TestCase Name -" +result.getMethod().getMethodName());
	}
	
	public synchronized void onTestSkipped(ITestResult result)
	{
		System.out.println("LOG-INFO : Inside onTestSkipped function.TestCase Name -" + result.getMethod().getMethodName());
	}
	
	public static ExtentReports getInstance()
	{
		if(ext == null)
		{
			String path = System.getProperty("user.dir") + "/Reports/ExecutionReport_"+ System.currentTimeMillis() + ".html";
			System.out.println("Path : "+ path);
			ExtentSparkReporter reporter = new ExtentSparkReporter(path);	
			ext1 = new ExtentReports();
			ext1.attachReporter(reporter);
			return ext1;
		}
		return ext;
	}
	/*
		
	<listeners>
		<listener class-name="GenericUtilities.ExtentTestNGITestListener"></listener>
	</listeners>
	
	*/
}
