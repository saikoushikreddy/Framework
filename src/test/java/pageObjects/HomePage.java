package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	@FindBy(xpath="//span[@class='caret']")
	WebElement linkmyaccount;
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement linkregister;
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement linklogin;
	public void clickMyAccount()
	{
		linkmyaccount.click();
	}
	public void clickRegister()
	{
		linkregister.click();
	}
	public void clicklogin()
	{
		linklogin.click();
	}
	

}
