package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase{
	
	@FindBy(xpath = "//a[@title='Contacts']")  // -- //a[contains(text(), 'Contacts')]
	@CacheLookup
	WebElement contactsLable;
	
	@FindBy(id = "first_name")
	@CacheLookup
	WebElement firstName;
	
	@FindBy(id = "surname")
	@CacheLookup
	WebElement lastName;
	
	@FindBy(name = "client_lookup")
	@CacheLookup
	WebElement company;
	
	@FindBy(xpath = "//input[@type='submit' and @value='Save']")
	@CacheLookup
	WebElement saveBtn;
	
	//initializing the page object
	public ContactsPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyContactsLable()
	{
		return contactsLable.isDisplayed();
	}
	
	public void selectContactsByName(String name)
	{
		driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td[@class='datalistrow']"
				+ "//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']")).click();
	}
	
	public void createNewContact(String title, String ftName, String ltName, String comp)
	{
		Select select = new Select(driver.findElement(By.name("title")));
		select.selectByVisibleText(title);
		
		firstName.sendKeys(ftName);
		lastName.sendKeys(ltName);
		company.sendKeys(comp);
		saveBtn.click();
		
	}
}
