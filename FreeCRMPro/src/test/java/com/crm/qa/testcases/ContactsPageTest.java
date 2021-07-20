package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.TasksPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactPage;
	TestUtil testUtil;
	
	String sheetName = "contacts";
	
	public ContactsPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		intialization();
		testUtil = new TestUtil();
		homePage = new HomePage();
		contactPage = new ContactsPage();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		contactPage = homePage.clickOnContactsLink();	
	}
	
	@Test(priority=1)
	public void verifyContactsPageLableTest()
	{
		Assert.assertTrue(contactPage.verifyContactsLable());
		
		System.out.println("Test one testcase1");
		System.out.println("Test two testcase1");
		System.out.println("Test three testcase1");
	}
	
	@Test(priority=2)
	public void verifySelectContactsNameTest()
	{
		contactPage.selectContactsByName("Apple Malvia");
	}
	
	@Test(priority=3)
	public void verifyMultiSelectContactsNameTest()
	{
		contactPage.selectContactsByName("Jerry Peter");
		contactPage.selectContactsByName("David Cris");
	}
	
	@DataProvider
	public Object[][] getCRMContactsTestData()
	{
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=4, dataProvider = "getCRMContactsTestData")
	public void validateCreateNewContact(String title, String firstName, String lastName, String company)
	{
		homePage.clickOnNewContactLink();
		//contactPage.createNewContact("Dr.", "Jaxon", "Groage", "Google");
		contactPage.createNewContact(title, firstName, lastName, company);
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}

}
