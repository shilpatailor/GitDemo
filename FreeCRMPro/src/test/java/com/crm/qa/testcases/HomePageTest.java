package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.TasksPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactPage;
	DealsPage dealsPage;
	TasksPage tasksPage;
	TestUtil testUtil;

	
	public HomePageTest()
	{
		super();
	}

	//test cases should be separated -- independent with each other 
	//before each test cases -- launch the browser and login
	//@test -- execute test cases
	//after each test cases -- close the browser
	
	@BeforeMethod
	public void setUp() throws InterruptedException
	{
		//testBase = new testBase();
		intialization();
		testUtil = new TestUtil();
		contactPage = new ContactsPage();
		dealsPage = new DealsPage();
		tasksPage = new TasksPage();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Thread.sleep(2000);
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest()
	{
		String homePageTitle = homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "CRMPRO", "Home page title is not match");
	}
	
	@Test(priority=2)
	public void verifyCorrrectUserNameTest()
	{
		testUtil.switchToFrame();
		Assert.assertTrue(homePage.verifyCorrectUserName());
		
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest()
	{
		testUtil.switchToFrame();
		contactPage = homePage.clickOnContactsLink();
	}
	
	@Test(priority=4)
	public void verifyDealsLinkTest()
	{
		testUtil.switchToFrame();
		dealsPage = homePage.clickOnDealsLink();
	}
	
	@Test(priority=5)
	public void verifyTasksLinkTest()
	{
		testUtil.switchToFrame();
		tasksPage = homePage.clickOnTasksLink();
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}
}
