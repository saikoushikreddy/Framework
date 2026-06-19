package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class registrationPage extends BasePage
{
	public registrationPage(WebDriver driver)
	{
		super(driver);
	}
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement firstname;
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement lastname;
	@FindBy(xpath="//input[@id='input-email']")
	WebElement email;
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement phone;
	@FindBy(xpath="//input[@id='input-password']")
	WebElement password;
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement conformpassword;
	@FindBy(xpath="//input[@name='agree']")
	WebElement agree;
	@FindBy(xpath="//input[@value='Continue']")
	WebElement submit;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confmessage;
	public void firstname(String fname)
	{
		firstname.sendKeys(fname);
	}
	public void lastname(String lname)
	{
		lastname.sendKeys(lname);
	}
	public void email(String Email)
	{
		email.sendKeys(Email);
	}
	public void phone(String ph)
	{
		phone.sendKeys(ph);
	}
	public void password(String pass)
	{
		password.sendKeys(pass);
	}
	public void confpass(String cpass)
	{
		conformpassword.sendKeys(cpass);
	}
	public void accept()
	{
		agree.click();
	}
	public void sub()
	{
		submit.click();
	}
	public String getmessage()
	{
		try {
			return (confmessage.getText());
		}
		catch(Exception e)
		{
			return(e.getMessage());
		}
	}
}
