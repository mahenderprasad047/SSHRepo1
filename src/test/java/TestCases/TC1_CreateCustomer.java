package TestCases;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseClass;
import GenericUtilities.ConfigReaderUtility;
import GenericUtilities.TestDataProviderForTC;
import Pages.AddNewCustomer;
import Pages.HomePage;
import Pages.LoginPage;

public class TC1_CreateCustomer extends BaseClass
{

	@Test	//(dataProvider = "login",dataProviderClass = DataProvidersForTCs.class)
	public void CreateCustomers() throws InterruptedException 
	{
		System.out.println("LOG-INFO : Start of TC1_CreateCustomer Execution");
		
		
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		Map<String,String> map = new LinkedHashMap<String,String>();
		List<String> lstCustID = new ArrayList<String>();
		
		int i;
		String sCustomerID;
		
		LoginPage login = new LoginPage(driver);
		login.LoginToApplication(ConfigReaderUtility.GetConfigProperty("LoginID"), ConfigReaderUtility.GetConfigProperty("LoginPassword"));
		Assert.assertTrue(driver.getCurrentUrl().contains("Managerhomepage"),"Login to application failed - URL mismatched");
		System.out.println("Log-INFO : successfully logged in to application");
		
		HomePage home = new HomePage(driver);
		home.ClickOnNewCustomerLink();
		Assert.assertTrue(driver.getCurrentUrl().contains("addcustomerpage"),"URL mismatched after clicking on 'New Customer' link");
		System.out.println("Log-INFO : Add New Customer page appears");
		
		lst = TestDataProviderForTC.getDataFromExcel("TC1_CreateCustomer","TestData1","NewCustomer");
		AddNewCustomer addCustomer = new AddNewCustomer(driver);
		for(i=0;i<lst.size();i++)
		{
			map = lst.get(i);
			
			System.out.println("Log-INFO : Adding a new customer with CustomerName - "+ map.get("CustomerName"));
			addCustomer.AddANewCustomer(map);
			sCustomerID = addCustomer.GetCustomerIDFromCustomerRegisteredPage();
			Assert.assertNotNull(sCustomerID, "Unable a register a new customer. Customer Name - "+ map.get("CustomerName"));
		
			lstCustID.add(sCustomerID);
			System.out.println("Log-INFO : Customer ID is " + sCustomerID);
			
			//Thread.sleep(4000);
			driver.navigate().back();
			
		}
		
		//Update TestData sheet with new Customer ID
		TestDataProviderForTC.SetDataToAColumnOfExcel("TC1_CreateCustomer","TestData1","NewCustomer", lstCustID, "CustomerID");
		TestDataProviderForTC.SetDataToAColumnOfExcel("TC2_EditCustomer","TestData1","NewCustomer", lstCustID, "CustomerID");
		TestDataProviderForTC.SetDataToAColumnOfExcel("TC3_DeleteCustomer","TestData1","NewCustomer", lstCustID, "CustomerID");
		
		System.out.println("Log-PASS : End of TC1_CreateCustomer execution");
		
		
	}
	
	
	
}
