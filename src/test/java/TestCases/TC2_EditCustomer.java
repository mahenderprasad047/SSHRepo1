package TestCases;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseClass;
import GenericUtilities.CommonFunctions;
import GenericUtilities.ConfigReaderUtility;
import GenericUtilities.TestDataProviderForTC;
import Pages.EditCustomer;
import Pages.HomePage;
import Pages.LoginPage;

public class TC2_EditCustomer extends BaseClass
{
	
	@Test
	public void EditCustomers() throws InterruptedException
	{
		System.out.println("Log-INFO : Start of TC2_EditCustomer execution");
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		Map<String,String> map = new LinkedHashMap<String,String>();
		
		LoginPage login = new LoginPage(driver);
		login.LoginToApplication(ConfigReaderUtility.GetConfigProperty("LoginID"), ConfigReaderUtility.GetConfigProperty("LoginPassword"));
		Assert.assertTrue(driver.getCurrentUrl().contains("Managerhomepage"),"URL mismatched after login to Application");
		System.out.println("Log-INFO : successfully logged in to application");
		
		HomePage home = new HomePage(driver);
		home.ClickOnEditCustomerLink();
		Assert.assertTrue(driver.getCurrentUrl().contains("EditCustomer"),"URL mismatched after clicking on EditCustomer link");
		System.out.println("Log-INFO : successfully navigated to EditCustomer page");
		
		lst = TestDataProviderForTC.getDataFromExcel("TC2_EditCustomer","TestData1","NewCustomer");
		EditCustomer edt= new EditCustomer(driver);
		for(int i=0;i<lst.size();i++)
		{
			map = lst.get(i);			
			Assert.assertNotNull(edt.EditACustomer(map), "Unable to Edit the customer with ID - " + map.get("CustomerID"));
			System.out.println("Log-INFO : successfully updated details for customer with ID - " + map.get("CustomerID"));
			CommonFunctions.WaitForFewSeconds(2);
		}
		
		System.out.println("Log-PASS : End of TC2_EditCustomer execution");
	}
}
