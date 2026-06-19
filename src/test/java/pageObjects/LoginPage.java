package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	@FindBy(xpath="//input[@id='input-email']")
	WebElement Emaillink;
	@FindBy(xpath="//input[@id='input-password']")
	WebElement passwordlink;
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginlink;
	public void email(String mail)
	{
		Emaillink.sendKeys(mail);
	}
	public void password(String passwor)
	{
		passwordlink.sendKeys(passwor);
	}
	public void login()
	{
		loginlink.click();
	}

}
