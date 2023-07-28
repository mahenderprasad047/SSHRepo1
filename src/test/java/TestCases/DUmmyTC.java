package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;


public class DUmmyTC 
{

	@Test
	public void DummyTCtoCheckLogs()
	{
		System.out.println("Inside Dummy Testcase");
		Assert.assertTrue(false);
	}
}
