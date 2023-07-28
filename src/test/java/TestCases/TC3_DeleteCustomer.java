package TestCases;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseClass;
import GenericUtilities.SwitchToUtility;
import GenericUtilities.CommonFunctions;
import GenericUtilities.ConfigReaderUtility;
import GenericUtilities.TestDataProviderForTC;
import Pages.DeleteCustomer;
import Pages.EditCustomer;
import Pages.HomePage;
import Pages.LoginPage;

public class TC3_DeleteCustomer extends BaseClass
{
	@Test
	void DeleteCstomer() throws InterruptedException
	{
		System.out.println("Log-INFO : Start of TC3_DeleteCustomer execution");
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		Map<String,String> map = new LinkedHashMap<String,String>();
		
		LoginPage login = new LoginPage(driver);
		login.LoginToApplication(ConfigReaderUtility.GetConfigProperty("LoginID"), ConfigReaderUtility.GetConfigProperty("LoginPassword"));
		Assert.assertTrue(driver.getCurrentUrl().contains("Managerhomepage"));
		System.out.println("Log-INFO : successfully logged in to application");
		
		HomePage home = new HomePage(driver);
		home.ClickOnDeleteCustomerLink();
		Assert.assertTrue(driver.getCurrentUrl().contains("DeleteCustomerInput"));
		System.out.println("Log-INFO : successfully navigated to DeleteCustomer page");
		
		lst = TestDataProviderForTC.getDataFromExcel("TC2_EditCustomer","TestData1","NewCustomer");
		DeleteCustomer delete = new DeleteCustomer(driver);
		for(int i=0;i<lst.size();i++)
		{
			map = lst.get(i);
			Assert.assertNotNull(delete.DeleteACustomer(map), "Unable to delete the customer with ID - " + map.get("CustomerID"));
			System.out.println("Log-INFO : successfully deleted the customer with ID - " + map.get("CustomerID"));
		}
		
		System.out.println("End of TC3_DeleteCustomer execution");
		
		
	}
}
